package yaas.trappers;

public interface EventTrapper<ObservingClass, Observable> extends
		EventGenerator<ObservingClass, Observable>,
		EventCatcher<Observable> {

}
