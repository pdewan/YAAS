package yaas.layout.nodes;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import yaas.animators.AnimationUtil;
import yaas.common.*;

import bus.uigen.shapes.ALineModel;

public abstract class AnAbstractLinearElement extends
		AVisualizerElementSkeleton<LinearElement> implements LinearElement {

	public AnAbstractLinearElement(BoundedShape shape, Object _o, ALineModel _l,
			ALineModel _l2) {

		super(shape, _o);
		verticalLine = _l;
		horizontalLine = _l2;
	}

	/**************
	 * Position Methods // * @throws NullParentException
	 **************************************/
	public void focusPosition(LinearElement parent) {

		for (LinearElement node : parent.getVector()) {

			LinearElement previousChild = node.getPreviousChild();

			if (null == previousChild) {
				previousChild = parent; // only for spacing; we do not set
				// previousChild to be the parent
			} else if (!previousChild.getVector().isEmpty()) {
				previousChild = previousChild.getVector().get(
						previousChild.getVector().size() - 1);
			}

			if (!isVertical())// node is offset from parent
			// we do not add parent.width because the x-coordinates of shapes
			// can overlap as long as the y-coordinates do not
			{
				AnimationUtil
						.move(
								node,
								(parent.getX() + elementShapeXOffset),
								(int) (previousChild.getY()
										+ previousChild.getHeight() + elementShapeYOffset),
								animate, super.getHighlighting(), super
										.getColor());
			} else {// vertical
				AnimationUtil.move(node, (previousChild.getX()
						+ previousChild.getWidth() + elementShapeXOffset),
						(int) (parent.getY() + elementShapeYOffset),
						animate, super.getHighlighting(), super.getColor());
			}

			// if the node has children position them before other nodes
			if (node.getVector().size() > 0) {

				focusPosition(node);
				positionVerticalLine(node);
			}
			// if the node is not a root it is connected to a parent
			if (!(node instanceof ALinearRoot))
				positionHorizontalLine(node, horizontalLineYOffset);
		}
	}

	protected void positionVerticalLine(LinearElement node) {

		// mask instance variable 'verticalLine'
		ALineModel verticalLine = node.getVerticalLine();

		AnimationUtil.move(verticalLine, node.getX() + verticalLineXOffset,
				node.getY() + node.getHeight(), animate, super
						.getHighlighting(), super.getColor());

		verticalLine.setWidth(0);

		LinearElement lastChild = node.getVector().get(
				node.getVector().size() - 1);
		verticalLine.setHeight(lastChild.getY() - node.getY()
				- lastChild.getHeight() / 2);
	}

	protected void positionHorizontalLine(LinearElement node,
			double horizontalLineYOffset) {

		// mask instance variable 'horizontalLine'
		ALineModel horizontalLine = node.getHorizontalLine();

		AnimationUtil.move(horizontalLine, node.getParent().getX()
				+ node.getParent().getWidth() / 2,
				(int) (node.getY() + horizontalLineYOffset), animate, super
						.getHighlighting(), super.getColor());

		horizontalLine.setHeight(0);
		horizontalLine.setWidth(node.getX() - node.getParent().getX()
				- node.getParent().getWidth() / 2);
	}
	
	public void copy(Object aReference) {
		
	}
}
