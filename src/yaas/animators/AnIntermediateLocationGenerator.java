package yaas.animators;

import java.awt.Point;
import java.awt.Rectangle;

public class AnIntermediateLocationGenerator
	extends AnAbstractIntermediateRangeBasedValueGenerator<Point>
	implements IntermediateRangeBasedValueGenerator<Point>{
//	Point initialValue, finalValue;	
//	int maxSteps;
	IntermediateRangeBasedValueGenerator<Integer> xGenerator = new AnIntermediateIntegerGenerator();
	IntermediateRangeBasedValueGenerator<Integer> yGenerator = new AnIntermediateIntegerGenerator();
//	IntermediateValueGenerator<Integer> widthGenerator = new AnIntermediateIntGenerator();
//	IntermediateValueGenerator<Integer> heightGenerator = new AnIntermediateIntGenerator();


//	public Point getFinalValue() {
//		return finalValue;
//	}
//	public Point getInitialValue() {
//		return initialValue;
//	}
//	
	public void set(Point anInitialValue, Point aFinalValue, int aMaxSteps) {
		super.set(anInitialValue, aFinalValue, aMaxSteps);
//		initialValue = anInitialValue;
//		finalValue = aFinalValue;
//		maxSteps = aMaxSteps;
		xGenerator.set(initialValue.x, finalValue.x, aMaxSteps);
		yGenerator.set(initialValue.y, finalValue.y, aMaxSteps);
		numSteps = Math.max(xGenerator.getNumSteps(), yGenerator.getNumSteps());

//		widthGenerator.set(initialBounds.width, finalBounds.width, aMaxSteps);
//		heightGenerator.set(initialBounds.height, finalBounds.height, aMaxSteps);

	}
	public Point getValue(int aStepNumber) {
		int newX = xGenerator.getValue(aStepNumber);
		int newY = yGenerator.getValue(aStepNumber);
//		int newWidth = widthGenerator.getValue(aStepNumber);
//		int newHeight = heightGenerator.getValue(aStepNumber);
		return new Point(newX, newY);
		
	}
//	boolean circulate;
//	public boolean isCirculate() {
//		return circulate;
//	}
//	public void setCirculate(boolean newVal) {
//		circulate = newVal;
//	}
	public void invertIncrement() {
		xGenerator.invertIncrement();
		yGenerator.invertIncrement();
	}
	
	
	

	

}
