/*
 * generated by Xtext
 */
package org.osate.organization.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.osate.organization.services.OrganizationGrammarAccess;

public class OrganizationParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private OrganizationGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.osate.organization.parser.antlr.internal.InternalOrganizationParser createParser(XtextTokenStream stream) {
		return new org.osate.organization.parser.antlr.internal.InternalOrganizationParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "Organization";
	}
	
	public OrganizationGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(OrganizationGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
