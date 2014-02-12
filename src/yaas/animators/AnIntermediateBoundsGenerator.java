package yaas.animators;

import java.awt.Rectangle;

public class AnIntermediateBoundsGenerator
	extends AnAbstractIntermediateRangeBasedValueGenerator<Rectangle>
	implements IntermediateRangeBasedValueGenerator<Rectangle>{
//	Rectangle initialValue, finalValue;	
//	int maxSteps;
//	boolean circulate;
//	public boolean isCirculate() {
//		return circulate;
//	}
//	public void setCirculate(boolean newVal) {
//		circulate = newVal;
//	}
	IntermediateRangeBasedValueGenerator<Integer> xGenerator = new AnIntermediateIntegerGenerator();
	IntermediateRangeBasedValueGenerator<Integer> yGenerator = new AnIntermediateIntegerGenerator();
	IntermediateRangeBasedValueGenerator<Integer> widthGenerator = new AnIntermediateIntegerGenerator();
	IntermediateRangeBasedValueGenerator<Integer> heightGenerator = new AnIntermediateIntegerGenerator();


//	public Rectangle getFinalValue() {
//		return finalValue;
//	}
//	public Rectangle getInitialValue() {
//		return initialValue;
//	}
//	
	public void set(Rectangle anInitialValue, Rectangle aFinalValue, int aMaxSteps) {
//		initialValue = anInitialValue;
//		finalValue = aFinalValue;
//		maxSteps = aMaxSteps;
		super.set(anInitialValue, aFinalValue, aMaxSteps);
		xGenerator.set(initialValue.x, finalValue.x, aMaxSteps);
		yGenerator.set(initialValue.y, finalValue.y, aMaxSteps);
		widthGenerator.set(initialValue.width, finalValue.width, aMaxSteps);
		heightGenerator.set(initialValue.height, finalValue.height, aMaxSteps);

	}
	public Rectangle getValue(int aStepNumber) {
		int newX = xGenerator.getValue(aStepNumber);
		int newY = yGenerator.getValue(aStepNumber);
		int newWidth = widthGenerator.getValue(aStepNumber);
		int newHeight = heightGenerator.getValue(aStepNumber);
		return new Rectangle(newX, newY, newWidth, newHeight);
		
	}
	public void invertIncrement() {
		xGenerator.invertIncrement();
		yGenerator.invertIncrement();
		widthGenerator.invertIncrement();
		heightGenerator.invertIncrement();
	}
	
	
	

	

}
