/*
 * generated by Xtext
 */
package org.osate.alisa.workbench.scoping

import com.google.inject.Inject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider
import org.eclipse.xtext.scoping.impl.SimpleScope
import org.eclipse.xtext.util.SimpleAttributeResolver
import org.osate.alisa.workbench.alisa.AssurancePlan
import org.osate.verify.util.IVerifyGlobalReferenceFinder

/**
 * This class contains custom scoping description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation.html#scoping
 * on how and when to use it 
 *
 */
class AlisaScopeProvider extends AbstractDeclarativeScopeProvider {

@Inject var IVerifyGlobalReferenceFinder refFinder

	def scope_AssurancePlan_assureGlobal(AssurancePlan acp, EReference reference){
		val targetCC = acp.target
		val vps = refFinder.getVerificationPlans(targetCC,acp).filter[vp| vp.systemRequirements.global]
		new SimpleScope(IScope::NULLSCOPE, 
			Scopes::scopedElementsFor(vps,
						QualifiedName::wrapper(SimpleAttributeResolver::NAME_RESOLVER)), true)

	}

	def scope_AssurancePlan_assureOwn(AssurancePlan acp, EReference reference){
		val targetCC = acp.target
		 val vps = refFinder.getVerificationPlans(targetCC,acp);
		new SimpleScope(IScope::NULLSCOPE, 
			Scopes::scopedElementsFor(vps,
						QualifiedName::wrapper(SimpleAttributeResolver::NAME_RESOLVER)), true)

	}

}