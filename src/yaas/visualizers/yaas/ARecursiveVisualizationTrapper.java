package yaas.visualizers.yaas;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Observable;

import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.models.ChangeableVector;
import util.models.ListenableVector;
import yaas.VisualizationBasedVisualizer;

import yaas.common.Util;
import yaas.controller.Control;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.layout.gui.APanelGenerator;
import yaas.visualizers.observer.*;

import bus.uigen.ObjectEditor;

public class ARecursiveVisualizationTrapper extends AnObserverEventTrapper {

	private ARecursiveVisualizationAnimator visualizer;
	private ListenableVector<BoundedShape> previousVal;
	private VisualizationBasedLayoutManager layoutManager;
	private APanelGenerator panelGenerator = new APanelGenerator();

	public ARecursiveVisualizationTrapper(ARecursiveVisualizationAnimator cv,
			ObservableEventGenerator observable,
			ARecursiveVisualizationLayoutManager layoutManager) {
		super(observable);
		this.layoutManager = layoutManager;
		visualizer = cv;
		previousVal = visualizer.getVisualization().getShapes().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {

		if (arg1.equals("next")) {

			previousVal = Util.copyVector((ListenableVector<? extends BoundedShape>) visualizer.getVisualization());
//			visualizer.clear();
			VisualizationBasedVisualizer vis = (VisualizationBasedVisualizer) ((Control) visualizer
					.getOriginalData().get(0)).getVisualizer();
//			ListenableVector<SimpleShape> oldVal = (ListenableVector<SimpleShape>)((Control) visualizer
//					.getOriginalData().get(0)).getVisualizer().getShapes();
			ListenableVector<BoundedShape> oldVal = (ListenableVector<BoundedShape>)vis.getVisualization();
//
//			ListenableVector<SimpleShape> oldVal = (ListenableVector<SimpleShape>)((Control) visualizer
//					.getOriginalData().get(0)).getVisualizer().getShapes();
			visualizer.getVisualization().getShapes().get(0).addAll(Util.copyVector(oldVal));
//			System.out.println ("commented out the extra display of drawing container in nextr");

			layoutManager.getPanel().removeAll();
			ObjectEditor.editInDrawingContainer(Util.copyVector(oldVal),
					layoutManager.getPanel(), false);
			layoutManager.getPanel().add(
					panelGenerator.layoutAndPopulateButtonPanel(),
					BorderLayout.NORTH);
		}
		if (arg1.equals("previous")) {

			ListenableVector<BoundedShape> temp = Util.copyVector(previousVal);
			previousVal = Util.copyVector((ListenableVector<? extends BoundedShape>) visualizer.getVisualization());
			((List<BoundedShape>) visualizer.getVisualization()).clear();

			((ChangeableVector<BoundedShape>) visualizer.getVisualization()).addAll(temp);
			
//			System.out.println ("commented out the extra display of drawing container in nextr");


			layoutManager.getPanel().removeAll();
//			System.out.println ("commented out the extra display of drawing container in nextr");

			ObjectEditor.editInDrawingContainer(temp, layoutManager.getPanel(),
					false);
			layoutManager.getPanel().add(
					panelGenerator.layoutAndPopulateButtonPanel(),
					BorderLayout.NORTH);
		}

	}
}
