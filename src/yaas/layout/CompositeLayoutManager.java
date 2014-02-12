package yaas.layout;

import shapes.BoundedShape;
import yaas.VisualizationBasedVisualizer;
import yaas.shapemappers.PointerShapeCreator;
import bus.uigen.shapes.ListenableShapeVector;

public interface CompositeLayoutManager<UserDataType> {
	public ListenableShapeVector getContainingShapes(UserDataType data);

	public ListenableShapeVector getAndPositionShapes(UserDataType data, VisualizationBasedLayoutManager aLayoutManager);
	public ListenableShapeVector getOrCreateShapeVector(int aShapesIndex);
	public VisualizationBasedVisualizer getVisualizer() ;

	public void setReadPointerShapeCreator(PointerShapeCreator newVal);
	public PointerShapeCreator getReadPointerShapeCreator() ;
	public PointerShapeCreator getAnnotationPointerShapeCreator();
	public void setAnnotationPointerShapeCreator(PointerShapeCreator newVal);

	public void setReadPointerShapeInitialCoordinates(BoundedShape newVal);
	public void setAnnotationPointerShapeInitialCoordinates(BoundedShape newVal);


}
