/*
 * generated by Xtext
 */
package org.osate.verify.validation

import org.eclipse.xtext.validation.Check
import org.osate.verify.verify.VerificationActivity
import org.osate.verify.verify.VerificationCondition
import org.osate.verify.verify.VerificationMethod
import org.osate.verify.verify.VerifyPackage
import org.osate.verify.verify.JavaMethod
import org.osate.verify.verify.PluginMethod
import org.osate.verify.util.VerificationMethodDispatchers
import org.osate.verify.verify.Claim
import static org.osate.verify.util.VerifyUtilExtension.*
import org.osate.verify.verify.VerificationPlan
import org.eclipse.xtext.validation.CheckType
import org.eclipse.emf.ecore.util.EcoreUtil

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
class VerifyValidator extends AbstractVerifyValidator {

  public static val INCORRECT_METHOD_PATH = "org.osate.verify.incorrectMethodPath"
  public static val INCORRECT_METHOD_REFERENCE = "org.osate.verify.incorrectMethodReference"
  public static val MISSING_METHOD_REFERENCE = "org.osate.verify.missingMethodReference"
  public static val INCORRECT_METHOD_ID = "org.osate.verify.incorrectMethodID"
  public static val CLAIM_MISSING_REQUIREMENT = "org.osate.verify.claimMissingRequirement"
  public static val CLAIM_INVALID_REQUIREMENT = "org.osate.verify.claimInvalidRequirement"
  public static val MISSING_CLAIM_FOR_REQ = "org.osate.verify.missingClaimForReq"
  public static val CLAIM_REQ_FOR_NOT_VP_FOR = "org.osate.verify.claimReqForNotVpFor"

	@Check
	def checkMethodPath(JavaMethod method) {
		val result = VerificationMethodDispatchers.eInstance.methodExists(method)
		if (result!=null) {
			warning("Could not method: "+result, 
					VerifyPackage.Literals.JAVA_METHOD__METHOD_PATH,
					INCORRECT_METHOD_PATH)
		}
	}
	@Check
	def checkMethodID(PluginMethod method) {
		val result = VerificationMethodDispatchers.eInstance.dispatchVerificationMethod(method,null,null)
		if (result == null) {
			warning('Plugin verification method ID not found', 
					VerifyPackage.Literals.PLUGIN_METHOD__METHOD_ID,
					INCORRECT_METHOD_ID)
		}
	}

	@Check
	def checkMethodReference(VerificationCondition cond) {
		if (cond.method == null) {
			warning('Verification precondition or validation should have a verification method reference', 
					VerifyPackage.Literals.VERIFICATION_CONDITION__METHOD,
					MISSING_METHOD_REFERENCE)
		}
	}
	@Check
	def checkMissingMethodReference(VerificationActivity va) {
		if (va.method == null) {
			warning('Verification activity should have a method reference', 
					VerifyPackage.Literals.VERIFICATION_ACTIVITY__METHOD,
					MISSING_METHOD_REFERENCE)
		}
	}
	@Check
	def checkInvalidRequirementForClaim(Claim cl) {
		if (cl.requirement == null) {
			warning('Claim is missing requirement', 
					VerifyPackage.Literals.CLAIM__REQUIREMENT,
					CLAIM_MISSING_REQUIREMENT)
		}else{
			if(!containingVerificationPlan(cl).systemRequirements.content.contains(cl.requirement)){
				error('Requirement ' + cl.requirement.name + ' does not exist in ' + 
					containingVerificationPlan(cl).systemRequirements.name + '.', cl, VerifyPackage.Literals.CLAIM__REQUIREMENT,
					CLAIM_INVALID_REQUIREMENT
				)
			}
		}
	}
	
	@Check (CheckType.NORMAL)
	def checkClaimsForRequirement(VerificationPlan vp){
		val systemRequirements = vp.systemRequirements
		val requirements = systemRequirements.content
		requirements.forEach[req | 
			if( !vp.claim.exists[claim | claim.requirement === req]){
				error('No claim for requirement ' + req.name, vp,
					VerifyPackage.Literals.VERIFICATION_PLAN__NAME,
					MISSING_CLAIM_FOR_REQ, req.name, EcoreUtil.getURI(req).toString())
			}
		]
	}
	
//	@Check (CheckType.FAST)
//	def checkClaimRequirementsAreForVerificationPlanTarget(VerificationPlan vp){
//		val systemRequirements = vp.systemRequirements
// 		val requirements = systemRequirements.content
//		vp.claim.forEach[claim|
//			 if (!requirements.exists[req | req === claim.requirement]){
//				error('Claim requirement ' + claim.requirement.name + ' does not match classifier identified for verification plan ' + vp.name,
//					claim, VerifyPackage.Literals.CLAIM__REQUIREMENT,
//					CLAIM_REQ_FOR_NOT_VP_FOR)
//			 }
//		]
//	}
	
}