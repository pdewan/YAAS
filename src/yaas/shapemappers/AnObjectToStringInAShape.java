package yaas.shapemappers;

import java.awt.Color;
import java.awt.Rectangle;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.misc.AnObjectColorManager;
import util.misc.ObjectColorManager;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.shapes.AStringInAShape;
import bus.uigen.shapes.StringInAShape;
import bus.uigen.translator.FormatException;
import bus.uigen.translator.Translator;

public abstract class AnObjectToStringInAShape<ElementType> implements ObjectToShapeTranslator<ElementType>  {	
	ObjectColorManager objectColorManager = new AnObjectColorManager();
	
	public abstract FlexibleShape createEnclosingShape();
	
	public AnObjectToStringInAShape(ObjectColorManager anObjectColorManager) {
		objectColorManager = anObjectColorManager;
		initializerColors();
//		objectColorManager.setSurroundingColors(new Color[] {AttributeNames.CAROLINA_BLUE, Color.BLACK});

	}
	
	public AnObjectToStringInAShape() {
		initializerColors();
//		objectColorManager.setSurroundingColors(new Color[] {AttributeNames.CAROLINA_BLUE, Color.BLACK});

	}
	public void initializerColors() {
		objectColorManager.setSurroundingColors(new Color[] {AttributeNames.CAROLINA_BLUE, Color.BLACK});

	}
	
	String toString(Object val) {
		if (val == null)
			return "null";
		else
			return val.toString();
	}
    protected StringInAShape createShape(ElementType val) {
		return new AStringInAShape(createEnclosingShape(), toString(val), true);

    	
    }
	public BoundedShape translate(ElementType val) {
		
//		BoundedShape newVal = new AStringInAShape(toString(val), 0, 0);
//		BoundedShape newVal = new AStringInAShape(createEnclosingShape(), toString(val), true);
		BoundedShape newVal = createShape(val);

		if (val == null) {
			newVal.setBounds(new Rectangle (0, 0, 0, 0));
			return newVal;
		}
			
		
		

		if (newVal instanceof AttributedShape) {
			   ((AttributedShape) newVal).setColor(objectColorManager.getColor(val));

		   ((AttributedShape) newVal).setFilled(true);
		}
		return newVal;
	}

	public BoundedShape calculateNewShape(BoundedShape anExistingShape,
			ElementType val) {
		BoundedShape newShape = translate(val);
		if (anExistingShape != null) {
		newShape.setX(anExistingShape.getX());
		newShape.setY(anExistingShape.getY());
		}
		return newShape;
	}
}
