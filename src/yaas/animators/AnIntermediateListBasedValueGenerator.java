package yaas.animators;

import shapes.AttributedShape;

public class AnIntermediateListBasedValueGenerator<ValueType> 
extends AnAbstractIntermediateValueGenerator<ValueType>
implements IntermediateListBasedValueGenerator<ValueType>{
//	double finalValue;
//	double initialValue;
//	int maxSteps;
//	double increment;
//	boolean traverseForward = true;
//
//	public boolean getTraverseForward() {
//		return traverseForward;
//	}
//	public void setTraverseForward(boolean newVal) {
//		traverseForward = newVal;
//		
//	}
	ValueType[] valueList;

	public ValueType getFinalValue() {
		if (traverseForward)
			return  valueList[valueList.length] ;
		else
			return valueList[0];
	}
	public ValueType getInitialValue() {
		if (traverseForward)
			return valueList[0];
		else
			return  valueList[valueList.length] ;

	}
	
	public ValueType getValue(int aStepNumber) {
		if (traverseForward)
			return valueList[aStepNumber];
		else
			return  valueList[valueList.length - 1 - aStepNumber ] ;

		
	}
	public void set(ValueType[] aValueList) {
		valueList = aValueList;
//		traverseForward = aTraverseForward;
		numSteps = aValueList.length;
		
	}
//	boolean circulate;
//	public boolean isCirculate() {
//		return circulate;
//	}
//	public void setCirculate(boolean newVal) {
//		circulate = newVal;
//	}
	
	
	

	

}
