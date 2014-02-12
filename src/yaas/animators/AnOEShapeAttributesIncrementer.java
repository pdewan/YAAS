package yaas.animators;

import java.awt.Color;

import shapes.AttributedShape;
import shapes.BoundedShape;


public class AnOEShapeAttributesIncrementer extends AnAbstractAtomicRangeBasedObjectIncrementer<BoundedShape, BoundedShape>{

	@Override
	IntermediateRangeBasedValueGenerator<BoundedShape> createIntermediateValueGenerator() {
		return new AnIntermediateOEShapeAttributesGenerator();
	}

	@Override
	BoundedShape getProperty(BoundedShape object) {
		return object;
	}

	@Override
	void setProperty(BoundedShape anObject, BoundedShape aNewValue) {
		anObject.copy(aNewValue);
	}
	

}
