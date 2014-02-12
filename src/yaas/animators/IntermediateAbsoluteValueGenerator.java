package yaas.animators;

public interface IntermediateAbsoluteValueGenerator<ValueType> extends   IntermediateValueGenerator<ValueType> {
//	ValueType getInitialValue();
//	void set(ValueType anInitialValue, ValueType aFinalValue, int aMaxSteps);
//	ValueType getFinalValue();
	ValueType getValue(int aStepNumber);
	int getNumSteps();
//	int getMaxCycles();
//	void setMaxCycles(int newVal);
//	int getMinCycles();
//	void setMinCycles(int newVal);
//	boolean isCirculate();
//	void setCirculate(boolean newValue);

	public boolean getTraverseForward() ;
	public void setTraverseForward(boolean newVal);
//	void invertIncrement();


}
