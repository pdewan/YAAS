package yaas.layout;

import bus.uigen.shapes.ListenableShapeVector;
import shapes.BoundedShape;
import yaas.Visualization;
import yaas.VisualizationBasedVisualizer;
import yaas.shapemappers.ACirclePointerShapeManager;
import yaas.shapemappers.AStringInAShapePointerShapeManager;
import yaas.shapemappers.AnObjectToStringInACutout;
import yaas.shapemappers.PointerShapeCreator;

public abstract class ACompositeLayoutManager<UserDataType> {
	public static final int DEFAULT_STATUS_X = 5;
	public static final int DEFAULT_STATUS_Y = 5;
	protected int statusX = DEFAULT_STATUS_X;
	protected int statusY = DEFAULT_STATUS_Y;
	VisualizationBasedVisualizer visualizer;
	PointerShapeCreator readPointerShapeCreator; // = new ACirclePointerShapeManager();
	PointerShapeCreator annotationPointerShapeCreator; // = new ACirclePointerShapeManager();

	public ACompositeLayoutManager(VisualizationBasedVisualizer aVisualizer) {
		visualizer = aVisualizer;
		Visualization visualization = visualizer.getVisualization();
		BoundedShape statusShape = visualization.getStatusShape();
		statusShape.setX(statusX);
		statusShape.setY(statusY);
		setReadPointerShapeCreator(new ACirclePointerShapeManager());
		PointerShapeCreator pointerShapeCreator = new AStringInAShapePointerShapeManager();
		pointerShapeCreator.setAdjustSize(false);
		setAnnotationPointerShapeCreator(pointerShapeCreator);


	}
	
	public VisualizationBasedVisualizer getVisualizer() {
		return visualizer;
	}

	public void setReadPointerShapeCreator(PointerShapeCreator newVal) {
		readPointerShapeCreator = newVal;	
		BoundedShape pointerShape = readPointerShapeCreator.toNewPointerShape(null, null, null);
        pointerShape = readPointerShapeCreator.toInvisiblePointedShape(pointerShape, null, null);


//		BoundedShape pointerShape = pointerShapeCreator.toInvisiblePointedShape(null, null);
		setReadPointerShapeInitialCoordinates(pointerShape);
//		pointerShape.setY(baseLineY);
//		pointerShape.setX(INITIAL_X - xSpacing);
		getVisualizer().getVisualization().setPointerShape(pointerShape);
	}
	public PointerShapeCreator getAnnotationPointerShapeCreator() {
		return annotationPointerShapeCreator;
		
	}
	public void setAnnotationPointerShapeCreator(PointerShapeCreator newVal) {
		annotationPointerShapeCreator = newVal;	
		BoundedShape pointerShape = annotationPointerShapeCreator.toNewPointerShape(null, null, null);
        pointerShape = annotationPointerShapeCreator.toInvisiblePointedShape(pointerShape, null, null);


//		BoundedShape pointerShape = pointerShapeCreator.toInvisiblePointedShape(null, null);
		setAnnotationPointerShapeInitialCoordinates(pointerShape);
//		pointerShape.setY(baseLineY);
//		pointerShape.setX(INITIAL_X - xSpacing);
		getVisualizer().getVisualization().setAnnotationShape(pointerShape);
	}
	public PointerShapeCreator getReadPointerShapeCreator() {
		return readPointerShapeCreator;
		
	}
	public  void setReadPointerShapeInitialCoordinates(BoundedShape aPointerShape) {
		aPointerShape.setY(statusY);
		aPointerShape.setX(statusX);
		
	}
	public  void setAnnotationPointerShapeInitialCoordinates(BoundedShape aPointerShape) {
		aPointerShape.setY(statusY);
		aPointerShape.setX(statusX);
		
	}
	public ListenableShapeVector getContainingShapes(UserDataType data) {
		Integer dataIndex = visualizer.getRootShapesIndex(data);
		ListenableShapeVector shapes = null;
		if (dataIndex == null)
			dataIndex = 0;
		 return getOrCreateShapeVector(dataIndex);
//		return visualizer.getVisualization().getShapes().get(dataIndex);
//		return shapes;

		
	}
	
//	public ListenableShapeVector createShapeVector() {
//		return new AListenableShapeVector();
//	}
	
	public ListenableShapeVector getOrCreateShapeVector(int aShapesIndex) {
		int numNewShapesVectorNeeded = aShapesIndex + 1 - visualizer.getVisualization().getShapes().size();
		for (int count = 0; count < numNewShapesVectorNeeded; count++)
			visualizer.getVisualization().getShapes().add(visualizer.createShapeVector());
		return visualizer.getVisualization().getShapes().get(aShapesIndex);
	}


}
