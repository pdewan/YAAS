package yaas.animators;

import java.util.ArrayList;
import java.util.List;

import util.misc.ThreadSupport;

public class AnAnimationStepGenerator implements AnimationStepGenerator{
//	public static final int DEFAULT_ANIMATION_STEP = 10;
//	public static final int DEFAULT_ANIMATION_PAUSE_TIME = 100;
//	public static final int DEFAULT_NUM_STEPS = 10;

//	int animationSteps =  DEFAULT_ANIMATION_STEP;
	int animationPauseTime = AnimationUtil.DEFAULT_ANIMATION_PAUSE_TIME;
    int maxSteps = AnimationUtil.DEFAULT_NUM_STEPS;
    int minSteps = 0;
    List<AnimationStepListener> listeners = new ArrayList();
    public AnAnimationStepGenerator() {
//    	animationPauseTime = AnimationUtil.getAnimationPauseTime();
//    	maxSteps = AnimationUtil.getAnimationSteps();
    }
    
    public int maxSteps () {
    	return AnimationUtil.getAnimateActions()?AnimationUtil.getAnimationSteps():0;
    }
    
    public void addListener(AnimationStepListener aListener) {
    	if (listeners.contains(aListener)) return;
    	listeners.add(aListener);
    }
    
    public void clearListeners() {
    	listeners.clear();
    }

	public int getAnimationPauseTime() {
		return animationPauseTime;
	}

	public int getMaxSteps() {
		return maxSteps;
	}
	public int getMinSteps() {
		return minSteps;
	}


	public void setAnimationPauseTime(int animationPauseTime) {
		this.animationPauseTime = animationPauseTime;
	}

	public void setMaxSteps(int newVal) {
		this.maxSteps = newVal;
	}
	
	public void setMinSteps(int newVal) {
		this.minSteps = newVal;
	}
	
	boolean notifyListenersOfNextStep(int aStepNumber) {
		boolean done = true;
		for (AnimationStepListener aListener:listeners) {
			done &= aListener.nextStep(aStepNumber, maxSteps());			
		}
		return done;
	}
	
	void notifyListenersOfAnimationStart() {
		for (AnimationStepListener aListener:listeners) {
			aListener.animationStart(maxSteps());
		}
	}
	
	void notifyListenersOfAnimationEnd(int aLastStep) {
		for (AnimationStepListener aListener:listeners) {
			aListener.animationEnd(aLastStep, maxSteps());
		}
	}
	protected void doBetweenStepAction() {
		ThreadSupport.sleep(AnimationUtil.getAnimationPauseTime());

	}
	
	public void animate() {
		notifyListenersOfAnimationStart();
		int currentStepNumber = 0;
		while (true) {
			if (currentStepNumber >= maxSteps()) break;		
			boolean done = notifyListenersOfNextStep(currentStepNumber) && currentStepNumber > minSteps;
			if (done) break;
			doBetweenStepAction();
//			ThreadSupport.sleep(AnimationUtil.getAnimationPauseTime());
			currentStepNumber++;
		}
		notifyListenersOfAnimationEnd(currentStepNumber);

		
	}
    
    

}
