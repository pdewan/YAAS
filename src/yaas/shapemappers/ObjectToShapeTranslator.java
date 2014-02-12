package yaas.shapemappers;

import shapes.BoundedShape;
import bus.uigen.translator.Translator;

//public interface ObjectToShapeTranslator<DataType, ShapeDataType> 
public interface ObjectToShapeTranslator<DataType> 

extends Translator<DataType, BoundedShape> {
//	void translate (SimpleShape anExistingShape, DataType newVal);
	BoundedShape calculateNewShape(BoundedShape anExistingShape, DataType val);
}
