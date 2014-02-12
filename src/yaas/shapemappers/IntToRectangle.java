package yaas.shapemappers;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import shapes.FlexibleShape;
import util.misc.AnObjectColorManager;
import util.misc.ObjectColorManager;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.shapes.ARectangleModel;

public class IntToRectangle implements ObjectToShapeTranslator<Integer> {
	ObjectColorManager objectColorManager = new AnObjectColorManager();
//	Color[] colors = {
//			Color.BLACK, 
//			Color.BLUE, 
//			Color.CYAN, 
//			Color.DARK_GRAY, 
//			Color.GRAY, 
//			Color.GREEN, 
//			Color.LIGHT_GRAY, 
//			Color.MAGENTA, 
//			Color.PINK,
//			Color.RED,
//			Color.WHITE,
//			Color.YELLOW
//	};
	
//	List<Color> remainingColors = util.misc.Common.arrayToArrayList(colors);	
//	
//	Map<Integer, Color> intToColor = new HashMap();
	
	private int barWidth = 10;
	private int scaleFactor = 10;
	
	
	public IntToRectangle (ObjectColorManager anObjectColorManager) {
		objectColorManager = anObjectColorManager;
		initializerColors();

//		objectColorManager.clearPredefinedColors();
	}
	 
	public void initializerColors() {
		objectColorManager.setSurroundingColors(new Color[] {AttributeNames.CAROLINA_BLUE});

	}

	public IntToRectangle () {
		initializerColors();
//		objectColorManager.setSurroundingColors(new Color[] {AttributeNames.CAROLINA_BLUE});
//		objectColorManager.clearPredefinedColors();
	}
	public  int dataToHeight(Integer val) {
		if (val == null)
			return 0;
		return val*scaleFactor;
	}
//	Color getColor(Integer val) {
//		if (val == null)
//			return null;
//		Color retVal = intToColor.get(val);
//		if (retVal != null)
//			return retVal;
//		if (remainingColors.isEmpty()) {
//			remainingColors = util.misc.Common.arrayToArrayList(colors);
//		}
//		int lastIndex = remainingColors.size() - 1;
//		Color nextColor = remainingColors.get(lastIndex);
//		remainingColors.remove(lastIndex);
//		intToColor.put(val, nextColor);
//		return nextColor;
//	}
	public FlexibleShape translate(Integer val) {
		if (val == null)
			return  new ARectangleModel(0, 0, 0, 0);
//		int height = val * scaleFactor;	
		int height = dataToHeight(val);
		FlexibleShape retVal = new ARectangleModel(0, 0, barWidth, height);
//		retVal.setColor(getColor(val));
		retVal.setColor(objectColorManager.getColor(val));
//		retVal.setFilled(true);
//		currX += xSpacing;
		return retVal;
	}
	
	public FlexibleShape calculateNewShape(BoundedShape anExistingShape, Integer val) {
		int newHeight = dataToHeight(val);
		int newY = anExistingShape.getY() - (newHeight - anExistingShape.getHeight());
		int newX = anExistingShape.getX();
//		OEShape retVal = new ARectangleModel(newX, newY, barWidth, newHeight);
		FlexibleShape retVal = translate(val);
		retVal.setX(newX);
		retVal.setY(newY);
		retVal.setHeight(newHeight);

//		Color newColor = intToColor.get(val);
		Color newColor = objectColorManager.getColor(val);
		if (retVal instanceof AttributedShape)
		((AttributedShape) retVal).setColor(newColor);		
		return retVal;
	}

//	public void translate(SimpleShape anExistingShape, Integer newVal) {
//		int height = dataToHeight(newVal);
//		anExistingShape.setBounds(new Rectangle(anExistingShape.getX(), anExistingShape.));
//		anExistingShape.setHeight(newVal);
//	
//	}

	

}
