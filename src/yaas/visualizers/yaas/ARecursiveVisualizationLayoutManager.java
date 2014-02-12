package yaas.visualizers.yaas;


import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Observer;

import javax.swing.JPanel;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.PseudoCode;
import yaas.VisualizationBasedVisualizer;
import yaas.Visualizer;
import yaas.common.Util;
import yaas.controller.Control;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.layout.gui.APanelGenerator;
import yaas.shapemappers.PointerShapeCreator;
import yaas.visualizers.observer.ObservableEventGenerator;
import bus.uigen.ObjectEditor;
import bus.uigen.shapes.AStringModel;
import bus.uigen.shapes.ListenableShapeVector;

public class ARecursiveVisualizationLayoutManager implements
		VisualizationBasedLayoutManager<ObservableEventGenerator> {

	private JPanel panel = new JPanel();
	private APanelGenerator panelGenerator = new APanelGenerator();
	protected ListenableVector<AttributedShape> pseudoCode =  new AListenableVector<AttributedShape>();
	Visualizer visualizer;
	public ARecursiveVisualizationLayoutManager(Visualizer aVisualizer) {
		visualizer = aVisualizer;
	}

	@SuppressWarnings("unchecked")
	public ListenableVector<BoundedShape> display(ObservableEventGenerator control) {
		this.constructPseudoCode();
		
		if (!(control instanceof ARecursiveVisualizationBuffer)) {
			return null;
		} else {
			Visualizer visualizer = ((Control) ((ARecursiveVisualizationBuffer) control)
					.getBufferData()).getVisualizer();
			ListenableVector<AttributedShape> oldVal = (ListenableVector<AttributedShape>) ((VisualizationBasedVisualizer) visualizer).getVisualization();
		
//			ListenableVector<SimpleShape> oldVal = (ListenableVector<SimpleShape>)((Control) ((ARecursiveVisualizationBuffer) control)
//					.getBufferData()).getVisualizer().getShapes();

			
			ObjectEditor.editInDrawingContainer(Util.copyVector(oldVal), panel, false);	
			panel.add(panelGenerator.layoutAndPopulateButtonPanel(),BorderLayout.NORTH);
			return Util.copyVector(oldVal);
			
		}
	}

	public Component displayInPanel() {
		return panel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public ListenableVector<AttributedShape> constructPseudoCode() {
		int i = 20;
		for (String s : PseudoCode.pseudoCode) {
			int numLeadingSpaces = Util.findLeadingSpaces(s);
			this.pseudoCode.add(new AStringModel(s,  10+ numLeadingSpaces, i,
					100, 20));
			i += 20;
		}
		return this.pseudoCode;
	}

	public ListenableVector<AttributedShape> getPseudoCode() {
		return this.pseudoCode;
	}

	public int getPseudoCodeMarker() {
		// TODO Auto-generated method stub
		return 0;
	}

	public PointerShapeCreator getPointerShapeCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPointerShapeCreator(PointerShapeCreator newVal) {
		// TODO Auto-generated method stub
		
	}
public ObservableEventGenerator createBuffer() {
		
		ARecursiveVisualizationBuffer buffer = new ARecursiveVisualizationBuffer((VisualizationBasedVisualizer<Observer, ObservableEventGenerator>) visualizer);
//		buffer.setBufferData((ARecursiveVisualizer) visualizer.originalData.get(0));
		return buffer;
	}

public int getPreferredHeight() {
	// TODO Auto-generated method stub
	return 0;
}

public void setPreferredHeight(int newVal) {
	// TODO Auto-generated method stub
	
}

public int getPreferredWidth() {
	// TODO Auto-generated method stub
	return 0;
}

public void setPreferredWidth(int newVal) {
	// TODO Auto-generated method stub
	
}

public ListenableShapeVector getContainingShapes(ObservableEventGenerator data) {
	// TODO Auto-generated method stub
	return null;
}
}
