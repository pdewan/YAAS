package yaas.shapemappers;

import java.awt.Color;
import java.awt.Rectangle;

import shapes.AttributedShape;
import shapes.FlexibleShape;
import shapes.BoundedShape;
import util.misc.AnObjectColorManager;
import util.misc.ObjectColorManager;

import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.translator.Translator;
// share code with intoRectangle
public class IntToLine implements ObjectToShapeTranslator<Integer>  {
	
	private int scaleFactor = 10;
	ObjectColorManager objectColorManager = new AnObjectColorManager();
	public IntToLine () {
	}
	
	public IntToLine (ObjectColorManager anObjectColorManager) {
		objectColorManager = anObjectColorManager;
	}
	
	public FlexibleShape translate(Integer val) {
		int height = 0;
		if (val != null)
			 
		 height = val * scaleFactor;	
		FlexibleShape retVal = new ALineModel(0, 0, 0, height);
		retVal.setColor(objectColorManager.getColor(val));
//		retVal.setFilled(true);
//		currX += xSpacing;
		return retVal;
	}

	public FlexibleShape calculateNewShape(BoundedShape anExistingShape, Integer val) {
		// TODO Auto-generated method stub
		FlexibleShape retVal = new ALineModel(0, 0, 0, val * scaleFactor);
		return retVal;
	}

//	public BoundedShape calculateNewShape(BoundedShape anExistingShape,
//			Integer val) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	

}
