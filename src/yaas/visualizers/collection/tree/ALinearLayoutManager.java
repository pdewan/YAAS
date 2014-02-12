package yaas.visualizers.collection.tree;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import shapes.TextShape;
import util.models.AListenableVector;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.VisualizationBasedVisualizer;
import yaas.PseudoCode;
import yaas.common.Util;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.shapemappers.PointerShapeCreator;
import bus.uigen.shapes.AShapeModel;
import bus.uigen.shapes.AStringModel;
import bus.uigen.shapes.ListenableShapeVector;

public class ALinearLayoutManager<ElementType> implements
		VisualizationBasedLayoutManager<ListenableVector<ElementType>> {

	protected LinearVisualizer<ElementType> visualizer;
	protected ListenableVector<AttributedShape> pseudoCode;
	protected int boxWidth = 0, boxHeight = 0;
	protected int verticalSpacing = 0, horizontalSpacing = 0;
	protected Color highlighting, color;
	protected boolean alignVertical, dynamicWidth, dynamicHeight, solid;
	protected int currentPseudoCodeMarker = 0;
	protected BoundedShape shape;

	private AttributedShape previousPseudoCodeLine, currentPseudoCodeLine;
	
	private Container panel = new JPanel();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ALinearLayoutManager(
			VisualizationBasedVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> cv) {

		pseudoCode = new AListenableVector<AttributedShape>();
		visualizer = (LinearVisualizer) cv;

		horizontalSpacing = 50;
		verticalSpacing = 10;
	}

	public ListenableVector<BoundedShape> display(
			ListenableVector<ElementType> vector) {
		visualizer.setRoot(visualizer.initRoot(vector, null, null));
		// initElements(root, vector);
		visualizer.getRoot().focusPosition(visualizer.getRoot());
		//((AListenableVector) visualizer).add(constructPseudoCode());
		constructPseudoCode();
		return null;
//		return visualizer.shapes();
	}

	public ListenableVector<AttributedShape> constructPseudoCode() {
		int i = 20;
		for (String s : PseudoCode.pseudoCode) {
			int numLeadingSpaces = Util.findLeadingSpaces(s);
//			this.pseudoCode.add(new AStringModel(s,  10+ numLeadingSpaces, i,
//					100, 20));
			this.pseudoCode.add(new AStringModel(s,  10+ numLeadingSpaces, i
					));
			i += 20;
		}
		return this.pseudoCode;
	}

	public ListenableVector<AttributedShape> getPseudoCode() {
		return this.pseudoCode;
	}



	public BoundedShape createShape(String stringValue, int initX, int initY) {

		try {
			BoundedShape s = this.shape.getClass().newInstance();

			try {
				s.setHeight(boxHeight
						* (dynamicHeight ? Integer.parseInt(stringValue) : 1));

				s.setWidth(boxWidth
						* (dynamicWidth ? Integer.parseInt(stringValue) : 1));
			} catch (Exception e) {
				s.setHeight(boxHeight);
				s.setWidth(boxWidth);
			}
			((AShapeModel) s).setFilled(solid);

			if (s instanceof TextShape) {
				((TextShape) s).setText(stringValue);
			}

			return s;
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	public int getBoxWidth() {
		return boxWidth;
	}

	public int getBoxHeight() {
		return boxHeight;
	}

	public int getHorizontalSpacing() {
		return horizontalSpacing;
	}

	public int getVerticalSpacing() {
		return verticalSpacing;
	}

	public Color getHighlighting() {
		return highlighting;
	}

	public Color getColor() {
		return color;
	}

	public boolean getVertical() {
		return alignVertical;
	}

	public boolean getDynamicWidth() {
		return dynamicWidth;
	}

	public boolean getDynamicHeight() {
		return dynamicHeight;
	}

	public void setShape(BoundedShape shape) {
		this.shape = shape;
		boxWidth = shape.getWidth();
		boxHeight = shape.getHeight();
	}

	public void setHighlighting(Color highlighting) {
		this.highlighting = highlighting;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setVertical(boolean alignVertical) {
		this.alignVertical = alignVertical;
	}

	public void setDynamicWidth(boolean dynamicWidth) {
		this.dynamicWidth = dynamicWidth;
	}

	public void setDynamicHeight(boolean dynamicHeight) {
		this.dynamicHeight = dynamicHeight;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public boolean getSolid() {
		return solid;
	}

	public int getPseudoCodeMarker() {
		return currentPseudoCodeMarker;
	}

	private void setPseudoCodeMarker(int newVal) {
		if (newVal > 0 && newVal < pseudoCode.size() + 1) {
			currentPseudoCodeMarker = newVal;
		}
	}

	public synchronized void setPseudoCodePointer(int newVal) {

		previousPseudoCodeLine = currentPseudoCodeLine;
		if (null != previousPseudoCodeLine) {
			((FlexibleShape) previousPseudoCodeLine).setColor(color);
		}

		this.setPseudoCodeMarker(newVal);

		currentPseudoCodeLine = pseudoCode.get(currentPseudoCodeMarker - 1);
		((FlexibleShape) currentPseudoCodeLine).setColor(highlighting);
	}

	public void clearPseudoCodePointers() {
		for(AttributedShape s: pseudoCode){
			((FlexibleShape) s).setColor(color);
		}
		currentPseudoCodeMarker = 0;
		
	}

	public Component displayInPanel() {
		return panel;
	}

	public Container getPanel() {
		return panel;
	}
	public void setPanel(Container newVal) {
		panel = newVal;
	}

	public PointerShapeCreator getPointerShapeCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPointerShapeCreator(PointerShapeCreator newVal) {
		// TODO Auto-generated method stub
		
	}

	public ListenableVector<ElementType> createBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPreferredHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPreferredHeight(int newVal) {
		// TODO Auto-generated method stub
		
	}

	public int getPreferredWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPreferredWidth(int newVal) {
		// TODO Auto-generated method stub
		
	}

	public ListenableShapeVector getContainingShapes(
			ListenableVector<ElementType> data) {
		// TODO Auto-generated method stub
		return null;
	}
}
