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

public abstract class AVisualizerElementSkeleton<ElementType> implements
		VisualizerElement<ElementType> {

	protected BoundedShape shape;
	protected Object object;
	protected ALineModel verticalLine, horizontalLine;
	protected int horizontalLineYOffset, verticalLineXOffset,
			elementShapeYOffset, elementShapeXOffset;
	protected ElementType parent, previousChild;
	protected ListenableVector<ElementType> vector = new AListenableVector<ElementType>();
	protected boolean animate;
	private Color highlighting = Color.black;
	private Color color = Color.black;
	private boolean vertical = false;

	public AVisualizerElementSkeleton(BoundedShape shape2, Object _o) {
		shape = shape2;
		object = _o;
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

	public void setParent(ElementType p) {
		parent = p;
	}

	public void setPreviousChild(ElementType p) {
		previousChild = p;
	}

	/************** Getters *****************************/
	public ElementType getPreviousChild() {
		return previousChild;
	}

	public ElementType getParent() {
		return parent;
	}

	public ListenableVector<ElementType> getVector() {

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
		return (FlexibleShape) shape;
	}

	public ALineModel getVerticalLine() {
		return verticalLine;
	}

	public ALineModel getHorizontalLine() {
		return horizontalLine;
	}

	public abstract void focusPosition(ElementType node);

	public abstract void focusPosition();

	public Object getObject() {
		return object;
	}

	public void setObject(Object o) {
		object = o;
	}

	public int getHeight() {
		return shape.getHeight();
	}

	public int getWidth() {
		return shape.getWidth();
	}

	public int getX() {
		return shape.getX();
	}

	public int getY() {
		return shape.getY();
	}

	public void setHeight(int arg0) {
		shape.setHeight(arg0);
	}

	public void setWidth(int arg0) {
		shape.setWidth(arg0);

	}

	public void setX(int arg0) {
		shape.setX(arg0);

	}

	public void setY(int arg0) {
		shape.setY(arg0);

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
		return ((FlexibleShape) shape).contains(arg0);
	}

	public boolean contains(Rectangle2D arg0) {
		return ((FlexibleShape) shape).contains(arg0);
	}

	public boolean contains(double arg0, double arg1) {
		return ((FlexibleShape) shape).contains(arg0, arg1);
	}

	public boolean contains(double arg0, double arg1, double arg2, double arg3) {
		return ((FlexibleShape) shape).contains(arg0, arg1, arg2, arg3);
	}

	public Rectangle getBounds() {
		return ((FlexibleShape) shape).getBounds();
	}

	public Rectangle2D getBounds2D() {
	return ((FlexibleShape) shape).getBounds2D();
	}

	public Point getCenter() {
		return ((FlexibleShape) shape).getCenter();
	}

	public Point getEastEnd() {
		return ((FlexibleShape) shape).getEastEnd();
	}

	public Font getFont() {
		return ((FlexibleShape) shape).getFont();
	}

	public Point getNECorner() {
		return ((FlexibleShape) shape).getNECorner();
	}

	public Point getNWCorner() {
		return ((FlexibleShape) shape).getNWCorner();
	}

	public Point getNorthEnd() {
		return ((FlexibleShape) shape).getNorthEnd();
	}

	public Paint getPaint() {
		return ((FlexibleShape) shape).getPaint();
	}

	public PathIterator getPathIterator(AffineTransform arg0) {
		return ((FlexibleShape) shape).getPathIterator(arg0);
	}

	public PathIterator getPathIterator(AffineTransform arg0, double arg1) {
		return ((FlexibleShape) shape).getPathIterator(arg0, arg1);
	}

	public Point getPosition() {
	return ((FlexibleShape) shape).getPosition();
	}

	public Point getSECorner() {
		return ((FlexibleShape) shape).getSECorner();
	}

	public Point getSWCorner() {
		return ((FlexibleShape) shape).getSWCorner();
	}

	public Dimension getSize() {
		return ((FlexibleShape) shape).getSize();
	}

	public Point getSouthEnd() {
		return ((FlexibleShape) shape).getSouthEnd();
	}

	public Stroke getStroke() {
		return ((FlexibleShape) shape).getStroke();
	}

	public Point getWestEnd() {
		return ((FlexibleShape) shape).getWestEnd();
	}

	public int getZIndex() {
		return ((FlexibleShape) shape).getZIndex();
	}

	public boolean intersects(Rectangle2D arg0) {
		return ((FlexibleShape) shape).intersects(arg0);
	}

	public boolean intersects(double arg0, double arg1, double arg2, double arg3) {
		return ((FlexibleShape) shape).intersects(arg0, arg1, arg2, arg3);
	}

	public boolean is3D() {
		return ((FlexibleShape) shape).is3D();
	}

	public boolean isFilled() {
		return ((FlexibleShape) shape).isFilled();
	}

	public boolean isRounded() {
		return ((FlexibleShape) shape).isRounded();
	}

	public void moveX(int arg0) {
		((FlexibleShape) shape).moveX(arg0);

	}

	public void moveY(int arg0) {
		((FlexibleShape) shape).moveY(arg0);
	}

	public Object remoteClone() {
		return ((FlexibleShape) shape).remoteClone();
	}

	public void set3D(boolean arg0) {
		((FlexibleShape) shape).set3D(arg0);

	}

	public void setBounds(Rectangle arg0) {
		((FlexibleShape) shape).setBounds(arg0);

	}

	public void setBounds(Point arg0, Point arg1) {
		((FlexibleShape) shape).setBounds(arg0, arg1);

	}

	public void setBounds(int arg0, int arg1, int arg2, int arg3) {
		((FlexibleShape) shape).setBounds(arg0, arg1, arg2, arg3);

	}

	public void setCenter(Point arg0) {
		((FlexibleShape) shape).setCenter(arg0);

	}

	public void setCenter(int arg0, int arg1) {
		((FlexibleShape) shape).setCenter(arg0, arg1);

	}

	public void setDashedStroke() {
		((FlexibleShape) shape).setDashedStroke();

	}

	public void setDottedStroke() {
		((FlexibleShape) shape).setDottedStroke();

	}

	public void setEastEnd(Point arg0) {
		((FlexibleShape) shape).setEastEnd(arg0);

	}

	public void setEastEnd(int arg0, int arg1) {
		((FlexibleShape) shape).setEastEnd(arg0, arg1);

	}

	public void setFilled(boolean arg0) {
		((FlexibleShape) shape).setFilled(arg0);

	}

	public void setFont(Font arg0) {
		((FlexibleShape) shape).setFont(arg0);

	}

	public void setNECorner(Point arg0) {
		((FlexibleShape) shape).setNECorner(arg0);

	}

	public void setNECorner(int arg0, int arg1) {
		((FlexibleShape) shape).setNECorner(arg0, arg1);

	}

	public void setNWCorner(Point arg0) {
		((FlexibleShape) shape).setNWCorner(arg0);

	}

	public void setNWCorner(int arg0, int arg1) {
		((FlexibleShape) shape).setNWCorner(arg0, arg1);

	}

	public void setNorthEnd(Point arg0) {
		((FlexibleShape) shape).setNorthEnd(arg0);

	}

	public void setNorthEnd(int arg0, int arg1) {
		((FlexibleShape) shape).setNorthEnd(arg0, arg1);

	}

	public void setPaint(Paint arg0) {
		((FlexibleShape) shape).setPaint(arg0);

	}

	public void setPosition(Point arg0) {
		((FlexibleShape) shape).setPosition(arg0);

	}

	public void setRounded(boolean arg0) {
		((FlexibleShape) shape).setRounded(arg0);

	}

	public void setSECorner(Point arg0) {
		((FlexibleShape) shape).setSECorner(arg0);

	}

	public void setSECorner(int arg0, int arg1) {
		((FlexibleShape) shape).setSECorner(arg0, arg1);

	}

	public void setSWCorner(Point arg0) {
		((FlexibleShape) shape).setSWCorner(arg0);

	}

	public void setSWCorner(int arg0, int arg1) {
		((FlexibleShape) shape).setSWCorner(arg0, arg1);

	}

	public void setSize(Dimension arg0) {
		((FlexibleShape) shape).setSize(arg0);

	}

	public void setSize(int arg0, int arg1) {
		((FlexibleShape) shape).setSize(arg0, arg1);

	}

	public void setSolidStroke() {
		((FlexibleShape) shape).setSolidStroke();

	}

	public void setSouthEnd(Point arg0) {
		((FlexibleShape) shape).setSouthEnd(arg0);

	}

	public void setSouthEnd(int arg0, int arg1) {
		((FlexibleShape) shape).setSouthEnd(arg0, arg1);

	}

	public void setStroke(Stroke arg0) {
		((FlexibleShape) shape).setStroke(arg0);

	}

	public void setWestEnd(Point arg0) {
		((FlexibleShape) shape).setWestEnd(arg0);

	}

	public void setWestEnd(int arg0, int arg1) {
		((FlexibleShape) shape).setWestEnd(arg0, arg1);

	}

	public void setZIndex(int arg0) {
		((FlexibleShape) shape).setZIndex(arg0);

	}
	public void copy(Object aReference) {
		
	}
}
