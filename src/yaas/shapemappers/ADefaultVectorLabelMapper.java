package yaas.shapemappers;

import shapes.BoundedShape;
import shapes.ShapesAPI;
import util.misc.Common;
import util.models.ListenableVector;
import bus.uigen.shapes.AStringModel;

public class ADefaultVectorLabelMapper implements LabelMapper{

	public BoundedShape getLabelShape(Object aParent, Object aChild) {
		try {
		ListenableVector aListParent = (ListenableVector) aParent;
		int index =  aListParent.indexOf(aChild);
		return getLabelShape (aParent, index);
		} catch (Exception e) {
			e.printStackTrace();
			return new AStringModel("?");
		}
//		String retVal = index < 0?"":""+index;
//		int height = Common.getDefaultFontHeight();
//		int width = Common.getDefaultFontStringWidth(retVal);
//		return new AStringModel (retVal, 0, 0, width, height);
	}

	public BoundedShape getLabelShape(Object aParent, int anIndex) {
		String label = "";
		if (aParent != null) {
			
		
		label = anIndex < 0?"":""+anIndex;
		}

//		int height = Common.getDefaultFontHeight();
//		int width = Common.getDefaultFontStringWidth(label);
		int height = Common.getFontHeight(ShapesAPI.getDefaultFont(), ShapesAPI.getDefaultFontSize());
		int width = Common.getFontStringWidth(ShapesAPI.getDefaultFont(), label, ShapesAPI.getDefaultFontSize());

		return new AStringModel (label, 0, 0, width, height);
	}

}
