package yaas.visualizers.jungGraph;


import util.models.PropertyListenerRegistrar;
import yaas.AVisualizer;
import yaas.Buffer;
import yaas.Visualizer;
import yaas.controller.AGenericButtonPressTrapper;
import yaas.controller.ButtonPressTrapper;
import yaas.layout.VisualizationBasedLayoutManager;
import bus.uigen.jung.JungGraphManager;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;
import edu.uci.ics.jung.graph.event.GraphEventListener;
import edu.uci.ics.jung.graph.util.Graphs;

public class ASimplifiedJungGraphVisualizer<V,E> extends
		AVisualizer<GraphEventListener<V, E>, ObservableGraph<V,E>>
		implements
		Visualizer<GraphEventListener<V, E>,  ObservableGraph<V,E>> {

	private static final long serialVersionUID = 5651052060059133256L;
	private int pauseTimeInMilliseconds = 20;
	private ObservableGraph jungGraphEventGenerator;
	ObservableGraph<V,E> jungBuffer;

	public ASimplifiedJungGraphVisualizer(VisualizationBasedLayoutManager<ObservableGraph<V,E>> layoutManager, ObservableGraph aJungGraphObservable) {
		super();
		this.jungGraphEventGenerator = aJungGraphObservable;
//		((AJungGraphBuffer) buffer).setBean(this.bean);
//		this.layoutManager = layoutManager;
		
	}
	@Override
	protected synchronized void visualizeBuffer(ObservableGraph<V,E> anObservable) {
		// do nothing, the layout manager takes care of displaying
	}
	
	 public JungGraphManager<V,E> getJungGraphManager() {
		return ((ASimplifiedJungGraphLayoutManager) firstLayoutManager).getJungGraphManager();
	}

	
	public ASimplifiedJungGraphVisualizer(ObservableGraph<V,E> aGraph) {
		super();
		this.jungGraphEventGenerator = aGraph;
//		((AJungGraphBuffer) buffer).setBean(this.bean);
		
	}
	@Override
	protected ObservableGraph<V,E> setBufferData(ObservableGraph<V,E>  data) {
//		PropertyListenerRegisterer rootBuffer = createBuffer();
		ObservableGraph<V,E> rootBuffer = getRootBuffer();

		((Buffer) rootBuffer).setBufferData(data);
//		rootBuffers.add(rootBuffer);
		this.addReplayMethodListenersToBuffer(rootBuffer);
		return rootBuffer;
	}
	
	public ObservableGraph<V,E> getRootBuffer() {
		if (jungBuffer == null)
			jungBuffer = createBuffer();
		return jungBuffer;
////		return rootBuffer;
//		if (rootBuffers.size() == 0) 
//			rootBuffers.add(firstLayoutManager.createBuffer());
////		}	else
//				return rootBuffers.get(0);
	}

	protected ObservableGraph<V,E> createBuffer() {
		Graph<V,E> aGraph = Graphs.<V,E>synchronizedDirectedGraph(new DirectedSparseMultigraph<V,E>());

		return new ASimplifiedJungGraphBuffer(aGraph, this);
	}
	
	

	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new AGenericButtonPressTrapper(controller, this,
				pauseTimeInMilliseconds);
	}
//	public void addReplayMethodListener(GraphEventListener<V, E> anObserver) {
//		((ASimplifiedJungGraphBuffer) getRootBuffer()).addGraphEventListener(anObserver);
//		
//	}
	@Override
	public void addObserver(ObservableGraph<V, E> anObservable,
			GraphEventListener<V, E> anObserver) {
		anObservable.addGraphEventListener(anObserver);
		
	}
	
//	public boolean removeElement(SimpleShape c) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
