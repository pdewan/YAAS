package yaas.visualizers.jungGraph;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.TextField;

import javax.swing.JTextField;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;

import yaas.common.VestigalListenableVector;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.shapemappers.PointerShapeCreator;

import bus.uigen.jung.AMonolithicJungGraphManager;
import bus.uigen.jung.JungGraphApplet;
import bus.uigen.jung.JungGraphManager;
import bus.uigen.shapes.ListenableShapeVector;
import edu.uci.ics.jung.graph.Graph;

public class AJungGraphLayoutManager<V,E> implements
	 VisualizationBasedLayoutManager<JungGraphEventGenerator<Number, Number>> {

//	private int currX = 220;
//	private int baseLineY = 200;
//	private int barWidth = 10;
	private int scaleFactor = 10;
//	private int xSpacing = 15;
	
	private Container panel;
//	private APanelGenerator panelGenerator = new APanelGenerator();
	
	private AJungGraphVisualizer visualizer;
	JungGraphManager jungGraphManager;

	public AJungGraphLayoutManager(AJungGraphVisualizer visualizer){
		this.visualizer = visualizer;
	}
	
	public VestigalListenableVector<AttributedShape> display(VestigalListenableVector<Integer> v) {

		return null;
	}

//	public OEShape nextShape(int val) {
//		int height = val * scaleFactor;
//		int currY = baseLineY - height;
//		OEShape retVal = new ARectangleModel(currX, currY, barWidth, height);
//		currX += xSpacing;
//		return retVal;
//	}

	public util.models.ListenableVector<AttributedShape> constructPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public VestigalListenableVector<AttributedShape> getPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPseudoCodeMarker() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getScaleFactor() {
		return scaleFactor;
	}

	public int getBaseLine() {
		return 0;
	}

	public Component displayInPanel() {
		JungGraphApplet applet = new JungGraphApplet();
		applet.init();
		Graph<V,E> aGraph = (JungGraphEventGenerator) visualizer.getRootBuffer();
		jungGraphManager = new AMonolithicJungGraphManager(aGraph,
				applet.getContentPane());
		panel = applet;
		panel.setSize(600, 600);
		panel.setBackground(Color.yellow);
//		panel.add(new JTextField("hellow"));
//		return new JTextField("hello");
		return panel;
	}
	
	public JungGraphManager getJungGraphManager() {
		return jungGraphManager;
	}

	public Container getPanel() {
		return panel;
	}
//
//	public ListenableVector<OEShape> display(Graph<V, E> data) {
//		return null;
//	}

	public util.models.ListenableVector<BoundedShape> display(
			JungGraphEventGenerator<Number, Number> data) {
		// TODO Auto-generated method stub
		return null;
	}
//
//	public PointerShapeCreator getPointerShapeCreator() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void setPointerShapeCreator(PointerShapeCreator newVal) {
//		// TODO Auto-generated method stub
//		
//	}

	public PointerShapeCreator getPointerShapeCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPointerShapeCreator(PointerShapeCreator newVal) {
		// TODO Auto-generated method stub
		
	}

	public JungGraphEventGenerator<Number, Number> createBuffer() {
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
			JungGraphEventGenerator<Number, Number> data) {
		// TODO Auto-generated method stub
		return null;
	}
}
