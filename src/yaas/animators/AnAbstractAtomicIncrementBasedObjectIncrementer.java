package yaas.animators;

import java.awt.Rectangle;

import shapes.AttributedShape;


public abstract class AnAbstractAtomicIncrementBasedObjectIncrementer<ObjectType, PropertyType>
//	extends AnAbstractObjectIncrementer
	extends AnAbstractValueGeneratorBasedAtomicObjectIncrementer<ObjectType, PropertyType>
//	extends AnAbstractObjectIncrementer

	implements IncrementBasedObjectIncrementer<ObjectType, PropertyType>{
	int numSteps;
	PropertyType increment;
//	boolean traverseForward;
//	ObjectType object;
//	PropertyType initialValue, finalValue;
//	PropertyType previousValue;

//	IntermediateRangeBasedValueGenerator<PropertyType> propertyGenerator;
//	IntermediateRangeBasedValueGenerator propertyGenerator() {
//		return (IntermediateRangeBasedValueGenerator) propertyGenerator;
//	}
//	public AnAbstractAtomicRangeBasedObjectIncrementer() {
//		propertyGenerator = createIntermediateValueGenerator();
//		numSteps = propertyGenerator.getNumSteps();
//	}
	
//	abstract IntermediateRangeBasedValueGenerator<PropertyType> createIntermediateValueGenerator();

//	public ObjectType getObject() {
//		return object;
//	}
//
	PropertyType nextValue(int aStepNumber) {
//		return increment;
		return propertyGenerator.getValue(aStepNumber);

     }
	public  boolean done(PropertyType aPreviousValue, PropertyType aNextValue, int aStepNumber, int aMaxSteps) {
		return  aStepNumber >= aMaxSteps;

	}
//	@Override
//	IntermediateValueGenerator<PropertyType> createIntermediateValueGenerator() {
//		// TODO Auto-generated method stub
//	
//	}
	public void init(ObjectType anObject, PropertyType anIncrement, int aNumSteps) {
		super.init(anObject);
		numSteps = aNumSteps;
		increment = anIncrement;
//		initialValue = anInitialValue;
	}

//	public void init(ObjectType anObject, PropertyType anIncrement, int aNumSteps) {
//		super.init(anObject);
//		numSteps = aNumSteps;
//		increment = anIncrement;
//	}
//	@Override
//	void setTraverseForward(boolean newVal) {
//		traverseForward = newVal;
//	}


	
//	abstract PropertyType getProperty(ObjectType object);
//	
//	abstract void setProperty(ObjectType anObject, PropertyType aNewValue);

	public void animationStart(int aMaxSteps) {
//		propertyGenerator().set(getProperty(object), finalValue, aMaxSteps);
//		setNumSteps();
	}
	 PropertyType getProperty(ObjectType object) {
		 return null;
	 }


//	public boolean nextStep(int aStepNumber, int aMaxSteps) {		
//		PropertyType nextValue =  propertyGenerator.getValue(aStepNumber);
//		if ( nextValue.equals(previousValue))
//			return true;
//		setProperty(object, nextValue);
//		previousValue = nextValue;		
//		return false;
//	}
//	
//	public boolean cycle() {
//		return false;
//		
//	}
	
//	boolean circulate;
//	public boolean isCirculate() {
//		return circulate;
//	}
//	public void setCirculate(boolean newVal) {
//		circulate = newVal;
//	}
	
	
	public void animationEnd(int aNumSteps, int aMaxSteps) {
		
//		if (!getProperty(object).equals(finalValue))		
//		   setProperty(object, finalValue);	
	}
	

}
