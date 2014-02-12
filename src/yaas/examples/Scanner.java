package yaas.examples;

import shapes.BoundedShape;
import util.javac.ParserMain;
import util.models.AListenableString;
import util.models.AListenableVector;
import util.models.ListenableString;
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
import yaas.shapemappers.AnObjectToStringInARectangle;
import yaas.shapemappers.AnObjectToStringInAShape;
import yaas.shapemappers.IntToVerticalBar;
import yaas.visualizers.collection.CollectionVisualizer;
import yaas.visualizers.collection.flat.AFlatCollectionLayoutManager;
import yaas.visualizers.collection.flat.AFlatCollectionVisualizer;
import yaas.visualizers.collection.flat.AFlatIntBarchartCollectionLayoutManager;
import yaas.visualizers.collection.flat.AFlatIntegerCollectionVisualizer;
import yaas.visualizers.collection.flat.AGraphicFlatCollectionVectorMethodsAnimator;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnIntegerBarChartVisualizer;

public class Scanner extends SharedDriver {
	static ListenableString string1 = new AListenableString(); 
	static ListenableVector<String> tokens = new AListenableVector<String>();
	static ListenableString token = new AListenableString();
//	static AnIntegerBarChartVisualizer visualizer;
//	static CollectionVisualizer visualizer;

//	static int[] intArray = {1, 2, 3, 4, 5};
	
	public static void createRootObjects() {
		string1.setName("sentence");
		tokens.setName("words");
		token.setName("token");

	}
	
	public static void fillElements() {
		string1.setString("Words, all I have");
//		string1.addElement('d');
//		string1.addElement('a');
//		string1.addElement('c');
//		string1.addElement('g');
//		string1.addElement('f');
//		string1.addElement('i');
//		string1.addElement('j');
//		string1.addElement('h');
//		string1.addElement('b');
//		string1.addElement('e');
	}
	
//	public static void createAnimator() {
//		string1.setName("list");
//
//		try {
////			visualizer = new AnIntegerBarChartVisualizer();
//			visualizer = new AFlatCollectionVisualizer();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	static CollectionLayoutManager layoutManager;
	
//	public static void configureLayout() {
//		try {
//			 layoutManager = new AFlatCollectionLayoutManager((AFlatCollectionVisualizer) visualizer);
////			CompositeLayoutManager<ListenableVector<Integer>, Integer> layoutManager = (CompositeLayoutManager) visualizer.getLayoutManager();
//			layoutManager.setElementToShapeTranslator(new AnObjectToStringInARectangle());
////			visualizer.setLayoutManager(layoutManager);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public static CollectionLayoutManager  configureFlatLayout() {
//		try {
		CollectionLayoutManager layoutManager = new AFlatCollectionLayoutManager((CollectionVisualizer) visualizer);
//			CompositeLayoutManager<ListenableVector<Integer>, Integer> layoutManager = (CompositeLayoutManager) visualizer.getLayoutManager();
			layoutManager.setElementToShapeTranslator(new AnObjectToStringInARectangle(visualizer.getObjectColorManager()));
//		CollectionLayoutManager flatLayoutManager = new AFlatIntBarchartCollectionLayoutManager((CollectionVisualizer) visualizer);
////			CompositeLayoutManager<ListenableVector<Integer>, Integer> layoutManager = (CompositeLayoutManager) visualizer.getLayoutManager();
//			flatLayoutManager.setElementToShapeTranslator(new IntToVerticalBar(visualizer.getObjectColorManager()));
//			return flatLayoutManager;
//			visualizer.setLayoutManager(vector, layoutManager);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
			return layoutManager;
//	
	}
	
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
	
//	public static void addGraphicsTrapper() {
//		try {
////		visualizer = new AnIntegerBarChartAnimator();
////		visualizer.animate(vector);
////		visualizer.addTrapper(new AnIntegerBarChartEventTrapper(visualizer,
////				(AnIntegerBarChartLayoutManager) visualizer
////						.getLayoutManager()));
////			VectorMethodsListener<Character> textReplayer = new ATextualCollectionVectorMethodsAnimator(( visualizer.getVisualization()).getStatusShape());
//			VectorMethodsListener<Character> graphicsReplayer = new AGraphicFlatCollectionVectorMethodsAnimator((CollectionVisualizer)visualizer);
//
////			visualizer.addReplayMethodListenerOfObjectTree(string1, textReplayer);
//			visualizer.addReplayMethodListenerOfObjectTree(string1, graphicsReplayer);
//
////			visualizer.addReplayMethodListenerOfObjectTree(string2, textReplayer);
//			visualizer.addReplayMethodListenerOfObjectTree(string2, graphicsReplayer);
////		visualizer.addReplayMethodListener(new AGraphicIntegerCollectionVectorMethodsAnimator(visualizer));
////		visualizer.addReplayMethodListener(null, new AGraphicFlatCollectionVectorMethodsAnimator((AFlatCollectionVisualizer)visualizer));
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
//		int index1 = 4;
//		int index2 = 8;
//		string1.setPointer(index1);
//		string1.setPointer2(index2);
		int startTokenPos = 0;
		int endTokenPos = 0;
		string1.setPointer(startTokenPos);
		while ( startTokenPos < string1.size()) {
			
			char c =  string1.observableGet(startTokenPos);
			if (!Character.isLetter(c)) {
				startTokenPos++;
				string1.setPointer(startTokenPos);
				continue;
			}
			string1.userOperation(startTokenPos, "letter");
			
			endTokenPos = startTokenPos;
			string1.setPointer2(endTokenPos);

			while (true) {
				endTokenPos++;
				string1.setPointer2(endTokenPos);
				if	(endTokenPos >= string1.size() ) {
					string1.userOperation(endTokenPos, "sentence end");

					break;
				}
				c = string1.observableGet(endTokenPos);
				if (!Character.isLetter(c)) {
					string1.userOperation(endTokenPos, "not letter");
					break;	
				}
			}
//			if (endTokenPos < string1.size())
			

			while (startTokenPos < endTokenPos) {
//				c = string1.get(startTokenPos);
//				token.add(c);
				string1.copyAndInsert(startTokenPos, token, token.size());
				startTokenPos++;
				string1.setPointer(startTokenPos);
			}
			tokens.add(token.toString());
			token.clear();
		}
		
//		string1.setString("Another    sentence");
//		string1.clear();
//		string1.add('d');
//		string1.add('e');
//		yaas.common.Algorithms.shellSort(vector);
//		yaas.common.Algorithms.shellSortTemp(list);
//		yaas.common.Algorithms.bubbleSort(vector);



	}
	public static void displayAnimator() {
		CreateCustomView viewCreator = new CreateCustomView();
		viewCreator.setDataPanel(visualizer.getLayoutManagerOfRootObject(string1)
				.displayInPanel());

		Object[] menuItems = {
				new yaas.OE.Sorting(string1),
				new Algorithms(string1) };

		CreateCustomView viewCreator1 = new CreateCustomView();
//		viewCreator.setDataPanel(visualizer.getDataPanel());
//		yaas.common.Algorithms.shellSortUserObject(vector);
//		yaas.common.Algorithms.shellSort(vector);

		viewCreator1.createView(menuItems, visualizer, visualizer
				.getMethodsCodeVisualization(), true, visualizer.isShowCode(), true);
	}
	
//	public static void visualize(ListenableVector aVector) {
//		try {
//			visualizer.visualize(aVector, layoutManager);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void visualize(ListenableVector aVector, LayoutManager aLayoutManager) {
//		try {
//			visualizer.visualize(aVector, aLayoutManager);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	static CompositeLayoutManager compositeLayoutManager;
//	public static void setCompositeLayout() {
//			compositeLayoutManager = new ACompositeRowColumnLayoutManager(visualizer);
//			visualizer.setCompositeLayoutManager(compositeLayoutManager);
//			
//		
//	}

	public static void main(String[] args) {
//		ParserMain.parse(Algorithms.class);
//		Character c1 = new Character('c');
//		Character c2 = new Character('c');
//		Integer i1 = new Integer(1);
//		Integer i2 = new Integer(1);
//		System.out.println(i1 == i2);
//		System.out.println(c1 == c2);
		createRootObjects();
	
		fillElements();
		createAnimator();
		setCompositeLayout();
		addTextTrapper(string1);
		addTextTrapper(token);
		addTextTrapper(tokens);
		
//		configureLayout();
		addFlatTrapper(string1);
		addFlatTrapper(token);
		addFlatTrapper(tokens);


		visualize(string1, configureFlatLayout());
		visualize(token, configureFlatLayout());
//		string2.setName("string2");
		visualize(tokens, configureFlatLayout());
//		addTrapper();

		setCodeParameters();
		displayAnimator();
		doSort(string1);

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
