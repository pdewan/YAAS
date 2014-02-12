package yaas.layout.nodes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.Observable;
import java.util.Observer;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.BoundedTextShape;
import shapes.FlexibleShape;
import shapes.RectangleDiagonalPoints;
import shapes.ShapesUtility;
import util.annotations.IsCompositeShape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
import util.misc.Common;
import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.visualization.collection.ACollectionVisualization;
import yaas.visualization.collection.CollectionVisualization;
import bus.uigen.ObjectEditor;
import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.AListenableShapeVector;
import bus.uigen.shapes.AStringModel;
import bus.uigen.shapes.ATextModel;
import bus.uigen.shapes.ListenableShapeVector;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@IsCompositeShape(true)
public class ATreeNodeShape extends Observable implements TreeNodeShape {
	public static final int LABEL_VERTICAL_OFFSET = 0;
	BoundedShape contentShape;
	BoundedShape labelShape = new AStringModel("label");

	BoundedShape lineToParent = new ALineModel(0, 0, 0, 0);;
	BoundedShape lineToChildren = new ALineModel(0, 0, 0, 0);;
	TreeNode treeNode;
	boolean disposed;
//	TreeNodeShape parent;
//	ListenableVector<TreeNodeShape> children = new AListenableVector();
	
	void maybeAddObserverOfContentShape() {
		if (contentShape != null && contentShape instanceof Observable) {
			((Observable) contentShape).addObserver(this);
//			((Observable) contentShape).addObserver(this);

			
			
		}
		
	}
	public ATreeNodeShape(BoundedShape aMainShape, TreeNode aTreeNode) {
		this.contentShape = aMainShape;
		maybeAddObserverOfContentShape();
		treeNode = aTreeNode;
		
//		if (treeNode != null && treeNode.getVisualizer() != null)
		labelShape.setHeight(Common.getDefaultFontHeight());
//		else {
//			labelShape.setHeight(0);
//			labelShape.setWidth(0);
//		}

		if ( contentShape != null && contentShape.getWidth() != 0 && contentShape.getHeight() != 0) //unstable invisible node, do not change parent attributes

		recomputeDependents();

	}
//	public ATreeNodeShape(BoundedShape mainShape, BoundedShape horizontalLine,
//			BoundedShape verticalLine, TreeNode aTreeNode) {
//		this.contentShape = mainShape;
////		verticalLine.addObserver(this);
//		this.lineToParent = horizontalLine;
//		this.lineToChildren = verticalLine;	
//		if ( mainShape != null && mainShape.getWidth() != 0 && mainShape.getHeight() != 0) //unstable invisible node, do not change parent attributes
//
//		recomputeDependents();
//		treeNode = aTreeNode;
//	}
	
	boolean showLabelShape() {
		return (treeNode != null && treeNode.getParent() != null);

	}
	@Visible(false)
	public void sythesizeHeightOfLineToChildren() {
		int newVal = treeNode.myVerticalLineSize();
		if (lineToChildren.getHeight() == newVal) return;
		lineToChildren.setHeight(newVal);
		setChanged();
		notifyObservers();
	}
	
	void setLabelShapeWidth () {
		labelShape.setWidth(Math.min(labelShape.getWidth(), lineToParent.getWidth()));
	}
	
	
	
	
	
	
	
//	public ATreeNodeShape(BoundedShape aMainShape, TreeNodeShape aParent) {
//		this.mainShape = aMainShape;
//		parent = aParent;
//		recomputeDependents();
//
//	}
	@Visible(false)
	public TreeNode getTreeNode() {
		return treeNode;
	}
	@Visible(false)
	public void setTreeNode (TreeNode aTreeNode) {
		treeNode = aTreeNode;
	}
	@Visible(false)
	public void updateLabelShape() {
		if (showLabelShape()) {
			BoundedShape newLabelShape = treeNode.getVisualizer().getCollectionLabelMapper().getLabelShape(treeNode.getParent().getObject(), treeNode.getObject());
			if (labelShape == null) {
				labelShape = newLabelShape;
			} else {
				labelShape.copy(newLabelShape);
			}
		} else {
			labelShape.setHeight(0);
			labelShape.setWidth(0);
		}
		
	}
	@Visible(false)
	public void recomputeDependents() {
//		if ( mainShape != null && mainShape.getWidth() != 0 && mainShape.getHeight() != 0) //unstable invisible node, do not change parent attributes

		if (lineToParent != null && lineToChildren != null && contentShape != null) {
			updateLabelShape();
			
		placeDependents();
		}
		if (treeNode != null)
		treeNode.setChildrenXY();
		
		setChanged();
		notifyObservers();
	}
	@Visible(false)
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}
	
	void placeDependents() {
//		verticalLine.setHeight(treeNode.myVerticalLineSize());
//		horizontalLine.setY(treeNode.getHorizontalLineYOffset() + mainShape.getY());
//		updateLabelShape();
		lineToParent.setY(contentShape.getHeight()/2+ contentShape.getY());
		labelShape.setY(lineToParent.getY() + LABEL_VERTICAL_OFFSET);

//		horizontalLine.setX( mainShape.getX() - horizontalLine.getWidth() );
		lineToChildren.setX(contentShape.getWidth()/2+ contentShape.getX());

//		verticalLine.setX(treeNode.getVerticalLineXOffset() + mainShape.getX());
		lineToChildren.setY(contentShape.getHeight() + contentShape.getY() );
		if (treeNode != null && 
				treeNode.getParent() != null && 
				!treeNode.isVisuallyUnparented()) {
		lineToParent.setWidth(contentShape.getX() - treeNode.getParent().getTreeNodeShape().getLineToChildren().getX());
//		if(treeNode.getVisualizer() != null)
//		labelShape.setWidth(lineToParent.getWidth());
		setLabelShapeWidth();

		
//		TreeNodeShape parentTreeNodeShape = treeNode.getParent().getTreeNodeShape();
//		parentTreeNodeShape.set
		treeNode.getParent().getTreeNodeShape().sythesizeHeightOfLineToChildren();

//		treeNode.getParent().getTreeNodeShape().getVerticalLine().setHeight(treeNode.getParent().myVerticalLineSize());
		} else {
			lineToParent.setWidth(0);
			labelShape.setWidth(0);

		}

		lineToParent.setX( contentShape.getX() - lineToParent.getWidth() );
		if (showLabelShape() && labelShape instanceof FlexibleShape && lineToParent instanceof FlexibleShape) {
			((FlexibleShape) labelShape).setCenterX(((FlexibleShape) lineToParent).getCenterX());
		}


		
	}
	public BoundedShape getContentShape() {
		return contentShape;
	}
	public BoundedShape getLabelShape() {
		return labelShape;
	}
	public BoundedShape getLineToParent() {
		return lineToParent;
	}
	public BoundedShape getLineToChildren() {
		return lineToChildren;
	}
	public void setContentShape(BoundedShape mainShape) {
		this.contentShape = mainShape;
		maybeAddObserverOfContentShape();
		recomputeDependents();
		setChanged();

		notifyObservers();


	}
	@Visible(false)
	public void setLineToParent(BoundedShape horizontalLine) {
		this.lineToParent = horizontalLine;
		notifyObservers();

//		placeLines();
	}
	@Visible(false)
	public void setLineToChildren(BoundedShape verticalLine) {
		this.lineToChildren = verticalLine;
		setChanged();

		notifyObservers();

//		placeLines();

	}
	@Visible(false)
	public void setLabelShape(BoundedShape newVal) {
		labelShape =newVal;
		notifyObservers();

//		placeLines();
	}
	@Visible(false)
	public int getX() {
		return contentShape.getX();
	}
	@Visible(false)
	public int getY() {
		return contentShape.getY();
	}
	@Visible(false)
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
	@Visible(false)
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
	@Visible(false)
	public void setWidth(int width) {
		if (getWidth() == width)
			return;
		contentShape.setWidth(width);
		recomputeDependents();
		notifyObservers();


//		placeLines();
	}
	@Visible(false)
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
			lineToParent.setWidth(0);
			lineToChildren.setHeight(0);
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
	public RectangleDiagonalPoints getAreaCovered() {
		return ShapesUtility.areaCovering(contentShape, 
				ShapesUtility.areaCovering (lineToParent, lineToChildren));
	}
	@Visible(false)
	public Color getColor() {
		// TODO Auto-generated method stub
		return 		((AttributedShape) contentShape).getColor();

	}
	@Visible(false)
	public void setColor(Color newVal) {
		((AttributedShape) contentShape).setColor(newVal);
	}
	@Visible(false)
	public boolean isFilled() {
		// TODO Auto-generated method stub
		return 		 		((AttributedShape) contentShape).isFilled();

	}
	@Visible(false)
	public void setFilled(boolean newVal) {
		((AttributedShape) contentShape).setFilled(newVal);
		
	}
	@Visible(false)
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}
	@Visible(false)
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
	public Paint getPaint() {
		// TODO Auto-generated method stub
		return null;
	}
	@Visible(false)
	public String toString() {
		return contentShape.toString();
	}
	
//	public ListenableVector<TreeNodeShape> getChildren() {
//		return children;
//	}
//	
//	public void  setChildren(ListenableVector<TreeNodeShape> newVal) {
//		 children = newVal;
//	}


	@Visible(false)
	public boolean copy(BoundedShape aReference) {
		if (!copyable(aReference)) return false;
		try {
			
		TreeNodeShape aReferenceTreeNodeShape = (TreeNodeShape) aReference;
		if (contentShape == null) {
			contentShape = aReferenceTreeNodeShape.getContentShape().getClass().newInstance();
		}
		if (lineToParent == null) {
			lineToParent = aReferenceTreeNodeShape.getLineToParent().getClass().newInstance();
		}
		if (lineToChildren == null) {
			lineToChildren = aReferenceTreeNodeShape.getLineToChildren().getClass().newInstance();
		}
		if (labelShape == null) {
			labelShape = aReferenceTreeNodeShape.getLabelShape().getClass().newInstance();
		}
		lineToChildren.copy(aReferenceTreeNodeShape.getLineToChildren()); // this does not depend on main shape
		contentShape.copy(aReferenceTreeNodeShape.getContentShape());
		labelShape.copy(aReferenceTreeNodeShape.getLabelShape());

//		if (! (mainShape.copy(aReferenceTreeNodeShape.getMainShape()))) return false;
//		if (treeNode == null) {
//			treeNode = aReferenceTreeNodeShape.getTreeNode();
//		}
		
		if (getDisposed()) {
			treeNode = aReferenceTreeNodeShape.getTreeNode();
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
//	public int myX() {
//		return parent.getX() + parent.getWidth();
//		
//	}
//	
//	public static int yOffsetOfSlotBelow (TreeNode aTreeNode) {
//		return aTreeNode.getTreeNodeShape().getHeight() + aTreeNode.getElementShapeYOffset();
//	}
//	public int myIndexInParent() {
//		return parent.getChildren().indexOf(this);
//	}
//	
//	public int myY() {
//		int myIndex = myIndexInParent();
//		return childY(parent, myIndex);
////		int retVal = parent.getY() + yOffsetOfSlotBelow(parent);
////
////		for (int i = 0; i < myIndex; i++) {
////			TreeNode prevChild = parent.getVector().get(i);
////			retVal += yOffsetOfSlotBelow(prevChild);
////		}
////		return retVal;
//	}
//	
//	public static int childY (TreeNodeShape aParent, int aChildPos) {
//		int retVal = aParent.getY() + aParent.getElementShapeYOffset()*(1 + aParent.getNumDescendentsAbove(aChildPos));
////		for (int i = 0; i < aChildPos; i++) {
////			TreeNode prevChild = aParent.getVector().get(i);
////			retVal += yOffsetOfSlotBelow(prevChild);
////		}
//		return retVal;
//	}
//	
//	public int getNumDescendents() {
//		return getNumDescendentsAbove(children.size());
//	}
//	
//	public int getNumDescendentsAbove(int childPos) {
//	
//		int retVal = childPos;
//		for (int i = 0; i < childPos; i++) {
//			TreeNodeShape childNode = children.get(i);
//			retVal += childNode.getNumDescendents();
//		}
//		return retVal;
//	}
//	
//	public int myVerticalLineSize() {
//		if (children.size() == 0) return 0;
//		// find the last child whose sizeis not zero;
//		TreeNodeShape lastChild = children.get(
//				children.size() - 1);
//		TreeNodeShape childTreeNodeShape = lastChild;
//		return 
//				childTreeNodeShape.getHorizontalLine().getY() - getVerticalLine().getY();
////				- childTreeNodeShape.getHeight() / 2;
////		return computeVerticalLineSize().height;
////		int numElements = vector.size();
////		if (numElements == 0) return 0;
////		int lastChildPos = childY(this, numElements -1);
////		TreeNodeShape lastChildTreeNodeShape = vector.get(numElements -1).getTreeNodeShape();
////		return lastChildPos - getTreeNodeShape().getY() - getTreeNodeShape().getHeight() + lastChildTreeNodeShape.getHeight()/2 ;
////		int retVal = 0;
////		for (int i = 0; i < numElements - 2; i++) {
////			TreeNode aChild = getVector().get(i);
////			retVal += yOffsetOfSlotBelow(aChild);
////		}
////		return childY(this, numElements-1) - this.getY() - this.getHeight();
//	}
//	
//	public void makeDisplayConsistent() {
//		setXY();
//		setChildrenXY();		
//	}
//	
//	public int getElementShapeYOffset() {
//		return 30;
//	}
//	
//	public void setVertilcalLineSize() {
//		getVerticalLine().setHeight(myVerticalLineSize());
//	}
//	
//	public void setXY() {
//		setX(myX());
//		setY(myY());
//	}
//	
//	public void setChildrenXY() {
//		for (TreeNodeShape aChild:children) {
//			aChild.setXY();
//		}
//	}
//	
//	
//	
//	
//
////	public void positionVerticalLine(TreeNode node) {
////
////		// mask instance variable 'verticalLine'
////		ALineModel verticalLine = node.getVerticalLine();
////
////		AnimationUtil.move(verticalLine, node.getX() + verticalLineXOffset,
////				node.getY() + node.getHeight(), animate, super
////						.getHighlighting(), super.getColor());
////
////		verticalLine.setWidth(0);
////
////		TreeNode lastChild = node.getVector().get(
////				node.getVector().size() - 1);
////		verticalLine.setHeight(lastChild.getY() - node.getY()
////				- lastChild.getHeight() / 2);
////	}
//	public Point computeVerticalLinePosition() {
//		return new Point (getX() + getVerticalLineXOffset(), getY() + getHeight());
//	}
//	public Dimension computeVerticalLineSize() {
//		TreeNodeShape lastChild = getChildren().get(
//				getChildren().size() - 1);
//		return new Dimension (
//				0,
//				lastChild.getY() - getY()
//				- lastChild.getHeight() / 2);
//				
//		
//	}
//	public int getVerticalLineXOffset() {
//		return 10;
//	}





	@Visible(false)
	public boolean copyable(BoundedShape aReference) {
		return this.getClass() == aReference.getClass() && (contentShape == null || contentShape.copyable(((TreeNodeShape) aReference).getContentShape()));
	}





	@Visible(false)
	public void setDisposed(boolean newVal) {
		disposed = newVal;
		if (disposed) {
//		treeNode = null; // wam to undispose right after disposing, so let us not remove treeNode
		setBounds (new Rectangle(0, 0, 0, 0));
		}
		
	}

//	 public synchronized void addObserver(Observer o) {
//	      super.addObserver(o);
//	      if (contentShape != null && contentShape instanceof Observable) {
//	    	 ( (Observable) contentShape).addObserver(o);
//	    	  
//	      }
//	    }



	@Visible(false)
	public boolean getDisposed() {
		return disposed;
	}
	
	public static void main (String[] args) {
		BoundedTextShape stringModel = new  AStringModel("hello model");
		stringModel.setX( 50);
		stringModel.setY(70);
		TreeNodeShape treeNodeShape = new ATreeNodeShape(stringModel, null);
		CollectionVisualization visualization = new ACollectionVisualization();
		ListenableVector listenableVector = new AListenableVector();
		ListenableShapeVector listenableShapeVector = new AListenableShapeVector();
//		BoundedTextShape stringModel = new AStringModel ("Hello world");
		
		listenableVector.add(listenableShapeVector);
//		ObjectEditor.graphicsOnlyEdit(listenableVector);
		visualization.getStatusShape().setText("hello world");
		visualization.getShapes().get(0).add(treeNodeShape);

		ObjectEditor.graphicsOnlyEdit(visualization);


	}
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}
	public void setFontSize(int newSize) {
		// TODO Auto-generated method stub
		
	}
	public int getFontSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getZIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setZIndex(int newVal) {
		// TODO Auto-generated method stub
		
	}
	public void setPaint(Paint newVal) {
		// TODO Auto-generated method stub
		
	}
	public boolean isRounded() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setRounded(boolean newVal) {
		// TODO Auto-generated method stub
		
	}
	public boolean is3D() {
		// TODO Auto-generated method stub
		return false;
	}
	public void set3D(boolean newVal) {
		// TODO Auto-generated method stub
		
	}
		
	

}
