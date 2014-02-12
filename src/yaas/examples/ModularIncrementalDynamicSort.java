package yaas.examples;

import java.util.List;

import util.models.ListenableVector;
import yaas.common.VestigalListenableVector;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.shapemappers.IntToLine;
import yaas.visualizers.collection.flat.AnIntegerBarChartEventTrapper;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnotherIntegerBarChartEventTrapper;

public class ModularIncrementalDynamicSort extends ModularSorting {
	
	public static void incrementalSort() {
//		vector.add(4);
//		vector.add(5);
//		vector.add(3);
//		vector.add(7);
//		vector.add(6);
//		vector.add(9);
//		vector.add(10);
//		vector.add(8);
//		vector.add(2);
//		vector.add(1);
		
		sortingAdd(vector, 4);
		sortingAdd(vector, 5);
		sortingAdd(vector, 3);
		sortingAdd(vector, 7);
		sortingAdd(vector, 6);
		sortingAdd(vector, 9);
		sortingAdd(vector, 10);
		sortingAdd(vector, 8);
		sortingAdd(vector, 2);
		sortingAdd(vector, 1);
	}
	
	public static void sortingAdd(ListenableVector vector, int element) {
		vector.add(element);
		doSort(vector);
	}
	
	public static void main(String[] args) {
		createRootObjects();
		createAnimator();
		setCompositeLayout();

//		configureFlatLayout();
		addTextTrapper(vector);

		addFlatTrapper(vector);
		visualize(vector, configureFlatLayout());
		displayAnimator();
		 incrementalSort();
//		doSort(vector);
//		deleteItems();

	}

	

}
