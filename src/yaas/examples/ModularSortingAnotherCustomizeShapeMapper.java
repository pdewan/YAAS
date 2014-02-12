package yaas.examples;

import shapes.BoundedShape;
import yaas.collection.CollectionLayoutManager;
import yaas.common.VestigalListenableVector;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.shapemappers.IntToLine;
import yaas.shapemappers.AnObjectToStringInARectangle;
import yaas.shapemappers.AnObjectToStringInAnOval;
import yaas.visualizers.collection.CollectionVisualizer;
import yaas.visualizers.collection.flat.AFlatCollectionLayoutManager;
import yaas.visualizers.collection.flat.AFlatCollectionVisualizer;
import yaas.visualizers.collection.flat.AFlatObjectCollectionLayoutManager;
import yaas.visualizers.collection.flat.FlatCollectionLayoutManager;

public class ModularSortingAnotherCustomizeShapeMapper extends ModularSorting {
	
	public static CollectionLayoutManager configureFlatLayout() {
//		visualizer.setLayoutManager(new AFlatObjectCollectionLayoutManager(visualizer));
		CollectionLayoutManager flatLayoutManager= new AFlatCollectionLayoutManager((CollectionVisualizer) visualizer);
		flatLayoutManager.setElementToShapeTranslator(new AnObjectToStringInAnOval(visualizer.getObjectColorManager()));
		return flatLayoutManager;
//		visualizer.setLayoutManager(flatCollectionLayoutManager);

//		try {
//			CompositeLayoutManager<ListenableVector<Integer>, Integer> layoutManager = (CompositeLayoutManager) visualizer.getLayoutManager();
//			layoutManager.setElementToShapeTranslator(new IntToLine());
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
		addTextTrapper(vector);

		addFlatTrapper(vector);

		visualize(vector, configureFlatLayout());
		doSort(vector);
		displayAnimator();
	}

	

}
