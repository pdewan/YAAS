package yaas.animators;

import java.awt.Dimension;
import java.awt.Dimension;

public class AnIntermediateDimensionGenerator
	extends AnAbstractIntermediateRangeBasedValueGenerator<Dimension>

	implements IntermediateRangeBasedValueGenerator<Dimension>{
//	Dimension initialValue, finalValue;	
//	int maxSteps;
	int prevHeight;
	int prevY;
	int fixedX;

	IntermediateRangeBasedValueGenerator<Integer> widthGenerator = new AnIntermediateIntegerGenerator();
	IntermediateRangeBasedValueGenerator<Integer> heightGenerator = new AnIntermediateIntegerGenerator();


//	public Dimension getFinalValue() {
//		return finalValue;
//	}
//	public Dimension getInitialValue() {
//		return initialValue;
//	}
	
	public void set(Dimension anInitialValue, Dimension aFinalValue, int aMaxSteps) {
		super.set(anInitialValue, aFinalValue, aMaxSteps);
//		initialValue = anInitialValue;
//		finalValue = aFinalValue;
//		
//		maxSteps = aMaxSteps;
	
		widthGenerator.set(initialValue.width, finalValue.width, aMaxSteps);
		heightGenerator.set(initialValue.height, finalValue.height, aMaxSteps);
		numSteps = Math.max(widthGenerator.getNumSteps(), heightGenerator.getNumSteps());
		

	}
	public Dimension getValue(int aStepNumber) {
	;
		int newWidth = widthGenerator.getValue(aStepNumber);
		int newHeight = heightGenerator.getValue(aStepNumber);
		int newY = prevY - (newHeight - prevHeight);
		Dimension retVal = new Dimension(newWidth, newHeight);
		prevY = newY;
		prevHeight = newHeight;
		return retVal;
		
	}
//	boolean circulate;
//	public boolean isCirculate() {
//		return circulate;
//	}
//	public void setCirculate(boolean newVal) {
//		circulate = newVal;
//	}
	public void invertIncrement() {
		widthGenerator.invertIncrement();
		heightGenerator.invertIncrement();
	}
	
	

	

}
