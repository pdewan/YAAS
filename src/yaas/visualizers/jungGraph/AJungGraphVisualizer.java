package yaas.visualizers.jungGraph;


import java.beans.PropertyChangeListener;

import shapes.AttributedShape;

import yaas.AVisualizer;
import yaas.VisualizationBasedVisualizer;
import yaas.Visualizer;
import yaas.controller.AGenericButtonPressTrapper;
import yaas.controller.ButtonPressTrapper;
import yaas.layout.VisualizationBasedLayoutManager;

import bus.uigen.jung.JungGraphManager;
import bus.uigen.oadapters.ObjectAdapter;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.event.GraphEventListener;
import edu.uci.ics.jung.graph.util.Graphs;

public class AJungGraphVisualizer<V,E> extends
		AVisualizer<GraphEventListener<V, E>, JungGraphEventGenerator<V,E>>
		implements
		Visualizer<GraphEventListener<V, E>, JungGraphEventGenerator<V,E>> 

{

	private static final long serialVersionUID = 5651052060059133256L;
	private int pauseTimeInMilliseconds = 20;
	private JungGraphEventGenerator jungGraphEventGenerator;


	public AJungGraphVisualizer(VisualizationBasedLayoutManager<JungGraphEventGenerator<V,E>> layoutManager, JungGraphEventGenerator aJungGraphObservable) {
		super();
		this.jungGraphEventGenerator = aJungGraphObservable;
//		((AJungGraphBuffer) buffer).setBean(this.bean);
//		this.layoutManager = layoutManager;
		
	}
	@Override
	protected synchronized void visualizeBuffer(JungGraphEventGenerator<V,E> anObservable) {
		// do nothing, the layout manager takes care of displaying
	}
	
	 public JungGraphManager<V,E> getJungGraphManager() {
		return ((AJungGraphLayoutManager) firstLayoutManager).getJungGraphManager();
	}

	
	public AJungGraphVisualizer(JungGraphEventGenerator<V,E> aGraph) {
		super();
		this.jungGraphEventGenerator = aGraph;
//		((AJungGraphBuffer) buffer).setBean(this.bean);
		
	}

	
	protected JungGraphEventGenerator<V,E> createBuffer() {
		Graph<V,E> aGraph = Graphs.<V,E>synchronizedDirectedGraph(new DirectedSparseMultigraph<V,E>());

		return new AJungGraphBuffer(aGraph, this);
	}
	
	

	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new AGenericButtonPressTrapper(controller, this,
				pauseTimeInMilliseconds);
	}
//	public void addReplayMethodListener(GraphEventListener<V, E> anObserver) {
//		((AJungGraphBuffer) getRootBuffer()).addGraphEventListener(anObserver);
//		
//	}
//	public boolean removeElement(SimpleShape c) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	@Override
	public void addObserver(JungGraphEventGenerator<V, E> anObservable,
			GraphEventListener<V, E> anObserver) {
		((AJungGraphBuffer) anObservable).addGraphEventListener(anObserver);
		
	}

}
