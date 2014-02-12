package yaas.visualizers.collection.flat;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;










import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.misc.Common;
import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.buffers.vector.ALinearBuffer;
import yaas.collection.ACollectionLayoutManager;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.layout.gui.APanelGenerator;
import yaas.layout.nodes.AFlatElement;
import yaas.layout.nodes.FlatElement;
import yaas.shapemappers.ACirclePointerShapeManager;
import yaas.shapemappers.LabelMapper;
import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.shapemappers.PointerShapeCreator;
import yaas.visualization.collection.CollectionVisualization;
import yaas.visualizers.collection.CollectionVisualizer;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.shapes.ListenableShapeVector;

public class AFlatCollectionLayoutManager<ElementType> extends ACollectionLayoutManager<ElementType> implements
//		CompositeLayoutManager<ListenableVector<ElementType>, ElementType> {
	FlatCollectionLayoutManager<ElementType> {

	
	public static final int INITIAL_X = 30;
//	public static final int DEFAULT_X_SPACING= 5;
	public static final int DEFAULT_X_SPACING= 5;

	

	
	public static final int LABEL_Y_OFFSET = 0;
	public static final int LABEL_X_OFFSET = 0;

	private int currX = INITIAL_X;
	public static final int TEMP_X = 5;
	public   final int tempY = preferredHeight;
//    int statusY = baseLineY + 30;

//	protected int barWidth = 10;
	protected int scaleFactor = 10;
//	private int xSpacing = 15;
	protected int xSpacing = DEFAULT_X_SPACING;

    
//	public static final int STATUS_DEFAULT_X = 30;
//	
//	
//	public static final int STATUS_DEFAULT_Y = 0;
//
//	
//	int statusX = STATUS_DEFAULT_X;
//	int statusY = STATUS_DEFAULT_Y;
	
	private JPanel panel = new JPanel();
	private APanelGenerator panelGenerator = new APanelGenerator();
	
//	private AnIntegerBarChartVisualizer visualizer;
//	Translator<Integer, SimpleShape> translator = new IntToBar();
//	Translator<Integer, SimpleShape> translator = new IntToLine();


	public AFlatCollectionLayoutManager(CollectionVisualizer visualizer){
		super(visualizer);
//		this.visualizer = visualizer;
//		setElementToShapeTranslator(new IntToBar());
//		setPointerShapeCreator(new CirclePointerShapeManager());
//		visualizer.getVisualization().getStatusShape().setX(INITIAL_X);
//		visualizer.getVisualization().getStatusShape().setY(statusY);
	}
	
	public void setReadPointerShapeInitialCoordinates(BoundedShape aPointerShape) {
		aPointerShape.setY(preferredHeight);
		aPointerShape.setX(INITIAL_X - xSpacing);
	}
	
	public ListenableVector<BoundedShape> display(ListenableVector<ElementType> list) {
		super.display(list);

//		ListenableVector<SimpleShape> shapes = new AListenableVector<SimpleShape>();
		CollectionVisualization visualization = getVisualizer().getVisualization();
//		ListenableShapeVector shapes = getCompositeLayoutManager().getAndPositionShapes(list);
//		ListenableShapeVector shapes = getCompositeLayoutManager().getContainingShapes(list);

		int index = 0;
		for (ElementType value : list) {
			BoundedShape shape = createShape(list, value, index, null, containingShapes);
			index++;
//			shapes.getShapes().get(0).add(shape);
			containingShapes.add(shape);
//			int height = i * scaleFactor;
//			int currY = baseLineY - height;
//			OEShape s = new ARectangleModel(currX, currY, barWidth, height);
//			s.setFilled(true);
//			shapes.add(s);
//			currX += xSpacing;
		}

//		shapes.setUserObjectShape(elementToShapeTranslator.translate(null));
//		((BoundedShape) shapes.getUserObjectShape()).setX(USER_OBJECT_X);
//		((BoundedShape) shapes.getUserObjectShape()).setY(USER_OBJECT_Y);
		containingShapes.setTempShape(initialTempShape(list, containingShapes));
//		shapes.setMarkerShape(createPointerShape());
//		shapes.setMarker2Shape(createPointerShape());

//		System.out.println("Why generate this user interface when custom view does it?");
//		ObjectEditor.editInDrawingContainer(Util.copyVector(shapes), panel, false);	
//		panel.add(panelGenerator.layoutAndPopulateButtonPanel(),BorderLayout.NORTH);
		return null;
	}
	
//	protected BoundedShape createPointerShape() {
//		return getMarkingPointerShapeCreator().toNewPointerShape(null, null);
//		
//	}
//	public AttributedShape nextShape(int val) {
//		int height = val * scaleFactor;
//		int currY = baseLineY - height;
//		AttributedShape retVal = new ARectangleModel(currX, currY, barWidth, height);
//		((FlexibleShape) retVal).setFilled(true);
//		currX += xSpacing;
//		return retVal;
//	}
	
	
//	public OEShape nextShape(int val, int index) {
//		int height = val * scaleFactor;
//		int currY = baseLineY - height;
//		int currX = INITIAL_X + xSpacing*index;
//		OEShape retVal = new ARectangleModel(currX, currY, barWidth, height);
//		retVal.setFilled(true);
////		currX += xSpacing;
//		return retVal;
//	}
	public Object getLabelInfo (BoundedShape anElementShape) {
		return  Common.deepCopy(((FlatElement) anElementShape).getLabelShape());
	}
	public void restoretLabelInfo (BoundedShape anElementShape, Object aLabelnfo) {
		((FlatElement) anElementShape).getLabelShape().copy((BoundedShape) aLabelnfo);
	}
	public CollectionVisualizer<ElementType> getVisualizer() {
		return (CollectionVisualizer<ElementType> ) visualizer;
	}
	public BoundedShape initialCopiedObjectShape() {
		BoundedShape contentShape = elementToShapeTranslator.translate(null);
//		LabelMapper labelMapper = getVisualizer().getCollectionLabelMapper();
//		BoundedShape aLabelShape = labelMapper.getLabelShape(parent, index);
		BoundedShape aLabelShape = getLabelShape(null, -1);	
		BoundedShape retVal = new AFlatElement(contentShape, aLabelShape,this);
		return retVal;
	}
	public BoundedShape initialTempShape(ListenableVector parent, ListenableShapeVector containingShapes) {
		return createShape(parent, null, -1, null, containingShapes);
		
	}
	int getXSpacing() {
		return xSpacing;
	}
	public BoundedShape createShape(ListenableVector parent, ElementType val, Integer index, Rectangle initBounds, ListenableShapeVector containingShapes) {
		BoundedShape contentShape = elementToShapeTranslator.translate((ElementType) val);
//		LabelMapper labelMapper = getVisualizer().getCollectionLabelMapper();
//		BoundedShape aLabelShape = labelMapper.getLabelShape(parent, index);
		BoundedShape aLabelShape = getLabelShape(parent, index);
		BoundedShape retVal = new AFlatElement(contentShape, aLabelShape,this);
//		BoundedShape label = labelMapper.getLabelShape(aParent, aChild)
//		BoundedShape compositeShape = new AFlatElement(contentShape, )
		
		int height = retVal.getHeight();
//		int currY = containingShapes.getY() + baseLineY - height;
		int parentY = containingShapes.getY();

//		int currX = containingShapes.getX() + INITIAL_X + xSpacing*index;
		int parentX = containingShapes.getX();
		int childY = getShapeY(parentY);
		int childX ;
		if (index < 0)
			childX = parentX + TEMP_X;
		else if (index == 0) {
			childX = parentX + INITIAL_X;
		} else {
			BoundedShape previousShape = containingShapes.get(index -1);
			childX = previousShape.getX() + previousShape.getWidth() + getXSpacing();
		}
//		if (index >= 0) {
//			parentX += INITIAL_X + (retVal.getWidth() + getXSpacing())*index; // assuming constant width for all shapes
//		} else {
//			parentX += TEMP_X;
//		}

//		retVal.setX(currX);
//		retVal.setY(currY);
		
		Point realLocation = normalizedToRealLocation(containingShapes,retVal, new Point (childX, childY));
//		aLabelShape.setX(realLocation.x + LABEL_X_OFFSET);
//		aLabelShape.setY(currY + LABEL_Y_OFFSET);
		retVal.setX(realLocation.x);
		retVal.setY(realLocation.y);
		
//		Point labelShapeLocation = getLabelShapeLocation(retVal);
//		aLabelShape.setX(labelShapeLocation.x);
//		aLabelShape.setY(labelShapeLocation.y);


		return retVal;
	}
	
	protected int  getShapeY(int aParentY) {
		return aParentY;
		
//		int parentY = containingShapes.getY() + preferredHeight;

	}
	
	public void updateLabelShapes(ListenableShapeVector aListenableShapeVector, ListenableVector aParent) {
		int size = aListenableShapeVector.size();
		for (int i = 0; i < size; i++ ) {
			BoundedShape aFlatElement = aListenableShapeVector.get(i);
			updateLabelShape(aFlatElement, aParent, i);
		}
		
	}

	
	public void updateLabelShape(BoundedShape aCompositeShape, ListenableVector parent,  int index) {
		FlatElement flatElement = (FlatElement) aCompositeShape;
		BoundedShape labelShape = getLabelShape(parent, index);
		flatElement.updateLabelShape(labelShape);
	}
	
	public BoundedShape getLabelShape(ListenableVector parent,  int index) {
		LabelMapper labelMapper = getVisualizer().getCollectionLabelMapper();
		return labelMapper.getLabelShape(parent, index);
		
	}
	
	public Point getLabelShapeLocation (BoundedShape aContentShape, BoundedShape aLabelShape) {
		return new Point (aContentShape.getX() + aContentShape.getWidth()/2 - aLabelShape.getWidth()/2, aContentShape.getY() + aContentShape.getHeight() + LABEL_Y_OFFSET);
	}
	
//	public Point getLabelShapeLocation (BoundedShape aContentShape) {
//		return new Point (aContentShape.getX() + LABEL_X_OFFSET, aContentShape.getY() + LABEL_Y_OFFSET);
//	}
	
	public  Point normalizedToRealLocation (ListenableShapeVector aContainingShapes, BoundedShape aShape, Point aNormalizedLocation) {
		return aNormalizedLocation;
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
		return preferredHeight;
	}

	public Component displayInPanel() {
		return panel;
	}

	public Container getPanel() {
		return panel;
	}
//	ObjectToShapeTranslator<ElementType> elementToShapeTranslator; 
//	//= new IntToBar();
//	public ObjectToShapeTranslator<ElementType> getElementToShapeTranslator() {
//		return elementToShapeTranslator;
//		
//	}
//	public void setElementToShapeTranslator(ObjectToShapeTranslator<ElementType> newVal) {
//		elementToShapeTranslator = newVal;
//
//		getVisualizer().getVisualization().setCopiedObjectShape(initialCopiedObjectShape());
//
//	}

	public ListenableVector<ElementType> createBuffer() {
		 return new ALinearBuffer<ElementType>(visualizer);
	}

	public int getPreferredHeight() {
		return preferredHeight;
	}

	public void setPreferredHeight(int preferredHeight) {
		this.preferredHeight = preferredHeight;
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
