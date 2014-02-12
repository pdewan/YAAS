package yaas.examples;

import util.javac.ParserMain;
import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.OE.Algorithms;
import yaas.OE.CreateCustomView;
import yaas.OE.Sorting;
import yaas.collection.ATextualCollectionVectorMethodsAnimator;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.layout.LayoutManager;
import yaas.shapemappers.IntToVerticalBar;
import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.shapemappers.AnObjectToStringModel;
import yaas.shapemappers.AnObjectToTextModel;
import yaas.visualizers.collection.CollectionVisualizer;
import yaas.visualizers.collection.flat.AFlatIntegerCollectionVisualizer;
import yaas.visualizers.collection.flat.AGraphicFlatCollectionVectorMethodsAnimator;
import yaas.visualizers.collection.tree.ATreeLayoutManager;
import yaas.visualizers.collection.tree.AnObjectTreeVisualizer;
import yaas.visualizers.collection.tree.ATreeVectorMethodsAnimator;
import yaas.visualizers.collection.tree.ATreeVisualizer;
import yaas.visualizers.collection.tree.TreeLayoutManager;
import yaas.visualizers.collection.tree.TreeVisualizer;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.shapes.ATextModel;

public class DynamicSmallTreeExample extends ModularSorting{
	 static ListenableVector root = new AListenableVector();
	static ListenableVector nested = new AListenableVector();
	static ListenableVector nested2 = new AListenableVector();
//	static TreeLayoutManager treeLayoutManager;

//	static TreeVisualizer visualizer;
	public static void fillTreeElements() {
		root.setTemp("user object");
		root.setTemp("temp");
		nested2.add("goodye");
		nested2.add("world");
//		
		root.add(0, 6);
		root.observableGet(0);
		root.set(0, 10);
		root.set(0, nested2);
		root.add(0, "four");
		root.observableGetTemp();
		root.copyFromTemp(1);

		
		root.add(nested);
		root.observableGet(root.size() - 1);
		nested.add("Hello");
		root.copyToTemp(2);
		nested.add("World");
//		vector.copyToUserObject(2);
		
		
//		vector.add(5);
//		vector.add(4); 
//		vector.add(3);
//		vector.add(2);
		root.swap(0, nested, 0);
		root.swap(0, 2);		
		root.add(2, 1);
//		root.copy(1, nested, 0);
		root.copy(1, nested, 0);

		root.copy(1, 3);
		root.remove(root.size() -1);

		root.remove(nested);
		root.set(0, 5);
		root.swap(0, 1);
//		root.move(0, 1);
		root.move(1, 0);
		root.add(nested);
		root.add(nested2);
		root.move(3, 1);
		root.move(1, 2);
	}
//	public static void treeVisualize(ListenableVector aVector, LayoutManager ) {
//		try {
//			visualizer.visualize(aVector, treeLayoutManager);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
public static void createRootObjects() {
	root.setName("root");
	nested.setName("nested");
	nested2.setName("nested2");
	}
	
//	public static void createAnimator() {
//		
//
//
//		try {
////			visualizer = new AnIntegerBarChartVisualizer();
//			visualizer = new ATreeVisualizer();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	static TreeLayoutManager layoutManager;
	public static TreeLayoutManager configureTreeLayout() {
//		try {
			TreeLayoutManager treeLayoutManager =  new ATreeLayoutManager (visualizer);

			ObjectToShapeTranslator<Object>  objectToShapeTranslator = new AnObjectToStringModel();

			ObjectToShapeTranslator<ListenableVector>  vectorToShapeTranslator =new AnObjectToTextModel(visualizer.getObjectColorManager());
			treeLayoutManager.setLeafNodeToContentShapeTranslator (objectToShapeTranslator);
		     treeLayoutManager.setInternalNodeToContentShapeTranslator (vectorToShapeTranslator);
		     treeLayoutManager.setRootNodeToContentShapeTranslator (vectorToShapeTranslator);
		     return treeLayoutManager;
			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public static void addTreeTrapper(ListenableVector aRoot) {
		try {
//			visualizer.addReplayMethodListenerOfObjectTree(root, new ATextualCollectionVectorMethodsAnimator(visualizer.getVisualization().getStatusShape()));

			visualizer.addReplayMethodListenerOfObjectTree(aRoot, new ATreeVectorMethodsAnimator(visualizer));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//	public static void visualize(ListenableVector<Integer> aVector) {
//		try {
//			visualizer.visualize(aVector);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void setCodeParameters() {
//		visualizer.setShowCode(false);
//	}
	public static void displayAnimator() {
//		Object[] menuItems = {new Sorting(root), new Algorithms(root)};
//		Object[] menuItems = {};
		
		ObjectEditor.setDefaultAttribute(AttributeNames.IS_SHAPE_SPECIFICATION_REQUIRED, true);
		
		CreateCustomView viewCreator1 = new CreateCustomView();
//		viewCreator1.createView(menuItems, visualizer,
//				visualizer.getLayoutManager().getPseudoCode(), true, false, true);
		viewCreator1.createView(null, visualizer);
	}
	
	public static void doSort(ListenableVector aVector) {
		
	}
	public static void main(String[] args) {
//		ParserMain.parse(Algorithms.class);
		
		createRootObjects();
		createAnimator();
		setCompositeLayout();
//		configureTreeLayout();
		addTextTrapper(root);

		addTreeTrapper(root);

		visualize(root, configureTreeLayout());

		doSort(root);
		setCodeParameters();
		displayAnimator();
		fillTreeElements();

	}
//	public static void main(String args[]) {
//		ListenableVector root = new AListenableVector();
//		root.setName("root");
//		ListenableVector nested = new AListenableVector();
//		nested.setName("nested");
//		ListenableVector nested2 = new AListenableVector();
//		nested2.setName("nested2");
//
//
////		vector.add(10);
////		vector.add(9);  
////		vector.add(8);
////		vector.add(7);
////		vector.add(6);
////		
////		vector.add(nested);
////		nested.add("Hello");
////		nested.add("World");
//////		
//////		vector.add(5);
//////		vector.add(4); 
//////		vector.add(3);
//////		vector.add(2);
////		vector.add(1);
//
////		ObjectEditor.setDefaultAttribute(AttributeNames.SEPARATE_THREAD, true);
////		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS,
////				false);
//
//		TreeVisualizer visualizer = new ATreeVisualizer(
//				new ATextModel("",0, 0, 100, 20));
//		TreeLayoutManager layoutManager = visualizer.getLayoutManager();
//		ObjectToShapeTranslator<Object>  objectToShapeTranslator = new ObjectToStringModel();
//
//		ObjectToShapeTranslator<ListenableVector>  vectorToShapeTranslator =new ObjectToTextModel(visualizer.getObjectColorManager());
//		layoutManager.setLeafNodeToContentShapeTranslator (objectToShapeTranslator);
//		layoutManager.setInternalNodeToContentShapeTranslator (vectorToShapeTranslator);
//		layoutManager.setRootNodeToContentShapeTranslator (vectorToShapeTranslator);
////		ALinearVisualizer visualizer = new ALinearVisualizer(
////				new AStringModel(""));
//
////		visualizer.showLines(true);
////		visualizer.showRoot(true);
////		visualizer.getLayoutManager().setVertical(false);
//		try {
////			visualizer.addTrapper(new ALinearEventTrapper<Integer>(visualizer));
//			visualizer.visualize(root);
//			visualizer.addReplayMethodListener(new ATextualCollectionVectorMethodsAnimator(visualizer.getVisualization().getStatusShape()));
//
//			visualizer.addReplayMethodListener(new ATreeVectorMethodsAnimator(visualizer));
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		Object[] menuItems = {new Sorting(root), new Algorithms(root)};
////		Object[] menuItems = {};
//		
//		ObjectEditor.setDefaultAttribute(AttributeNames.IS_SHAPE_SPECIFICATION_REQUIRED, true);
//		
//		CreateCustomView viewCreator1 = new CreateCustomView();
////		viewCreator1.createView(menuItems, visualizer,
////				visualizer.getLayoutManager().getPseudoCode(), true, false, true);
//		viewCreator1.createView(null, visualizer);
//		root.setUserObject("user object");
//		nested2.add("goodye");
//		nested2.add("world");
////		
//		root.add(0, 6);
//		root.observableGet(0);
//		root.set(0, nested2);
//		root.add(0, "four");
//		root.observableGetUserObject();
//		root.copyFromUserObject(1);
//
//		
//		root.add(nested);
//		root.observableGet(root.size() - 1);
//		nested.add("Hello");
//		root.copyToUserObject(2);
//		nested.add("World");
////		vector.copyToUserObject(2);
//		
//		
////		vector.add(5);
////		vector.add(4); 
////		vector.add(3);
////		vector.add(2);
//		root.swap(0, nested, 0);
//		root.swap(0, 2);		
//		root.add(2, 1);
////		root.copy(1, nested, 0);
//		root.copy(1, nested, 0);
//
//		root.copy(1, 3);
//		root.remove(root.size() -1);
//
//		root.remove(nested);
//		root.set(0, 5);
//		root.swap(0, 1);
////		root.move(0, 1);
//		root.move(1, 0);
//		root.add(nested);
//		root.add(nested2);
//		root.move(3, 1);
//		root.move(1, 2);
//	
//	}


}
