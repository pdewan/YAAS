package yaas.animators;

import shapes.AttributedShape;

public class AnIntermediateIntegerGenerator 
	extends AnAbstractIntermediateRangeBasedValueGenerator<Integer> 

	implements IntermediateRangeBasedValueGenerator<Integer>{
//	int finalValue;
//	int initialValue;
//	int maxSteps;
//	double increment;
//	boolean decreasing;
//
//	public Integer getFinalValue() {
//		return finalValue;
//	}
//	public Integer getInitialValue() {
//		return initialValue;
//	}
	
	public void set(Integer anInitialValue, Integer aFinalValue, int aMaxSteps) {
		super.set(anInitialValue, aFinalValue, aMaxSteps);
//		initialValue = anInitialValue;
//		finalValue = aFinalValue;
//		maxSteps = aMaxSteps;
		increment = aMaxSteps > 0?(finalValue - initialValue) / aMaxSteps : 0;
		decreasing = finalValue < initialValue;
		numSteps = increment > 0? (int) ((finalValue - initialValue)/increment):0;


	}
	public Integer getValue(int aStepNumber) {
		int nextValue = (int) (initialValue + (increment * (aStepNumber + 1)));
		if (decreasing)
			return Math.max(finalValue, nextValue) ;
		else
		return Math.min(finalValue, nextValue );
	}
//	boolean circulate;
//	public boolean isCirculate() {
//		return circulate;
//	}
//	public void setCirculate(boolean newVal) {
//		circulate = newVal;
//	}
//	@Override
	public void invertIncrement() {
		// TODO Auto-generated method stub
		increment = - increment;
	}
	
	
	

	

}
