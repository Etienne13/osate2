/*
 * <copyright>
 * Copyright  2012 by Carnegie Mellon University, all rights reserved.
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
package org.osate.aadl2.errormodel.analysis;
/**
 * This class provides a method for traversing the propagation paths breadth first.
 * It does so by propagating a token one level at a time, taking fan-out into account, i.e.,
 * does so for the resulting token replicates.
 */
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.FlowSpecificationInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.modelsupport.WriteToFile;
import org.osate.aadl2.util.Aadl2InstanceUtil;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorFlow;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorPath;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorPropagation;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorSink;
import org.osate.xtext.aadl2.errormodel.errorModel.ErrorSource;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeMappingSet;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeSet;
import org.osate.xtext.aadl2.errormodel.errorModel.TypeToken;
import org.osate.xtext.aadl2.errormodel.util.EM2TypeSetUtil;
import org.osate.xtext.aadl2.errormodel.util.EMV2Util;

/**
 * @author phf
 */
public class PropagateErrorFlows {
	protected WriteToFile report ;
	protected AnalysisModel faultModel ;
	protected int maxDepth = 10;

	public PropagateErrorFlows(String reportType, ComponentInstance root){
		report = new WriteToFile(reportType, root);
		faultModel = new AnalysisModel(root);
	}

	public PropagateErrorFlows(String reportType, EObject root, int maxDepth){
		report = new WriteToFile(reportType, root);
		this.maxDepth = maxDepth;
	}
	
	public void setMaxDepth(int maxDepth){
		this.maxDepth = maxDepth;
	}
	
	public void addText(String text){
		if (report != null)
			report.addOutput(text);
	}
	
	public int getMaxDepth(){
		return this.maxDepth;
	}
	
	public void addTextNewline(String text){
		if (report != null)
			report.addOutputNewline(text);
	}
	
	public void addNewline(){
		if (report != null)
			report.addOutputNewline("");
	}
	
	public void saveReport(){
		report.saveToFile();
	}
	
	/**
	 * traverse error flow if the component instance is an error source
	 * @param ci component instance
	 */
	public void startErrorFlows(ComponentInstance ci){
		Collection<ErrorSource> eslist = EMV2Util.getAllErrorSources(ci.getComponentClassifier());
		 EList<EObject> nextStep =new UniqueEList<EObject>();
		for (ErrorSource errorSource : eslist) {
			ErrorPropagation ep = errorSource.getOutgoing();
			FeatureInstance fi = EMV2Util.findFeatureInstance(ep,ci);
			// only propagate if error propagation?
			if (process(null,errorSource,fi)){
			}
		}
	}
	
	/**
	 * can be overwritten. Will write something about the component instance into the report
	 * @param ci component instance
	 * @param prefix
	 */
	public void report(InstanceObject io, String prefix){
		if (io instanceof FeatureInstance){
			FeatureInstance fi = (FeatureInstance)io;
			ComponentInstance ci = fi.getContainingComponentInstance();
			report.addOutputNewline(prefix+ci.getCategory().name()+" "+ci.getQualifiedName()+"."+fi.getName());
		} else if (io instanceof ComponentInstance){
			ComponentInstance ci = (ComponentInstance)io;
			report.addOutputNewline(prefix+ci.getCategory().name()+" "+ci.getQualifiedName());
		} else {
			report.addOutputNewline(prefix+io.getName());
		}
	}
	
	/**
	 * process feature instance fi, which is the destination of path.
	 * Path can be a connection instance, a flow spec instance, or an error flow
	 * @param path connection instance, flow spec instance, error flow
	 * @param fi feature instance
	 * @return true if to continue traversing, false if to prune traversal (not follow outgoing paths)
	 */
	protected boolean process(InstanceObject source,EObject path,FeatureInstance fi){
		ErrorPropagation ep;
		if (path instanceof ConnectionInstance){
			ConnectionInstance conni = (ConnectionInstance)path;
//			ConnectionInstanceEnd srcEnd = conni.getSource();
			ErrorModelState srcst = (ErrorModelState) ErrorModelStateAdapterFactory.INSTANCE.adapt(source, ErrorModelState.class);
			if (srcst.getToken() != null){
				TypeToken tu = srcst.getToken();
				// TODO lookup type transformations for connections and use them to determine target type
				ErrorModelState st = (ErrorModelState) ErrorModelStateAdapterFactory.INSTANCE.adapt(fi, ErrorModelState.class);
				st.setToken(tu);
			}
			ep = EMV2Util.getIncomingErrorPropagation(fi);
		} else if (path instanceof ErrorSource){
			ep = EMV2Util.getOutgoingErrorPropagation(fi);
			TypeSet ts = ((ErrorSource)path).getTypeTokenConstraint();
			if (ts == null) ts = ep.getTypeSet();
			EList<TypeToken> result = EM2TypeSetUtil.generateAllTypeTokens(ts);
			System.out.println("tokens "+EMV2Util.getPrintName(result));
			if (!result.isEmpty()){
			ErrorModelState st = (ErrorModelState) ErrorModelStateAdapterFactory.INSTANCE.adapt(fi, ErrorModelState.class);
			st.setToken(result.get(0));
			}
		} else if (path instanceof ErrorPath){
			ErrorPath epath = (ErrorPath)path;
			ErrorPropagation srcep = epath.getIncoming();
			ComponentInstance ci = fi.getContainingComponentInstance();
			FeatureInstance srcfi = EMV2Util.findFeatureInstance(srcep,ci);
			ErrorModelState srcst = (ErrorModelState) ErrorModelStateAdapterFactory.INSTANCE.adapt(srcfi, ErrorModelState.class);
			TypeToken tu = srcst.getToken();
			// map the token
			TypeToken ttup = epath.getTargetToken();
			if (ttup != null){
				tu = ttup;
			} else {
				// amp token via tms
				TypeMappingSet tms = epath.getTypeMappingSet();
				if (tms != null){
					tu = EM2TypeSetUtil.mapTypeToken(tu, tms);
				}
			}
			ErrorModelState st = (ErrorModelState) ErrorModelStateAdapterFactory.INSTANCE.adapt(fi, ErrorModelState.class);
			st.setToken(tu);
			ep = EMV2Util.getOutgoingErrorPropagation(fi);
		} else {
			ep = EMV2Util.getOutgoingErrorPropagation(fi);
		}
		report(fi,ep);
		return ep != null;
	}
	
	/**
	 * report on io object with optional error propagation.
	 * report on attached ErrorModelState if present
	 * note: error propagation ep can be null.
	 * @param io Instance Object
	 * @param ep Error Propagation
	 */
	public void report(InstanceObject io, ErrorPropagation ep){
		ComponentInstance ci = null;
		if (io instanceof FeatureInstance){
			ci = ((FeatureInstance)io).getContainingComponentInstance();
		} else if (io instanceof ComponentInstance){
			ci = (ComponentInstance)io;
		}
		if (ci != null){
			ErrorModelState st = (ErrorModelState) ErrorModelStateAdapterFactory.INSTANCE.adapt(io, ErrorModelState.class);
			TypeToken tu = st.getToken();
			if (tu != null){
				report.addOutputNewline(ci.getName()+
						(ep!=null?"."+EMV2Util.getPrintName(ep):"")+
						EMV2Util.getPrintName(tu));
				return;
			}
			if (ep != null){
			report.addOutputNewline(ci.getName()+"."+EMV2Util.getPrintName(ep)+
					(ep.getTypeSet()!=null?EMV2Util.getPrintName(ep.getTypeSet()):""));
			} else {
				report.addOutputNewline(ci.getName());
				}
		}
	}
	
	/**
	 * process component instance ci, which is the destination of path.
	 * Path can be a connection instance
	 * @param source Source instance object of path
	 * @param path connection instance or flow instance
	 * @param destination component instance
	 * @return true if to continue traversing, false if to prune traversal (not follow outgoing paths)
	 */
	protected boolean process(InstanceObject source,EObject path, ComponentInstance destination){

		ErrorModelState st = (ErrorModelState) ErrorModelStateAdapterFactory.INSTANCE.adapt(destination, ErrorModelState.class);
		
		return true;
	}
	
	/**
	 * traverse through the destination of the connection instance 
	 * @param conni
	 */
	protected void traceErrorFlows(InstanceObject source,ConnectionInstance conni, int depth){
		ConnectionInstanceEnd incie = conni.getDestination();
		ComponentInstance ci = incie instanceof ComponentInstance? (ComponentInstance)incie: incie.getContainingComponentInstance();
		faultModel.getContainingComponentInstance(ci);
		// we go to the end of the connection instance, not an enclosing component that may have an error model abstraction
		if (incie instanceof FeatureInstance){
			if (!process(source,conni,(FeatureInstance)incie)) { return;}
		} else {
			// component instance
			if (!process(source,conni,(ComponentInstance)incie)) {return;}
		}
		Collection<ErrorFlow> efs = EMV2Util.getAllErrorFlows(ci.getComponentClassifier());
		if (efs != null){
			ErrorFlow ef=EMV2Util.findErrorFlowFrom(efs, incie);
			if (ef instanceof ErrorSink){
				// process should have returned false, but for safety we check again
				return;
			} else if (ef instanceof ErrorPath){ // error path
				ErrorPropagation outp = ((ErrorPath)ef).getOutgoing();
				FeatureInstance fi = EMV2Util.findFeatureInstance(outp,ci);
				process(incie,ef,fi);
				EList<ConnectionInstance> connilist = Aadl2InstanceUtil.getOutgoingConnection(ci,fi);
				for (ConnectionInstance connectionInstance : connilist) {
					traceErrorFlows(fi,connectionInstance,depth+1);
				}
				return;
			} 
		}
		// no error flows: 
		// try flows or propagate to all outgoing connections
		if (incie instanceof FeatureInstance){
			FeatureInstance fi = ((FeatureInstance)incie);
			EList<FlowSpecificationInstance> flowlist = ci.getFlowSpecifications();
			if (!flowlist.isEmpty()){
				for (FlowSpecificationInstance flowSpecificationInstance : flowlist) {
					if (flowSpecificationInstance.getSource() == fi){
						if (process(incie,flowSpecificationInstance,fi)){
							FeatureInstance outp = flowSpecificationInstance.getDestination();
							if (outp != null){
								// assumes connection instance does not start inside
								EList<ConnectionInstance> outconnlist = outp.getSrcConnectionInstances();
								for (ConnectionInstance connectionInstance : outconnlist) {
									traceErrorFlows(outp,connectionInstance,depth+1);
								}
							}
						}
					}
				}
				return;
			}
		}
		// now all outgoing connections since we did not find flows
		EList<ConnectionInstance> connilist = Aadl2InstanceUtil.getOutgoingConnections(ci);
		for (ConnectionInstance connectionInstance : connilist) {
//			ConnectionInstanceEnd srcci = connectionInstance.getSource();
			ConnectionInstanceEnd srcci = (ConnectionInstanceEnd)Aadl2InstanceUtil.getSrcEndPointInstance(ci, conni);
			// this may be a feature instance of a contained component instance
			// we may want to find the feature of the component we are starting out from
			if (srcci instanceof FeatureInstance){
				if (!process(incie,null,(FeatureInstance)srcci)) {return;}
			} else {
				// component instance
				if (!process(incie,null,(ComponentInstance)srcci)) {return;}
			}
			traceErrorFlows(srcci,connectionInstance,depth+1);
		}
	}

	
//	/**
//	 * traverse through the destination of the connection instance 
//	 * @param conni
//	 */
//	protected void traceBackErrorFlows(ConnectionInstance conni, int depth){
//		int old = this.depth;
//		this.depth = depth;
//		ConnectionInstanceEnd incie = conni.getSource();
//		ComponentInstance ci = incie instanceof ComponentInstance? (ComponentInstance)incie: incie.getContainingComponentInstance();
//		if (!visited.add(incie)){
//			report(incie,"Visited before: ");
//		}
//		// we go to the end of the connection instance, not an enclosing component that may have an error model abstraction
//		ErrorPropagations eps = EM2Util.getComponentErrorPropagations(ci.getComponentClassifier());
//		if (incie instanceof FeatureInstance){
//			if (!process(conni,(FeatureInstance)incie)) {this.depth = old; return;}
//		} else {
//			// component instance
//			if (!process(conni,(ComponentInstance)incie)) {this.depth = old; return;}
//		}
//		if (eps != null){
//			ErrorFlow ef=EM2Util.findReverseErrorFlow(eps, incie);
//			if (ef instanceof ErrorSource){
//				// process should have returned false, but for safety we check again
//				this.depth = old; return;
//			} else if (ef instanceof ErrorPath){ // error path
//				ErrorPropagation outp = ((ErrorPath)ef).getIncoming();
//				FeatureInstance fi = ci.findFeatureInstance(outp.getFeature());
//				process(ef,fi);
//				EList<ConnectionInstance> connilist = Aadl2InstanceUtil.getIncomingConnection(ci,fi);
//				for (ConnectionInstance connectionInstance : connilist) {
//					traceErrorFlows(connectionInstance,depth+1);
//				}
//				this.depth = old; return;
//			} 
//		}
//		// no error flows: 
//		// try flows or propagate to all incoming connections
//		if (incie instanceof FeatureInstance){
//			FeatureInstance fi = ((FeatureInstance)incie);
//			EList<FlowSpecificationInstance> flowlist = ci.getFlowSpecifications();
//			if (!flowlist.isEmpty()){
//				for (FlowSpecificationInstance flowSpecificationInstance : flowlist) {
//					if (flowSpecificationInstance.getDestination() == fi){
//						if (process(flowSpecificationInstance,fi)){
//							FeatureInstance outp = flowSpecificationInstance.getSource();
//							if (outp != null){
//								// assumes connection instance does not start inside
//								EList<ConnectionInstance> outconnlist = outp.getDstConnectionInstances();
//								for (ConnectionInstance connectionInstance : outconnlist) {
//									traceErrorFlows(connectionInstance,depth+1);
//								}
//							}
//						}
//					}
//				}
//				this.depth = old; return;
//			}
//		}
//		// now all incoming connections since we did not find flows
//		EList<ConnectionInstance> connilist = Aadl2InstanceUtil.getIncomingConnections(ci);
//		for (ConnectionInstance connectionInstance : connilist) {
//			ConnectionInstanceEnd destci = connectionInstance.getDestination();
////			ConnectionInstanceEnd destci = (ConnectionInstanceEnd)Aadl2InstanceUtil.getDestEndPointInstance(ci, conni);
//			// this may be a feature instance of a contained component instance
//			// we may want to find the feature of the component we are starting out from
//			if (destci instanceof FeatureInstance){
//				if (!process(incie,(FeatureInstance)destci)) {this.depth = old; return;}
//			} else {
//				// component instance
//				if (!process(incie,(ComponentInstance)destci)) {this.depth = old; return;}
//			}
//			traceErrorFlows(connectionInstance,depth+1);
//		}
//	}

}

