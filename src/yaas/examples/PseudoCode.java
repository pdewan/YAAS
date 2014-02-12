package yaas.examples;

import java.awt.Color;

import yaas.OE.Algorithms;
import yaas.OE.CreateCustomView;
import yaas.OE.Sorting;
import yaas.common.*;
import yaas.visualizers.collection.tree.ALinearEventTrapper;
import yaas.visualizers.collection.tree.ALinearVectorMethodsAnimator;
import yaas.visualizers.collection.tree.ALinearVisualizer;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.shapes.ARectangleModel;


public class PseudoCode {
	public static void main(String args[]) {
		VestigalListenableVector<Integer> vector = new AListenableVector<Integer>();

		vector.add(20);
		vector.add(19);  
		vector.add(18);
		vector.add(17);
		vector.add(16);
		vector.add(15);
		vector.add(14);  
		vector.add(13);
		vector.add(12);
		vector.add(11);
		vector.add(10);
		vector.add(9);  
		vector.add(8);
		vector.add(7);
		vector.add(6);
		vector.add(5);
		vector.add(4);  //3D Bar
		vector.add(3);
		vector.add(2);
		vector.add(1);

		ObjectEditor.setDefaultAttribute(AttributeNames.SEPARATE_THREAD, true);
		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS,
				false);

		ALinearVisualizer<Integer> visualizer = new ALinearVisualizer<Integer>(
				new ARectangleModel(0, 0, 10, 6));

		visualizer.getLayoutManager().setDynamicHeight(false);
		visualizer.getLayoutManager().setDynamicWidth(true);
		visualizer.getLayoutManager().setSolid(true);
		visualizer.showLines(false);
		visualizer.showRoot(false);
		visualizer.getLayoutManager().setVertical(false);

		visualizer.getLayoutManager().setColor(Color.BLACK);
		visualizer.getLayoutManager().setHighlighting(Color.GREEN);
		try {
			visualizer.visualize(vector, null);
//			visualizer.addTrapper(new ALinearEventTrapper<Integer>(visualizer));
			visualizer.addReplayMethodListenerOfObjectTree(vector, new ALinearVectorMethodsAnimator<Integer>(visualizer));

		} catch (Exception e) {
			e.printStackTrace();
		}

		Object[] menuItems = {new Sorting(vector), new Algorithms(vector)};
		
		CreateCustomView viewCreator1 = new CreateCustomView();
		viewCreator1.createView(menuItems, visualizer,
				visualizer.getLayoutManager().getPseudoCode(), true, true, true);
		yaas.common.Algorithms.bubbleSort(vector);

	}
}
