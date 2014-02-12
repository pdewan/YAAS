package yaas.shapemappers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.models.ListenableVector;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.shapes.AnOvalModel;

public  abstract class AbstractPointerShapeManager<DataType> implements PointerShapeCreator<DataType> {
	public static final Color DEFAULT_POINTED_COLOR = AttributeNames.DEFAULT_MAIN_WINDOW_COLOR; 
	public static final Color DEFAULT_COMPOSITE_OBJECT_POINTED_COLOR =AttributeNames.LIGHT_BLUE;

	public static final int DEFAULT_POINTER_HEIGHT = 10;
	public static final int DEFAULT_POINTER_WIDTH = 10;
	public static final int DEFAULT_MIN_POINTER_WIDTH = 5;
	
//	public static final int DEFAULT_POINTER_WIDTH = 10;
	int pointerHeight = DEFAULT_POINTER_HEIGHT;
	int pointerWidth = DEFAULT_POINTER_WIDTH;
	int minPointerWidth = DEFAULT_MIN_POINTER_WIDTH ;
	
	public abstract BoundedShape createPointerShape();
	boolean center;
	boolean adjustSize = true;
	
	Dimension sizePointerShape(BoundedShape aPointedShape,
			DataType aPointedValue) {
		
		return new Dimension (pointerWidth(aPointedShape, aPointedValue), pointerHeight(aPointedShape, aPointedValue));
		
	}
	
	protected int pointerWidth(BoundedShape aPointedShape,
			DataType aPointedValue) {
	
		if (aPointedShape == null)
			return 0;
		int maxValue = Math.min(pointerWidth, aPointedShape.getWidth());
		int minValue = Math.max(maxValue,  minPointerWidth);
		return minValue;
		
//				aPointedShape.getWidth();

//		return aPointedShape.getWidth();
	}
	
	protected int pointerHeight(BoundedShape aPointedShape,
			DataType aPointedValue) {
		if (aPointedShape == null)
			return 0;
		return pointerHeight;
	}
	Point positionPointerShapeLowerLeft(BoundedShape aPointerShape, 
			BoundedShape aPointedShape,
			DataType aPointedValue ) {
		return new Point (
				aPointedShape.getX(), 
				aPointedShape.getY() + aPointedShape.getHeight());
	}
	
	Point positionPointerShapeUpperLeft(BoundedShape aPointerShape, 
			BoundedShape aPointedShape,
			DataType aPointedValue ) {
		return new Point (
				aPointedShape.getX(), 
				aPointedShape.getY()  - 
				aPointerShape.getHeight());
	}
	
	Point positionPointerShapeUpperRight(BoundedShape aPointerShape, 
			BoundedShape aPointedShape,
			DataType aPointedValue ) {
		return new Point (
				aPointedShape.getX() + 
				aPointedShape.getWidth() - 
				aPointerShape.getWidth(), 
				aPointedShape.getY()  - 
				aPointerShape.getHeight());
	}
	
	Point positionPointerShapeUpperCenter(BoundedShape aPointerShape, 
			BoundedShape aPointedShape,
			DataType aPointedValue ) {
		return new Point (
				aPointedShape.getX() + 
				aPointedShape.getWidth()/2 - 
				aPointerShape.getWidth()/2, 
				aPointedShape.getY()  - 
				aPointerShape.getHeight());
	}
	
	Point positionPointerShapeBeyondUpperRight(BoundedShape aPointerShape, 
			BoundedShape aPointedShape,
			DataType aPointedValue ) {
		return new Point (
				aPointedShape.getX() + 
				aPointedShape.getWidth(), 
				aPointedShape.getY()  - 
				aPointerShape.getHeight());
	}
	
	Point positionPointerShapeLowerRight(BoundedShape aPointerShape, 
			BoundedShape aPointedShape,
			DataType aPointedValue ) {
		return new Point (
				aPointedShape.getX() + 
				aPointedShape.getWidth() - 
				aPointerShape.getWidth(), 
				aPointedShape.getY() + 
				aPointedShape.getY() - 
				aPointerShape.getHeight());
	}
	
	Point positionPointerShape(BoundedShape aPointerShape, 
			BoundedShape aPointedShape,
			DataType aPointedValue,
			RectangleEnds aPosition) {
		if (aPointedShape == null) return new Point(0, 0);
		if (aPosition == null) aPosition = RectangleEnds.LowerLeft;
		switch (aPosition) {
		case LowerLeft:
			return positionPointerShapeLowerLeft(aPointerShape, aPointedShape, aPointedValue);
		case LowerRight:
			return positionPointerShapeLowerRight(aPointerShape, aPointedShape, aPointedValue);
		case UpperLeft:
			return positionPointerShapeUpperLeft(aPointerShape, aPointedShape, aPointedValue);
		case UpperRight:
			return positionPointerShapeUpperRight(aPointerShape, aPointedShape, aPointedValue);
		case UpperCenter:
			return positionPointerShapeUpperCenter(aPointerShape, aPointedShape, aPointedValue);
		case BeyondUpperRight:
			return positionPointerShapeBeyondUpperRight(aPointerShape, aPointedShape, aPointedValue);
			
		}
		return null;
//		int x = 0;
//		if (center) {
//		 x = aPointedShape.getX() + aPointedShape.getWidth()/2 - aPointerShape.getWidth()/2;
//		} else {
//			x = aPointedShape.getX();
//		}
//		int y = aPointedShape.getY() + aPointedShape.getHeight();
//
//		return new Point (x, y);
		
	}
	
	public BoundedShape toInvisiblePointedShape(BoundedShape aPointerShape, BoundedShape aPointedShape,
			DataType aPointedValue) {
//		BoundedShape retVal = createPointerShape();
		BoundedShape retVal = aPointerShape;
//		if (retVal instanceof AttributedShape) {
//		((AttributedShape) retVal).setColor(DEFAULT_POINTED_COLOR);		
//		((AttributedShape) retVal).setFilled(true);
//		}
//		Point position = positionPointerShape(retVal, aPointedShape, aPointedValue);
//		retVal.setX(position.x);
//		retVal.setY(position.y);
		retVal.setWidth(0);
		retVal.setHeight(0);
		
		return retVal;
	}
	 
	

	public BoundedShape toNewPointerShape(BoundedShape aPointedShape,
			DataType aPointedValue, RectangleEnds anEndPoint) {
		BoundedShape retVal = createPointerShape();
		if (retVal instanceof AttributedShape) {
			((AttributedShape) retVal).setColor(DEFAULT_POINTED_COLOR);
//			if (aPointedValue instanceof ListenableVector)
//				((AttributedShape) retVal).setColor(DEFAULT_USER_OBJECT_POINTED_COLOR);
//			else				
//			     ((AttributedShape) retVal).setColor(DEFAULT_POINTED_COLOR);		
			((AttributedShape) retVal).setFilled(true);
			}
//		Point position = positionPointerShape(aPointedShape, aPointedValue);
//		Dimension size = sizePointerShape(aPointedShape, aPointedValue);
//		retVal.setWidth(size.width);
//		retVal.setHeight(size.height);
		retVal =		toInvisiblePointedShape(retVal, aPointedShape, aPointedValue);
		Point position = positionPointerShape(retVal, aPointedShape, aPointedValue, anEndPoint);
		retVal.setX(position.x);
		retVal.setY(position.y);

//		retVal.setBounds(new Rectangle (position.x, position.y, size.width, size.height));
		return retVal;
	}
	
	Color getColor (BoundedShape aPointerShape, BoundedShape aPointedShape, DataType aPointedValue, Integer aPos) {
		Color retVal = null;
		if ( (aPointedValue == null && aPos != null && aPos >= 0 ) // probably a listenable vector
			|| (aPointedValue instanceof ListenableVector))
			
			return DEFAULT_COMPOSITE_OBJECT_POINTED_COLOR;
		
		if (aPointedShape instanceof AttributedShape)
			retVal = ((AttributedShape) aPointedShape).getColor();
		if (retVal == null)
			retVal = DEFAULT_POINTED_COLOR;
		return retVal;
		
		
		
//		
//		if (aPos == -1) // make this a constant!
//			return DEFAULT_USER_OBJECT_POINTED_COLOR;
//		else				
//		     return DEFAULT_POINTED_COLOR;	
	}
	
	public BoundedShape calculateNewShape(BoundedShape aPointerShape, BoundedShape aPointedShape, DataType aPointedValue, Integer aPos, RectangleEnds anEndPoint, Object aPointerValue) {
		if (adjustSize) {
		Dimension size = sizePointerShape(aPointedShape, aPointedValue);
		aPointerShape.setWidth(size.width);
		aPointerShape.setHeight(size.height);
		}
		Point location =positionPointerShape(aPointerShape, aPointedShape, aPointedValue, anEndPoint);
		aPointerShape.setX(location.x);
		aPointerShape.setY(location.y);
		((AttributedShape) aPointerShape).setColor(getColor(aPointerShape, aPointedShape, aPointedValue, aPos));
//		if (aPos == -1) // make this a constant!
//			((AttributedShape) aPointerShape).setColor(DEFAULT_USER_OBJECT_POINTED_COLOR);
//		else				
//		     ((AttributedShape) aPointerShape).setColor(DEFAULT_POINTED_COLOR);		
//		BoundedShape retVal = toInvisiblePointedShape(aPointerShape, aPointedShape, aPointedValue);

//		retVal.setBounds(new Rectangle (position.x, position.y, size.width, size.height));
		return aPointerShape;
	}
	
	public boolean adjustSize() {
		return adjustSize;
	}
	public void setAdjustSize(boolean newVal) {
		adjustSize = newVal;
	}


}