package yaas.examples;

import yaas.OE.Algorithms;
import yaas.OE.CreateCustomView;
import yaas.common.*;
import yaas.layout.ACompositeRowColumnLayoutManager;
import yaas.visualizers.collection.flat.AnIntegerBarChartVisualizer;
import yaas.visualizers.jungGraph.AJungGraphMethodReplayer;
import yaas.visualizers.jungGraph.AJungGraphVisualizer;
import yaas.visualizers.jungGraph.AJungGraphBuffer;
import yaas.visualizers.jungGraph.AJungGraphEventTrapper;
import yaas.visualizers.jungGraph.AJungGraphLayoutManager;
import yaas.visualizers.jungGraph.ASimplifiedJungGraphBuffer;
import yaas.visualizers.jungGraph.ASimplifiedJungGraphLayoutManager;
import yaas.visualizers.jungGraph.ASimplifiedJungGraphVisualizer;
import yaas.visualizers.jungGraph.AnAnimatableJungGraph;
import yaas.visualizers.jungGraph.ObservableGraphInterface;

import edu.uci.ics.jung.algorithms.layout.util.Relaxer;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.ObservableGraph;
import edu.uci.ics.jung.graph.util.Graphs;

public class SimplifiedJungGraph {

	public static void main(String[] args) {
//		Graph<Number,Number> ig = Graphs.<Number,Number>synchronizedDirectedGraph(new DirectedSparseMultigraph<Number,Number>());
		Graph<Number,Number> ig = new DelegateForest<Number, Number>();
//        ObservableGraph<Number,Number> og = new ObservableGraph<Number,Number>(ig);
//        AnAnimatableJungGraph<Number, Number> ag = new AnAnimatableJungGraph<Number, Number>(ig);
        ObservableGraph<Number, Number> ag = new ObservableGraph<Number, Number>(ig);



//		vector.add(10);
//		vector.add(9);
//		vector.add(8);
//		vector.add(7);
//		vector.add(6);
//		vector.add(5);
//		vector.add(4);
//		vector.add(3);
//		vector.add(2);
//		vector.add(1);

		try {
//			AJungGraphLayoutManager<Number, Number> layoutManager = new AJungGraphLayoutManager<Number, Number>(visualizer)
			ASimplifiedJungGraphVisualizer<Number, Number> visualizer = new ASimplifiedJungGraphVisualizer(ag);
//			visualizer.setCompositeLayoutManager(new ACompositeRowColumnLayoutManager(visualizer));

			
			ASimplifiedJungGraphLayoutManager<Number, Number> layoutManager = new ASimplifiedJungGraphLayoutManager<Number, Number>(visualizer);
//			visualizer.setLayoutManager(layoutManager);

//			visualizer.visualize(ag, layoutManager);
			AJungGraphMethodReplayer<Number, Number> trapper = new AJungGraphMethodReplayer<Number, Number>(visualizer,
					(ASimplifiedJungGraphLayoutManager<Number, Number>) visualizer
					.getFirstLayoutManager(), (ASimplifiedJungGraphBuffer<Number, Number>) visualizer
					.getRootBuffer());
			visualizer.addReplayMethodListenerOfObjectTree(ag, trapper);
			visualizer.visualize(ag, layoutManager);

//			((AJungGraphBuffer) visualizer
//			.getBuffer()).setTrapper(trapper);
		
//			
			

			CreateCustomView viewCreator = new CreateCustomView(600, 600);
//			viewCreator.setDataPanel(visualizer.getLayoutManager()
//					.displayInPanel());
			viewCreator.setDataPanel(visualizer.getDataPanel(ag));

			Object[] menuItems = null;
//			CreateCustomView viewCreator1 = new CreateCustomView();
//			collection.visualizer.common.Algorithms.shellSort(vector);
			viewCreator.createView(menuItems, visualizer, visualizer
					.getFirstLayoutManager().getPseudoCode(), true, false, false);
			process(ag);
			


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void process(Graph<Number, Number> g) {
		Integer v_prev = null;

		for (int i = 0; i < 4; i++) {

			try {

				// if (g.getVertexCount() < 100) {
				// layout.lock(true);
				// add a vertex
				Integer v1 = new Integer(g.getVertexCount());

				// Relaxer relaxer = vv.getModel().getRelaxer();
				// relaxer.pause();
				g.addVertex(v1);
				System.err.println("added node " + v1);

				// wire it to some edges
				if (v_prev != null) {
					g.addEdge(g.getEdgeCount(), v_prev, v1);
					// let's connect to a random vertex, too!
					int rand = (int) (Math.random() * g.getVertexCount());
					g.addEdge(g.getEdgeCount(), v1, rand);
				}

				v_prev = v1;

				// layout.initialize();
				// relaxer.resume();
				// layout.lock(false);
				// } else {
				// done = true;
				// }

			} catch (Exception e) {
//				System.out.println(e);
				e.printStackTrace();

			}
		}
	}

//	public static void visualize(ListenableVector<Integer> vector)
//			throws Exception {
//
//		AJungGraphVisualizer visualizer = new AJungGraphVisualizer();
//		visualizer.visualize(vector);
//
//		visualizer
//				.addTrapper(new AJungGraphEventTrapper(visualizer,
//						(AJungGraphLayoutManager) visualizer
//								.getLayoutManager()));
//
//		Object[] menuItems = {};
//
//		CreateCustomView viewCreator1 = new CreateCustomView();
//		viewCreator1.createView(menuItems, visualizer, visualizer
//				.getLayoutManager().getPseudoCode(), true, false);
//	}

}
