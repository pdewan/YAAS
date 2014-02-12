package yaas;

import java.util.Collection;

import shapes.BoundedShape;
import util.models.AListenableVector;
import util.models.Hashcodetable;
import util.models.ListenableVector;
import yaas.layout.VisualizationBasedLayoutManager;

@SuppressWarnings("unchecked")
public abstract class AVisualizationtBasedVisualizer<Observer, ObservableDataType /*extends EventGenerator<Observer, ObservableDataType>*/>
		extends AVisualizer<Observer, ObservableDataType> 
//		implements
//		ShapeBasedVisualizer<Observer, ObservableDataType>
{
	
//	protected ListenableVector<BoundedShape> shapes = new AListenableVector<BoundedShape>();
//	@util.annotations.Visible(false)
//	public ListenableVector<BoundedShape> getShapes() {
//		return shapes;
//	}
//	public Object getShapes() {
//		return shapes;
//	}
//	protected ListenableVector<AttributedShape> visualization = new AListenableVector(); 

	
	Hashcodetable<ObservableDataType, Integer> rootToShapesPosition = new Hashcodetable();
//	int curRootShapesPosition = 0;
	
	
	public Visualization getVisualization() {
		return (Visualization) shapes;
	}
	public void setVisualization(Visualization newVal) {
		shapes = newVal;
	}
	// needs to be shared with barchart visualized 
	protected synchronized void visualizeBuffer(ObservableDataType vector) {
//		bufferData.put(collectionIndex, vector);
		bufferData.put(collectionIndex, vector);
		rootToShapesPosition.put(vector, collectionIndex);
		collectionIndex++;
		Collection<BoundedShape> display = getLayoutManagerOfBuffer(vector).display(vector);
		if (display != null) {
			bufferData.put(collectionIndex, vector);
//			rootToShapesPosition.put(vector, collectionIndex);
//			collectionIndex++;

			getVisualization().getShapes().get(collectionIndex).addAll(getLayoutManagerOfBuffer(vector).display(vector));
//			rootToShapesPosition.put(vector, collectionIndex);
//			collectionIndex++;
		}
	}
	
	public Integer getRootShapesIndex(ObservableDataType aRoot) {
		return rootToShapesPosition.get(aRoot);
	}
	
	public VisualizationBasedLayoutManager<ObservableDataType> getLayoutManagerOfBuffer(ObservableDataType aRootBuffer) {
		return (VisualizationBasedLayoutManager<ObservableDataType>) super.getLayoutManagerOfBuffer(aRootBuffer);
	}
//	public void setLayoutManager (VisualizationBasedLayoutManager<ObservableDataType> newVal) {
//		super.
//	}
}
