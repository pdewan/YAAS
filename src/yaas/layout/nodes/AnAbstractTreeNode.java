package yaas.layout.nodes;

import java.awt.Dimension;
import java.awt.Point;

//import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import shapes.BoundedShape;

import yaas.animators.AnimationUtil;
import bus.uigen.shapes.ALineModel;

public abstract class AnAbstractTreeNode extends
		ASimpleTreeNodeSkeleton implements TreeNode {
	
	public static final int MINIMUM_CHILD_OFFSET = 10;

	public AnAbstractTreeNode(BoundedShape shape, Object _o, ALineModel _l,
			ALineModel _l2) {

		super(shape, _o);
		if (_l != null)
		treeNodeShape.setLineToChildren(_l);
		if (_l2 != null)
		treeNodeShape.setLineToParent( _l2);
	}
	public AnAbstractTreeNode(BoundedShape shape, Object _o) {

		super(shape, _o);
		
	}

	/**************
	 * Position Methods // * @throws NullParentException
	 **************************************/
	public void focusPosition(TreeNode parent) {

		for (TreeNode node : parent.getVector()) {

			TreeNode previousChild = node.getPreviousChild();

			if (null == previousChild) {
				previousChild = parent; // only for spacing; we do not set
				// previousChild to be the parent
			} else if (!previousChild.getVector().isEmpty()) {
				previousChild = previousChild.getVector().get(
						previousChild.getVector().size() - 1);
			}

			if (!isVertical())// node is offset from parent
			// we do not add parent.width because the x-coordinates of shapes
			// can overlap as long as the y-coordinates do not
			{
				AnimationUtil
						.move(
								node,
								(parent.getX() + elementShapeXOffset),
								(int) (previousChild.getY()
										+ previousChild.getHeight() + elementShapeYOffset),
								animate, super.getHighlighting(), super
										.getColor());
			} else {// vertical
				AnimationUtil.move(node, (previousChild.getX()
						+ previousChild.getWidth() + elementShapeXOffset),
						(int) (parent.getY() + elementShapeYOffset),
						animate, super.getHighlighting(), super.getColor());
			}

			// if the node has children position them before other nodes
			if (node.getVector().size() > 0) {

				focusPosition(node);
				positionVerticalLine(node);
			}
			// if the node is not a root it is connected to a parent
			if (!(node instanceof ATreeRoot))
				positionHorizontalLine(node, horizontalLineYOffset);
		}
		// may need to undo, so should always position vertical line
//		if (parent.getVector().size() > 0) {

			positionVerticalLine(parent);
//		}
	}
	
	public int myX() {
		if (parent != null)
		return parent.getTreeNodeShape().getX() + parent.getTreeNodeShape().getWidth();
		else return getTreeNodeShape().getX();
		
	}
	
	public static int yOffsetOfSlotBelow (TreeNode aTreeNode) {
		return aTreeNode.getTreeNodeShape().getHeight() + aTreeNode.getElementShapeYOffset();
	}
	
	public int myY() {
		if (parent == null)
			return  getTreeNodeShape().getY();;
		int myIndex = myIndexInParent();
		return childY(parent, myIndex);
//		int retVal = parent.getY() + yOffsetOfSlotBelow(parent);
//
//		for (int i = 0; i < myIndex; i++) {
//			TreeNode prevChild = parent.getVector().get(i);
//			retVal += yOffsetOfSlotBelow(prevChild);
//		}
//		return retVal;
	}
	
	public static int childY (TreeNode aParent, int aChildPos) {
		int retVal = aParent.getY() + aParent.getElementShapeYOffset()*(1 + aParent.getNumDescendentsAbove(aChildPos));
//		for (int i = 0; i < aChildPos; i++) {
//			TreeNode prevChild = aParent.getVector().get(i);
//			retVal += yOffsetOfSlotBelow(prevChild);
//		}
		return retVal;
	}
	
	public int getNumDescendents() {
		return getNumDescendentsAbove(vector.size());
	}
	
	public int getNumDescendentsAbove(int childPos) {
	
		int retVal = childPos;
		for (int i = 0; i < childPos; i++) {
			TreeNode childNode = vector.get(i);
			retVal += childNode.getNumDescendents();
		}
		return retVal;
	}
	
	public int myVerticalLineSize() {
		if (vector.size() == 0) return 0;
		// find the last child whose sizeis not zero;
		TreeNode lastChild = getVector().get(
				getVector().size() - 1);
		TreeNodeShape childTreeNodeShape = lastChild.getTreeNodeShape();
		return 
				childTreeNodeShape.getLineToParent().getY() - treeNodeShape.getLineToChildren().getY();
//				- childTreeNodeShape.getHeight() / 2;
//		return computeVerticalLineSize().height;
//		int numElements = vector.size();
//		if (numElements == 0) return 0;
//		int lastChildPos = childY(this, numElements -1);
//		TreeNodeShape lastChildTreeNodeShape = vector.get(numElements -1).getTreeNodeShape();
//		return lastChildPos - getTreeNodeShape().getY() - getTreeNodeShape().getHeight() + lastChildTreeNodeShape.getHeight()/2 ;
//		int retVal = 0;
//		for (int i = 0; i < numElements - 2; i++) {
//			TreeNode aChild = getVector().get(i);
//			retVal += yOffsetOfSlotBelow(aChild);
//		}
//		return childY(this, numElements-1) - this.getY() - this.getHeight();
	}
	
	public void makeDisplayConsistent() {
		setXY();
		setChildrenXY();		
	}
	
	public void setVertilcalLineSize() {
		treeNodeShape.sythesizeHeightOfLineToChildren();
//		int originalHeight = treeNodeShape.getLineToChildren().getHeight();
//		int newHeight = myVerticalLineSize();
//		if (originalHeight == newHeight)
//			return;
//
//		treeNodeShape.getLineToChildren().setHeight(newHeight);
//		treeNodeShape.recomputeDependents();
	}
	
	public void setXY() {
//		treeNodeShape.setX(myX());
		treeNodeShape.setY(myY());
		treeNodeShape.setX(myX());

	}
	
	public void setChildrenXY() {
		for (TreeNode aChild:vector) {
			aChild.setXY();
		}
	}
	
	
	
	

//	public void positionVerticalLine(TreeNode node) {
//
//		// mask instance variable 'verticalLine'
//		ALineModel verticalLine = node.getVerticalLine();
//
//		AnimationUtil.move(verticalLine, node.getX() + verticalLineXOffset,
//				node.getY() + node.getHeight(), animate, super
//						.getHighlighting(), super.getColor());
//
//		verticalLine.setWidth(0);
//
//		TreeNode lastChild = node.getVector().get(
//				node.getVector().size() - 1);
//		verticalLine.setHeight(lastChild.getY() - node.getY()
//				- lastChild.getHeight() / 2);
//	}
	public Point computeVerticalLinePosition() {
		return new Point (getX() + getVerticalLineXOffset(), getY() + getHeight());
	}
	public Dimension computeVerticalLineSize() {
		TreeNode lastChild = getVector().get(
				getVector().size() - 1);
		return new Dimension (
				0,
				lastChild.getY() - getY()
				- lastChild.getHeight() / 2);
				
		
	}
	public static void positionVerticalLine(TreeNode node) {

		// mask instance variable 'verticalLine'
		ALineModel verticalLine = node.getVerticalLine();

		AnimationUtil.move(verticalLine, node.getX() + node.getVerticalLineXOffset(),
				node.getY() + node.getHeight(), node.isAnimate(), node
						.getHighlighting(), node.getColor());

		verticalLine.setWidth(0);
		if (node.getVector().size() == 0) {
			verticalLine.setHeight(0);
		
			return;
		}

		TreeNode lastChild = node.getVector().get(
				node.getVector().size() - 1);
		verticalLine.setHeight(lastChild.getY() - node.getY()
				- lastChild.getHeight() / 2);
	}
	public static void  positionHorizontalLine(TreeNode node) {
		positionHorizontalLine(node, node.getHorizontalLineYOffset());
	}


//	public void positionHorizontalLine(TreeNode node,
//			double horizontalLineYOffset) {
//
//		// mask instance variable 'horizontalLine'
//		ALineModel horizontalLine = node.getHorizontalLine();
//
////		AnimationUtil.move(horizontalLine, node.getParent().getX()
////				+ node.getParent().getWidth() / 2,
////				(int) (node.getY() + horizontalLineYOffset), animate, super
////						.getHighlighting(), super.getColor());
//		AnimationUtil.move(horizontalLine, node.getParent().getX()
//				+ node.getParent().getWidth() / 2,
//				(int) (node.getY() + node.getHorizontalLineYOffset()), animate, super
//						.getHighlighting(), super.getColor());
//
//		horizontalLine.setHeight(0);
//		horizontalLine.setWidth(node.getX() - node.getParent().getX()
//				- node.getParent().getWidth() / 2);
//	}
	
	public Point computeHorizontalLinePosition() {
		return new Point (
				getParent().getX()
				+ getParent().getWidth() / 2,
				(int) (getY() + getHorizontalLineYOffset()));
	}
	
	public Dimension computeHorizontalLineSize() {
		return new Dimension (
				getX() - getParent().getX() - getParent().getWidth() / 2,
				0);
	}
	
	public static void positionHorizontalLine(TreeNode node,
			double horizontalLineYOffset) {

		// mask instance variable 'horizontalLine'
		ALineModel horizontalLine = node.getHorizontalLine();

//		AnimationUtil.move(horizontalLine, node.getParent().getX()
//				+ node.getParent().getWidth() / 2,
//				(int) (node.getY() + horizontalLineYOffset), animate, super
//						.getHighlighting(), super.getColor());
		AnimationUtil.move(horizontalLine, node.getParent().getX()
				+ node.getParent().getWidth() / 2,
				(int) (node.getY() + node.getHorizontalLineYOffset()), node.isAnimate(), node
						.getHighlighting(), node.getColor());

		horizontalLine.setHeight(0);
		horizontalLine.setWidth(node.getX() - node.getParent().getX()
				- node.getParent().getWidth() / 2);
	}
	
	public void copy(Object aReference) {
		
	}
	
}
