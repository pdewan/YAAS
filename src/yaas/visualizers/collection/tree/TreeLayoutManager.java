package yaas.visualizers.collection.tree;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import bus.uigen.shapes.ListenableShapeVector;
import shapes.AttributedTextShape;
import shapes.BoundedShape;
//import shapes.BoundedTextShape;
import util.models.ListenableVector;
import yaas.collection.CollectionLayoutManager;
import yaas.layout.LayoutManager;
import yaas.layout.RowColumnLayoutManager;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.layout.nodes.TreeNode;
import yaas.layout.nodes.TreeNodeShape;
import yaas.shapemappers.ObjectToShapeTranslator;

public interface TreeLayoutManager<ElementType> 
		extends CollectionLayoutManager<ElementType> {
//		RowColumnLayoutManager<ListenableVector<ElementType>> {

	List<TreeNodeShape> getTreeNodeShapes(TreeNode toTreeNode);

	TreeNode getTreeNode(Object source);

	void addTreeNodeShapes(ListenableShapeVector aContainingList, List<TreeNodeShape> aShapeList);
	TreeNode createTreeAndShapes(Object element);
	public  Point normalizedToRealLocation (ListenableShapeVector aContainingShapes, BoundedShape aShape, Point aNormalizedLocation) ;

	// this one created just the atomic shape, not the tree node shape that encompasses it
	public BoundedShape createShape(ListenableVector aParent, ElementType anElement, Integer anIndex, Rectangle initBounds, ListenableShapeVector aConainingShapes);

	void removeTreeNodeShapes(ListenableShapeVector aContainingList,TreeNode toBeRemoved);
	public ObjectToShapeTranslator<ListenableVector> getRootNodeToContentShapeTranslator() ;

	public void setRootNodeToContentShapeTranslator(
			ObjectToShapeTranslator<ListenableVector> rootNodeToShapeTranslator) ;
	public ObjectToShapeTranslator<ListenableVector> getInternalNodeToContentShapeTranslator() ;

	public void setInternalNodeToContentShapeTranslator(
			ObjectToShapeTranslator<ListenableVector> internalNodeToShapeTranslator) ;

	public ObjectToShapeTranslator<ElementType> getLeafNodeToContentShapeTranslator() ;

	public void setLeafNodeToContentShapeTranslator(
			ObjectToShapeTranslator<ElementType> leafNodeToShapeTranslator) ;
	public void copy(TreeNode toTreeNode, TreeNode aTreeNode);
	public void retarget (TreeNode toTreeNode, TreeNode aFromTreeNode);
	public  TreeNode removeTreeNode(Object aValue);
	ListenableShapeVector getContainingShapes (TreeNode aTreeNode);



//	void setRootToShapeTranslator(ObjectToShapeTranslator<ElementType> newVal);
//	ObjectToShapeTranslator<ElementType> getInternalNodeToShapeTranslator();
//	void setInternalNodeToShapeTranslator(ObjectToShapeTranslator<ElementType> newVal);
//	ObjectToShapeTranslator<ElementType> getLeafNodeToShapeTranslator();
//	void setLeafNodeToShapeTranslator(ObjectToShapeTranslator<ElementType> newVal);


}
