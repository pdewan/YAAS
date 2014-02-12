package yaas.examples;

import java.util.Date;

import yaas.OE.CreateCustomView;
import yaas.layout.ACompositeRowColumnLayoutManager;
import yaas.visualizers.date.ADateLayoutManager;
import yaas.visualizers.date.ADateVisualizer;
import yaas.visualizers.date.ADateEventTrapper;
import yaas.visualizers.date.ObservableDate;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;


public class Clock {

	public static void main(String[] args) {

		try {
			

			ObjectEditor.setDefaultAttribute(AttributeNames.SEPARATE_THREAD, true);
			ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS,
					false);
			
			ADateVisualizer visualizer = new ADateVisualizer();
			ADateLayoutManager layoutManager = new ADateLayoutManager(visualizer, 100, 100, 25);

			ObservableDate date = new ObservableDate();
			ADateEventTrapper trapper = new ADateEventTrapper(visualizer);
				visualizer.setCompositeLayoutManager(new ACompositeRowColumnLayoutManager(visualizer));
				
			
			// original lines
//			visualizer.addTrapper(trapper);
//			visualizer.visualize(date);
			// swapped lines
			visualizer.visualize(date, layoutManager);
			visualizer.addTrapper(trapper);

			Object[] menuItems = {};
			
			
			CreateCustomView viewCreator1 = new CreateCustomView();
			viewCreator1.createView(menuItems, visualizer,
					layoutManager.getPseudoCode(), true, false, true);
			//ObjectEditor.edit(visualizer);

			Clock timer = new Clock();

			while (true) {
				date.setDate(new Date());
				timer.waitOneSecond();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// should be a static method
	public synchronized void waitOneSecond() {
		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
