package yaas.shapemappers;

import java.awt.Dimension;

import bus.uigen.shapes.AStringModel;
import bus.uigen.shapes.ATextModel;
import shapes.AttributedTextShape;
import shapes.FlexibleTextShape;
import util.misc.Common;
import util.misc.ObjectColorManager;

public class AnObjectToTextModel<ElementType> extends AnObjectToTextShape<ElementType>{
	public static final int TEXT_BOX_MARGIN = 10;

	public AnObjectToTextModel(ObjectColorManager anObjectColorManager) {
		super(anObjectColorManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected FlexibleTextShape createTextShape() {
		return new ATextModel();
	}
	int getDynamicWidth(AttributedTextShape aShape) {
		return super.getDynamicWidth(aShape) + TEXT_BOX_MARGIN ;
	}

}
