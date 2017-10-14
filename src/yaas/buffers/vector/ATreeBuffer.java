package yaas.buffers.vector;

import java.util.Collection;
import java.util.List;

//import sun.security.krb5.internal.rcache.ReplayCache;
import util.models.Hashcodetable;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import util.trace.Tracer;
import yaas.Buffer;
import yaas.Visualizer;
import yaas.trappers.EventTrapper;
import yaas.visualizers.collection.tree.ATreeVisualizer;

public class ATreeBuffer extends ALinearBuffer<Object>
		implements TreeBuffer
//		VectorMethodsListener<Object>,
//		Buffer<VectorMethodsListener<Object>, ListenableVector<Object>>,
//		EventTrapper<VectorMethodsListener<Object>, ListenableVector<Object>

{
//	Hashcodetable<ListenableVector, LinearBuffer> listenableVectorToBuffer = new Hashcodetable();
//
//	protected Buffer<VectorMethodsListener<Object>, ListenableVector<Object>> buffer;
	
	
	public ATreeBuffer(
			Visualizer<VectorMethodsListener<Object>, ListenableVector<Object>> aVisualizer) {
		super(aVisualizer);
//		buffer = new ABuffer<VectorMethodsListener<Object>, ListenableVector<Object>>(aVisualizer);
		
	}
//	public synchronized void addElement(Object element) {
//		
//		super.addElement(element);
//	}

//	public ATreeBuffer(
//			ListenableVector<Object> data,
//			ShapeBasedVisualizer<VectorMethodsListener<Object>, ListenableVector<Object>> visualizer) {
//		this(visualizer);
//		this.setBufferData(data);
//
//	}
	
	Object maybeTransform(Object anElement, Object aParent) {
		if (anElement instanceof ListenableVector) {
//			LinearBuffer buffer = listenableVectorToBuffer.get((ListenableVector) anElement);
			LinearBuffer buffer = (LinearBuffer) visualizer.getObjectToBufferRegistry().getBuffer(anElement);

			if (buffer == null) {
				ListenableVector anElementVector = (ListenableVector) anElement;
				buffer = createChildBuffer(anElementVector, this);
//				buffer.setBufferData(anElementVector);
			}
			return buffer;
		}
		return anElement;
	}
	
	
	public void addVectorMethodsListeners( Collection<? extends VectorMethodsListener> someListeners) {
		super.addVectorMethodsListeners(someListeners);
		for (Object anElement:this) {
			if (anElement instanceof ATreeBuffer) {
				((ATreeBuffer) anElement).addVectorMethodsListeners(someListeners);
			}
		}
		
	}
	
	public void addVectorMethodsListener( VectorMethodsListener  aListener) {
		super.addVectorMethodsListener(aListener);
		for (Object anElement:this) {
			if (anElement instanceof ATreeBuffer) {
				((ATreeBuffer) anElement).addVectorMethodsListener(aListener);
			}
		}
		
	}

	ATreeBuffer createChildBuffer(ListenableVector<Object> e, ListenableVector parentBuffer) {
		ATreeBuffer childBuffer = new ATreeBuffer(visualizer);
		childBuffer.setBufferData((ListenableVector<Object>)e);
		childBuffer.setParent(parentBuffer);
		visualizer.addReplayMethodListenersToBuffer(childBuffer);
//		listenableVectorToBuffer.put((ListenableVector) e, childBuffer);
		return childBuffer;
	}
	
//	void addaddVectorMethodsListener(List<VectorMethodsListener<Object>> someListeners) {
//		for (VectorMethodsListener aListener:someListeners) {
//			addVectorMethodsListener(aListener);
//		}
//	}
	

	public void setBufferData(ListenableVector<Object> data) {
		
//		listenableVectorToBuffer.put((ListenableVector) data, this);
		visualizer.getObjectToBufferRegistry().putBuffer((ListenableVector) data, this);


		buffer.setBufferData(data);
		this.setName(data.getName());
		this.setUserObject(data.getUserObject());
		this.removeAllElements();
//		data.addVectorMethodsListener(this);
//		List<VectorMethodsListener<Object>> replayMethodsListeners = ((ATreeVisualizer) visualizer).getReplayMethodListeners();
//		if (replayMethodsListeners != null) {
//			addVectorMethodsListeners(replayMethodsListeners); 
//		}
		
		for(Object e: data){
			if (e instanceof ListenableVector) {
				ATreeBuffer childBuffer = createChildBuffer((ListenableVector) e, this);
//				List<VectorMethodsListener<Object>> replayMethodsListeners = ((ATreeVisualizer) visualizer).getReplayMethodListeners();
//				if (replayMethodsListeners != null) {
//					childBuffer.addVectorMethodsListeners(replayMethodsListeners); 
//				}
//				LinearBuffer childBuffer = new ATreeBuffer(visualizer);
//				childBuffer.setBufferData((ListenableVector<Object>)e);
//				listenableVectorToBuffer.put((ListenableVector) e, childBuffer);
//				data.addVectorMethodsListener(childBuffer);
				this.add(childBuffer);
				visualizer.addReplayMethodListenersToBuffer(childBuffer);

			} else
			this.add(e);
		}
//		List<VectorMethodsListener<Object>> replayMethodsListeners = ((ATreeVisualizer) visualizer).getReplayMethodListeners();
//		if (replayMethodsListeners != null) {
//			addVectorMethodsListeners(replayMethodsListeners); 
//		}
		
		data.addVectorMethodsListener(this);

	}
	
	

	


}
