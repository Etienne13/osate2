package edu.uah.rsesc.aadl.age.diagrams.type;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IMoveBendpointFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import edu.uah.rsesc.aadl.age.diagrams.common.AgeFeatureProvider;
import edu.uah.rsesc.aadl.age.diagrams.common.patterns.FeaturePattern;
import edu.uah.rsesc.aadl.age.diagrams.common.patterns.FlowSpecificationPattern;
import edu.uah.rsesc.aadl.age.diagrams.common.patterns.ModePattern;
import edu.uah.rsesc.aadl.age.diagrams.type.features.TypeUpdateDiagramFeature;
import edu.uah.rsesc.aadl.age.diagrams.type.patterns.TypeClassifierPattern;

public class TypeFeatureProvider extends AgeFeatureProvider {
	public TypeFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
		this.addPattern(new FeaturePattern());
		this.addPattern(new ModePattern());
		this.addPattern(new TypeClassifierPattern());
		this.addConnectionPattern(new FlowSpecificationPattern());
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
	   PictogramElement pictogramElement = context.getPictogramElement();
	   if(pictogramElement instanceof Diagram) {
		   return new TypeUpdateDiagramFeature(this);
	   }
	   return super.getUpdateFeature(context);
	}
	
	// Disable all moving of bendpoints. Context does not seem to contain reference to connection. Ideally will only disable for source and sink flow specifications
	@Override
	public IMoveBendpointFeature getMoveBendpointFeature(final IMoveBendpointContext context) {
		return null;
	}
}
