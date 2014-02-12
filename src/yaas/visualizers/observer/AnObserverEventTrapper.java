package yaas.visualizers.observer;

import java.util.Observable;
import java.util.Observer;

import yaas.trappers.EventTrapper;


public abstract class AnObserverEventTrapper implements Observer,
		EventTrapper<Observer, ObservableEventGenerator>,
		ObservableEventGenerator {

	protected ObservableEventGenerator observable;

	public AnObserverEventTrapper(ObservableEventGenerator observable) {
		this.observable = observable;
	}

	public void addListener(
			EventTrapper<Observer, ObservableEventGenerator> observer)
			throws Exception {

		observable.addListener(observer);
	}

	public void removeListener(
			EventTrapper<Observer, ObservableEventGenerator> observer) {
		observable.removeListener(observer);
	}

	public abstract void update(Observable arg0, Object arg1);

}
