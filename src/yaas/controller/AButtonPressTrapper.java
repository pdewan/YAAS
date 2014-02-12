package yaas.controller;

import yaas.AVisualizer;
import yaas.Visualizer;

@SuppressWarnings("rawtypes")
public abstract class AButtonPressTrapper implements ButtonPressTrapper {

	protected Control controller;
	protected Visualizer visualizer;

	public AButtonPressTrapper(Control controller,
			Visualizer visualizer) {
		this.controller = controller;
		this.visualizer = visualizer;
	}

	public void maybeDiscardNextButtonPress() {
		if(null == controller){
			controller = ((AVisualizer) visualizer).getController();
		}
		controller.setMoveForward(true);
		visualizer.setCanProceed(true);
		visualizer.notifyVisualizer();

	}


	public void maybeDiscardPreviousButtonPress() {
		if(null == controller){
			controller = ((AVisualizer) visualizer).getController();
		}
		controller.setMoveForward(false);
		visualizer.setCanProceed(true);
		visualizer.notifyVisualizer();
		
	}
}
