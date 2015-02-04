/*
* generated by Xtext
*/
package org.osate.alisa.workbench.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import org.osate.alisa.workbench.services.AlisaGrammarAccess;

public class AlisaParser extends AbstractContentAssistParser {
	
	@Inject
	private AlisaGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected org.osate.alisa.workbench.ui.contentassist.antlr.internal.InternalAlisaParser createParser() {
		org.osate.alisa.workbench.ui.contentassist.antlr.internal.InternalAlisaParser result = new org.osate.alisa.workbench.ui.contentassist.antlr.internal.InternalAlisaParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getDescriptionElementAccess().getAlternatives(), "rule__DescriptionElement__Alternatives");
					put(grammarAccess.getPredicateExpressionAccess().getOpAlternatives_1_0(), "rule__PredicateExpression__OpAlternatives_1_0");
					put(grammarAccess.getConstantValueAccess().getAlternatives(), "rule__ConstantValue__Alternatives");
					put(grammarAccess.getTextElementAccess().getAlternatives(), "rule__TextElement__Alternatives");
					put(grammarAccess.getAlisaWorkAreaAccess().getGroup(), "rule__AlisaWorkArea__Group__0");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getGroup(), "rule__AssuranceCaseConfiguration__Group__0");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getGroup_2(), "rule__AssuranceCaseConfiguration__Group_2__0");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getGroup_6(), "rule__AssuranceCaseConfiguration__Group_6__0");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getGroup_6_0(), "rule__AssuranceCaseConfiguration__Group_6_0__0");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getGroup_6_1(), "rule__AssuranceCaseConfiguration__Group_6_1__0");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getGroup_6_4(), "rule__AssuranceCaseConfiguration__Group_6_4__0");
					put(grammarAccess.getShowValueAccess().getGroup(), "rule__ShowValue__Group__0");
					put(grammarAccess.getShowValueAccess().getGroup_1(), "rule__ShowValue__Group_1__0");
					put(grammarAccess.getReferencePathAccess().getGroup(), "rule__ReferencePath__Group__0");
					put(grammarAccess.getReferencePathAccess().getGroup_1(), "rule__ReferencePath__Group_1__0");
					put(grammarAccess.getPredicateExpressionAccess().getGroup(), "rule__PredicateExpression__Group__0");
					put(grammarAccess.getConstantDeclAccess().getGroup(), "rule__ConstantDecl__Group__0");
					put(grammarAccess.getRealTermAccess().getGroup(), "rule__RealTerm__Group__0");
					put(grammarAccess.getREALAccess().getGroup(), "rule__REAL__Group__0");
					put(grammarAccess.getIntegerTermAccess().getGroup(), "rule__IntegerTerm__Group__0");
					put(grammarAccess.getAadlClassifierReferenceAccess().getGroup(), "rule__AadlClassifierReference__Group__0");
					put(grammarAccess.getAadlClassifierReferenceAccess().getGroup_0(), "rule__AadlClassifierReference__Group_0__0");
					put(grammarAccess.getAadlClassifierReferenceAccess().getGroup_2(), "rule__AadlClassifierReference__Group_2__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup(), "rule__QualifiedName__Group__0");
					put(grammarAccess.getQualifiedNameAccess().getGroup_1(), "rule__QualifiedName__Group_1__0");
					put(grammarAccess.getAlisaWorkAreaAccess().getNameAssignment_1(), "rule__AlisaWorkArea__NameAssignment_1");
					put(grammarAccess.getAlisaWorkAreaAccess().getCasesAssignment_2(), "rule__AlisaWorkArea__CasesAssignment_2");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getNameAssignment_1(), "rule__AssuranceCaseConfiguration__NameAssignment_1");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getTitleAssignment_2_1(), "rule__AssuranceCaseConfiguration__TitleAssignment_2_1");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getSystemAssignment_4(), "rule__AssuranceCaseConfiguration__SystemAssignment_4");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getDescriptionAssignment_6_0_1(), "rule__AssuranceCaseConfiguration__DescriptionAssignment_6_0_1");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getConstantAssignment_6_1_1(), "rule__AssuranceCaseConfiguration__ConstantAssignment_6_1_1");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getPlansAssignment_6_3(), "rule__AssuranceCaseConfiguration__PlansAssignment_6_3");
					put(grammarAccess.getAssuranceCaseConfigurationAccess().getSelectionFilterAssignment_6_4_1(), "rule__AssuranceCaseConfiguration__SelectionFilterAssignment_6_4_1");
					put(grammarAccess.getModelAccess().getContentAssignment(), "rule__Model__ContentAssignment");
					put(grammarAccess.getDescriptionAccess().getDescriptionAssignment(), "rule__Description__DescriptionAssignment");
					put(grammarAccess.getDescriptionElementAccess().getTextAssignment_0(), "rule__DescriptionElement__TextAssignment_0");
					put(grammarAccess.getDescriptionElementAccess().getValueAssignment_1(), "rule__DescriptionElement__ValueAssignment_1");
					put(grammarAccess.getDescriptionElementAccess().getNewlineAssignment_2(), "rule__DescriptionElement__NewlineAssignment_2");
					put(grammarAccess.getDescriptionElementAccess().getThisTargetAssignment_3(), "rule__DescriptionElement__ThisTargetAssignment_3");
					put(grammarAccess.getShowValueAccess().getRefAssignment_0(), "rule__ShowValue__RefAssignment_0");
					put(grammarAccess.getShowValueAccess().getUnitAssignment_1_1(), "rule__ShowValue__UnitAssignment_1_1");
					put(grammarAccess.getReferencePathAccess().getRefAssignment_0(), "rule__ReferencePath__RefAssignment_0");
					put(grammarAccess.getReferencePathAccess().getSubpathAssignment_1_1(), "rule__ReferencePath__SubpathAssignment_1_1");
					put(grammarAccess.getPredicateExpressionAccess().getOpAssignment_1(), "rule__PredicateExpression__OpAssignment_1");
					put(grammarAccess.getPredicateExpressionAccess().getLimitAssignment_2(), "rule__PredicateExpression__LimitAssignment_2");
					put(grammarAccess.getConstantDeclAccess().getNameAssignment_0(), "rule__ConstantDecl__NameAssignment_0");
					put(grammarAccess.getConstantDeclAccess().getConstantvalueAssignment_2(), "rule__ConstantDecl__ConstantvalueAssignment_2");
					put(grammarAccess.getStringTermAccess().getValueAssignment(), "rule__StringTerm__ValueAssignment");
					put(grammarAccess.getRealTermAccess().getValueAssignment_0(), "rule__RealTerm__ValueAssignment_0");
					put(grammarAccess.getRealTermAccess().getUnitAssignment_1(), "rule__RealTerm__UnitAssignment_1");
					put(grammarAccess.getIntegerTermAccess().getValueAssignment_0(), "rule__IntegerTerm__ValueAssignment_0");
					put(grammarAccess.getIntegerTermAccess().getUnitAssignment_1(), "rule__IntegerTerm__UnitAssignment_1");
					put(grammarAccess.getMultiLineStringAccess().getDescriptionAssignment(), "rule__MultiLineString__DescriptionAssignment");
					put(grammarAccess.getTextElementAccess().getTextAssignment_0(), "rule__TextElement__TextAssignment_0");
					put(grammarAccess.getTextElementAccess().getNewlineAssignment_1(), "rule__TextElement__NewlineAssignment_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			org.osate.alisa.workbench.ui.contentassist.antlr.internal.InternalAlisaParser typedParser = (org.osate.alisa.workbench.ui.contentassist.antlr.internal.InternalAlisaParser) parser;
			typedParser.entryRuleAlisaWorkArea();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public AlisaGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(AlisaGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
