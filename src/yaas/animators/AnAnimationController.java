package yaas.animators;

import bus.uigen.ObjectEditor;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AnAnimationController implements AnimationController{
	
	static AnimationController defaultAnimationController = new AnAnimationController();
	
	
	int animationPauseTime = DEFAULT_ANIMATION_PAUSE_TIME ;
	int animationIncrement = 10;
	int animationSteps = DEFAULT_NUM_STEPS;
//	double animationRotateIncrement = 0.5;
	boolean animateActions = true;
//	public AnAnimationController(int theAnimationPauseTime, int theAnimationStep) {
//		animationPauseTime = theAnimationPauseTime;
//		animationStep = theAnimationStep;
//	}
	public int getAnimationPauseTime() {
		return animationPauseTime;
	}
	public void setAnimationPauseTime(int animationPauseTime) {
		this.animationPauseTime = animationPauseTime;
	}
//	public int getAnimationMoveIncrement() {
//		return animationIncrement;
//	}
//	public void setAnimationMoveIncrement(int newVal) {
//		this.animationIncrement = newVal;
//	}
//	public double getAnimationRotateIncrement() {
//		return animationRotateIncrement;
//	}
//	public void setAnimationRotateIncrement(double newVal) {
//		animationRotateIncrement = newVal;
//	}
	
	
	public int getAnimationSteps() {
		return animationSteps;
	}
	public void setAnimationSteps(int animationSteps) {
		this.animationSteps = animationSteps;
	}
	public boolean getAnimateActions() {
		return animateActions;
	}
	public void setAnimateActions(boolean newValue) {
		animateActions = newValue;
	}
	@Visible(false)
	public static AnimationController getDefaultAnimationController() {
		return defaultAnimationController;
	}
	
	public static void setDefaultAnimationController (AnimationController newVal) {
		defaultAnimationController = newVal;
	}
	
	public static void main (String[] args) {
		ObjectEditor.edit(new AnAnimationController());
	}
	

}
