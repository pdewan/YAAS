package yaas.animators;

import shapes.AttributedShape;

public abstract class AnAbstractIntermediateValueGenerator<ValueType> 
	implements IntermediateAbsoluteValueGenerator<ValueType>{
	
	protected int numSteps;
	public int getNumSteps() {
		return numSteps;
	}
	boolean traverseForward = true;

	public boolean getTraverseForward() {
		return traverseForward;
	}
	public void setTraverseForward(boolean newVal) {
		traverseForward = newVal;
		
	}

//
//	boolean circulate;
//	public boolean isCirculate() {
//		return circulate;
//	}
//	public void setCirculate(boolean newVal) {
//		circulate = newVal;
//	}
//	protected int maxCycles;
//	protected int minCycles;
//	public int getMaxCycles() {
//		return maxCycles;
//		
//	}
//	public void setMaxCycles(int newVal) {
//		maxCycles = newVal;
//	}
//	public int getMinCycles() {
//		return minCycles;
//	}
//	public void setMinCycles(int newVal) {
//		minCycles = newVal;
//	}
//	
	
	

	

}
