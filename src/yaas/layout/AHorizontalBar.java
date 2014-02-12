package yaas.layout;

import java.util.ArrayList;
import java.util.List;

import shapes.FlexibleShape;

import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.ARectangleModel;

public class AHorizontalBar extends ACompositeShape implements FlexibleShape{

	private List<FlexibleShape> shapes = new ArrayList<FlexibleShape>();
	public AHorizontalBar(int x, int y, int height, int width){

		ARectangleModel base = new ARectangleModel(10, 10, 40, 10);
		ALineModel l1 = new ALineModel(10,10,4,-4);
		ALineModel l2 = new ALineModel(50,10,4,-4);
		ALineModel l3 = new ALineModel(14,6,40,0);
		
		ALineModel l4 = new ALineModel(50,20,4,-4);
		ALineModel l5 = new ALineModel(54, 16,0,-10);

		shapes.add(base);
		shapes.add(l1);
		shapes.add(l2);
		shapes.add(l3);
		shapes.add(l4);
		shapes.add(l5);
		
		super.setShapes(shapes);
//		super.setX(x);
//		super.setY(y);
//		super.setWidth(width);
//		super.setHeight(height);
	}
}
