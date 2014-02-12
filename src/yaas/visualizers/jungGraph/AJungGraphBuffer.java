package yaas.visualizers.jungGraph;

import java.util.Collection;

import yaas.ABuffer;
import yaas.Buffer;
import yaas.VisualizationBasedVisualizer;
import yaas.Visualizer;
import yaas.commands.CommandHistory;
import yaas.commands.jungGraph.ARemoveEdgeCommand;
import yaas.commands.jungGraph.ARemoveVertexCommand;
import yaas.commands.jungGraph.AnAddEdgeCommand;
import yaas.commands.jungGraph.AnAddVertexCommand;
import yaas.controller.Control;
import yaas.trappers.EventTrapper;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;
import edu.uci.ics.jung.graph.event.GraphEvent;
import edu.uci.ics.jung.graph.event.GraphEvent.Edge;
import edu.uci.ics.jung.graph.event.GraphEvent.Type;
import edu.uci.ics.jung.graph.event.GraphEvent.Vertex;
import edu.uci.ics.jung.graph.event.GraphEventListener;
import edu.uci.ics.jung.graph.util.Pair;

public class AJungGraphBuffer<V,E> extends ObservableGraph<V, E>
		implements
		GraphEventListener<V, E>,
		JungGraphEventGenerator<V, E>,
		Buffer<GraphEventListener<V, E>, JungGraphEventGenerator<V, E>>, EventTrapper<GraphEventListener<V, E>, JungGraphEventGenerator<V, E>> {
//	,
//}
//		EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> {

	protected Buffer<GraphEventListener<V, E>, JungGraphEventGenerator<V,E>> buffer;
	
	AJungGraphVisualizer<V, E> jungGraphVisualizer;
	AJungGraphEventTrapper<Number, Number> trapper;
	
	public AJungGraphBuffer(Graph<V,E> aGraph,
			Visualizer<GraphEventListener<V, E>, JungGraphEventGenerator<V,E>> aVisualizer) {
		super(aGraph);
		buffer = new ABuffer<GraphEventListener<V, E>, JungGraphEventGenerator<V,E>>(aVisualizer);
		jungGraphVisualizer = (AJungGraphVisualizer<V, E>) aVisualizer;
		
	}

	public AJungGraphBuffer(Graph<V,E> aGraph,
			JungGraphEventGenerator<V,E> data,
			Visualizer<GraphEventListener<V, E>, JungGraphEventGenerator<V,E>> visualizer) {
		super(aGraph);
		buffer = new ABuffer<GraphEventListener<V, E>, JungGraphEventGenerator<V,E>>(visualizer);

		this.setBufferData(data);

	}

	public CommandHistory getCommandHistory() {
		return buffer.getCommandHistory();
	}

	public void putBufferThread(
			Visualizer<GraphEventListener<V, E>, JungGraphEventGenerator<V, E>> v,
			Control control) {
		// TODO Auto-generated method stub
		buffer.putBufferThread(v, control);

		
	}

	public void setBufferData(JungGraphEventGenerator<V, E> data) {
		buffer.setBufferData(data);
		// we can clear the graph, but we will assume we have a cleared graph
		try {
			data.addListener(this);
			Collection<V> someVertices = data.getVertices();
			for (V aVertex:someVertices) {
				addVertex(aVertex);	
			}
			Collection<E> someEdges= data.getEdges();
			for (E anEdge:someEdges) {
				Pair<V> endPointPair = data.getEndpoints(anEdge);
				addEdge(anEdge, endPointPair);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public JungGraphEventGenerator<V, E> getBufferData() {
		return buffer.getBufferData();
	}

	
	public void handleGraphEvent(GraphEvent<V, E> evt) {
		Type eventType = evt.getType();
		switch (eventType) {
		case VERTEX_ADDED: 
			handleVertexAddEvent((Vertex<V,E>) evt);
			break;		
		case VERTEX_REMOVED: 
		
		case EDGE_ADDED:
			handleEdgeAddEvent((Edge<V,E>) evt);
			
		case EDGE_REMOVED:
		}
		
		
	}
	public void handleVertexAddEvent(Vertex<V, E> evt) {
//		this.getCommandHistory().addCommand(new AnAddVertexCommand<V,E>(this, evt.getVertex()));
		jungGraphVisualizer.getCommandHistory().addCommand(new AnAddVertexCommand<V,E>(jungGraphVisualizer.getJungGraphManager(), evt.getVertex()));

	
	}
    public void handleVertexRemoveEvent(Vertex<V, E> evt) {
    	jungGraphVisualizer.getCommandHistory().addCommand(new ARemoveVertexCommand<V,E>(jungGraphVisualizer.getJungGraphManager(), evt.getVertex()));

	}
    
    public void handleEdgeAddEvent(Edge<V, E> evt) {
    	E anEdge = evt.getEdge();
    	Graph<V,E> source = evt.getSource();
    	Pair<V> aVertixPair = source.getEndpoints(anEdge);
//		this.getCommandHistory().addCommand(new AnAddEdgeCommand<V,E>(this, anEdge, aVertixPair));
    	jungGraphVisualizer.getCommandHistory().addCommand(new AnAddEdgeCommand<V,E>(jungGraphVisualizer.getJungGraphManager(), anEdge, aVertixPair));

		
  	}
    public void handleEdgeRemoveEvent(Edge<V, E> evt) {
    	E anEdge = evt.getEdge();
//		this.getCommandHistory().addCommand(new AnAddEdgeCommand<V,E>(this, anEdge, aVertixPair));
    	jungGraphVisualizer.getCommandHistory().addCommand(new ARemoveEdgeCommand<V,E>(jungGraphVisualizer.getJungGraphManager(), this, anEdge));

		
  	}
    
	
	
//	public void putBufferThread(
//			CollectionVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> v,
//			Control control) {
//		buffer.putBufferThread(v, control);
//
//	}

//	public void setBufferData(ListenableVector<ElementType> data) {
//		buffer.setBufferData(data);
//		this.removeAllElements();
//		data.addVectorMethodsListener(this);
//		
//		for(ElementType e: data){
//			this.add(e);
//		}
//	}
	private static final long serialVersionUID = -5548423608100622613L;


//	public ListenableVector<ElementType> getBufferData() {
//		return buffer.getBufferData();
//	}
	@SuppressWarnings("unchecked")
	// the signature of the method does not seem to matter
	public void addListener(
			EventTrapper<GraphEventListener<V, E>, JungGraphEventGenerator<V, E>> observer)
			throws Exception {
		
			if (!(observer instanceof GraphEventListener))
				throw new Exception("Ill Defined Trapper: The trapper "
						+ observer.toString()
						+ "must be an instance of an observer and an observable");
			this.addGraphEventListener( (GraphEventListener<V, E>) observer);

		}


	public void removeListener(
			EventTrapper<GraphEventListener<V, E>, JungGraphEventGenerator<V, E>> observer) {
		if (observer instanceof GraphEventListener) {
			this.removeGraphEventListener((GraphEventListener<V, E>) observer);
			
		}
		
	}

	public void setTrapper(AJungGraphEventTrapper<Number, Number> aTrapper) {
		trapper = aTrapper;
		
	}


}
