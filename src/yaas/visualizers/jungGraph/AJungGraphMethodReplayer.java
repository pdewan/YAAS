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

public  class AJungGraphMethodReplayer<V,E> extends ObservableGraph<V,E> implements GraphEventListener<V,E> {
	ObservableGraph<V,E> jungGraphEventGenerator;
//	ASimplifiedJungGraphLayoutManager<V, E> layoutManager;
	ASimplifiedJungGraphVisualizer<V, E> visualizer;
	ASimplifiedJungGraphBuffer<V,E> buffer;
	
	
	public AJungGraphMethodReplayer(Graph<V,E> aGraph, ObservableGraph<V,E> aJungGrapEventGenerator, ASimplifiedJungGraphBuffer<V,E> aBuffer ) {
		super(aGraph);
		jungGraphEventGenerator = aJungGrapEventGenerator;	
		buffer = aBuffer;
	}
	
	public AJungGraphMethodReplayer(
			ASimplifiedJungGraphVisualizer<V, E> aVisualizer ) {
		super(null);
		visualizer = aVisualizer;
//		layoutManager = (ASimplifiedJungGraphLayoutManager) aVisualizer.getLayoutManager();
		buffer = (ASimplifiedJungGraphBuffer) aVisualizer.getRootBuffer();
		// TODO Auto-generated constructor stub
	}

	public AJungGraphMethodReplayer(
			ASimplifiedJungGraphVisualizer<V, E> aVisualizer,
			ASimplifiedJungGraphLayoutManager<V,E> aLayoutManager,
			ASimplifiedJungGraphBuffer<V,E> aBuffer ) {
		super(null);
		visualizer = aVisualizer;
//		layoutManager = aLayoutManager;
		buffer = aBuffer;
		// TODO Auto-generated constructor stub
	}

//	public void addListener(
//			GraphEventListener<V, E> observer)
//			throws Exception {
//		jungGraphEventGenerator.addGraphEventListener(observer);
//	}
//
//	public void removeListener(
//			GraphEventListener<V, E> observer)  {
//		jungGraphEventGenerator.removeGraphEventListener(observer);
//
//	}

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
