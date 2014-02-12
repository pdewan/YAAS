package yaas.visualization.code;

import bus.uigen.shapes.ALineModel;
import shapes.AttributedShape;
import shapes.AttributedTextShape;
import shapes.BoundedShape;
import shapes.FlexibleTextShape;
import util.models.AListenableVector;
import util.models.ListenableVector;

public class AMethodCodeVisualization extends AListenableVector<AttributedTextShape> implements MethodCodeVisualization {
	BoundedShape marker;

	public BoundedShape getMarker() {
		return marker;
	}

	public void setMarker(BoundedShape marker) {
		this.marker = marker;
	}
	


}
