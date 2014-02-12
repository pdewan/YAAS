package yaas.common;

import util.models.VectorMethodsListener;

public interface VectorMethodsListenerWithReads<T> extends VectorMethodsListener<T>{
	void elementRead(Object source, T element, Integer pos);
}
