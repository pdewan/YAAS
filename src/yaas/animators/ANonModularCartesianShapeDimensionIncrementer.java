package yaas.animators;

import java.awt.Rectangle;

import shapes.BoundedShape;


public class ANonModularCartesianShapeDimensionIncrementer extends AShapeBoundsIncrementer{

	@Override
	IntermediateRangeBasedValueGenerator<Rectangle> createIntermediateValueGenerator() {
		return new AnIntermediateCartesianDimensionGenerator();
	}

	

}
