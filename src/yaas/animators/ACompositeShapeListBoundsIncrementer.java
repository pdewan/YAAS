package yaas.animators;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import shapes.BoundedShape;

public class ACompositeShapeListBoundsIncrementer extends AnAbstractRangeBasedCompositeObjectIncrementer<BoundedShape, Rectangle> implements RangeBasedObjectIncrementer<List<? extends BoundedShape>, List<Rectangle>> {

	public void init(List<? extends BoundedShape> anObjects, List<Rectangle> aFinalValues) {
		List<RangeBasedObjectIncrementer<BoundedShape, Rectangle>> anIncrementers = new ArrayList();
		for (int i = 0; i < anObjects.size(); i++) {
			anIncrementers.add(new AShapeBoundsIncrementer());
		}
		init (anIncrementers, anObjects, aFinalValues);
	}

	

//	public void init(BoundedShape anObject, Rectangle aFinalValue) {
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
