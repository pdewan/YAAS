package yaas.visualizers.jungGraph;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.event.GraphEventListener;

public interface ObservableGraphInterface<V,E> extends Graph<V,E>{
	public void addGraphEventListener(GraphEventListener<V,E> l) ;
	public void removeGraphEventListener(GraphEventListener<V,E> l);

}
