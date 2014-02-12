package yaas.examples;

import yaas.layout.CompositeShapeLayoutManager;
import yaas.shapemappers.IntToLine;
import yaas.visualizers.collection.CollectionVisualizer;
import yaas.visualizers.collection.flat.AFlatCollectionVisualizer;
import yaas.visualizers.collection.flat.AGraphicFlatCollectionVectorMethodsAnimator;
import yaas.visualizers.collection.flat.AnIntegerBarChartEventTrapper;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnotherGraphicFlatCollectionVectorMethodsAnimator;
import yaas.visualizers.collection.flat.AnotherIntegerBarChartEventTrapper;

public class ModularSortingCustomizeAnimation extends ModularSorting {
	
	
	public static void addTrapper() {
		try {
//		visualizer = new AnIntegerBarChartAnimator();
//		visualizer.animate(vector);
//		visualizer.addTrapper(new AnotherIntegerBarChartEventTrapper(visualizer,
//				(AnIntegerBarChartLayoutManager) visualizer
//						.getLayoutManager()));
//		visualizer.addReplayMethodListener(new AnotherIntegerBarChartEventTrapper(visualizer,
//				(AnIntegerBarChartLayoutManager) visualizer
//						.getLayoutManager()));
		visualizer.addReplayMethodListenerOfObjectTree(vector, new AnotherGraphicFlatCollectionVectorMethodsAnimator((CollectionVisualizer) visualizer));

		
//		visualizer.animate(vector);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		fillFlatElements();
		createRootObjects();
		createAnimator();
		setCompositeLayout();
//		configureFlatLayout();
		addTrapper();
		visualize(vector, configureFlatLayout());
		doSort(vector);
		displayAnimator();
	}

	

}
