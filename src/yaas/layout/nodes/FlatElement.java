package yaas.layout.nodes;

import shapes.BoundedShape;

public interface FlatElement extends BoundedShape{
	public void recomputeDependents() ;
	public void setLabelShape(BoundedShape newVal);
	public BoundedShape getContentShape();
	public BoundedShape getLabelShape();
	public void updateLabelShape(BoundedShape newLabelShape);




}
