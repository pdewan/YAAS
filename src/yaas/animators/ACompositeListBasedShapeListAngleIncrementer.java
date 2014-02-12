package yaas.animators;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import shapes.BoundedShape;
import shapes.Rotatable;

public class ACompositeListBasedShapeListAngleIncrementer 
    extends AnAbstractListBasedCompositeObjectIncrementer<Rotatable, Double> {
//implements ListBasedObjectIncrementer<List<? extends Rotatable>, List<Double[]>> {
//
//	public void init(List<? extends Rotatable> anObjects, List<Double[]> aListOfValueLists) {
//		List<ListBasedObjectIncrementer<Rotatable, Double>> anIncrementers = new ArrayList();
//		for (int i = 0; i < anObjects.size(); i++) {
//			anIncrementers.add(new AListBasedShapeAngleIncrementer());
//		}
//		init (anIncrementers, anObjects, aListOfValueLists);
//	}
//	 ListBasedObjectIncrementer<Rotatable, Double> createObjectIncrementer() {
//		 ListBasedObjectIncrementer<Rotatable, Double> retVal = new AListBasedShapeAngleIncrementer();
//		 retVal.setCirculate(true);
//		 return retVal;
//	 }

	 ListBasedObjectIncrementer<Rotatable, Double> createObjectIncrementer() {
		 return new AListBasedShapeAngleIncrementer();
	 }

//	public void init(BoundedShape anObject, Dimension aFinalValue) {
//		ObjectIncrementer<BoundedShape, Integer> xIncrementer = new AShapeXIncrementer();
//		ObjectIncrementer<BoundedShape, Integer> yIncrementer = new AShapeYIncrementer();
//		ObjectIncrementer<BoundedShape, Integer> widthIncrementer = new AShapeWidthIncrementer();
//		ObjectIncrementer<BoundedShape, Integer> heightIncrementer = new AShapeHeightIncrementer();
//		List <ObjectIncrementer<BoundedShape, Integer>> incrementers = new ArrayList();
//		List<BoundedShape>  objects = new ArrayList();
//		List<Integer> finalValues = new ArrayList();
//		incrementers.add(xIncrementer);
//		incrementers.add(yIncrementer);
//		incrementers.add(widthIncrementer);
//		incrementers.add(heightIncrementer);
//		for (int i=0; i < 4; i++)
//			objects.add(anObject);
//		finalValues.add(aFinalValue.x);
//		finalValues.add(aFinalValue.y);
//		finalValues.add(aFinalValue.width);
//		finalValues.add(aFinalValue.height);
//		super.init(incrementers, objects, finalValues);
//
//		
//	}

}
