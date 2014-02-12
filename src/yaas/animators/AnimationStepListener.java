package yaas.animators;

public interface AnimationStepListener {
	void animationStart(int aMaxSteps);
	boolean nextStep (int aStepNumber, int aMaxSteps);
	void animationEnd(int aNumSteps, int aMaxSteps);

}
