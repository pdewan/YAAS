package yaas;

import shapes.AttributedShape;
import yaas.controller.Control;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.trappers.EventGenerator;

public interface VestigalVisualizer<Observer, ObservableDataType /*extends EventGenerator<Observer, ObservableDataType>*/, ShapeDataType>
		extends Visualizer<Observer, ObservableDataType>/*, ListenableVector<OEShape>*/ {

//	LayoutManager<ObservableDataType> getLayoutManager();
//	void setLayoutManager (LayoutManager<ObservableDataType> newVal);
//	Control getController();
//	Visualization getShapes(); // this should go away at some point
//	ShapeDataType getVisualization();
	


}
