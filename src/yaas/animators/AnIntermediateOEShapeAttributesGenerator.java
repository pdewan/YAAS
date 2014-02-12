package yaas.animators;

import java.awt.Color;
import java.awt.Rectangle;

import shapes.AttributedShape;
import shapes.BoundedShape;
import util.misc.Common;
import bus.uigen.shapes.AShapeModel;

public class AnIntermediateOEShapeAttributesGenerator
	extends AnAbstractIntermediateRangeBasedValueGenerator<BoundedShape>
	implements IntermediateRangeBasedValueGenerator<BoundedShape>{
//	BoundedShape initialValue, finalValue, intermediateOEShape;	
//	int maxSteps;
	BoundedShape intermediateOEShape;	

	IntermediateRangeBasedValueGenerator<Rectangle> boundsGenerator = new AnIntermediateBoundsGenerator();
	IntermediateRangeBasedValueGenerator<Color> colorGenerator = new AnIntermediateColorGenerator();


//	public BoundedShape getFinalValue() {
//		return finalValue;
//	}
//	public BoundedShape getInitialValue() {
//		return initialValue;
//	}
//	
	public void set(BoundedShape anInitialValue, BoundedShape aFinalValue, int aMaxSteps) {
//		initialValue = anInitialValue;
//		finalValue = aFinalValue;
//		maxSteps = aMaxSteps;
		super.set(anInitialValue, aFinalValue, aMaxSteps);
		numSteps = boundsGenerator.getNumSteps();
		boundsGenerator.set(initialValue.getBounds(), finalValue.getBounds(), aMaxSteps);
		if (initialValue instanceof AttributedShape && finalValue instanceof AttributedShape) {
		colorGenerator.set(((AttributedShape) initialValue).getColor(), ((AttributedShape) finalValue).getColor(), aMaxSteps);
		numSteps = Math.max(numSteps, colorGenerator.getNumSteps());
		}
//		intermediateOEShape = (OEShape) Common.deepCopy(anInitialValue);
//		((AShapeModel) intermediateOEShape).initSerializedObject();
	}
	public BoundedShape getValue(int aStepNumber) {
		Rectangle newBounds = boundsGenerator.getValue(aStepNumber);
		Color newColor= colorGenerator.getValue(aStepNumber);
		intermediateOEShape = (BoundedShape) Common.deepCopy(initialValue);
		if (intermediateOEShape instanceof AShapeModel)
		((AShapeModel) intermediateOEShape).initSerializedObject();
		intermediateOEShape.setBounds(newBounds);
		if (intermediateOEShape instanceof AttributedShape)
		((AttributedShape) intermediateOEShape).setColor(newColor);
		return intermediateOEShape;
		
	}
	
	boolean circulate;
	public boolean isCirculate() {
		return circulate;
	}
	public void setCirculate(boolean newVal) {
		circulate = newVal;
	}
	public void invertIncrement() {
		boundsGenerator.invertIncrement();
		colorGenerator.invertIncrement();
	}
	

	

}
