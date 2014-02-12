package yaas.shapemappers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import bus.uigen.attributes.AttributeNames;
import bus.uigen.shapes.AShapeModel;
import bus.uigen.translator.FormatException;
import shapes.AttributedShape;
import shapes.AttributedTextShape;
import shapes.BoundedShape;
import shapes.BoundedTextShape;
import shapes.FlexibleTextShape;
import shapes.TextShape;
import util.misc.AnObjectColorManager;
import util.misc.Common;
import util.misc.ObjectColorManager;
import util.models.ListenableVector;


public abstract class AnObjectToTextShape<ElementType> implements ObjectToShapeTranslator<ElementType>{
	ObjectColorManager objectColorManager = new AnObjectColorManager();
	public static final int DEFAULT_BOX_WIDTH = 80;
	public static final int DEFAULT_MINIMUM_BOX_WIDTH = 15;
	public static final int DEFAULT_BOX_HEIGHT = 20;
	public static final Color DEFAULT_NULL_COLOR = Color.BLACK;
	public static final int DEFAULT_NULL_WIDTH = 0;
	public static final int DEFAULT_NULL_HEIGHT = 0;

	boolean isDisplayName = true;

	protected boolean dynamicWidth = true, dynamicHeight, filled = true;
	protected int 
			boxWidth = DEFAULT_BOX_WIDTH, 
			boxHeight = DEFAULT_BOX_HEIGHT,
			minBoxWidth = DEFAULT_MINIMUM_BOX_WIDTH;
	protected  Color nullColor = DEFAULT_NULL_COLOR;
	
	public AnObjectToTextShape(ObjectColorManager anObjectColorManager) {
		objectColorManager = anObjectColorManager;
	}
	
	public AnObjectToTextShape() {
		if (filled)
		    objectColorManager.setSurroundingColors(new Color[] {Color.BLACK, AttributeNames.CAROLINA_BLUE});
		else
			objectColorManager.setSurroundingColors(new Color[] {AttributeNames.CAROLINA_BLUE});
	}


	

	protected abstract AttributedTextShape createTextShape();
	protected String toString(ElementType anObject) {
		if (anObject == null)
			return "";
		else if (anObject instanceof ListenableVector) {
//			return ((ListenableVector) anObject).getName();
			return toString (((ListenableVector) anObject));

		}
		return anObject.toString();
	}
	
	protected Color getNullColor() {
		return nullColor;
	}
	
	public Color getAtomicColor (Object anObject) {
		if (anObject == null)
			return getNullColor();
		else
			return objectColorManager.getColor(anObject);
	}
	
	public Color getColor(ElementType anObject) {
		if (! (anObject instanceof ListenableVector))
			return getAtomicColor(anObject);
		
		ListenableVector listenableVector = (ListenableVector) anObject;
		if (isDisplayName()) {
				return getAtomicColor(listenableVector.getName());
		} else {
				return getAtomicColor(listenableVector.getUserObject());
			}
	
			
	}
	
	protected String userObjectString(ListenableVector aVector) {
		if (aVector.getUserObject() == null)
			return "";
		else return aVector.getUserObject().toString();
	}
	
	protected String toString(ListenableVector aVector) {
		if (isDisplayName())
		return aVector.getName();
		else
			return userObjectString(aVector);
	}
	
	public AttributedTextShape translate(ElementType obj) throws FormatException {
		return createShape(obj);
	}

	public AttributedTextShape calculateNewShape(BoundedShape anExistingShape,
			ElementType val) {
		AttributedTextShape retVal = translate(val);
		retVal.setBounds(anExistingShape.getBounds());
		return retVal;
	}
	void setAttributes (AttributedTextShape aShape, ElementType anObjectValue) {
		aShape.setFilled(filled);
		aShape.setColor(getColor(anObjectValue));
//		if (anObjectValue == null)
//			aShape.setColor(DEFAULT_NULL_COLOR);
//		else
//		   aShape.setColor(objectColorManager.getColor(anObjectValue));
////		aShape.setFont(Common.getDefaultFont());
		
	}
	Dimension getNullDimension() {
		return new Dimension (DEFAULT_NULL_WIDTH, DEFAULT_NULL_HEIGHT);
	}
	Dimension getStaticDimension() {
		return new Dimension (getStaticWidth(), getStaticHeight());
	}
	int getStaticWidth() {
		return boxWidth;
	}
	int getStaticHeight() {
		return boxHeight;
	}
	int getDynamicWidth(AttributedTextShape aShape) {
		return Math.max (minBoxWidth, Common.getFontStringWidth(aShape.getFont(), aShape.getText(), null));
	}
	int getDynamicHeight(AttributedTextShape aShape) {
		return Common.getFontHeight(aShape.getFont(), null);
	}
	Dimension getDynamicDimension(AttributedTextShape aShape) {
		return new Dimension 
				(getDynamicWidth(aShape), 
				getDynamicHeight(aShape));
	}
	Dimension getDynamicWidthStaticHeight(AttributedTextShape aShape) {
		return new Dimension 
				(getDynamicWidth(aShape), 
				getStaticHeight());
	}
	Dimension getStaticcWidthDynamicHeight(AttributedTextShape aShape) {
		return new Dimension 
				(getStaticWidth(), 
				getDynamicHeight(aShape));
	}
	Dimension getDimension(AttributedTextShape aShape, ElementType anObjectValue) {
		if (anObjectValue == null)
			return getNullDimension();
		else if (dynamicWidth && dynamicHeight) 
			return getDynamicDimension(aShape);
	    else if (!dynamicWidth && !dynamicHeight)
			return getStaticDimension();
	    else if (dynamicWidth && !dynamicHeight) 
	    	return getDynamicWidthStaticHeight(aShape);
	    else
	    	return getStaticcWidthDynamicHeight(aShape);
		
	}
	void setBounds (AttributedTextShape aShape, ElementType anObjectValue) {
//		((FlexibleTextShape) aShape).setSize(getDimension(aShape, anObjectValue));
		Dimension size = getDimension(aShape, anObjectValue);
		Rectangle shapeBounds = new Rectangle ( aShape.getBounds());
		shapeBounds.width = size.width;
		shapeBounds.height = size.height;
		
		aShape.setBounds(shapeBounds);
		
//		Dimension size = null;
//		if (anObjectValue == null) {
//			size = 
//		}
//		if (anObjectValue == null) {
//			aShape.setHeight(0);
//			aShape.setWidth(0);
//			
//		} else if (dynamicWidth) {
//		} else {
//
//		aShape.setHeight(boxHeight
//				* (dynamicHeight ? Integer.parseInt(anObjectValue.toString()) : 1));
//
//		aShape.setWidth(boxWidth
//				* (dynamicWidth ? Integer.parseInt(anObjectValue.toString()) : 1));
//		}
		
	}
	public AttributedTextShape createShape(ElementType anObjectValue) {

//		try {
			AttributedTextShape aShape = createTextShape();
			aShape.setText(toString(anObjectValue));
			setAttributes(aShape, anObjectValue);
			setBounds(aShape, anObjectValue);

//			try {
			
//				if (objectValue == null) {
//					s.setHeight(0);
//					s.setWidth(0);
//					
//				} else {
//				s.setHeight(boxHeight
//						* (dynamicHeight ? Integer.parseInt(objectValue.toString()) : 1));
//
//				s.setWidth(boxWidth
//						* (dynamicWidth ? Integer.parseInt(objectValue.toString()) : 1));
//				}
//			} catch (Exception e) {
//				s.setHeight(boxHeight);
//				s.setWidth(boxWidth);
//			}
//			if (s instanceof AttributedShape) {
//			((AttributedShape) s).setFilled(solid);
//			((AttributedShape) s).setColor(objectColorManager.getColor(objectValue));
			
//			if (anObject != null && s instanceof AttributedShape) {
//				((AttributedShape) s).setColor(objectColorManager.getColor(anObject));
//			}
			

//			if (s instanceof TextShape) {
//				((TextShape) aShape).setText(toString(anObjectValue));
//				setAttributes(aShape, anObjectValue);
//			}

			return aShape;
//		} catch (InstantiationException e) {
//			return null;
//		} catch (IllegalAccessException e) {
//			return null;
//		}
	}
	
	public boolean getDynamicWidth() {
		return dynamicWidth;
	}
	
	public boolean isDisplayName() {
		return isDisplayName;
	}
	
	public void setDisplayName(boolean newVal) {
		isDisplayName = newVal;
	}

	public boolean getDynamicHeight() {
		return dynamicHeight;
	}
	public void setDynamicWidth(boolean dynamicWidth) {
		this.dynamicWidth = dynamicWidth;
	}

	public void setDynamicHeight(boolean dynamicHeight) {
		this.dynamicHeight = dynamicHeight;
	}

	public void setSolid(boolean solid) {
		this.filled = solid;
	}

	public boolean getSolid() {
		return filled;
	}
	public int getMinBoxWidth() {
		return minBoxWidth;
	}

	public void setMinBoxWidth(int minBoxWidth) {
		this.minBoxWidth = minBoxWidth;
	}

	public int getBoxWidth() {
		return boxWidth;
	}

	public int getBoxHeight() {
		return boxHeight;
	}
	
	public void setBoxWidth(int newVal) {
		boxWidth = newVal;
	}
	public void setBoxHeight(int newVal) {
		boxHeight = newVal;
	}

}
