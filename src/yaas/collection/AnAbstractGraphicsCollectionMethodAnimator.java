package yaas.collection;

import java.util.Collection;

import bus.uigen.shapes.ListenableShapeVector;
import shapes.BoundedShape;
import util.models.AListenableVector;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.animators.AnimationUtil;
import yaas.layout.LayoutManager;
import yaas.shapemappers.PointerShapeCreator;
import yaas.shapemappers.RectangleEnds;
import yaas.visualizers.collection.CollectionVisualizer;
import yaas.visualizers.collection.flat.AFlatCollectionVisualizer;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnIntegerBarChartVisualizer;
import yaas.visualizers.collection.flat.FlatCollectionLayoutManager;

public abstract class AnAbstractGraphicsCollectionMethodAnimator<ElementType> implements VectorMethodsListener<ElementType>  {
	protected CollectionVisualizer<ElementType> visualizer;
//	protected CollectionLayoutManager<ElementType> layoutManager;
//	protected BoundedShape readPointerShape, destinationReadPointerShape, destinationMarkerShape, destinationMarker2Shape;
	protected BoundedShape readPointerShape, annotationPointerShape;
//	PointerShapeCreator<ElementType> readPointerShapeManager, markerPointerShapeCreator;
	public AnAbstractGraphicsCollectionMethodAnimator(
			CollectionVisualizer<ElementType>  visualizer) {
//			CollectionLayoutManager<ElementType> layoutManager) {
		this.visualizer = visualizer;
		
//		readPointerShapeManager = layoutManager.getReadPointerShapeCreator();
//		markerPointerShapeCreator = layoutManager.getMarkingPointerShapeCreator();
//		destinationReadPointerShape = readPointerShapeManager.toNewPointerShape(null, null);
//		destinationMarkerShape = markerPointerShapeCreator.toNewPointerShape(null, null);
//		destinationMarker2Shape = markerPointerShapeCreator.toNewPointerShape(null, null);


		 readPointerShape =  visualizer.getVisualization().getPointerShape();
		 annotationPointerShape = visualizer.getVisualization().getAnnotationShape();
	}
//	public abstract BoundedShape getPointedShape(Object source, ElementType element, Integer pos) ;
	 public BoundedShape getPointedShape(Object source, Object element, Integer pos) {
		 BoundedShape pointedShape = null;
		 if (pos == null) {
				pointedShape = null;
			}
			else 
				if (pos >= 0)
					return getElementShape(source, pos);
		 
				else if (pos == -1) 
					return getParentShape(source);
		 	
			
//			pointedShape =  visualizer.getShapes().get(pos);
			else if (pos == - 2)
//				pointedShape = (BoundedShape) visualizer.getShapes().getUserObjectShape();
				return getUserObjectShape(source);
		 return pointedShape;
	 }
	public abstract BoundedShape getElementShape(Object source,  Integer pos) ;
	public abstract BoundedShape getUserObjectShape(Object source) ;
	public abstract BoundedShape getParentShape(Object source) ;
	
	public CollectionLayoutManager<ElementType> getLayoutManager(ListenableVector<ElementType> aBuffer) {
		return ((CollectionLayoutManager) visualizer.getLayoutManagerOfBuffer(aBuffer));
	}

public void objectRead(Object source, Object element, Integer pos) {
	makeAnnotationPointerInvisible(source);
		
		// add a method to layout manager to use these shape creators
//		System.out.println("read element:" + pos);
		
//		BoundedShape pointerShape = (BoundedShape) visualizer.getShapes().getPointerShape();
//		PointerShapeCreator pointerShapeCreator = getLayoutManager((ListenableVector<ElementType>)source).getReadPointerShapeCreator();
		PointerShapeCreator pointerShapeCreator = visualizer.getCompositeLayoutManager().getReadPointerShapeCreator();

		BoundedShape pointedShape = getPointedShape(source, element, pos);
//		if (pos == null) {
//			pointedShape = null;
//		}
//		else 
//			if (pos >= 0)
//		
//		pointedShape =  visualizer.getShapes().get(pos);
//		else
//			pointedShape = (BoundedShape) visualizer.getShapes().getUserObjectShape();
//		OEShape pointerShape = (OEShape) visualizer.getPointerShape();
//		PointerShapeCreator<Integer> pointerShapeCreator = layoutManager.getPointerShapeCreator();
//		BoundedShape pointerShape =  visualizer.getShapes().getPointerShape();

//		BoundedShape destinationPointerShape =   pointerShapeCreator.calculateNewShape(destinatipointerShape, pointedShape, element);
		BoundedShape destinationReadPointerShape = pointerShapeCreator.toNewPointerShape(null, null, null);
		destinationReadPointerShape =   pointerShapeCreator.calculateNewShape(destinationReadPointerShape, pointedShape, element, pos, null, null);

		if (pos == null)
//			destinationPointerShape.setY(layoutManager.getBaseLine());
//			getLayoutManager((ListenableVector<ElementType>)source).setReadPointerShapeInitialCoordinates(destinationReadPointerShape);
		visualizer.getCompositeLayoutManager().setReadPointerShapeInitialCoordinates(destinationReadPointerShape);

		AnimationUtil.newOEShapeAttributes(readPointerShape, destinationReadPointerShape);
	}
public void userOperationOccured(Object source, Integer aTargetIndex,
		Object anOperation) {
	

	
	PointerShapeCreator pointerShapeCreator = visualizer.getCompositeLayoutManager().getAnnotationPointerShapeCreator();
	int size = ((ListenableVector) source).size();
	BoundedShape destinationPointerShape = pointerShapeCreator.toNewPointerShape(null, null, null);

	BoundedShape pointedShape;
	if (aTargetIndex < size) {
		pointedShape = getPointedShape(source, null, aTargetIndex);
		destinationPointerShape =   pointerShapeCreator.calculateNewShape(destinationPointerShape, pointedShape, null, aTargetIndex, RectangleEnds.UpperCenter, anOperation);

	} else {
		pointedShape = getPointedShape(source, null, size - 1);
		destinationPointerShape =   pointerShapeCreator.calculateNewShape(destinationPointerShape, pointedShape, null, size - 1, RectangleEnds.BeyondUpperRight, anOperation);

	}

//	destinationPointerShape =   pointerShapeCreator.calculateNewShape(destinationPointerShape, pointedShape, null, aTargetIndex, RectangleEnds.UpperCenter, anOperation);

	if (aTargetIndex == null)
//		destinationPointerShape.setY(layoutManager.getBaseLine());
//		getLayoutManager((ListenableVector<ElementType>)source).setReadPointerShapeInitialCoordinates(destinationReadPointerShape);
	visualizer.getCompositeLayoutManager().setAnnotationPointerShapeInitialCoordinates(destinationPointerShape);

	AnimationUtil.newOEShapeAttributes(annotationPointerShape, destinationPointerShape);
}

public void objectMarked(ListenableShapeVector containingShapes, BoundedShape aMarkingPointer, Object source,  Integer pos, int pointerNo) {
	
	
	ListenableVector sourceVector = (ListenableVector<ElementType>) source;
	int size = sourceVector.size();
	PointerShapeCreator pointerShapeCreator;
	if (pointerNo == 0)
		pointerShapeCreator = getLayoutManager((ListenableVector<ElementType>)source).getMarkingPointerShapeCreator();
	else
		pointerShapeCreator = getLayoutManager((ListenableVector<ElementType>)source).getMarkingPointer2ShapeCreator();

	Object element = null;
	Integer virtualPosition = pos;
	RectangleEnds anEndPoint = RectangleEnds.UpperLeft;
	
	if (virtualPosition != null) {
		if (virtualPosition >= size) {
			
			virtualPosition = size - 1;
			anEndPoint = RectangleEnds.BeyondUpperRight;
		}
			element = ((ListenableVector) source).get(virtualPosition);
	}

	
	BoundedShape pointedShape = getPointedShape(source, element, virtualPosition);
	BoundedShape destinationMarkerShape = pointerShapeCreator.toNewPointerShape(null, null, null);

	destinationMarkerShape =   pointerShapeCreator.calculateNewShape(destinationMarkerShape, pointedShape, element, virtualPosition, anEndPoint, null);

	if (pos == null) {
//		destinationPointerShape.setY(layoutManager.getBaseLine());
		getLayoutManager((ListenableVector<ElementType>)source).setMarkingPointerShapeInitialCoordinates(containingShapes, destinationMarkerShape);
//		pointerShapeCreator.toInvisiblePointedShape(aMarkingPointer, pointedShape, element);
	}
	AnimationUtil.newOEShapeAttributes(aMarkingPointer, destinationMarkerShape);
}


	public void elementRead(Object source, ElementType element, Integer pos) {
		objectRead(source, element, pos);
		
//		// add a method to layout manager to use these shape creators
////		System.out.println("read element:" + pos);
//		
////		BoundedShape pointerShape = (BoundedShape) visualizer.getShapes().getPointerShape();
//		PointerShapeCreator pointerShapeCreator = layoutManager.getPointerShapeCreator();
//		
//		BoundedShape pointedShape = getPointedShape(source, element, pos);
////		if (pos == null) {
////			pointedShape = null;
////		}
////		else 
////			if (pos >= 0)
////		
////		pointedShape =  visualizer.getShapes().get(pos);
////		else
////			pointedShape = (BoundedShape) visualizer.getShapes().getUserObjectShape();
////		OEShape pointerShape = (OEShape) visualizer.getPointerShape();
////		PointerShapeCreator<Integer> pointerShapeCreator = layoutManager.getPointerShapeCreator();
////		BoundedShape pointerShape =  visualizer.getShapes().getPointerShape();
//
////		BoundedShape destinationPointerShape =   pointerShapeCreator.calculateNewShape(destinatipointerShape, pointedShape, element);
//		destinationPointerShape =   pointerShapeCreator.calculateNewShape(destinationPointerShape, pointedShape, element);
//
//		if (pos == null)
////			destinationPointerShape.setY(layoutManager.getBaseLine());
//			layoutManager.setPointerShapeInitialCoordinates(destinationPointerShape);
//		AnimationUtil.newOEShapeAttributes(pointerShape, destinationPointerShape);
	}
	public void userObjectRead(Object source, Object readValue) {
		objectRead(source, null, -1);
//        PointerShapeCreator pointerShapeCreator = layoutManager.getPointerShapeCreator();
//		
//		BoundedShape pointedShape = getPointedShape(null, readValue, -1);
//		destinationPointerShape =   pointerShapeCreator.calculateNewShape(destinationPointerShape, pointedShape, readValue);
//
//		
//		AnimationUtil.newOEShapeAttributes(pointerShape, destinationPointerShape);
		
		
	}

	public void tempRead(Object source, Object readValue) {
		objectRead(source, readValue, -2);

//        PointerShapeCreator pointerShapeCreator = layoutManager.getPointerShapeCreator();
//		
//		BoundedShape pointedShape = getPointedShape(null, readValue, -2);
//		destinationPointerShape =   pointerShapeCreator.calculateNewShape(destinationPointerShape, pointedShape, readValue);
//
//		
//		AnimationUtil.newOEShapeAttributes(pointerShape, destinationPointerShape);
		
	}
	
	void makeDynamicPointerShapesInvisible(Object source) {
		makeReadPointerInvisible(source);
		makeAnnotationPointerInvisible(source);
	}
	void makeReadPointerInvisible( Object source) {
//		readPointerShapeManager.toInvisiblePointedShape(readPointerShape, null, null);
//		getLayoutManager((ListenableVector<ElementType>) source).getReadPointerShapeCreator().

		visualizer.getCompositeLayoutManager().getReadPointerShapeCreator().
		toInvisiblePointedShape(readPointerShape, null, null);

	}
	void makeAnnotationPointerInvisible( Object source) {
//		readPointerShapeManager.toInvisiblePointedShape(readPointerShape, null, null);
//		getLayoutManager((ListenableVector<ElementType>) source).getReadPointerShapeCreator().

	visualizer.getCompositeLayoutManager().getAnnotationPointerShapeCreator().
		toInvisiblePointedShape(annotationPointerShape, null, null);

	}
	public void elementAdded(Object source, ElementType element, int newSize) {
		makeDynamicPointerShapesInvisible(source);
		
	}
	public void elementsAdded(Object source,
			Collection<? extends ElementType> element, int newSize) {
		makeDynamicPointerShapesInvisible(source);

		// TODO Auto-generated method stub
		
	}
	public void elementInserted(Object source, ElementType element, int pos,
			int newSize) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementChanged(Object source, ElementType element, int pos) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void userObjectChanged(Object source, Object newVal) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void tempChanged(Object source, Object newVal) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementRemoved(Object source, int pos, int newSize) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementRemoved(Object source, ElementType element, int newSize, int pos) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementsCleared(Object source) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementSwapped(Object newParam, int index1, int index2) {
		// TODO Auto-generated method stub\
		makeDynamicPointerShapesInvisible(newParam);

		
	}
	public void elementSwapped(Object source, int index1, Object other,
			int index2) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
//	public void elementMoved(Object source, int fromIndex, int toIndex) {
//		elementMoved(source, fromIndex, 0, source, toIndex);
//
//
//	}
	public void elementMoved(Object source, int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		makeDynamicPointerShapesInvisible(source);

		// TODO Auto-generated method stub
		
	}
	public void elementReplaced(Object source, int fromIndex, int toIndex,
			int newSize) {
		makeDynamicPointerShapesInvisible(source);

		// TODO Auto-generated method stub
		
	}
	public void elementReplaced(Object source, int fromIndex, int newFromSize,
			Object to, int toIndex) {
		makeDynamicPointerShapesInvisible(source);

		// TODO Auto-generated method stub
		
	}
	public void elementCopiedToUserObject(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementCopiedToTemp(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementCopiedFromUserObject(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void elementCopiedFromTemp(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		makeDynamicPointerShapesInvisible(source);

		
	}
	public void pointerChanged(Object source, Integer pointerValue) {
		makeAnnotationPointerInvisible(source);

		
		ListenableShapeVector containingShapes = getContainingShapes(source);
		BoundedShape pointer = containingShapes.getMarkerShape();
		objectMarked(containingShapes, pointer, source, pointerValue, 0);
//		if (pointerValue == null) {
//			
//			markerPointerShapeCreator.toInvisiblePointedShape(pointer, null, null);
//			return;

//		}
		
//		ListenableVector listenableSource = (ListenableVector) source;
//		Object element = ((ListenableVector) source).get(pointerValue);
		 
			
		
	}
	
	
	
	public void pointer2Changed(Object source, Integer pointerValue) {
		makeAnnotationPointerInvisible(source);
		ListenableShapeVector containingShapes = getContainingShapes(source);
		BoundedShape pointer = containingShapes.getMarker2Shape();
		objectMarked(containingShapes, pointer, source, pointerValue, 1);
		
	}
	CollectionVisualizer<ElementType> getVisualizer() {
		return (CollectionVisualizer<ElementType>) visualizer;
	}
	
	protected ListenableShapeVector getContainingShapes(Object source) {
		return getVisualizer().
				getLayoutManagerOfBuffer((ListenableVector<ElementType>) source).
				getContainingShapes((ListenableVector<ElementType>) source);

	}
	public void elementCopiedAndInserted(Object source, int fromIndex,
			int toIndex, int newSize) {
		makeDynamicPointerShapesInvisible(source);
		
	}
	public void elementCopiedAndInserted(Object source, int fromIndex,
			int fromNewSize, Object to, int toIndex) {
		makeDynamicPointerShapesInvisible(source);
		
	}
	public void userObjectCopiedToTemp(Object source, Object copiedValue) {
		makeDynamicPointerShapesInvisible(source);
		
	}
	public void tempCopiedToUserObject(Object source, Object copiedValue) {
		
		makeDynamicPointerShapesInvisible(source);
		
	}
	

}
