package yaas.animators;

public interface IntermediateListBasedValueGenerator<ValueType> extends IntermediateAbsoluteValueGenerator<ValueType>{
//	ValueType getInitialValue();
	void set(ValueType[] aValueList);
//	boolean getTraverseForward();
//	void setTraverseForward(boolean newVal);
//	ValueType getFinalValue();
//	ValueType getValue(int aStepNumber);	

}
