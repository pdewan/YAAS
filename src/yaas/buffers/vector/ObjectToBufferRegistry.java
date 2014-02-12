package yaas.buffers.vector;

import util.models.Hashcodetable;
import util.models.ListenableVector;

public interface ObjectToBufferRegistry<ObservableDataType> {
	
	public ObservableDataType getBuffer(ObservableDataType anObject);
	public ObservableDataType putBuffer(ObservableDataType anObject, ObservableDataType aBuffer) ;

}
