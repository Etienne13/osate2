/*
 * generated by Xtext
 */
package org.osate.alisa.common.scoping

import java.util.ArrayList
import java.util.Collection
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider
import org.eclipse.xtext.scoping.impl.SimpleScope
import org.eclipse.xtext.util.SimpleAttributeResolver
import org.osate.aadl2.Aadl2Package
import org.osate.aadl2.IntegerLiteral
import org.osate.aadl2.RealLiteral
import org.osate.aadl2.UnitLiteral
import org.osate.aadl2.UnitsType
import org.osate.alisa.common.common.ShowValue
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval
import com.google.inject.Inject

/**
 * This class contains custom scoping description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation.html#scoping
 * on how and when to use it 
 *
 */
class CommonScopeProvider extends AbstractDeclarativeScopeProvider {

	def protected static scopeFor(Iterable<? extends EObject> elements) {
		new SimpleScope(IScope::NULLSCOPE,
			Scopes::scopedElementsFor(elements, QualifiedName::wrapper(SimpleAttributeResolver::NAME_RESOLVER)), true)
	}


	def scope_UnitLiteral(
		ShowValue context,
		EReference reference
	) {
		val units = context.unitLiterals
		if (!units.empty) {
			units.scopeFor
		} else {
			IScope.NULLSCOPE
		}
	}

	def scope_UnitLiteral(
		IntegerLiteral context,
		EReference reference
	) {
		val units = context.unitLiterals
		if (!units.empty) {
			units.scopeFor
		} else {
			IScope.NULLSCOPE
		}
	}

	def scope_UnitLiteral(
		RealLiteral context,
		EReference reference
	) {
		val units = context.unitLiterals
		if (!units.empty) {
			units.scopeFor
		} else {
			IScope.NULLSCOPE
		}
	}

	@Inject ICommonGlobalReferenceFinder refFinder

	def scope_AbstractNamedValue(EObject context, EReference reference) {
		val props = refFinder.getEObjectDescriptions(context, Aadl2Package.eINSTANCE.property, "aadl")
		+ refFinder.getEObjectDescriptions(context, Aadl2Package.eINSTANCE.propertyConstant, "aadl")
		new SimpleScope(IScope::NULLSCOPE, props, true)
	}

	val private static EClass UNITS_TYPE = Aadl2Package.eINSTANCE.getUnitsType();

	def private static getUnitLiterals(EObject context) {

		// TODO: Scope literals by type, but how to do we know the type of an
		// expression?
		val Collection<UnitLiteral> result = new ArrayList<UnitLiteral>()
		for (IEObjectDescription desc : EMFIndexRetrieval.getAllEObjectsOfTypeInWorkspace(context, UNITS_TYPE)) {
			val unitsType = EcoreUtil.resolve(desc.getEObjectOrProxy(), context) as UnitsType;
			unitsType.ownedLiterals.forall[lit|result += lit as UnitLiteral];
		}

		return result;
	}

}
