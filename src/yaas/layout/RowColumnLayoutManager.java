package yaas.layout;

import bus.uigen.shapes.ListenableShapeVector;
import shapes.BoundedShape;
import util.models.ListenableVector;
import yaas.shapemappers.PointerShapeCreator;

public interface RowColumnLayoutManager<UserDataType> extends VisualizationBasedLayoutManager<UserDataType> {
//	CompositeLayoutManager<ListenableVector<ElementType>, ElementType>  {
//	public PointerShapeCreator<ElementType>getPointerShapeCreator() ;
//	public void setPointerShapeCreator(PointerShapeCreator<ElementType> newVal) ;
//	public abstract void setPointerShapeInitialCoordinates(BoundedShape aPointerShape);
	public ListenableShapeVector getContainingShapes(UserDataType data);

	public ListenableShapeVector getAndPositionShapes(UserDataType data);
	public ListenableShapeVector getOrCreateShapeVector(int aShapesIndex);



}
