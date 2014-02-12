package yaas.visualizers.collection.flat;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.JPanel;




import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.collection.ACollectionLayoutManager;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.layout.gui.APanelGenerator;
import yaas.shapemappers.ACirclePointerShapeManager;
import yaas.shapemappers.IntToVerticalBar;
import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.shapemappers.PointerShapeCreator;
import yaas.visualization.collection.CollectionVisualization;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.shapes.ListenableShapeVector;

public class AnIntegerBarChartLayoutManager extends ACollectionLayoutManager<Integer> implements
		CompositeShapeLayoutManager<ListenableVector<Integer>, Integer> {
	
	public static final int INITIAL_X = 30;
	


	private int currX = INITIAL_X;
	private int baseLineY = 200;
	public static final int USER_OBJECT_X = 5;
	public  final int USER_OBJECT_Y = baseLineY;
    int statusY = baseLineY + 30;

	private int barWidth = 10;
	private int scaleFactor = 10;
	private int xSpacing = 15;
	
	private JPanel panel = new JPanel();
	private APanelGenerator panelGenerator = new APanelGenerator();
//	List<LayoutManager> layoutManagers = new ArrayList();
	
//	private AnIntegerBarChartVisualizer visualizer;
//	Translator<Integer, SimpleShape> translator = new IntToBar();
//	Translator<Integer, SimpleShape> translator = new IntToLine();


	public AnIntegerBarChartLayoutManager(AnIntegerBarChartVisualizer visualizer){
		super(visualizer);
//		this.visualizer = visualizer;
		setElementToShapeTranslator(new IntToVerticalBar());
//		setPointerShapeCreator(new CirclePointerShapeManager());
		visualizer.getVisualization().getStatusShape().setX(INITIAL_X);
		visualizer.getVisualization().getStatusShape().setY(statusY);
	}
	
	public void setReadPointerShapeInitialCoordinates(BoundedShape aPointerShape) {
		aPointerShape.setY(baseLineY);
		aPointerShape.setX(INITIAL_X - xSpacing);
	}
	
	public ListenableVector<BoundedShape> display(ListenableVector<Integer> list) {
//		super.display(list);

//		ListenableVector<SimpleShape> shapes = new AListenableVector<SimpleShape>();
		CollectionVisualization visualization = getVisualizer().getVisualization();
		ListenableShapeVector shapes = getCompositeLayoutManager().getAndPositionShapes(list, this);
		int index = 0;
		for (Integer value : list) {
			BoundedShape shape = nextShape(value, index);
			index++;
//			shapes.getShapes().get(0).add(shape);
			shapes.add(shape);
//			int height = i * scaleFactor;
//			int currY = baseLineY - height;
//			OEShape s = new ARectangleModel(currX, currY, barWidth, height);
//			s.setFilled(true);
//			shapes.add(s);
//			currX += xSpacing;
		}
//		((BoundedShape) visualization.getUserObjectShape()).setX(USER_OBJECT_X);
//		((BoundedShape) visualization.getUserObjectShape()).setY(USER_OBJECT_Y);
		shapes.setTempShape(elementToShapeTranslator.translate(null));
//		getVisualizer().getVisualization().setCopiedObjectShape(elementToShapeTranslator.translate(null)););
		((BoundedShape) shapes.getTempShape()).setX(USER_OBJECT_X);
		((BoundedShape) shapes.getTempShape()).setY(USER_OBJECT_Y);
//		System.out.println("Why generate this user interface when custom view does it?");
//		ObjectEditor.editInDrawingContainer(Util.copyVector(shapes), panel, false);	
//		panel.add(panelGenerator.layoutAndPopulateButtonPanel(),BorderLayout.NORTH);
		return null;
	}
	public AttributedShape nextShape(int val) {
		int height = val * scaleFactor;
		int currY = baseLineY - height;
		AttributedShape retVal = new ARectangleModel(currX, currY, barWidth, height);
		((FlexibleShape) retVal).setFilled(true);
		currX += xSpacing;
		return retVal;
	}
	
	
//	public OEShape nextShape(int val, int index) {
//		int height = val * scaleFactor;
//		int currY = baseLineY - height;
//		int currX = INITIAL_X + xSpacing*index;
//		OEShape retVal = new ARectangleModel(currX, currY, barWidth, height);
//		retVal.setFilled(true);
////		currX += xSpacing;
//		return retVal;
//	}
	
	public BoundedShape nextShape(int val, int index) {
		BoundedShape retVal = elementToShapeTranslator.translate(val);
		
		int height = retVal.getHeight();
		int currY = getVisualizer().getVisualization().getShapes().get(0).getY() + baseLineY - height;
		int currX = getVisualizer().getVisualization().getShapes().get(0).getX() + INITIAL_X + xSpacing*index;
		retVal.setX(currX);
		retVal.setY(currY);
//		OEShape retVal = new ARectangleModel(currX, currY, barWidth, height);
//		retVal.setFilled(true);
//		currX += xSpacing;
		return retVal;
	}
	

	public ListenableVector<AttributedShape> constructPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListenableVector<AttributedShape> getPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPseudoCodeMarker() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getScaleFactor() {
		return scaleFactor;
	}

	public int getBaseLine() {
		return baseLineY;
	}

	public Component displayInPanel() {
		return panel;
	}

	public Container getPanel() {
		return panel;
	}
	ObjectToShapeTranslator<Integer> elementToShapeTranslator = new IntToVerticalBar();
	public ObjectToShapeTranslator<Integer> getElementToShapeTranslator() {
		return elementToShapeTranslator;
		
	}
	public void setElementToShapeTranslator(ObjectToShapeTranslator<Integer> newVal) {
		elementToShapeTranslator = newVal;
//		getVisualizer().getVisualization().setUserObjectShape(elementToShapeTranslator.translate(null));
		getVisualizer().getVisualization().setCopiedObjectShape(elementToShapeTranslator.translate(null));
	}

	@Override
	public BoundedShape initialTempShape(ListenableVector parent,
			ListenableShapeVector containingShapes) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListenableVector<Integer> createBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListenableShapeVector getAndPositionShapes(
			ListenableVector<Integer> data) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListenableShapeVector getOrCreateShapeVector(int aShapesIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoundedShape initialCopiedObjectShape() {
		// TODO Auto-generated method stub
		return null;
	}

	public BoundedShape createShape(ListenableVector parent, Integer val,
			Integer index, Rectangle initBounds,
			ListenableShapeVector containingShapes) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	PointerShapeCreator<Integer> pointerShapeCreator = new CirclePointedShapeCreator();
//	public PointerShapeCreator<Integer>getPointerShapeCreator() {
//		return pointerShapeCreator;
//		
//	}
//	public void setPointerShapeInitialCoordinates(AttributedShape aPointerShape) {
//		aPointerShape.setY(baseLineY);
//		aPointerShape.setX(INITIAL_X - xSpacing);
//	}
//	public void setPointerShapeCreator(PointerShapeCreator<Integer> newVal) {
//		pointerShapeCreator = newVal;
//		AttributedShape pointerShape = pointerShapeCreator.toInvisiblePointedShape(null, null);
//		setPointerShapeInitialCoordinates(pointerShape);
////		pointerShape.setY(baseLineY);
////		pointerShape.setX(INITIAL_X - xSpacing);
//		visualizer.getShapes().setPointerShape(pointerShape);
//	}

	
}
