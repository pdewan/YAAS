package yaas.animators;

public interface IntermediateRangeBasedValueGenerator<ValueType> extends IntermediateAbsoluteValueGenerator<ValueType>{
//	ValueType getInitialValue();
	void set(ValueType anInitialValue, ValueType aFinalValue, int aMaxSteps);
	void invertIncrement();

//	ValueType getFinalValue();
//	ValueType getValue(int aStepNumber);	

}
