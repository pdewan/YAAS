package yaas.common;


import util.models.VectorMethodsListener;
import yaas.trappers.*;

public class AListenableVector<ElementType> extends
		util.models.AListenableVector<ElementType>
		implements
		VestigalListenableVector<ElementType>,
		EventGenerator<util.models.VectorMethodsListener<ElementType>, VestigalListenableVector<ElementType>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2780834614415774024L;

	@SuppressWarnings("unchecked")
	public void addListener(
			EventTrapper<VectorMethodsListener<ElementType>, VestigalListenableVector<ElementType>> observer)
			throws Exception {
		if (!(observer instanceof VectorMethodsListener))
			throw new Exception("Ill Defined Trapper: The trapper "
					+ observer.toString()
					+ "must be an instance of an observer and an observable");
		this.addVectorMethodsListener((VectorMethodsListener<ElementType>) observer);

	}

	@SuppressWarnings("unchecked")
	public void removeListener(
			EventTrapper<VectorMethodsListener<ElementType>, VestigalListenableVector<ElementType>> observer) {
		if (observer instanceof VectorMethodsListener) {
			this.removeVectorMethodsListener((VectorMethodsListener<ElementType>) observer);
			
		}

	}

}
