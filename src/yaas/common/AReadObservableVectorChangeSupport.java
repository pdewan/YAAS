package yaas.common;

import util.models.VectorChangeEvent;
import util.models.VectorChangeSupport;
import util.models.VectorListener;

public class AReadObservableVectorChangeSupport<ElementType> extends
		VectorChangeSupport<ElementType> {

	private static final long serialVersionUID = -4952196102928216285L;

	public AReadObservableVectorChangeSupport(ElementType o){
		super(o);
	}
	
	public void addVectorMethodsListener(VectorMethodsListenerWithReads<ElementType> vectorListener) {
		super.addVectorMethodsListener(vectorListener);
	}
	
	public void elementRead(ElementType element, Integer pos) {

		for (int i = 0; i < listeners.size(); i++)
			((VectorListener) listeners.elementAt(i))
					.updateVector(new VectorChangeEvent(changeable,
							VectorChangeEvent.UndefEvent, pos, element,
							element, changeableCopy.size()));
		initTransients();
		for (int i = 0; i < transientListeners.size(); i++)
			((VectorListener) transientListeners.elementAt(i))
					.updateVector(new VectorChangeEvent(changeable,
							VectorChangeEvent.UndefEvent, pos, element,
							element, changeableCopy.size()));

		notifyElementRead(element, pos);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void notifyElementRead(ElementType element, int pos) {
		initTransients();
		for (int i = 0; i < methodsListeners.size(); i++) {
			((VectorMethodsListenerWithReads) methodsListeners.elementAt(i))
					.elementRead(changeable, element, pos);
		}
		for (int i = 0; i < transientMethodsListeners.size(); i++) {
			((VectorMethodsListenerWithReads) transientMethodsListeners.elementAt(i))
					.elementRead(changeable, element, pos);
		}

	}
}
