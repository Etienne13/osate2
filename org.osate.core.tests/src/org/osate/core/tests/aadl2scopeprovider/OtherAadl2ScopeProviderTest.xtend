/*
 *
 * <copyright>
 * Copyright  2014 by Carnegie Mellon University, all rights reserved.
 *
 * Use of the Open Source AADL Tool Environment (OSATE) is subject to the terms of the license set forth
 * at http://www.eclipse.org/org/documents/epl-v10.html.
 *
 * NO WARRANTY
 *
 * ANY INFORMATION, MATERIALS, SERVICES, INTELLECTUAL PROPERTY OR OTHER PROPERTY OR RIGHTS GRANTED OR PROVIDED BY
 * CARNEGIE MELLON UNIVERSITY PURSUANT TO THIS LICENSE (HEREINAFTER THE "DELIVERABLES") ARE ON AN "AS-IS" BASIS.
 * CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR A PARTICULAR PURPOSE, MERCHANTABILITY, INFORMATIONAL CONTENT,
 * NONINFRINGEMENT, OR ERROR-FREE OPERATION. CARNEGIE MELLON UNIVERSITY SHALL NOT BE LIABLE FOR INDIRECT, SPECIAL OR
 * CONSEQUENTIAL DAMAGES, SUCH AS LOSS OF PROFITS OR INABILITY TO USE SAID INTELLECTUAL PROPERTY, UNDER THIS LICENSE,
 * REGARDLESS OF WHETHER SUCH PARTY WAS AWARE OF THE POSSIBILITY OF SUCH DAMAGES. LICENSEE AGREES THAT IT WILL NOT
 * MAKE ANY WARRANTY ON BEHALF OF CARNEGIE MELLON UNIVERSITY, EXPRESS OR IMPLIED, TO ANY PERSON CONCERNING THE
 * APPLICATION OF OR THE RESULTS TO BE OBTAINED WITH THE DELIVERABLES UNDER THIS LICENSE.
 *
 * Licensee hereby agrees to defend, indemnify, and hold harmless Carnegie Mellon University, its trustees, officers,
 * employees, and agents from all claims or demands made against them (and any related losses, expenses, or
 * attorney's fees) arising out of, or relating to Licensee's and/or its sub licensees' negligent use or willful
 * misuse of or negligent conduct or willful misconduct regarding the Software, facilities, or other rights or
 * assistance granted by Carnegie Mellon University under this License, including, but not limited to, any claims of
 * product liability, personal injury, death, damage to property, or violation of any laws or regulations.
 *
 * Carnegie Mellon Carnegie Mellon University Software Engineering Institute authored documents are sponsored by the U.S. Department
 * of Defense under Contract F19628-00-C-0003. Carnegie Mellon University retains copyrights in all material produced
 * under this contract. The U.S. Government retains a non-exclusive, royalty-free license to publish or reproduce these
 * documents, or allow others to do so, for U.S. Government purposes only pursuant to the copyright license
 * under the contract clause at 252.227.7013.
 * </copyright>
 */
package org.osate.core.tests.aadl2scopeprovider

import com.google.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipselabs.xtext.utils.unittesting.XtextRunner2
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.osate.aadl2.Aadl2Package
import org.osate.aadl2.AadlPackage
import org.osate.aadl2.AbstractImplementation
import org.osate.aadl2.AbstractType
import org.osate.aadl2.ComponentImplementation
import org.osate.aadl2.ComponentPrototypeBinding
import org.osate.aadl2.ComponentType
import org.osate.aadl2.FeatureGroupPrototypeBinding
import org.osate.aadl2.FeaturePrototypeBinding
import org.osate.aadl2.ModelUnit
import org.osate.aadl2.NamedElement
import org.osate.core.test.Aadl2UiInjectorProvider
import org.osate.core.test.OsateTest

import static extension org.junit.Assert.assertEquals

@RunWith(XtextRunner2)
@InjectWith(Aadl2UiInjectorProvider)
class OtherAadl2ScopeProviderTest extends OsateTest {
	@Inject extension ParseHelper<ModelUnit>
	@Inject extension ValidationTestHelper
	
	static val TEST_PROJECT_NAME = "Aadl2_Scope_Provider_Test"
	
	@Before
	def setUp() {
		createProject(TEST_PROJECT_NAME, "packages")
		buildProject("Plugin_Resources", true)
		setResourceRoot("platform:/resource/" + TEST_PROJECT_NAME + "/packages")
	}
	
	@After
	def cleanUp() {
		deleteProject(TEST_PROJECT_NAME)
	}
	
//	@BeforeClass
//	def static showReferences() {
//		println
//		typeof(Aadl2Package).methods.filter[declaringClass == typeof(Aadl2Package) && returnType == typeof(EReference)].map[it -> invoke(Aadl2Package::eINSTANCE) as EReference].
//			filter[!value.containment && !value.derived].filter[value.EReferenceType == Aadl2Package::eINSTANCE.feature].
//			forEach[println(key.name.substring(3, key.name.indexOf("_")) + "." + value.name + "->" + value.EReferenceType.name)]
//	}
	
	/*
	 * Tests scope_ComponentPrototype_constrainingClassifier, scope_FeaturePrototype_constrainingClassifier, scope_FeatureGroupPrototypeActual_featureType,
	 * scope_PortSpecification_classifier, scope_AccessSpecification_classifier, scope_ComponentPrototypeActual_subcomponentType,
	 * scope_EventDataSource_dataClassifier, scope_PortProxy_dataClassifier, and scope_SubprogramProxy_subprogramClassifier
	 */
	@Test
	def void testRenamesInClassifierReferenceScope() {
		createFiles(
			"pack1.aadl" -> '''
				package pack1
				public
				  with pack3;
				  with pack4;
				  with pack5;
				  
				  renames pack3::all;
				  renamed_package renames package pack4;
				  
				  renames abstract pack5::a6;
				  renames data pack5::d5;
				  renames subprogram pack5::subp5;
				  renames feature group pack5::fgt5;
				  
				  renamed_abstract renames abstract pack5::a7;
				  renamed_data renames data pack5::d6;
				  renamed_subprogram renames subprogram pack5::subp6;
				  renamed_feature_group renames feature group pack5::fgt5;
				
				  abstract a1
				    prototypes
				      proto1: abstract a2;
				      proto2: feature a2;
				      proto3: feature group;
				      proto4: feature;
				      proto5: feature;
				      proto6: data;
				  end a1;
				  
				  abstract a2 extends a1 (
				    proto3 => feature group fgt1,
				    proto4 => in data port d1,
				    proto5 => provides data access d1,
				    proto6 => data d1
				  )
				  end a2;
				  
				  abstract implementation a2.i
				  internal features
				    eds1: event data d1;
				  processor features
				    pp1: port d1;
				    sp1: subprogram subp1;
				  end a2.i;
				  
				  feature group fgt1
				  end fgt1;
				  
				  data d1
				  end d1;
				  
				  data implementation d1.i
				  end d1.i;
				  
				  subprogram subp1
				  end subp1;
				  
				  subprogram implementation subp1.i
				  end subp1.i;
				end pack1;
			''',
			"pack2.aadl" -> '''
				package pack2
				public
				  abstract a3
				  end a3;
				  
				  abstract implementation a3.i
				  end a3.i;
				  
				  feature group fgt2
				  end fgt2;
				  
				  data d2
				  end d2;
				  
				  data implementation d2.i
				  end d2.i;
				  
				  subprogram subp2
				  end subp2;
				  
				  subprogram implementation subp2.i
				  end subp2.i;
				end pack2;
			''',
			"pack3.aadl" -> '''
				package pack3
				public
				  abstract a4
				  end a4;
				  
				  abstract implementation a4.i
				  end a4.i;
				  
				  feature group fgt3
				  end fgt3;
				  
				  data d3
				  end d3;
				  
				  data implementation d3.i
				  end d3.i;
				  
				  subprogram subp3
				  end subp3;
				  
				  subprogram implementation subp3.i
				  end subp3.i;
				end pack3;
			''',
			"pack4.aadl" -> '''
				package pack4
				public
				  abstract a5
				  end a5;
				  
				  abstract implementation a5.i
				  end a5.i;
				  
				  feature group fgt4
				  end fgt4;
				  
				  data d4
				  end d4;
				  
				  data implementation d4.i
				  end d4.i;
				  
				  subprogram subp4
				  end subp4;
				  
				  subprogram implementation subp4.i
				  end subp4.i;
				end pack4;
			''',
			"pack5.aadl" -> '''
				package pack5
				public
				  abstract a6
				  end a6;
				  
				  abstract a7
				  end a7;
				  
				  feature group fgt5
				  end fgt5;
				  
				  data d5
				  end d5;
				  
				  data implementation d5.i
				  end d5.i;
				  
				  subprogram subp5
				  end subp5;
				  
				  subprogram implementation subp5.i
				  end subp5.i;
				  
				  data d6
				  end d6;
				  
				  data implementation d6.i
				  end d6.i;
				  
				  subprogram subp6
				  end subp6;
				  
				  subprogram implementation subp6.i
				  end subp6.i;
				end pack5;
			'''
		)
		suppressSerialization
		testFile("pack2.aadl")
		testFile("pack3.aadl")
		testFile("pack4.aadl")
		testFile("pack5.aadl")
		testFile("pack1.aadl").resource.contents.head as AadlPackage => [
			"pack1".assertEquals(name)
			val componentClassifierScopeForPack1 = #["a1", "a2", "a2.i", "a4", "a4.i", "a6", "d1", "d1.i", "d3", "d3.i", "d5", "renamed_abstract",
				"renamed_data", "renamed_subprogram", "subp1", "subp1.i", "subp3", "subp3.i", "subp5", "pack1::a1", "pack1::a2", "pack1::a2.i", "pack1::d1",
				"pack1::d1.i", "pack1::subp1", "pack1::subp1.i", "pack2::a3", "pack2::a3.i", "pack2::d2", "pack2::d2.i", "pack2::subp2", "pack2::subp2.i",
				"pack3::a4", "pack3::a4.i", "pack3::d3", "pack3::d3.i", "pack3::subp3", "pack3::subp3.i", "pack4::a5", "pack4::a5.i", "pack4::d4",
				"pack4::d4.i", "pack4::subp4", "pack4::subp4.i", "pack5::a6", "pack5::a7", "pack5::d5", "pack5::d5.i", "pack5::d6", "pack5::d6.i",
				"pack5::subp5", "pack5::subp5.i", "pack5::subp6", "pack5::subp6.i", "renamed_package::a5", "renamed_package::a5.i", "renamed_package::d4",
				"renamed_package::d4.i", "renamed_package::subp4", "renamed_package::subp4.i"
			]
			publicSection.ownedClassifiers.get(0) => [
				"a1".assertEquals(name)
				ownedPrototypes.get(0) => [
					"proto1".assertEquals(name)
					//Tests scope_ComponentPrototype_constrainingClassifier
					assertScope(Aadl2Package::eINSTANCE.componentPrototype_ConstrainingClassifier, componentClassifierScopeForPack1)
				]
				ownedPrototypes.get(1) => [
					"proto2".assertEquals(name)
					//Tests scope_FeaturePrototype_constrainingClassifier
					assertScope(Aadl2Package::eINSTANCE.featurePrototype_ConstrainingClassifier, componentClassifierScopeForPack1)
				]
			]
			publicSection.ownedClassifiers.get(1) => [
				"a2".assertEquals(name)
				ownedPrototypeBindings.get(0) as FeatureGroupPrototypeBinding => [
					"proto3".assertEquals(formal.name)
					//Tests scope_FeatureGroupPrototypeActual_featureType
					actual.assertScope(Aadl2Package::eINSTANCE.featureGroupPrototypeActual_FeatureType, #["fgt1", "fgt3", "fgt5", "proto3",
						"renamed_feature_group", "pack1::fgt1", "pack2::fgt2", "pack3::fgt3", "pack4::fgt4", "pack5::fgt5", "renamed_package::fgt4"
					])
				]
				ownedPrototypeBindings.get(1) as FeaturePrototypeBinding => [
					"proto4".assertEquals(formal.name)
					//Tests scope_PortSpecification_classifier
					actual.assertScope(Aadl2Package::eINSTANCE.portSpecification_Classifier, componentClassifierScopeForPack1)
				]
				ownedPrototypeBindings.get(2) as FeaturePrototypeBinding => [
					"proto5".assertEquals(formal.name)
					//Tests scope_AccessSpecification_classifier
					actual.assertScope(Aadl2Package::eINSTANCE.accessSpecification_Classifier, componentClassifierScopeForPack1)
				]
				ownedPrototypeBindings.get(3) as ComponentPrototypeBinding => [
					"proto6".assertEquals(formal.name)
					//Tests scope_ComponentPrototypeActual_subcomponentType
					actuals.get(0).assertScope(Aadl2Package::eINSTANCE.componentPrototypeActual_SubcomponentType, #["proto1", "proto6"] + componentClassifierScopeForPack1)
				]
			]
			publicSection.ownedClassifiers.get(2) as ComponentImplementation => [
				"a2.i".assertEquals(name)
				ownedEventDataSources.head => [
					"eds1".assertEquals(name)
					//Tests scope_EventDataSource_dataClassifier
					assertScope(Aadl2Package::eINSTANCE.eventDataSource_DataClassifier, #["d1", "d1.i", "d3", "d3.i", "d5", "renamed_data", "pack1::d1",
						"pack1::d1.i", "pack2::d2", "pack2::d2.i", "pack3::d3", "pack3::d3.i", "pack4::d4", "pack4::d4.i", "pack5::d5", "pack5::d5.i",
						"pack5::d6", "pack5::d6.i", "renamed_package::d4", "renamed_package::d4.i"
					])
				]
				ownedPortProxies.head => [
					"pp1".assertEquals(name)
					//Tests scope_PortProxy_dataClassifier
					assertScope(Aadl2Package::eINSTANCE.portProxy_DataClassifier, #["d1", "d1.i", "d3", "d3.i", "d5", "renamed_data", "pack1::d1",
						"pack1::d1.i", "pack2::d2", "pack2::d2.i", "pack3::d3", "pack3::d3.i", "pack4::d4", "pack4::d4.i", "pack5::d5", "pack5::d5.i",
						"pack5::d6", "pack5::d6.i", "renamed_package::d4", "renamed_package::d4.i"
					])
				]
				ownedSubprogramProxies.head => [
					"sp1".assertEquals(name)
					//Tests scope_SubprogramProxy_subprogramClassifier
					assertScope(Aadl2Package::eINSTANCE.subprogramProxy_SubprogramClassifier, #["renamed_subprogram", "subp1", "subp1.i", "subp3", "subp3.i",
						"subp5", "pack1::subp1", "pack1::subp1.i", "pack2::subp2", "pack2::subp2.i", "pack3::subp3", "pack3::subp3.i", "pack4::subp4",
						"pack4::subp4.i", "pack5::subp5", "pack5::subp5.i", "pack5::subp6", "pack5::subp6.i", "renamed_package::subp4",
						"renamed_package::subp4.i"
					])
				]
			]
		]
	}
	
	/*
	 * Tests scope_PrototypeBinding_formal, scope_FeatureGroupPrototypeActual_featureType, scope_FeaturePrototypeReference_prototype, and
	 * scope_ComponentPrototypeActual_subcomponentType
	 */
	@Test
	def void testPrototypeBindings() {
		('''
			package pack
			public
			  abstract a1
			  prototypes
			    proto1: abstract;
			    proto3: feature group;
			    proto5: feature group;
			    proto8: feature;
			    proto9: feature;
			    proto11: abstract;
			  end a1;
			  
			  abstract a2 extends a1 (
			    proto1 => abstract a3 (
			      proto2 => in data port
			    ),
			    proto3 => feature group fgt1 (
			      proto4 => in data port
			    )
			  )
			  end a2;
			  
			  abstract implementation a1.i1
			  subcomponents
			    asub1: abstract [](a3.i1 (
			      proto2 => in data port
			    ));
			    asub2: abstract a3 (
			      proto2 => in data port
			    );
			  end a1.i1;
			  
			  abstract implementation a1.i2 extends a1.i1 (
			    proto1 => abstract a3,
			    proto3 => feature group fgt1,
			    proto5 => feature group proto3,
			    proto8 => feature proto9,
			    proto11 => abstract proto1
			  )
			  end a1.i2;
			  
			  abstract implementation a1.i3 (
			    proto1 => abstract a3
			  )
			  end a1.i3;
			  
			  abstract a3
			  prototypes
			    proto2: feature;
			  end a3;
			  
			  abstract implementation a3.i1
			  end a3.i1;
			  
			  feature group fgt1
			  prototypes
			    proto4: feature;
			    proto6: feature group;
			    proto10: feature;
			    proto12: abstract;
			  end fgt1;
			  
			  feature group fgt2 extends fgt1 (
			    proto4 => in data port,
			    proto6 => feature group proto7,
			    proto10 => feature proto4,
			    proto12 => abstract proto13
			  )
			  prototypes
			    proto7: feature group;
			    proto13: abstract;
			  end fgt2;
			end pack;
		'''.parse as AadlPackage) => [
			"pack".assertEquals(name)
			assertNoIssues
			publicSection.ownedClassifiers.get(1) => [
				"a2".assertEquals(name)
				ownedPrototypeBindings.get(0) as ComponentPrototypeBinding => [
					"proto1".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto1", "proto11", "proto3", "proto5", "proto8", "proto9"])
					actuals.head => [
						"a3".assertEquals(subcomponentType.name)
						//Tests scope_ComponentPrototypeActual_subcomponentType
						assertScope(Aadl2Package::eINSTANCE.componentPrototypeActual_SubcomponentType, #["a1", "a1.i1", "a1.i2", "a1.i3", "a2", "a3", "a3.i1",
							"proto1", "proto11", "pack::a1", "pack::a1.i1", "pack::a1.i2", "pack::a1.i3", "pack::a2", "pack::a3", "pack::a3.i1"
						])
						bindings.head => [
							"proto2".assertEquals(formal.name)
							//Tests scope_PrototypeBinding_formal(ComponentPrototypeActual, EReference)
							assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto2"])
						]
					]
				]
				ownedPrototypeBindings.get(1) as FeatureGroupPrototypeBinding => [
					"proto3".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto1", "proto11", "proto3", "proto5", "proto8", "proto9"])
					actual => [
						"fgt1".assertEquals((featureType as NamedElement).name)
						//Tests scope_FeatureGroupPrototypeActual_featureType
						assertScope(Aadl2Package::eINSTANCE.featureGroupPrototypeActual_FeatureType, #["fgt1", "fgt2", "proto3", "proto5", "pack::fgt1",
							"pack::fgt2"
						])
						bindings.head => [
							"proto4".assertEquals(formal.name)
							//Tests scope_PrototypeBinding_formal(FeatureGroupPrototypeActual, EReference)
							assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto10", "proto12", "proto4", "proto6"])
						]
					]
				]
			]
			publicSection.ownedClassifiers.get(2) as ComponentImplementation => [
				"a1.i1".assertEquals(name)
				ownedSubcomponents.get(0) => [
					"asub1".assertEquals(name)
					implementationReferences.head => [
						"a3.i1".assertEquals(implementation.name)
						ownedPrototypeBindings.head => [
							"proto2".assertEquals(formal.name)
							//Tests scope_PrototypeBinding_formal(ComponentImplementationReference, EReference)
							assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto2"])
						]
					]
				]
				ownedSubcomponents.get(1) => [
					"asub2".assertEquals(name)
					ownedPrototypeBindings.head => [
						"proto2".assertEquals(formal.name)
						//Tests scope_PrototypeBinding_formal(Subcomponent, EReference)
						assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto2"])
					]
				]
			]
			publicSection.ownedClassifiers.get(3) => [
				"a1.i2".assertEquals(name)
				ownedPrototypeBindings.get(0) as ComponentPrototypeBinding => [
					"proto1".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto1", "proto11", "proto3", "proto5", "proto8", "proto9"])
					//Tests scope_ComponentPrototypeActual_subcomponentType
					actuals.head.assertScope(Aadl2Package::eINSTANCE.componentPrototypeActual_SubcomponentType, #["a1", "a1.i1", "a1.i2", "a1.i3", "a2", "a3",
						"a3.i1", "proto1", "proto11", "pack::a1", "pack::a1.i1", "pack::a1.i2", "pack::a1.i3", "pack::a2", "pack::a3", "pack::a3.i1"
					])
				]
				ownedPrototypeBindings.get(1) as FeatureGroupPrototypeBinding => [
					"proto3".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto1", "proto11", "proto3", "proto5", "proto8", "proto9"])
					//Tests scope_FeatureGroupPrototypeActual_featureType
					actual.assertScope(Aadl2Package::eINSTANCE.featureGroupPrototypeActual_FeatureType, #["fgt1", "fgt2", "proto3", "proto5", "pack::fgt1",
						"pack::fgt2"
					])
				]
				ownedPrototypeBindings.get(2) as FeatureGroupPrototypeBinding => [
					"proto5".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto1", "proto11", "proto3", "proto5", "proto8", "proto9"])
					//Tests scope_FeatureGroupPrototypeActual_featureType
					actual.assertScope(Aadl2Package::eINSTANCE.featureGroupPrototypeActual_FeatureType, #["fgt1", "fgt2", "proto3", "proto5", "pack::fgt1",
						"pack::fgt2"
					])
				]
				ownedPrototypeBindings.get(3) as FeaturePrototypeBinding => [
					"proto8".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto1", "proto11", "proto3", "proto5", "proto8", "proto9"])
					//Tests scope_FeaturePrototypeReference_prototype
					actual.assertScope(Aadl2Package::eINSTANCE.featurePrototypeReference_Prototype, #["proto8", "proto9"])
				]
				ownedPrototypeBindings.get(4) as ComponentPrototypeBinding => [
					"proto11".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto1", "proto11", "proto3", "proto5", "proto8", "proto9"])
					//Tests scope_ComponentPrototypeActual_subcomponentType
					actuals.head.assertScope(Aadl2Package::eINSTANCE.componentPrototypeActual_SubcomponentType, #["a1", "a1.i1", "a1.i2", "a1.i3", "a2", "a3",
						"a3.i1", "proto1", "proto11", "pack::a1", "pack::a1.i1", "pack::a1.i2", "pack::a1.i3", "pack::a2", "pack::a3", "pack::a3.i1"
					])
				]
			]
			publicSection.ownedClassifiers.get(4) => [
				"a1.i3".assertEquals(name)
				ownedPrototypeBindings.get(0) as ComponentPrototypeBinding => [
					"proto1".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto1", "proto11", "proto3", "proto5", "proto8", "proto9"])
					//Tests scope_ComponentPrototypeActual_subcomponentType
					actuals.head.assertScope(Aadl2Package::eINSTANCE.componentPrototypeActual_SubcomponentType, #["a1", "a1.i1", "a1.i2", "a1.i3", "a2", "a3",
						"a3.i1", "proto1", "proto11", "pack::a1", "pack::a1.i1", "pack::a1.i2", "pack::a1.i3", "pack::a2", "pack::a3", "pack::a3.i1"
					])
				]
			]
			publicSection.ownedClassifiers.get(8) => [
				"fgt2".assertEquals(name)
				ownedPrototypeBindings.get(0) => [
					"proto4".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto10", "proto12", "proto4", "proto6"])
				]
				ownedPrototypeBindings.get(1) as FeatureGroupPrototypeBinding => [
					"proto6".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto10", "proto12", "proto4", "proto6"])
					//Tests scope_FeatureGroupPrototypeActual_featureType
					actual.assertScope(Aadl2Package::eINSTANCE.featureGroupPrototypeActual_FeatureType, #["fgt1", "fgt2", "proto6", "proto7", "pack::fgt1",
						"pack::fgt2"
					])
				]
				ownedPrototypeBindings.get(2) as FeaturePrototypeBinding => [
					"proto10".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto10", "proto12", "proto4", "proto6"])
					//Tests scope_FeaturePrototypeReference_prototype
					actual.assertScope(Aadl2Package::eINSTANCE.featurePrototypeReference_Prototype, #["proto10", "proto4"])
				]
				ownedPrototypeBindings.get(3) as ComponentPrototypeBinding => [
					"proto12".assertEquals(formal.name)
					//Tests scope_PrototypeBinding_formal(Classifier, EReference)
					assertScope(Aadl2Package::eINSTANCE.prototypeBinding_Formal, #["proto10", "proto12", "proto4", "proto6"])
					//Tests scope_ComponentPrototypeActual_subcomponentType
					actuals.head.assertScope(Aadl2Package::eINSTANCE.componentPrototypeActual_SubcomponentType, #["a1", "a1.i1", "a1.i2", "a1.i3", "a2", "a3",
						"a3.i1", "proto12", "proto13", "pack::a1", "pack::a1.i1", "pack::a1.i2", "pack::a1.i3", "pack::a2", "pack::a3", "pack::a3.i1"
					])
				]
			]
		]
	}
	
	/*
	 * Tests the reference ArraySize_SizeProperty used in the parser rule ArraySize.
	 * The scope for this rule is automatically provided, so there is no scoping method to test here.
	 */
	@Test
	def void testArraySizeProperty() {
		createFiles(
			"pack.aadl" -> '''
				package pack
				public
				  with ps;
				  
				  abstract a
				  features
				    af: feature[ps::def];
				  properties
				    ps::def => 4;
				  end a;
				end pack;
			''',
			"ps.aadl" -> '''
				property set ps is
				  def: aadlinteger applies to (all);
				  const: constant aadlinteger => 10;
				end ps;
			'''
		)
		suppressSerialization
		testFile("ps.aadl")
		testFile("pack.aadl").resource.contents.head as AadlPackage => [
			"pack".assertEquals(name)
			publicSection.ownedClassifiers.head as ComponentType => [
				"a".assertEquals(name)
				ownedAbstractFeatures.head => [
					"af".assertEquals(name)
					arrayDimensions.head.size => [
						"ps::def".assertEquals((sizeProperty as NamedElement).qualifiedName())
						//Tests the reference ArraySize_SizeProperty
						assertScope(Aadl2Package::eINSTANCE.arraySize_SizeProperty, #["Acceptable_Array_Size", "Access_Right", "Access_Time",
							"Activate_Deadline", "Activate_Entrypoint", "Activate_Entrypoint_Call_Sequence", "Activate_Entrypoint_Source_Text",
							"Activate_Execution_Time", "Active_Thread_Handling_Protocol", "Active_Thread_Queue_Handling_Protocol", "Actual_Connection_Binding",
							"Actual_Function_Binding", "Actual_Latency", "Actual_Memory_Binding", "Actual_Processor_Binding", "Actual_Subprogram_Call",
							"Actual_Subprogram_Call_Binding", "Allowed_Connection_Binding", "Allowed_Connection_Binding_Class", "Allowed_Connection_Type",
							"Allowed_Dispatch_Protocol", "Allowed_Memory_Binding", "Allowed_Memory_Binding_Class", "Allowed_Message_Size", "Allowed_Period",
							"Allowed_Physical_Access", "Allowed_Physical_Access_Class", "Allowed_Processor_Binding", "Allowed_Processor_Binding_Class",
							"Allowed_Subprogram_Call", "Allowed_Subprogram_Call_Binding", "Assign_Time", "Base_Address", "Byte_Count",
							"Classifier_Matching_Rule", "Classifier_Substitution_Rule", "Client_Subprogram_Execution_Time", "Clock_Jitter", "Clock_Period",
							"Clock_Period_Range", "Code_Size", "Collocated", "Compute_Deadline", "Compute_Entrypoint", "Compute_Entrypoint_Call_Sequence",
							"Compute_Entrypoint_Source_Text", "Compute_Execution_Time", "Concurrency_Control_Protocol", "Connection_Pattern", "Connection_Set",
							"Criticality", "Data_Rate", "Data_Size", "Deactivate_Dealing", "Deactivate_Entrypoint", "Deactivate_Entrypoint_Call_Sequence",
							"Deactivate_Entrypoint_Source_Text", "Deactivate_Execution_Time", "Deactivation_Policy", "Deadline", "Dequeue_Protocol",
							"Dequeued_Items", "Device_Driver", "Device_Register_Address", "Dispatch_Able", "Dispatch_Jitter", "Dispatch_Offset",
							"Dispatch_Protocol", "Dispatch_Trigger", "Execution_Time", "Fan_Out_Policy", "Finalize_Deadline", "Finalize_Entrypoint",
							"Finalize_Entrypoint_Call_Sequence", "Finalize_Entrypoint_Source_Text", "Finalize_Execution_Time", "First_Dispatch_Time",
							"Frame_Period", "Hardware_Description_Source_Text", "Hardware_Source_Language", "Heap_Size", "Implemented_As",
							"Initialize_Deadline", "Initialize_Entrypoint", "Initialize_Entrypoint_Call_Sequence", "Initialize_Entrypoint_Source_Text",
							"Initialize_Execution_Time", "Input_Rate", "Input_Time", "Latency", "Load_Deadline", "Load_Time", "Max_Aadlinteger",
							"Max_Base_Address", "Max_Byte_Count", "Max_Memory_Size", "Max_Queue_Size", "Max_Target_Integer", "Max_Thread_Limit", "Max_Time",
							"Max_Urgency", "Max_Volume", "Max_Word_Space", "Memory_Protocol", "Memory_Size", "Mode_Transition_Response", "Not_Collocated",
							"Output_Rate", "Output_Time", "Overflow_Handling_Protocol", "POSIX_Scheduling_Policy", "Period", "Preemptive_Scheduler",
							"Priority", "Priority_Map", "Priority_Range", "Process_Swap_Execution_Time", "Processor_Capacity", "Prototype_Substitution_Rule",
							"Provided_Connection_Quality_Of_Service", "Provided_Virtual_Bus_Class", "Queue_Processing_Protocol", "Queue_Size", "Read_Time",
							"Recover_Deadline", "Recover_Entrypoint", "Recover_Entrypoint_Call_Sequence", "Recover_Entrypoint_Source_Text",
							"Recover_Execution_Time", "Reference_Processor", "Required_Connection", "Required_Connection_Quality_Of_Service",
							"Required_Virtual_Bus_Class", "Resumption_Policy", "Runtime_Protection", "Runtime_Protection_Support", "Scheduler_Quantum",
							"Scheduling_Protocol", "Slot_Time", "Source_Language", "Source_Name", "Source_Text", "Stack_Size", "Startup_Deadline",
							"Startup_Execution_Time", "Subprogram_Call_Rate", "Subprogram_Call_Type", "Supported_Classifier_Complement_Matches",
							"Supported_Classifier_Equivalence_Matches", "Supported_Classifier_Subset_Matches", "Supported_Source_Language",
							"Supported_Type_Conversions", "Synchronized_Component", "Thread_Limit", "Thread_Swap_Execution_Time", "Time_Slot", "Timing",
							"Transmission_Time", "Transmission_Type", "Type_Source_Name", "Urgency", "Word_Size", "Word_Space", "Write_Time", "ps::const",
							"ps::def", "AADL_Project::Max_Aadlinteger", "AADL_Project::Max_Base_Address", "AADL_Project::Max_Byte_Count",
							"AADL_Project::Max_Memory_Size", "AADL_Project::Max_Queue_Size", "AADL_Project::Max_Target_Integer",
							"AADL_Project::Max_Thread_Limit", "AADL_Project::Max_Time", "AADL_Project::Max_Urgency", "AADL_Project::Max_Volume",
							"AADL_Project::Max_Word_Space", "AADL_Project::Supported_Classifier_Complement_Matches",
							"AADL_Project::Supported_Classifier_Equivalence_Matches", "AADL_Project::Supported_Classifier_Subset_Matches",
							"AADL_Project::Supported_Type_Conversions", "Communication_Properties::Actual_Latency",
							"Communication_Properties::Connection_Pattern", "Communication_Properties::Connection_Set", "Communication_Properties::Data_Rate",
							"Communication_Properties::Fan_Out_Policy", "Communication_Properties::Input_Rate", "Communication_Properties::Input_Time",
							"Communication_Properties::Latency", "Communication_Properties::Output_Rate", "Communication_Properties::Output_Time",
							"Communication_Properties::Overflow_Handling_Protocol", "Communication_Properties::Queue_Processing_Protocol",
							"Communication_Properties::Queue_Size", "Communication_Properties::Required_Connection",
							"Communication_Properties::Subprogram_Call_Rate", "Communication_Properties::Timing",
							"Communication_Properties::Transmission_Time", "Communication_Properties::Transmission_Type",
							"Deployment_Properties::Actual_Connection_Binding", "Deployment_Properties::Actual_Function_Binding",
							"Deployment_Properties::Actual_Memory_Binding", "Deployment_Properties::Actual_Processor_Binding",
							"Deployment_Properties::Actual_Subprogram_Call", "Deployment_Properties::Actual_Subprogram_Call_Binding",
							"Deployment_Properties::Allowed_Connection_Binding", "Deployment_Properties::Allowed_Connection_Binding_Class",
							"Deployment_Properties::Allowed_Connection_Type", "Deployment_Properties::Allowed_Dispatch_Protocol",
							"Deployment_Properties::Allowed_Memory_Binding", "Deployment_Properties::Allowed_Memory_Binding_Class",
							"Deployment_Properties::Allowed_Period", "Deployment_Properties::Allowed_Physical_Access",
							"Deployment_Properties::Allowed_Physical_Access_Class", "Deployment_Properties::Allowed_Processor_Binding",
							"Deployment_Properties::Allowed_Processor_Binding_Class", "Deployment_Properties::Allowed_Subprogram_Call",
							"Deployment_Properties::Allowed_Subprogram_Call_Binding", "Deployment_Properties::Collocated",
							"Deployment_Properties::Memory_Protocol", "Deployment_Properties::Not_Collocated", "Deployment_Properties::Preemptive_Scheduler",
							"Deployment_Properties::Priority_Map", "Deployment_Properties::Priority_Range",
							"Deployment_Properties::Provided_Connection_Quality_Of_Service", "Deployment_Properties::Provided_Virtual_Bus_Class",
							"Deployment_Properties::Required_Connection_Quality_Of_Service", "Deployment_Properties::Required_Virtual_Bus_Class",
							"Deployment_Properties::Runtime_Protection_Support", "Deployment_Properties::Scheduling_Protocol",
							"Deployment_Properties::Thread_Limit", "Memory_Properties::Access_Right", "Memory_Properties::Access_Time",
							"Memory_Properties::Allowed_Message_Size", "Memory_Properties::Assign_Time", "Memory_Properties::Base_Address",
							"Memory_Properties::Byte_Count", "Memory_Properties::Code_Size", "Memory_Properties::Data_Size",
							"Memory_Properties::Device_Register_Address", "Memory_Properties::Heap_Size", "Memory_Properties::Memory_Size",
							"Memory_Properties::Read_Time", "Memory_Properties::Stack_Size", "Memory_Properties::Word_Size", "Memory_Properties::Word_Space",
							"Memory_Properties::Write_Time", "Modeling_Properties::Acceptable_Array_Size", "Modeling_Properties::Classifier_Matching_Rule",
							"Modeling_Properties::Classifier_Substitution_Rule", "Modeling_Properties::Implemented_As",
							"Modeling_Properties::Prototype_Substitution_Rule", "Programming_Properties::Activate_Entrypoint",
							"Programming_Properties::Activate_Entrypoint_Call_Sequence", "Programming_Properties::Activate_Entrypoint_Source_Text",
							"Programming_Properties::Compute_Entrypoint", "Programming_Properties::Compute_Entrypoint_Call_Sequence",
							"Programming_Properties::Compute_Entrypoint_Source_Text", "Programming_Properties::Deactivate_Entrypoint",
							"Programming_Properties::Deactivate_Entrypoint_Call_Sequence", "Programming_Properties::Deactivate_Entrypoint_Source_Text",
							"Programming_Properties::Device_Driver", "Programming_Properties::Finalize_Entrypoint",
							"Programming_Properties::Finalize_Entrypoint_Call_Sequence", "Programming_Properties::Finalize_Entrypoint_Source_Text",
							"Programming_Properties::Hardware_Description_Source_Text", "Programming_Properties::Hardware_Source_Language",
							"Programming_Properties::Initialize_Entrypoint", "Programming_Properties::Initialize_Entrypoint_Call_Sequence",
							"Programming_Properties::Initialize_Entrypoint_Source_Text", "Programming_Properties::Recover_Entrypoint",
							"Programming_Properties::Recover_Entrypoint_Call_Sequence", "Programming_Properties::Recover_Entrypoint_Source_Text",
							"Programming_Properties::Source_Language", "Programming_Properties::Source_Name", "Programming_Properties::Source_Text",
							"Programming_Properties::Supported_Source_Language", "Programming_Properties::Type_Source_Name",
							"Thread_Properties::Active_Thread_Handling_Protocol", "Thread_Properties::Active_Thread_Queue_Handling_Protocol",
							"Thread_Properties::Concurrency_Control_Protocol", "Thread_Properties::Criticality", "Thread_Properties::Deactivation_Policy",
							"Thread_Properties::Dequeue_Protocol", "Thread_Properties::Dequeued_Items", "Thread_Properties::Dispatch_Able",
							"Thread_Properties::Dispatch_Protocol", "Thread_Properties::Dispatch_Trigger", "Thread_Properties::Mode_Transition_Response",
							"Thread_Properties::POSIX_Scheduling_Policy", "Thread_Properties::Priority", "Thread_Properties::Resumption_Policy",
							"Thread_Properties::Runtime_Protection", "Thread_Properties::Subprogram_Call_Type", "Thread_Properties::Synchronized_Component",
							"Thread_Properties::Time_Slot", "Thread_Properties::Urgency", "Timing_Properties::Activate_Deadline",
							"Timing_Properties::Activate_Execution_Time", "Timing_Properties::Client_Subprogram_Execution_Time",
							"Timing_Properties::Clock_Jitter", "Timing_Properties::Clock_Period", "Timing_Properties::Clock_Period_Range",
							"Timing_Properties::Compute_Deadline", "Timing_Properties::Compute_Execution_Time", "Timing_Properties::Deactivate_Dealing",
							"Timing_Properties::Deactivate_Execution_Time", "Timing_Properties::Deadline", "Timing_Properties::Dispatch_Jitter",
							"Timing_Properties::Dispatch_Offset", "Timing_Properties::Execution_Time", "Timing_Properties::Finalize_Deadline",
							"Timing_Properties::Finalize_Execution_Time", "Timing_Properties::First_Dispatch_Time", "Timing_Properties::Frame_Period",
							"Timing_Properties::Initialize_Deadline", "Timing_Properties::Initialize_Execution_Time", "Timing_Properties::Load_Deadline",
							"Timing_Properties::Load_Time", "Timing_Properties::Period", "Timing_Properties::Process_Swap_Execution_Time",
							"Timing_Properties::Processor_Capacity", "Timing_Properties::Recover_Deadline", "Timing_Properties::Recover_Execution_Time",
							"Timing_Properties::Reference_Processor", "Timing_Properties::Scheduler_Quantum", "Timing_Properties::Slot_Time",
							"Timing_Properties::Startup_Deadline", "Timing_Properties::Startup_Execution_Time", "Timing_Properties::Thread_Swap_Execution_Time"
						])
					]
				]
			]
		]
	}
	
	//Tests scope_ModalPath_inModeOrTransition and scope_FlowImplementation_specification
	@Test
	def void testInModesAndFlows() {
		('''
			package pack
			public
			  abstract a1
			  features
			    fg1: feature group;
			    ep1: in event port;
			    da1: provides data access;
			  end a1;
			  
			  abstract implementation a1.i
			  subcomponents
			    asub1: abstract a1;
			    asub2: abstract a2;
			    asub3: abstract a2;
			  connections
			  	portconn1: port ep1 -> asub1.ep1 in modes (m1, m2, mt1, mt2);
			  	portconn2: port asub2.ep2 -> asub3.ep3;
			  	aconn1: data access da1 -> asub1.da1 in modes (m1, m2, mt1, mt2);
			    fgconn1: feature group fg1 <-> fg1 in modes (m1, m2, mt1, mt2);
			    fconn1: feature fg1 -> asub1.fg1 in modes (m1, m2, mt1, mt2);
			    paramconn1: parameter da1 -> da1 in modes (m1, m2, mt1, mt2);
			  flows
			    ete1: end to end flow asub2.fsource1 -> portconn2 -> asub3.fsink1 in modes (m1, m2, mt1, mt2);
			  modes
			    m1: initial mode;
			    m2: mode;
			    m3: mode;
			    m4: mode;
			    mt1: m1 -[ep1]-> m2;
			    mt2: m2 -[ep1]-> m3;
			    mt3: m3 -[ep1]-> m4;
			    mt4: m4 -[ep1]-> m1;
			  end a1.i;
			  
			  abstract a2
			  features
			    ep2: out event port;
			    ep3: in event port;
			  flows
			    fsource1: flow source ep2 in modes (m5, m6, mt5, mt6);
			    fsink1: flow sink ep3 in modes (m5, m6, mt5, mt6);
			  modes
			    m5: initial mode;
			    m6: mode;
			    m7: mode;
			    m8: mode;
			    mt5: m5 -[ep3]-> m6;
			    mt6: m6 -[ep3]-> m7;
			    mt7: m7 -[ep3]-> m8;
			    mt8: m8 -[ep3]-> m5;
			  end a2;
			  
			  abstract implementation a2.i
			  flows
			    fsource1: flow source ep2 in modes (m5, m6, m7, mt5, mt6, mt7);
			    fsink1: flow sink ep3 in modes (m5, m6, m7, mt5, mt6, mt7);
			  end a2.i;
			end pack;
		'''.parse as AadlPackage) => [
			assertNoIssues
			"pack".assertEquals(name)
			publicSection.ownedClassifiers.get(1) as AbstractImplementation => [
				"a1.i".assertEquals(name)
				ownedPortConnections.get(0) => [
					"portconn1".assertEquals(name)
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m1", "m2", "m3", "m4", "mt1", "mt2", "mt3", "mt4"])
				]
				ownedPortConnections.get(1) => [
					"portconn2".assertEquals(name)
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m1", "m2", "m3", "m4", "mt1", "mt2", "mt3", "mt4"])
				]
				ownedAccessConnections.head => [
					"aconn1".assertEquals(name)
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m1", "m2", "m3", "m4", "mt1", "mt2", "mt3", "mt4"])
				]
				ownedFeatureGroupConnections.head => [
					"fgconn1".assertEquals(name)
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m1", "m2", "m3", "m4", "mt1", "mt2", "mt3", "mt4"])
				]
				ownedFeatureConnections.head => [
					"fconn1".assertEquals(name)
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m1", "m2", "m3", "m4", "mt1", "mt2", "mt3", "mt4"])
				]
				ownedParameterConnections.head => [
					"paramconn1".assertEquals(name)
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m1", "m2", "m3", "m4", "mt1", "mt2", "mt3", "mt4"])
				]
				ownedEndToEndFlows.head => [
					"ete1".assertEquals(name)
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m1", "m2", "m3", "m4", "mt1", "mt2", "mt3", "mt4"])
				]
			]
			publicSection.ownedClassifiers.get(2) as AbstractType => [
				"a2".assertEquals(name)
				ownedFlowSpecifications.get(0) => [
					"fsource1".assertEquals(name)
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m5", "m6", "m7", "m8", "mt5", "mt6", "mt7", "mt8"])
				]
				ownedFlowSpecifications.get(1) => [
					"fsink1".assertEquals(name)
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m5", "m6", "m7", "m8", "mt5", "mt6", "mt7", "mt8"])
				]
			]
			publicSection.ownedClassifiers.get(3) as AbstractImplementation => [
				"a2.i".assertEquals(name)
				ownedFlowImplementations.get(0) => [
					"fsource1".assertEquals(specification.name)
					//Tests scope_FlowImplementation_specification
					assertScope(Aadl2Package::eINSTANCE.flowImplementation_Specification, #["fsink1", "fsource1"])
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m5", "m6", "m7", "m8", "mt5", "mt6", "mt7", "mt8"])
				]
				ownedFlowImplementations.get(1) => [
					"fsink1".assertEquals(specification.name)
					//Tests scope_FlowImplementation_specification
					assertScope(Aadl2Package::eINSTANCE.flowImplementation_Specification, #["fsource1", "fsink1"])
					//Tests scope_ModalPath_inModeOrTransition
					assertScope(Aadl2Package::eINSTANCE.modalPath_InModeOrTransition, #["m5", "m6", "m7", "m8", "mt5", "mt6", "mt7", "mt8"])
				]
			]
		]
	}
}