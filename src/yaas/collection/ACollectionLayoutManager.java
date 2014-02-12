package yaas.collection;

import bus.uigen.shapes.ListenableShapeVector;
import shapes.BoundedShape;
import util.models.ListenableVector;
import yaas.Visualizer;
import yaas.layout.ARowColumnLayoutManager;
import yaas.layout.AVisualizationBasedLayoutManager;
import yaas.layout.CompositeLayoutManager;
import yaas.layout.LayoutManager;
import yaas.shapemappers.ACirclePointerShapeManager;
import yaas.shapemappers.AFlagPointerShapeManager;
import yaas.shapemappers.ARedFlagPointerShapeManager;
import yaas.shapemappers.ASquarePointerShapeManager;
import yaas.shapemappers.AThickLinePointerShapeManager;
import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.shapemappers.PointerShapeCreator;
import yaas.visualizers.collection.CollectionVisualizer;
import yaas.visualizers.collection.flat.AnIntegerBarChartVisualizer;

public abstract class ACollectionLayoutManager<ElementType> 
	extends AVisualizationBasedLayoutManager<ListenableVector<ElementType>>
//	ARowColumnLayoutManager<ListenableVector<ElementType>>

	implements CollectionLayoutManager<ElementType> {
	PointerShapeCreator<ElementType> readPointerShapeCreator; // = new ACirclePointerShapeManager();
	PointerShapeCreator<ElementType> markingPointerShapeCreator, markingPointer2ShapeCreator; // = new ASquarePointerShapeManager();
//	protected ListenableShapeVector containingShapes;

	
//	protected CollectionVisualizer<ElementType> visualizer;
	
	public CollectionVisualizer<ElementType>  getVisualizer() {
		return (CollectionVisualizer<ElementType>) visualizer;
	}

	public ACollectionLayoutManager(CollectionVisualizer<ElementType> aVisualizer) {
		super (aVisualizer);
//		visualizer = aVisualizer;
//		setReadPointerShapeCreator(new ACirclePointerShapeManager());
//		setMarkingPointerShapeCreator(new AThickLinePointerShapeManager());
		setMarkingPointerShapeCreator(new AFlagPointerShapeManager());
		setMarkingPointer2ShapeCreator(new ARedFlagPointerShapeManager());


		

	}
	public abstract Object initialTempShape(ListenableVector parent, ListenableShapeVector containingShapes);
	
	public CompositeLayoutManager<ListenableVector<ElementType>> getCompositeLayoutManager() {
		return getVisualizer().getCompositeLayoutManager();
	}

	public ListenableVector<BoundedShape> display(ListenableVector<ElementType> list) {
		 containingShapes = getCompositeLayoutManager().getAndPositionShapes(list, this);
		containingShapes.setTempShape(initialTempShape(list, containingShapes));


		containingShapes.setMarkerShape(createMarkerPointerShape());
		containingShapes.setMarker2Shape(createMarkerPointer2Shape());
		return null;
	}
	protected BoundedShape createMarkerPointerShape() {
		return getMarkingPointerShapeCreator().toNewPointerShape(null, null, null);
		
	}
	protected BoundedShape createMarkerPointer2Shape() {
		return getMarkingPointer2ShapeCreator().toNewPointerShape(null, null, null);
		
	}
//	public PointerShapeCreator<ElementType>getReadPointerShapeCreator() {
//		return readPointerShapeCreator;
//		
//	}
	public PointerShapeCreator<ElementType>getMarkingPointerShapeCreator() {
		return markingPointerShapeCreator;
		
	}
	public PointerShapeCreator<ElementType>getMarkingPointer2ShapeCreator() {
		return markingPointer2ShapeCreator;
		
	}
	public abstract void setReadPointerShapeInitialCoordinates(BoundedShape aPointerShape);
	
	public  void setMarkingPointerShapeInitialCoordinates(ListenableShapeVector aListenableShapeVector, BoundedShape aPointerShape) {
		aPointerShape.setX(aListenableShapeVector.getX());
		aPointerShape.setY(aListenableShapeVector.getY());

	}
	
	public  void setMarkingPointer2ShapeInitialCoordinates(ListenableShapeVector aListenableShapeVector, BoundedShape aPointerShape) {
		aPointerShape.setX(aListenableShapeVector.getX());
		aPointerShape.setY(aListenableShapeVector.getY());

	}


//	public void setReadPointerShapeCreator(PointerShapeCreator<ElementType> newVal) {
//		readPointerShapeCreator = newVal;	
//		BoundedShape pointerShape = readPointerShapeCreator.toNewPointerShape(null, null);
//        pointerShape = readPointerShapeCreator.toInvisiblePointedShape(pointerShape, null, null);
//
//
////		BoundedShape pointerShape = pointerShapeCreator.toInvisiblePointedShape(null, null);
//		setReadPointerShapeInitialCoordinates(pointerShape);
////		pointerShape.setY(baseLineY);
////		pointerShape.setX(INITIAL_X - xSpacing);
//		getVisualizer().getVisualization().setPointerShape(pointerShape);
//	}
	public void setMarkingPointerShapeCreator(PointerShapeCreator<ElementType> newVal) {
		markingPointerShapeCreator = newVal;	
//		BoundedShape pointerShape = readPointerShapeCreator.toNewPointerShape(null, null);
//        pointerShape = readPointerShapeCreator.toInvisiblePointedShape(pointerShape, null, null);
//
//
////		BoundedShape pointerShape = pointerShapeCreator.toInvisiblePointedShape(null, null);
//		setReadPointerShapeInitialCoordinates(pointerShape);
////		pointerShape.setY(baseLineY);
////		pointerShape.setX(INITIAL_X - xSpacing);
//		getVisualizer().getVisualization().setPointerShape(pointerShape);
	}
	public void setMarkingPointer2ShapeCreator(PointerShapeCreator<ElementType> newVal) {
		markingPointer2ShapeCreator = newVal;	
//		BoundedShape pointerShape = readPointerShapeCreator.toNewPointerShape(null, null);
//        pointerShape = readPointerShapeCreator.toInvisiblePointedShape(pointerShape, null, null);
//
//
////		BoundedShape pointerShape = pointerShapeCreator.toInvisiblePointedShape(null, null);
//		setReadPointerShapeInitialCoordinates(pointerShape);
////		pointerShape.setY(baseLineY);
////		pointerShape.setX(INITIAL_X - xSpacing);
//		getVisualizer().getVisualization().setPointerShape(pointerShape);
	}
//	@Override
//	public ListenableShapeVector getContainingShapes(ListenableVector<ElementType> data) {
//		return containingShapes;
//	}
	protected ObjectToShapeTranslator<ElementType> elementToShapeTranslator; 
	//= new IntToBar();
	public ObjectToShapeTranslator<ElementType> getElementToShapeTranslator() {
		return elementToShapeTranslator;
		
	}
	public void setElementToShapeTranslator(ObjectToShapeTranslator<ElementType> newVal) {
		elementToShapeTranslator = newVal;

		getVisualizer().getVisualization().setCopiedObjectShape(initialCopiedObjectShape());

	}
	public abstract BoundedShape initialCopiedObjectShape();


}
