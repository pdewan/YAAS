package yaas.visualizers.bean.date;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

import yaas.trappers.EventTrapper;
import yaas.visualizers.bean.BeanEventGenerator;


public class ABeanDate implements BeanEventGenerator {

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	private Date date;
	
	public ABeanDate(){
		date = new Date();
	}

	public void addListener(
			EventTrapper<PropertyChangeListener, BeanEventGenerator> observer)
			throws Exception {
		if (!(observer instanceof PropertyChangeListener)) {
			throw new Exception("Ill Defined Trapper: The trapper "
					+ observer.toString()
					+ "must be an instance of a PropertyChangeListener");
		}
		propertyChangeSupport
				.addPropertyChangeListener((PropertyChangeListener) observer);

	}

	public void removeListener(
			EventTrapper<PropertyChangeListener, BeanEventGenerator> observer) {
		if (observer instanceof PropertyChangeListener) {
			propertyChangeSupport
					.removePropertyChangeListener((PropertyChangeListener) observer);
		}
	}

	public void setDate(Date date) {

		Date oldVal = this.date;
		this.date = date;
		propertyChangeSupport.firePropertyChange("setDate", oldVal, this.date);
	}
	public Date getDate() {

		return date;
	}

}
