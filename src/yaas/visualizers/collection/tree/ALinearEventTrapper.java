package yaas.visualizers.collection.tree;

import java.util.Collection;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import shapes.LabelShape;
import shapes.TextShape;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.Visualization;
import yaas.animators.AnimationUtil;
import yaas.layout.nodes.LinearElement;
import yaas.trappers.*;



public class ALinearEventTrapper<ElementType> extends
		AnAbstractLinearEventTrapper<ElementType>
		implements
		EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> {

	private static final long serialVersionUID = 8683688773211768379L;

	protected LinearElement root;
	protected int boxWidth;
	protected int boxHeight;
	protected boolean dynamicWidth;
	protected boolean dynamicHeight;
	protected yaas.visualizers.collection.tree.LinearVisualizer<ElementType> visualizer;
	protected Visualization shapes;

	public ALinearEventTrapper(
			yaas.visualizers.collection.tree.LinearVisualizer<ElementType> visualizer) {
		this.visualizer = visualizer;
		root = visualizer.getRoot();
//
//		boxWidth = ((ALinearLayoutManager<ElementType>) visualizer
//				.getLayoutManager()).getBoxWidth();
//		boxHeight = ((ALinearLayoutManager<ElementType>) visualizer
//				.getLayoutManager()).getBoxHeight();
//		dynamicWidth = ((ALinearLayoutManager<ElementType>) visualizer
//				.getLayoutManager()).getDynamicWidth();
//		dynamicHeight = ((ALinearLayoutManager<ElementType>) visualizer
//				.getLayoutManager()).getDynamicHeight();
		shapes = visualizer.shapes();

	}

	public synchronized void elementAdded(Object source, ElementType element,
			int newSize) {

		elementInserted(source, element, newSize - 1, newSize);
	}

	public synchronized void elementChanged(Object source, ElementType element,
			int pos) {

		LinearElement node = root.getVector().get(pos);
		AnimationUtil.move(node, node.getX() + boxWidth / 3, node.getY(), true,
				((ALinearLayoutManager<ElementType>) visualizer
						.getLayoutManagerOfBuffer((ListenableVector<ElementType>) source)).

						getHighlighting(),
				((FlexibleShape) visualizer.
						getLayoutManagerOfBuffer((ListenableVector<ElementType>) source)).

						getColor());
		node.setObject(element);

		BoundedShape shape = node.getShape();

		try {
			double shapeStretchFactor = Double.parseDouble(element.toString());
			shape.setWidth((int) (boxWidth * (dynamicWidth ? shapeStretchFactor
					: 1)));
			shape.setHeight((int) (boxHeight * (dynamicHeight ? shapeStretchFactor
					: 1)));
		} catch (Exception e) {
			shape.setWidth((int) (boxWidth));
			shape.setHeight((int) (boxHeight));

		}

		if (shape instanceof TextShape)
			((TextShape) shape).setText(element.toString());
		if (shape instanceof LabelShape)
			((LabelShape) shape).setText(element.toString());

		AnimationUtil.move(node, node.getX() - boxWidth / 3, node.getY(), true,
				((ALinearLayoutManager<ElementType>) visualizer
						.getLayoutManagerOfBuffer((ListenableVector<ElementType>) source)).

						getHighlighting(),
				((FlexibleShape) visualizer.
						getLayoutManagerOfBuffer((ListenableVector<ElementType>) source)).

						getColor());
	}

	public synchronized void elementCopied(Object source, int fromIndex,
			int toIndex, int newSize) {
		// TODO Auto-generated method stub

	}

	public synchronized void elementCopied(Object source, int fromIndex,
			int fromNewSize, Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public synchronized void elementInserted(Object source,
			ElementType element, int pos, int newSize) {

		LinearElement parent = root;
		LinearElement previousChild = pos - 1 >= 0 ? root.getVector().get(
				pos - 1) : null;

		LinearElement newElement = visualizer.initElement(element, parent,
				previousChild);

		if (pos + 1 < newSize) // if there is a child after us
			// use pos not pos + 1 because the element has not been
			// added to the vector yet, so pos + 1 in the user's
			// vector is pos in the roots vector.
			root.getVector().get(pos).setPreviousChild(newElement);

		parent.getVector().insertElementAt(newElement, pos);
		root.focusPosition();
	}

	public synchronized void elementMoved(Object source, int fromIndex,
			int toIndex) {
		// TODO Auto-generated method stub

	}

	public synchronized void elementMoved(Object source, int fromIndex,
			int fromNewSize, Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public synchronized void elementRemoved(Object source, int pos, int newSize) {

		LinearElement toBeRemoved = root.getVector().get(pos);
		shapes.getShapes().remove(toBeRemoved.getShape());
		visualizer.removeLine(toBeRemoved.getVerticalLine());
		visualizer.removeLine(toBeRemoved.getHorizontalLine());

		LinearElement parent = root;
		LinearElement previousChild = toBeRemoved.getPreviousChild();

		if (pos + 1 <= newSize)// If it is not the last element
			root.getVector().get(pos + 1).setPreviousChild(previousChild);

		parent.getVector().remove(toBeRemoved);

		root.focusPosition();
	}

	public synchronized void elementRemoved(Object source, ElementType element,
			int newSize, int pos) {
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
		// TODO Auto-generated method stub

	}

	/*
	 * This method takes two elements and exchanges their parents and previous
	 * elements. It then makes sure spacing is correct by calling
	 * focusPositionAndLines
	 */
	public synchronized void elementSwapped(Object newParam, int index1,
			int index2) {

		if (index1 < index2) {
			swapElements(newParam, index1, index2);
		} else {
			swapElements(newParam, index2, index1);
		}

	}

	/*
	 * This method takes two elements and exchanges their parents and previous
	 * elements. It then makes sure spacing is correct by calling
	 * focusPositionAndLines
	 * 
	 * This method assumes index1 < index2
	 */
	private synchronized void swapElements(Object newParam, int index1,
			int index2) {

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

	
}
