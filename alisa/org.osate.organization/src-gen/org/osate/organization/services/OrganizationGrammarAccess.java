/*
 * generated by Xtext
 */
package org.osate.organization.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import java.util.List;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.xtext.common.services.TerminalsGrammarAccess;

@Singleton
public class OrganizationGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class OrganizationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Organization");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cOrganizationKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cStakeholderAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cStakeholderStakeholderParserRuleCall_2_0 = (RuleCall)cStakeholderAssignment_2.eContents().get(0);
		
		//Organization:
		//	"organization" name=ID stakeholder+=Stakeholder+;
		@Override public ParserRule getRule() { return rule; }

		//"organization" name=ID stakeholder+=Stakeholder+
		public Group getGroup() { return cGroup; }

		//"organization"
		public Keyword getOrganizationKeyword_0() { return cOrganizationKeyword_0; }

		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }

		//stakeholder+=Stakeholder+
		public Assignment getStakeholderAssignment_2() { return cStakeholderAssignment_2; }

		//Stakeholder
		public RuleCall getStakeholderStakeholderParserRuleCall_2_0() { return cStakeholderStakeholderParserRuleCall_2_0; }
	}

	public class StakeholderElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Stakeholder");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cStakeholderKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Keyword cLeftSquareBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final UnorderedGroup cUnorderedGroup_3 = (UnorderedGroup)cGroup.eContents().get(3);
		private final Group cGroup_3_0 = (Group)cUnorderedGroup_3.eContents().get(0);
		private final Keyword cFullKeyword_3_0_0 = (Keyword)cGroup_3_0.eContents().get(0);
		private final Keyword cNameKeyword_3_0_1 = (Keyword)cGroup_3_0.eContents().get(1);
		private final Assignment cFullnameAssignment_3_0_2 = (Assignment)cGroup_3_0.eContents().get(2);
		private final RuleCall cFullnameSTRINGTerminalRuleCall_3_0_2_0 = (RuleCall)cFullnameAssignment_3_0_2.eContents().get(0);
		private final Group cGroup_3_1 = (Group)cUnorderedGroup_3.eContents().get(1);
		private final Keyword cTitleKeyword_3_1_0 = (Keyword)cGroup_3_1.eContents().get(0);
		private final Assignment cTitleAssignment_3_1_1 = (Assignment)cGroup_3_1.eContents().get(1);
		private final RuleCall cTitleSTRINGTerminalRuleCall_3_1_1_0 = (RuleCall)cTitleAssignment_3_1_1.eContents().get(0);
		private final Group cGroup_3_2 = (Group)cUnorderedGroup_3.eContents().get(2);
		private final Keyword cDescriptionKeyword_3_2_0 = (Keyword)cGroup_3_2.eContents().get(0);
		private final Assignment cDescriptionAssignment_3_2_1 = (Assignment)cGroup_3_2.eContents().get(1);
		private final RuleCall cDescriptionSTRINGTerminalRuleCall_3_2_1_0 = (RuleCall)cDescriptionAssignment_3_2_1.eContents().get(0);
		private final Group cGroup_3_3 = (Group)cUnorderedGroup_3.eContents().get(3);
		private final Keyword cRoleKeyword_3_3_0 = (Keyword)cGroup_3_3.eContents().get(0);
		private final Assignment cRoleAssignment_3_3_1 = (Assignment)cGroup_3_3.eContents().get(1);
		private final RuleCall cRoleSTRINGTerminalRuleCall_3_3_1_0 = (RuleCall)cRoleAssignment_3_3_1.eContents().get(0);
		private final Group cGroup_3_4 = (Group)cUnorderedGroup_3.eContents().get(4);
		private final Keyword cEmailKeyword_3_4_0 = (Keyword)cGroup_3_4.eContents().get(0);
		private final Assignment cEmailAssignment_3_4_1 = (Assignment)cGroup_3_4.eContents().get(1);
		private final RuleCall cEmailSTRINGTerminalRuleCall_3_4_1_0 = (RuleCall)cEmailAssignment_3_4_1.eContents().get(0);
		private final Group cGroup_3_5 = (Group)cUnorderedGroup_3.eContents().get(5);
		private final Keyword cPhoneKeyword_3_5_0 = (Keyword)cGroup_3_5.eContents().get(0);
		private final Assignment cPhoneAssignment_3_5_1 = (Assignment)cGroup_3_5.eContents().get(1);
		private final RuleCall cPhoneSTRINGTerminalRuleCall_3_5_1_0 = (RuleCall)cPhoneAssignment_3_5_1.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_4 = (Keyword)cGroup.eContents().get(4);
		
		/// *
		// * Stakeholder
		// * / Stakeholder:
		//	"stakeholder" name=ID "[" (("full" "name" fullname=STRING)? & ("title" title=STRING)? & ("description"
		//	description=STRING)? & ("role" role=STRING)? & ("email" email=STRING)? & ("phone" phone=STRING)?) "]";
		@Override public ParserRule getRule() { return rule; }

		//"stakeholder" name=ID "[" (("full" "name" fullname=STRING)? & ("title" title=STRING)? & ("description"
		//description=STRING)? & ("role" role=STRING)? & ("email" email=STRING)? & ("phone" phone=STRING)?) "]"
		public Group getGroup() { return cGroup; }

		//"stakeholder"
		public Keyword getStakeholderKeyword_0() { return cStakeholderKeyword_0; }

		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }

		//"["
		public Keyword getLeftSquareBracketKeyword_2() { return cLeftSquareBracketKeyword_2; }

		//("full" "name" fullname=STRING)? & ("title" title=STRING)? & ("description" description=STRING)? & ("role" role=STRING)?
		//& ("email" email=STRING)? & ("phone" phone=STRING)?
		public UnorderedGroup getUnorderedGroup_3() { return cUnorderedGroup_3; }

		//("full" "name" fullname=STRING)?
		public Group getGroup_3_0() { return cGroup_3_0; }

		//"full"
		public Keyword getFullKeyword_3_0_0() { return cFullKeyword_3_0_0; }

		//"name"
		public Keyword getNameKeyword_3_0_1() { return cNameKeyword_3_0_1; }

		//fullname=STRING
		public Assignment getFullnameAssignment_3_0_2() { return cFullnameAssignment_3_0_2; }

		//STRING
		public RuleCall getFullnameSTRINGTerminalRuleCall_3_0_2_0() { return cFullnameSTRINGTerminalRuleCall_3_0_2_0; }

		//("title" title=STRING)?
		public Group getGroup_3_1() { return cGroup_3_1; }

		//"title"
		public Keyword getTitleKeyword_3_1_0() { return cTitleKeyword_3_1_0; }

		//title=STRING
		public Assignment getTitleAssignment_3_1_1() { return cTitleAssignment_3_1_1; }

		//STRING
		public RuleCall getTitleSTRINGTerminalRuleCall_3_1_1_0() { return cTitleSTRINGTerminalRuleCall_3_1_1_0; }

		//("description" description=STRING)?
		public Group getGroup_3_2() { return cGroup_3_2; }

		//"description"
		public Keyword getDescriptionKeyword_3_2_0() { return cDescriptionKeyword_3_2_0; }

		//description=STRING
		public Assignment getDescriptionAssignment_3_2_1() { return cDescriptionAssignment_3_2_1; }

		//STRING
		public RuleCall getDescriptionSTRINGTerminalRuleCall_3_2_1_0() { return cDescriptionSTRINGTerminalRuleCall_3_2_1_0; }

		//("role" role=STRING)?
		public Group getGroup_3_3() { return cGroup_3_3; }

		//"role"
		public Keyword getRoleKeyword_3_3_0() { return cRoleKeyword_3_3_0; }

		//role=STRING
		public Assignment getRoleAssignment_3_3_1() { return cRoleAssignment_3_3_1; }

		//STRING
		public RuleCall getRoleSTRINGTerminalRuleCall_3_3_1_0() { return cRoleSTRINGTerminalRuleCall_3_3_1_0; }

		//("email" email=STRING)?
		public Group getGroup_3_4() { return cGroup_3_4; }

		//"email"
		public Keyword getEmailKeyword_3_4_0() { return cEmailKeyword_3_4_0; }

		//email=STRING
		public Assignment getEmailAssignment_3_4_1() { return cEmailAssignment_3_4_1; }

		//STRING
		public RuleCall getEmailSTRINGTerminalRuleCall_3_4_1_0() { return cEmailSTRINGTerminalRuleCall_3_4_1_0; }

		//("phone" phone=STRING)?
		public Group getGroup_3_5() { return cGroup_3_5; }

		//"phone"
		public Keyword getPhoneKeyword_3_5_0() { return cPhoneKeyword_3_5_0; }

		//phone=STRING
		public Assignment getPhoneAssignment_3_5_1() { return cPhoneAssignment_3_5_1; }

		//STRING
		public RuleCall getPhoneSTRINGTerminalRuleCall_3_5_1_0() { return cPhoneSTRINGTerminalRuleCall_3_5_1_0; }

		//"]"
		public Keyword getRightSquareBracketKeyword_4() { return cRightSquareBracketKeyword_4; }
	}
	
	
	private final OrganizationElements pOrganization;
	private final StakeholderElements pStakeholder;
	
	private final Grammar grammar;

	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public OrganizationGrammarAccess(GrammarProvider grammarProvider,
		TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pOrganization = new OrganizationElements();
		this.pStakeholder = new StakeholderElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.osate.organization.Organization".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	

	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//Organization:
	//	"organization" name=ID stakeholder+=Stakeholder+;
	public OrganizationElements getOrganizationAccess() {
		return pOrganization;
	}
	
	public ParserRule getOrganizationRule() {
		return getOrganizationAccess().getRule();
	}

	/// *
	// * Stakeholder
	// * / Stakeholder:
	//	"stakeholder" name=ID "[" (("full" "name" fullname=STRING)? & ("title" title=STRING)? & ("description"
	//	description=STRING)? & ("role" role=STRING)? & ("email" email=STRING)? & ("phone" phone=STRING)?) "]";
	public StakeholderElements getStakeholderAccess() {
		return pStakeholder;
	}
	
	public ParserRule getStakeholderRule() {
		return getStakeholderAccess().getRule();
	}

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	} 

	//terminal INT returns ecore::EInt:
	//	"0".."9"+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	} 

	//terminal STRING:
	//	"\"" ("\\" . / * 'b'|'t'|'n'|'f'|'r'|'u'|'"'|"'"|'\\' * / | !("\\" | "\""))* "\"" | "\'" ("\\" .
	//	/ * 'b'|'t'|'n'|'f'|'r'|'u'|'"'|"'"|'\\' * / | !("\\" | "\'"))* "\'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	} 
}
