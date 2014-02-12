package yaas.visualizers.collection.flat;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.List;

import bus.uigen.shapes.ListenableShapeVector;
import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.models.VectorMethodsListener;
import yaas.animators.AnimationUtil;
import yaas.common.AListenableVector;
import yaas.common.VestigalListenableVector;
import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.shapemappers.PointerShapeCreator;
import yaas.trappers.EventTrapper;

public class AnIntegerBarChartEventTrapper extends AListenableVector<Integer>
		implements
		EventTrapper<VectorMethodsListener<Integer>, VestigalListenableVector<Integer>>,
		VectorMethodsListener<Integer> {
	
	protected AnIntegerBarChartVisualizer visualizer;
	protected AnIntegerBarChartLayoutManager layoutManager;

	public AnIntegerBarChartEventTrapper(
			AnIntegerBarChartVisualizer visualizer,
			AnIntegerBarChartLayoutManager layoutManager) {
		this.visualizer = visualizer;
		this.layoutManager = layoutManager;
	}
	
	
	private static final long serialVersionUID = 3971272244005861176L;
	private static final int COPY_DELTA_X = 5;


	public void elementAdded(Object source, Integer element, int newSize) {

//		visualizer.add(layoutManager.nextShape(element));
		BoundedShape aShape = layoutManager.nextShape(element, newSize - 1);
		Rectangle bounds = new Rectangle(aShape.getX(), aShape.getY(), aShape.getWidth(), aShape.getHeight());
		aShape.setWidth(0);
		aShape.setHeight(0);
		visualizer.getVisualization().getShapes().get(0).add(aShape);
		AnimationUtil.grow(aShape, bounds.width, bounds.height, false);

	}
public void elementRemoved(Object source, int pos, int newSize) {
	BoundedShape shape = visualizer.getVisualization().getShapes().get(0).get(pos);

	AnimationUtil.grow(shape, 0, 0, false);

		visualizer.getVisualization().getShapes().get(0).remove(pos);


	}

	public void elementChanged(Object source, Integer element, int pos) {

		FlexibleShape shape = (FlexibleShape) visualizer.getVisualization().getShapes().get(0).get(pos);
		
		ObjectToShapeTranslator<Integer> translator = layoutManager.getElementToShapeTranslator();
//		Rectangle newBounds = translator.calculateNewShape(shape, element).getBounds();
		FlexibleShape newOEShape = (FlexibleShape) translator.calculateNewShape(shape, element);
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
//		ListenableShapeVector shapesList = 
		FlexibleShape shape = (FlexibleShape) visualizer.getVisualization().getShapes().get(0).getTempShape();
		ObjectToShapeTranslator<Integer> translator = layoutManager.getElementToShapeTranslator();

		FlexibleShape newOEShape = (FlexibleShape) translator.calculateNewShape(shape, (Integer) newVal);
		AnimationUtil.newOEShapeAttributes(shape, newOEShape);
		
	}
	public void elementCopied(FlexibleShape fromShape, FlexibleShape toShape) {
		
//		SimpleShape clonedFromShape = (SimpleShape) Common.deepCopy(fromShape);
		FlexibleShape copiedShape = (FlexibleShape) visualizer.getVisualization().getCopiedObjectShape();
		Rectangle oldBounds = new Rectangle (copiedShape.getBounds());

		copiedShape.copy((FlexibleShape) fromShape) ;
//		copiedShape.setX(copiedShape.getX() + COPY_DELTA_X);
		
//		((AShapeModel) clonedFromShape).initSerializedObject();
//		visualizer.addElement(clonedFromShape);
//		clonedFromShape.setX(0);
//		Rectangle bounds = copiedShape.getBounds();
		Rectangle newBounds = new Rectangle(copiedShape.getBounds());
		newBounds.x = toShape.getX();
		AnimationUtil.newBoundsModular(copiedShape, newBounds);
//		AnimationUtil.move(copiedShape, toShape.getX(), copiedShape.getY(), true,
//				Color.BLACK, Color.BLACK);
//		int newHeight = element * layoutManager.getScaleFactor();
//		toShape.setY( layoutManager.getBaseLine()
//				- newHeight);
		toShape.copy((FlexibleShape) copiedShape);
//		toShape.setHeight(newHeight);
//		copiedShape.setBounds(0, 0, 0, 0);
		copiedShape.setBounds(oldBounds);

//		visualizer.remove(visualizer.size() -1);

		

//		AnimationUtil.move(shape, shape.getX(), layoutManager.getBaseLine()
//				- shape.getHeight(), true, Color.BLACK, Color.BLACK);


	}
	
	
	

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {
		Integer element= (Integer) ((List)source).get(fromIndex);
		
		
		FlexibleShape toShape =  (FlexibleShape) visualizer.getVisualization().getShapes().get(0).get(toIndex);
		
		FlexibleShape fromShape = (FlexibleShape) visualizer.getVisualization().getShapes().get(0).get(fromIndex);
		elementCopied(fromShape, toShape);
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
//	Integer element= (Integer) ((List)source).get(fromIndex);
	
	
	FlexibleShape toShape =  (FlexibleShape) visualizer.getVisualization().getShapes().get(0).getTempShape();
	
	FlexibleShape fromShape = (FlexibleShape) visualizer.getVisualization().getShapes().get(0).get(fromIndex);
	elementCopied(fromShape, toShape);

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
	FlexibleShape fromShape =  (FlexibleShape) visualizer.getVisualization().getShapes().get(0).getTempShape();
	
	FlexibleShape toShape = (FlexibleShape) visualizer.getVisualization().getShapes().get(0).get(toIndex);
	elementCopied(fromShape, toShape);
	
}



	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementInserted(Object source, Integer element, int pos,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementMoved(Object source, int fromIndex, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	

	public void elementRemoved(Object source, Integer element, int newSize, int pos) {
		// TODO Auto-generated method stub

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

		if (index1 <= index2) {
			BoundedShape s1 = visualizer.getVisualization().getShapes().get(0).get(index1);
			BoundedShape s2 = visualizer.getVisualization().getShapes().get(0).get(index2);
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
			visualizer.getVisualization().getShapes().get(0).swap(index1, index2);
		} else {
			elementSwapped(newParam, index2, index1);
		}

	}

	public void elementSwapped(Object source, int index1, Object other,
			int index2) {
		// TODO Auto-generated method stub

	}

	public void elementsAdded(Object source,
			Collection<? extends Integer> element, int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementsCleared(Object source) {
		// TODO Auto-generated method stub

	}

	public boolean removeElement(Integer c) {
		// TODO Auto-generated method stub
		return false;
	}
	public void elementRead(Object source, Integer element, Integer pos) {
		
		// add a method to layout manager to use these shape creators
//		System.out.println("read element:" + pos);
		
		FlexibleShape pointerShape = (FlexibleShape) visualizer.getVisualization().getPointerShape();
		PointerShapeCreator<Integer> pointerShapeCreator = visualizer.getCompositeLayoutManager().getReadPointerShapeCreator();
		
		FlexibleShape pointedShape = null;
		if (pos == null) {
			pointedShape = null;
		}
		else 
			if (pos >= 0)
		
		pointedShape = (FlexibleShape) visualizer.getVisualization().getShapes().get(0).get(pos);
		else
			pointedShape = (FlexibleShape) visualizer.getVisualization().getShapes().get(0).getTempShape();
//		OEShape pointerShape = (OEShape) visualizer.getPointerShape();
//		PointerShapeCreator<Integer> pointerShapeCreator = layoutManager.getPointerShapeCreator();
		FlexibleShape destinationPointerShape =  (FlexibleShape) pointerShapeCreator.calculateNewShape(pointerShape, pointedShape, element, pos, null, null);
		if (pos == null)
//			destinationPointerShape.setY(layoutManager.getBaseLine());
			layoutManager.setReadPointerShapeInitialCoordinates(destinationPointerShape);
		AnimationUtil.newOEShapeAttributes(pointerShape, destinationPointerShape);
	}
	public void tempChanged(Object source, Object newVal) {
		// TODO Auto-generated method stub
		
	}
	public void elementCopiedToTemp(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		
	}
	public void elementCopiedFromTemp(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		
	}
	public void userObjectCopiedToTemp(Object source, Object copiedValue) {
		// TODO Auto-generated method stub
		
	}
	public void tempCopiedToUserObject(Object source, Object copiedValue) {
		// TODO Auto-generated method stub
		
	}
	public void userObjectRead(Object source, Object readValue) {
		// TODO Auto-generated method stub
		
	}
	public void tempRead(Object source, Object readValue) {
		// TODO Auto-generated method stub
		
	}
	public void elementCopiedAndInserted(Object source, int fromIndex,
			int toIndex, int newSize) {
		// TODO Auto-generated method stub
		
	}
	public void elementCopiedAndInserted(Object source, int fromIndex,
			int fromNewSize, Object to, int toIndex) {
		// TODO Auto-generated method stub
		
	}
	public void pointerChanged(Object source, Integer pointerValue) {
		// TODO Auto-generated method stub
		
	}
	public void pointer2Changed(Object source, Integer pointerValue) {
		// TODO Auto-generated method stub
		
	}
	

	public void userOperationOccured(Object source, Integer aTargetIndex,
			Object anOperation) {
//		String text = elementToString(source, aTargetIndex) + USER_OPERATION + anOperation;
	}
	
}
