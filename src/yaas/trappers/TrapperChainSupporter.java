package yaas.trappers;

public interface TrapperChainSupporter<Observer, Observable> {
	public void addTrapper(EventTrapper<Observer, Observable> trapper) throws Exception;
	public void removeTrapper(EventTrapper<Observer, Observable> trapper) throws Exception;
}
