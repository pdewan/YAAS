package yaas.shapemappers;

import shapes.BoundedShape;

public interface LabelMapper {
	public BoundedShape getLabelShape (Object aParent, Object aChild);
	public BoundedShape getLabelShape (Object aParent, int anIndex);

}
