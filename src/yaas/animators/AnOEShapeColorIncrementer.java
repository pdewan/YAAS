package yaas.animators;

import java.awt.Color;

import shapes.AttributedShape;
import shapes.FlexibleShape;


public class AnOEShapeColorIncrementer extends AnAbstractAtomicRangeBasedObjectIncrementer<FlexibleShape, Color>{

	@Override
	IntermediateRangeBasedValueGenerator<Color> createIntermediateValueGenerator() {
		return new AnIntermediateColorGenerator();
	}

	@Override
	Color getProperty(FlexibleShape object) {
		return object.getColor();
	}

	@Override
	void setProperty(FlexibleShape anObject, Color aNewValue) {
		anObject.setColor(aNewValue);
	}
	

}
