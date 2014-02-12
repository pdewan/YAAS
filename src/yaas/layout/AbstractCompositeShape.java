package yaas.layout;

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

import shapes.FlexibleShape;


public abstract class AbstractCompositeShape implements FlexibleShape{

	public boolean contains(Point2D arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contains(Rectangle2D arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contains(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contains(double arg0, double arg1, double arg2, double arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Point getCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Point getEastEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Point getNECorner() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Point getNWCorner() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Point getNorthEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Paint getPaint() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public PathIterator getPathIterator(AffineTransform arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public PathIterator getPathIterator(AffineTransform arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Point getSECorner() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Point getSWCorner() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Point getSouthEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Stroke getStroke() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Point getWestEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getZIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean intersects(Rectangle2D arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean intersects(double arg0, double arg1, double arg2, double arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean is3D() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isFilled() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isRounded() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void moveX(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void moveY(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public Object remoteClone() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void set3D(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setBounds(Rectangle arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setBounds(Point arg0, Point arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setBounds(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	
	public void setCenter(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setCenter(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setColor(Color arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setDashedStroke() {
		// TODO Auto-generated method stub
		
	}

	
	public void setDottedStroke() {
		// TODO Auto-generated method stub
		
	}

	
	public void setEastEnd(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setEastEnd(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setFilled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setFont(Font arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setHeight(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setNECorner(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setNECorner(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setNWCorner(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setNWCorner(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setNorthEnd(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setNorthEnd(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setPaint(Paint arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setPosition(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setRounded(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSECorner(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSECorner(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSWCorner(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSWCorner(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSize(Dimension arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSolidStroke() {
		// TODO Auto-generated method stub
		
	}

	
	public void setSouthEnd(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSouthEnd(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setStroke(Stroke arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setWestEnd(Point arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setWestEnd(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void setWidth(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setZIndex(int arg0) {
		// TODO Auto-generated method stub
		
	}

}
