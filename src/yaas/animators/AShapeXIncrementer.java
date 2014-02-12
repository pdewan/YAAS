package yaas.animators;

import shapes.AttributedShape;
import shapes.BoundedShape;


public class AShapeXIncrementer extends AnAbstractAtomicRangeBasedObjectIncrementer<BoundedShape, Integer>{
	@Override
	IntermediateRangeBasedValueGenerator<Integer> createIntermediateValueGenerator() {
		return new AnIntermediateIntegerGenerator();
	}

	@Override
	Integer getProperty(BoundedShape object) {
		return object.getX();
	}

	@Override
	void setProperty(BoundedShape anObject, Integer aNewValue) {
		anObject.setX(aNewValue);
	}
	

}
