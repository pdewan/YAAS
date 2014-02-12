package yaas.layout.nodes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.Observable;

import bus.uigen.shapes.AStringModel;
import shapes.BoundedShape;
import shapes.RectangleDiagonalPoints;
import shapes.ShapesUtility;
import util.annotations.IsCompositeShape;
import util.annotations.Visible;
import yaas.visualizers.collection.flat.FlatCollectionLayoutManager;
@IsCompositeShape(true)
public class AFlatElement extends Observable implements FlatElement {

	
	BoundedShape contentShape;
	BoundedShape labelShape ;;
	boolean disposed;
	FlatCollectionLayoutManager flatCollectionLayoutManager;

	public BoundedShape getContentShape() {
		return contentShape;
	}
	public void setContentShape(BoundedShape contentShape) {
		this.contentShape = contentShape;
	}
	public BoundedShape getLabelShape() {
		return labelShape;
	}
	
	public AFlatElement(BoundedShape aContentShape, BoundedShape aLabelShape, FlatCollectionLayoutManager aFlatCollectionLayoutManager) {
		flatCollectionLayoutManager = aFlatCollectionLayoutManager;
		contentShape = aContentShape;
		if (aLabelShape != null)
		labelShape = aLabelShape;
		recomputeDependents();
	}
	
	
	
	public void setLabelShape(BoundedShape newVal) {
		labelShape =newVal;
		recomputeDependents();
//		notifyObservers();

//		placeLines();
	}
	public int getX() {
		return contentShape.getX();
	}
	public int getY() {
		return contentShape.getY();
	}
	public void setX(int newVal) {
		if (getX() == newVal)
			return;
		contentShape.setX(newVal);
		recomputeDependents();
		setChanged();

		notifyObservers();

//		placeLines();
//		treeNode.setChildrenXY();
//		treeNode.positionHorizontalLine(treeNode);
//		treeNode.positionVerticalLine(treeNode);
	}
	public void setY(int newVal) {
		if (getY() == newVal)
			return;
		contentShape.setY(newVal);
//		placeLines();
//		treeNode.setChildrenXY();
		recomputeDependents();
		setChanged();
		notifyObservers();




	}
	public void setWidth(int width) {
		if (getWidth() == width)
			return;
		contentShape.setWidth(width);
		recomputeDependents();
//		notifyObservers();


//		placeLines();
	}
	public void updateLabelShape(BoundedShape newLabelShape) {
		labelShape.copy(newLabelShape);
		recomputeDependents();
//		setChanged();
//		notifyObservers();
		
	}
	public void setHeight(int height) {
		if (getHeight() == height)
			return;
		contentShape.setHeight(height);
		recomputeDependents();
		notifyObservers();


//		placeLines();
	}
	@Visible(false)
	public int getHeight() {
		return contentShape.getHeight();
	}
	@Visible(false)

	public int getWidth() {
		return contentShape.getWidth();
	}
	@Visible(false)

	public Rectangle getBounds() {
		return contentShape.getBounds();
	}
	@Visible(false)

	public void setBounds(Rectangle newVal) {

		contentShape.setBounds(newVal);
		if (newVal.width == 0 && newVal.height == 0) {
			
			labelShape.setHeight(0);
			labelShape.setWidth(0);
			return;
		}
		recomputeDependents();
		setChanged();

		notifyObservers();

//		placeLines();
	}
	@Visible(false)

	public void updateLabelShape() {
		Point labelShapeLocation = flatCollectionLayoutManager.getLabelShapeLocation(this, labelShape);
		labelShape.setX(labelShapeLocation.x);
		labelShape.setY(labelShapeLocation.y);
	}
	@Visible(false)

	public void recomputeDependents() {
//		Point labelShapeLocation = flatCollectionLayoutManager.getLabelShapeLocation(this);
//		labelShape.setX(labelShapeLocation.x);
//		labelShape.setY(labelShapeLocation.y);
		updateLabelShape();

		setChanged();
		notifyObservers();
//		labelShape.setX(contentShape.getX() + X_OFFSET);
//		labelShape.setY(newVal);
	}
	@Visible(false)

	public RectangleDiagonalPoints getAreaCovered() {
		return ShapesUtility.areaCovering(contentShape, 
				labelShape);
	}
	@Visible(false)

	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	@Visible(false)

	public void setColor(Color newVal) {
		// TODO Auto-generated method stub
		
	}	
	@Visible(false)
	public boolean isFilled() {
		// TODO Auto-generated method stub
		return false;
	}
	@Visible(false)
	public void setFilled(boolean newVal) {
		// TODO Auto-generated method stub
		
	}
	@Visible(false)
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setFont(Font newVal) {
		// TODO Auto-generated method stub
		
	}
	@Visible(false)
	public Stroke getStroke() {
		// TODO Auto-generated method stub
		return null;
	}
	@Visible(false)
	public void setStroke(Stroke newVal) {
		// TODO Auto-generated method stub
		
	}
	@Visible(false)
	
	public boolean copy(BoundedShape aReference) {
		if (!copyable(aReference)) return false;
		try {
			
			FlatElement aReferenceFlastElementShape = (FlatElement) aReference;
		if (contentShape == null) {
			contentShape = aReferenceFlastElementShape.getContentShape().getClass().newInstance();
		}
		
		if (labelShape == null) {
			labelShape = aReferenceFlastElementShape.getLabelShape().getClass().newInstance();
		}
		contentShape.copy(aReferenceFlastElementShape.getContentShape());
		labelShape.copy(aReferenceFlastElementShape.getLabelShape());

//		if (! (mainShape.copy(aReferenceTreeNodeShape.getMainShape()))) return false;
//		if (treeNode == null) {
//			treeNode = aReferenceTreeNodeShape.getTreeNode();
//		}
		
		if (getDisposed()) {
			setDisposed(false);
		}
//		horizontalLine.copy(aReferenceTreeNodeShape.getHorizontalLine());
//		verticalLine.copy(aReferenceTreeNodeShape.getVerticalLine());
		recomputeDependents();
		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
//		treeNode = aReferenceTreeNodeShape.treeNode;
		
	}
	@Visible(false)
	public boolean getDisposed() {
		return disposed;
	}
	@Visible(false)
	public void setDisposed(boolean newVal) {
		disposed = newVal;
		if (disposed) {
//		treeNode = null; // wam to undispose right after disposing, so let us not remove treeNode
		setBounds (new Rectangle(0, 0, 0, 0));
		}
		
	}
	@Visible(false)
	public boolean copyable(BoundedShape aReference) {
		return this.getClass() == aReference.getClass() && (contentShape == null || contentShape.copyable(((FlatElement) aReference).getContentShape()));
	}



}
