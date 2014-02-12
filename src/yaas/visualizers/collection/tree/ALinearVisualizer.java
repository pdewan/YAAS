package yaas.visualizers.collection.tree;

import java.util.Collection;
import java.util.List;




import shapes.BoundedShape;
import shapes.FlexibleShape;
import shapes.LabelShape;
import shapes.TextShape;
import util.models.AListenableVector;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.AVisualizationtBasedVisualizer;
import yaas.AVisualizer;
import yaas.Visualization;
import yaas.animators.AnimationUtil;
import yaas.buffers.vector.ALinearBuffer;
import yaas.controller.ButtonPressTrapper;
import yaas.layout.LayoutManager;
import yaas.layout.nodes.ALinearElement;
import yaas.layout.nodes.ALinearRoot;
import yaas.layout.nodes.AVisualizerElementSkeleton;
import yaas.layout.nodes.LinearElement;
import bus.uigen.shapes.ALabelModel;
import bus.uigen.shapes.ALineModel;

@SuppressWarnings("rawtypes")
public class ALinearVisualizer<ElementType>
		extends
		AVisualizationtBasedVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>>
		implements LinearVisualizer<ElementType> {

	private static final long serialVersionUID = 2125646954227767702L;
	private ListenableVector<ALineModel> LINE_VEC = new AListenableVector<ALineModel>();
//	private ListenableVector<SimpleShape> shapes = new AListenableVector();
	private LinearElement root;
	private boolean showLines = true;
	private boolean showRoot = true;
	private ALabelModel label;

	private int initX = 20, initY = 20;

	/**************** Initialization *******************************/
	public ALinearVisualizer(BoundedShape shape) {
		super();
		this.getVisualization().getShapes().get(0).addAll(LINE_VEC);
		animationPauseTime = 10;

//		layoutManager = new ALinearLayoutManager<ElementType>(this);
//		((ALinearLayoutManager<ElementType>) layoutManager).setShape(shape);
	}
	
	public void temp(){
		System.out.println("SUCCESSFUL METHOD BINDING");
	}

	/********************** Graphics Methods *******************************************/

	private void initElements(LinearElement head,
			ListenableVector<ElementType> vector) {
		for (int i = 0; i < vector.size(); i++) {

			Object element = vector.get(i);
			LinearElement node;
			if (i > 0) { // If this is not the first node in the list
				LinearElement previousChild = head.getVector().get(i - 1);
				node = initElement(element, head, previousChild);
			} else {
				node = initElement(element, head, null);
			}
			((AVisualizerElementSkeleton) node).setAnimate(true);
			((FlexibleShape) node).setColor(this.getLayoutManager().getColor());
			((AVisualizerElementSkeleton) node).setHighlighting(this.getLayoutManager().getHighlighting());
			head.getVector().add(node);
		}
	}

	public ALinearRoot initRoot(Object element, LinearElement parent,
			LinearElement previousChild) {

		String stringValue = element.toString();
		BoundedShape shape = ((ALinearLayoutManager<ElementType>) firstLayoutManager)
				.createShape(stringValue, initX, initY);
		ALineModel horizontalLine = new ALineModel(0, 0, 0, 0);
		ALineModel verticalLine = new ALineModel(0, 0, 0, 0);

		ALinearRoot visualizerElement = new ALinearRoot(shape, stringValue,
				verticalLine, horizontalLine);

		postInitElement(element, parent, previousChild, visualizerElement);
		visualizerElement.setAnimate(true);
		visualizerElement.setColor(this.getLayoutManager().getColor());
		visualizerElement.setHighlighting(this.getLayoutManager().getHighlighting());
		return visualizerElement;
	}
	// will new method to make lines appear
    @Override
    public synchronized void visualize(ListenableVector<ElementType> data, LayoutManager aLayoutManager)  {
          super.visualize(data, aLayoutManager);
          this.getVisualization().getShapes().get(0).addAll(LINE_VEC);
    }

	@SuppressWarnings("unchecked")
	private void postInitElement(Object element, LinearElement parent,
			LinearElement previousChild, LinearElement visualizerElement) {

		if (!((ALinearLayoutManager<ElementType>) firstLayoutManager).getVertical()) {// horizontal
			visualizerElement.setVertical(false);
			visualizerElement
					.setElementShapeXOffset(((ALinearLayoutManager<ElementType>) firstLayoutManager)
							.getBoxWidth());
//							.getBoxHeight());
			visualizerElement
					.setElementShapeYOffset(((ALinearLayoutManager<ElementType>) firstLayoutManager)
							.getBoxHeight() / 2);
			visualizerElement
					.setHorizontalLineYOffset(((ALinearLayoutManager<ElementType>) firstLayoutManager)
							.getBoxHeight() / 2);
			visualizerElement
					.setVerticalLineXOffset(((ALinearLayoutManager<ElementType>) firstLayoutManager)
							.getBoxWidth() / 2);
		} else {
			// vertical
			visualizerElement.setVertical(true);
			visualizerElement
					.setElementShapeXOffset(((ALinearLayoutManager<ElementType>) firstLayoutManager)
							.getBoxWidth());
			visualizerElement
					.setElementShapeYOffset(((ALinearLayoutManager<ElementType>) firstLayoutManager)
							.getBoxHeight() / 2);
			visualizerElement
					.setHorizontalLineYOffset(((ALinearLayoutManager<ElementType>) firstLayoutManager)
							.getBoxHeight() / 2);
			visualizerElement
					.setVerticalLineXOffset(((ALinearLayoutManager<ElementType>) firstLayoutManager)
							.getBoxWidth() / 2);
		}

		visualizerElement.setParent(parent);
		visualizerElement.setPreviousChild(previousChild);

		if (element instanceof ListenableVector) {
			initElements(visualizerElement, (ListenableVector) element);
		}

		if (visualizerElement instanceof ALinearRoot && showRoot) {

			((Visualization) shapes).getShapes().get(0).add(visualizerElement.getShape());
			this.addLine(visualizerElement.getHorizontalLine());
			this.addLine(visualizerElement.getVerticalLine());
		} else if (!(visualizerElement instanceof ALinearRoot)) {

			((Visualization) shapes).getShapes().get(0).add(visualizerElement.getShape());
			this.addLine(visualizerElement.getHorizontalLine());
			this.addLine(visualizerElement.getVerticalLine());
		}
	}

	public LinearElement initElement(Object element, LinearElement parent,
			LinearElement previousChild) {

		String stringValue = element.toString();
		BoundedShape shape = ((ALinearLayoutManager<ElementType>) firstLayoutManager)
				.createShape(stringValue, initX, initY);
		ALineModel horizontalLine = new ALineModel(0, 0, 0, 0);
		ALineModel verticalLine = new ALineModel(0, 0, 0, 0);

		LinearElement visualizerElement = new ALinearElement(shape,
				stringValue, verticalLine, horizontalLine);

		postInitElement(element, parent, previousChild, visualizerElement);

		return visualizerElement;
	}

	public void magnify(double mag) {
		// TODO Auto-generated method stub

	}

	public int theStepCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void elementAdded(Object source, Object element, int newSize) {

		elementInserted(source, element, newSize - 1, newSize);
	}

	public void elementChanged(Object source, Object element, int pos) {

		LinearElement node = root.getVector().get(pos);
		AnimationUtil.move(
				node,
				node.getX()
						+ ((ALinearLayoutManager<ElementType>) firstLayoutManager)
								.getBoxWidth() / 3, node.getY(), true,
				((ALinearLayoutManager<ElementType>) firstLayoutManager)
						.getHighlighting(),
				((ALinearLayoutManager<ElementType>) firstLayoutManager).getColor());
		node.setObject(element);

		BoundedShape shape = node.getShape();

		try {
			double shapeStretchFactor = Double.parseDouble(element.toString());
			shape.setWidth((int) (((ALinearLayoutManager<ElementType>) firstLayoutManager)
					.getBoxWidth() * (((ALinearLayoutManager<ElementType>) firstLayoutManager)
					.getDynamicWidth() ? shapeStretchFactor : 1)));
			shape.setHeight((int) (((ALinearLayoutManager<ElementType>) firstLayoutManager)
					.getBoxHeight() * (((ALinearLayoutManager<ElementType>) firstLayoutManager)
					.getDynamicHeight() ? shapeStretchFactor : 1)));
		} catch (Exception e) {
			shape.setWidth(((ALinearLayoutManager<ElementType>) firstLayoutManager)
					.getBoxWidth());
			shape.setHeight(((ALinearLayoutManager<ElementType>) firstLayoutManager)
					.getBoxHeight());

		}

		if (shape instanceof TextShape)
			((TextShape) shape).setText(element.toString());
		if (shape instanceof LabelShape)
			((LabelShape) shape).setText(element.toString());

		AnimationUtil.move(
				node,
				node.getX()
						- ((ALinearLayoutManager<ElementType>) firstLayoutManager)
								.getBoxWidth() / 3, node.getY(), true,
				((ALinearLayoutManager<ElementType>) firstLayoutManager)
						.getHighlighting(),
				((ALinearLayoutManager<ElementType>) firstLayoutManager).getColor());
	}

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementInserted(Object source, Object element, int pos,
			int newSize) {

		LinearElement parent = root;
		LinearElement previousChild = pos - 1 >= 0 ? root.getVector().get(
				pos - 1) : null;

		LinearElement newElement = initElement(element, parent, previousChild);

		if (pos + 1 < newSize) // if there is a child after us
			// use pos not pos + 1 because the element has not been
			// added to the vector yet, so pos + 1 in the user's
			// vector is pos in the roots vector.
			root.getVector().get(pos).setPreviousChild(newElement);

		parent.getVector().insertElementAt(newElement, pos);
		root.focusPosition();
	}

	public void elementMoved(Object source, int fromIndex, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementRemoved(Object source, int pos, int newSize) {

		LinearElement toBeRemoved = root.getVector().get(pos);
		((Visualization) shapes).getShapes().get(0).remove(toBeRemoved.getShape());
		this.removeLine(toBeRemoved.getVerticalLine());
		this.removeLine(toBeRemoved.getHorizontalLine());

		LinearElement parent = root;
		LinearElement previousChild = toBeRemoved.getPreviousChild();

		if (pos + 1 <= newSize)// If it is not the last element
			root.getVector().get(pos + 1).setPreviousChild(previousChild);

		parent.getVector().remove(toBeRemoved);

		root.focusPosition();
	}

	public void elementRemoved(Object source, Object element, int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementReplaced(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementReplaced(Object source, int fromIndex, int newFromSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementsAdded(Object source, Collection element, int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementsCleared(Object source) {
		// TODO Auto-generated method stub

	}

	public void collectionRemoved(int collectionNum) {
		// TODO Auto-generated method stub

	}

	public void elementSwapped(Object source, int index1, Object other,
			int index2) {
		// TODO Auto-generated method stub

	}

	/*
	 * This method takes two elements and exchanges their parents and previous
	 * elements. It then makes sure spacing is correct by calling
	 * focusPositionAndLines
	 */
	public void elementSwapped(Object newParam, int index1, int index2) {
		if (index1 < index2)
			swapElements(newParam, index1, index2);
		else
			swapElements(newParam, index2, index1);

	}

	/*
	 * This method takes two elements and exchanges their parents and previous
	 * elements. It then makes sure spacing is correct by calling
	 * focusPositionAndLines
	 * 
	 * This method assumes index1 < index2
	 */
	private void swapElements(Object newParam, int index1, int index2) {

		// the two elements to be swapped
		LinearElement firstElement = root.getVector().get(index1);
		LinearElement secondElement = root.getVector().get(index2);

		// the two elements after each of the elements to be swapped
		LinearElement secondElementNext;
		LinearElement firstElementNext;
		/*
		 * There is a corner case where secondElement.previousChild is
		 * firstElement
		 */

		// the first element's previous child before it is reassigned
		LinearElement tempPrevChild = firstElement.getPreviousChild();

		if (index2 != index1 + 1) {// the elements are not adjacent

			// set previous elements for the two nodes
			firstElement.setPreviousChild(secondElement.getPreviousChild());
			secondElement.setPreviousChild(tempPrevChild);

			// set previous elements for the nodes referencing the swapped nodes
			if (index1 + 1 < root.getVector().size()) {
				firstElementNext = root.getVector().get(index1 + 1);
				firstElementNext.setPreviousChild(secondElement);
			}
			if (index2 + 1 < root.getVector().size()) {
				secondElementNext = root.getVector().get(index2 + 1);
				secondElementNext.setPreviousChild(firstElement);
			}

		} else { // the elements are adjacent
			firstElement.setPreviousChild(secondElement);
			secondElement.setPreviousChild(tempPrevChild);

			// set previous elements for the nodes referencing the second node
			if (index2 + 1 < root.getVector().size()) {
				secondElementNext = root.getVector().get(index2 + 1);
				secondElementNext.setPreviousChild(firstElement);
			}
		}

		// swap the elements in root
		root.getVector().swap(index1, index2);

		root.focusPosition();
	}

	private void addLine(ALineModel l) {
		if (showLines) {
			LINE_VEC.add(l);
		}
	}

	public void removeLine(ALineModel l) {
		if (showLines) {
			LINE_VEC.remove(l);
		}
	}

	public void showLines(boolean b) {
		showLines = b;
	}

	public void showRoot(boolean b) {
		showRoot = b;
	}

	public void setLabel(ALabelModel l) {
		label = l;
	}

	@util.annotations.Visible(false)
	public ALabelModel getLabel() {
		return label;
	}

	public Visualization shapes() {
		return (Visualization) shapes;
	}

	@util.annotations.Visible(false)
	public LinearElement getRoot() {
		return root;
	}

	public void setRoot(LinearElement newVal) {
		root = newVal;
	}

	@util.annotations.Visible(false)
	public ALinearLayoutManager<ElementType> getLayoutManager() {
		return (ALinearLayoutManager<ElementType>) firstLayoutManager;
	}

	/************************* Visualizer Methods ***********************************/
	protected ListenableVector<ElementType> createBuffer() {
		// TODO Auto-generated method stub
		return new ALinearBuffer<ElementType>(this);
	}
	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new ALinearButtonPressTrapper(controller, this);
	}

	public void addReplayMethodListener(
			VectorMethodsListener<ElementType> anObservable, VectorMethodsListener<ElementType> anObserver) {
		getRootBuffer().addVectorMethodsListener(anObserver);
		
	}
//
//	public boolean removeElement(SimpleShape c) {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	@Override
//	public void addObserver(ListenableVector<ElementType> anObservable,
//			VectorMethodsListener<ElementType> anObserver) {
//		// TODO Auto-generated method stub
//		
//	}
	public void addObserver(ListenableVector<ElementType> anObservable,
			VectorMethodsListener<ElementType> anObserver) {
		anObservable.addVectorMethodsListener(anObserver);
	}
}
