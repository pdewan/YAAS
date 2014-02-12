package yaas.layout;

import bus.uigen.shapes.AListenableShapeVector;
import bus.uigen.shapes.ListenableShapeVector;
import shapes.BoundedShape;
import util.models.ListenableVector;
import yaas.VisualizationBasedVisualizer;
import yaas.shapemappers.ACirclePointerShapeManager;
import yaas.shapemappers.PointerShapeCreator;
import yaas.visualizers.collection.CollectionVisualizer;

public abstract class ARowColumnLayoutManager<UserDataType>
			extends AVisualizationBasedLayoutManager<UserDataType>
			implements RowColumnLayoutManager<UserDataType>   {
	public static final int DEFAULT_COLUMN_GAP = 50;
	public static final int DEFAULT_ROW_GAP = 150;
	public static final int DEFAULT_NUM_ROWS = 1;

//	int curRowNum, curColumnNum;
	int numRows = DEFAULT_NUM_ROWS ;
	int columnGap = DEFAULT_COLUMN_GAP;
	int rowGap = DEFAULT_ROW_GAP;
	
//public static final int STATUS_DEFAULT_X = 30;
//	
//	
//	public static final int STATUS_DEFAULT_Y = 0;
//
//	
//	int statusX = STATUS_DEFAULT_X;
//	int statusY = STATUS_DEFAULT_Y;
//	
//	int curItemNo;
	
//	PointerShapeCreator<ElementType> pointerShapeCreator = new CirclePointerShapeManager();
//	protected VisualizationBasedVisualizer visualizer;

	public ARowColumnLayoutManager(VisualizationBasedVisualizer aVisualizer) {
		super(aVisualizer);
//		visualizer = aVisualizer;
//		visualizer.getVisualization().getStatusShape().setX(statusX);
//		visualizer.getVisualization().getStatusShape().setY(statusY);
//		setPointerShapeCreator(new CirclePointerShapeManager());

	}
//	public ListenableVector<BoundedShape> display(UserDataType data) {
//		int dataIndex = visualizer.getRootShapesIndex(data);
//		int rowNum = dataIndex / numRows;
//		int colNum = dataIndex % numRows;
//		
//		ListenableShapeVector shapes = getOrCreateShapeVector(dataIndex);
//		shapes.setX(rowNum*rowGap);
//		shapes.setY(colNum*columnGap);
//		return null;
//	}
	
	public ListenableShapeVector getAndPositionShapes(UserDataType data) {
		Integer dataIndex = visualizer.getRootShapesIndex(data);
		if (dataIndex == null)
			dataIndex = 0; // for date layout manager
//		ListenableShapeVector shapes = getOrCreateShapeVector(dataIndex);
		
		ListenableShapeVector shapes = getContainingShapes(data);

		int rowNum = dataIndex / numRows;
		int colNum = dataIndex % numRows;
		
		shapes.setY(rowNum*rowGap);
		shapes.setX(colNum*columnGap);
		return shapes;
		
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
	
	

//	public PointerShapeCreator<ElementType>getPointerShapeCreator() {
//		return pointerShapeCreator;
//		
//	}
//	public abstract void setPointerShapeInitialCoordinates(BoundedShape aPointerShape);
////	public void setPointerShapeInitialCoordinates(AttributedShape aPointerShape) {
////		aPointerShape.setY(baseLineY);
////		aPointerShape.setX(INITIAL_X - xSpacing);
////	}
//	public void setPointerShapeCreator(PointerShapeCreator<ElementType> newVal) {
//		pointerShapeCreator = newVal;	
//		BoundedShape pointerShape = pointerShapeCreator.toNewPointerShape(null, null);
//        pointerShape = pointerShapeCreator.toInvisiblePointedShape(pointerShape, null, null);
//
//
////		BoundedShape pointerShape = pointerShapeCreator.toInvisiblePointedShape(null, null);
//		setPointerShapeInitialCoordinates(pointerShape);
////		pointerShape.setY(baseLineY);
////		pointerShape.setX(INITIAL_X - xSpacing);
//		visualizer.getVisualization().setPointerShape(pointerShape);
//	}
//	public ListenableVector<BoundedShape> display(UserDataType data) {
//		return null;
//		
//	}


}
