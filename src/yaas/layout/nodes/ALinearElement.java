package yaas.layout.nodes;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import bus.uigen.shapes.ALineModel;

public class ALinearElement extends AnAbstractLinearElement implements  LinearElement {

	public ALinearElement(BoundedShape shape, Object o, ALineModel l,
			ALineModel l2) {
		super(shape, o, l, l2);
	}

	@Override
	public void focusPosition() {
		focusPosition(this);
		
	}

	public void setFontSize(int newSize) {
		// TODO Auto-generated method stub
		
	}

	public int getFontSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public LinearElement getNextChild(int aMyIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean copy(BoundedShape aReference) {
		return false;
	}

	public boolean copyable(BoundedShape aReference) {
		// TODO Auto-generated method stub
		return false;
	}

}