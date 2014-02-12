package yaas.OE;


import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.controller.AController;
import yaas.visualizers.collection.flat.AnIntegerBarChartEventTrapper;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnIntegerBarChartVisualizer;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;

public class Test {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		ListenableVector<Integer> vector = new AListenableVector<Integer>();

		vector.add(5);
		vector.add(4);
		vector.add(3);
		vector.add(2);
		vector.add(1);
		
		try {
			AnIntegerBarChartVisualizer visualizer = new AnIntegerBarChartVisualizer();
			visualizer.visualize(vector, null);
			yaas.common.Algorithms.bubbleSort(vector);

//			visualizer.addTrapper(new AnIntegerBarChartEventTrapper(visualizer,
//					(AnIntegerBarChartLayoutManager) visualizer
//							.getLayoutManager()));
			
//			visualizer.addReplayMethodListener(null, new AnIntegerBarChartEventTrapper(visualizer,
//					(AnIntegerBarChartLayoutManager) visualizer
//							.getLayoutManager(null)));

			Object[] menuItems = {
					new yaas.OE.Sorting(vector),
					new Algorithms(vector) };
			
			CreateCustomView viewCreator1 = new CreateCustomView();
			viewCreator1.createView(menuItems, visualizer,
					null, true, false, true);
			
			
			AController<Integer> controller = (AController) visualizer.getController();
			
			ObjectEditor.setPropertyComponentWidth(AListenableVector.class,
					AttributeNames.ANY_ELEMENT, 300);
			OEFrame controllerFrame = ObjectEditor.edit(controller);
			controller.setFrame(controllerFrame);
			controllerFrame.setDemoFont();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
