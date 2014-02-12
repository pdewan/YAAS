package yaas.animators;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import shapes.BoundedShape;

public class ACompositeShapeBoundsIncrementer extends AnAbstractRangeBasedCompositeObjectIncrementer<BoundedShape, Integer> /*implements RangeBasedObjectIncrementer<BoundedShape, Rectangle>*/ {

	

	public void init(BoundedShape anObject, Rectangle aFinalValue) {
		RangeBasedObjectIncrementer<BoundedShape, Integer> xIncrementer = new AShapeXIncrementer();
		RangeBasedObjectIncrementer<BoundedShape, Integer> yIncrementer = new AShapeYIncrementer();
		RangeBasedObjectIncrementer<BoundedShape, Integer> widthIncrementer = new AShapeWidthIncrementer();
		RangeBasedObjectIncrementer<BoundedShape, Integer> heightIncrementer = new AShapeHeightIncrementer();
		List <RangeBasedObjectIncrementer<BoundedShape, Integer>> incrementers = new ArrayList();
		List<BoundedShape>  objects = new ArrayList();
		List<Integer> finalValues = new ArrayList();
		incrementers.add(xIncrementer);
		incrementers.add(yIncrementer);
		incrementers.add(widthIncrementer);
		incrementers.add(heightIncrementer);
		for (int i=0; i < 4; i++)
			objects.add(anObject);
		finalValues.add(aFinalValue.x);
		finalValues.add(aFinalValue.y);
		finalValues.add(aFinalValue.width);
		finalValues.add(aFinalValue.height);
		super.init(incrementers, objects, finalValues);
//		super.init(incrementers);
//		super.init(objects, finalValues);

		
	}

}
