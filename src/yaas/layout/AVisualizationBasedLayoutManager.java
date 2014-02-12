package yaas.layout;

import bus.uigen.shapes.AListenableShapeVector;
import bus.uigen.shapes.ListenableShapeVector;
import shapes.BoundedShape;
import util.models.ListenableVector;
import yaas.VisualizationBasedVisualizer;
import yaas.shapemappers.ACirclePointerShapeManager;
import yaas.shapemappers.PointerShapeCreator;
import yaas.visualizers.collection.CollectionVisualizer;

public abstract class AVisualizationBasedLayoutManager<UserDataType> 
			implements VisualizationBasedLayoutManager<UserDataType>  {
//	public static final int DEFAULT_COLUMN_GAP = 50;
//	public static final int DEFAULT_ROW_GAP = 150;
//	public static final int DEFAULT_NUM_ROWS = 1;
//
////	int curRowNum, curColumnNum;
//	int numRows = DEFAULT_NUM_ROWS ;
//	int columnGap = DEFAULT_COLUMN_GAP;
//	int rowGap = DEFAULT_ROW_GAP;
	
public static final int STATUS_DEFAULT_X = 30;
public static final int DEFAULT_HEIGHT= 40;
public static final int DEFAULT_WIDTH= 100;


protected int preferredHeight = DEFAULT_HEIGHT;

protected int preferredWidth = DEFAULT_WIDTH;

protected int x, y;
protected ListenableShapeVector containingShapes;



	public static final int STATUS_DEFAULT_Y = 0;

	
	int statusX = STATUS_DEFAULT_X;
	int statusY = STATUS_DEFAULT_Y;
	
//	int curItemNo;
	
//	PointerShapeCreator<ElementType> pointerShapeCreator = new CirclePointerShapeManager();
	protected VisualizationBasedVisualizer visualizer;

	public AVisualizationBasedLayoutManager(VisualizationBasedVisualizer aVisualizer) {
		visualizer = aVisualizer;
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
	public int getPreferredHeight() {
		return preferredHeight;
	}

	public void setPreferredHeight(int preferredHeight) {
		this.preferredHeight = preferredHeight;
	}
	public int getPreferredWidth() {
		return preferredWidth;
	}
	public void setPreferredWidth(int preferredWidth) {
		this.preferredWidth = preferredWidth;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ListenableShapeVector getContainingShapes(UserDataType data) {
		return containingShapes;
	}

}
