package yaas.buffers.vector;

import java.util.Collection;

import util.models.AListenableVector;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.ABuffer;
import yaas.Buffer;
import yaas.VisualizationBasedVisualizer;
import yaas.Visualizer;
import yaas.commands.CommandHistory;
import yaas.commands.vector.AChangeElementCommand;
import yaas.commands.vector.AChangeMarker2Command;
import yaas.commands.vector.AChangeMarkerCommand;
import yaas.commands.vector.AChangeTempCommand;
import yaas.commands.vector.AChangeUserObjectCommand;
import yaas.commands.vector.AClearCommand;
import yaas.commands.vector.ACopyElementCommand;
import yaas.commands.vector.ACopyElementFromTempCommand;
import yaas.commands.vector.ACopyElementFromUserObjectCommand;
import yaas.commands.vector.ACopyElementToTempCommand;
import yaas.commands.vector.ACopyElementToUserObjectCommand;
import yaas.commands.vector.ACopyInsertElementCommand;
import yaas.commands.vector.ACopyInsertRemoteElementCommand;
import yaas.commands.vector.ACopyRemoteElementCommand;
import yaas.commands.vector.ACopyTempToUserObjectCommand;
import yaas.commands.vector.AMoveElementCommand;
import yaas.commands.vector.AMoveRemoteElementCommand;
import yaas.commands.vector.ASwapRemoteElementCommand;
import yaas.commands.vector.AUserOperationCommand;
import yaas.commands.vector.AnObservableReadElementCommand;
import yaas.commands.vector.ARemoveCommand;
import yaas.commands.vector.ASwapElementCommand;
import yaas.commands.vector.AnAddCommand;
import yaas.commands.vector.AnInsertCommand;
import yaas.controller.Control;
import yaas.trappers.EventTrapper;

public class ALinearBuffer<ElementType> extends AListenableVector<ElementType>
		implements LinearBuffer<ElementType>
//		VectorMethodsListener<ElementType>,
//		Buffer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>>,
//		EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> 

{
	Object maybeTransformSource (Object anElement) {
		Object retVal = visualizer.getObjectToBufferRegistry().getBuffer(anElement);
		if (retVal == null)
			retVal = anElement;
		return retVal;
	   }
	
	protected Buffer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> buffer;
	Visualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> visualizer;

	ElementType maybeTransform(ElementType anElement, Object aParent) {
		return anElement;
	}
//	Object maybeTransformOther(Object anElement) {
//	if (anElement instanceof ListenableVector) {
////		LinearBuffer buffer = listenableVectorToBuffer.get((ListenableVector) anElement);
//		LinearBuffer buffer = (LinearBuffer) visualizer.getObjectToBufferRegistry().getBuffer(anElement);
//
//		if (buffer == null) {
//			return anElement;
////			ListenableVector anElementVector = (ListenableVector) anElement;
////			buffer = createChildBuffer(anElementVector);
//////			buffer.setBufferData(anElementVector);
//		}
//		return buffer;
//	}
//	return anElement;
//}
//	Object maybeTransform(Object anElement) {
//		if (anElement instanceof ListenableVector) {
////			LinearBuffer buffer = listenableVectorToBuffer.get((ListenableVector) anElement);
//			LinearBuffer buffer = (LinearBuffer) visualizer.getObjectToBufferRegistry().getBuffer(anElement);
//
//			if (buffer == null) {
//				ListenableVector anElementVector = (ListenableVector) anElement;
//				buffer = createChildBuffer(anElementVector);
////				buffer.setBufferData(anElementVector);
//			}
//			return buffer;
//		}
//		return anElement;
//	}
	
	
	public ALinearBuffer(
			Visualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> aVisualizer) {
		buffer = new ABuffer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>>(aVisualizer);
		visualizer = aVisualizer;

	}

	public ALinearBuffer(
			ListenableVector<ElementType> data,
			VisualizationBasedVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> visualizer) {
		this(visualizer);
		this.setBufferData(data);

	}

	public void elementAdded(Object source, ElementType element, int newSize) {
		visualizer.getCommandHistory().addCommand(new AnAddCommand<ElementType>(this, maybeTransform(element, source)));

	}

	public void elementChanged(Object source, ElementType element, int pos) {
		visualizer.getCommandHistory().addCommand(new AChangeElementCommand<ElementType>(this, maybeTransform(element, source), pos));

	}

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {
		visualizer.getCommandHistory().addCommand(new ACopyElementCommand<ElementType>(this, fromIndex, toIndex));

	}

	

	public void tempCopiedToUserObject(Object source, Object val) {
		visualizer.getCommandHistory().addCommand(new ACopyTempToUserObjectCommand<ElementType>(this, (ListenableVector) maybeTransformSource(val)));	
	}
	
	public void userObjectCopiedToTemp(Object source, Object val) {
		visualizer.getCommandHistory().addCommand(new ACopyTempToUserObjectCommand<ElementType>(this, (ListenableVector) maybeTransformSource(val)));	
	}
	
	public void elementCopiedToUserObject(Object source, int fromIndex) {
		visualizer.getCommandHistory().addCommand(new ACopyElementToUserObjectCommand<ElementType>(this, fromIndex));	
	}
	
	public void elementCopiedFromTemp(Object source, int toIndex) {
		visualizer.getCommandHistory().addCommand(new ACopyElementFromTempCommand<ElementType>(this, toIndex));	
		
	}
	
	public void elementCopiedToTemp(Object source, int fromIndex) {
		visualizer.getCommandHistory().addCommand(new ACopyElementToTempCommand<ElementType>(this, fromIndex));	
	}
	
	public void elementCopiedFromUserObject(Object source, int toIndex) {
		visualizer.getCommandHistory().addCommand(new ACopyElementFromUserObjectCommand<ElementType>(this, toIndex));	
		
	}

	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		visualizer.getCommandHistory().addCommand(new ACopyRemoteElementCommand(this, fromIndex, (ListenableVector) maybeTransformSource(to), toIndex));


	}

	public void elementInserted(Object source, ElementType element, int pos,
			int newSize) {
		visualizer.getCommandHistory().addCommand(new AnInsertCommand<ElementType>(this, maybeTransform(element, source), pos));

	}

	public void elementMoved(Object source, int fromIndex, int toIndex) {
		visualizer.getCommandHistory().addCommand(new AMoveElementCommand<ElementType>(this, fromIndex, toIndex));


	}
	public void elementCopiedAndInserted(Object source, int fromIndex,
			int toIndex, int newSize) {
		visualizer.getCommandHistory().addCommand(new ACopyInsertElementCommand<ElementType>(this, fromIndex, toIndex));
		
	}

	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		visualizer.getCommandHistory().addCommand(new AMoveRemoteElementCommand(this, fromIndex, (ListenableVector) maybeTransformSource(to), toIndex));

	}
	
	public void elementCopiedAndInserted(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		visualizer.getCommandHistory().addCommand(new ACopyInsertRemoteElementCommand(this, fromIndex, (ListenableVector) maybeTransformSource(to), toIndex));

	}


	public void elementRemoved(Object source, int pos, int newSize) {
		visualizer.getCommandHistory().addCommand(new ARemoveCommand<ElementType>(this, pos));

	}

	public void elementRemoved(Object source, ElementType element, int newSize, int pos) {
		visualizer.getCommandHistory().addCommand(new ARemoveCommand<ElementType>(this, maybeTransform(element, source)));

	}

	public void elementReplaced(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementReplaced(Object source, int fromIndex, int newFromSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementSwapped(Object newParam, int index1, int index2) {
		visualizer.getCommandHistory().addCommand(new ASwapElementCommand(this, index1, index2));

	}

	public void elementSwapped(Object source, int index1, Object other,
			int index2) {
		visualizer.getCommandHistory().addCommand(new ASwapRemoteElementCommand(this, index1, (ListenableVector) maybeTransformSource(other), index2));

	}

//	@SuppressWarnings("rawtypes")
//	public void elementsAdded(Object source, Collection element, int newSize) {
//		// TODO Auto-generated method stub
//
//	}

	public void elementsCleared(Object source) {
		visualizer.getCommandHistory().addCommand(new AClearCommand<ElementType>(this)); // I dont think elements have to be transformed as we are not naming them

	}

	public CommandHistory getCommandHistory() {
		return visualizer.getCommandHistory();
	}

	public void putBufferThread(
			Visualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> v,
			Control control) {
		buffer.putBufferThread(v, control);

	}
	
//	public Object getObject

	public void setBufferData(ListenableVector<ElementType> data) {
		
		buffer.setBufferData(data); // why was this commented out
		this.setName(data.getName());
		this.setUserObject(data.getUserObject());
		this.setTemp(data.getTemp());

		this.removeAllElements();
		data.addVectorMethodsListener(this);
		for (int i = 0; i < data.size(); i++)
			this.add(data.get(i));
//		
//		for(ElementType e: data){
//			this.add(e);
//		}
	}
	private static final long serialVersionUID = -5548423608100622613L;


	public ListenableVector<ElementType> getBufferData() {
		return buffer.getBufferData();
	}

//	public boolean removeElement(ElementType c) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	public void elementsAdded(Object source,
			Collection<? extends ElementType> element, int newSize) {
		// TODO Auto-generated method stub
		
	}

//	public boolean removeElement(ElementType c) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	public void userObjectChanged(Object source, Object newVal) {
		visualizer.getCommandHistory().addCommand(new AChangeUserObjectCommand<ElementType>(this, newVal));

	}
	
	
	public void tempChanged(Object source, Object newVal) {
		visualizer.getCommandHistory().addCommand(new AChangeTempCommand<ElementType>(this, newVal));

	}
	
	

	public void elementRead(Object source, ElementType element, Integer pos) {
		visualizer.getCommandHistory().addCommand(new AnObservableReadElementCommand<ElementType>(this,maybeTransform(element, source),  pos));

		
	}
	
	public void userObjectRead(Object source, Object element) {
		visualizer.getCommandHistory().addCommand(new AnObservableReadElementCommand<ElementType>(this, null,  -1));

		
	}
	
	public void tempRead(Object source, Object element) {
		visualizer.getCommandHistory().addCommand(new AnObservableReadElementCommand<ElementType>(this, null,  -2));

		
	}
	

	public void addListener(
			EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> observer)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void removeListener(
			EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> observer) {
		// TODO Auto-generated method stub
		
	}


	


	

	public void pointerChanged(Object source, Integer pointerValue) {
		visualizer.getCommandHistory().addCommand(new AChangeMarkerCommand(this, pointerValue));

		
	}


	public void pointer2Changed(Object source, Integer pointerValue) {
		visualizer.getCommandHistory().addCommand(new AChangeMarker2Command(this, pointerValue));
		
	}


	public void userOperationOccured(Object source, Integer aTargetIndex,
			Object anOperation) {
		visualizer.getCommandHistory().addCommand(new AUserOperationCommand(this, aTargetIndex, anOperation));
		
	}


	


	


	

	


	


	

	

	


}
