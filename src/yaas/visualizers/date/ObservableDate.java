package yaas.visualizers.date;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import yaas.trappers.*;


@SuppressWarnings("deprecation")
public class ObservableDate extends Observable implements
		EventGenerator<Observer, ObservableDate> {
	Date date;

	public ObservableDate() {
		date = new Date();
	}

	public boolean after(Date arg0) {
		return date.after(arg0);
	}

	public boolean before(Date arg0) {
		return date.before(arg0);
	}

	public int getDateAsInt() {
		return date.getDate();
	}

	public Date getDate() {
		return date;
	}

	public int getDay() {
		return date.getDay();
	}

	public int getHours() {
		return date.getHours();
	}

	public int getMinutes() {
		return date.getMinutes();
	}

	public int getMonth() {
		return date.getMonth();
	}

	public int getSeconds() {
		return date.getSeconds();
	}

	public long getTime() {
		return date.getTime();
	}

	public int getTimezoneOffset() {
		return date.getTimezoneOffset();
	}

	public int getYear() {
		return date.getYear();
	}

	public void setDate(int date) {
		this.date.setDate(date);
		this.setChanged();
		this.notifyObservers(this);
	}

	public void setDate(Date date) {
		this.date = date;
		this.setChanged();
		this.notifyObservers(this);

	}

	public void setHours(int hours) {
	}

	public void setMinutes(int minutes) {
	}

	public void setMonth(int month) {
	}

	public void setSeconds(int seconds) {
	}

	public void setTime(long time) {
	}

	public void setYear(int year) {
	}

	public String toGMTString() {
		return date.toGMTString();
	}

	public String toLocaleString() {
		return date.toLocaleString();
	}

	public void addListener(EventTrapper<Observer, ObservableDate> observer)
			throws Exception {
		if (!(observer instanceof Observer)) {
			throw new Exception("Ill Defined Trapper: The trapper "
					+ observer.toString()
					+ "must be an instance of an observer and an observable");
		}
		super.addObserver((Observer) observer);

	}

	public void removeListener(EventTrapper<Observer, ObservableDate> observer) {
		if (observer instanceof Observer) {
			super.deleteObserver((Observer) observer);
		}

	}
}
