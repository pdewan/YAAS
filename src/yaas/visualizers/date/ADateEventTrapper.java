package yaas.visualizers.date;

import java.util.Observable;
import java.util.Observer;

import yaas.trappers.*;

public class ADateEventTrapper extends ObservableDate implements Observer,
		EventTrapper<Observer, ObservableDate> {

	private ADateVisualizer visualizer;

	public ADateEventTrapper(ADateVisualizer visualizer) {
		this.visualizer = visualizer;

	}

	public void update(Observable observable, Object changedObject) {

		if (changedObject instanceof ObservableDate) {
			// buffer.getUndoer().addCommand(c);
			this.setDate(((ObservableDate) changedObject).getDate());
			this.setChanged();
			this.notifyObservers();

			visualizer.update(observable, changedObject);
		}
	}

}
