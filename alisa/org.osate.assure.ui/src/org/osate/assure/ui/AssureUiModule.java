/*
 * generated by Xtext
 */
package org.osate.assure.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.osate.assure.ui.outline.AssureEObjectHoverProvider;

/**
 * Use this class to register components to be used within the IDE.
 */
public class AssureUiModule extends org.osate.assure.ui.AbstractAssureUiModule {
	public AssureUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	public Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider() {
		return AssureEObjectHoverProvider.class;
	}
}
