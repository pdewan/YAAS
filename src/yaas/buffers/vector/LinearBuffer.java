package yaas.buffers.vector;

import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.Buffer;
import yaas.trappers.EventTrapper;

public interface LinearBuffer<ElementType> extends VectorMethodsListener<ElementType>,
Buffer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>>,
EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>>{

}
