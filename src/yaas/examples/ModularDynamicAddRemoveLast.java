package yaas.examples;

import yaas.common.VestigalListenableVector;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.shapemappers.IntToLine;
import yaas.visualizers.collection.flat.AnIntegerBarChartEventTrapper;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnotherIntegerBarChartEventTrapper;

public class ModularDynamicAddRemoveLast extends ModularSorting {
	
	public static void deleteItems() {
		int numItems = vector.size();
		for (int i = numItems - 1; i >= 0; i--) {
			vector.remove(i);
//			vector.remove(0);

//			vector2.add(vector.get(i));
		}
	}
	
	
	public static void main(String[] args) {
		createRootObjects();
		createAnimator();
		setCompositeLayout();
//		configureFlatLayout();
		addFlatTrapper(vector);
		visualize(vector, configureFlatLayout());
		displayAnimator();
		fillFlatElements();
//		doSort(vector);
		deleteItems();

	}

	

}
