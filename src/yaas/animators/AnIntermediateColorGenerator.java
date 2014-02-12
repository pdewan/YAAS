package yaas.animators;

import java.awt.Color;
import java.awt.Rectangle;

public class AnIntermediateColorGenerator
	extends AnAbstractIntermediateRangeBasedValueGenerator<Color>
	implements IntermediateRangeBasedValueGenerator<Color>{
//	Color initialValue, finalValue;	
//	int maxSteps;
	IntermediateRangeBasedValueGenerator<Integer> redGenerator = new AnIntermediateIntegerGenerator();
	IntermediateRangeBasedValueGenerator<Integer> greenGenerator = new AnIntermediateIntegerGenerator();
	IntermediateRangeBasedValueGenerator<Integer> blueGenerator = new AnIntermediateIntegerGenerator();


//	public Color getFinalValue() {
//		return finalValue;
//	}
//	public Color getInitialValue() {
//		return initialValue;
//	}
//	
	public void set(Color anInitialValue, Color aFinalValue, int aMaxSteps) {
//		initialValue = anInitialValue;
//		finalValue = aFinalValue;		
//		maxSteps = aMaxSteps;	
		super.set(anInitialValue, aFinalValue, aMaxSteps);
		if (initialValue == null || finalValue == null)
			return;
		redGenerator.set(initialValue.getRed(), finalValue.getRed(), aMaxSteps);
		greenGenerator.set(initialValue.getGreen(), finalValue.getGreen(), aMaxSteps);
		blueGenerator.set(initialValue.getBlue(), finalValue.getBlue(), aMaxSteps);
		numSteps = Math.max(redGenerator.getNumSteps(), 
				Math.max(greenGenerator.getNumSteps(), 
				blueGenerator.getNumSteps()));


	}
	public Color getValue(int aStepNumber) {
		if (initialValue == null || finalValue == null)
			return finalValue;		 
		int newRed = redGenerator.getValue(aStepNumber);
		int newGreen = greenGenerator.getValue(aStepNumber);
		int newBlue = blueGenerator.getValue(aStepNumber);
		return new Color(newRed, newGreen, newBlue);
		
	}
//	boolean circulate;
//	public boolean isCirculate() {
//		return circulate;
//	}
//	public void setCirculate(boolean newVal) {
//		circulate = newVal;
//	}
	public void invertIncrement() {
		redGenerator.invertIncrement();
		greenGenerator.invertIncrement();
		blueGenerator.invertIncrement();
	}
	
	
	

	

}
