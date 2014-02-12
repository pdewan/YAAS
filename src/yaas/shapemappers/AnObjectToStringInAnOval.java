package yaas.shapemappers;

import java.awt.Color;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.misc.AnObjectColorManager;
import util.misc.ObjectColorManager;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.shapes.AnOvalModel;
import bus.uigen.translator.FormatException;
import bus.uigen.translator.Translator;

public  class AnObjectToStringInAnOval<ElementType> extends AnObjectToStringInAShape<ElementType>  {	
	
	public AnObjectToStringInAnOval(ObjectColorManager anObjectColorManager) {
		super(anObjectColorManager);
	}
	
	public AnObjectToStringInAnOval() {
		super();
	}
	
	
	public  FlexibleShape createEnclosingShape() {
		return new AnOvalModel();
		
	}
	
	
}
