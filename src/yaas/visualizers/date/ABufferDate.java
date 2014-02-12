package yaas.visualizers.date;

import java.util.Observable;
import java.util.Observer;

import yaas.ABuffer;
import yaas.Buffer;
import yaas.VisualizationBasedVisualizer;
import yaas.Visualizer;
import yaas.commands.CommandHistory;
import yaas.controller.Control;
import yaas.trappers.EventTrapper;



public class ABufferDate extends ObservableDate implements Observer,
		Buffer<Observer, ObservableDate>, EventTrapper<Observer, ObservableDate>{

	private VisualizationBasedVisualizer<Observer, ObservableDate> visualizer;
	protected Buffer<Observer, ObservableDate> buffer;

	private ObservableDate observable;

	public ABufferDate(VisualizationBasedVisualizer<Observer, ObservableDate> visualizer){
		this.visualizer = visualizer;
		buffer  = new ABuffer<Observer, ObservableDate>(this.visualizer);
	}
	public ABufferDate(ObservableDate date,
			VisualizationBasedVisualizer<Observer, ObservableDate> visualizer) {
		this(visualizer);
		observable = date;
		observable.addObserver(this);
		this.setDate(date.getDate());
	}

	public void update(Observable observable, Object changedObject) {
		if (changedObject == this.observable) {
			//buffer.getUndoer().addCommand(c);
			this.setDate(this.observable.getDate());
			this.setChanged();
			this.notifyObservers();
		}
	}

	public CommandHistory getCommandHistory() {
		return buffer.getCommandHistory();
	}

	public Visualizer<Observer, ObservableDate> getVisualizer() {
		return visualizer;
	}

	public void putBufferThread(
			Visualizer<Observer, ObservableDate> v, Control control) {
		buffer.putBufferThread(v, control);

	}

	@SuppressWarnings("deprecation")
	public void setBufferData(ObservableDate data) {
		buffer.setBufferData(data);
		observable = data;
		observable.addObserver(this);
		this.setDate(date.getDate());
		
	}
	public ObservableDate getBufferData() {
		return buffer.getBufferData();
	}
}
