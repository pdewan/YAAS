package yaas.visualizers.collection.flat;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.List;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.models.VectorMethodsListener;
import yaas.animators.AnimationUtil;

import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.trappers.EventTrapper;

public class AnotherIntegerBarChartEventTrapper 
		extends AnIntegerBarChartEventTrapper
//		implements
//		EventTrapper<VectorMethodsListener<Integer>, ListenableVector<Integer>>,
//		VectorMethodsListener<Integer> 
	{
	
//	protected AnIntegerBarChartVisualizer visualizer;
//	protected AnIntegerBarChartLayoutManager layoutManager;

	public AnotherIntegerBarChartEventTrapper(
			AnIntegerBarChartVisualizer visualizer,
			AnIntegerBarChartLayoutManager layoutManager) {
		super(visualizer, layoutManager);
	}
	
	
	public void elementChanged(Object source, Integer element, int pos) {

		BoundedShape shape = visualizer.getVisualization().getShapes().get(0).get(pos);
		
		ObjectToShapeTranslator<Integer> translator = layoutManager.getElementToShapeTranslator();
		Rectangle newBounds = translator.calculateNewShape(shape, element).getBounds();
//		AnimationUtil.newBounds(shape, newBounds);
//		int newHeight = element * layoutManager.getScaleFactor();
		
//		AnimationUtil.grow(shape, shape.getWidth(), newHeight);

		AnimationUtil.move(shape, shape.getX(), shape.getY() - 100, true,
				Color.BLACK, Color.BLACK);
//		Rectangle newBounds = translator.calcuateNewBounds(shape, element);


//		shape.setHeight(element * layoutManager.getScaleFactor());
		shape.setHeight(newBounds.height);

		AnimationUtil.move(shape, shape.getX(), layoutManager.getBaseLine()
				- shape.getHeight(), true, Color.BLACK, Color.BLACK);

	}

	

}
