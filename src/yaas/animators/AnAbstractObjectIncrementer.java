package yaas.animators;

public class AnAbstractObjectIncrementer {
	boolean circulate;
//	int currentStepNumber;
	int numSteps;
	public boolean isCirculate() {
		return circulate;
	}
	public void setCirculate(boolean newVal) {
		circulate = newVal;
	}
	
	int rawToCookedStepNumber(int aRawStepNumber) {
		return 
				numSteps > 0 && circulate?
						aRawStepNumber % numSteps:
						aRawStepNumber;
	}	
	

}
