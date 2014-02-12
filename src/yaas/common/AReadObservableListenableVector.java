package yaas.common;

@SuppressWarnings("rawtypes")
public class AReadObservableListenableVector<ElementType> extends
		AListenableVector<ElementType> {

	private static final long serialVersionUID = 6796575314019329929L;

	public AReadObservableListenableVector(){
		initVectorChangeSupport();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized ElementType get(int pos) {
		ElementType retVal = super.get(pos);

		((AReadObservableVectorChangeSupport)vectorChangeSupport).elementRead(retVal, pos);
		return retVal;
	}
	
	@SuppressWarnings("unchecked")
	public void initVectorChangeSupport(){
		vectorChangeSupport = new AReadObservableVectorChangeSupport(this);
	}
}
