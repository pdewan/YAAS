package yaas.examples;

import util.javac.ParserMain;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.VisualizationBasedVisualizer;
import yaas.OE.Algorithms;
import yaas.collection.ATextualCollectionVectorMethodsAnimator;
import yaas.common.AListenableVector;
import yaas.common.VestigalListenableVector;
import yaas.visualizers.collection.flat.AFlatCollectionVisualizer;
import yaas.visualizers.collection.flat.AGraphicFlatCollectionVectorMethodsAnimator;

public class TwoVectorsFrontCopy extends ModularSorting {
	static VestigalListenableVector<Integer> vector2 = new AListenableVector(); 
//	static AnIntegerBarChartVisualizer visualizer;

//	static int[] intArray = {1, 2, 3, 4, 5};
	
	public static void fillElements2() {
//		vector.move(0, vector2, 0);
		vector2.add(4);
		vector2.add(5);
		vector2.add(3);
		vector2.add(7);
		vector2.add(6);
		vector2.add(9);
		vector2.add(10);
		vector2.add(8);
		vector2.add(2);
		vector2.add(1);
	}
	
	public static void copyItems() {
		int numItems = vector.size();
		for (int i = numItems - 1; i >= 0; i--) {
			vector.copy(i, vector2, numItems - i - 1);
//			vector2.add(vector.get(i));
		}
	}
//	public static void addFlatTrapper(ListenableVector aRoot) {
//		try {
////		visualizer = new AnIntegerBarChartAnimator();
////		visualizer.animate(vector);
////		visualizer.addTrapper(new AnIntegerBarChartEventTrapper(visualizer,
////				(AnIntegerBarChartLayoutManager) visualizer
////						.getLayoutManager()));
////			VectorMethodsListener<Object> textualReplayListener = new ATextualCollectionVectorMethodsAnimator(( visualizer.getVisualization()).getStatusShape());
//			if (graphicalReplayListener == null)
//			graphicalReplayListener = new AGraphicFlatCollectionVectorMethodsAnimator((AFlatCollectionVisualizer)visualizer);
//
////			visualizer.addReplayMethodListenerOfObjectTree(vector, textualReplayListener);
//			visualizer.addReplayMethodListenerOfObjectTree(aRoot, graphicalReplayListener);
//			
////			visualizer.addReplayMethodListenerOfObjectTree(vector2, textualReplayListener);
//
////		visualizer.addReplayMethodListenerOfObjectTree(vector, new ATextualCollectionVectorMethodsAnimator(( visualizer.getVisualization()).getStatusShape()));
//
////		visualizer.addReplayMethodListenerOfObjectTree(vector, new AGraphicFlatCollectionVectorMethodsAnimator((AFlatCollectionVisualizer)visualizer));
//
//	//		visualizer.animate(vector);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
	
	public static void copyItems2() {
		int numItems = vector.size();
		for (int i = 0; i < numItems; i++) {
			vector.copyAndInsert(i, vector2, 0);
//			vector2.add(vector.get(i));
		}
	}
	public static void createRootObjects() {
		vector.setName("list");
		vector2.setName("list2");

	}
	
	public static void main(String[] args) {
		ParserMain.parse(Algorithms.class, ".");
		
	
		fillFlatElements();
//		fillElements2();
		createRootObjects();
		createAnimator();
		setCompositeLayout();
//		configureFlatLayout();
		addTextTrapper(vector);
		addTextTrapper(vector2);
		addFlatTrapper(vector);
		addFlatTrapper(vector2);



		visualize(vector, configureFlatLayout());
		vector2.setName("list2");
		visualize(vector2, configureFlatLayout());

//		doSort(vector);
//		doSort(vector2);
//		moveItems();
		copyItems2();

		setCodeParameters();
		displayAnimator();
	}

}
