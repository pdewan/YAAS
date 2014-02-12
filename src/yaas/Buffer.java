package yaas;

import yaas.commands.CommandHistory;
import yaas.controller.Control;
import yaas.trappers.*;

public interface Buffer<Observer, ObservableDataType /*extends EventGenerator<Observer, ObservableDataType>*/> {
	CommandHistory getCommandHistory();

	void putBufferThread(Visualizer<Observer, ObservableDataType> v,
			Control control);
	void setBufferData(ObservableDataType data);
	public ObservableDataType getBufferData();
}
