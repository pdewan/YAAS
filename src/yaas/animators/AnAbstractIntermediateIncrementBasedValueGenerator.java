package yaas.animators;

import shapes.AttributedShape;

public abstract class AnAbstractIntermediateIncrementBasedValueGenerator<ValueType>
//				extends AnAbstractIntermediateRangeBasedValueGenerator<ValueType>
extends AnAbstractIntermediateValueGenerator<ValueType>
				implements IntermediateIncrementBasedValueGenerator<ValueType>{
	
//	ValueType increment, initialValue, finalValue;
	ValueType increment;
//	boolean decreasing;
//	int numSteps;

//	public void set( ValueType anIncrement, int aNumSteps) {
//		increment = anIncrement;
//		numSteps = aNumSteps;
//	}
	public void set(ValueType anIncrement, int aNumSteps) {
		increment = anIncrement;
		numSteps = aNumSteps;
//		initialValue = anInitialValue;
//		finalValue = aFinalValue;
	}

	
	public ValueType getIncrement() {
		return increment;
	}
	public ValueType getValue(int aStepNumber) {
		return increment;
//		int nextValue = (int) (initialValue + (increment * (aStepNumber + 1)));
//		if (decreasing)
//			return Math.max(finalValue, nextValue) ;
//		else
//		return Math.min(finalValue, nextValue );
	}
//	public ValueType getInitialValue() {
//		return null;
//	}
//
//	public ValueType getFinalValue() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

	
	
//	public void set(ValueType anInitialValue, ValueType aFinalValue, int aMaxSteps) {
//		initialValue = anInitialValue;
//		finalValue = aFinalValue;
//		maxSteps = aMaxSteps;
////		increment = aMaxSteps > 0? (finalValue - initialValue) / aMaxSteps:0;
////		decreasing = finalValue < initialValue;
//
//	}
//	public ValueType getValue(int aStepNumber) {
//		int nextValue = (int) (initialValue + (increment * (aStepNumber + 1)));
//		if (decreasing)
//			return Math.max(finalValue, nextValue) ;
//		else
//		return Math.min(finalValue, nextValue );
//	}
//	boolean circulate;
//	public boolean isCirculate() {
//		return circulate;
//	}
//	public void setCirculate(boolean newVal) {
//		circulate = newVal;
//	}
//	
	
	

	

}
