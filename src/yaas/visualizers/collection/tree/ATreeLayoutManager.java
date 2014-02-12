package yaas.visualizers.collection.tree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import shapes.TextShape;
import util.misc.AnObjectColorManager;
import util.misc.ObjectColorManager;
import util.models.AListenableVector;
import util.models.Hashcodetable;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import util.trace.Tracer;
import yaas.PseudoCode;
import yaas.VisualizationBasedVisualizer;
import yaas.buffers.vector.ALinearBuffer;
import yaas.buffers.vector.ATreeBuffer;
import yaas.collection.ACollectionLayoutManager;
import yaas.common.Util;
import yaas.layout.ACompositeRowColumnLayoutManager;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.layout.nodes.ATreeNode;
import yaas.layout.nodes.ATreeNodeShape;
import yaas.layout.nodes.ATreeRoot;
import yaas.layout.nodes.TreeNode;
import yaas.layout.nodes.TreeNodeShape;
import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.shapemappers.AnObjectToTextModel;
import yaas.visualization.collection.CollectionVisualization;
import yaas.visualizers.collection.CollectionVisualizer;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.shapes.AShapeModel;
import bus.uigen.shapes.AStringModel;
import bus.uigen.shapes.ListenableShapeVector;

public class ATreeLayoutManager<ElementType> extends
		ACollectionLayoutManager<ElementType> implements
		TreeLayoutManager<ElementType> {
	// ShapeBasedLayoutManager<ListenableVector<ElementType>> {

	public static final int DEFAULT_POINTER_X = 50;
	public static final int DEFAULT_POINTER_Y = 15;
//	public static final int DEFAULT_ROOT_X = 50;
//	public static final int DEFAULT_ROOT_Y = 50;
	public static final int DEFAULT_ROOT_X = 10;
	public static final int DEFAULT_ROOT_Y = 20;
	private static final int DEFAULT_COPY_DELTA_X = 5;
	public static final int DEFAULT_TEMP_X_OFFSET = 200;
	public static final int DEFAULT_TEMP_Y_OFFSET = 0;

	public static final int DEFAULT_USER_OBJECT_Y = DEFAULT_ROOT_Y;
	public static final int DEFAULT_STATUS_X_OFFSET = 0;
	public static final int DEFAULT_STATUS_Y_OFFSET = 30;
	public static final int DEFAULT_STATUS_Y = DEFAULT_ROOT_Y
			- DEFAULT_STATUS_Y_OFFSET;

	protected TreeNode copiedObjectTreeNode/*, userObjectTreeNode, rootNode*/;
	protected CollectionVisualizer<ElementType> treeVisualizer;
	protected ListenableVector<AttributedShape> pseudoCode;
	protected int boxWidth = 0, boxHeight = 0;
	protected int verticalSpacing = 0, horizontalSpacing = 0;
	protected Color highlighting, color;
	protected boolean alignVertical, dynamicWidth, dynamicHeight, solid;
	protected int currentPseudoCodeMarker = 0;
	protected BoundedShape shape;
	protected int statusXOffset = DEFAULT_STATUS_X_OFFSET;
	protected int statusYOffset = DEFAULT_STATUS_Y_OFFSET;

	// ObjectColorManager objectColorManager = new AnObjectColorManager();
//	protected int statusX = DEFAULT_STATUS_X;
//	protected int statusY = DEFAULT_STATUS_Y;
	int tempXOffset = DEFAULT_TEMP_X_OFFSET;
	int tempYOffset = DEFAULT_TEMP_Y_OFFSET;
	Hashcodetable<Object, TreeNode> valueToTreeNode = new Hashcodetable();
	Hashcodetable<Object, TreeNodeShape> valueToTreeNodeShape = new Hashcodetable();
	Hashcodetable<Object, TreeNode> valueToTempTreeNode = new Hashcodetable();

	private AttributedShape previousPseudoCodeLine, currentPseudoCodeLine;

	private Container panel = new JPanel();
	private int initX = 20, initY = 20;
//	CollectionVisualization shapes;
	Map<TreeNodeKind, ObjectToShapeTranslator> treeNodeKindToContentShapeTranslator = new HashMap();

	// ObjectToShapeTranslator<ElementType> rootNodeToShapeTranslator,
	// internalNodeToShapeTranslator,
	// leafNodeToShapeTranslator;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ATreeLayoutManager(
	// ShapeBasedVisualizer<VectorMethodsListener<ElementType>,
	// ListenableVector<ElementType>> cv)
			CollectionVisualizer<ElementType> cv) {
		super(cv);
		pseudoCode = new AListenableVector<AttributedShape>();
		treeVisualizer =  cv;

		horizontalSpacing = 50;
		verticalSpacing = 10;
		// objectColorManager.setSurroundingColors(new Color[]
		// {AttributeNames.CAROLINA_BLUE, Color.BLACK});
		solid = true;
		copiedObjectTreeNode = new ATreeNode(null, null, null, null);
		copiedObjectTreeNode.setTreeVisualizer(treeVisualizer);
//		shapes = treeVisualizer.getVisualization();
//		shapes.getStatusShape().setX(statusX);
//		shapes.getStatusShape().setY(statusY);
//		shapes.getStatusShape().setText("Tree Animation");

		// AttributedShape shape = createShape(null, ROOT_X +
		// USER_OBJECT_X_OFFSET, ROOT_Y, null);
		// shape.setWidth(0);
		// shape.setHeight(0);
		// userObjectTreeNode = new ATreeNode(shape, null);
	}

	public ListenableVector<BoundedShape> display(
			ListenableVector<ElementType> vector) {
		super.display(vector);
//		ListenableShapeVector shapes = getCompositeLayoutManager().getAndPositionShapes(vector);
		TreeNode rootNode = createTreeAndShapes(vector);
		TreeNodeShape rootTreeNodeShape = rootNode.getTreeNodeShape();
		BoundedShape statusShape = visualizer.getVisualization().getStatusShape();
		Point rootLocation = rootLocation();
		rootTreeNodeShape.setX(rootLocation.x);
		rootTreeNodeShape.setY(rootLocation.y);
		rootNode.setChildrenXY();
		// AttributedShape userObjectShape = createShape("", 0, 0, null);
		// userObjectShape.setWidth(0);
		// userObjectShape.setHeight(0);
		// userObjectShape.setX(rootTreeNodeShape.getX() +
		// rootTreeNodeShape.getWidth() + USER_OBJECT_X_OFFSET);
		// userObjectShape.setY(rootTreeNodeShape.getY());

		// AttributedShape copiedObjectMainShape = createShape(null,
		// rootTreeNodeShape.getX() + USER_OBJECT_X_OFFSET, ROOT_Y, null);
		// BoundedShape copiedObjectShape = new
		// ATreeNodeShape(copiedObjectMainShape, (TreeNode) null);
		// copiedObjectShape.setWidth(0);
		// copiedObjectShape.setHeight(0);

		treeVisualizer.getVisualization()
				.setCopiedObjectShape(new AListenableVector());
//		treeVisualizer.getVisualization().setUserObjectShape(new AListenableVector());
		
//		shapes.setUserObjectShape(new AListenableVector());

		// visualizer.getShapes().setCopiedObjectShape(copiedObjectShape);
		addTreeNodeShapes(getContainingShapes(rootNode), rootNode);
		// AttributedShape userObjectMainShape = createShape(null,
		// rootTreeNodeShape.getX() + rootTreeNodeShape.getWidth() +
		// USER_OBJECT_X_OFFSET, ROOT_Y, null);
		//
		// userObjectTreeNode = new ATreeNode(userObjectMainShape, null);
		TreeNode tempTreeNode = createTreeAndShapes(null);
		valueToTempTreeNode.put(vector, tempTreeNode);
		TreeNodeShape tempNodeShape = tempTreeNode.getTreeNodeShape();
		Point tempLocation = tempLocation();
		tempNodeShape.setX(tempLocation.x);
		tempNodeShape.setY(tempLocation.y);
//		treeNodeShape.setX(rootTreeNodeShape.getX()
//				+ rootTreeNodeShape.getWidth() + DEFAULT_TEMP_X_OFFSET);
//		treeNodeShape.setY(DEFAULT_ROOT_Y);
//		((ListenableVector) treeVisualizer.getVisualization().getUserObjectShape())
//				.add(userObjectTreeNode.getTreeNodeShape());
		((ListenableVector) containingShapes.getTempShape())
		.add(tempTreeNode.getTreeNodeShape());
//		treeVisualizer.getVisualization().getStatusShape().setY(statusY);

		// visualizer.addTreeNodeShapes(userObjectTreeNode);

		return null;
	}
	
	protected Point rootLocation() {
		BoundedShape statusShape = visualizer.getVisualization().getStatusShape();
		int x = containingShapes.getX() + statusShape.getX() + statusXOffset;
		int y = containingShapes.getY() + statusShape.getY() + statusYOffset;
		return new Point (x, y);
		
	}
	
	public Point tempLocation() {
		Point rootLocation = rootLocation();
		int x = rootLocation.x + tempXOffset;
		int y = rootLocation.y + tempYOffset;
		return new Point(x, y);
	}
	
	public  Object initialTempShape(ListenableVector parent, ListenableShapeVector containingShapes) {
		return new AListenableVector();
	}


	public ListenableVector<AttributedShape> constructPseudoCode() {
		int i = 20;
		for (String s : PseudoCode.pseudoCode) {
			int numLeadingSpaces = Util.findLeadingSpaces(s);
			// this.pseudoCode.add(new AStringModel(s, 10+ numLeadingSpaces, i,
			// 100, 20));
			this.pseudoCode.add(new AStringModel(s, 10 + numLeadingSpaces, i));
			i += 20;
		}
		return this.pseudoCode;
	}

	public ListenableVector<AttributedShape> getPseudoCode() {
		return this.pseudoCode;
	}

	public BoundedShape createShape(ListenableVector aParent, ElementType anElement, Integer anIndex, Rectangle initBounds, ListenableShapeVector aConainingShapes) {
		TreeNodeKind aTreeNodeKind = toTreeNodeKind(anElement);
		ObjectToShapeTranslator<Object> objectToShapeTranslator = treeNodeKindToContentShapeTranslator
				.get(aTreeNodeKind);

		// try {
		// BoundedShape s = this.shape.getClass().newInstance();
		BoundedShape s = (BoundedShape) objectToShapeTranslator
				.translate(anElement);

		s.setX(initBounds.x);
		s.setY(initBounds.y);
		if (initBounds.width != -1)
			s.setWidth(initBounds.width);
		if (initBounds.height != -1)
			s.setHeight(initBounds.height);
		return s;

	}

	// public BoundedShape createShape(String stringValue, int initX, int initY,
	// Object anObject, TreeNodeKind aTreeNodeKind) {
	// ObjectToShapeTranslator<> objectToShapeTranslator =
	// treeNodeKindToContentShapeTranslator.get(aTreeNodeKind);
	//
	//
	// try {
	// // BoundedShape s = this.shape.getClass().newInstance();
	// BoundedShape s = (BoundedShape)
	// objectToShapeTranslator.translate(anObject);
	//
	// try {
	// s.setX(initX);
	// s.setY(initY);
	// if (stringValue == null) {
	// s.setHeight(0);
	// s.setWidth(0);
	//
	// } else {
	// s.setHeight(boxHeight
	// * (dynamicHeight ? Integer.parseInt(stringValue) : 1));
	//
	// s.setWidth(boxWidth
	// * (dynamicWidth ? Integer.parseInt(stringValue) : 1));
	// }
	// } catch (Exception e) {
	// s.setHeight(boxHeight);
	// s.setWidth(boxWidth);
	// }
	// ((AShapeModel) s).setFilled(solid);
	//
	// if (anObject != null && s instanceof AttributedShape) {
	// ((AttributedShape) s).setColor(objectColorManager.getColor(anObject));
	// }
	//
	//
	// if (s instanceof TextShape) {
	// ((TextShape) s).setText(stringValue);
	// }
	//
	// return s;
	// } catch (InstantiationException e) {
	// return null;
	// } catch (IllegalAccessException e) {
	// return null;
	// }
	// }

	public int getBoxWidth() {
		return boxWidth;
	}

	public int getBoxHeight() {
		return boxHeight;
	}

	public int getHorizontalSpacing() {
		return horizontalSpacing;
	}

	public int getVerticalSpacing() {
		return verticalSpacing;
	}

	public Color getHighlighting() {
		return highlighting;
	}

	// public Color getColor() {
	// return color;
	// }

	public boolean getVertical() {
		return alignVertical;
	}

	public boolean getDynamicWidth() {
		return dynamicWidth;
	}

	public boolean getDynamicHeight() {
		return dynamicHeight;
	}

	public void setShape(BoundedShape shape) {
		this.shape = shape;
		boxWidth = shape.getWidth();
		boxHeight = shape.getHeight();
	}

	public void setHighlighting(Color highlighting) {
		this.highlighting = highlighting;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setVertical(boolean alignVertical) {
		this.alignVertical = alignVertical;
	}

	public void setDynamicWidth(boolean dynamicWidth) {
		this.dynamicWidth = dynamicWidth;
	}

	public void setDynamicHeight(boolean dynamicHeight) {
		this.dynamicHeight = dynamicHeight;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public boolean getSolid() {
		return solid;
	}

	public int getPseudoCodeMarker() {
		return currentPseudoCodeMarker;
	}

	private void setPseudoCodeMarker(int newVal) {
		if (newVal > 0 && newVal < pseudoCode.size() + 1) {
			currentPseudoCodeMarker = newVal;
		}
	}

	public synchronized void setPseudoCodePointer(int newVal) {

		previousPseudoCodeLine = currentPseudoCodeLine;
		if (null != previousPseudoCodeLine) {
			((FlexibleShape) previousPseudoCodeLine).setColor(color);
		}

		this.setPseudoCodeMarker(newVal);

		currentPseudoCodeLine = pseudoCode.get(currentPseudoCodeMarker - 1);
		((FlexibleShape) currentPseudoCodeLine).setColor(highlighting);
	}

	public void clearPseudoCodePointers() {
		for (AttributedShape s : pseudoCode) {
			((FlexibleShape) s).setColor(color);
		}
		currentPseudoCodeMarker = 0;

	}

	public Component displayInPanel() {
		return panel;
	}

	public Container getPanel() {
		return panel;
	}

	public void setPanel(Container newVal) {
		panel = newVal;
	}

	public TreeNode getCopiedObjectTreeNode() {
		return copiedObjectTreeNode;
	}

	public void setCopiedObjectTreeNode(TreeNode copiedObjectTreeNode) {
		this.copiedObjectTreeNode = copiedObjectTreeNode;
	}

//	public TreeNode getUserObjectTreeNode() {
//		return userObjectTreeNode;
//	}
	
	public TreeNode getTempTreeNode(Object source) {
		return valueToTempTreeNode.get(source);
	}

//	public void setUserObjectTreeNode(TreeNode newVal) {
//		this.userObjectTreeNode = newVal;
//	}
	
	public void setTempTreeNode(Object source, TreeNode newVal) {
		valueToTempTreeNode.put(source, newVal);
	}


//	public TreeNode getRootNode() {
//		return rootNode;
//	}
//
//	public void setRootNode(TreeNode rootNode) {
//		this.rootNode = rootNode;
//	}

	@Override
	public void setReadPointerShapeInitialCoordinates(BoundedShape aPointerShape) {

	}

//	public ObjectToShapeTranslator<ElementType> getElementToShapeTranslator() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void setElementToShapeTranslator(
//			ObjectToShapeTranslator<ElementType> newVal) {
//		// TODO Auto-generated method stub
//
//	}
	
	public  BoundedShape initialCopiedObjectShape() {
		return null;
	}


	boolean isComposite(Object anElement) {
		return anElement instanceof ListenableVector;
	}

	TreeNodeKind toTreeNodeKind(Object anElement) {
//		if (anElement == visualizer.getRootBuffer())
		if (visualizer.getRootBuffers().contains(anElement))
			return TreeNodeKind.Root;
		else if (isComposite(anElement))
			return TreeNodeKind.Internal;
		else
			return TreeNodeKind.Leaf;

	}
	
	

	public TreeNode createTreeAndShapes(Object element) {

		// String stringValue;
		// if (element == null)
		// stringValue = "";
		// else if (element instanceof ListenableVector)
		// stringValue = ((ListenableVector) element).getName();
		// else
		// stringValue = element.toString();
		//
//		BoundedShape shape = createShape(initX, initY, -1, -1, element);
		BoundedShape shape = createShape(null, (ElementType) element, null, new Rectangle(initX, initY, -1, -1), null);
		// TreeNode retVal = new ATreeNode(stringValue, element,

		TreeNode retVal = new ATreeNode(shape, element, null, null);
		retVal.setTreeVisualizer((CollectionVisualizer) getVisualizer());
		// if (element != null) {
		// if (valueToTreeNode.get(element) != null) {
		// Tracer.error("Multiple links in tree  to object :" + element +
		// ". Undo will not work." );
		// }
		// valueToTreeNode.put(element, retVal);
		// }
		// else {
		// shape.setWidth(0);
		// shape.setHeight(0);
		// }
		if (element instanceof ListenableVector) {
			if (element != null) {
				if (valueToTreeNode.get(element) != null) {
					Tracer.error("Multiple links in tree  to object :"
							+ element + ". Undo will not work.");
				}
				System.out.println("added binding for:" + element + " code:" + System.identityHashCode(element));

				valueToTreeNode.put(element, retVal);
			}
			addChildren(retVal, (ListenableVector) element);

		}

		return retVal;

	}

	public void addChildren(TreeNode parent, ListenableVector vector) {
		for (Object element : vector) {
			TreeNode childNode = createTreeAndShapes(element);
			parent.getVector().add(childNode);
			childNode.setParent(parent);
		}
	}

	public synchronized TreeNode getTreeNode(Object aValue) {
		return valueToTreeNode.get(aValue);
	}

	public synchronized TreeNode removeTreeNode(Object aValue) {
		if (aValue instanceof ListenableVector) {
			System.out.println("removed binding for:" + aValue + " code:"
					+ System.identityHashCode(aValue));
			return valueToTreeNode.remove(aValue);
		} else
			return null;
	}

	public synchronized TreeNodeShape getTreeNodeShape(Object aValue) {
		return valueToTreeNodeShape.get(aValue);
	}
	
	public ListenableShapeVector getContainingShapes (TreeNode aTreeNode) {
		if (aTreeNode == null)
			return null;
		TreeNode rootNode = aTreeNode.getRoot();
		Object rootObject = rootNode.getObject();
		return getCompositeLayoutManager().getContainingShapes((ListenableVector<ElementType>)rootObject);
	}

	public void addTreeNodeShapesToDisplay(Object anObject) {
		TreeNode aTreeNode = valueToTreeNode.get(anObject);
		
		if (aTreeNode != null)
			addTreeNodeShapeToDisplay(getContainingShapes(aTreeNode), aTreeNode);
		if (anObject instanceof ListenableVector) {
			ListenableVector aList = (ListenableVector) anObject;
			for (Object anElement : aList)
				addTreeNodeShapesToDisplay(anElement);
		}

	}

	public void addTreeNodeShapeToDisplay(ListenableShapeVector aContainingList, Object anObject) {
		TreeNodeShape aTreeNodeShape = valueToTreeNodeShape.get(anObject);
		if (aTreeNodeShape != null)
//			shapes.getShapes().get(0).add(aTreeNodeShape);
		aContainingList.add(aTreeNodeShape);


	}

	public void addTreeNodeShapeToDisplay(ListenableShapeVector aContainingList, TreeNodeShape aTreeNodeShape) {
		// TreeNodeShape aTreeNodeShape = valueToTreeNodeShape.get(anObject);
		if (aTreeNodeShape != null)
//			shapes.getShapes().get(0).add(aTreeNodeShape);
		aContainingList.add(aTreeNodeShape);


	}

	public void removeTreeNodeShapes(ListenableShapeVector aContainingList, Object anObject) {
		TreeNode aTreeNode = valueToTreeNode.get(anObject);

		if (anObject instanceof ListenableVector) {
			ListenableVector aList = (ListenableVector) anObject;
			for (Object anElement : aList)
				removeTreeNodeShapes(aContainingList, anElement);
		}

		if (aTreeNode != null)
//			shapes.getShapes().get(0).remove(aTreeNode.getTreeNodeShape());
		aContainingList.remove(aTreeNode.getTreeNodeShape());
	}

	public void removeTreeNodeShape(ListenableShapeVector aContainingList, Object anObject) {
		TreeNodeShape aTreeNodeShape = valueToTreeNodeShape.get(anObject);

		// if (anObject instanceof ListenableVector) {
		// ListenableVector aList = (ListenableVector) anObject;
		// for (Object anElement:aList)
		// removeTreeNodeShapesDisplayed(anElement);
		// }

		if (aTreeNodeShape != null)
//			shapes.getShapes().get(0).remove(aTreeNodeShape);
		aContainingList.remove(aTreeNodeShape);
	}

	public void removeTreeNodeShapes(ListenableShapeVector aContainingShapes, TreeNode aTreeNode) {
		// TreeNode aTreeNode = valueToTreeNode.get(anObject);

		List<TreeNode> children = aTreeNode.getVector();

		for (TreeNode aChild : children) {
			removeTreeNodeShapes(aContainingShapes, aChild);
		}

//		shapes.getShapes().get(0).remove(aTreeNode.getTreeNodeShape());
		aContainingShapes.remove(aTreeNode.getTreeNodeShape());
	}

	public List<TreeNodeShape> getTreeNodeShapes(TreeNode aTreeNode) {
		// TreeNode aTreeNode = valueToTreeNode.get(anObject);
		List<TreeNodeShape> treeNodeShapes = new ArrayList();
		fillTreeNodeShapes(treeNodeShapes, aTreeNode);

		// treeNodeShapes.add(aTreeNode.getTreeNodeShape());
		//
		//
		// List<TreeNode> children = aTreeNode.getVector();
		//
		//
		//
		// for (TreeNode aChild:children) {
		// getTreeNodeShapes(aChild);
		// }
		//
		return treeNodeShapes;

	}

	public void fillTreeNodeShapes(List<TreeNodeShape> treeNodeShapes,
			TreeNode aTreeNode) {
		// TreeNode aTreeNode = valueToTreeNode.get(anObject);
		treeNodeShapes.add(aTreeNode.getTreeNodeShape());

		List<TreeNode> children = aTreeNode.getVector();

		for (TreeNode aChild : children) {
			fillTreeNodeShapes(treeNodeShapes, aChild);
		}

	}

	public void addTreeNodeShapes(ListenableShapeVector aContainingList, TreeNode aTreeNode) {
		// TreeNode aTreeNode = valueToTreeNode.get(anObject);
//		shapes.getShapes().get(0).add(aTreeNode.getTreeNodeShape());
		aContainingList.add(aTreeNode.getTreeNodeShape());


		List<TreeNode> children = aTreeNode.getVector();

		for (TreeNode aChild : children) {
			addTreeNodeShapes(aContainingList, aChild);
		}

	}

	public void addTreeNodeShapes(ListenableShapeVector aContainingList, List<TreeNodeShape> aShapesList) {
		for (TreeNodeShape aTreeNodeShape : aShapesList) {
//			shapes.getShapes().get(0).add(aTreeNodeShape);
			aContainingList.add(aTreeNodeShape);


		}
	}

	boolean showRoot = true;

	public void addTreeNodeShapeToDisplay(ListenableShapeVector aContainingList,TreeNode aNode) {
		// if (aNode instanceof ATreeRoot && showRoot {
		//
		// shapes.add(aNode.getTreeNodeShape());
		// // shapes.add(visualizerElement.getShape());
		// // this.addLine(visualizerElement.getHorizontalLine());
		// // this.addLine(visualizerElement.getVerticalLine());
		// } else if (!(aNode instanceof ATreeRoot)) {
//		shapes.getShapes().get(0).add(aNode.getTreeNodeShape());
		aContainingList.add(aNode.getTreeNodeShape());


		// // shapes.add(visualizerElement.getShape());
		// // this.addLine(visualizerElement.getHorizontalLine());
		// // this.addLine(visualizerElement.getVerticalLine());
		// }
	}

	public ObjectToShapeTranslator<ListenableVector> getRootNodeToContentShapeTranslator() {
		return treeNodeKindToContentShapeTranslator.get(TreeNodeKind.Root);
	}

	public void setRootNodeToContentShapeTranslator(
			ObjectToShapeTranslator<ListenableVector> rootNodeToShapeTranslator) {
		treeNodeKindToContentShapeTranslator.put(TreeNodeKind.Root,
				rootNodeToShapeTranslator);

	}

	public ObjectToShapeTranslator<ListenableVector> getInternalNodeToContentShapeTranslator() {
		return treeNodeKindToContentShapeTranslator.get(TreeNodeKind.Internal);

	}

	public void setInternalNodeToContentShapeTranslator(
			ObjectToShapeTranslator<ListenableVector> internalNodeToShapeTranslator) {
		treeNodeKindToContentShapeTranslator.put(TreeNodeKind.Internal,
				internalNodeToShapeTranslator);
	}

	public ObjectToShapeTranslator<ElementType> getLeafNodeToContentShapeTranslator() {
		return treeNodeKindToContentShapeTranslator.get(TreeNodeKind.Leaf);
	}

	public void setLeafNodeToContentShapeTranslator(
			ObjectToShapeTranslator<ElementType> leafNodeToShapeTranslator) {
		treeNodeKindToContentShapeTranslator.put(TreeNodeKind.Leaf,
				leafNodeToShapeTranslator);

	}
	
//	boolean isUserObjectTreeNode (TreeNode aTreeNode) {
//		r
//	}
	public void retarget(TreeNode toTreeNode, TreeNode fromTreeNode) {
		Object newValue = fromTreeNode.getObject();

		if (toTreeNode.getObject() == newValue)
			return;
		
		toTreeNode.setObject(newValue);
		TreeNodeKind toTreeNodeKind = toTreeNodeKind(newValue);
		if (toTreeNodeKind == TreeNodeKind.Leaf
				|| toTreeNode == copiedObjectTreeNode
//				|| toTreeNode == userObjectTreeNode)
			|| valueToTempTreeNode.values().contains(toTreeNode))
			return;
		valueToTreeNode.put(newValue, toTreeNode);

	}

	public void copy(TreeNode toTreeNode, TreeNode fromTreeNode) {
		TreeNodeKind toTreeNodeKind = toTreeNodeKind(toTreeNode.getObject());
		TreeNodeKind fromTreeNodeKind = toTreeNodeKind(fromTreeNode.getObject());
		if (toTreeNode.getTreeNodeShape() == null
				|| toTreeNodeKind != fromTreeNodeKind) {
//			BoundedShape toMainShape = createShape(0, 0, 0, 0,
//					fromTreeNode.getObject());
			BoundedShape toMainShape = createShape(null, (ElementType)  fromTreeNode.getObject(), null, new Rectangle(0, 0, 0, 0), null);
//			toTreeNode.getTreeNodeShape().setDisposed(true);
			toTreeNode.setTreeNodeShape(new ATreeNodeShape(toMainShape,
					toTreeNode));
		}

		retarget(toTreeNode, fromTreeNode);

		
		ListenableVector<TreeNode> vector = toTreeNode.getVector();
		List<TreeNode> children = fromTreeNode.getVector();

		int sizeDifference = toTreeNode.getVector().size() - children.size();
		if (sizeDifference > 0) {
			for (int i = 0; i < sizeDifference; i++) {
				vector.remove(vector.size() - 1);
			}
		} else {
			for (int i = 0; i < -sizeDifference; i++) {

				TreeNode childNode = new ATreeNode(null, null, null, null);
				childNode.setTreeVisualizer((CollectionVisualizer) getVisualizer());
				vector.add(childNode);
				childNode.setParent(toTreeNode);
			}
		}
		for (int i = 0; i < children.size(); i++) {

			copy(vector.get(i), children.get(i));

		}
		toTreeNode.getTreeNodeShape().setDisposed(false); // we disposed it so set it to false

		toTreeNode.getTreeNodeShape().copy(fromTreeNode.getTreeNodeShape());
		toTreeNode.recomputeDependentsInSubTree();

	}
	public ListenableVector<ElementType> createBuffer() {
		return (ListenableVector<ElementType>) new ATreeBuffer (visualizer);

	}

	public Point normalizedToRealLocation(
			ListenableShapeVector aContainingShapes, BoundedShape aShape,
			Point aNormalizedLocation) {
		// TODO Auto-generated method stub
		return aNormalizedLocation;
	}
}
