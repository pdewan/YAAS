package yaas.examples;

import util.javac.ParserMain;
import yaas.VisualizationBasedVisualizer;
import yaas.OE.Algorithms;
import yaas.common.AListenableVector;
import yaas.common.VestigalListenableVector;
import yaas.visualizers.collection.flat.AFlatCollectionVisualizer;

public class AscendDescendSortMoveTwoLists extends ModularSorting {
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
	
	public static void moveItems() {
		int numItems = vector.size();
		for (int i = numItems - 1; i >= 0; i--) {
			vector.move(i, vector2, numItems - i - 1);
//			vector2.add(vector.get(i));
		}
	}
	
	public static void moveItems2() {
		int numItems = vector.size();
		for (int i = 0; i < numItems; i++) {
			vector.move(0, vector2, 0);
//			vector2.add(vector.get(i));
		}
	}
	
	public static void main(String[] args) {
		ParserMain.parse(Algorithms.class);
		
	
		fillFlatElements();
//		fillElements2();
		createAnimator();
		configureFlatLayout();
		setCompositeLayout(); // this must come earlier

		addFlatTrapper(vector);


		visualize(vector, configureFlatLayout());

		vector2.setName("list2");
//		visualize(vector2);
		visualize(vector2, configureFlatLayout());


		doSort(vector);
//		doSort(vector2);
		moveItems();
//		moveItems2();

		setCodeParameters();
		displayAnimator();
	}

}
