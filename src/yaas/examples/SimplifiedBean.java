package yaas.examples;

import java.util.Date;

import yaas.OE.CreateCustomView;
import yaas.controller.AController;
import yaas.layout.ACompositeRowColumnLayoutManager;
import yaas.visualizers.bean.ABeanVisualizer;
import yaas.visualizers.bean.ASimplifiedBeanVisualizer;
import yaas.visualizers.bean.date.ABeanDate;
import yaas.visualizers.bean.date.ABeanDateEventTrapper;
import yaas.visualizers.bean.date.ABeanDateLayoutManager;
import yaas.visualizers.bean.date.ABeanDateMethodReplayer;
import yaas.visualizers.bean.date.ASimplifiedBeanDate;
import yaas.visualizers.bean.date.ASimplifiedBeanDateLayoutManager;


public class SimplifiedBean {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		try {

			ASimplifiedBeanDate date = new ASimplifiedBeanDate();
			ASimplifiedBeanVisualizer visualizer = new ASimplifiedBeanVisualizer(
					 date);
			visualizer.setCompositeLayoutManager(new ACompositeRowColumnLayoutManager(visualizer));

			
			ASimplifiedBeanDateLayoutManager layoutManager = new ASimplifiedBeanDateLayoutManager(visualizer, 100, 100, 50);
//			visualizer.setLayoutManager(layoutManager);
//			ABeanDateMethodReplayer trapper = new ABeanDateMethodReplayer(
//					(ASimplifiedBeanDateLayoutManager) visualizer.getLayoutManager(),
//					new ASimplifiedBeanDate());
			ABeanDateMethodReplayer trapper = new ABeanDateMethodReplayer(
					layoutManager,
					new ASimplifiedBeanDate());
			visualizer.addReplayMethodListenerOfObjectTree(date, trapper);

			visualizer.visualize(date, layoutManager);

			Object[] menuItems = {};
			CreateCustomView viewCreator1 = new CreateCustomView();

			viewCreator1.createView(menuItems, visualizer, layoutManager.getPseudoCode(), true, false, true);

			Clock timer = new Clock();
			while (true) {
				Date d = new Date();
				date.setDate(d);
				((AController)visualizer.getController()).next();
				timer.waitOneSecond();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void waitOneSecond() {
		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
