package yaas.buffers.vector;

import util.models.Hashcodetable;
import util.models.ListenableVector;

public class AnObjectToBufferRegistry<ObservableDataType> 
		implements ObjectToBufferRegistry<ObservableDataType>  {
	Hashcodetable<ObservableDataType , ObservableDataType > objectToBuffer = new Hashcodetable();
	
	public ObservableDataType getBuffer(ObservableDataType anObject) {
		return objectToBuffer.get(anObject);
	}
	
	public ObservableDataType putBuffer(ObservableDataType anObject, ObservableDataType aBuffer) {
		return objectToBuffer.put(anObject, aBuffer);
	}

}
