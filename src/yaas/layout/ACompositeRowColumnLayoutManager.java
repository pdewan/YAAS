package yaas.layout;

import java.util.ArrayList;
import java.util.List;

import bus.uigen.shapes.AListenableShapeVector;
import bus.uigen.shapes.ListenableShapeVector;
import shapes.BoundedShape;
import util.models.ListenableVector;
import yaas.Visualization;
import yaas.VisualizationBasedVisualizer;
import yaas.shapemappers.ACirclePointerShapeManager;
import yaas.shapemappers.PointerShapeCreator;
import yaas.visualizers.collection.CollectionVisualizer;

public class ACompositeRowColumnLayoutManager<UserDataType> extends
		ACompositeLayoutManager<UserDataType>
// extends AVisualizationBasedLayoutManager<UserDataType>
		implements CompositeRowColumnLayoutManager<UserDataType> {
	public static final int DEFAULT_COLUMN_GAP = 10;
	public static final int DEFAULT_ROW_GAP = 25;
	public static final int DEFAULT_STATUS_GAP = 50;

	
	public static final int DEFAULT_NUM_ROWS = 1;
	// public static final int DEFAULT_STATUS_X = 5;
	// public static final int DEFAULT_STATUS_Y = 5;
	// protected int statusX = DEFAULT_STATUS_X;
	// protected int statusY = DEFAULT_STATUS_Y;

	// int curRowNum, curColumnNum;
	int numRows = DEFAULT_NUM_ROWS;
	int columnGap = DEFAULT_COLUMN_GAP;
	int rowGap = DEFAULT_ROW_GAP;
	
	int statusGap = DEFAULT_STATUS_GAP;
	// VisualizationBasedVisualizer visualizer;

	// public static final int STATUS_DEFAULT_X = 30;
	//
	//
	// public static final int STATUS_DEFAULT_Y = 0;
	//
	//
	// int statusX = STATUS_DEFAULT_X;
	// int statusY = STATUS_DEFAULT_Y;
	//
	// int curItemNo;

	// PointerShapeCreator<ElementType> pointerShapeCreator = new
	// CirclePointerShapeManager();
	// protected VisualizationBasedVisualizer visualizer;
	List<VisualizationBasedLayoutManager> layoutManagers = new ArrayList();

	public ACompositeRowColumnLayoutManager(
			VisualizationBasedVisualizer aVisualizer) {
		super(aVisualizer);
		// visualizer = aVisualizer;

		// Visualization visualization = visualizer.getVisualization();
		// BoundedShape statusShape = visualization.getStatusShape();
		// statusShape.setX(statusX);
		// statusShape.setY(statusY);
		// super(aVisualizer);
		// visualizer = aVisualizer;
		// visualizer.getVisualization().getStatusShape().setX(statusX);
		// visualizer.getVisualization().getStatusShape().setY(statusY);
		// setPointerShapeCreator(new CirclePointerShapeManager());

	}

	// public ListenableVector<BoundedShape> display(UserDataType data) {
	// int dataIndex = visualizer.getRootShapesIndex(data);
	// int rowNum = dataIndex / numRows;
	// int colNum = dataIndex % numRows;
	//
	// ListenableShapeVector shapes = getOrCreateShapeVector(dataIndex);
	// shapes.setX(rowNum*rowGap);
	// shapes.setY(colNum*columnGap);
	// return null;
	// }

	public ListenableShapeVector getAndPositionShapes(UserDataType data,
			VisualizationBasedLayoutManager aLayoutManager) {
		Integer dataIndex = visualizer.getRootShapesIndex(data);
		if (dataIndex == null)
			dataIndex = 0; // for date layout manager
			// ListenableShapeVector shapes = getOrCreateShapeVector(dataIndex);

		ListenableShapeVector shapes = getContainingShapes(data);

		int rowNum = dataIndex / numRows;
		int colNum = dataIndex % numRows;
		int shapesX = 0;
		int shapesY = 0;
		if (layoutManagers.size() > 0) {
			VisualizationBasedLayoutManager<UserDataType> previousLayoutManager = layoutManagers
					.get(layoutManagers.size() - 1);
			ListenableShapeVector previousShapes = previousLayoutManager
					.getContainingShapes(data);

			if (rowNum > 0) {
				shapesY = previousShapes.getY()
						+ previousLayoutManager.getPreferredHeight() + rowGap;
			}
			if (colNum > 0) {
				shapesX = previousShapes.getX()
						+ previousLayoutManager.getPreferredWidth() + columnGap;
			}
		} else {
			shapesY = statusY +
					statusGap;
		}
		//
		// shapes.setY(rowNum*rowGap);
		// shapes.setX(colNum*columnGap);

		shapes.setY(shapesY);
		shapes.setX(shapesX);
		layoutManagers.add(aLayoutManager);
		return shapes;

	}

	// public ListenableShapeVector getContainingShapes(UserDataType data) {
	// Integer dataIndex = visualizer.getRootShapesIndex(data);
	// ListenableShapeVector shapes = null;
	// if (dataIndex == null)
	// dataIndex = 0;
	// return getOrCreateShapeVector(dataIndex);
	// // return visualizer.getVisualization().getShapes().get(dataIndex);
	// // return shapes;
	//
	//
	// }
	//
	// // public ListenableShapeVector createShapeVector() {
	// // return new AListenableShapeVector();
	// // }
	//
	// public ListenableShapeVector getOrCreateShapeVector(int aShapesIndex) {
	// int numNewShapesVectorNeeded = aShapesIndex + 1 -
	// visualizer.getVisualization().getShapes().size();
	// for (int count = 0; count < numNewShapesVectorNeeded; count++)
	// visualizer.getVisualization().getShapes().add(visualizer.createShapeVector());
	// return visualizer.getVisualization().getShapes().get(aShapesIndex);
	// }

	// public PointerShapeCreator<ElementType>getPointerShapeCreator() {
	// return pointerShapeCreator;
	//
	// }
	// public abstract void setPointerShapeInitialCoordinates(BoundedShape
	// aPointerShape);
	// // public void setPointerShapeInitialCoordinates(AttributedShape
	// aPointerShape) {
	// // aPointerShape.setY(baseLineY);
	// // aPointerShape.setX(INITIAL_X - xSpacing);
	// // }
	// public void setPointerShapeCreator(PointerShapeCreator<ElementType>
	// newVal) {
	// pointerShapeCreator = newVal;
	// BoundedShape pointerShape = pointerShapeCreator.toNewPointerShape(null,
	// null);
	// pointerShape = pointerShapeCreator.toInvisiblePointedShape(pointerShape,
	// null, null);
	//
	//
	// // BoundedShape pointerShape =
	// pointerShapeCreator.toInvisiblePointedShape(null, null);
	// setPointerShapeInitialCoordinates(pointerShape);
	// // pointerShape.setY(baseLineY);
	// // pointerShape.setX(INITIAL_X - xSpacing);
	// visualizer.getVisualization().setPointerShape(pointerShape);
	// }
	// public ListenableVector<BoundedShape> display(UserDataType data) {
	// return null;
	//
	// }

}
