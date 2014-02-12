package yaas.shapemappers;

import java.awt.Color;
import java.awt.Point;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.shapes.AnOvalModel;

public class AThickLinePointerShapeManager<DataType> extends AbstractPointerShapeManager<DataType> {
	public static final int DEFAULT_THICK_LINE_WIDTH = 5;
	public AThickLinePointerShapeManager() {
		pointerWidth = DEFAULT_THICK_LINE_WIDTH;
	}
	public BoundedShape createPointerShape() {
		return new ARectangleModel();
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
