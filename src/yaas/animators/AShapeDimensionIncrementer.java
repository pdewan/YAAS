package yaas.animators;

import java.awt.Dimension;
import java.awt.Rectangle;

import shapes.BoundedShape;


public class AShapeDimensionIncrementer extends AnAbstractAtomicRangeBasedObjectIncrementer<BoundedShape, Dimension>{
//	boolean adjustY;
//	public AnInheritingShapeBoundsIncrementer(boolean anAdjustY) {
//		adjustY = anAdjustY;
//	}
//	public AnInheritingShapeBoundsIncrementer() {
//		adjustY = false;
//	}
	@Override
	IntermediateRangeBasedValueGenerator<Dimension> createIntermediateValueGenerator() {
		return new AnIntermediateDimensionGenerator();
	}

	@Override
	Dimension getProperty(BoundedShape object) {
		return new Dimension (object.getWidth(), object.getHeight());
	}

	@Override
	void setProperty(BoundedShape anObject, Dimension aNewValue) {
//		int oldHeight = anObject.getHeight();
//		int newHeight = aNewValue.height;
//		aNewValue.y = (aNewValue.y - (newHeight - oldHeight));		
//		anObject.setWidth(aNewValue.width);
//		anObject.setHeight(aNewValue.height);
		Rectangle newBounds = new Rectangle (anObject.getX(), anObject.getY(), aNewValue.width, aNewValue.height);
		anObject.setBounds(newBounds);
	}
	

}
