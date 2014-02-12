package yaas.visualizers.collection.flat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bus.uigen.shapes.ListenableShapeVector;
import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import sun.security.action.GetLongAction;
import util.misc.Common;
import util.models.VectorMethodsListener;
import util.session.AnAbstractCommunicator;
import util.trace.Tracer;
import yaas.animators.AnimationUtil;
import yaas.collection.AnAbstractGraphicsCollectionMethodAnimator;
import yaas.collection.CollectionLayoutManager;
import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.shapemappers.PointerShapeCreator;
import yaas.trappers.EventTrapper;
import yaas.visualizers.collection.CollectionVisualizer;

public class AGraphicFlatCollectionVectorMethodsAnimator<ElementType> 
        extends AnAbstractGraphicsCollectionMethodAnimator<ElementType>
		implements
		VectorMethodsListener<ElementType> {
	
//	protected AnElementTypeBarChartVisualizer visualizer;
//	protected AnIntegerBarChartLayoutManager layoutManager;
//	BoundedShape destinationPointerShape;
	public AGraphicFlatCollectionVectorMethodsAnimator(
			CollectionVisualizer visualizer) {
		super (visualizer);
//		this.visualizer = visualizer;
//		this.layoutManager = layoutManager;
//		destinationReadPointerShape = ((CollectionLayoutManager<ElementType>) visualizer.getLayoutManager()).getReadPointerShapeCreator().toNewPointerShape(null, null);
	}
	
	
	private static final long serialVersionUID = 3971272244005861176L;
	private static final int COPY_DELTA_X = 5;
	
	public FlatCollectionLayoutManager<ElementType> getLayoutManager(ListenableVector<ElementType> aBuffer) {
		return ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer(aBuffer));
	}

	void addShape(Object source, ElementType element, int newSize, ListenableShapeVector containingShapes) {
		BoundedShape aShape = ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer ((ListenableVector<ElementType>)source)).
				   createShape((ListenableVector) source, element, newSize - 1, null, containingShapes);
		Rectangle bounds = new Rectangle(aShape.getX(), aShape.getY(), aShape.getWidth(), aShape.getHeight());
		aShape.setWidth(0);
		aShape.setHeight(0);
//		visualizer.getVisualization().getShapes().get(0).add(aShape);
//		getContainingShapes(source).add(aShape);
		containingShapes.add(aShape);

//		getVisualizer().getLayoutManagerOfBuffer((ListenableVector<ElementType>) source).getContainingShapes((util.models.ListenableVector<ElementType>) source);
//		AnimationUtil.grow(aShape, bounds.width, bounds.height, false);
		AnimationUtil.newSize(aShape, new Dimension(bounds.width, bounds.height));

	}
	public void elementAdded(Object source, ElementType element, int newSize) {
		super.elementAdded(source, element, newSize);

//		visualizer.add(layoutManager.nextShape(element));
		ListenableShapeVector containingShapes = getContainingShapes(source);
		addShape(source, element, newSize, containingShapes);
//		BoundedShape aShape = ((AFlatCollectionLayoutManager) visualizer.getLayoutManager()).nextShape(element, newSize - 1, containingShapes);
//		Rectangle bounds = new Rectangle(aShape.getX(), aShape.getY(), aShape.getWidth(), aShape.getHeight());
//		aShape.setWidth(0);
//		aShape.setHeight(0);
////		visualizer.getVisualization().getShapes().get(0).add(aShape);
////		getContainingShapes(source).add(aShape);
//		containingShapes.add(aShape);
//
//		getVisualizer().getFlatCollectionLayoutManager().getShapesVector((util.models.ListenableVector<ElementType>) source);
////		AnimationUtil.grow(aShape, bounds.width, bounds.height, false);
//		AnimationUtil.newSize(aShape, new Dimension(bounds.width, bounds.height));

	}
	CollectionVisualizer<ElementType> getVisualizer() {
		return (CollectionVisualizer<ElementType>) visualizer;
	}
//	ListenableShapeVector getContainingShapes(Object source) {
//		return getVisualizer().
//				getFlatCollectionLayoutManager().
//				getShapesVector((ListenableVector<ElementType>) source);
//
//	}
public void elementRemoved(Object source, int pos, int newSize) {
	super.elementRemoved(source, pos, newSize);
	elementRemoved(source, pos);
//	BoundedShape shape = visualizer.getVisualization().getShapes().get(0).get(pos);
//	BoundedShape shape = getContainingShapes(source).get(pos);
//
//
//	AnimationUtil.grow(shape, 0, 0, false);
//
////		visualizer.getVisualization().getShapes().get(0).remove(pos);
//		getContainingShapes(source).remove(pos);


	}

List<Point> getSuccessorLocations (ListenableShapeVector aContainingShapes, int  aPosition) {
	return getSuccesorLocations(aContainingShapes, aPosition, aContainingShapes.size() -1);
}

   List<Point> getSuccesorLocations (ListenableShapeVector aContainingShapes, int  aPosition, int aToPosition) {
	   List<Point> retVal = new ArrayList();
	   for (int i = aPosition + 1; i <= aToPosition; i++) {
		   BoundedShape boundedShape = aContainingShapes.get(i);		   
		   retVal.add(new Point (boundedShape.getX(), boundedShape.getY()));		   
		   
	   }
	   return retVal;	   
   }
   List<Integer> getSuccessorX (ListenableShapeVector aContainingShapes, int  aPosition) {
	   	return getSuccesorX(aContainingShapes, aPosition, aContainingShapes.size() - 1);
   }
   List<Integer> getSuccesorX (ListenableShapeVector aContainingShapes, int  aPosition, int aToPosition) {
	   List<Integer> retVal = new ArrayList();
	   for (int i = aPosition + 1; i <= aToPosition; i++) {
		   BoundedShape boundedShape = aContainingShapes.get(i);		   
		   retVal.add(boundedShape.getX());		   
		   
	   }
	   return retVal;	   
   }
   List<BoundedShape> getSuccesorShapes (ListenableShapeVector aContainingShapes, int  aPosition, int aToPosition) {
	   List<BoundedShape> retVal = new ArrayList();
	   for (int i = aPosition + 1; i <= aToPosition; i++) {
		   retVal.add(aContainingShapes.get(i));		   
//		   retVal.add(new Point (boundedShape.getX(), boundedShape.getY()));		   
		   
	   }
	   return retVal;	   
   }
   
   List<BoundedShape> getSuccessorShapes (ListenableShapeVector aContainingShapes, int  aPosition) {
	   return getSuccesorShapes(aContainingShapes, aPosition, aContainingShapes.size() -1);
   }

  
   
   List<Point> getPrecedingLocations (ListenableShapeVector aContainingShapes, int  aPosition, int aFromPosition) {
	   List<Point> retVal = new ArrayList();
	   for (int i = aFromPosition; i < aPosition; i++) {
		   BoundedShape boundedShape = aContainingShapes.get(i);		   
		   retVal.add(new Point (boundedShape.getX(), boundedShape.getY()));		   
		   
	   }
	   return retVal;	   
   }
   List<Point> getPrecedingLocations (ListenableShapeVector aContainingShapes, int  aPosition) {
	  return getPrecedingLocations(aContainingShapes, aPosition, 0) ;
   }
   
   List<Integer> getPrecedingX (ListenableShapeVector aContainingShapes, int  aPosition, int aFromPosition) {
	   List<Integer> retVal = new ArrayList();
	   for (int i = aFromPosition; i < aPosition; i++) {
		   BoundedShape boundedShape = aContainingShapes.get(i);		   
		   retVal.add(boundedShape.getX());		   
		   
	   }
	   return retVal;	   
   }
   List<Integer> getPrecedingX (ListenableShapeVector aContainingShapes, int  aPosition) {
	   return getPrecedingX(aContainingShapes, aPosition, 0);
   }
   
   List<BoundedShape> getPrecedingShapes (ListenableShapeVector aContainingShapes, int  aPosition) {
	   return getPrecedingShapes(aContainingShapes, aPosition, 0);
   }

   
   List<BoundedShape> getPrecedingShapes (ListenableShapeVector aContainingShapes, int  aPosition, int aFromPosition) {
	   List<BoundedShape> retVal = new ArrayList();
	   for (int i = aFromPosition; i < aPosition; i++) {
		   retVal.add (aContainingShapes.get(i));		   
//		   retVal.add(new Point (boundedShape.getX(), boundedShape.getY()));		   
		   
	   }
	   return retVal;	   
   }
   public void normalizedToRealLocations (List<BoundedShape> aShapeList, 
		   List<Point> aNormalizedPoints,
		   ListenableShapeVector aContainingShapes, Object aSource) {
	   for (int i = 0; i < aShapeList.size(); i++) {
//		   Point aNormalizedLocation = aNormalizedPoints.get(i);
//		   Point aRealLocation = ((FlatCollectionLayoutManager) visualizer.getLayoutManager()).normalizedToRealLocation(
//				   	aContainingShapes, aShapeList.get(i),
//				   	aNormalizedLocation );
//		   aNormalizedLocation.x = aRealLocation.x;
//		   aNormalizedLocation.y = aRealLocation.y;
		   normalizedToRealLocation(aShapeList.get(i), 
				   aNormalizedPoints.get(i), 
				   aContainingShapes , aSource);
	   }
   }
   public void normalizedToRealLocation (BoundedShape aShape, 
		   Point aNormalizedLocation,
		   ListenableShapeVector aContainingShapes,
		   Object aRootBuffer) {
	   Point aRealLocation = ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer ((ListenableVector<ElementType>)aRootBuffer)).normalizedToRealLocation(
			   	aContainingShapes, aShape,
			   	aNormalizedLocation );
	   aNormalizedLocation.x = aRealLocation.x;
	   aNormalizedLocation.y = aRealLocation.y;
   }
   
    
//   public void elementCopiedAndInserted(Object source, int fromIndex,
//			int fromNewSize, Object to, int toIndex) {
//		// TODO Auto-generated method stub
//		
//	}
   public void elementCopiedAndInserted(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
	   super.elementCopiedAndInserted(source, fromIndex, fromNewSize, to, toIndex);
	   
	    ListenableShapeVector aContainingFromShapes = getContainingShapes(source);
	    ListenableShapeVector aContainingToShapes = getContainingShapes(to);
	    Object elementCopied = ((ListenableVector) to).get(toIndex);
	    
	    //no translation allowed for now to tree shape
		BoundedShape copiedShapeClone = ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer ((ListenableVector<ElementType>)to)).
				   createShape((ListenableVector) to, 
						   elementCopied, 
						   aContainingToShapes.size(), 
						   new Rectangle (0, 0, -1, -1), aContainingToShapes);
		
		BoundedShape copiedShapeOriginal = aContainingFromShapes.get(fromIndex);	
//		List<BoundedShape> successorFromShapesUntilDestination = getSuccessorShapes(aContainingFromShapes, fromIndex);
//		List<Point> successorFromLocationsUntilDestination = getSuccessorLocations(aContainingFromShapes, fromIndex);
////		Integer destinationX = successorXUntilDestination.remove(successorXUntilDestination.size() - 1);
//		if (successorFromShapesUntilDestination.size() > 1) {
//		successorFromLocationsUntilDestination.add(0, new Point (copiedShapeOriginal.getX(), copiedShapeOriginal.getY()));
//		successorFromLocationsUntilDestination.remove(successorFromLocationsUntilDestination.size() - 1);
////		successorFromLocationsUntilDestination.add(new Point (movedShapeOriginal.getX(), movedShapeOriginal.getY()));
//		}
		List<BoundedShape> successorToShapesUntilDestination = getSuccessorShapes(aContainingToShapes, toIndex-1);
		List<Point> successorToLocationsUntilDestination = getSuccessorLocations(aContainingToShapes, toIndex);
		if (successorToShapesUntilDestination.size() > 0) {
		
		   successorToLocationsUntilDestination.add(new Point (copiedShapeClone.getX(), copiedShapeClone.getY()));
	
		     BoundedShape replacedToShape = aContainingToShapes.get(toIndex);

		     copiedShapeClone.setX(replacedToShape.getX());
		}
//		normalizedToRealLocations(successorFromShapesUntilDestination, 
//				successorFromLocationsUntilDestination, aContainingFromShapes, source);
		normalizedToRealLocations(successorToShapesUntilDestination, successorToLocationsUntilDestination, aContainingToShapes, to);

//		normalizedToRealLocations(successorToShapesUntilDestination, successorToLocationsUntilDestination, aContainingToShapes, source);
		List<BoundedShape> allCopiedShapes = successorToShapesUntilDestination;
//		allMovedShapes.addAll(successorToShapesUntilDestination);
//		allCopiedShapes.add(copiedShapeOriginal);
		List<Point> allCopiedLocations = successorToLocationsUntilDestination;
//		allMovedLocations.addAll(successorToLocationsUntilDestination);
		allCopiedLocations.add(new Point (copiedShapeClone.getX(), copiedShapeClone.getY()));
		copiedShapeClone.setX(copiedShapeOriginal.getX());
		copiedShapeClone.setY(copiedShapeOriginal.getY());
		aContainingToShapes.insertElementAt(copiedShapeClone, toIndex);
		allCopiedShapes.add(copiedShapeClone);
		AnimationUtil.moveLocations(allCopiedShapes, allCopiedLocations);
//		aContainingToShapes.insertElementAt(copiedShapeClone, toIndex);
//		aContainingFromShapes.remove(fromIndex);
		getLayoutManager ((ListenableVector<ElementType>)source).updateLabelShapes(aContainingToShapes, (ListenableVector) to);
//		getLayoutManager ((ListenableVector<ElementType>)source).updateLabelShapes(aContainingFromShapes, (ListenableVector) source);



	}
   
   public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
	   super.elementMoved(source, fromIndex, fromNewSize, to, toIndex);
	   
	    ListenableShapeVector aContainingFromShapes = getContainingShapes(source);
	    ListenableShapeVector aContainingToShapes = getContainingShapes(to);
	    Object elementMoved = ((ListenableVector) to).get(toIndex);
	    
	    //no translation allowed for now to tree shape
		BoundedShape movedShapeClone = ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer ((ListenableVector<ElementType>)to)).
				   createShape((ListenableVector) to, 
						   elementMoved, 
						   aContainingToShapes.size(), 
						   new Rectangle (0, 0, -1, -1), aContainingToShapes);
		
		BoundedShape movedShapeOriginal = aContainingFromShapes.get(fromIndex);	
		List<BoundedShape> successorFromShapesUntilDestination = getSuccessorShapes(aContainingFromShapes, fromIndex);
		List<Point> successorFromLocationsUntilDestination = getSuccessorLocations(aContainingFromShapes, fromIndex);
//		Integer destinationX = successorXUntilDestination.remove(successorXUntilDestination.size() - 1);
		if (successorFromShapesUntilDestination.size() > 1) {
		successorFromLocationsUntilDestination.add(0, new Point (movedShapeOriginal.getX(), movedShapeOriginal.getY()));
		successorFromLocationsUntilDestination.remove(successorFromLocationsUntilDestination.size() - 1);
//		successorFromLocationsUntilDestination.add(new Point (movedShapeOriginal.getX(), movedShapeOriginal.getY()));
		}
		List<BoundedShape> successorToShapesUntilDestination = getSuccessorShapes(aContainingToShapes, toIndex-1);
		List<Point> successorToLocationsUntilDestination = getSuccessorLocations(aContainingToShapes, toIndex);
		if (successorToShapesUntilDestination.size() > 0) {
		
		   successorToLocationsUntilDestination.add(new Point (movedShapeClone.getX(), movedShapeClone.getY()));
	
		     BoundedShape replacedToShape = aContainingToShapes.get(toIndex);

		     movedShapeClone.setX(replacedToShape.getX());
		}
		normalizedToRealLocations(successorFromShapesUntilDestination, 
				successorFromLocationsUntilDestination, aContainingFromShapes, source);
		normalizedToRealLocations(successorToShapesUntilDestination, successorToLocationsUntilDestination, aContainingToShapes, to);

//		normalizedToRealLocations(successorToShapesUntilDestination, successorToLocationsUntilDestination, aContainingToShapes, source);
		List<BoundedShape> allMovedShapes = successorFromShapesUntilDestination;
		allMovedShapes.addAll(successorToShapesUntilDestination);
		allMovedShapes.add(movedShapeOriginal);
		List<Point> allMovedLocations = successorFromLocationsUntilDestination;
		allMovedLocations.addAll(successorToLocationsUntilDestination);
		allMovedLocations.add(new Point (movedShapeClone.getX(), movedShapeClone.getY()));
		AnimationUtil.moveLocations(allMovedShapes, allMovedLocations);
		aContainingToShapes.insertElementAt(movedShapeClone, toIndex);
		aContainingFromShapes.remove(fromIndex);
		getLayoutManager ((ListenableVector<ElementType>)source).updateLabelShapes(aContainingToShapes, (ListenableVector) to);
		getLayoutManager ((ListenableVector<ElementType>)source).updateLabelShapes(aContainingFromShapes, (ListenableVector) source);



	}
//   public void elementMoved(Object source, int fromIndex, int fromNewSize,
//			Object to, int toIndex) {
//	    ListenableShapeVector aContainingFromShapes = getContainingShapes(source);
//	    ListenableShapeVector aContainingToShapes = getContainingShapes(to);
//	    Object elementMoved = ((ListenableVector) to).get(toIndex);
//		BoundedShape movedShapeClone = ((AFlatCollectionLayoutManager) visualizer.getLayoutManager()).nextShape(elementMoved, aContainingToShapes.size(), aContainingToShapes);
//		BoundedShape movedShapeOriginal = aContainingFromShapes.get(fromIndex);	
//		List<BoundedShape> successorFromShapesUntilDestination = getSuccessorShapes(aContainingFromShapes, fromIndex);
//		List<Integer> successorFromXUntilDestination = getSuccessorX(aContainingFromShapes, fromIndex);
////		Integer destinationX = successorXUntilDestination.remove(successorXUntilDestination.size() - 1);
//		if (successorFromShapesUntilDestination.size() > 1) {
//		successorFromXUntilDestination.add(0, movedShapeOriginal.getX());
//		successorFromXUntilDestination.add(movedShapeOriginal.getX(), successorFromXUntilDestination.size() - 1);
//		}
//		List<BoundedShape> successorToShapesUntilDestination = getSuccessorShapes(aContainingToShapes, toIndex-1);
//		List<Integer> successorToXUntilDestination = getSuccessorX(aContainingToShapes, toIndex);
//		if (successorToShapesUntilDestination.size() > 0) {
//		
//		   successorToXUntilDestination.add(movedShapeClone.getX());
//	
//		     BoundedShape replacedToShape = aContainingToShapes.get(toIndex);
//
//		     movedShapeClone.setX(replacedToShape.getX());
//		}
//		List<BoundedShape> allMovedShapes = successorFromShapesUntilDestination;
//		allMovedShapes.addAll(successorToShapesUntilDestination);
//		allMovedShapes.add(movedShapeOriginal);
//		List<Integer> allMovedX = successorFromXUntilDestination;
//		allMovedX.addAll(successorToXUntilDestination);
//		allMovedX.add(movedShapeClone.getX());
//		AnimationUtil.moveX(allMovedShapes, allMovedX);
//		aContainingToShapes.insertElementAt(movedShapeClone, toIndex);
//		aContainingFromShapes.remove(fromIndex);
//
//	}
   public void elementMovedAhead(Object source, int fromIndex, int toIndex) {	   
	    ListenableShapeVector aContainingShapes = getContainingShapes(source);
	    Object elementMoved = ((ListenableVector) source).get(toIndex - 1);
		BoundedShape movedShapeClone = ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer ((ListenableVector<ElementType>)source)).
				createShape((ListenableVector) source, 
				elementMoved, 
				toIndex - 1, 
				null, aContainingShapes);

	    
		BoundedShape movedShapeOriginal = aContainingShapes.get(fromIndex);	

		List<BoundedShape> successorShapesUntilDestination = getSuccesorShapes(aContainingShapes, fromIndex, toIndex - 1);
		List<Point> successorLocationsUntilDestination = getSuccesorLocations(aContainingShapes, fromIndex, toIndex - 1);
		successorLocationsUntilDestination.add(0, new Point (
				movedShapeOriginal.getX(),
				movedShapeOriginal.getY()));		
		successorShapesUntilDestination.add(movedShapeOriginal);
		normalizedToRealLocations(successorShapesUntilDestination, successorLocationsUntilDestination, aContainingShapes, source);
		
		

		AnimationUtil.moveLocations(successorShapesUntilDestination, successorLocationsUntilDestination);
		aContainingShapes.insertElementAt(movedShapeClone, toIndex); // add moved shape
		aContainingShapes.removeElementAt(fromIndex); // remove clone
		getLayoutManager ((ListenableVector<ElementType>)source).updateLabelShapes(aContainingShapes, (ListenableVector) source);




		
	}
   public void elementMovedBehind(Object source, int fromIndex, int toIndex) {	   
	    ListenableShapeVector aContainingShapes = getContainingShapes(source);
	    Object elementMoved = ((ListenableVector) source).get(toIndex);
		BoundedShape movedShapeClone = ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer((ListenableVector<ElementType>) source)).
				createShape((ListenableVector) source,
						elementMoved,
						toIndex, 
						null, aContainingShapes);

	    
		BoundedShape movedShapeOriginal = aContainingShapes.get(fromIndex);	
//		BoundedShape movedShapeClone = (BoundedShape) Common.deepCopy(movedShapeOriginal);
//		System.out.println("Moved Shape before moving" + movedShapeClone);
		List<BoundedShape> predecessorShapesUntilDestination = getPrecedingShapes(aContainingShapes, fromIndex, toIndex);
		List<Point> predecessorLocationsUntilDestination = getPrecedingLocations(aContainingShapes, fromIndex, toIndex);
//		Integer destinationX = successorXUntilDestination.remove(successorXUntilDestination.size() - 1);
		predecessorLocationsUntilDestination.add(new Point (movedShapeOriginal.getX(), movedShapeOriginal.getY()));		
		predecessorShapesUntilDestination.add(0, movedShapeOriginal);
//		aContainingShapes.add(movedShapeClone); // oeverlap moved shape, does not matter where
		
		normalizedToRealLocations(predecessorShapesUntilDestination, predecessorLocationsUntilDestination, aContainingShapes, source);


		AnimationUtil.moveLocations(predecessorShapesUntilDestination, predecessorLocationsUntilDestination);
//		aContainingShapes.removeElementAt(fromIndex); // remove moved shape
		aContainingShapes.insertElementAt(movedShapeClone, toIndex); // add moved shape
		aContainingShapes.removeElementAt(fromIndex + 1); // remove clone
//		System.out.println("moved Shape:" + movedShapeClone);
//		System.out.println("Before move from:" +
//		aContainingShapes.get(fromIndex) + "to:" + aContainingShapes.get(toIndex));
////		aContainingShapes.move(fromIndex, toIndex);
//		BoundedShape destinationShape = aContainingShapes.get(toIndex - 1);
//		System.out.println("After move from:" +
//				aContainingShapes.get(fromIndex) + "to:" + destinationShape);
		
//		List<BoundedShape> successorShapesFromDestination = getSuccesorShapes(aContainingShapes, toIndex);
//		List<Integer> successorXFromDestination = getSuccesorX(aContainingShapes, toIndex);
		getLayoutManager ((ListenableVector<ElementType>)source).updateLabelShapes(aContainingShapes, (ListenableVector) source);



		
	}

   void elementRemoved(Object source, int pos) {
	    ListenableShapeVector aContainingShapes = getContainingShapes(source);
		BoundedShape shape = aContainingShapes.get(pos);	
		if (aContainingShapes.size() == 1) {
			AnimationUtil.makeDisappear(shape);
		} else {
		List<BoundedShape> successorShapes = getSuccessorShapes(aContainingShapes, pos);
		List<Point> successorLocations = getSuccessorLocations(aContainingShapes, pos);
		if (successorShapes.size() == 0) {
			AnimationUtil.makeDisappear(shape);
		} else {
		successorLocations.remove(successorLocations.size() - 1);
		successorLocations.add(0, new Point (shape.getX(), shape.getY()));
		normalizedToRealLocations(successorShapes, successorLocations, aContainingShapes, source);
		AnimationUtil.moveLocationAndMakeDisappear(shape, successorShapes, successorLocations);
		}
		}
		aContainingShapes.remove(pos);
		getLayoutManager ((ListenableVector<ElementType>)source).updateLabelShapes(aContainingShapes, (ListenableVector) source);

	
		
   }
   @Override
   public void elementsCleared(Object source) {
	   super.elementsCleared(source);
	    ListenableShapeVector aContainingShapes = getContainingShapes(source);
	   
		List<BoundedShape> shapes = new ArrayList();
		shapes.addAll(aContainingShapes);
		if (shapes.size() == 0) {
			return;
		} 
		
		AnimationUtil.makeDisappearCartesian(shapes);
		
		aContainingShapes.removeAllElements();
//		getLayoutManager().updateLabelShapes(aContainingShapes, (ListenableVector) source);

	
		
  }
   
   public void elementInserted(Object source, ElementType element, int pos,
			int newSize) {
	   super.elementInserted(source, element, pos, newSize);
	   ListenableShapeVector aContainingShapes = getContainingShapes(source);
	   if (aContainingShapes.size() == pos ) {
			addShape(source, element, newSize, aContainingShapes);
			return;

	   }

		BoundedShape newShape = ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer((ListenableVector<ElementType>) source)).
				createShape((ListenableVector) source, 
						element, 
						newSize - 1, 
						null, aContainingShapes);
		this.getLayoutManager ((ListenableVector<ElementType>)source).updateLabelShape(newShape, (ListenableVector) source, pos);
		
//		if (aContainingShapes.size() == 0) {
////			Dimension retVal = AnimationUtil.modifyForGrowing(newShape);
////			aContainingShapes.add(newShape);
////			AnimationUtil.newSize(newShape, retVal);
//		} else {
			Point newShapeLocation = new Point (newShape.getX(), newShape.getY());
			BoundedShape curShapeAtPos = aContainingShapes.get(pos);
			Point curPos = new Point (curShapeAtPos.getX(), curShapeAtPos.getY());
			List<BoundedShape> successorShapes = getSuccessorShapes(
					aContainingShapes, pos - 1);
			List<Point> successorLocations = getSuccessorLocations(aContainingShapes, pos);
			successorLocations.add(newShapeLocation);
		
			 normalizedToRealLocation(newShape, 
					curPos,
					aContainingShapes, source);
			newShape.setX(curPos.x);
			newShape.setY(curPos.y);
			Dimension newShapeSize = AnimationUtil.modifyForGrowing(newShape);

//				new Dimension(newShape.getWidth(), newShape.getHeight());
//		newShape.setWidth(0);
//		newShape.setHeight(0);
		aContainingShapes.insertElementAt(newShape, 0);
		normalizedToRealLocations(successorShapes, successorLocations, aContainingShapes, source);

		AnimationUtil.moveLocationsAndNewSize(newShape, newShapeSize, successorShapes, successorLocations);
//		}
		getLayoutManager ((ListenableVector<ElementType>)source).updateLabelShapes(aContainingShapes, (ListenableVector) source);
		

	}
	public void elementMoved(Object source, int fromIndex, int toIndex) {
		super.elementMoved(source, fromIndex, toIndex);
//	    ListenableShapeVector aContainingShapes = getContainingShapes(source);
//		BoundedShape shape = aContainingShapes.get(fromIndex);	
		if (fromIndex < toIndex) {
			elementMovedAhead(source, fromIndex, toIndex);
		} else {
			elementMovedBehind(source, fromIndex, toIndex);
		}
		


  }
	
//public void elementMovedBehind(Object source, int fromIndex, int toIndex) {
//		
//	}
 

	public void elementChanged(Object source, ElementType element, int pos) {
		super.elementChanged(source, element, pos);

//		BoundedShape shape = (BoundedShape) visualizer.getVisualization().getShapes().get(0).get(pos);
		BoundedShape shape = (BoundedShape) getContainingShapes(source).get(pos);

		ObjectToShapeTranslator<ElementType> translator = ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer ((ListenableVector<ElementType>)source)).getElementToShapeTranslator();
//		Rectangle newBounds = translator.calculateNewShape(shape, element).getBounds();
		BoundedShape newOEShape = (BoundedShape) translator.calculateNewShape(shape, element);
		AnimationUtil.newOEShapeAttributes(shape, newOEShape);
//		AnimationUtil.newOEShapeAttributes(shape, newOEShape);

//		AnimationUtil.newBounds(shape, newBounds);
//		AnimationUtil.newBoundsModular(shape, newBounds);

//		int newHeight = element * layoutManager.getScaleFactor();
		
//		AnimationUtil.grow(shape, shape.getWidth(), newHeight);

//		AnimationUtil.move(shape, shape.getX(), shape.getY() - 100, true,
//				Color.BLACK, Color.BLACK);
//
//		shape.setHeight(element * layoutManager.getScaleFactor());
//
//		AnimationUtil.move(shape, shape.getX(), layoutManager.getBaseLine()
//				- shape.getHeight(), true, Color.BLACK, Color.BLACK);

	}
	public void userObjectChanged(Object source, Object newVal) {
		super.userObjectChanged(source, newVal);
//		BoundedShape shape = (BoundedShape) visualizer.getVisualization().getUserObjectShape();
		ListenableShapeVector shapes = getContainingShapes(source);
		BoundedShape shape = (BoundedShape) shapes.getTempShape();

//		BoundedShape shape = (BoundedShape) visualizer.getVisualization().getUserObjectShape();

		ObjectToShapeTranslator<ElementType> translator = ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer ((ListenableVector<ElementType>)source)).getElementToShapeTranslator();

		BoundedShape newOEShape = (BoundedShape) translator.calculateNewShape(shape, (ElementType) newVal);
		AnimationUtil.newOEShapeAttributes(shape, newOEShape);
		
	}
	
	public void tempChanged(Object source, Object newVal) {
		super.tempChanged(source, newVal);
//		BoundedShape shape = (BoundedShape) visualizer.getVisualization().getUserObjectShape();
		ListenableShapeVector shapes = getContainingShapes(source);
		BoundedShape shape = (BoundedShape) shapes.getTempShape();

//		BoundedShape shape = (BoundedShape) visualizer.getVisualization().getUserObjectShape();

		ObjectToShapeTranslator<ElementType> translator = ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer ((ListenableVector<ElementType>)source)).getElementToShapeTranslator();

		BoundedShape newOEShape = (BoundedShape) translator.calculateNewShape(shape, (ElementType) newVal);
		AnimationUtil.newOEShapeAttributes(shape, newOEShape);
		
	}

	
//	public static void copy (BoundedShape aTarget, BoundedShape aReference) {
//		aTarget.setBounds(new Rectangle ((aReference.getBounds());
//		
//	}
	public  void elementCopied(BoundedShape fromShape, 
			BoundedShape toShape,
			BoundedShape copiedShape, 
			boolean changeY, 
			boolean changeLabel,
			Object fromParent,
			Object toParent) {
		
//		SimpleShape clonedFromShape = (SimpleShape) Common.deepCopy(fromShape);
//		BoundedShape copiedShape = (BoundedShape) visualizer.getShapes().getCopiedObjectShape();
		Rectangle oldBounds = new Rectangle (copiedShape.getBounds());

//		copiedShape.copy((FancyShape) fromShape) ;
		copiedShape.copy(fromShape) ;

//		copiedShape.setX(copiedShape.getX() + COPY_DELTA_X);
		
//		((AShapeModel) clonedFromShape).initSerializedObject();
//		visualizer.addElement(clonedFromShape);
//		clonedFromShape.setX(0);
//		Rectangle bounds = copiedShape.getBounds();
		Rectangle newBounds = new Rectangle(copiedShape.getBounds());
		newBounds.x = toShape.getX();
		if (changeY) // should this check with shape mapper
		   newBounds.y = toShape.getY();
		AnimationUtil.newBoundsModular(copiedShape, newBounds);
//		AnimationUtil.move(copiedShape, toShape.getX(), copiedShape.getY(), true,
//				Color.BLACK, Color.BLACK);
//		int newHeight = element * layoutManager.getScaleFactor();
//		toShape.setY( layoutManager.getBaseLine()
//				- newHeight);
		Object labelInfo = null;
		if (!changeLabel) {
			labelInfo = getLayoutManager ((ListenableVector<ElementType>)toParent).getLabelInfo(toShape);
			
		}
		toShape.copy((BoundedShape) copiedShape);
		if (!changeLabel) {
			getLayoutManager ((ListenableVector<ElementType>)toParent).restoretLabelInfo(toShape, labelInfo);
		}
//		toShape.setHeight(newHeight);
//		copiedShape.setBounds(0, 0, 0, 0);
		copiedShape.setBounds(oldBounds);

//		visualizer.remove(visualizer.size() -1);

		

//		AnimationUtil.move(shape, shape.getX(), layoutManager.getBaseLine()
//				- shape.getHeight(), true, Color.BLACK, Color.BLACK);


	}
	
	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		super.elementCopied(source, fromIndex, fromNewSize, to, toIndex);
ElementType element= (ElementType) ((List)source).get(fromIndex);
		
		
//		BoundedShape toShape =  (BoundedShape) visualizer.getVisualization().getShapes().get(0).get(toIndex);
		BoundedShape toShape =  (BoundedShape) getContainingShapes(to).get(toIndex);

//		BoundedShape fromShape = (BoundedShape) visualizer.getVisualization().getShapes().get(0).get(fromIndex);
		BoundedShape fromShape = (BoundedShape) getContainingShapes(source).get(fromIndex);

		elementCopied(fromShape, toShape, (BoundedShape) visualizer.getVisualization().getCopiedObjectShape(), false, false, source, to);

	}
	

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {
		super.elementCopied(source, fromIndex, toIndex, newSize);
		ElementType element= (ElementType) ((List)source).get(fromIndex);
		
		
//		BoundedShape toShape =  (BoundedShape) visualizer.getVisualization().getShapes().get(0).get(toIndex);
		BoundedShape toShape =  (BoundedShape) getContainingShapes(source).get(toIndex);

//		BoundedShape fromShape = (BoundedShape) visualizer.getVisualization().getShapes().get(0).get(fromIndex);
		BoundedShape fromShape = (BoundedShape) getContainingShapes(source).get(fromIndex);

		elementCopied(fromShape, toShape, (BoundedShape) visualizer.getVisualization().getCopiedObjectShape(), false, false, source, source);
////		SimpleShape clonedFromShape = (SimpleShape) Common.deepCopy(fromShape);
//		OEShape copiedShape = (OEShape) visualizer.getCopiedObjectShape();
//		Rectangle oldBounds = new Rectangle (copiedShape.getBounds());
//
//		copiedShape.copy((OEShape) fromShape) ;
////		copiedShape.setX(copiedShape.getX() + COPY_DELTA_X);
//		
////		((AShapeModel) clonedFromShape).initSerializedObject();
////		visualizer.addElement(clonedFromShape);
////		clonedFromShape.setX(0);
////		Rectangle bounds = copiedShape.getBounds();
//		Rectangle newBounds = new Rectangle(copiedShape.getBounds());
//		newBounds.x = toShape.getX();
//		AnimationUtil.newBoundsModular(copiedShape, newBounds);
////		AnimationUtil.move(copiedShape, toShape.getX(), copiedShape.getY(), true,
////				Color.BLACK, Color.BLACK);
////		int newHeight = element * layoutManager.getScaleFactor();
////		toShape.setY( layoutManager.getBaseLine()
////				- newHeight);
//		toShape.copy((OEShape) copiedShape);
////		toShape.setHeight(newHeight);
////		copiedShape.setBounds(0, 0, 0, 0);
//		copiedShape.setBounds(oldBounds);
//
////		visualizer.remove(visualizer.size() -1);
//
//		
//
////		AnimationUtil.move(shape, shape.getX(), layoutManager.getBaseLine()
////				- shape.getHeight(), true, Color.BLACK, Color.BLACK);


	}
//public void elementCopiedToUserObject(Object source, int fromIndex) {
//	Integer element= (Integer) ((List)source).get(fromIndex);
//	
//	
//	
//	SimpleShape fromShape = visualizer.get(fromIndex);
//	OEShape copiedShape = (OEShape) visualizer.getUserObjectShape();
//	Rectangle oldBounds = new Rectangle (copiedShape.getBounds());
//
//	copiedShape.copy((OEShape) fromShape) ;
//
//	Rectangle newBounds = new Rectangle(copiedShape.getBounds());
//	newBounds.x = oldBounds.x;
//	AnimationUtil.newBoundsModular(copiedShape, newBounds);
//
//
//		
//	}

public void elementCopiedToUserObject(Object source, int fromIndex) {
	super.elementCopiedToUserObject(source, fromIndex);
	
	Tracer.error("copied to user object not supported");

//	Integer element= (Integer) ((List)source).get(fromIndex);
	
	
//	BoundedShape toShape =  (BoundedShape) visualizer.getVisualization().getUserObjectShape();
	BoundedShape toShape =  (BoundedShape) getContainingShapes(source).getTempShape();

//	BoundedShape fromShape = (BoundedShape) visualizer.getVisualization().getShapes().get(0).get(fromIndex);
	BoundedShape fromShape = (BoundedShape) getContainingShapes(source).get(fromIndex);

	elementCopied(fromShape, toShape, (BoundedShape) visualizer.getVisualization().getCopiedObjectShape(), false, true, source, source);

////	SimpleShape clonedFromShape = (SimpleShape) Common.deepCopy(fromShape);
//	OEShape copiedShape = (OEShape) visualizer.getCopiedObjectShape();
//	Rectangle oldBounds = new Rectangle (copiedShape.getBounds());
//
//	copiedShape.copy((OEShape) fromShape) ;
////	copiedShape.setX(copiedShape.getX() + COPY_DELTA_X);
//	
////	((AShapeModel) clonedFromShape).initSerializedObject();
////	visualizer.addElement(clonedFromShape);
////	clonedFromShape.setX(0);
////	Rectangle bounds = copiedShape.getBounds();
//	Rectangle newBounds = new Rectangle(copiedShape.getBounds());
//	newBounds.x = toShape.getX();
//	AnimationUtil.newBoundsModular(copiedShape, newBounds);
////	AnimationUtil.move(copiedShape, toShape.getX(), copiedShape.getY(), true,
////			Color.BLACK, Color.BLACK);
////	int newHeight = element * layoutManager.getScaleFactor();
////	toShape.setY( layoutManager.getBaseLine()
////			- newHeight);
//	toShape.copy((OEShape) copiedShape);
////	toShape.setHeight(newHeight);
////	copiedShape.setBounds(0, 0, 0, 0);
//	copiedShape.setBounds(oldBounds);
//
////	visualizer.remove(visualizer.size() -1);
//
//	
//
////	AnimationUtil.move(shape, shape.getX(), layoutManager.getBaseLine()
////			- shape.getHeight(), true, Color.BLACK, Color.BLACK);

		
	}
public void elementCopiedToTemp(Object source, int fromIndex) {
	super.elementCopiedToTemp(source, fromIndex);

//	Integer element= (Integer) ((List)source).get(fromIndex);
	
	
//	BoundedShape toShape =  (BoundedShape) visualizer.getVisualization().getUserObjectShape();
	BoundedShape toShape =  (BoundedShape) getContainingShapes(source).getTempShape();

//	BoundedShape fromShape = (BoundedShape) visualizer.getVisualization().getShapes().get(0).get(fromIndex);
	BoundedShape fromShape = (BoundedShape) getContainingShapes(source).get(fromIndex);

	elementCopied(fromShape, toShape, (BoundedShape) visualizer.getVisualization().getCopiedObjectShape(), false, true, source, source);

////	SimpleShape clonedFromShape = (SimpleShape) Common.deepCopy(fromShape);
//	OEShape copiedShape = (OEShape) visualizer.getCopiedObjectShape();
//	Rectangle oldBounds = new Rectangle (copiedShape.getBounds());
//
//	copiedShape.copy((OEShape) fromShape) ;
////	copiedShape.setX(copiedShape.getX() + COPY_DELTA_X);
//	
////	((AShapeModel) clonedFromShape).initSerializedObject();
////	visualizer.addElement(clonedFromShape);
////	clonedFromShape.setX(0);
////	Rectangle bounds = copiedShape.getBounds();
//	Rectangle newBounds = new Rectangle(copiedShape.getBounds());
//	newBounds.x = toShape.getX();
//	AnimationUtil.newBoundsModular(copiedShape, newBounds);
////	AnimationUtil.move(copiedShape, toShape.getX(), copiedShape.getY(), true,
////			Color.BLACK, Color.BLACK);
////	int newHeight = element * layoutManager.getScaleFactor();
////	toShape.setY( layoutManager.getBaseLine()
////			- newHeight);
//	toShape.copy((OEShape) copiedShape);
////	toShape.setHeight(newHeight);
////	copiedShape.setBounds(0, 0, 0, 0);
//	copiedShape.setBounds(oldBounds);
//
////	visualizer.remove(visualizer.size() -1);
//
//	
//
////	AnimationUtil.move(shape, shape.getX(), layoutManager.getBaseLine()
////			- shape.getHeight(), true, Color.BLACK, Color.BLACK);

		
	}
public void elementCopiedFromUserObject(Object source, int toIndex) {
	super.elementCopiedFromUserObject(source, toIndex);
	Tracer.error("element copied from user object Not supported in flat animator");
//	BoundedShape fromShape =  (BoundedShape) visualizer.getVisualization().getUserObjectShape();
	BoundedShape fromShape =  (BoundedShape) getContainingShapes(source).getTempShape();

//	BoundedShape toShape = (BoundedShape) visualizer.getVisualization().getShapes().get(0).get(toIndex);
	BoundedShape toShape = (BoundedShape) getContainingShapes(source).get(toIndex);

	elementCopied(fromShape, toShape, (BoundedShape) visualizer.getVisualization().getCopiedObjectShape(), false, false, source, source);
	
}

public void elementCopiedFromTemp(Object source, int toIndex) {
	super.elementCopiedFromTemp(source, toIndex);
//	BoundedShape fromShape =  (BoundedShape) visualizer.getVisualization().getUserObjectShape();
	BoundedShape fromShape =  (BoundedShape) getContainingShapes(source).getTempShape();

//	BoundedShape toShape = (BoundedShape) visualizer.getVisualization().getShapes().get(0).get(toIndex);
	BoundedShape toShape = (BoundedShape) getContainingShapes(source).get(toIndex);

	elementCopied(fromShape, toShape, (BoundedShape) visualizer.getVisualization().getCopiedObjectShape(), false, false, source, source);
	
}



//	public void elementCopied(Object source, int fromIndex, int fromNewSize,
//			Object to, int toIndex) {
//		super.elementCopied(source, fromIndex, fromNewSize, to, toIndex);
//		// TODO Auto-generated method stub
//
//	}

	

//	public void elementMoved(Object source, int fromIndex, int toIndex) {
//		elementMoved(source, fromIndex, 0, source, toIndex);
//
//
//	}

	
	

	public void elementRemoved(Object source, ElementType element, int newSize, int pos) {
		super.elementRemoved(source, element, newSize, pos);
//		BoundedShape removedShape = (BoundedShape) getContainingShapes(source).get(toIndex);
		elementRemoved(source, pos);

	}

	public void elementReplaced(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementReplaced(Object source, int fromIndex, int newFromSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public synchronized void elementSwapped(Object newParam, int index1,
			int index2) {
		super.elementSwapped(newParam, index1, index2);

		if (index1 <= index2) {
//			BoundedShape s1 = visualizer.getVisualization().getShapes().get(0).get(index1);
//			BoundedShape s2 = visualizer.getVisualization().getShapes().get(0).get(index2);
			BoundedShape s1 = getContainingShapes(newParam).get(index1);
			BoundedShape s2 = getContainingShapes(newParam).get(index2);
			AnimationUtil.swapX(s1, s2);
//
//			int x1 = s1.getX();
//			int x2 = s2.getX();
//
//			AnimationUtil.move(s1, x2, s1.getY(), true, Color.BLACK,
//					Color.BLACK);
//			AnimationUtil.move(s2, x1, s2.getY(), true, Color.BLACK,
//					Color.BLACK);
////			System.out.println("This swap seems to be unncessary and inheritance form AListenableVector seems to be also unncessary");
////			this.swap(index1, index2);
//			visualizer.getVisualization().getShapes().get(0).swap(index1, index2);
			getContainingShapes(newParam).swap(index1, index2);

		} else {
			elementSwapped(newParam, index2, index1);
		}

	}

	public void elementSwapped(Object source, int index1, Object other,
			int index2) {
		// TODO Auto-generated method stub

	}

	public void elementsAdded(Object source,
			Collection<? extends ElementType> element, int newSize) {
		// TODO Auto-generated method stub

	}

//	public void elementsCleared(Object source) {
//		// TODO Auto-generated method stub
//
//	}

	public boolean removeElement(ElementType c) {
		// TODO Auto-generated method stub
		return false;
	}
//	public void elementRead(Object source, Integer element, Integer pos) {
//		
//		// add a method to layout manager to use these shape creators
////		System.out.println("read element:" + pos);
//		
////		BoundedShape pointerShape = (BoundedShape) visualizer.getShapes().getPointerShape();
//		PointerShapeCreator<Integer> pointerShapeCreator = layoutManager.getPointerShapeCreator();
//		
//		BoundedShape pointedShape = null;
//		if (pos == null) {
//			pointedShape = null;
//		}
//		else 
//			if (pos >= 0)
//		
//		pointedShape =  visualizer.getShapes().get(pos);
//		else
//			pointedShape = (BoundedShape) visualizer.getShapes().getUserObjectShape();
////		OEShape pointerShape = (OEShape) visualizer.getPointerShape();
////		PointerShapeCreator<Integer> pointerShapeCreator = layoutManager.getPointerShapeCreator();
//		BoundedShape pointerShape =  visualizer.getShapes().getPointerShape();
//
////		BoundedShape destinationPointerShape =   pointerShapeCreator.calculateNewShape(destinatipointerShape, pointedShape, element);
//		destinationPointerShape =   pointerShapeCreator.calculateNewShape(destinationPointerShape, pointedShape, element);
//
//		if (pos == null)
////			destinationPointerShape.setY(layoutManager.getBaseLine());
//			layoutManager.setPointerShapeInitialCoordinates(destinationPointerShape);
//		AnimationUtil.newOEShapeAttributes(pointerShape, destinationPointerShape);
//	}
	 public BoundedShape getPointedShape(Object source, Object element, Integer pos) {
		 BoundedShape pointedShape = null;
		 if (pos == null) {
				pointedShape = null;
			}
			else 
				if (pos >= 0)
					return getElementShape(source, pos);
			
//			pointedShape =  visualizer.getShapes().get(pos);
			else
//				pointedShape = (BoundedShape) visualizer.getShapes().getUserObjectShape();
				return getUserObjectShape(source);
		 return pointedShape;
	 }

	 public BoundedShape getElementShape(Object source, Integer pos) {
//		return visualizer.getVisualization().getShapes().get(0).get(pos);
		return getContainingShapes(source).get(pos);
			
	 }
	 
//	 public BoundedShape getUserObjectShape() {
////			return (BoundedShape) visualizer.getVisualization().getUserObjectShape();
//			
//			return (BoundedShape) visualizer.getVisualization().getUserObjectShape();
//
//				
//		 }
	 public BoundedShape getUserObjectShape(Object source) {
//			return (BoundedShape) visualizer.getVisualization().getUserObjectShape();
			
			return (BoundedShape) getContainingShapes(source).getTempShape();

				
		 }

	public void userObjectCopiedToTemp(Object source, Object copiedValue) {
		super.userObjectCopiedToTemp(source, copiedValue);
		// TODO Auto-generated method stub
		
	}

	public void tempCopiedToUserObject(Object source, Object copiedValue) {
		super.tempCopiedToUserObject(source, copiedValue);
		// TODO Auto-generated method stub
		
	}
	// maybe need to add another shape to the flat manager
	@Override
	public BoundedShape getParentShape(Object source) {
		return getUserObjectShape(source);
	}

	public void elementCopiedAndInserted(Object source, int fromIndex,
			int toIndex, int newSize) {
		super.elementCopiedAndInserted(source, fromIndex, toIndex, newSize);
		
	}

	

	
	
	

	
	
}
