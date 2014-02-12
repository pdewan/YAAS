package yaas;

import shapes.BoundedShape;
import util.models.ListenableVector;
import yaas.controller.Control;
import yaas.layout.LayoutManager;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.trappers.EventGenerator;

public interface VisualizationBasedVisualizer<Observer, ObservableDataType /*extends EventGenerator<Observer, ObservableDataType>*/>
//		extends OEGraphicsObjectBasedVisualizer<Observer, ObservableDataType,  ListenableVector<BoundedShape>> {
	extends Visualizer<Observer, ObservableDataType> {
	
//	VisualizationBasedVisualizer<Observer, ObservableDataType,  Visualization> {


//	LayoutManager<ObservableDataType> getLayoutManager();
//	void setLayoutManager (LayoutManager<ObservableDataType> newVal);
//	Control getController();
//	ListenableVector<SimpleShape> getShapes(); // this should go away at some point
//	ShapeDataType getVisualization();
//	VisualizationBasedLayoutManager<ObservableDataType> getLayoutManager();
//	void setLayoutManager (VisualizationBasedLayoutManager<ObservableDataType> newVal);
	 Integer getRootShapesIndex(ObservableDataType aRoot);
		public Visualization getVisualization() ;
		public void setVisualization(Visualization newVal) ;
	


}
