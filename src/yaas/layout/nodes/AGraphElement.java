package yaas.layout.nodes;

import java.util.HashMap;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.models.AListenableVector;
import util.models.ListenableVector;
import bus.uigen.shapes.AnArcModel;

public class AGraphElement extends AVisualizerElementSkeleton<GraphElement>
		implements GraphElement {

	private HashMap<GraphElement, AnArcModel> graphPaths = new HashMap<GraphElement, AnArcModel>();
	private ListenableVector<GraphElement> adjacentNodes = new AListenableVector<GraphElement>();

	public AGraphElement(FlexibleShape s, Object o) {
		super(s, o);
	}

	@Override
	public void focusPosition(GraphElement node) {
		ListenableVector<GraphElement> vector = getVector();
		GraphElement previousElement = null;

		for (GraphElement element : vector) {
			if (previousElement != null) {
				element.setX(previousElement.getX() + elementShapeXOffset);
				element.setY(previousElement.getY() + elementShapeYOffset);
			}

			previousElement = element;
		}
		// we use a separate loop for this because we need all the shape's
		// positions to be set before we set the arc positions
		for (GraphElement element : vector) {
			positionLines(element);
		}
	}

	protected void positionLines(GraphElement element) {

		for (GraphElement adjacentElement : element.getLines().keySet()) {
			AnArcModel arc = element.getLines().get(adjacentElement);
			
			arc.setWidth(Math.abs((adjacentElement.getX() - element.getX()) * 2));
			arc.setHeight(Math.abs((adjacentElement.getY() - element.getY()) * 2));

			
			if (element.getX() < adjacentElement.getX()) { //forward path
				arc.setX(element.getX() + element.getWidth()/2 - arc.getWidth() / 2);
				arc.setY(element.getY() + element.getHeight() / 2);
				arc.setStartAngle(0);
				arc.setEndAngle(90);
			} else {//backward path
				arc.setX(element.getX() + element.getWidth()/2 - arc.getWidth() / 2);
				arc.setY(element.getY() + element.getHeight()/2 - arc.getHeight());
				arc.setStartAngle(180);
				arc.setEndAngle(90);
			}
			System.out.println(arc);
		}
	}

	@Override
	public void focusPosition() {
		focusPosition(this.getVector().get(0));

	}

	public HashMap<GraphElement, AnArcModel> getLines() {
		return graphPaths;
	}

	@Override
	public void setParent(GraphElement p) {

	}

	@Override
	public void setPreviousChild(GraphElement p) {

	}

	public ListenableVector<GraphElement> getAdjacencyList() {
		return adjacentNodes;
	}

	public void setFontSize(int newSize) {
		// TODO Auto-generated method stub
		
	}

	public int getFontSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public GraphElement getNextChild(int aMyIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean copy(BoundedShape aReference) {
		return false;
	}

	public boolean copyable(BoundedShape aReference) {
		// TODO Auto-generated method stub
		return false;
	}
}
