package yaas.animators;

import java.awt.Rectangle;

import shapes.AttributedShape;


public abstract class AnAbstractAtomicObjectIncrementer<ObjectType, PropertyType>
	extends AnAbstractObjectIncrementer
	implements ObjectIncrementer<ObjectType, PropertyType>{
	ObjectType object;
	
	PropertyType finalValue;
	PropertyType previousValue;
	boolean traverseForward;
	int lastStepNumber = -1;
	boolean toggleDirection = true;
	
//	int initialWidth;
//	double widthIncrement;
//	IntermediateValueGenerator<PropertyType> propertyGenerator;
	
//	public AnAbstractAtomicObjectIncrementer() {
//		propertyGenerator = createIntermediateValueGenerator();
////		numSteps = propertyGenerator.getNumSteps();
//	}
//	abstract void setNumSteps() ;
	
	
//	void setNumSteps() {
//		numSteps = propertyGenerator.getNumSteps();
//
//	}
	
//	abstract IntermediateValueGenerator<PropertyType> createIntermediateValueGenerator();

	public ObjectType getObject() {
		return object;
	}

	public void init(ObjectType anObject) {
		object = anObject;
//		finalValue = aFinalValue;
		previousValue = getProperty(anObject);		
	}
	abstract PropertyType getProperty(ObjectType object);
	
	abstract void setProperty(ObjectType anObject, PropertyType aNewValue);
	public abstract void animationStart(int aMaxSteps);

//	public void animationStart(int aMaxSteps) {
//		propertyGenerator.set(getProperty(object), finalValue, aMaxSteps);
//	}
	abstract boolean done(PropertyType aPreviousValue, PropertyType aNextValue, int aStepNumber, int maxSteps);
	abstract PropertyType nextValue(int aStepNumber) ;
	abstract void setTraverseForward(boolean newVal);
	void processRawCookedNumber(int aRawStepNumber, int aStepNumber, int aMaxSteps) {
		if (aStepNumber < lastStepNumber)  { // cycled
			if (toggleDirection)
			traverseForward = !traverseForward;
			setTraverseForward(traverseForward);
//			propertyGenerator().setTraverseForward(traverseForward);
		}
		lastStepNumber = aStepNumber;
	}
//	public boolean nextStep(int aRawStepNumber, int aMaxSteps) {	
//		int aStepNumber = rawToCookedStepNumber(aRawStepNumber);
//		
////		PropertyType nextValue =  propertyGenerator.getValue(aStepNumber);
//		PropertyType nextValue =  nextValue(aStepNumber);
//
//		if (done(previousValue, nextValue, aStepNumber, aMaxSteps))
//			return true;
////		if ( nextValue.equals(previousValue))
////			return true;
//		setProperty(object, nextValue);
//		previousValue = nextValue;		
//		return false;
//	}
	public boolean nextStep(int aRawStepNumber, int aMaxSteps) {	
		int aStepNumber = rawToCookedStepNumber(aRawStepNumber);
		processRawCookedNumber(aRawStepNumber, aStepNumber, aMaxSteps);
//		if (aStepNumber < lastStepNumber)  { // cycled
//			if (toggleDirection)
//			traverseForward = !traverseForward;
//			propertyGenerator().setTraverseForward(traverseForward);
//			setTraverseForward(traverseForward);
//		}
//		lastStepNumber = aStepNumber;
		PropertyType nextValue =  nextValue(aStepNumber);
		if (done(previousValue, nextValue, aStepNumber, aMaxSteps))
			return true;
//		PropertyType nextValue =  propertyGenerator.getValue(aStepNumber);
		setProperty(object, nextValue);
		previousValue = nextValue;	
		return false;
			

	}
	
	public boolean cycle() {
		return false;
		
	}
	
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
	public boolean isToggleDirection() {
		return toggleDirection;
	}


	public void setToggleDirection(boolean toggleDirection) {
		this.toggleDirection = toggleDirection;
	}
	

}
