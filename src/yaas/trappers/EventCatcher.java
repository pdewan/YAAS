package yaas.trappers;

/**
 * 
 * @author William
 *
 * @param <ElementType> The type of data that an event is generated for
 * 
 * This empty interface marks the class as being able to catch events.
 * 
 * This class should be paired with an EventGenerator. The programmer is
 * responsible for guaranteeing the coupling between event generators
 * and event catchers, i.e. if one is Observable the other should be an 
 * Observer.
 */
public interface EventCatcher<Observable>{

}
