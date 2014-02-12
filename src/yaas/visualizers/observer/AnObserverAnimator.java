package yaas.visualizers.observer;


import java.util.Observer;

import yaas.AVisualizationtBasedVisualizer;
import yaas.VisualizationBasedVisualizer;
import yaas.Visualizer;
import yaas.controller.AGenericButtonPressTrapper;
import yaas.controller.ButtonPressTrapper;
import yaas.layout.VisualizationBasedLayoutManager;


public abstract class AnObserverAnimator extends
		AVisualizationtBasedVisualizer<Observer, ObservableEventGenerator> implements
		VisualizationBasedVisualizer<Observer, ObservableEventGenerator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5270046450696451458L;

	ObservableEventGenerator observable;
	
	public AnObserverAnimator(
			VisualizationBasedLayoutManager<ObservableEventGenerator> layoutManager,
			ObservableEventGenerator observable) {
		super();
		this.observable = observable;
		((AnObserverBuffer) getRootBuffer()).setObservable(this.observable);
//		this.layoutManager = layoutManager;

	}

//	@Override
//	protected abstract ObservableEventGenerator createBuffer();

	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new AGenericButtonPressTrapper(controller, this,
				0);
	}

}
