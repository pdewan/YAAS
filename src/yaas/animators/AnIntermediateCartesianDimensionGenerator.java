package yaas.animators;

import java.awt.Dimension;
import java.awt.Rectangle;

public class AnIntermediateCartesianDimensionGenerator
	extends AnAbstractIntermediateRangeBasedValueGenerator<Rectangle>
	implements IntermediateRangeBasedValueGenerator<Rectangle>{
//	Rectangle initialValue, finalValue;	
//	int maxSteps;
	int prevHeight;
	int prevY;
	int fixedX;

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

		fixedX = anInitialValue.x;
		prevY = anInitialValue.y;
		prevHeight = anInitialValue.height;
	
		widthGenerator.set(initialValue.width, finalValue.width, aMaxSteps);
		heightGenerator.set(initialValue.height, finalValue.height, aMaxSteps);

	}
	public Rectangle getValue(int aStepNumber) {
	;
		int newWidth = widthGenerator.getValue(aStepNumber);
		int newHeight = heightGenerator.getValue(aStepNumber);
		int newY = prevY - (newHeight - prevHeight);
		Rectangle retVal = new Rectangle(fixedX, newY, newWidth, newHeight);
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
