package yaas.layout.nodes;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import shapes.BoundedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import shapes.RectangleDiagonalPoints;
import shapes.ShapesUtility;
import util.misc.Common;
import yaas.visualizers.collection.CollectionVisualizer;
import yaas.visualizers.collection.tree.TreeVisualizer;
import bus.uigen.shapes.ALineModel;

public class ATreeNode extends AnAbstractTreeNode implements TreeNode {
	CollectionVisualizer treeVisualizer;

	public ATreeNode( BoundedShape shape, Object o, ALineModel l, ALineModel l2) {
		super(shape, o, l, l2);
	}

	public ATreeNode(BoundedShape shape, Object o) {
		super(shape, o);
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

	public String toString() {
		if (getVector().size() > 1)
			return treeNodeShape.toString() + "," + getVector().toString();
		else
			return treeNodeShape.toString();
	}
	
	// removing ths as it adds to much layout manager awareness

//	public void copy(TreeNode aTreeNode) {
//		if (treeNodeShape == null) {
//			treeNodeShape = new ATreeNodeShape(null, aTreeNode);
//		}
//		treeNodeShape.copy(aTreeNode.getTreeNodeShape());
//		List<TreeNode> children = aTreeNode.getVector();
//
//		int sizeDifference = vector.size() - children.size();
//		if (sizeDifference > 0) {
//			for (int i = 0; i < sizeDifference; i++) {
//				vector.remove(vector.size() - 1);
//			}
//		} else {
//			for (int i = 0; i < -sizeDifference; i++) {
//				TreeNode childNode = new ATreeNode(null, null, null, null);
//				vector.add(childNode);
//				childNode.setParent(this);
//			}
//		}
//		for (int i = 0; i < children.size(); i++) {
//
//			vector.get(i).copy(children.get(i));
//
//		}
//	}

	// public void recomputeDependentsInSubTree() {
	// if (treeNodeShape != null) {
	// treeNodeShape.recomputeDependents();
	// }
	// for (TreeNode aTreeNode:vector) {
	// aTreeNode.recomputeDependentsInSubTree();
	// }
	// }

	public List<TreeNodeShape> getTreeNodeShapesInSubTree() {
		// TreeNode aTreeNode = valueToTreeNode.get(anObject);
		List<TreeNodeShape> treeNodeShapes = new ArrayList();
		fillTreeNodeShapes(treeNodeShapes, this);

		return treeNodeShapes;

	}

	public List<Rectangle> getTreeNodeShapeBoundsInSubTree() {
		// TreeNode aTreeNode = valueToTreeNode.get(anObject);
		List<Rectangle> retVal = new ArrayList();
		List<TreeNodeShape> shapes = getTreeNodeShapesInSubTree();
		for (TreeNodeShape aTreeNodeShape : shapes) {
			retVal.add(aTreeNodeShape.getBounds());
		}
		return retVal;
	}

	public void setZeroBoundsInSubTree() {
		// TreeNode aTreeNode = valueToTreeNode.get(anObject);
		List<TreeNodeShape> shapes = getTreeNodeShapesInSubTree();
		for (TreeNodeShape aTreeNodeShape : shapes) {
			aTreeNodeShape.setWidth(0);
			aTreeNodeShape.setHeight(0);
		}
	}

	public static void fillTreeNodeShapes(List<TreeNodeShape> treeNodeShapes,
			TreeNode aTreeNode) {
		// TreeNode aTreeNode = valueToTreeNode.get(anObject);
		treeNodeShapes.add(aTreeNode.getTreeNodeShape());

		List<TreeNode> children = aTreeNode.getVector();

		for (TreeNode aChild : children) {
			fillTreeNodeShapes(treeNodeShapes, aChild);
		}

	}

	public RectangleDiagonalPoints getSubTreeDiagonalPoints() {
		RectangleDiagonalPoints retVal = treeNodeShape.getAreaCovered();
		for (TreeNode aChild : vector) {
			retVal = ShapesUtility.areaCovering(retVal,
					aChild.getSubTreeDiagonalPoints());
		}
		return retVal;
	}

	public Rectangle getSubTreeAreaBounds() {
		return ShapesUtility.toRectangle(getSubTreeDiagonalPoints());
	}

	public boolean copy(BoundedShape aReference) {
		return false;
		// TODO Auto-generated method stub

	}

	public boolean copyable(BoundedShape aReference) {
		// TODO Auto-generated method stub
		return false;
	}
	public CollectionVisualizer getVisualizer() {
		return treeVisualizer;
	}
	public void setTreeVisualizer(CollectionVisualizer newVal) {
		treeVisualizer = newVal;
	}

}