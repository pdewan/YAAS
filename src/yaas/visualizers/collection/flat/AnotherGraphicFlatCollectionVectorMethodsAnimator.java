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
import yaas.visualizers.collection.CollectionVisualizer;

public class AnotherGraphicFlatCollectionVectorMethodsAnimator 
		extends AGraphicFlatCollectionVectorMethodsAnimator
//		implements
//		EventTrapper<VectorMethodsListener<Integer>, ListenableVector<Integer>>,
//		VectorMethodsListener<Integer> 
	{
	
//	protected AnIntegerBarChartVisualizer visualizer;
//	protected AnIntegerBarChartLayoutManager layoutManager;

//	public AnotherGraphicFlatCollectionVectorMethodAnimator(
//			AnIntegerBarChartVisualizer visualizer,
//			AnIntegerBarChartLayoutManager layoutManager) {
//		super(visualizer, layoutManager);
//	}
	
	
	public AnotherGraphicFlatCollectionVectorMethodsAnimator(
			CollectionVisualizer visualizer) {
		super(visualizer);
		// TODO Auto-generated constructor stub
	}

	public void elementChanged(Object source, Integer element, int pos) {

//		BoundedShape shape = visualizer.getVisualization().getShapes().get(0).get(pos);
		BoundedShape shape = getContainingShapes(source).get(pos);

		ObjectToShapeTranslator<Integer> translator =((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer(source)).getElementToShapeTranslator();
		Rectangle newBounds = translator.calculateNewShape(shape, element).getBounds();
//		AnimationUtil.newBounds(shape, newBounds);
//		int newHeight = element * layoutManager.getScaleFactor();
		
//		AnimationUtil.grow(shape, shape.getWidth(), newHeight);

		AnimationUtil.move(shape, shape.getX(), shape.getY() - 100, true,
				Color.BLACK, Color.BLACK);
//		Rectangle newBounds = translator.calcuateNewBounds(shape, element);


//		shape.setHeight(element * layoutManager.getScaleFactor());
		shape.setHeight(newBounds.height);

		AnimationUtil.move(shape, shape.getX(), ((FlatCollectionLayoutManager) visualizer.getLayoutManagerOfBuffer(source)).getBaseLine()
				- shape.getHeight(), true, Color.BLACK, Color.BLACK);

	}

	

}
