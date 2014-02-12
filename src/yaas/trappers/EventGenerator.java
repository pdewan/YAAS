package yaas.trappers;

/**
 * 
 * @author William
 *
 * @param <ElementType> The type of data that an event is generated for
 * 
 * This empty interface marks the class as being able to produce events.
 * 
 * This class should be paired with an EventCatcher. The programmer is
 * responsible for guaranteeing the coupling between event generators
 * and event catchers, i.e. if one is Observable the other should be an 
 * Observer.
 */
public interface EventGenerator<ObservingClass, Observable> {

	public void addListener(EventTrapper<ObservingClass, Observable> observer) throws Exception;
	public void removeListener(EventTrapper<ObservingClass, Observable> observer);
}
