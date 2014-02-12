package yaas.animators;

import java.awt.Rectangle;

import shapes.BoundedShape;


public class AShapeBoundsIncrementer extends AnAbstractAtomicRangeBasedObjectIncrementer<BoundedShape, Rectangle>{
//	boolean adjustY;
//	public AnInheritingShapeBoundsIncrementer(boolean anAdjustY) {
//		adjustY = anAdjustY;
//	}
//	public AnInheritingShapeBoundsIncrementer() {
//		adjustY = false;
//	}
	@Override
	IntermediateRangeBasedValueGenerator<Rectangle> createIntermediateValueGenerator() {
		return new AnIntermediateBoundsGenerator();
	}

	@Override
	Rectangle getProperty(BoundedShape object) {
		return object.getBounds();
	}

	@Override
	void setProperty(BoundedShape anObject, Rectangle aNewValue) {
//		int oldHeight = anObject.getHeight();
//		int newHeight = aNewValue.height;
//		aNewValue.y = (aNewValue.y - (newHeight - oldHeight));		
		anObject.setBounds(aNewValue);
	}
	

}
