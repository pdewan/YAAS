package yaas.examples;

import yaas.common.VestigalListenableVector;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.shapemappers.IntToLine;
import yaas.visualizers.collection.flat.AnIntegerBarChartEventTrapper;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnotherIntegerBarChartEventTrapper;

public class ModularDynamicSort extends ModularSorting {
	
	
	
	
	public static void main(String[] args) {
		createRootObjects();
		createAnimator();
		setCompositeLayout();

//		configureFlatLayout();
		addTextTrapper(vector);

		addFlatTrapper(vector);
		visualize(vector, configureFlatLayout());
		displayAnimator();
		fillFlatElements();
		doSort(vector);
//		deleteItems();

	}

	

}
