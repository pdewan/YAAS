package yaas.controller;

import yaas.VisualizationBasedVisualizer;
import yaas.Visualizer;
import yaas.visualizers.observer.ObservableEventGenerator;

@SuppressWarnings("rawtypes")
public interface Control extends ObservableEventGenerator{
	
	void first();
	void previous();
	void next();
	void last();
	
	
	Visualizer getVisualizer();
	boolean getSynchronous();
	void setSynchronous(boolean newVal);
	boolean getMoveForward();
	void setMoveForward(boolean newVal);
	void commandExecuted();
	void init(Visualizer cv);
}
