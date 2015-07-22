package yaas.visualizers.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import util.models.PropertyListenerRegisterer;
import yaas.trappers.EventTrapper;


public abstract class ABeanMethodReplayer implements PropertyChangeListener//,
//		EventTrapper<PropertyChangeListener, BeanEventGenerator>,
//		BeanEventGenerator 
{

	protected PropertyChangeSupport propertyChangeSupport;
	protected PropertyListenerRegisterer bean;

	public ABeanMethodReplayer(PropertyListenerRegisterer bean) {
		this.bean = bean;
		propertyChangeSupport = new PropertyChangeSupport(this.bean);
	}

//	public void addListener(
//			EventTrapper<PropertyChangeListener, BeanEventGenerator> observer)
//			throws Exception {
//
//		bean.addListener(observer);
//	}
//
//	public void removeListener(
//			EventTrapper<PropertyChangeListener, BeanEventGenerator> observer) {
//		bean.removeListener(observer);
//	}

	public abstract void propertyChange(PropertyChangeEvent arg0);
}
