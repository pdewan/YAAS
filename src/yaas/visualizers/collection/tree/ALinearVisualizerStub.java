package yaas.visualizers.collection.tree;
// vestigal class

import java.awt.Color;

import shapes.BoundedShape;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.ADelegatingAnimator;
import yaas.Visualization;
import yaas.layout.nodes.ALinearRoot;
import yaas.layout.nodes.LinearElement;
import bus.uigen.shapes.ALabelModel;
import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.ListenableShapeVector;

public class ALinearVisualizerStub<ElementType> extends
		ADelegatingAnimator<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> implements
		LinearVisualizer<ElementType>{

	protected ALinearVisualizer<ElementType> visualizer;

	public ALinearVisualizerStub(ALinearVisualizer<ElementType> visualizer) {
		super(visualizer);
		this.visualizer = (ALinearVisualizer<ElementType>) super.visualizer;
	}

	public LinearElement initElement(Object element, LinearElement parent,
			LinearElement previousChild) {
		return visualizer.initElement(element, parent, previousChild);
	}

	public void showLines(boolean newVal) {
		visualizer.showLines(newVal);
	}

	public void showRoot(boolean newVal) {
		visualizer.showRoot(newVal);
	}

	public Visualization getVisualization() {
		return visualizer.shapes();
	}

	public ALabelModel getLabel() {
		return visualizer.getLabel();
	}
	public void setLabel(ALabelModel label) {
		 visualizer.setLabel(label);
	}

	public int getBoxHeight() {
		return visualizer.getLayoutManager().getBoxHeight();
	}

	public int getBoxWidth() {
		return visualizer.getLayoutManager().getBoxWidth();
	}

	public Color getColor() {
		return visualizer.getLayoutManager().getColor();
	}

	public boolean getDynamicHeight() {
		return visualizer.getLayoutManager().getDynamicHeight();
	}

	public boolean getDynamicWidth() {
		return visualizer.getLayoutManager().getDynamicWidth();
	}

	public Color getHighlighting() {
		return visualizer.getLayoutManager().getHighlighting();
	}

	public LinearElement getRoot() {
		return visualizer.getRoot();
	}

	public void removeLine(ALineModel l) {
		visualizer.removeLine(l);

	}

	public Visualization shapes() {
		return visualizer.shapes();
	}

	public ALinearRoot initRoot(Object element, LinearElement parent,
			LinearElement previousChild) {
		return visualizer.initRoot(element, parent, previousChild);
	}

	public void setRoot(LinearElement newVal) {
		visualizer.setRoot(newVal);

	}

	public ALinearLayoutManager<ElementType> getLayoutManager() {
		return visualizer.getLayoutManager();
	}

	public Integer getRootShapesIndex(ListenableVector<ElementType> aRoot) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setVisualization(Visualization newVal) {
		// TODO Auto-generated method stub
		
	}

	public ListenableShapeVector createShapeVector() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
