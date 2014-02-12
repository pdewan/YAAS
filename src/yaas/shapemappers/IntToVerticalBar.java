package yaas.shapemappers;

import java.awt.Color;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.misc.ObjectColorManager;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.translator.FormatException;
import bus.uigen.translator.Translator;

public class IntToVerticalBar extends IntToRectangle {
	public IntToVerticalBar (ObjectColorManager anObjectColorManager) {
		super(anObjectColorManager);
	}
	public IntToVerticalBar () {
		super();
	}
	Color barColor = Color.BLACK;	
	public FlexibleShape translate(Integer val) {
		FlexibleShape newVal = super.translate(val);
		if (newVal instanceof AttributedShape)
		((AttributedShape) newVal).setFilled(true);
		return newVal;
	}
}
