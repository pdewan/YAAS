package yaas.shapemappers;

import java.awt.Dimension;

import bus.uigen.shapes.AStringModel;
import shapes.FlexibleTextShape;
import util.misc.Common;
import util.misc.ObjectColorManager;

public class AnObjectToStringModel<ElementType> extends AnObjectToTextShape<ElementType>{

	public AnObjectToStringModel(ObjectColorManager anObjectColorManager) {
		super(anObjectColorManager);
		// TODO Auto-generated constructor stub
	}
	public AnObjectToStringModel() {
		super();
		// TODO Auto-generated constructor stub
	}
//	void setAttributes (FlexibleTextShape aShape, ElementType anObjectValue) {
////		aShape.setFilled(filled);
////		aShape.setColor(objectColorManager.getColor(anObjectValue));
////		aShape.setColor(objectColorManager.getColor(anObjectValue));
//	}

//	Dimension getDynamicDimension(FlexibleTextShape aShape, ElementType anObjectValue) {
//		return new Dimension (-1, -1);
//	}
//		

	@Override
	protected FlexibleTextShape createTextShape() {
		return new AStringModel();
	}

}
