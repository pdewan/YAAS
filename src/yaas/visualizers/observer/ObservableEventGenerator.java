package yaas.visualizers.observer;

import java.util.Observer;

import yaas.trappers.EventGenerator;


public interface ObservableEventGenerator extends
		EventGenerator<Observer, ObservableEventGenerator> {

}
