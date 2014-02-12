package yaas.visualizers.jungGraph;

import yaas.trappers.EventTrapper;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;
import edu.uci.ics.jung.graph.event.GraphEvent;
import edu.uci.ics.jung.graph.event.GraphEvent.Edge;
import edu.uci.ics.jung.graph.event.GraphEvent.Type;
import edu.uci.ics.jung.graph.event.GraphEvent.Vertex;
import edu.uci.ics.jung.graph.event.GraphEventListener;
import edu.uci.ics.jung.graph.util.Pair;

public  class AJungGraphEventTrapper<V,E> extends ObservableGraph<V,E> implements GraphEventListener<V,E>,
		EventTrapper<GraphEventListener<V,E>, JungGraphEventGenerator<V,E>>,
		JungGraphEventGenerator<V,E> {
	JungGraphEventGenerator<V,E> jungGraphEventGenerator;
	AJungGraphLayoutManager<V, E> layoutManager;
	AJungGraphVisualizer<V, E> visualizer;
	AJungGraphBuffer<V,E> buffer;
	
	
	public AJungGraphEventTrapper(Graph<V,E> aGraph, JungGraphEventGenerator<V,E> aJungGrapEventGenerator, AJungGraphBuffer<V,E> aBuffer ) {
		super(aGraph);
		jungGraphEventGenerator = aJungGrapEventGenerator;	
		buffer = aBuffer;
	}
	
	public AJungGraphEventTrapper(
			AJungGraphVisualizer<V, E> aVisualizer ) {
		super(null);
		visualizer = aVisualizer;
		layoutManager = (AJungGraphLayoutManager) aVisualizer.getFirstLayoutManager();
		buffer = (AJungGraphBuffer) aVisualizer.getRootBuffer();
		// TODO Auto-generated constructor stub
	}

	public AJungGraphEventTrapper(
			AJungGraphVisualizer<V, E> aVisualizer,
			AJungGraphLayoutManager aLayoutManager,
			AJungGraphBuffer<V,E> aBuffer ) {
		super(null);
		visualizer = aVisualizer;
		layoutManager = aLayoutManager;
		buffer = aBuffer;
		// TODO Auto-generated constructor stub
	}

	public void addListener(
			EventTrapper<GraphEventListener<V, E>, JungGraphEventGenerator<V, E>> observer)
			throws Exception {
		jungGraphEventGenerator.addListener(observer);
	}

	public void removeListener(
			EventTrapper<GraphEventListener<V, E>, JungGraphEventGenerator<V, E>> observer) {
		jungGraphEventGenerator.removeListener(observer);

	}

	public void handleGraphEvent(GraphEvent<V, E> evt) {
		Type eventType = evt.getType();
		switch (eventType) {
		case VERTEX_ADDED: 
			handleVertexAddEvent((Vertex<V,E>) evt);
			break;		
		case VERTEX_REMOVED: 
		
		case EDGE_ADDED:
			
		case EDGE_REMOVED:
		}
		
		
	}
	public void handleVertexAddEvent(Vertex<V, E> evt) {
//		jungGraphEventGenerator.addVertex(evt.getVertex());
	
	}
    public void handleVertexRemoveEvent(Vertex<V, E> evt) {
//		jungGraphEventGenerator.removeVertex(evt.getVertex());		
	}
    
    public void handleEdgeAddEvent(Edge<V, E> evt) {
//    	E edge = evt.getEdge();
//    	Pair<V>  theEndPoints = getEndpoints(edge);
//    	jungGraphEventGenerator.addEdge(edge, theEndPoints);	
		
  	}
    
    public void handleEdgeRemoveEvent(Edge<V, E> evt) {
//    	E edge = evt.getEdge();    	
//    	jungGraphEventGenerator.removeEdge(edge);	
  	}

	
    
    
	

//	public void addListener(
//			EventTrapper<GraphEventListener<V,E>, JungGraphEventGenerator> observer)
//			throws Exception {
//
//		bean.addListener(observer);
//	}
//
//	public void removeListener(
//			EventTrapper<GraphEventListener<V,E>, JungGraphEventGenerator> observer) {
//		bean.removeListener(observer);
//	}

//	public abstract void propertyChange(PropertyChangeEvent arg0);
}
