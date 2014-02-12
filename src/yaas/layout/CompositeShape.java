package yaas.layout;

import java.util.List;

import shapes.FlexibleShape;


public interface CompositeShape extends FlexibleShape{

	public List<FlexibleShape> getShapes();
	public void setShapes(List<FlexibleShape> _shapes);

}
