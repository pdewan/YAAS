package yaas.controller;

import yaas.AVisualizer;
import yaas.Visualizer;

@SuppressWarnings("rawtypes")
public class AGenericButtonPressTrapper extends AButtonPressTrapper implements
		ButtonPressTrapper {

	public AGenericButtonPressTrapper(Control controller,
			Visualizer visualizer, int pauseTimeInMillisecond) {
		super(controller, visualizer);
		// this.pauseTimeInMillisecond = pauseTimeInMillisecond;
	}

	@Override
	public void maybeDiscardNextButtonPress() {

		super.maybeDiscardNextButtonPress();

	}

	@Override
	public void maybeDiscardPreviousButtonPress() {

		super.maybeDiscardPreviousButtonPress();
	}

	public void maybeDiscardLastButtonPress() {
		initializeComponents();
		controller.setSynchronous(true);
		super.maybeDiscardNextButtonPress();

	}

	public void maybeDiscardFirstButtonPress() {
		initializeComponents();
		controller.setSynchronous(true);
		super.maybeDiscardPreviousButtonPress();

	}

	private void initializeComponents() {
		if (null == controller) {
			controller = ((AVisualizer) visualizer).getController();
		}
	}

}
