package yaas.animators;

public interface IntermediateIncrementBasedValueGenerator<ValueType> extends IntermediateValueGenerator<ValueType>{
//	ValueType getInitialValue();
	void set(ValueType anIncrement, int aNumSteps);
	void invertIncrement();

//	ValueType getFinalValue();
//	ValueType getValue(int aStepNumber);	

}
