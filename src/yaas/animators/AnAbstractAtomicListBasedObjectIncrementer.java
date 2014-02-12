package yaas.animators;

import java.awt.Rectangle;

import shapes.AttributedShape;


public abstract class AnAbstractAtomicListBasedObjectIncrementer<ObjectType, PropertyType>
//	extends AnAbstractObjectIncrementer
	extends AnAbstractValueGeneratorBasedAtomicObjectIncrementer<ObjectType, PropertyType>
//	extends AnAbstractObjectIncrementer

	implements ListBasedObjectIncrementer<ObjectType, PropertyType>{
//	ObjectType object;
//	PropertyType finalValue;
//	PropertyType previousValue;
	PropertyType[] valueRange;
//	boolean traverseForward;
//	int lastStepNumber = -1;
//	boolean toggleDirection = true;

	
	
	
	
	IntermediateAbsoluteValueGenerator<PropertyType> createIntermediateValueGenerator() {
		return new AnIntermediateListBasedValueGenerator();
	}


//	IntermediateRangeBasedValueGenerator<PropertyType> propertyGenerator;
	IntermediateListBasedValueGenerator<PropertyType> propertyGenerator() {
		return (IntermediateListBasedValueGenerator) propertyGenerator;
	}
//	public AnAbstractAtomicRangeBasedObjectIncrementer() {
//		propertyGenerator = createIntermediateValueGenerator();
//		numSteps = propertyGenerator.getNumSteps();
//	}
	
//	abstract IntermediateRangeBasedValueGenerator<PropertyType> createIntermediateValueGenerator();

//	public ObjectType getObject() {
//		return object;
//	}
//
	public void init(ObjectType anObject, 
			PropertyType[] aValueRange) {
		super.init(anObject);

		valueRange = aValueRange;
		traverseForward = true;
		finalValue = aValueRange[aValueRange.length - 1];
		lastStepNumber = - 1;
		
		
	}
//	abstract PropertyType getProperty(ObjectType object);
//	
//	abstract void setProperty(ObjectType anObject, PropertyType aNewValue);

	public void animationStart(int aMaxSteps) {
		propertyGenerator().set(valueRange);
		setNumSteps();
	}
	public  boolean done(PropertyType aPreviousValue, PropertyType aNextValue, int aStepNumber, int aMaxSteps) {
//		return  aStepNumber == aMaxSteps;
//		if (traverseForward)
		return  aStepNumber == numSteps;
//		else
//			return aStepNumber == -1;


	}
//	public boolean nextStep(int aRawStepNumber, int aMaxSteps) {	
//		int aStepNumber = rawToCookedStepNumber(aRawStepNumber);
//		PropertyType nextValue =  propertyGenerator.getValue(aStepNumber);
//		if (done(previousValue, nextValue, aStepNumber, aMaxSteps))
//			return true;
////		if ( nextValue.equals(previousValue))
////			return true;
//		setProperty(object, nextValue);
//		previousValue = nextValue;		
//		return false;
//	}
//	public boolean nextStep(int aRawStepNumber, int aMaxSteps) {	
//		int aStepNumber = rawToCookedStepNumber(aRawStepNumber);
//		
//		if (aStepNumber < lastStepNumber)  { // cycled
//			if (toggleDirection)
//			traverseForward = !traverseForward;
//			propertyGenerator().setTraverseForward(traverseForward);
//		}
//		lastStepNumber = aStepNumber;
//		if (done(previousValue, null, aStepNumber, aMaxSteps))
//			return true;
//		PropertyType nextValue =  propertyGenerator.getValue(aStepNumber);
//		setProperty(object, nextValue);
//		previousValue = nextValue;	
//		return false;
//			
//
//	}


//	public boolean isToggleDirection() {
//		return toggleDirection;
//	}
//
//
//	public void setToggleDirection(boolean toggleDirection) {
//		this.toggleDirection = toggleDirection;
//	}

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
	
	
//	public void animationEnd(int aNumSteps, int aMaxSteps) {
//		
//		if (!getProperty(object).equals(finalValue))		
//		   setProperty(object, finalValue);	
//	}
	

}
