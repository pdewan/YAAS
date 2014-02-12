package yaas.shapemappers;

import java.awt.Color;
import java.awt.Point;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import bus.uigen.shapes.AnOvalModel;
import bus.uigen.shapes.StringInAShape;

public class AStringInAShapePointerShapeManager<DataType> extends AbstractPointerShapeManager<DataType> {
	ObjectToShapeTranslator shapeCreator = new AnObjectToStringInACutout();

	public BoundedShape createPointerShape() {
		return shapeCreator.calculateNewShape(null, null);
	}
	public BoundedShape calculateNewShape(BoundedShape aPointerShape, BoundedShape aPointedShape, DataType aPointedValue, Integer aPos, 
			RectangleEnds anEndPoint, Object aPointerValue) {
		StringInAShape stringInAShape = (StringInAShape) aPointerShape;
		if (aPointerValue != null) {
		stringInAShape.setText(aPointerValue.toString());
		}
		return super.calculateNewShape(aPointerShape, aPointedShape, aPointedValue, aPos, anEndPoint, aPointerValue);
//		StringInAShape stringInAShape = (StringInAShape) retVal;
//		if (aPointerValue != null)
//		stringInAShape.setText(aPointerValue.toString());
//		return retVal;
	}

//	public BoundedShape toInvisiblePointedShape(BoundedShape aPointedShape,
//			Object aPointedValue) {
//		FancyShape retVal = new AnOvalModel();
//		retVal.setColor(DEFAULT_POINTED_COLOR);		
//		retVal.setFilled(true);
//		Point position = positionPointerShape(aPointedShape, aPointedValue);
//		retVal.setX(position.x);
//		retVal.setY(position.y);
//		retVal.setWidth(0);
//		retVal.setHeight(0);
//		
//		return retVal;
//	}

}
