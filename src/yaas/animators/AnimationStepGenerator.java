package yaas.animators;

import util.misc.ThreadSupport;

public interface AnimationStepGenerator {
	  public void addListener(AnimationStepListener aListener) ;
	    public void clearListeners();

		public int getAnimationPauseTime();
		public int getMaxSteps();

		public void setAnimationPauseTime(int animationPauseTime);

		public void setMaxSteps(int numSteps) ;
		
		
		
		public void animate() ;
}
