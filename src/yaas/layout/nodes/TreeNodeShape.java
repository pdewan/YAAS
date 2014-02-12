package yaas.layout.nodes;

import java.util.Observer;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.Disposable;
import shapes.FlexibleShape;
import shapes.RectangleDiagonalPoints;
import util.annotations.IsCompositeShape;
import util.models.ListenableVector;
public interface TreeNodeShape extends AttributedShape, Disposable, Observer{
	BoundedShape getLabelShape();
	BoundedShape getContentShape();
	BoundedShape getLineToParent();
	BoundedShape getLineToChildren();
	public void setContentShape(BoundedShape mainShape) ;
	public void setLabelShape(BoundedShape newVal) ;

	public void setLineToParent(BoundedShape horizontalLine) ;
	public void setLineToChildren(BoundedShape verticalLine);
	void recomputeDependents();
	 RectangleDiagonalPoints getAreaCovered();
	 public TreeNode getTreeNode() ;
		public void setTreeNode (TreeNode aTreeNode) ;
	void	 sythesizeHeightOfLineToChildren();
	public void notifyObservers();


//	public ListenableVector<TreeNodeShape> getChildren() ;
//	
//	public void  setChildren(ListenableVector<TreeNodeShape> newVal) ;
//	int getNumDescendents();
//	public int getNumDescendentsAbove(int childPos); 
//	int getElementShapeYOffset();
//	public void setXY() ;
//	public int getVerticalLineXOffset() ;



}
