package yaas.animators;

import java.awt.Rectangle;

import shapes.BoundedShape;


public class AShapeWidthIncrementer extends AnAbstractAtomicRangeBasedObjectIncrementer<BoundedShape, Integer>{
	@Override
	IntermediateRangeBasedValueGenerator<Integer> createIntermediateValueGenerator() {
		return new AnIntermediateIntegerGenerator();
	}

	@Override
	Integer getProperty(BoundedShape object) {
		return object.getWidth();
	}

	@Override
	void setProperty(BoundedShape anObject, Integer aNewValue) {
		anObject.setWidth(aNewValue);
	}
	

}
