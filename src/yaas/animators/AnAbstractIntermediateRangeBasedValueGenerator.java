package yaas.animators;

import shapes.AttributedShape;

public abstract class AnAbstractIntermediateRangeBasedValueGenerator<ValueType>
				extends AnAbstractIntermediateValueGenerator<ValueType>
				implements IntermediateRangeBasedValueGenerator<ValueType>{
	ValueType finalValue;
	ValueType initialValue;
	int maxSteps;
	ValueType increment;
	boolean decreasing;

	public ValueType getFinalValue() {
		return finalValue;
	}
	public ValueType getInitialValue() {
		return initialValue;
	}
	
	
	public void set(ValueType anInitialValue, ValueType aFinalValue, int aMaxSteps) {
		initialValue = anInitialValue;
		finalValue = aFinalValue;
		maxSteps = aMaxSteps;
//		increment = aMaxSteps > 0? (finalValue - initialValue) / aMaxSteps:0;
//		decreasing = finalValue < initialValue;

	}
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
