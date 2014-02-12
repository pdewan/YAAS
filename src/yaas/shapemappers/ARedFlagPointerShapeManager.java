package yaas.shapemappers;

import java.awt.Color;
import java.awt.Point;

import shapes.BoundedShape;
import shapes.FlexibleShape;

import bus.uigen.shapes.AnImageModel;
import bus.uigen.shapes.AnOvalModel;

public class ARedFlagPointerShapeManager extends AbstractPointerShapeManager {
	String flagFile = "data/redflag.png";
	public BoundedShape createPointerShape() {
		return new AnImageModel(flagFile);
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
