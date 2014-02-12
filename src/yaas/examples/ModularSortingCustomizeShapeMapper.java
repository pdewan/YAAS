package yaas.examples;

import shapes.BoundedShape;
import yaas.collection.CollectionLayoutManager;
import yaas.common.VestigalListenableVector;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.shapemappers.IntToLine;
import yaas.visualizers.collection.flat.AFlatCollectionLayoutManager;
import yaas.visualizers.collection.flat.AFlatCollectionVisualizer;
import yaas.visualizers.collection.flat.AFlatIntBarchartCollectionLayoutManager;

public class ModularSortingCustomizeShapeMapper extends ModularSorting {
	
	public static CollectionLayoutManager configureFlatLayout() {
//		try {
			CollectionLayoutManager flatLayoutManager = new AFlatIntBarchartCollectionLayoutManager((AFlatCollectionVisualizer) visualizer);
			 flatLayoutManager.setElementToShapeTranslator(new IntToLine(visualizer.getObjectColorManager()));
			return flatLayoutManager;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) {
		fillFlatElements();
		createRootObjects();
		createAnimator();
		setCompositeLayout();
//		configureLayout();
		addFlatTrapper(vector);
		visualize(vector, configureFlatLayout());
		doSort(vector);
		displayAnimator();
	}

	

}
