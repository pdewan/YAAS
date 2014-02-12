package yaas.shapemappers;

import bus.uigen.shapes.ALabelModel;
import bus.uigen.shapes.AStringModel;
import bus.uigen.shapes.ATextModel;
import shapes.FlexibleTextShape;
import util.misc.ObjectColorManager;

public class AnObjectToLabelModel<ElementType> extends AnObjectToTextShape<ElementType>{

	public AnObjectToLabelModel(ObjectColorManager anObjectColorManager) {
		super(anObjectColorManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected FlexibleTextShape createTextShape() {
		return new ALabelModel();
	}

}
