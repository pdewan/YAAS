package yaas.visualization.code;

import shapes.AttributedShape;
import shapes.AttributedTextShape;
import shapes.BoundedShape;
import shapes.FlexibleTextShape;
import util.models.ListenableVector;

public interface MethodCodeVisualization extends ListenableVector<AttributedTextShape> {
	public BoundedShape getMarker();

	public void setMarker(BoundedShape marker) ;

}
