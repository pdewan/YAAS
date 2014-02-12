package yaas.examples;

import shapes.BoundedShape;
import util.javac.ParserMain;
import util.models.AListenableVector;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.Visualizer;
import yaas.OE.Algorithms;
import yaas.OE.CreateCustomView;
import yaas.collection.ATextualCollectionVectorMethodsAnimator;
import yaas.collection.CollectionLayoutManager;
import yaas.layout.ACompositeRowColumnLayoutManager;
import yaas.layout.CompositeLayoutManager;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.layout.LayoutManager;
import yaas.shapemappers.IntToVerticalBar;
import yaas.visualizers.collection.ACollectionVisualizer;
import yaas.visualizers.collection.CollectionVisualizer;
import yaas.visualizers.collection.flat.AFlatCollectionVisualizer;
import yaas.visualizers.collection.flat.AFlatIntBarchartCollectionLayoutManager;
import yaas.visualizers.collection.flat.AFlatIntegerCollectionVisualizer;
import yaas.visualizers.collection.flat.AGraphicFlatCollectionVectorMethodsAnimator;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnIntegerBarChartVisualizer;

public class ModularSorting extends SharedDriver {
	static ListenableVector<Integer> vector = new AListenableVector(); 
//	static AnIntegerBarChartVisualizer visualizer;
//	static CollectionVisualizer visualizer;

//	static int[] intArray = {1, 2, 3, 4, 5};
	
	public static void createRootObjects() {
		vector.setName("list");

	}

	
	public static void fillFlatElements() {
		flatAdd(4);
		flatAdd(5);
		flatAdd(3);
		flatAdd(7);
		flatAdd(6);
		flatAdd(9);
		flatAdd(10);
		flatAdd(8);
		flatAdd(2);
		flatAdd(1);
	}
	public static void flatAdd(Integer aValue) {
		vector.add(aValue);
	}
	public static void clearFlatElements() {
		vector.clear();
	}
//	static CompositeLayoutManager compositeLayoutManager;
//	public static void setCompositeLayout() {
//			compositeLayoutManager = new ACompositeRowColumnLayoutManager(visualizer);
//			visualizer.setCompositeLayoutManager(compositeLayoutManager);
//			
//		
//	}
	
	public static void createAnimator() {

		try {
//			visualizer = new AnIntegerBarChartVisualizer();
			visualizer = new ACollectionVisualizer();
//			visualizer.setCompositeLayoutManager(new)

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
////	static CollectionLayoutManager flatLayoutManager;
	public static CollectionLayoutManager  configureFlatLayout() {
//		try {
		CollectionLayoutManager flatLayoutManager = new AFlatIntBarchartCollectionLayoutManager((CollectionVisualizer) visualizer);
//			CompositeLayoutManager<ListenableVector<Integer>, Integer> layoutManager = (CompositeLayoutManager) visualizer.getLayoutManager();
			flatLayoutManager.setElementToShapeTranslator(new IntToVerticalBar(visualizer.getObjectColorManager()));
			return flatLayoutManager;
//			visualizer.setLayoutManager(vector, layoutManager);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
//	
//	public static void configureCodeLayout() {
//		try {
//			CodelayoutManager codeLayoutManager = new ACode
//			CompositeLayoutManager<ListenableVector<Integer>, Integer> layoutManager = (CompositeLayoutManager) visualizer.getLayoutManager();
//			layoutManager.setElementToShapeTranslator(new IntToBar());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	static VectorMethodsListener<Object> textualReplayListener;
//	public static void addTextTrapper(ListenableVector aRoot) {
//		if (textualReplayListener == null)
//		textualReplayListener = new ATextualCollectionVectorMethodsAnimator(( visualizer.getVisualization()).getStatusShape());
//		visualizer.addReplayMethodListenerOfObjectTree(aRoot, textualReplayListener);
//
//	}
//	static VectorMethodsListener<Object> graphicalReplayListener;
//	public static void addFlatTrapper(ListenableVector aRoot) {
//		try {
//			if (graphicalReplayListener == null)
//			 graphicalReplayListener = new AGraphicFlatCollectionVectorMethodsAnimator((CollectionVisualizer)visualizer);
//
////			visualizer.addReplayMethodListenerOfObjectTree(vector, textualReplayListener);
//			visualizer.addReplayMethodListenerOfObjectTree(aRoot, graphicalReplayListener);
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
//	
//	public static void setCodeParameters() {
//		visualizer.setShowCode(false);
//	}
	public static void doSort (ListenableVector list) {
//		yaas.common.Algorithms.shellSort(vector);
		yaas.common.Algorithms.shellSortTemp(list);
//		yaas.common.Algorithms.bubbleSort(vector);



	}
	public static void displayAnimator() {
		CreateCustomView viewCreator = new CreateCustomView();
		viewCreator.setDataPanel(visualizer.getLayoutManagerOfRootObject(vector)
				.displayInPanel());

		Object[] menuItems = {
				new yaas.OE.Sorting(vector),
				new Algorithms(vector) };

		CreateCustomView viewCreator1 = new CreateCustomView();
//		viewCreator.setDataPanel(visualizer.getDataPanel());
//		yaas.common.Algorithms.shellSortUserObject(vector);
//		yaas.common.Algorithms.shellSort(vector);

		viewCreator1.createView(menuItems, visualizer, visualizer
				.getMethodsCodeVisualization(), true, visualizer.isShowCode(), true);
	}
	
//	public static void visualize(ListenableVector aVector, LayoutManager aLayoutManager) {
//		try {
//			visualizer.visualize(aVector, aLayoutManager);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	

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

//	public static void visualize(ListenableVector<Integer> vector)
//			throws Exception {
//
//		AnIntegerBarChartAnimator visualizer = new AnIntegerBarChartAnimator();
//		visualizer.animate(vector);
//
//		visualizer
//				.addTrapper(new AnIntegerBarChartEventTrapper(visualizer,
//						(AnIntegerBarChartLayoutManager) visualizer
//								.getLayoutManager()));
//
//		Object[] menuItems = {};
//
//		CreateCustomView viewCreator1 = new CreateCustomView();
//		viewCreator1.createView(menuItems, visualizer, visualizer
//				.getLayoutManager().getPseudoCode(), true, false, true);
//	}

}
