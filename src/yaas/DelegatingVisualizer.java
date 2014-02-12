package yaas;

import java.util.HashMap;
import java.util.Map;


import yaas.commands.CommandHistory;
import yaas.controller.ButtonPressTrapper;
import yaas.trappers.*;



public interface DelegatingVisualizer<Observer, ObservableDataType /*extends EventGenerator<Observer, ObservableDataType>*/>
		extends TrapperChainSupporter<Observer, ObservableDataType> {

	void visualize(ObservableDataType anObservable) throws Exception;

	HashMap<Integer, ObservableDataType> getOriginalData();

	void setCanProceed(boolean b);

	boolean getCanProceed();

	void changeAnimationPauseTime(int n);

	void notifyVisualizer();

	void waitForNextBufferThreadStep();

	CommandHistory getUndoer();
	
	ButtonPressTrapper initializeButtonPressTrapper();

}
