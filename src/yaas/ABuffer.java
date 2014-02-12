package yaas;

import yaas.commands.AHistoryUndoer;
import yaas.commands.CommandHistory;
import yaas.controller.Control;
import yaas.threads.BufferThread;
import yaas.trappers.*;

public class ABuffer<Observer, ObservableDataType /*extends EventGenerator<Observer, ObservableDataType>*/>
		implements Buffer<Observer, ObservableDataType> {

	protected CommandHistory commandHistory = new AHistoryUndoer();;
	protected BufferThread bufferThread;
	protected Visualizer<Observer, ObservableDataType> visualizer;
	protected ObservableDataType data;

	public ABuffer(Visualizer<Observer, ObservableDataType> visualizer){
		this.visualizer = visualizer;
	}
	
	public CommandHistory getCommandHistory() {
		return commandHistory;
	}

	public void putBufferThread(
			Visualizer<Observer, ObservableDataType> v,
			Control control) {
		BufferThread b = new BufferThread(commandHistory, control, v);
		Thread bufferThread = new Thread(b);
		bufferThread.start();
	}

	public Visualizer<Observer, ObservableDataType> getVisualizer() {
		return visualizer;
	}

	public void setBufferData(ObservableDataType data) {
		this.data = data;

	}
	public ObservableDataType getBufferData() {
		return this.data;

	}
}
