package yaas.visualizers.jungGraph;

import yaas.trappers.EventGenerator;
import bus.uigen.jung.JungGraphManager;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;
import edu.uci.ics.jung.graph.event.GraphEventListener;

public interface JungGraphEventGenerator<V,E> extends Graph<V,E>,
		EventGenerator<GraphEventListener<V,E>, JungGraphEventGenerator<V,E> > {

}
