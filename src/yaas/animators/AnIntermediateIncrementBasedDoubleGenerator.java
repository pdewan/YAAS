package yaas.animators;

import shapes.AttributedShape;

public class AnIntermediateIncrementBasedDoubleGenerator
			extends AnAbstractIntermediateIncrementBasedValueGenerator<Double> {
//	double finalValue;
//	double initialValue;
//	int maxSteps;
//	double increment;
//	boolean decreasing;

//	public Double getFinalValue() {
//		return finalValue;
//	}
//	public Double getInitialValue() {
//		return initialValue;
//	}
	
//	public void set(Double anIncrement, int aNumSteps) {
//		super.set(anInitialValue, aFinalValue, aMaxSteps);
////		initialValue = anInitialValue;
////		finalValue = aFinalValue;
////		maxSteps = aMaxSteps;
//		increment = aMaxSteps > 0? (finalValue - initialValue) / aMaxSteps:0;
//		decreasing = finalValue < initialValue;
//		numSteps = increment > 0? (int) ((finalValue - initialValue)/increment):0;
//
////		numSteps = increment > 0? (int) Math.round((finalValue - initialValue)/increment):0;
//
//	}
//	public Double getValue(int aStepNumber) {
//		return increment;
//	}
//	boolean circulate;
//	public boolean isCirculate() {
//		return circulate;
//	}
//	public void setCirculate(boolean newVal) {
//		circulate = newVal;
//	}
	public void invertIncrement() {
		// TODO Auto-generated method stub
		 increment = - increment;
	}
//	public Double getValue(int aStepNumber) {
////		int nextValue = (int) (initialValue + (increment * (aStepNumber + 1)));
////		if (decreasing)
////			return Math.max(finalValue, nextValue) ;
////		else
////		return Math.min(finalValue, nextValue );
//	}
//	public Double getInitialValue() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public Double getFinalValue() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public Double getValue(int aStepNumber) {
//		// TODO Auto-generated method stub
//		return previousValue + increment;
//	}

//	public void set(Double anInitialValue, Double aFinalValue, int aNumSteps) {
//		// TODO Auto-generated method stub
//		
//	}

//	public Double getInitialValue() {
//		return null;
//	}
//
//	public Double getFinalValue() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Double getValue(int aStepNumber) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public Double getValue(int aStepNumber) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public Double getInitialValue() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Double getFinalValue() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Double getValue(int aStepNumber) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void set(Double anInitialValue, Double aFinalValue, int aMaxSteps) {
//		// TODO Auto-generated method stub
//		
//	}
	
	
	

	

}
