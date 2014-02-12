package yaas.animators;

import java.awt.Rectangle;

import shapes.BoundedShape;


public class AShapeHeightIncrementer extends AnAbstractAtomicRangeBasedObjectIncrementer<BoundedShape, Integer>{
	@Override
	IntermediateRangeBasedValueGenerator<Integer> createIntermediateValueGenerator() {
		return new AnIntermediateIntegerGenerator();
	}

	@Override
	Integer getProperty(BoundedShape object) {
		return object.getHeight();
	}

	@Override
	void setProperty(BoundedShape anObject, Integer aNewValue) {
		anObject.setHeight(aNewValue);
	}
	

}
