package yaas.visualizers.date;

import java.awt.Component;
import java.awt.Container;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import yaas.VisualizationBasedVisualizer;
import yaas.collection.ACollectionLayoutManager;
import yaas.common.AListenableVector;
import yaas.common.VestigalListenableVector;
import yaas.layout.ARowColumnLayoutManager;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.shapemappers.PointerShapeCreator;
import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.ATextModel;
import bus.uigen.shapes.AnOvalModel;
import bus.uigen.shapes.ListenableShapeVector;

public class ADateLayoutManager extends ARowColumnLayoutManager<ObservableDate>  implements VisualizationBasedLayoutManager<ObservableDate> {

	private int minuteDegrees = 0, hourDegrees = 0;

	private FlexibleShape clock, secondHand, minuteHand, hourHand;
	private ATextModel digital;
	protected int x, y, radius;
	public ListenableShapeVector getAndPositionShapes(ObservableDate data) {
		Integer dataIndex = visualizer.getRootShapesIndex(data);
		if (dataIndex == null)
			dataIndex = 0; // for date layout manager
//		ListenableShapeVector shapes = getOrCreateShapeVector(dataIndex);
		
		ListenableShapeVector shapes = getContainingShapes(data);

//		int rowNum = dataIndex / numRows;
//		int colNum = dataIndex % numRows;
//		
//		shapes.setY(rowNum*rowGap);
//		shapes.setX(colNum*columnGap);
		return shapes;
		
	}
	public ListenableShapeVector getContainingShapes(ObservableDate data) {
		Integer dataIndex = visualizer.getRootShapesIndex(data);
		if (dataIndex == null)
			dataIndex = 0;
		ListenableShapeVector shapes = visualizer.getCompositeLayoutManager().getOrCreateShapeVector(dataIndex);
		return shapes;

		
	}

	public ADateLayoutManager(VisualizationBasedVisualizer aVisualizer, int x, int y, int radius) {
		super(aVisualizer);
		this.x = x;
		this.y = y;
		this.radius = radius;

		clock = new AnOvalModel(x, y, 2 * radius, 2 * radius);
		secondHand = new ALineModel(x + radius, y + radius, 25, -25);
		minuteHand = new ALineModel(x + radius, y + radius,
				this.widthFromRadians(minuteDegrees),
				this.heightFromRadians(minuteDegrees));
		hourHand = new ALineModel(x + radius, y + radius,
				this.widthFromRadians(hourDegrees),
				this.heightFromRadians(hourDegrees));

		digital = new ATextModel("", x - radius * 2, y - radius, radius * 6,
				radius);
	}

	public util.models.ListenableVector<BoundedShape> display(ObservableDate date) {

//		ListenableVector<BoundedShape> vector = new AListenableVector<BoundedShape>();
		ListenableShapeVector vector = getAndPositionShapes(date);

		

		this.update(date);

		vector.add(clock);
		vector.add(secondHand);
		vector.add(minuteHand);
		vector.add(hourHand);
		vector.add(digital);

//		return vector;
		return null;
	}
	public void update(ObservableDate date) {

		int seconds = date.getSeconds();
		int minutes = date.getMinutes();
		int hours = date.getHours();
		String timeString = date.toLocaleString();

		double secondRadians = this.convertSecondOrMinuteToRadians(seconds);
		double minuteRadians = this.convertSecondOrMinuteToRadians(minutes);
		double hourRadians = this.convertHourToRadians(hours);

		secondHand.setWidth(this.widthFromRadians(secondRadians));
		secondHand.setHeight(this.heightFromRadians(secondRadians));
		minuteHand.setWidth(this.widthFromRadians(minuteRadians));
		minuteHand.setHeight(this.heightFromRadians(minuteRadians));
		hourHand.setWidth(3 * this.widthFromRadians(hourRadians) / 4);
		hourHand.setHeight(3 * this.heightFromRadians(hourRadians) / 4);

		digital.setText(timeString);
	}
	

	/**
	 * 
	 * @param time
	 *            : and integer measure of either seconds or minutes
	 * @return: the degrees, measured from the vertical y axis, that correspond
	 *          to that number of minutes or seconds on a round clock.
	 */
	public double convertSecondOrMinuteToRadians(int time) {
		return (6 * time) * (Math.PI / 180);
	}

	public double convertHourToRadians(int hour) {
		return (30 * hour) * (Math.PI / 180);
	}

	public int widthFromRadians(double radians) {
		return (int) (Math.sin(radians) * radius);
	}

	public int heightFromRadians(double radians) {
		return (int) (-Math.cos(radians) * radius);
	}

	public util.models.ListenableVector<AttributedShape> constructPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public VestigalListenableVector<AttributedShape> getPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public void incrementPseudoCodeMarker() {
		// TODO Auto-generated method stub

	}

	public void decrementPseudoCodeMarker() {
		// TODO Auto-generated method stub

	}

	public int getPseudoCodeMarker() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Component displayInPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Container getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	public PointerShapeCreator getPointerShapeCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPointerShapeCreator(PointerShapeCreator newVal) {
		// TODO Auto-generated method stub
		
	}
	public ObservableDate createBuffer() {
		return new ABufferDate(visualizer);
	}

//	public PointerShapeCreator getPointerShapeCreator() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void setPointerShapeCreator(PointerShapeCreator newVal) {
//		// TODO Auto-generated method stub
//		
//	}
}
