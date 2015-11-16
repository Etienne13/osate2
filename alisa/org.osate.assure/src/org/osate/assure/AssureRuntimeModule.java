package org.osate.assure;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class AssureRuntimeModule extends org.osate.assure.AbstractAssureRuntimeModule {
	@Override
	public Class<? extends org.eclipse.xtext.linking.ILinkingService> bindILinkingService() {
		return org.osate.assure.linking.AssureLinkingService.class;
	}

	public Class<? extends org.eclipse.xtext.naming.IQualifiedNameConverter> bindIQualifiedNameConverter() {
		return org.osate.alisa.common.naming.CommonQualifiedNameConverter.class;
	}

	@SuppressWarnings("restriction")
	public Class<? extends org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer> bindICrossReferenceSerializer() {
		return org.osate.assure.serializer.AssureCrossReferenceSerializer.class;
	}

}
