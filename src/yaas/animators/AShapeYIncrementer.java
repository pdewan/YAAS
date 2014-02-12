package yaas.animators;

import java.awt.Rectangle;

import shapes.BoundedShape;


public class AShapeYIncrementer extends AnAbstractAtomicRangeBasedObjectIncrementer<BoundedShape, Integer>{
	@Override
	IntermediateRangeBasedValueGenerator<Integer> createIntermediateValueGenerator() {
		return new AnIntermediateIntegerGenerator();
	}

	@Override
	Integer getProperty(BoundedShape object) {
		return object.getY();
	}

	@Override
	void setProperty(BoundedShape anObject, Integer aNewValue) {
		anObject.setY(aNewValue);
	}
	

}
