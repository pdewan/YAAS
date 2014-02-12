package yaas.animators;

import java.awt.Rectangle;

import shapes.AttributedShape;


public abstract class AnAbstractValueGeneratorBasedAtomicObjectIncrementer<ObjectType, PropertyType>
	extends AnAbstractAtomicObjectIncrementer<ObjectType, PropertyType>
	implements ObjectIncrementer<ObjectType, PropertyType>{
//	ObjectType object;
//	
//	PropertyType finalValue;
//	PropertyType previousValue;
//	int initialWidth;
//	double widthIncrement;
	IntermediateAbsoluteValueGenerator<PropertyType> propertyGenerator;
	
	public AnAbstractValueGeneratorBasedAtomicObjectIncrementer() {
		propertyGenerator = createIntermediateValueGenerator();
//		numSteps = propertyGenerator.getNumSteps();
	}
	
	void setNumSteps() {
		numSteps = propertyGenerator.getNumSteps();

	}
	PropertyType nextValue(int aStepNumber) {
		return propertyGenerator.getValue(aStepNumber);
     }
	
	abstract IntermediateAbsoluteValueGenerator<PropertyType> createIntermediateValueGenerator();
	void setTraverseForward(boolean newVal) {
		propertyGenerator.setTraverseForward(newVal);
	}

//	public ObjectType getObject() {
//		return object;
//	}
//
//	public void init(ObjectType anObject) {
//		object = anObject;
////		finalValue = aFinalValue;
//		previousValue = getProperty(anObject);		
//	}
//	abstract PropertyType getProperty(ObjectType object);
//	
//	abstract void setProperty(ObjectType anObject, PropertyType aNewValue);
//	public abstract void animationStart(int aMaxSteps);
//
////	public void animationStart(int aMaxSteps) {
////		propertyGenerator.set(getProperty(object), finalValue, aMaxSteps);
////	}
//	abstract boolean done(PropertyType aPreviousValue, PropertyType aNextValue, int aStepNumber, int maxSteps);
//
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
