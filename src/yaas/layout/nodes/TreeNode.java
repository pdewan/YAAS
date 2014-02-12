package yaas.layout.nodes;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import shapes.RectangleDiagonalPoints;
import yaas.visualizers.collection.CollectionVisualizer;
import yaas.visualizers.collection.tree.TreeVisualizer;
import bus.uigen.shapes.ALineModel;

public interface TreeNode extends VisualizerNode<TreeNode>{
	
	public int getHorizontalLineYOffset();
	public int getVerticalLineXOffset();
	public int getElementShapeYOffset();
	public int getElementShapeXOffset();
	public void setHorizontalLineYOffset(int newY);
	public void setVerticalLineXOffset(int newX);
	public void setElementShapeYOffset(int newY);
	public void setElementShapeXOffset(int newX);
	
	public ALineModel getHorizontalLine();
	public ALineModel getVerticalLine();
//	public void positionVerticalLine(TreeNode node);
//	public void positionHorizontalLine(TreeNode node);
	public Color getHighlighting() ;

	public void setColor(Color theColor) ;
	@util.annotations.Visible(false)
	public Color getColor() ;
	public boolean isAnimate();


	public TreeNodeShape getTreeNodeShape();
	public void setTreeNodeShape(TreeNodeShape newVal);
	public int myIndexInParent();
	public int myX();
	public int myY();
	public void setXY();
	void setChildrenXY();
	public int myVerticalLineSize();
	public int getNumDescendents() ;
	public void setVertilcalLineSize() ;
	
	public int getNumDescendentsAbove(int childPos); 
	public boolean isVisuallyUnparented();
	public void setVisuallyUnparented(boolean visuallyUnparented) ;
//	public void copy (TreeNode aTreeNode);
	
	public List<TreeNodeShape>  getTreeNodeShapesInSubTree() ;
	
	public List<Rectangle>  getTreeNodeShapeBoundsInSubTree() ;
	
	public void  setZeroBoundsInSubTree() ;
	void recomputeDependentsInSubTree();
	public RectangleDiagonalPoints getSubTreeDiagonalPoints();
	public Rectangle getSubTreeAreaBounds();
	public TreeNode getRoot();
	public CollectionVisualizer getVisualizer();
	public void setTreeVisualizer(CollectionVisualizer newVal);
	

}
