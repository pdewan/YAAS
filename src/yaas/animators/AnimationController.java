package yaas.animators;

public interface AnimationController {
	public static int DEFAULT_ANIMATION_PAUSE_TIME = 100;
	public static int DEFAULT_NUM_STEPS = 6;
	public int getAnimationPauseTime();
	public void setAnimationPauseTime(int animationPauseTime) ;
//	public int getAnimationMoveIncrement();
//	public void setAnimationMoveIncrement(int animationStep) ;
	public int getAnimationSteps() ;
	public void setAnimationSteps(int animationSteps) ;
	public boolean getAnimateActions() ;
	public void setAnimateActions(boolean newValue) ;
//	public double getAnimationRotateIncrement();
//	public void setAnimationRotateIncrement(double newVal) ;

}
