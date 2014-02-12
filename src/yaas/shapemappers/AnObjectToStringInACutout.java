package yaas.shapemappers;

import java.awt.Color;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.misc.AnObjectColorManager;
import util.misc.ObjectColorManager;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.shapes.AStringInAShape;
import bus.uigen.shapes.AnImageModel;
import bus.uigen.shapes.AnOvalModel;
import bus.uigen.shapes.StringInAShape;
import bus.uigen.translator.FormatException;
import bus.uigen.translator.Translator;

public  class AnObjectToStringInACutout extends AnObjectToStringInAShape<Object>  {	
	
	public AnObjectToStringInACutout(ObjectColorManager anObjectColorManager) {
		super(anObjectColorManager);
	}
	
	public AnObjectToStringInACutout() {
		super();
	}
	
	
	public  FlexibleShape createEnclosingShape() {
		return   new AnImageModel("data/cutout.png");
		
	}
	  protected StringInAShape createShape(Object val) {
			StringInAShape retVal = new AStringInAShape(createEnclosingShape(), toString(val), true);
			retVal.setyMargin(20);
			retVal.setyCorrection(5);
			return retVal;

	    	
	    }
	
//	public BoundedShape translate(Object val) {
//		StringInAShape retVal = (StringInAShape) super.translate(val);
//		retVal.setyMargin(10);
//		
//		
//	}
	
	
}
