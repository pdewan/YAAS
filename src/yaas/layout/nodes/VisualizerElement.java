package yaas.layout.nodes;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.annotations.Visible;
import util.models.ListenableVector;

public interface VisualizerElement<ElementType> extends  BoundedShape, LinkedTree<ElementType>
{	
	public void focusPosition(ElementType node);
	public void focusPosition();
	
	public FlexibleShape getShape();
	public ListenableVector<ElementType> getVector();
	
	public Object getObject();
	public void setObject(Object o);
	public void setVertical(boolean b);
}