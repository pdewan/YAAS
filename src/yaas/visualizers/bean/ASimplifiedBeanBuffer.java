package yaas.visualizers.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegistrar;
import yaas.ABuffer;
import yaas.Buffer;
import yaas.VisualizationBasedVisualizer;
import yaas.commands.bean.APropertyChangeCommand;
import yaas.trappers.EventTrapper;



public class ASimplifiedBeanBuffer extends
		ABuffer<PropertyChangeListener, PropertyListenerRegistrar> implements
		PropertyChangeListener, PropertyListenerRegistrar,
		Buffer<PropertyChangeListener, PropertyListenerRegistrar>
//		EventTrapper<PropertyChangeListener, BeanEventGenerator>,
//		BeanEventGenerator 
		{

	public ASimplifiedBeanBuffer(
			VisualizationBasedVisualizer<PropertyChangeListener, PropertyListenerRegistrar> visualizer) {
		super(visualizer);
	}

	private PropertyListenerRegistrar copy;
	public void setBean(PropertyListenerRegistrar observedBean) {

		try {
			observedBean.addPropertyChangeListener(this);
			this.copy = observedBean.getClass().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	public void addListener(
//			EventTrapper<PropertyChangeListener, BeanEventGenerator> observer)
//			throws Exception {
//		copy.addListener(observer);
//	}
//	public void removeListener(
//			EventTrapper<PropertyChangeListener, BeanEventGenerator> observer) {
//		copy.removeListener(observer);
//	}
	public void propertyChange(PropertyChangeEvent event) {
		visualizer.getCommandHistory().addCommand(
				new APropertyChangeCommand(copy, event));
	}

	public Object getBean() {
		return copy;
	}
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		copy.addPropertyChangeListener(aListener);
	}
}
