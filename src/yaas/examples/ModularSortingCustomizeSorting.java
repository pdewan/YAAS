package yaas.examples;

import util.javac.ParserMain;
import util.models.ListenableVector;
import yaas.OE.Algorithms;
import yaas.OE.CreateCustomView;
import yaas.common.AListenableVector;
import yaas.common.VestigalListenableVector;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.shapemappers.IntToVerticalBar;
import yaas.visualizers.collection.flat.AnIntegerBarChartEventTrapper;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnIntegerBarChartVisualizer;

public class ModularSortingCustomizeSorting extends ModularSorting{
	
//	public static void doSort() {
////		yaas.common.Algorithms.shellSort(vector);
////		yaas.common.Algorithms.shellSortUserObject(vector);
//		yaas.common.Algorithms.bubbleSort(vector);
//
//
//
//	}
	public static void doSort (ListenableVector list) {
//		yaas.common.Algorithms.shellSort(vector);
		yaas.common.Algorithms.bubbleSort(list);
//		yaas.common.Algorithms.bubbleSort(vector);



	}
	
	public static void main(String[] args) {
		ParserMain.parse(Algorithms.class);
		
		createRootObjects();
		fillFlatElements();
		createAnimator();
		setCompositeLayout();
//		configureLayout();
		addTextTrapper(vector);
		addFlatTrapper(vector);

		visualize(vector, configureFlatLayout());
//		addTrapper();

		doSort(vector);
		setCodeParameters();
		displayAnimator();
	}
	

//	public static void main(String[] args) {
//	
//		fillFlatElements();
//		createRootObjects();
//		createAnimator();
//		setCompositeLayout();
////		configureFlatLayout();
//		visualize(vector, configureFlatLayout());
//		addFlatTrapper(vector);
//		doSort();
//		displayAnimator();
//	}



}
