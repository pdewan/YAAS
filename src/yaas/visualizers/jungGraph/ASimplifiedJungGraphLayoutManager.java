package yaas.visualizers.jungGraph;

import java.awt.Component;
import java.awt.Container;

import shapes.AttributedShape;

import yaas.common.VestigalListenableVector;
import yaas.layout.LayoutManager;
import yaas.shapemappers.PointerShapeCreator;
import bus.uigen.jung.AMonolithicJungGraphManager;
import bus.uigen.jung.JungGraphApplet;
import bus.uigen.jung.JungGraphManager;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;

public class ASimplifiedJungGraphLayoutManager<V,E> implements
	LayoutManager {

//	private int currX = 220;
//	private int baseLineY = 200;
//	private int barWidth = 10;
	private int scaleFactor = 10;
//	private int xSpacing = 15;
	
	private Container panel;
//	private APanelGenerator panelGenerator = new APanelGenerator();
	
	private ASimplifiedJungGraphVisualizer visualizer;
	JungGraphManager jungGraphManager;

	public ASimplifiedJungGraphLayoutManager(ASimplifiedJungGraphVisualizer visualizer){
		this.visualizer = visualizer;
	}
	
//	public ListenableVector<SimpleShape> display(ListenableVector<Integer> v) {
//
//		return null;
//	}

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

//	public int getScaleFactor() {
//		return scaleFactor;
//	}
//
//	public int getBaseLine() {
//		return 0;
//	}

	public Component displayInPanel() {
		JungGraphApplet applet = new JungGraphApplet();
		applet.init();
		Graph<V,E> aGraph = (Graph<V,E>) visualizer.getRootBuffer();
		jungGraphManager = new AMonolithicJungGraphManager(aGraph,
				applet.getContentPane());
//		applet.getContentPane().setBackground(Color.BLUE);
		panel = applet;
		panel.setSize(600, 600);
//		panel.setBackground(Color.yellow);
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

	public VestigalListenableVector<AttributedShape> display(
			ObservableGraph<Number, Number> data) {
		// TODO Auto-generated method stub
		return null;
	}

	public PointerShapeCreator getPointerShapeCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPointerShapeCreator(PointerShapeCreator newVal) {
		// TODO Auto-generated method stub
		
	}

	public Object createBuffer() {
		// TODO Auto-generated method stub
		return null;
	}
}
