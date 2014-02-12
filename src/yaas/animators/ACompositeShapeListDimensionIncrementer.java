package yaas.animators;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import shapes.BoundedShape;

public class ACompositeShapeListDimensionIncrementer extends AnAbstractRangeBasedCompositeObjectIncrementer<BoundedShape, Dimension> 
implements RangeBasedObjectIncrementer<List<? extends BoundedShape>, List<Dimension>> 
{

	public void init(List<? extends BoundedShape> anObjects, List<Dimension> aFinalValues) {
		List<RangeBasedObjectIncrementer<BoundedShape, Dimension>> anIncrementers = new ArrayList();
		for (int i = 0; i < anObjects.size(); i++) {
			anIncrementers.add(new AShapeDimensionIncrementer());
		}
		init (anIncrementers, anObjects, aFinalValues);
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
