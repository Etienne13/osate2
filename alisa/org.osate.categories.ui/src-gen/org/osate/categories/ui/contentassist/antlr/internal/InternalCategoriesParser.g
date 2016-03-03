/**
 * Copyright 2015 Carnegie Mellon University. All Rights Reserved.
 *
 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE
 * MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR MERCHANTABILITY,
 * EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON
 * UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM
 * PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 *
 * Released under the Eclipse Public License (http://www.eclipse.org/org/documents/epl-v10.php)
 *
 * See COPYRIGHT file for full details.
 */
parser grammar InternalCategoriesParser;

options {
	tokenVocab=InternalCategoriesLexer;
	superClass=AbstractInternalContentAssistParser;
	
}

@header {
package org.osate.categories.ui.contentassist.antlr.internal; 

import java.util.Map;
import java.util.HashMap;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.osate.categories.services.CategoriesGrammarAccess;

}

@members {
 
 	private CategoriesGrammarAccess grammarAccess;
 	
 	private final Map<String, String> tokenNameToValue = new HashMap<String, String>();
 	
 	{
		tokenNameToValue.put("FullStop", "'.'");
		tokenNameToValue.put("LeftSquareBracket", "'['");
		tokenNameToValue.put("RightSquareBracket", "']'");
 	}
 	
    public void setGrammarAccess(CategoriesGrammarAccess grammarAccess) {
    	this.grammarAccess = grammarAccess;
    }
    
    @Override
    protected Grammar getGrammar() {
    	return grammarAccess.getGrammar();
    }

	@Override
    protected String getValueForTokenName(String tokenName) {
    	String result = tokenNameToValue.get(tokenName);
    	if (result == null)
    		result = tokenName;
    	return result;
    }
}




// Entry rule entryRuleCategoriesDefinitions
entryRuleCategoriesDefinitions 
:
{ before(grammarAccess.getCategoriesDefinitionsRule()); }
	 ruleCategoriesDefinitions
{ after(grammarAccess.getCategoriesDefinitionsRule()); } 
	 EOF 
;

// Rule CategoriesDefinitions
ruleCategoriesDefinitions 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getCategoriesDefinitionsAccess().getCategoriesAssignment()); }
(rule__CategoriesDefinitions__CategoriesAssignment)*
{ after(grammarAccess.getCategoriesDefinitionsAccess().getCategoriesAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleCategories
entryRuleCategories 
:
{ before(grammarAccess.getCategoriesRule()); }
	 ruleCategories
{ after(grammarAccess.getCategoriesRule()); } 
	 EOF 
;

// Rule Categories
ruleCategories 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getCategoriesAccess().getGroup()); }
(rule__Categories__Group__0)
{ after(grammarAccess.getCategoriesAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}



// Entry rule entryRuleCategory
entryRuleCategory 
:
{ before(grammarAccess.getCategoryRule()); }
	 ruleCategory
{ after(grammarAccess.getCategoryRule()); } 
	 EOF 
;

// Rule Category
ruleCategory 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getCategoryAccess().getNameAssignment()); }
(rule__Category__NameAssignment)
{ after(grammarAccess.getCategoryAccess().getNameAssignment()); }
)

;
finally {
	restoreStackSize(stackSize);
}





// Entry rule entryRuleCatRef
entryRuleCatRef 
:
{ before(grammarAccess.getCatRefRule()); }
	 ruleCatRef
{ after(grammarAccess.getCatRefRule()); } 
	 EOF 
;

// Rule CatRef
ruleCatRef 
    @init {
		int stackSize = keepStackSize();
    }
    :
(
{ before(grammarAccess.getCatRefAccess().getGroup()); }
(rule__CatRef__Group__0)
{ after(grammarAccess.getCatRefAccess().getGroup()); }
)

;
finally {
	restoreStackSize(stackSize);
}






rule__Categories__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Categories__Group__0__Impl
	rule__Categories__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Categories__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCategoriesAccess().getNameAssignment_0()); }
(rule__Categories__NameAssignment_0)
{ after(grammarAccess.getCategoriesAccess().getNameAssignment_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Categories__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Categories__Group__1__Impl
	rule__Categories__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Categories__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCategoriesAccess().getLeftSquareBracketKeyword_1()); }

	LeftSquareBracket 

{ after(grammarAccess.getCategoriesAccess().getLeftSquareBracketKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Categories__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Categories__Group__2__Impl
	rule__Categories__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Categories__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
(
{ before(grammarAccess.getCategoriesAccess().getCategoryAssignment_2()); }
(rule__Categories__CategoryAssignment_2)
{ after(grammarAccess.getCategoriesAccess().getCategoryAssignment_2()); }
)
(
{ before(grammarAccess.getCategoriesAccess().getCategoryAssignment_2()); }
(rule__Categories__CategoryAssignment_2)*
{ after(grammarAccess.getCategoriesAccess().getCategoryAssignment_2()); }
)
)

;
finally {
	restoreStackSize(stackSize);
}


rule__Categories__Group__3
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__Categories__Group__3__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Categories__Group__3__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCategoriesAccess().getRightSquareBracketKeyword_3()); }

	RightSquareBracket 

{ after(grammarAccess.getCategoriesAccess().getRightSquareBracketKeyword_3()); }
)

;
finally {
	restoreStackSize(stackSize);
}











rule__CatRef__Group__0
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__CatRef__Group__0__Impl
	rule__CatRef__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__CatRef__Group__0__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCatRefAccess().getIDTerminalRuleCall_0()); }
	RULE_ID
{ after(grammarAccess.getCatRefAccess().getIDTerminalRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__CatRef__Group__1
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__CatRef__Group__1__Impl
	rule__CatRef__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__CatRef__Group__1__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCatRefAccess().getFullStopKeyword_1()); }

	FullStop 

{ after(grammarAccess.getCatRefAccess().getFullStopKeyword_1()); }
)

;
finally {
	restoreStackSize(stackSize);
}


rule__CatRef__Group__2
    @init {
		int stackSize = keepStackSize();
    }
:
	rule__CatRef__Group__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__CatRef__Group__2__Impl
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCatRefAccess().getIDTerminalRuleCall_2()); }
	RULE_ID
{ after(grammarAccess.getCatRefAccess().getIDTerminalRuleCall_2()); }
)

;
finally {
	restoreStackSize(stackSize);
}









rule__CategoriesDefinitions__CategoriesAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCategoriesDefinitionsAccess().getCategoriesCategoriesParserRuleCall_0()); }
	ruleCategories{ after(grammarAccess.getCategoriesDefinitionsAccess().getCategoriesCategoriesParserRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Categories__NameAssignment_0
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCategoriesAccess().getNameIDTerminalRuleCall_0_0()); }
	RULE_ID{ after(grammarAccess.getCategoriesAccess().getNameIDTerminalRuleCall_0_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Categories__CategoryAssignment_2
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCategoriesAccess().getCategoryCategoryParserRuleCall_2_0()); }
	ruleCategory{ after(grammarAccess.getCategoriesAccess().getCategoryCategoryParserRuleCall_2_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}

rule__Category__NameAssignment
    @init {
		int stackSize = keepStackSize();
    }
:
(
{ before(grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_0()); }
	RULE_ID{ after(grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_0()); }
)

;
finally {
	restoreStackSize(stackSize);
}




