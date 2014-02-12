package yaas.models.barChart.debug;

import java.awt.Color;

import shapes.BoundedShape;
import shapes.FlexibleShape;

import yaas.animators.AnimationUtil;
import yaas.common.*;
import yaas.visualizers.collection.flat.AnIntegerBarChartEventTrapper;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnIntegerBarChartVisualizer;



public class ABrokenIntegerBarChartEventTrapper extends
		AnIntegerBarChartEventTrapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5602392808540960613L;
	public ABrokenIntegerBarChartEventTrapper(
			AnIntegerBarChartVisualizer visualizer,
			AnIntegerBarChartLayoutManager layoutManager) {
		super(visualizer, layoutManager);
		// TODO Auto-generated constructor stub
	}
	@Override
	public synchronized void elementSwapped(Object newParam, int index1,
			int index2) {

		if (index1 <= index2) {
			BoundedShape s1 = visualizer.getVisualization().getShapes().get(0).get(index1);
			BoundedShape s2 = visualizer.getVisualization().getShapes().get(0).get(index2);

			int x1 = s1.getX();
			int x2 = s2.getX();

			AnimationUtil.move(s1, x2, s1.getY(), true, Color.BLACK,
					Color.BLACK);
			AnimationUtil.move(s2, x1, s2.getY(), true, Color.BLACK,
					Color.BLACK);
			// This commented out section introduces an error
			//visualizer.swap(index1, index2);
		} else {
			elementSwapped(newParam, index2, index1);
		}

	}

}
