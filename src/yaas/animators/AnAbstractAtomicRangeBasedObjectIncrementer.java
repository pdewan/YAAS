package yaas.animators;

import java.awt.Rectangle;

import shapes.AttributedShape;


public abstract class AnAbstractAtomicRangeBasedObjectIncrementer<ObjectType, PropertyType>
//	extends AnAbstractObjectIncrementer
	extends AnAbstractValueGeneratorBasedAtomicObjectIncrementer<ObjectType, PropertyType>
//	extends AnAbstractObjectIncrementer

	implements RangeBasedObjectIncrementer<ObjectType, PropertyType>{
//	ObjectType object;
//	PropertyType finalValue;
//	PropertyType previousValue;

//	IntermediateRangeBasedValueGenerator<PropertyType> propertyGenerator;
	IntermediateRangeBasedValueGenerator propertyGenerator() {
		return (IntermediateRangeBasedValueGenerator) propertyGenerator;
	}
//     PropertyType nextValue(int aStepNumber) {
//		return propertyGenerator.getValue(aStepNumber);
//     }
     public AnAbstractAtomicRangeBasedObjectIncrementer() {
 		propertyGenerator = createIntermediateValueGenerator();
// 		numSteps = propertyGenerator.getNumSteps();
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
	public  boolean done(PropertyType aPreviousValue, PropertyType aNextValue, int aStepNumber, int aMaxSteps) {
		return  aNextValue.equals(aPreviousValue);

	}

	public void init(ObjectType anObject, PropertyType aFinalValue) {
		super.init(anObject);
//		object = anObject;
		finalValue = aFinalValue;
//		previousValue = getProperty(anObject);		
	}
//	abstract PropertyType getProperty(ObjectType object);
//	
//	abstract void setProperty(ObjectType anObject, PropertyType aNewValue);

	public void animationStart(int aMaxSteps) {
		propertyGenerator().set(getProperty(object), finalValue, aMaxSteps);
		setNumSteps();
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
		
		if (!getProperty(object).equals(finalValue))		
		   setProperty(object, finalValue);	
	}
	

}
