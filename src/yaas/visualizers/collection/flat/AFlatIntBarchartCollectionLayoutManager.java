package yaas.visualizers.collection.flat;

import java.awt.Point;

import shapes.BoundedShape;
import bus.uigen.shapes.ListenableShapeVector;
import yaas.shapemappers.IntToVerticalBar;
import yaas.visualizers.collection.CollectionVisualizer;

public class AFlatIntBarchartCollectionLayoutManager  extends AFlatCollectionLayoutManager<Integer>{
	public static final int DEFAULT_HEIGHT= 100;
	public static final int DEFAULT_X_SPACING= 5;


	public AFlatIntBarchartCollectionLayoutManager(CollectionVisualizer visualizer) {
		super(visualizer);
		preferredHeight = DEFAULT_HEIGHT;
		xSpacing = DEFAULT_X_SPACING;
		setElementToShapeTranslator(new IntToVerticalBar());

	}
//	protected int  getShapeY(int aParentY) {
//		return aParentY + preferredHeight;
//		
////		int parentY = containingShapes.getY() + preferredHeight;
//
//	}
	public  Point normalizedToRealLocation (ListenableShapeVector aContainingShapes, BoundedShape aShape, Point aNormalizedLocation) {
		return new Point (aNormalizedLocation.x, 
				aContainingShapes.getY() + preferredHeight - aShape.getHeight());
	}
//	public Point getLabelShapeLocation (BoundedShape aContentShape, BoundedShape aLabelShape) {
//		return new Point (aContentShape.getX() + LABEL_X_OFFSET, aContentShape.getY() + aContentShape.getHeight() +LABEL_Y_OFFSET);
//	}
	
	public Point getLabelShapeLocation (BoundedShape aContentShape, BoundedShape aLabelShape) {
		return new Point (aContentShape.getX() + aContentShape.getWidth()/2 - aLabelShape.getWidth()/2, aContentShape.getY() + + aContentShape.getHeight() + LABEL_Y_OFFSET);
	}

}
