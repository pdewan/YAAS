package yaas.examples;

import util.javac.ParserMain;
import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.OE.Algorithms;
import yaas.OE.CreateCustomView;
import yaas.OE.Sorting;
import yaas.collection.ATextualCollectionVectorMethodsAnimator;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.shapemappers.IntToVerticalBar;
import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.shapemappers.AnObjectToStringModel;
import yaas.shapemappers.AnObjectToTextModel;
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
import bus.uigen.visitors.AddListenersAdapterVisitor;

public class DynamicBinaryTreeNodeInsertion extends DynamicSmallTreeExample{
//	 static ListenableVector root; 
	 static int nodeNumber = 0;
//	 = new AListenableVector();
	
//	static TreeVisualizer visualizer;
	public static void fillTreeElements() {
		treeAdd(4);
		treeAdd(5);
		treeAdd(3);
		treeAdd(7);
		treeAdd(6);
		treeAdd(9);
		treeAdd(10);
		treeAdd(8);
		treeAdd(2);
		treeAdd(1);
		
		
		
		
		
	}
	
	
	public static void preOrderVisit(ListenableVector aNode) {
		
		ListenableVector aLeftNode = getLeftNode(aNode);
		if (aLeftNode != null) {
			preOrderVisit(aLeftNode);
		}
		System.out.println(getValue(aNode));
		ListenableVector aRightNode = getRightNode(aNode);
		if (aRightNode != null) {
			preOrderVisit(aRightNode);

		}
		
	}
	
	static Integer getValue (ListenableVector aNode) {
		return (Integer) (((ListenableVector) aNode).observableGetUserObject());
	}
	
	public static void treeAdd (int value) {
//		ListenableVector aNewNode = createNode(value);
		root.setTemp(value);
		add (root, value);

	}
	
	public static void add (ListenableVector aNode, ListenableVector aNewNode) {
		 
		Integer aNodeValue = getValue(aNode);
		int value = getValue(aNewNode);
		if (aNodeValue == null) {
			aNode.setUserObject(value);
			return;
		}
//		ListenableVector aNewNode = createNode(value);
		if (value < aNodeValue) {
			ListenableVector aLeftNode = getLeftNode(aNode);
			if (aLeftNode == null) {
				setLeftNode(aNode, aNewNode);
			} else {
				add(aLeftNode, aNewNode);
			}
		} else{
			ListenableVector aRightNode = getRightNode(aNode);
			if (aRightNode == null) {
				setRightNode(aNode, aNewNode);
			} else {
				add(aRightNode, aNewNode);
			}
		}
		
		
	}
	public static void add (ListenableVector aNode, Integer value) {
		 
		Integer aNodeValue = getValue(aNode);
//		ListenableVector aNewNode;
//		int value = getValue(aNewNode);
		if (aNodeValue == null) {
//			aNode.setUserObject(value);
			aNode.copyTempToUserObject(root);

			return;
		}
//		ListenableVector aNewNode = createNode(value);
		if (value < aNodeValue) {
			ListenableVector aLeftNode = getLeftNode(aNode);

			if (aLeftNode == null) {
				aLeftNode = createNode(null);
				setLeftNode(aNode, aLeftNode);
//				aLeftNode.setUserObject(value);
				aLeftNode.copyTempToUserObject(root);
			} else {
				add(aLeftNode, value);
			}
		} else{
			ListenableVector aRightNode = getRightNode(aNode);
			if (aRightNode == null) {
				aRightNode = createNode(null);
				setRightNode(aNode, aRightNode);
				
				aRightNode.copyTempToUserObject(root);
			} else {
				add(aRightNode, value);
			}
		}
		
		
	}
	
	public static void maybeCreateChildren (ListenableVector aParent) {
		if (aParent.size() < 2) {
			aParent.add(null);
			aParent.add(null);
		}
	}
	
	public static void maybeCreateLeftNode (ListenableVector aParent) {
		if (aParent.size() == 0) {
			aParent.add(null);
//			aParent.add(null);
		}
	}
	
	public static void maybeCreateRightNode (ListenableVector aParent) {
		maybeCreateLeftNode(aParent);
		if (aParent.size() < 2) {
			aParent.add(null);
//			aParent.add(null);
		}
	}
	
	
	public static ListenableVector getLeftNode (ListenableVector aParent) {
//		maybeCreateChildren(aParent);
		if (aParent.size() == 0)
			return null;
		return (ListenableVector) aParent.observableGet(0);
//		return (ListenableVector) aParent.get(0);

	}
	
	public static ListenableVector getRightNode (ListenableVector aParent) {
		if (aParent.size() < 2)
			return null;
//		maybeCreateChildren(aParent);

//		if (aParent.size() == 0) {
//			aParent.add(null);
//			aParent.add(null);
//		}
		return (ListenableVector) aParent.observableGet(1);
//		return (ListenableVector) aParent.get(1);

	}
	
	
	public static void setLeftNode (ListenableVector aParent, ListenableVector aChild) {
		maybeCreateLeftNode(aParent);
		aParent.set(0, aChild);
	}
	
	public static void setRightNode (ListenableVector aParent, ListenableVector aChild) {
		maybeCreateRightNode(aParent);
		aParent.set(1, aChild);
	}
	
	public static ListenableVector createNode (Integer value) {
		ListenableVector node = new AListenableVector();
		node.setName("node" + nodeNumber);
		nodeNumber++;
		node.setUserObject(value);
		maybeCreateChildren(node);
//		maybeCreateChildren(aParent);
		return node;
	}
//	public static void createAnimator() {
////		root.setName("root");
////		nested.setName("nested");
////		nested2.setName("nested2");
//
//
//		try {
////			visualizer = new AnIntegerBarChartVisualizer();
//			visualizer = new ATreeVisualizer();
//			((TreeVisualizer) visualizer).setDisplayName(false);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public static TreeLayoutManager configureTreeLayout() {
//		try {
//			layoutManager = (TreeLayoutManager) visualizer.getLayoutManager();
			TreeLayoutManager treeLayoutManager =  new ATreeLayoutManager (visualizer);


			AnObjectToTextModel  objectToShapeTranslator = new AnObjectToTextModel(visualizer.getObjectColorManager());
			objectToShapeTranslator.setDisplayName(false);
//
//			ObjectToShapeTranslator<ListenableVector>  vectorToShapeTranslator =new AnObjectToTextModel(visualizer.getObjectColorManager());
			((TreeLayoutManager) treeLayoutManager).setLeafNodeToContentShapeTranslator (objectToShapeTranslator);
			((TreeLayoutManager) treeLayoutManager).setInternalNodeToContentShapeTranslator (objectToShapeTranslator);
			((TreeLayoutManager) treeLayoutManager).setRootNodeToContentShapeTranslator (objectToShapeTranslator);
			return treeLayoutManager;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
//	public static void addTreeTrapper() {
//		try {
//			visualizer.addReplayMethodListenerOfObjectTree(root, new ATextualCollectionVectorMethodsAnimator(visualizer.getVisualization().getStatusShape()));
//
//			visualizer.addReplayMethodListenerOfObjectTree(root, new ATreeVectorMethodsAnimator((TreeVisualizer) visualizer));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
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
	public static void createRootObjects() {
		root = createNode(null);

		}
	
	public static void doSort(ListenableVector aVector) {
		
	}
	public static void main(String[] args) {
//		ParserMain.parse(Algorithms.class);
		
//		root.setUserObject("foooo");
		createRootObjects();
		createAnimator();
		setCompositeLayout();
//		configureTreeLayout();

//		configureTreeLayout();
		addTextTrapper(root);
		addTreeTrapper(root);

		visualize(root, configureTreeLayout());

//		doSort(root);
		setCodeParameters();
		displayAnimator();
		fillTreeElements();
		preOrderVisit(root);

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
