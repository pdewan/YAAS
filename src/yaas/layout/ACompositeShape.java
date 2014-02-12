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
import java.util.List;

import shapes.BoundedShape;
import shapes.FlexibleShape;


public class ACompositeShape implements CompositeShape {

	private List<FlexibleShape> shapes;
	
	public ACompositeShape(List<FlexibleShape> s) {
		setShapes(s);
	}
	public int getWidth() {
		int width = 0;
		int min_x = this.getX();
		for (int i = 0; i < shapes.size(); i++) {
			int x = shapes.get(i).getEastEnd().x;
			if (x - min_x > width)
				width = x - min_x;
		}
		return width;
	}
	
	private static final int MAX_INT = 200000;
	

	
	
	public ACompositeShape(){
		
	}


	public void addShape(FlexibleShape s) {
		shapes.add(s);
	}

	public List<FlexibleShape> getShapes() {
		return shapes;
	}

	public void setShapes(List<FlexibleShape> _shapes) {
		shapes = _shapes;
	}

	@util.annotations.Visible(false)
	public int getHeight() {
		int height = 0;
		int min_y = this.getY();
		for (int i = 0; i < shapes.size(); i++) {
			int y = shapes.get(i).getSouthEnd().y;
			if (y - min_y > height)
				height = y - min_y;
		}
		return height;
	}
	@util.annotations.Visible(false)
	public int getX() {
		int min_x = MAX_INT;
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).getX() < min_x) {
				min_x = shapes.get(i).getX();
			}
		}
		return min_x;
	}
	@util.annotations.Visible(false)
	public int getY() {
		int min_y = MAX_INT;
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).getY() < min_y) {
				min_y = shapes.get(i).getY();
			}
		}
		return min_y;
	}

	public void setX(int _x) {
		int x_diff = _x - getX();
		for (int i = 0; i < shapes.size(); i++) {
			shapes.get(i).moveX(x_diff);
		}
	}

	public void setY(int _y) {
		int y_diff = _y - getY();
		for (int i = 0; i < shapes.size(); i++) {
			shapes.get(i).moveY(y_diff);
		}
	}

	public void setHeight(int newHeight) {
		int overallY = getY();
		int overallHeight = getHeight();
		for (FlexibleShape s : shapes) {
			int yOffset = s.getY() - overallY;
			double relationalHeight;
			
			//checks for vertical lines
			if (s.getHeight() != 0) {
				relationalHeight =  (double)s.getHeight()/(double)overallHeight;
			} else {
				relationalHeight = 0;
			}
			
			//checks for shapes that have the same X as the overall structure
			double multiplier;
			if (yOffset != 0) {
				multiplier = (double)yOffset / (double)overallHeight;
			} else {
				multiplier = yOffset;
			}

			s.setY((int) (overallY + multiplier * newHeight));
			s.setHeight((int) (newHeight * relationalHeight));

			// prevents divide by zero exceptions when sized to zero then
			// resized
			if (s.getHeight() == 0) {
				s.setHeight(1);
			}
		}
	}

	public void setWidth(int newWidth) {
		int overallX = getX();
		int overallWidth = getWidth();
		for (FlexibleShape s : shapes) {
			int xOffset = s.getX() - overallX;
			double relationalWidth;
			
			//checks for vertical lines
			if (s.getWidth() != 0) {
				relationalWidth =  (double)s.getWidth()/(double)overallWidth;
			} else {
				relationalWidth = 0;
			}
			
			//checks for shapes that have the same X as the overall structure
			double multiplier;
			if (xOffset != 0) {
				multiplier = (double)xOffset / (double)overallWidth;
			} else {
				multiplier = xOffset;
			}

			s.setX((int) (overallX + multiplier * newWidth));
			s.setWidth((int) (newWidth * relationalWidth));

			// prevents divide by zero exceptions when sized to zero then
			// resized
			if (s.getWidth() == 0) {
				s.setWidth(1);
			}
		}
	}

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

	@util.annotations.Visible(false)
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Point getCenter() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Point getEastEnd() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Point getNECorner() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Point getNWCorner() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Point getNorthEnd() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Paint getPaint() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public PathIterator getPathIterator(AffineTransform arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public PathIterator getPathIterator(AffineTransform arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Point getSECorner() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Point getSWCorner() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}
	@util.annotations.Visible(false)
	public Point getSouthEnd() {
		 
		return null;
	}
	@util.annotations.Visible(false)
	public Stroke getStroke() {
		 
		return null;
	}
	@util.annotations.Visible(false)
	public Point getWestEnd() {
		 
		return null;
	}
	@util.annotations.Visible(false)
	public int getZIndex() {
		 
		return 0;
	}

	public boolean intersects(Rectangle2D arg0) {
		 
		return false;
	}

	public boolean intersects(double arg0, double arg1, double arg2, double arg3) {
		 
		return false;
	}
	@util.annotations.Visible(false)
	public boolean is3D() {
		 
		return false;
	}
	@util.annotations.Visible(false)
	public boolean isFilled() {
		 
		return false;
	}
	@util.annotations.Visible(false)
	public boolean isRounded() {
		 
		return false;
	}

	public void moveX(int arg0) {
		 
		
	}

	public void moveY(int arg0) {
		 
		
	}

	public Object remoteClone() {
		 
		return null;
	}

	public void set3D(boolean arg0) {
		 
		
	}

	public void setBounds(Rectangle arg0) {
		 
		
	}

	public void setBounds(Point arg0, Point arg1) {
		 
		
	}

	public void setBounds(int arg0, int arg1, int arg2, int arg3) {
		 
		
	}

	public void setCenter(Point arg0) {
		 
		
	}

	public void setCenter(int arg0, int arg1) {
		 
		
	}

	public void setColor(Color arg0) {
		 for(FlexibleShape s: shapes){
			 s.setColor(arg0);
		 }
		
	}

	public void setDashedStroke() {
		 
		
	}

	public void setDottedStroke() {
		 
		
	}

	public void setEastEnd(Point arg0) {
		 
		
	}

	public void setEastEnd(int arg0, int arg1) {
		 
		
	}

	public void setFilled(boolean arg0) {
		 
		
	}

	public void setFont(Font arg0) {
		 
		
	}

	public void setNECorner(Point arg0) {
		 
		
	}

	public void setNECorner(int arg0, int arg1) {
		 
		
	}

	public void setNWCorner(Point arg0) {
		 
		
	}

	public void setNWCorner(int arg0, int arg1) {
		 
		
	}

	public void setNorthEnd(Point arg0) {
		 
		
	}

	public void setNorthEnd(int arg0, int arg1) {
		 
		
	}

	public void setPaint(Paint arg0) {
		 
		
	}

	public void setPosition(Point arg0) {
		 
		
	}

	public void setRounded(boolean arg0) {
		 
		
	}

	public void setSECorner(Point arg0) {
		 
		
	}

	public void setSECorner(int arg0, int arg1) {
		 
		
	}

	public void setSWCorner(Point arg0) {
		 
		
	}

	public void setSWCorner(int arg0, int arg1) {
		 
		
	}

	public void setSize(Dimension arg0) {
		 
		
	}

	public void setSize(int arg0, int arg1) {
		 
		
	}

	public void setSolidStroke() {
		 
		
	}

	public void setSouthEnd(Point arg0) {
		 
		
	}

	public void setSouthEnd(int arg0, int arg1) {
		 
		
	}

	public void setStroke(Stroke arg0) {
		 
		
	}

	public void setWestEnd(Point arg0) {
		 
		
	}

	public void setWestEnd(int arg0, int arg1) {
		 
		
	}

	public void setZIndex(int arg0) {
		 
		
	}
	public void setFontSize(int newSize) {
		// TODO Auto-generated method stub
		
	}
	public int getFontSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean copy(BoundedShape aReference) {
		return false;
	}
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setAngle(double newAngle) {
		// TODO Auto-generated method stub
		
	}
	public double getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setRadius(double newRadius) {
		// TODO Auto-generated method stub
		
	}
	public double getMagnification() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setMagnification(double newVal) {
		// TODO Auto-generated method stub
		
	}
	public boolean copyable(BoundedShape aReference) {
		// TODO Auto-generated method stub
		return false;
	}
	public void setDisposed(boolean newVal) {
		// TODO Auto-generated method stub
		
	}
	public boolean getDisposed() {
		// TODO Auto-generated method stub
		return false;
	}
	public int getCenterX() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setCenterX(int newVal) {
		// TODO Auto-generated method stub
		
	}
	public int getCenterY() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setCenterY(int newVal) {
		// TODO Auto-generated method stub
		
	}
	public void joinStartToEndOf(BoundedShape shape) {
		// TODO Auto-generated method stub
		
	}
	public void joinStartToStartOf(BoundedShape shape) {
		// TODO Auto-generated method stub
		
	}
	public void joinEndToEndOf(BoundedShape shape) {
		// TODO Auto-generated method stub
		
	}
	public void joinEndToStartOf(BoundedShape shape) {
		// TODO Auto-generated method stub
		
	}
}