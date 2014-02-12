package yaas.animators;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.Rotatable;


public class AnIncrementBasedShapeAngleIncrementer extends AnAbstractAtomicIncrementBasedObjectIncrementer<Rotatable, Double>{
//	@Override
//	IntermediateRangeBasedValueGenerator<Double> createIntermediateValueGenerator() {
//		return new AnIntermediateDoubleGenerator();
//	}

//	@Override
//	Double getProperty(Rotatable object) {
//		return object.getAngle();
//	}

	@Override
	void setProperty(Rotatable anObject, Double aNewValue) {
		anObject.rotate(aNewValue);
	}

	@Override
	IntermediateAbsoluteValueGenerator<Double> createIntermediateValueGenerator() {
		// TODO Auto-generated method stub
		return new AnIntermediateIncrementBasedDoubleGenerator();
	}
	 

	

	

	
	

}
