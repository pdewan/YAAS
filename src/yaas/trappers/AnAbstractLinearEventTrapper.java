package yaas.trappers;


import java.util.Collection;
import java.util.List;

import util.models.AListenableVector;
import util.models.VectorMethodsListener;

public class AnAbstractLinearEventTrapper<ElementType> extends
		AListenableVector<ElementType> implements
		VectorMethodsListener<ElementType> {

	private static final long serialVersionUID = 1968067808070681492L;

	public void elementAdded(Object source, ElementType element, int newSize) {
		this.add(element);

	}

	public void elementChanged(Object source, ElementType element, int pos) {
		this.set(pos, element);

	}

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {
		this.copy(fromIndex, toIndex);

	}

	@SuppressWarnings("unchecked")
	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		this.copy(fromIndex, (List<ElementType>) to, toIndex);

	}

	public void elementInserted(Object source, ElementType element, int pos,
			int newSize) {
		this.insertElementAt(element, pos);

	}

	public void elementMoved(Object source, int fromIndex, int toIndex) {
		this.move(fromIndex, toIndex);

	}

	@SuppressWarnings("unchecked")
	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		this.move(fromIndex, (List<ElementType>) to, toIndex);

	}

	public void elementRemoved(Object source, int pos, int newSize) {
		this.remove(pos);

	}

	public void elementRemoved(Object source, ElementType element, int newSize, int pos) {
		this.remove(element);

	}

	public void elementReplaced(Object source, int fromIndex, int toIndex,
			int newSize) {
		this.replace(fromIndex, toIndex);

	}

	@SuppressWarnings("unchecked")
	public void elementReplaced(Object source, int fromIndex, int newFromSize,
			Object to, int toIndex) {
		this.replace(fromIndex, (List<ElementType>) to, toIndex);

	}

	public void elementSwapped(Object newParam, int index1, int index2) {
		this.swap(index1, index2);

	}

	@SuppressWarnings("unchecked")
	public void elementSwapped(Object source, int index1, Object other,
			int index2) {
		this.swap(index1, (List<ElementType>) other, index2);
	}

	public void elementsAdded(Object source,
			Collection<? extends ElementType> element, int newSize) {
		this.addAll(element);

	}

	public void elementsCleared(Object source) {
		this.clear();

	}

//	public boolean removeElement(ElementType c) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	public void elementCopiedToUserObject(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		
	}

	public void elementCopiedFromUserObject(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		
	}



	public void userObjectChanged(Object source, Object newVal) {
		// TODO Auto-generated method stub
		
	}


	public void elementRead(Object source, ElementType element, Integer pos) {
		// TODO Auto-generated method stub
		
	}

	

	public void tempChanged(Object source, Object newVal) {
		// TODO Auto-generated method stub
		
	}

	public void elementCopiedToTemp(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		
	}

	public void elementCopiedFromTemp(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		
	}

	public void userObjectCopiedToTemp(Object source, Object copiedValue) {
		// TODO Auto-generated method stub
		
	}

	public void tempCopiedToUserObject(Object source, Object copiedValue) {
		// TODO Auto-generated method stub
		
	}

	public void userObjectRead(Object source, Object readValue) {
		// TODO Auto-generated method stub
		
	}

	public void tempRead(Object source, Object readValue) {
		// TODO Auto-generated method stub
		
	}

	

	public void elementCopiedAndInserted(Object source, int fromIndex,
			int toIndex, int newSize) {
		// TODO Auto-generated method stub
		
	}

	public void elementCopiedAndInserted(Object source, int fromIndex,
			int fromNewSize, Object to, int toIndex) {
		// TODO Auto-generated method stub
		
	}

	public void pointerChanged(Object source, Integer pointerValue) {
		// TODO Auto-generated method stub
		
	}

	

	public void pointer2Changed(Object source, Integer pointerValue) {
		// TODO Auto-generated method stub
		
	}



	public void userOperationOccured(Object source, Integer aTargetIndex,
			Object anOperation) {
		// TODO Auto-generated method stub
		
	}
}
