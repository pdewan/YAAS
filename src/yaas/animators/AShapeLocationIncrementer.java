package yaas.animators;

import java.awt.Point;
import java.awt.Rectangle;

import shapes.BoundedShape;


public class AShapeLocationIncrementer extends AnAbstractAtomicRangeBasedObjectIncrementer<BoundedShape, Point>{
//	boolean adjustY;
//	public AnInheritingShapeBoundsIncrementer(boolean anAdjustY) {
//		adjustY = anAdjustY;
//	}
//	public AnInheritingShapeBoundsIncrementer() {
//		adjustY = false;
//	}
	@Override
	IntermediateRangeBasedValueGenerator<Point> createIntermediateValueGenerator() {
		return new AnIntermediateLocationGenerator();
	}

	@Override
	Point getProperty(BoundedShape object) {
		return new Point (object.getX(), object.getY());
	}

	@Override
	void setProperty(BoundedShape anObject, Point aNewValue) {
//		int oldHeight = anObject.getHeight();
//		int newHeight = aNewValue.height;
//		aNewValue.y = (aNewValue.y - (newHeight - oldHeight));	
		Rectangle newBounds = new Rectangle (aNewValue.x, aNewValue.y, anObject.getWidth(), anObject.getHeight());
		anObject.setBounds(newBounds);
	}
	

}
