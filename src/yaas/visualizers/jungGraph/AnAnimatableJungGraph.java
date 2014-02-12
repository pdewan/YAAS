package yaas.visualizers.jungGraph;

import yaas.common.VestigalListenableVector;
import yaas.trappers.EventGenerator;
import yaas.trappers.EventTrapper;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;
import edu.uci.ics.jung.graph.event.GraphEventListener;

public class AnAnimatableJungGraph<V,E> extends ObservableGraph<V, E> 
		implements JungGraphEventGenerator<V, E> {
//		ObservableGraphInterface<V,E>,
//EventGenerator<GraphEventListener<V, E>, ObservableGraphInterface<V, E>> ,{

	public AnAnimatableJungGraph(Graph<V, E> delegate) {
		super(delegate);
	}
//
//	public void addListener(
//			EventTrapper<GraphEventListener<V, E>, ObservableGraphInterface<V, E>> observer)
//			throws Exception {
//		if (!(observer instanceof GraphEventListener))
//			throw new Exception("Ill Defined Trapper: The trapper "
//					+ observer.toString()
//					+ "must be an instance of an observer and an observable");
//		this.addGraphEventListener( (GraphEventListener<V, E>) observer);
//
//	}
//		
//	
//
//	public void removeListener(
//			EventTrapper<GraphEventListener<V, E>, ObservableGraphInterface<V, E>> observer) {
//		if (observer instanceof GraphEventListener) {
//			this.removeGraphEventListener((GraphEventListener<V, E>) observer);
//			
//		}
//		
//		
//	}

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
	
	

}
