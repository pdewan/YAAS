package yaas.visualizers.collection.tree;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bus.uigen.shapes.ListenableShapeVector;
import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import shapes.LabelShape;
import shapes.TextShape;
import util.misc.Common;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import util.trace.Tracer;
import yaas.animators.AnimationUtil;
import yaas.collection.AnAbstractGraphicsCollectionMethodAnimator;
import yaas.collection.CollectionLayoutManager;
import yaas.layout.nodes.TreeNode;
import yaas.layout.nodes.TreeNodeShape;
import yaas.trappers.AnAbstractLinearEventTrapper;
import yaas.trappers.EventTrapper;
import yaas.visualizers.collection.CollectionVisualizer;



public class ATreeVectorMethodsAnimator<ElementType> extends AnAbstractGraphicsCollectionMethodAnimator<ElementType>
//		extends
//		AnAbstractLinearEventTrapper<ElementType>
		implements
		VectorMethodsListener<ElementType> {

	private static final long serialVersionUID = 8683688773211768379L;

//	protected TreeNode root;
	protected int boxWidth;
	protected int boxHeight;
	protected boolean dynamicWidth;
	protected boolean dynamicHeight;
//	protected yaas.visualizers.collection.tree.TreeVisualizer<ElementType > visualizer;
//	protected ListenableVector<BoundedShape> shapes;
	
	public ATreeLayoutManager<ElementType> getLayoutManager(ListenableVector<ElementType> aBuffer) {
		return (ATreeLayoutManager) super.getLayoutManager(aBuffer);
	}

	public ATreeVectorMethodsAnimator(
			CollectionVisualizer<ElementType> visualizer) {
		super(visualizer);
//		this.visualizer = visualizer;
//		root = visualizer.getRootTreeNode();

//		boxWidth = ((ATreeLayoutManager<ElementType>) visualizer
//				.getLayoutManager(source)).getBoxWidth();
//		boxHeight = ((ATreeLayoutManager<ElementType>) visualizer
//				.getLayoutManager()).getBoxHeight();
//		dynamicWidth = ((ATreeLayoutManager<ElementType>) visualizer
//				.getLayoutManager()).getDynamicWidth();
//		dynamicHeight = ((ATreeLayoutManager<ElementType>) visualizer
//				.getLayoutManager()).getDynamicHeight();
//		shapes = visualizer.getShapes();

	}

	public synchronized void elementAdded(Object source, ElementType element,
			int newSize) {

		elementInserted(source, element, newSize - 1, newSize);
	}

//	public synchronized void elementChanged(Object source, ElementType element,
//			int pos) {
//
//		TreeNode node = root.getVector().get(pos);
//		AnimationUtil.move(node, node.getX() + boxWidth / 3, node.getY(), true,
//				((ATreeLayoutManager<ElementType>) visualizer
//						.getLayoutManager()).getHighlighting(),
////				((OEShape) visualizer.getLayoutManager()).getColor());
//		null);
//		node.setObject(element);
//
//		TreeNodeShape shape = node.getTreeNodeShape();
//
//		try {
//			double shapeStretchFactor = Double.parseDouble(element.toString());
//			shape.setWidth((int) (boxWidth * (dynamicWidth ? shapeStretchFactor
//					: 1)));
//			shape.setHeight((int) (boxHeight * (dynamicHeight ? shapeStretchFactor
//					: 1)));
//		} catch (Exception e) {
//			shape.setWidth((int) (boxWidth));
//			shape.setHeight((int) (boxHeight));
//
//		}
//
//		if (shape.getMainShape() instanceof TextShape)
//			((TextShape) shape.getMainShape()).setText(element.toString());
//		if (shape.getMainShape() instanceof LabelShape)
//			((LabelShape) shape.getMainShape()).setText(element.toString());
//
//		AnimationUtil.move(node, node.getX() - boxWidth / 3, node.getY(), true,
//				((ATreeLayoutManager<ElementType>) visualizer
//						.getLayoutManager()).getHighlighting(),
////				((OEShape) visualizer.getLayoutManager()).getColor());
//		null);
//	}
	public void userObjectCopiedToTemp(Object source, Object copiedValue) {
		// TODO Auto-generated method stub
		
	}

	public void tempCopiedToUserObject(Object aUserObjectParent, Object aTempParent) {
		TreeNode aUserObjectParentTreeNode = ((ATreeLayoutManager<ElementType>) getVisualizer().getLayoutManagerOfBuffer((ListenableVector<ElementType>) aTempParent)).getTreeNode(aUserObjectParent);
		TreeNode aTempParentTreeNode = getLayoutManager((ListenableVector<ElementType>) aTempParent).
				getTempTreeNode(aTempParent);
		
		BoundedShape aTempContentShape = aTempParentTreeNode.getTreeNodeShape().getContentShape();
		BoundedShape aUserObjectContentShape = aUserObjectParentTreeNode.getTreeNodeShape().getContentShape();
		contentCopied (aTempContentShape, aUserObjectContentShape, aTempParentTreeNode.getObject(), aTempParent, aTempParent);

		
	}
	public  void contentCopied(BoundedShape fromShape, BoundedShape toShape, Object templateObject, Object source, Object to) {
		   
		   TreeNode copiedTreeNode = getLayoutManager((ListenableVector<ElementType>) source).getCopiedObjectTreeNode();
			
		   // duplicate shapes in from tree node which will end up becoming to tree node shapes
		   // we will set disposed to true, maybe not a good idea, and should put an extra parameter
		   // to copy
			BoundedShape copiedContentShape = copiedTreeNode.getTreeNodeShape().getContentShape();
			if (copiedContentShape == null) {
//				copiedContentShape = getLayoutManager((ListenableVector<ElementType>) source).createShape(0, 0, -1, -1, templateObject);
				copiedContentShape = getLayoutManager((ListenableVector<ElementType>) source).createShape(null, (ElementType) templateObject, null, new Rectangle(0, 0, -1, -1), null);
			}
			copiedTreeNode.getTreeNodeShape().setContentShape(copiedContentShape);

			
			
			// chnage bounds to new size
//			Rectangle newBounds = new Rectangle(copiedTreeNodeShape.getBounds());
//			newBounds.x = toTreeNode.getTreeNodeShape().getX();
//			newBounds.y = toTreeNode.getTreeNodeShape().getY();
			Point newLocation = new Point (toShape.getX(), toShape.getY());
			ListenableVector<BoundedShape> copiedTreeNodeShapesVector = (ListenableVector) getVisualizer().getVisualization().getCopiedObjectShape();
		
			copiedContentShape.copy(fromShape);
			copiedTreeNodeShapesVector.add(copiedContentShape);

			// this should really be new Location
//			AnimationUtil.newBoundsModular(copiedTreeNodeShape, newBounds);
			AnimationUtil.moveLocation(copiedContentShape, newLocation);
			toShape.copy(copiedContentShape);
//
//
//			if (toTreeNode.getParent() != null) {
//				toTreeNode.getRoot().setChildrenXY();
//				toTreeNode.getRoot().recomputeDependentsInSubTree();
//			} 
//			// do we need this?, yes we do, as the shapes have not been added, just assigned to tree nodes
//			List<TreeNodeShape>  toNewShapes = visualizer.getLayoutManager().getTreeNodeShapes(toTreeNode);
//			// this will add duplicate nodes
//			listContainingToNodeShapes.addAll(toNewShapes);
			// duplicate ones are now cleared 
			copiedTreeNodeShapesVector.clear();


		}
public  void elementCopied(TreeNode fromTreeNode, TreeNode toTreeNode, 
		ListenableVector<BoundedShape> listContainingToNodeShapes, Object from, Object to ) {
	   TreeNode copiedTreeNode = getLayoutManager((ListenableVector<ElementType>) from).getCopiedObjectTreeNode();
		
	   // duplicate shapes in from tree node which will end up becoming to tree node shapes
	   // we will set disposed to true, maybe not a good idea, and should put an extra parameter
	   // to copy
		getLayoutManager((ListenableVector<ElementType>) from).copy(copiedTreeNode, fromTreeNode);

		
		// need to worry about node kind having changed from leaf to Internal or vice versa, or maybe even root
		TreeNodeShape copiedTreeNodeShape = copiedTreeNode.getTreeNodeShape();
		// chnage bounds to new size
//		Rectangle newBounds = new Rectangle(copiedTreeNodeShape.getBounds());
//		newBounds.x = toTreeNode.getTreeNodeShape().getX();
//		newBounds.y = toTreeNode.getTreeNodeShape().getY();
		Point newLocation = new Point (toTreeNode.getTreeNodeShape().getX(), toTreeNode.getTreeNodeShape().getY());
		List<TreeNodeShape> newShapes = copiedTreeNode.getTreeNodeShapesInSubTree();
		ListenableVector<BoundedShape> copiedTreeNodeShapesVector = (ListenableVector) getVisualizer().getVisualization().getCopiedObjectShape();
	
		copiedTreeNodeShapesVector.addAll(newShapes);

		// this should really be new Location
//		AnimationUtil.newBoundsModular(copiedTreeNodeShape, newBounds);
		AnimationUtil.moveLocation(copiedTreeNodeShape, newLocation);


		List<TreeNodeShape> toShapes = ((ATreeLayoutManager<ElementType>) getVisualizer().getLayoutManagerOfBuffer((ListenableVector<ElementType>) from)).getTreeNodeShapes(toTreeNode);
//		listContainingToNodeShapes.removeAll(toShapes);
		removeAllTreeNodeShapes (listContainingToNodeShapes, toShapes);
		// we will copy on to the parent shape parser
//		for (TreeNodeShape treeNodeShape: toShapes) {
//			treeNodeShape.setDisposed(false);
//		}
//		toTreeNode.getTreeNodeShape().setDisposed(false);
		// now we copy, but only the top shape
		((ATreeLayoutManager<ElementType>) getVisualizer().getLayoutManagerOfBuffer((ListenableVector<ElementType>) from)).copy(toTreeNode, copiedTreeNode);

		if (toTreeNode.getParent() != null) {
			toTreeNode.getRoot().setChildrenXY();
			toTreeNode.getRoot().recomputeDependentsInSubTree();
		} 
		// do we need this?, yes we do, as the shapes have not been added, just assigned to tree nodes
		List<TreeNodeShape>  toNewShapes = ((ATreeLayoutManager<ElementType>) getVisualizer().getLayoutManagerOfBuffer((ListenableVector<ElementType>) from)).getTreeNodeShapes(toTreeNode);
		// this will add duplicate nodes
		listContainingToNodeShapes.addAll(toNewShapes);
		// duplicate ones are now cleared 
		copiedTreeNodeShapesVector.clear();


	}
	public CollectionVisualizer getVisualizer() {
		return (CollectionVisualizer) visualizer;
	}
	public synchronized void elementCopied(Object source, int fromIndex,
			int toIndex, int newSize) {
		super.elementCopied(source, fromIndex, toIndex, newSize);
		TreeNode parent = ((ATreeLayoutManager<ElementType>) getVisualizer().getLayoutManagerOfBuffer((ListenableVector<ElementType>) source)).getTreeNode(source);


		TreeNode fromNode = parent.getVector().get(fromIndex);
		TreeNode toNode = parent.getVector().get(toIndex);
		if (fromNode.getObject() instanceof ListenableVector) {
			Tracer.error("Copying composite element:" + fromNode.getObject() + ". Will create problems in undoo"  );
		}
		Object oldObject = toNode.getObject();
		((ATreeLayoutManager<ElementType>) getVisualizer().getLayoutManagerOfBuffer((ListenableVector<ElementType>) source)).removeTreeNode(oldObject);
//		AGraphicIntegerCollectionVectorMethodsAnimator.elementCopied(
//				fromNode.getTreeNodeShape().getMainShape(), 
//				toNode.getTreeNodeShape().getMainShape(),
//				visualizer.getShapes().getCopiedObjectShape(), true);
//		
		elementCopied(
				fromNode, 
//				toNode, visualizer.getVisualization().getShapes().get(0));
		toNode, getContainingShapes(source), source, source);

		
//		FancyShape toShape =  (FancyShape) visualizer.getShapes().get(toIndex);
//		
//		FancyShape fromShape = (FancyShape) visualizer.getShapes().get(fromIndex);
//		A
		
		// TODO Auto-generated method stub

	}
	public void elementCopiedToUserObject(Object source, int fromIndex) {
		super.elementCopiedToUserObject(source, fromIndex);
//		ListenableVector<BoundedShape>  userObjectList = (ListenableVector) visualizer.getVisualization().getUserObjectShape();
		ListenableVector<BoundedShape>  userObjectList = (ListenableVector)getTempShapes(source);

		TreeNode parent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);


		TreeNode fromNode = parent.getVector().get(fromIndex);
		elementCopied(fromNode, getLayoutManager((ListenableVector<ElementType>) source).getTempTreeNode(source),
//				((ListenableVector) visualizer.getVisualization().getUserObjectShape()));
		((ListenableVector) getTempShapes(source)), source, source);
		
	}
	
	public void elementCopiedToTemp(Object source, int fromIndex) {
		super.elementCopiedToTemp(source, fromIndex);
//		ListenableVector<BoundedShape>  userObjectList = (ListenableVector) visualizer.getVisualization().getUserObjectShape();
		ListenableVector<BoundedShape>  userObjectList = (ListenableVector)getTempShapes(source);

		TreeNode parent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);


		TreeNode fromNode = parent.getVector().get(fromIndex);
		elementCopied(fromNode, getLayoutManager((ListenableVector<ElementType>) source).getTempTreeNode(source),
//				((ListenableVector) visualizer.getVisualization().getUserObjectShape()));
		((ListenableVector) getTempShapes(source)), source, source);
		
	}

	public void elementCopiedFromUserObject(Object source, int toIndex) {
		super.elementCopiedFromUserObject(source, toIndex);
//		ListenableVector<BoundedShape>  userObjectList = (ListenableVector) visualizer.getVisualization().getUserObjectShape();
		ListenableVector<BoundedShape>  userObjectList = (ListenableVector) getTempShapes(source);

		TreeNode parent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);
		TreeNode toNode = parent.getVector().get(toIndex);
		getLayoutManager((ListenableVector<ElementType>) source).removeTreeNode(toNode.getObject());

//		elementCopied(((ATreeLayoutManager )visualizer.getLayoutManager()).getUserObjectTreeNode(), toNode, visualizer.getVisualization().getShapes().get(0));
		elementCopied(getLayoutManager((ListenableVector<ElementType>) source).getTempTreeNode(source), toNode, getContainingShapes(source), source, source);

	}

	public void elementCopiedFromTemp(Object source, int toIndex) {
		super.elementCopiedFromUserObject(source, toIndex);
//		ListenableVector<BoundedShape>  userObjectList = (ListenableVector) visualizer.getVisualization().getUserObjectShape();
		ListenableVector<BoundedShape>  userObjectList = (ListenableVector) getTempShapes(source);

		TreeNode parent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);
		TreeNode toNode = parent.getVector().get(toIndex);
		getLayoutManager((ListenableVector<ElementType>) source).removeTreeNode(toNode.getObject());

//		elementCopied(((ATreeLayoutManager )visualizer.getLayoutManager()).getUserObjectTreeNode(), toNode, visualizer.getVisualization().getShapes().get(0));
		elementCopied(getLayoutManager((ListenableVector<ElementType>) source).getTempTreeNode(source), toNode, getContainingShapes(source), source, source);

	}

	
//public static void elementCopied(BoundedShape fromShape, BoundedShape toShape, BoundedShape copiedShape, boolean changeY) {
//		
////		SimpleShape clonedFromShape = (SimpleShape) Common.deepCopy(fromShape);
////		FancyShape copiedShape = (FancyShape) visualizer.getShapes().getCopiedObjectShape();
//		Rectangle oldBounds = new Rectangle (copiedShape.getBounds());
//ss
//		copiedShape.copy((FancyShape) fromShape) ;
////		copiedShape.setX(copiedShape.getX() + COPY_DELTA_X);
//		
////		((AShapeModel) clonedFromShape).initSerializedObject();
////		visualizer.addElement(clonedFromShape);
////		clonedFromShape.setX(0);
////		Rectangle bounds = copiedShape.getBounds();
//		Rectangle newBounds = new Rectangle(copiedShape.getBounds());
//		newBounds.x = toShape.getX();
//		if (changeY)
//		newBounds.y = toShape.getY();
//		AnimationUtil.newBoundsModular(copiedShape, newBounds);
////		AnimationUtil.move(copiedShape, toShape.getX(), copiedShape.getY(), true,
////				Color.BLACK, Color.BLACK);
////		int newHeight = element * layoutManager.getScaleFactor();
////		toShape.setY( layoutManager.getBaseLine()
////				- newHeight);
//		toShape.copy((BoundedShape) copiedShape);
////		toShape.setHeight(newHeight);
////		copiedShape.setBounds(0, 0, 0, 0);
//		copiedShape.setBounds(oldBounds);
//
////		visualizer.remove(visualizer.size() -1);
//
//		
//
////		AnimationUtil.move(shape, shape.getX(), layoutManager.getBaseLine()
////				- shape.getHeight(), true, Color.BLACK, Color.BLACK);
//
//
//	}

	public synchronized void elementCopied(Object source, int fromIndex,
			int fromNewSize, Object to, int toIndex) {
		super.elementCopied(source, fromIndex, fromNewSize, to, toIndex);
		TreeNode parent1 = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);


		TreeNode fromNode = parent1.getVector().get(fromIndex);
		TreeNode parent2 = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(to);

		TreeNode toNode = parent2.getVector().get(toIndex);
//		elementCopied(fromNode, toNode, visualizer.getVisualization().getShapes().get(0));
		elementCopied(fromNode, toNode, getContainingShapes(source), source, source);

//		AGraphicIntegerCollectionVectorMethodsAnimator.elementCopied(
//				fromNode.getTreeNodeShape(), 
//				toNode.getTreeNodeShape(),
//				visualizer.getShapes().getCopiedObjectShape(), true);

	}
	
//	public static List<Dimension> modifyForGrowing(List<TreeNodeShape> aShapeList) {
//		List<Dimension> oldSizeList = new ArrayList();
//
//		for (int i = 0; i < aShapeList.size(); i++) {			 
//			 Dimension oldSize = new Dimension( aShapeList.get(i).getWidth(), aShapeList.get(i).getHeight());
//			 aShapeList.get(i).setWidth(0);
//			 aShapeList.get(i).setHeight(0);
//			
//			 oldSizeList.add(oldSize);
//			 
//		 }
////		visualizer.addTreeNodeShapes(aShapeList);
//
//		return oldSizeList;
//	}
	

	public List<Dimension>  modifyForGrowingAndInsertShapes (TreeNode aParentNode,
			List<TreeNodeShape> aShapeList, Object source) {
//		List<Dimension> oldSizeList = new ArrayList();
//		List<TreeNodeShape> aShapeList = visualizer.getLayoutManager().getTreeNodeShapes(newElement);

		List<Dimension> oldSizeList = AnimationUtil.modifyForGrowing(aShapeList);
		getLayoutManager((ListenableVector<ElementType>) source).addTreeNodeShapes(getLayoutManager((ListenableVector<ElementType>) source).getContainingShapes(aParentNode), aShapeList);
		return oldSizeList;
	}

	
	public void makeAppear(TreeNode newElement, Object source ) {
//		List<Dimension> oldSizeList = new ArrayList();
		List<TreeNodeShape> aShapeList = getLayoutManager((ListenableVector<ElementType>) source).getTreeNodeShapes(newElement);
//
//		List<Dimension> oldSizeList = AnimationUtil.modifyForGrowing(aShapeList);
//		visualizer.getLayoutManager().addTreeNodeShapes(aShapeList);
		List<Dimension> oldSizeList = modifyForGrowingAndInsertShapes(
				newElement,
				aShapeList, source);
		



//		 for (int i = 0; i < aShapeList.size(); i++) {			 
//			 Dimension oldSize = new Dimension( aShapeList.get(i).getWidth(), aShapeList.get(i).getHeight());
//			 aShapeList.get(i).setWidth(0);
//			 aShapeList.get(i).setHeight(0);
//			
//			 oldSizeList.add(oldSize);
//			 
//		 }
////		parent.setChildrenXY();
//
//		visualizer.addTreeNodeShapes(newElement);

//		AnimationUtil.newBoundsModular(newElement.getTreeNodeShape(), newBounds);
//		AnimationUtil.makeAppear(treeNodeShapes);
		AnimationUtil.newSize(aShapeList, oldSizeList);
		
	}
	
	 synchronized TreeNode insertNewTreeNode(Object source,
			ElementType element, int pos) {
		 TreeNode parent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);
			
			TreeNode newElement = getLayoutManager((ListenableVector<ElementType>) source).createTreeAndShapes(element);

			parent.getVector().insertElementAt(newElement, pos);
			newElement.setParent(parent);
			parent.setChildrenXY();
			parent.recomputeDependentsInSubTree();
			return newElement;
		
	}
	

	public synchronized void elementInserted(Object source,
			ElementType element, int pos, int newSize) {
		super.elementInserted(source, element, pos, newSize);

//		TreeNode parent = visualizer.getLayoutManager().getTreeNode(source);
//		
//		TreeNode newElement = visualizer.getLayoutManager().createTreeAndShapes(element);
//
//		parent.getVector().insertElementAt(newElement, pos);
//		newElement.setParent(parent);
//		parent.setChildrenXY();
//		parent.recomputeDependentsInSubTree();
		TreeNode newElement = insertNewTreeNode(source, element, pos);
		makeAppear(newElement, source);

//
//		List<Dimension> oldSizeList = new ArrayList();
//		List<TreeNodeShape> aShapeList = visualizer.getTreeNodeShapes(newElement);
//
//
//		 for (int i = 0; i < aShapeList.size(); i++) {			 
//			 Dimension oldSize = new Dimension( aShapeList.get(i).getWidth(), aShapeList.get(i).getHeight());
//			 aShapeList.get(i).setWidth(0);
//			 aShapeList.get(i).setHeight(0);
//			
//			 oldSizeList.add(oldSize);
//			 
//		 }
////		parent.setChildrenXY();
//
//		visualizer.addTreeNodeShapes(newElement);
//
////		AnimationUtil.newBoundsModular(newElement.getTreeNodeShape(), newBounds);
////		AnimationUtil.makeAppear(treeNodeShapes);
//		AnimationUtil.newSize(aShapeList, oldSizeList);


	}
//	public synchronized void elementInserted(Object source,
//			ElementType element, int pos, int newSize) {
//
////		TreeNode parent = root;
//		TreeNode parent = visualizer.getTreeNode(source);
//	
//		TreeNode previousChild = pos - 1 >= 0 ? root.getVector().get(
//				pos - 1) : null;
//
//		TreeNode newElement = visualizer.initElement(element, parent,
//				previousChild);
//
//		if (pos + 1 < newSize) // if there is a child after us
//			// use pos not pos + 1 because the element has not been
//			// added to the vector yet, so pos + 1 in the user's
//			// vector is pos in the roots vector.
//			root.getVector().get(pos).setPreviousChild(newElement);
//
//		parent.getVector().insertElementAt(newElement, pos);
//		parent.focusPosition();
//	}

//	ListenableShapeVector getContainingShapes(Object source) {
//		return visualizer.getLayoutManager().getShapesVector((ListenableVector) source);
//
//	}

	public synchronized void elementRemoved(Object source, int pos, int newSize) {
		super.elementRemoved(source, pos, newSize);
		TreeNode parent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);


		TreeNode toBeRemoved = parent.getVector().get(pos);
		TreeNodeShape shape = toBeRemoved.getTreeNodeShape();
//		TreeNode prevChild = toBeRemoved.getPreviousChild();
//		TreeNode nextChild = toBeRemoved.getNextChild(pos);
//		if (nextChild != null)
//			nextChild.setPreviousChild(prevChild);
		List<TreeNodeShape> treeNodeShapes = getLayoutManager((ListenableVector<ElementType>) source).getTreeNodeShapes(toBeRemoved);
//		Rectangle newBounds = new Rectangle ( shape.getBounds());
//		newBounds.width = 0;
//		newBounds.height = 0;
//		AnimationUtil.newBoundsModular(shape, newBounds);

		AnimationUtil.makeDisappear(treeNodeShapes);
		parent.getVector().remove(pos);

//		Object objectToBeRemoved = ((ListenableVector) source).get(pos);
//		shapes.remove(toBeRemoved.getTreeNodeShape());
//		visualizer.removeLine(toBeRemoved.getVerticalLine());
//		visualizer.removeLine(toBeRemoved.getHorizontalLine());
		getLayoutManager((ListenableVector<ElementType>) source).removeTreeNodeShapes(
				getLayoutManager((ListenableVector<ElementType>) source).getContainingShapes(toBeRemoved),
				
				toBeRemoved);
		parent.setChildrenXY();
		parent.getTreeNodeShape().sythesizeHeightOfLineToChildren();
//		parent.setVertilcalLineSize();
		Object element = toBeRemoved.getObject();
		getLayoutManager((ListenableVector<ElementType>) source).removeTreeNode(element);
//		parent.focusPosition();
//
//
////		TreeNode parent = root;
//		TreeNode previousChild = toBeRemoved.getPreviousChild();
//
//		if (pos + 1 <= newSize)// If it is not the last element
//			parent.getVector().get(pos + 1).setPreviousChild(previousChild);
//
//		parent.getVector().remove(toBeRemoved);
//
//		parent.focusPosition();
	}

	public synchronized void elementRemoved(Object source, ElementType element,
			int newSize, int pos) {
		ListenableVector listenableVector = (ListenableVector) source;
//		int pos = listenableVector.indexOf(element);
		elementRemoved(source, pos, newSize);
//		super.elementRemoved(source, element, newSize);
		// TODO Auto-generated method stub

	}

	public synchronized void elementReplaced(Object source, int fromIndex,
			int toIndex, int newSize) {
		// TODO Auto-generated method stub

	}

	public synchronized void elementReplaced(Object source, int fromIndex,
			int newFromSize, Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public synchronized void elementsCleared(Object source) {
		// TODO Auto-generated method stub

	}

	public synchronized void collectionRemoved(int collectionNum) {
		// TODO Auto-generated method stub

	}

	public synchronized void elementSwapped(Object source, int index1,
			Object other, int index2) {
		super.elementSwapped(source, index1, other, index2);
		TreeNode parent1 = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);
		TreeNode child1 = parent1.getVector().get(index1);	
//		TreeNode child1PrevChild = child1.getPreviousChild();
//		TreeNode child1Parent = child1.getParent();
		TreeNode parent2 = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(other);
//		TreeNode child1NextNode = child1.getNextChild(index1);
		TreeNode child2 = parent2.getVector().get(index2);
		changeTreeNodeDataStructure(parent1, child1, index1, parent2, child2, index2);
//		TreeNode child2NextNode = child2.getNextChild(index2);
//
//		parent1.getVector().remove(index1);
//		parent2.getVector().remove(index2);
//		parent1.getVector().insertElementAt(child2, index1);
//		parent2.getVector().insertElementAt(child1, index2);
//		child1.setPreviousChild(child2.getPreviousChild());
//		child1.setParent(parent2);
//		child2.setPreviousChild(child1PrevChild);
//		child2.setParent(parent1);	
//		child1NextNode.setPreviousChild(child2);
//		child2NextNode.setPreviousChild(child1);
		child1.setVisuallyUnparented(true);
		child2.setVisuallyUnparented(true);
		AnimationUtil.swapLocations(child1.getTreeNodeShape(), child2.getTreeNodeShape());
		child1.setVisuallyUnparented(false);
		child2.setVisuallyUnparented(false);
//		parent1.focusPosition();
//		parent1.setChildrenXY();
		parent1.recomputeDependentsInSubTree();
		if (parent2 != parent1)
			parent2.recomputeDependentsInSubTree();
//			parent2.setChildrenXY();
//		parent2.focusPosition();

	}
	
//	public synchronized void elementMoved(Object source, int index1,
//			Object other, int index2) {
//		
//
//	}


	/*
	 * This method takes two elements and exchanges their parents and previous
	 * elements. It then makes sure spacing is correct by calling
	 * focusPositionAndLines
	 */
	public synchronized void elementSwapped(Object newParam, int index1,
			int index2) {
		super.elementSwapped(newParam, index1, index2);

		if (index1 < index2) {
			swapElements(newParam, index1, index2);
		} else {
			swapElements(newParam, index2, index1);
		}

	}
	public synchronized void elementMoved(Object source, int fromIndex,
			int toIndex) {
		elementMoved(source, fromIndex, 0, source, toIndex);
	}

	public synchronized void elementMoved(Object source, int fromIndex,
			int fromNewSize, Object to, int toIndex) {
		super.elementMoved(source, fromIndex, fromNewSize, to, toIndex);
		
		
		TreeNode fromParent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);
		TreeNode fromChild = fromParent.getVector().get(fromIndex);	

		TreeNode toParent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(to);
//		TreeNode child1NextNode = child1.getNextChild(index1);
		TreeNode toChild = toParent.getVector().get(toIndex);
//		TreeNodeShape toTreeNodeShape = toChild.getTreeNodeShape();
		getLayoutManager((ListenableVector<ElementType>) source).removeTreeNode(fromChild.getObject());
		TreeNode newElement = insertNewTreeNode(source, (ElementType) fromChild.getObject(), toIndex);
		List<TreeNodeShape> insertedTreeNodeShapes = getLayoutManager((ListenableVector<ElementType>) source).getTreeNodeShapes(newElement);
		List<Dimension> insertedTreeNodeSizeList = modifyForGrowingAndInsertShapes(toParent, insertedTreeNodeShapes, source);
		List<TreeNodeShape> deletedTreeNodeShapes = getLayoutManager((ListenableVector<ElementType>) source).getTreeNodeShapes(fromChild);


		changeTreeNodeDataStructureForMove(fromParent, fromChild, fromIndex, toParent, toChild, toIndex);
//		TreeNode child2NextNode = child2.getNextChild(index2);
//
//		parent1.getVector().remove(index1);
//		parent2.getVector().remove(index2);
//		parent1.getVector().insertElementAt(child2, index1);
//		parent2.getVector().insertElementAt(child1, index2);
//		child1.setPreviousChild(child2.getPreviousChild());
//		child1.setParent(parent2);
//		child2.setPreviousChild(child1PrevChild);
//		child2.setParent(parent1);	
//		child1NextNode.setPreviousChild(child2);
//		child2NextNode.setPreviousChild(child1);
		fromChild.setVisuallyUnparented(true);
//		fromParent.getVector().remove(fromIndex);

//		toChild.setVisuallyUnparented(true);
		AnimationUtil.moveInsertAndDelete(fromChild.getTreeNodeShape(),toChild.getTreeNodeShape(), deletedTreeNodeShapes, insertedTreeNodeShapes, insertedTreeNodeSizeList);
		fromChild.setVisuallyUnparented(false);
//		removeAllTreeNodeShapes(visualizer.getVisualization().getShapes().get(0), deletedTreeNodeShapes);
		removeAllTreeNodeShapes(getContainingShapes(source), deletedTreeNodeShapes);

//		toChild.setVisuallyUnparented(false);
//		parent1.focusPosition();
//		parent1.setChildrenXY();
		fromParent.recomputeDependentsInSubTree();
		if (toParent != fromParent)
			toParent.recomputeDependentsInSubTree();
//			parent2.setChildrenXY();
//		parent2.focusPosition();

	}

	/*
	 * This method takes two elements and exchanges their parents and previous
	 * elements. It then makes sure spacing is correct by calling
	 * focusPositionAndLines
	 * 
	 * This method assumes index1 < index2
	 */
//	private synchronized void swapElements(Object newParam, int index1,
//			int index2) {
//
//		// the two elements to be swapped
//		TreeNode firstElement = root.getVector().get(index1);
//		TreeNode secondElement = root.getVector().get(index2);
//
//		// the two elements after each of the elements to be swapped
//		TreeNode secondElementNext;
//		TreeNode firstElementNext;
//		/*
//		 * There is a corner case where secondElement.previousChild is
//		 * firstElement
//		 */
//
//		// the first element's previous child before it is reassigned
//		TreeNode tempPrevChild = firstElement.getPreviousChild();
//
//		if (index2 != index1 + 1) {// the elements are not adjacent
//
//			// set previous elements for the two nodes
//			firstElement.setPreviousChild(secondElement.getPreviousChild());
//			secondElement.setPreviousChild(tempPrevChild);
//
//			// set previous elements for the nodes referencing the swapped nodes
//			if (index1 + 1 < root.getVector().size()) {
//				firstElementNext = root.getVector().get(index1 + 1);
//				firstElementNext.setPreviousChild(secondElement);
//			}
//			if (index2 + 1 < root.getVector().size()) {
//				secondElementNext = root.getVector().get(index2 + 1);
//				secondElementNext.setPreviousChild(firstElement);
//			}
//
//		} else { // the elements are adjacent
//			firstElement.setPreviousChild(secondElement);
//			secondElement.setPreviousChild(tempPrevChild);
//
//			// set previous elements for the nodes referencing the second node
//			if (index2 + 1 < root.getVector().size()) {
//				secondElementNext = root.getVector().get(index2 + 1);
//				secondElementNext.setPreviousChild(firstElement);
//			}
//		}
//
//		// swap the elements in root
//		root.getVector().swap(index1, index2);
//
//		root.focusPosition();
//	}
	public void userObjectChanged(Object source, Object newVal) {
//		TreeNode newElement = visualizer.createTreeAndShapes(newVal);
		super.userObjectChanged(source, newVal);
//		if (visualizer.isDisplayName())
//		changeUserObjectShape(source, newVal);
//		else 
			changeUserObjectContent(source, newVal);


		
		
	}
	public void tempChanged(Object source, Object newVal) {
//		TreeNode newElement = visualizer.createTreeAndShapes(newVal);
		super.tempChanged(source, newVal);
//		if (visualizer.isDisplayName())
		    changeUserObjectShape(source, newVal);
//		else 
//			changeUserObjectContent(source, newVal);


		
		
	}
	void changeUserObjectContent (Object source, Object newVal) {
		TreeNode sourceNode = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);
		ListenableShapeVector aContainingShapes = getContainingShapes(source);
		changeContentShape(null, sourceNode, newVal, aContainingShapes, -1, source);
	}
	public void changeParentNodeShape (Object source, Object newVal) {
		 TreeNode userObjectNode = getLayoutManager((ListenableVector<ElementType>) source).getTempTreeNode(source);
//		 ListenableVector<BoundedShape> userObjectList =  (ListenableVector) visualizer.getVisualization().getUserObjectShape();
		 ListenableVector<BoundedShape> userObjectList =  (ListenableVector) getTempShapes(source);

		 TreeNode newElement = elementChanged(null, userObjectNode, newVal, userObjectList, -1, source);
		 ((ATreeLayoutManager )getLayoutManager((ListenableVector<ElementType>) source)).setTempTreeNode(source, newElement);
//			child.setTreeNodeShape(newElement.getTreeNodeShape());
//			child.recomputeDependentsInSubTree();
			newElement.recomputeDependentsInSubTree();

		
	}
	
	public void changeUserObjectShape (Object source, Object newVal) {
		 TreeNode userObjectNode = getLayoutManager((ListenableVector<ElementType>) source).getTempTreeNode(source);
//		 ListenableVector<BoundedShape> userObjectList =  (ListenableVector) visualizer.getVisualization().getUserObjectShape();
		 ListenableVector<BoundedShape> userObjectList =  (ListenableVector) getTempShapes(source);

		 TreeNode newElement = elementChanged(null, userObjectNode, newVal, userObjectList, -1, source);
		 getLayoutManager((ListenableVector<ElementType>) source).setTempTreeNode(source, newElement);
//			child.setTreeNodeShape(newElement.getTreeNodeShape());
//			child.recomputeDependentsInSubTree();
			newElement.recomputeDependentsInSubTree();

		
	}
	public BoundedShape getUserObjectShape(Object source) {
		TreeNode userObjectNode = getLayoutManager((ListenableVector<ElementType>) source).getTempTreeNode(source);
		return userObjectNode.getTreeNodeShape();
		 
			
	 }
	
	public ListenableVector getTempShapes(Object source) {
//	TreeNode userObjectNode = ((ATreeLayoutManager )visualizer.getLayoutManager()).getUserObjectTreeNode();
//	return userObjectNode.getTreeNodeShape();
		return (ListenableVector) getContainingShapes(source).getTempShape();
	 
		
 }
	

	
	void removeAllTreeNodeShapes (List aList, List<TreeNodeShape> aSubList) {
		aList.removeAll(aSubList);
//		for (TreeNodeShape aTreeNodeShape:aSubList) {
//			aTreeNodeShape.setTreeNode(null);
//		}
		
	}

	public synchronized TreeNode elementChanged(TreeNode parent, TreeNode child,
			Object element, ListenableVector<BoundedShape> listContainingChild, int pos, Object source) {
		
		TreeNodeShape shape = child.getTreeNodeShape();

		TreeNode newElement = getLayoutManager((ListenableVector<ElementType>) source).
				createTreeAndShapes(element);
		newElement.setChildrenXY();
		// this step must come before the setX
//		newElement.getTreeNodeShape().getHorizontalLine().setWidth(child.getTreeNodeShape().getHorizontalLine().getWidth());

		newElement.getTreeNodeShape().setX(child.getTreeNodeShape().getX());
		newElement.getTreeNodeShape().setY(child.getTreeNodeShape().getY());

		List<TreeNodeShape> oldShapes = getLayoutManager((ListenableVector<ElementType>) source).getTreeNodeShapes(child);
		List<TreeNodeShape> newShapes = getLayoutManager((ListenableVector<ElementType>) source).getTreeNodeShapes(newElement);
		List targetOldSizeList = AnimationUtil.modifyForShrinking(oldShapes);
		List targetNewSizeList = AnimationUtil.modifyForGrowing(newShapes);
//		visualizer.addTreeNodeShapes(newShapes);
		listContainingChild.addAll(newShapes);
		List<TreeNodeShape> mergedShapes = new ArrayList(oldShapes);
		mergedShapes.addAll(newShapes);
		List<Dimension> mergedSizeList = new ArrayList(targetOldSizeList);
		mergedSizeList.addAll(targetNewSizeList);
		AnimationUtil.newSize(mergedShapes, mergedSizeList);

		List<TreeNodeShape> childShapes = getLayoutManager((ListenableVector<ElementType>) source).getTreeNodeShapes(child);
		removeAllTreeNodeShapes (listContainingChild, childShapes);


		
		return newElement;


	}
	
	public synchronized void changeContentShape(TreeNode parent, TreeNode child,
			Object element, ListenableVector<BoundedShape> listContainingChild, int pos, Object source) {
		
		TreeNodeShape shape = child.getTreeNodeShape();
		BoundedShape contentShape = shape.getContentShape();
//		BoundedShape newContentShape = getLayoutManager((ListenableVector<ElementType>) parent).
//				createShape(contentShape.getX(), contentShape.getY(), -1, -1, element);
		BoundedShape newContentShape = getLayoutManager((ListenableVector<ElementType>) source).
				createShape(null, (ElementType) element, null, new Rectangle(contentShape.getX(), contentShape.getY(), -1, -1), null);
		
		Dimension newContentTargetSize = new Dimension (contentShape.getWidth(), contentShape.getHeight());
		newContentShape.setWidth(0);
		newContentShape.setHeight(0);
		Dimension oldContentTargetSize = new Dimension (0, 0);
		int addPosition = listContainingChild.size();
		listContainingChild.add(newContentShape);
		List<BoundedShape> targetShapes = new ArrayList(2);
		targetShapes.add(contentShape);
		targetShapes.add(newContentShape);
		List<Dimension> targetDimensions = new ArrayList(2);
		targetDimensions.add(oldContentTargetSize);
		targetDimensions.add(newContentTargetSize);

		
		AnimationUtil.newSize(targetShapes, targetDimensions);

		contentShape.copy(newContentShape);
		// so that observables of shape know about the change
//		shape.recomputeDependents();
		listContainingChild.remove(addPosition);

//		listContainingChild.remove(addPosition);

		


	}
	
	public synchronized void elementChanged(Object source, ElementType element,
			int pos) {
		super.elementChanged(source, element, pos);
		
		TreeNode parent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);
		TreeNode child = parent.getVector().get(pos);
		Object oldObject = child.getObject();
		getLayoutManager((ListenableVector<ElementType>) source).removeTreeNode(oldObject);
//		elementChanged(parent, child, element, visualizer.getVisualization().getShapes().get(0), pos);
		TreeNode newElement = elementChanged(parent, child, element, getContainingShapes(source), pos, source);
		parent.getVector().set(pos, newElement);
		newElement.setParent(parent);
		parent.getRoot().recomputeDependentsInSubTree();
		
//		TreeNodeShape shape = child.getTreeNodeShape();
//
//		TreeNode newElement = visualizer.createTreeAndShapes(element);
//		newElement.setChildrenXY();
//		// this step must come before the setX
//		newElement.getTreeNodeShape().getHorizontalLine().setWidth(child.getTreeNodeShape().getHorizontalLine().getWidth());
//
//		newElement.getTreeNodeShape().setX(child.getTreeNodeShape().getX());
//		newElement.getTreeNodeShape().setY(child.getTreeNodeShape().getY());
//
//		List<TreeNodeShape> oldShapes = visualizer.getTreeNodeShapes(child);
//		List<TreeNodeShape> newShapes = visualizer.getTreeNodeShapes(newElement);
//		List targetOldSizeList = AnimationUtil.modifyForShrinking(oldShapes);
//		List targetNewSizeList = AnimationUtil.modifyForGrowing(newShapes);
//		visualizer.addTreeNodeShapes(newShapes);
//		List<TreeNodeShape> mergedShapes = new ArrayList(oldShapes);
//		mergedShapes.addAll(newShapes);
//		List<Dimension> mergedSizeList = new ArrayList(targetOldSizeList);
//		mergedSizeList.addAll(targetNewSizeList);
//		AnimationUtil.newSize(mergedShapes, mergedSizeList);
////		List<BoundedShape> newAndOldShape = new ArrayList();
////		newAndOldShape.add(child.getTreeNodeShape());
////		newAndOldShape.add(newElement.getTreeNodeShape());
////		List<Rectangle> newAndOldBounds = new ArrayList();
////		newAndOldBounds.add(new Rectangle(oldBounds));
////		newAndOldBounds.add(new Rectangle(oldBounds));
//////		AnimationUtil.newBoundsModular(child.getTreeNodeShape(), oldBounds);
////		AnimationUtil.newBounds(newAndOldShape, newAndOldBounds);
//
//		visualizer.removeTreeNodeShapes(child);
//		child.copy(newElement);
////		child.setXY();
////		parent.setXY();
//		parent.recomputeDependentsInSubTree();
////		parent.setChildrenXY();
//		visualizer.removeTreeNodeShapes(newElement);
//		visualizer.addTreeNodeShapes(child);
//		
//
//
////		AnimationUtil.makeDisappear(oldShapes);
//
//
////		AnimationUtil.makeAppear(null);
//
////		if (shape.getMainShape() instanceof TextShape)
////			((TextShape) shape.getMainShape()).setText(element.toString());
////		if (shape.getMainShape() instanceof LabelShape)
////			((LabelShape) shape.getMainShape()).setText(element.toString());
//		
////		AnimationUtil.newBoundsModular(child.getTreeNodeShape(), oldBounds);
//		
//		
//
////		AnimationUtil.move(node, node.getX() - boxWidth / 3, node.getY(), true,
////				((ATreeLayoutManager<ElementType>) visualizer
////						.getLayoutManager()).getHighlighting(),
//////				((OEShape) visualizer.getLayoutManager()).getColor());
////		null);
	}
	
	
	private synchronized void swapElements(Object source, int index1,
			int index2) {
		
		TreeNode parent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);
		TreeNode child1 = parent.getVector().get(index1);
		TreeNode child2 = parent.getVector().get(index2);
//		TreeNode child1PrevChild = child1.getPreviousChild();
//		TreeNode child1NextNode = child1.getNextChild(index1);
		changeTreeNodeDataStructure(parent, child1, index1, parent, child2, index2);

		AnimationUtil.swapLocations(child1.getTreeNodeShape(), child2.getTreeNodeShape());
//		parent.setChildrenXY();
		parent.recomputeDependentsInSubTree();
	}
	 public BoundedShape getElementShape(Object source,  Integer pos) {
		 TreeNode parent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);
			TreeNode child = parent.getVector().get(pos);
			return child.getTreeNodeShape();
				
		 }
	 
	 public BoundedShape getParentShape(Object source) {
		 TreeNode parent = getLayoutManager((ListenableVector<ElementType>) source).getTreeNode(source);
			return parent.getTreeNodeShape();
				
		 }

//	public BoundedShape getPointedShape(Object source, ElementType element, Integer pos) {
//		 BoundedShape pointedShape = null;
//		 if (pos == null) {
//				pointedShape = null;
//			}
//			else 
//				if (pos >= 0) {
//					return getElementShape(source, pos);
//			
////					TreeNode parent = visualizer.getTreeNode(source);
////					TreeNode child1 = parent.getVector().get(pos);
////					return child1.getTreeNodeShape();
//				} else
//				pointedShape = (BoundedShape) visualizer.getShapes().getUserObjectShape();
//		 return pointedShape;
//	 }
	void changeTreeNodeDataStructure(TreeNode parent1, TreeNode child1, int index1, 
			                      TreeNode parent2, TreeNode child2, int index2 ) {
//		TreeNode child1PrevChild = child1.getPreviousChild();
//		TreeNode child1NextNode = child1.getNextChild(index1);
//		TreeNode child2NextNode = child2.getNextChild(index2);
		parent1.getVector().remove(index1);
		parent1.getVector().insertElementAt(child2, index1);
		parent2.getVector().remove(index2);
		parent2.getVector().insertElementAt(child1, index2);
		child1.setParent(parent2);
		child2.setParent(parent1);	
//		if (parent1 == parent2 && index2 == index1 + 1) {
//			child1.setPreviousChild(child2);
//			child2.setPreviousChild(child1PrevChild);
//			if (child2NextNode != null)
//				child2NextNode.setPreviousChild(child1);
//		} else {
//		child1.setPreviousChild(child2.getPreviousChild());
//		child2.setPreviousChild(child1PrevChild);
//		if (child1NextNode != null)
//		child1NextNode.setPreviousChild(child2);
//		if (child2NextNode != null)
//		child2NextNode.setPreviousChild(child1);
//		}

		
	}
	
	void changeTreeNodeDataStructureForMove(TreeNode fromParent,
			TreeNode fromChild, int fromIndex, TreeNode toParent,
			TreeNode toChild, int toIndex) {
		// TreeNode child1PrevChild = child1.getPreviousChild();
		// TreeNode child1NextNode = child1.getNextChild(index1);
		// TreeNode child2NextNode = child2.getNextChild(index2);
		if (toIndex > fromIndex || fromParent != toParent) {
//			toParent.getVector().insertElementAt(fromChild, toIndex);
			fromParent.getVector().remove(fromIndex);
//
//			super.insertElementAt(temp, toIndex);
//			super.remove(fromIndex);
		} else if (toIndex < fromIndex) {
//			toParent.getVector().insertElementAt(fromChild, toIndex - 1);
			fromParent.getVector().remove(fromIndex + 1);

		}
//		toParent.getVector().insertElementAt(fromChild, toIndex);
//		fromParent.getVector().remove(fromIndex);

		fromChild.setParent(toParent);
		// if (parent1 == parent2 && index2 == index1 + 1) {
		// child1.setPreviousChild(child2);
		// child2.setPreviousChild(child1PrevChild);
		// if (child2NextNode != null)
		// child2NextNode.setPreviousChild(child1);
		// } else {
		// child1.setPreviousChild(child2.getPreviousChild());
		// child2.setPreviousChild(child1PrevChild);
		// if (child1NextNode != null)
		// child1NextNode.setPreviousChild(child2);
		// if (child2NextNode != null)
		// child2NextNode.setPreviousChild(child1);
		// }

	}
	
//	public synchronized void elementSwapped(Object source, int index1,
//			Object other, int index2) {
//		TreeNode parent1 = visualizer.getTreeNode(source);
//		TreeNode child1 = parent1.getVector().get(index1);	
//		TreeNode child1PrevChild = child1.getPreviousChild();
////		TreeNode child1Parent = child1.getParent();
//		TreeNode parent2 = visualizer.getTreeNode(other);
//		TreeNode child1NextNode = child1.getNextChild(index1);
//
//		TreeNode child2 = parent2.getVector().get(index2);
//		TreeNode child2NextNode = child2.getNextChild(index2);
//
//		parent1.getVector().remove(index1);
//		parent2.getVector().remove(index2);
//		parent1.getVector().insertElementAt(child2, index1);
//		parent2.getVector().insertElementAt(child1, index2);
//		child1.setPreviousChild(child2.getPreviousChild());
//		child1.setParent(parent2);
//		child2.setPreviousChild(child1PrevChild);
//		child2.setParent(parent1);	
//		child1NextNode.setPreviousChild(child2);
//		child2NextNode.setPreviousChild(child1);
//		
//		AnimationUtil.swapBounds(child1.getTreeNodeShape(), child2.getTreeNodeShape());
//		parent1.focusPosition();
//		if (parent2 != parent1)
//		parent2.focusPosition();
//
//	}

	public synchronized void elementsAdded(Object source,
			Collection<? extends ElementType> element, int newSize) {
		// TODO Auto-generated method stub

	}

	public void addListener(
			EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> observer)
			throws Exception {
		
	
	}

	public void removeListener(
			EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> observer) {
		// TODO Auto-generated method stub
		
	}

	public void elementCopiedAndInserted(Object source, int fromIndex,
			int toIndex, int newSize) {
		// TODO Auto-generated method stub
		
	}

	public void elementCopiedAndInserted(Object source, int fromIndex,
			int fromNewSize, Object to, int toIndex) {
		// TODO Auto-generated method stub
		
	}

	

//	public void userObjectRead(Object source, Object readValue) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void tempRead(Object source, Object readValue) {
//		// TODO Auto-generated method stub
//		
//	}

	

	
}
