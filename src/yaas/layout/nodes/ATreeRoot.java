package yaas.layout.nodes;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import bus.uigen.shapes.ALineModel;

//public class ATreeRoot extends AnAbstractTreeNode implements
public class ATreeRoot extends ATreeNode implements

		TreeNode {

	public ATreeRoot(BoundedShape shape, Object _o, ALineModel _l,
			ALineModel _l2) {

		super(shape, _o, _l, _l2);
	}
//	public void focusPosition(){
//		focusPosition(this);
//	}
//	@Override
//	public void focusPosition(TreeNode node) {
//
//		super.focusPosition(node);
////		if (node.getVector().size() > 0) {
////
////			super.positionVerticalLine(this);
////		}
//	}
//	public void setFontSize(int newSize) {
//		// TODO Auto-generated method stub
//		
//	}
//	public int getFontSize() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
}
