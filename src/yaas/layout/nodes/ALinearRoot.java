package yaas.layout.nodes;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import bus.uigen.shapes.ALineModel;

public class ALinearRoot extends AnAbstractLinearElement implements
		LinearElement {

	public ALinearRoot(BoundedShape shape, Object _o, ALineModel _l,
			ALineModel _l2) {

		super(shape, _o, _l, _l2);
	}
	public void focusPosition(){
		focusPosition(this);
	}
	@Override
	public void focusPosition(LinearElement node) {

		super.focusPosition(node);
		if (node.getVector().size() > 0) {

			super.positionVerticalLine(this);
		}
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
