/*******************************************************************************
 * Copyright (C) 2016 University of Alabama in Huntsville (UAH)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The US Government has unlimited rights in this work in accordance with W31P4Q-10-D-0092 DO 0105.
 *******************************************************************************/
package org.osate.ge.errormodel;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import javax.inject.Named;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.DefaultAnnexLibrary;
import org.osate.aadl2.Element;
import org.osate.ge.di.BuildReference;
import org.osate.ge.di.Names;
import org.osate.ge.di.ResolveReference;
import org.osate.ge.errormodel.model.ErrorTypeExtension;
import org.osate.ge.errormodel.model.ErrorTypeLibrary;
import org.osate.ge.services.ReferenceBuilderService;
import org.osate.ge.services.ReferenceResolutionService;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorEvent;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorState;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorStateMachine;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorTransition;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorModelLibrary;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorType;

public class ErrorModelReferenceHandler {
	private final static String TYPE_BEHAVIOR_STATE_MACHINE = "emv2.behavior";
	private final static String TYPE_BEHAVIOR_EVENT = "emv2.be";
	private final static String TYPE_BEHAVIOR_STATE = "emv2.bs";
	private final static String TYPE_BEHAVIOR_TRANSITION = "emv2.bt";
	private final static String TYPE_ERROR_TYPE_LIBRARY = "emv2.etl";
	private final static String TYPE_ERROR_TYPE = "emv2.et";
	private final static String TYPE_ERROR_TYPE_EXT = "emv2.ete";
	
	@BuildReference
	public String[] getReference(final @Named(Names.BUSINESS_OBJECT) Object bo, final ReferenceBuilderService refBuilder) {
		if(bo instanceof Element) {
			final Element el = (Element)bo;
			
			if(el.getElementRoot() instanceof AadlPackage) {
				final AadlPackage pkg = (AadlPackage)el.getElementRoot();
				if(bo instanceof ErrorBehaviorStateMachine) {
					return new String[] {TYPE_BEHAVIOR_STATE_MACHINE, refBuilder.getReference(pkg), ((ErrorBehaviorStateMachine)bo).getName().toLowerCase()};				
				} else if(bo instanceof ErrorBehaviorEvent) {
					final ErrorBehaviorEvent typedBo = (ErrorBehaviorEvent)bo;
					return new String[] {TYPE_BEHAVIOR_EVENT, refBuilder.getReference(typedBo.eContainer()), typedBo.getName().toLowerCase()};				
				} else if(bo instanceof ErrorBehaviorState) {
					final ErrorBehaviorState typedBo = (ErrorBehaviorState)bo;
					return new String[] {TYPE_BEHAVIOR_STATE, refBuilder.getReference(typedBo.eContainer()), ((ErrorBehaviorState)bo).getName().toLowerCase()};				
				} else if(bo instanceof ErrorBehaviorTransition) {
					final ErrorBehaviorTransition typedBo = (ErrorBehaviorTransition)bo;
					return new String[] {TYPE_BEHAVIOR_TRANSITION, refBuilder.getReference(typedBo.eContainer()), ((ErrorBehaviorTransition)bo).getName().toLowerCase()};				
				} else if(bo instanceof ErrorType) {
					return new String[] {TYPE_ERROR_TYPE, refBuilder.getReference(pkg), ((ErrorType)bo).getName().toLowerCase()};				
				}  
			}
		} else if(bo instanceof ErrorTypeLibrary) {
			final ErrorTypeLibrary etl = (ErrorTypeLibrary)bo;
			if(etl.getErrorModelLibrary().getElementRoot() instanceof AadlPackage) {
				final AadlPackage pkg = (AadlPackage)etl.getErrorModelLibrary().getElementRoot();
				return new String[] {TYPE_ERROR_TYPE_LIBRARY, refBuilder.getReference(pkg)};
			}
		} else if(bo instanceof ErrorTypeExtension) {
			final ErrorTypeExtension ete = (ErrorTypeExtension)bo;
			if(ete.getSubtype().getElementRoot() instanceof AadlPackage && ete.getSupertype().getElementRoot() instanceof AadlPackage) {
				return new String[] {TYPE_ERROR_TYPE_EXT, 
						refBuilder.getReference(ete.getSupertype()),
						refBuilder.getReference(ete.getSubtype())};
			}
		}
		
		return null;
	}
	
	@ResolveReference
	public Object getReferencedObject(final @Named(Names.REFERENCE) String[] ref, final ReferenceResolutionService refService) {
		Objects.requireNonNull(ref, "ref must not be null");
		// Handle references with 2 or more segments
		if(ref.length < 2) {
			return null;
		}
		
		final String type = ref[0];
		final Object ref1 = refService.getReferencedObject(ref[1]);
		
		if(ref1 == null) {
			return null;
		}

		if(type.equals(TYPE_ERROR_TYPE_LIBRARY)) {
			final AadlPackage pkg = (AadlPackage)ref1;
			final Optional<ErrorModelLibrary> errorModelLibrary = pkg.getOwnedPublicSection().getOwnedAnnexLibraries().stream(). // Get annex libraries
					filter(lib -> lib instanceof DefaultAnnexLibrary && ((DefaultAnnexLibrary)lib).getParsedAnnexLibrary() instanceof ErrorModelLibrary). // Filter non EMV2 Libraries
					map(lib -> ((ErrorModelLibrary)((DefaultAnnexLibrary)lib).getParsedAnnexLibrary())). // Get parsed annex library
					findAny();
			
			return errorModelLibrary.isPresent() ? new ErrorTypeLibrary(errorModelLibrary.get()) : null;
		}
		
		// Handle types which require 3 reference segments
		if(ref.length < 3) {
			return null;
		}
		
		if(type.equals(TYPE_BEHAVIOR_STATE_MACHINE)) {
			final AadlPackage pkg = (AadlPackage)ref1;
			final String name = ref[2];
			return pkg.getOwnedPublicSection().getOwnedAnnexLibraries().stream(). // Get annex libraries
					filter(lib -> lib instanceof DefaultAnnexLibrary && ((DefaultAnnexLibrary)lib).getParsedAnnexLibrary() instanceof ErrorModelLibrary). // Filter non EMV2 Libraries
					map(lib -> ((ErrorModelLibrary)((DefaultAnnexLibrary)lib).getParsedAnnexLibrary()).getBehaviors().stream()). // Get behaviors as stream
					reduce(Stream.empty(), (a, b) -> Stream.concat(a, b)). // Combine streams
					filter(b -> name.equalsIgnoreCase(b.getName())). // Filter behaviors by name
					findAny().orElse(null);
		} else if(type.equals(TYPE_BEHAVIOR_EVENT)) {
			final ErrorBehaviorStateMachine stateMachine = (ErrorBehaviorStateMachine)ref1;
			final String name = ref[2];
			return stateMachine.getEvents().stream().
				filter(o -> name.equalsIgnoreCase(o.getName())). // Filter objects by name
				findAny().orElse(null);
		} else if(type.equals(TYPE_BEHAVIOR_STATE)) {
			final ErrorBehaviorStateMachine stateMachine = (ErrorBehaviorStateMachine)ref1;
			final String name = ref[2];
			return stateMachine.getStates().stream().
				filter(o -> name.equalsIgnoreCase(o.getName())). // Filter objects by name
				findAny().orElse(null);
		} else if(type.equals(TYPE_BEHAVIOR_TRANSITION)) {
			final ErrorBehaviorStateMachine stateMachine = (ErrorBehaviorStateMachine)ref1;
			final String name = ref[2];
			return stateMachine.getTransitions().stream().
				filter(o -> name.equalsIgnoreCase(o.getName())). // Filter objects by name
				findAny().orElse(null);
		} else if(type.equals(TYPE_ERROR_TYPE)) {
			final AadlPackage pkg = (AadlPackage)ref1;
			final String name = ref[2];
			
			final Optional<ErrorType> errorType = pkg.getOwnedPublicSection().getOwnedAnnexLibraries().stream(). // Get annex libraries
					filter(lib -> lib instanceof DefaultAnnexLibrary && ((DefaultAnnexLibrary)lib).getParsedAnnexLibrary() instanceof ErrorModelLibrary). // Filter non EMV2 Libraries
					map(lib -> ((ErrorModelLibrary)((DefaultAnnexLibrary)lib).getParsedAnnexLibrary()).getTypes().stream()). // Get types as stream
					reduce(Stream.empty(), (a, b) -> Stream.concat(a, b)). // Combine streams
					filter(b -> name.equalsIgnoreCase(b.getName())). // Filter types by name
					findAny();
			
			return errorType.orElse(null);			
		} else if(type.equals(TYPE_ERROR_TYPE_EXT)) {
			final ErrorType supertype = (ErrorType)ref1;
			final ErrorType subtype = (ErrorType)refService.getReferencedObject(ref[2]);
			if(supertype == null || subtype == null) {
				return null;
			}
			
			return new ErrorTypeExtension(supertype, subtype);
		}
		
		return null;
	}
}