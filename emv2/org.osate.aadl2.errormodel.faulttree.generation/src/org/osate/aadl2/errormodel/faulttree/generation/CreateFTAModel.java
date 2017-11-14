package org.osate.aadl2.errormodel.faulttree.generation;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.errormodel.FaultTree.FaultTree;
import org.osate.aadl2.errormodel.PropagationGraph.PropagationGraph;
import org.osate.aadl2.errormodel.PropagationGraph.util.Util;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorBehaviorState;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorPropagation;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorTypes;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeToken;
import org.osate.xtext.aadl2.errormodel.util.EM2TypeSetUtil;
import org.osate.xtext.aadl2.errormodel.util.EMV2Util;

public class CreateFTAModel {

	private static final String prefixState = "state ";
	private static final String prefixOutgoingPropagation = "outgoing propagation on ";

	public static URI createTransformedFTA(ComponentInstance selection, final String errorStateName) {
		return createModel(selection, errorStateName, true, false, false);
	}

	public static URI createMinimalCutSet(ComponentInstance selection, final String errorStateName) {
		return createModel(selection, errorStateName, true, false, true);
	}

	public static URI createModel(ComponentInstance selection, final String errorStateName, boolean transform,
			boolean graph, boolean mincutset) {
		NamedElement errorStateOrPropagation=null;
		ErrorTypes errorType =null;

		if (errorStateName.startsWith(prefixState)) {
			String toProcess = errorStateName.replace(prefixState, "");
			for (ErrorBehaviorState ebs : EMV2Util.getAllErrorBehaviorStates(selection)) {
				if (ebs.getName().equalsIgnoreCase(toProcess)) {
					errorStateOrPropagation = ebs;
				}
			}

		}

		if (errorStateName.startsWith(prefixOutgoingPropagation)) {
			String toProcess = errorStateName.replace(prefixOutgoingPropagation, "");
			for (ErrorPropagation opc : EMV2Util.getAllOutgoingErrorPropagations(selection.getComponentClassifier())) {
				EList<TypeToken> result = EM2TypeSetUtil.generateAllLeafTypeTokens(opc.getTypeSet(),
						EMV2Util.getUseTypes(opc));
				for (TypeToken tt : result) {
					String longName = EMV2Util.getPrintName(opc) + EMV2Util.getPrintName(tt);
					if (longName.equalsIgnoreCase(toProcess) && !tt.getType().isEmpty()) {
						errorStateOrPropagation = opc;
						errorType = tt.getType().get(0);
					}
				}
			}
		}
		PropagationGraph currentPropagationGraph = Util.generatePropagationGraph(selection.getSystemInstance(), false);
		FTAGenerator wrapper = new FTAGenerator(currentPropagationGraph);
		FaultTree ftamodel = wrapper.getftaModel(selection, errorStateOrPropagation, errorType,transform, graph, mincutset);
		String rootname = ftamodel.getName() + (mincutset ? "_cutset" : (transform ? "" : "_full"))
				+ (graph ? "_graph" : "");
		ftamodel.setName(rootname);

		URI ftaURI = EcoreUtil.getURI(selection).trimFragment().trimSegments(1).appendSegment("reports")
				.appendSegment("fta").appendSegment(rootname + ".faulttree");
		AadlUtil.makeSureFoldersExist(new Path(ftaURI.toPlatformString(true)));
		URI ftauri = OsateResourceUtil.saveEMFModel(ftamodel, ftaURI, selection);
		return ftauri;
	}

}