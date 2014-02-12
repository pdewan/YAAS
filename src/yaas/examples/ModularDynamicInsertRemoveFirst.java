package yaas.examples;

import yaas.common.VestigalListenableVector;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.shapemappers.IntToLine;
import yaas.visualizers.collection.flat.AnIntegerBarChartEventTrapper;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnotherIntegerBarChartEventTrapper;

public class ModularDynamicInsertRemoveFirst extends ModularSorting {
	
	public static void deleteItems() {
		int numItems = vector.size();
		for (int i = numItems - 1; i >= 0; i--) {
//			vector.remove(i);
			vector.remove(0);

//			vector2.add(vector.get(i));
		}
	}
	public static void fillElements() {
		vector.add(0, 4);
		vector.add(0, 5);
		vector.add(0, 3);
		vector.add(0, 7);
		vector.add(0, 6);
		vector.add(0, 9);
		vector.add(0, 10);
		vector.add(0, 8);
		vector.add(0, 2);
		vector.add(0, 1);
		vector.move(0,  5);
		vector.move(4, 0);
		vector.move(4, vector.size() - 1 );
	}
	
	
	public static void main(String[] args) {
		createRootObjects();
		createAnimator();
		setCompositeLayout();
//		configureFlatLayout();
		addFlatTrapper(vector);
		visualize(vector, configureFlatLayout());
		displayAnimator();
		fillElements();
//		doSort(vector);
		deleteItems();

	}

	

}
