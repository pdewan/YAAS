package yaas.visualizers.bean.date;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.util.Date;

import javax.swing.JPanel;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.models.PropertyListenerRegisterer;
import yaas.VisualizationBasedVisualizer;
import yaas.common.VestigalListenableVector;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.visualizers.bean.ABeanBuffer;
import yaas.visualizers.bean.ASimplifiedBeanBuffer;
import yaas.visualizers.bean.ASimplifiedBeanVisualizer;
import yaas.visualizers.bean.BeanEventGenerator;
import yaas.visualizers.date.ADateLayoutManager;
import yaas.visualizers.date.ObservableDate;
import bus.uigen.shapes.ListenableShapeVector;
import bus.uigen.translator.Translator;

public class ASimplifiedBeanDateLayoutManager implements
		VisualizationBasedLayoutManager<PropertyListenerRegisterer> {

	private ADateLayoutManager layoutManager;
	ASimplifiedBeanVisualizer visualizer;
	Translator<PropertyListenerRegisterer, AttributedShape> dataToShapeTranslator;

	public ASimplifiedBeanDateLayoutManager(ASimplifiedBeanVisualizer aVisualizer, int x, int y, int radius) {
		
		layoutManager = new ADateLayoutManager(aVisualizer, x, y, radius);
		visualizer = aVisualizer;
	}

	public util.models.ListenableVector<BoundedShape> display(PropertyListenerRegisterer bean) {
		if (bean instanceof ASimplifiedBeanBuffer) {
			ObservableDate observableDate = new ObservableDate();
			ASimplifiedBeanDate date = (ASimplifiedBeanDate) ((ASimplifiedBeanBuffer)bean).getBean();
			observableDate.setDate(date.getDate());
			return layoutManager.display(observableDate);
		}
		return null;
	}

	public util.models.ListenableVector<AttributedShape> constructPseudoCode() {
		return layoutManager.constructPseudoCode();
	}

	public VestigalListenableVector<AttributedShape> getPseudoCode() {
		return layoutManager.getPseudoCode();
	}

	public int getPseudoCodeMarker() {
		return layoutManager.getPseudoCodeMarker();
	}

	public void update(PropertyChangeEvent event) {
		Object newVal = event.getNewValue();
		if (newVal instanceof Date) {

			ObservableDate observableDate = new ObservableDate();
			observableDate.setDate((Date)newVal);
			layoutManager.update(observableDate);
		}
	}

	public Component displayInPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}
	public PropertyListenerRegisterer createBuffer() {
		return new ASimplifiedBeanBuffer(visualizer);
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

	public ListenableShapeVector getContainingShapes(
			PropertyListenerRegisterer data) {
		// TODO Auto-generated method stub
		return null;
	}

//	public Translator<BeanEventGenerator, SimpleShape> getDataToShapeTranslator() {
//		return dataToShapeTranslator;
//	}
//
//	public void setDataToShapeTranslator(
//			Translator<BeanEventGenerator, SimpleShape> dataToShapeTranslator) {
//		this.dataToShapeTranslator = dataToShapeTranslator;
//	}

}
