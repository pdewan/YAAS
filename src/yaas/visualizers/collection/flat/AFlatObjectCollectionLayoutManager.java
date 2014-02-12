package yaas.visualizers.collection.flat;

import java.awt.Point;

import shapes.BoundedShape;
import bus.uigen.shapes.ListenableShapeVector;
import yaas.shapemappers.IntToVerticalBar;
import yaas.shapemappers.AnObjectToStringInARectangle;
import yaas.shapemappers.AnObjectToStringInAShape;

public class AFlatObjectCollectionLayoutManager  extends AFlatCollectionLayoutManager<Integer>{

	public AFlatObjectCollectionLayoutManager(AFlatCollectionVisualizer visualizer) {
		super(visualizer);
		setElementToShapeTranslator(new AnObjectToStringInARectangle());

	}
//	public  Point normalizedToRealLocation (ListenableShapeVector aContainingShapes, BoundedShape aShape, Point aNormalizedLocation) {
//		return new Point (aNormalizedLocation.x, 
//				aContainingShapes.getY() + baseLineY - aShape.getHeight());
//	}
//	public Point getLabelShapeLocation (BoundedShape aContentShape, BoundedShape aLabelShape) {
//		return new Point (aContentShape.getX() + LABEL_X_OFFSET, aContentShape.getY() + aContentShape.getHeight() +LABEL_Y_OFFSET);
//	}

}
