package yaas.shapemappers;

import shapes.BoundedShape;

public interface PointerShapeCreator<DataType> {
	public boolean adjustSize();
	public void setAdjustSize(boolean newVal);
	BoundedShape toNewPointerShape (BoundedShape aPointedShape, DataType aPointedValue, RectangleEnds anEndPoint);
	BoundedShape toInvisiblePointedShape(BoundedShape aPointerShape, BoundedShape aPointedShape,
			DataType aPointedValue) ;
	BoundedShape calculateNewShape(BoundedShape anExistingShape, BoundedShape aPointedShape, DataType aPointedValue, Integer aPos, RectangleEnds anEndPoint, Object aPointerValue);

}
