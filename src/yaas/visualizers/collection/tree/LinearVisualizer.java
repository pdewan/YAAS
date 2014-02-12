package yaas.visualizers.collection.tree;

import shapes.BoundedShape;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.VisualizationBasedVisualizer;
import yaas.Visualization;
import yaas.layout.nodes.ALinearRoot;
import yaas.layout.nodes.LinearElement;
import bus.uigen.shapes.ALineModel;

public interface LinearVisualizer<ElementType>
		extends
		VisualizationBasedVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> {

	ALinearRoot initRoot(Object element, LinearElement parent,
			LinearElement previousChild);

	LinearElement getRoot();

	void setRoot(LinearElement newVal);

	Visualization shapes();

	LinearElement initElement(Object element, LinearElement parent,
			LinearElement parentChild);

	void removeLine(ALineModel l);

}
