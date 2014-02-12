package yaas.layout.nodes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.models.AListenableVector;
import util.models.ListenableVector;
import bus.uigen.shapes.ALineModel;

public abstract class ASimpleTreeNodeSkeleton implements
		VisualizerElement<TreeNode>, TreeNode {

//	protected SimpleShape shape;
	boolean visuallyUnparented;
	protected Object object;
	TreeNodeShape treeNodeShape;

	//	protected ALineModel verticalLine, horizontalLine;
	protected int horizontalLineYOffset, verticalLineXOffset,
			elementShapeYOffset, elementShapeXOffset;
	protected TreeNode parent, previousChild;
	protected ListenableVector<TreeNode> vector = new AListenableVector<TreeNode>();
	protected boolean animate;
	private Color highlighting = Color.black;
	private Color color = Color.black;
	private boolean vertical = false;

	public ASimpleTreeNodeSkeleton(BoundedShape shape2, Object _o) {
		treeNodeShape = new ATreeNodeShape(shape2, this);
//		shape = shape2;
		object = _o;
		elementShapeYOffset = 30;
	}
	public TreeNodeShape getTreeNodeShape() {
		return treeNodeShape;
	}
	public void setTreeNodeShape(TreeNodeShape treeNodeShape) {
		this.treeNodeShape = treeNodeShape;
	}
	public void setVertical(boolean b){
		vertical = b;
	}
	public boolean isVertical(){
		return vertical;
	}
	public void setAnimate(boolean newVal) {
		animate = newVal;
	}

	public void setParent(TreeNode p) {
		parent = p;
//		p.setChildrenXY();
//		recomputeDependentsInSubTree();
	}

	public void recomputeDependentsInSubTree() {
		if (treeNodeShape != null) {
			treeNodeShape.recomputeDependents();
		}
		for (TreeNode  aTreeNode:vector) {
			aTreeNode.recomputeDependentsInSubTree();
		}
//		treeNodeShape.sythesizeVerticalLineHeight();
	}
	

	public void setPreviousChild(TreeNode p) {
		previousChild = p;
	}
	
	

	/************** Getters *****************************/
	public TreeNode getNextChild(int aMyIndex) {
		return aMyIndex < getParent().getVector().size() - 1?
				getParent().getVector().get(aMyIndex+ 1): null;
	}
	
	public TreeNode getPreviousChild() {
		return previousChild;
	}

	public TreeNode getParent() {
		return parent;
	}
	
	public TreeNode getRoot() {
		if (parent == null)
			return this;
		else return parent.getRoot();
	}

	public ListenableVector<TreeNode> getVector() {

		return vector;
	}

	public int getHorizontalLineYOffset() {
		return horizontalLineYOffset;
	}

	public int getVerticalLineXOffset() {
		return verticalLineXOffset;
	}

	public int getElementShapeYOffset() {
		return elementShapeYOffset;
	}

	public int getElementShapeXOffset() {
		return elementShapeXOffset;
	}

	public boolean isVisuallyUnparented() {
		return visuallyUnparented;
	}
	public void setVisuallyUnparented(boolean visuallyUnparented) {
		this.visuallyUnparented = visuallyUnparented;
	}
	public void setHorizontalLineYOffset(int newY) {
		horizontalLineYOffset = newY;
	}

	public void setVerticalLineXOffset(int newX) {
		verticalLineXOffset = newX;
	}

	public void setElementShapeYOffset(int newY) {
		elementShapeYOffset = newY;
	}

	public void setElementShapeXOffset(int newX) {
		elementShapeXOffset = newX;
	}

	public FlexibleShape getShape() {
		return (FlexibleShape) treeNodeShape.getContentShape();
	}

	public ALineModel getVerticalLine() {
		return (ALineModel) treeNodeShape.getLineToChildren();
	}

	public ALineModel getHorizontalLine() {
		return (ALineModel) treeNodeShape.getLineToParent();
	}

	public abstract void focusPosition(TreeNode node);

	public abstract void focusPosition();

	public Object getObject() {
		return object;
	}

	public void setObject(Object o) {
		object = o;
	}

	public boolean isAnimate() {
		return animate;
	}
	public int getHeight() {
		return treeNodeShape.getContentShape().getHeight();
	}

	public int getWidth() {
		return treeNodeShape.getContentShape().getWidth();
	}

	public int getX() {
		return treeNodeShape.getContentShape().getX();
	}

	public int getY() {
		return treeNodeShape.getContentShape().getY();
	}

	public void setHeight(int arg0) {
		treeNodeShape.getContentShape().setHeight(arg0);
	}

	public void setWidth(int arg0) {
		treeNodeShape.getContentShape().setWidth(arg0);

	}

	public void setX(int arg0) {
		treeNodeShape.getContentShape().setX(arg0);

	}

	public void setY(int arg0) {
		treeNodeShape.getContentShape().setY(arg0);

	}

	public void setHighlighting(Color theColor) {
		highlighting = theColor;
	}

	@util.annotations.Visible(false)
	public Color getHighlighting() {
		return highlighting;
	}

	public void setColor(Color theColor) {
		color = theColor;
	}

	@util.annotations.Visible(false)
	public Color getColor() {
		return color;
	}

	public boolean contains(Point2D arg0) {
		return ((FlexibleShape) treeNodeShape.getContentShape()).contains(arg0);
	}

	public boolean contains(Rectangle2D arg0) {
		return ((FlexibleShape) treeNodeShape.getContentShape()).contains(arg0);
	}

	public boolean contains(double arg0, double arg1) {
		return ((FlexibleShape) treeNodeShape.getContentShape()).contains(arg0, arg1);
	}

	public boolean contains(double arg0, double arg1, double arg2, double arg3) {
		return ((FlexibleShape) treeNodeShape.getContentShape()).contains(arg0, arg1, arg2, arg3);
	}

	public Rectangle getBounds() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getBounds();
	}

	public Rectangle2D getBounds2D() {
	return ((FlexibleShape) treeNodeShape.getContentShape()).getBounds2D();
	}

	public Point getCenter() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getCenter();
	}

	public Point getEastEnd() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getEastEnd();
	}

	public Font getFont() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getFont();
	}

	public Point getNECorner() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getNECorner();
	}

	public Point getNWCorner() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getNWCorner();
	}

	public Point getNorthEnd() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getNorthEnd();
	}

	public Paint getPaint() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getPaint();
	}

	public PathIterator getPathIterator(AffineTransform arg0) {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getPathIterator(arg0);
	}

	public PathIterator getPathIterator(AffineTransform arg0, double arg1) {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getPathIterator(arg0, arg1);
	}

	public Point getPosition() {
	return ((FlexibleShape) treeNodeShape.getContentShape()).getPosition();
	}

	public Point getSECorner() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getSECorner();
	}

	public Point getSWCorner() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getSWCorner();
	}

	public Dimension getSize() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getSize();
	}

	public Point getSouthEnd() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getSouthEnd();
	}

	public Stroke getStroke() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getStroke();
	}

	public Point getWestEnd() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getWestEnd();
	}

	public int getZIndex() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).getZIndex();
	}

	public boolean intersects(Rectangle2D arg0) {
		return ((FlexibleShape) treeNodeShape.getContentShape()).intersects(arg0);
	}

	public boolean intersects(double arg0, double arg1, double arg2, double arg3) {
		return ((FlexibleShape) treeNodeShape.getContentShape()).intersects(arg0, arg1, arg2, arg3);
	}

	public boolean is3D() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).is3D();
	}

	public boolean isFilled() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).isFilled();
	}

	public boolean isRounded() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).isRounded();
	}

	public void moveX(int arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).moveX(arg0);

	}

	public void moveY(int arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).moveY(arg0);
	}

	public Object remoteClone() {
		return ((FlexibleShape) treeNodeShape.getContentShape()).remoteClone();
	}

	public void set3D(boolean arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).set3D(arg0);

	}

	public void setBounds(Rectangle arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setBounds(arg0);

	}

	public void setBounds(Point arg0, Point arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setBounds(arg0, arg1);

	}

	public void setBounds(int arg0, int arg1, int arg2, int arg3) {
		((FlexibleShape) treeNodeShape.getContentShape()).setBounds(arg0, arg1, arg2, arg3);

	}

	public void setCenter(Point arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setCenter(arg0);

	}

	public void setCenter(int arg0, int arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setCenter(arg0, arg1);

	}

	public void setDashedStroke() {
		((FlexibleShape) treeNodeShape.getContentShape()).setDashedStroke();

	}

	public void setDottedStroke() {
		((FlexibleShape) treeNodeShape.getContentShape()).setDottedStroke();

	}

	public void setEastEnd(Point arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setEastEnd(arg0);

	}

	public void setEastEnd(int arg0, int arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setEastEnd(arg0, arg1);

	}

	public void setFilled(boolean arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setFilled(arg0);

	}

	public void setFont(Font arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setFont(arg0);

	}

	public void setNECorner(Point arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setNECorner(arg0);

	}

	public void setNECorner(int arg0, int arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setNECorner(arg0, arg1);

	}

	public void setNWCorner(Point arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setNWCorner(arg0);

	}

	public void setNWCorner(int arg0, int arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setNWCorner(arg0, arg1);

	}

	public void setNorthEnd(Point arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setNorthEnd(arg0);

	}

	public void setNorthEnd(int arg0, int arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setNorthEnd(arg0, arg1);

	}

	public void setPaint(Paint arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setPaint(arg0);

	}

	public void setPosition(Point arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setPosition(arg0);

	}

	public void setRounded(boolean arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setRounded(arg0);

	}

	public void setSECorner(Point arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setSECorner(arg0);

	}

	public void setSECorner(int arg0, int arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setSECorner(arg0, arg1);

	}

	public void setSWCorner(Point arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setSWCorner(arg0);

	}

	public void setSWCorner(int arg0, int arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setSWCorner(arg0, arg1);

	}

	public void setSize(Dimension arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setSize(arg0);

	}

	public void setSize(int arg0, int arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setSize(arg0, arg1);

	}

	public void setSolidStroke() {
		((FlexibleShape) treeNodeShape.getContentShape()).setSolidStroke();

	}

	public void setSouthEnd(Point arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setSouthEnd(arg0);

	}

	public void setSouthEnd(int arg0, int arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setSouthEnd(arg0, arg1);

	}

	public void setStroke(Stroke arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setStroke(arg0);

	}

	public void setWestEnd(Point arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setWestEnd(arg0);

	}

	public void setWestEnd(int arg0, int arg1) {
		((FlexibleShape) treeNodeShape.getContentShape()).setWestEnd(arg0, arg1);

	}

	public void setZIndex(int arg0) {
		((FlexibleShape) treeNodeShape.getContentShape()).setZIndex(arg0);

	}
	public void copy(Object aReference) {
		
	}
	public int myIndexInParent() {
		return getParent().getVector().indexOf(this);
	}
}
