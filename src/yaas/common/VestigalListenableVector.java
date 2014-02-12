package yaas.common;

import yaas.trappers.*;

public interface VestigalListenableVector<ElementType>
		extends
		util.models.ListenableVector<ElementType>,
		EventGenerator<util.models.VectorMethodsListener<ElementType>, VestigalListenableVector<ElementType>> {

}
