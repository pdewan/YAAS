package yaas.examples;

import yaas.OE.Algorithms;
import yaas.OE.CreateCustomView;
import yaas.common.AListenableVector;
import yaas.common.VestigalListenableVector;
import yaas.visualizers.collection.flat.AnIntegerBarChartEventTrapper;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnIntegerBarChartVisualizer;
import yaas.visualizers.observer.ObservableEventGenerator;
import yaas.visualizers.yaas.ARecursiveVisualizationAnimator;
import yaas.visualizers.yaas.ARecursiveVisualizationBuffer;
import yaas.visualizers.yaas.ARecursiveVisualizationLayoutManager;
import yaas.visualizers.yaas.ARecursiveVisualizationTrapper;

public class RecursiveVisualization {

	public static void main(String[] args) {
		VestigalListenableVector<Integer> vector = new AListenableVector<Integer>();

		vector.add(10);
		vector.add(9);
		vector.add(8);
		vector.add(7);
		vector.add(6);
		vector.add(5);
		vector.add(4);
		vector.add(3);
		vector.add(2);
		vector.add(1);

		try {
			AnIntegerBarChartVisualizer v1 = new AnIntegerBarChartVisualizer();
			AnIntegerBarChartLayoutManager layoutManager = new AnIntegerBarChartLayoutManager(v1);

			v1.visualize(vector, null);
//			v1.addTrapper(new AnIntegerBarChartEventTrapper(v1,
//					(AnIntegerBarChartLayoutManager) v1.getLayoutManager()));
			 v1.addReplayMethodListenerOfObjectTree(vector, new AnIntegerBarChartEventTrapper(v1,
					layoutManager));


			Object[] menuItems = {
					new yaas.OE.Sorting(vector),
					new Algorithms(vector) };

			ObservableEventGenerator controller = v1.getController();
			ARecursiveVisualizationLayoutManager beanLayoutManager = new ARecursiveVisualizationLayoutManager(v1);

			ARecursiveVisualizationAnimator beanVisualizer = new ARecursiveVisualizationAnimator(
					beanLayoutManager, controller);

			ARecursiveVisualizationTrapper trapper = new ARecursiveVisualizationTrapper(
					beanVisualizer,
					((ARecursiveVisualizationBuffer) beanVisualizer.getRootBuffer())
							.getCopy(), beanLayoutManager);
			beanVisualizer.addTrapper(trapper);
			
			yaas.common.Algorithms.shellSort(vector);

			CreateCustomView viewCreator1 = new CreateCustomView();
			CreateCustomView viewCreator2 = new CreateCustomView();
			
			viewCreator1.createView(menuItems, v1, layoutManager.getPseudoCode(), true, false, true);
			
			viewCreator2.setDataPanel(beanLayoutManager.getPanel());
			viewCreator2.createNestedView(menuItems, beanVisualizer, beanLayoutManager, true, true);
			
//			JFrame f = new JFrame("The Frame");
//			f.setLocation(100, 100);
//			Container content = v1.getLayoutManager().displayInPanel();
//			f.setContentPane(content);
//			f.setSize(600, 600);
//			f.setVisible(true);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
