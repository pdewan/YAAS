package yaas.controller;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import yaas.Visualizer;
import yaas.commands.vector.AChangeElementCommand;
import yaas.commands.vector.ARemoveCommand;
import yaas.commands.vector.ASwapElementCommand;
import yaas.commands.vector.AnAddCommand;
import yaas.commands.vector.AnInsertCommand;
import yaas.common.AListenableVector;
import yaas.common.VestigalListenableVector;
import yaas.trappers.EventTrapper;
import yaas.visualizers.observer.ObservableEventGenerator;
import bus.uigen.OEFrame;


@SuppressWarnings("rawtypes")
public class AController<ElementType> extends Observable implements
		Controller<ElementType>, ObservableEventGenerator {

	private Visualizer visualizer;

	private int pauseTime = 1;
	private int position = 0;
	private boolean continuous;
	private boolean moveForward;
	private VestigalListenableVector<String> commands = new AListenableVector<String>();
	private ButtonPressTrapper buttonPressTrapper;
	private int commandIndex = -1;
	private OEFrame frame;

	// TODO This class should delegate to a stub of vector methods listener
	// instead of
	// implementing al of it
	/***************** Constructor ***********************/
	public void init(Visualizer cv){
		visualizer = cv;
		buttonPressTrapper = visualizer.initializeButtonPressTrapper();
	}

	/******** PROPERTIES *****************************/
	public void setMoveForward(boolean newMoveForward) {
		moveForward = newMoveForward;
	}

	public boolean getMoveForward() {
		return moveForward;
	}

	public void setAnimationPauseTime(int newVal) {
		pauseTime = newVal;
		visualizer.changeAnimationPauseTime(pauseTime);
	}

	public void setListenableVector(int newVal) {
		position = newVal;
	}

	public int getAnimationPauseTime() {
		return pauseTime;
	}

	public int getListenableVector() {
		return position;
	}

	public void setSynchronous(boolean b) {
		continuous = b;
	}

	public boolean getSynchronous() {
		return continuous;
	}

	public VestigalListenableVector<String> getCommands() {

		return commands;
	}

	public void setFrame(OEFrame frame) {
		this.frame = frame;
	}

	/****************** Animation Controls ***************************/

	public synchronized void first() {
		buttonPressTrapper.maybeDiscardFirstButtonPress();
	}

	public synchronized void previous() {
		if (buttonPressTrapper != null)
		buttonPressTrapper.maybeDiscardPreviousButtonPress();
		this.setChanged();
		this.notifyObservers("previous");
	}

	public synchronized void next() {
		buttonPressTrapper.maybeDiscardNextButtonPress();
		this.setChanged();
		this.notifyObservers("next");
	}

	public synchronized void last() {
		buttonPressTrapper.maybeDiscardLastButtonPress();
	}

	/************************** Observer Methods **********************************/

	public void commandExecuted() {
		if (getMoveForward()) {
			commandIndex++;
		} else {
			commandIndex--;
		}
		if (null != frame && commandIndex >= 0) {
			frame.select(commands, commandIndex);
		}
	}
	
	String toString (Object anObject) {
		if (anObject == null) return "null";
		else return anObject.toString();
	}

	public void elementAdded(Object source, Object element, int newSize) {

		if (element instanceof AnAddCommand)
			commands.add("Add "
					+ (toString (((AnAddCommand) element).getObject())));
		else if (element instanceof AnInsertCommand)
			commands.add("Insert "
//					+ ((AnInsertCommand) element).getObject().toString()
					+ (toString (((AnInsertCommand) element).getObject()))
					+ " at position "
					+ ((AnInsertCommand) element).getPosition());
		else if (element instanceof ARemoveCommand)
			commands.add("Remove from "
//					+ ((ARemoveCommand) element).getPosition());
					+ (toString (((ARemoveCommand) element).getObject())));
		else if (element instanceof ASwapElementCommand)
			commands.add("Swap indices "
					+ ((ASwapElementCommand) element).getIndexOne() + " and "
					+ ((ASwapElementCommand) element).getIndexTwo());
		else if (element instanceof AChangeElementCommand)
			commands.add("Change position "
					+ ((AChangeElementCommand) element).getPosition()
					+ " to have value "
					+ ((AChangeElementCommand) element).getObject());
	}

//	public void elementChanged(Object source, Object element, int pos) {
//
//	}

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {

	}

	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {

	}

//	public void elementInserted(Object source, Object element, int pos,
//			int newSize) {
//
//	}

	public void elementMoved(Object source, int fromIndex, int toIndex) {

	}

	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {

	}

	public void elementRemoved(Object source, int pos, int newSize) {

		commands.remove(pos);
	}

//	public void elementRemoved(Object source, Object element, int newSize) {
//
//	}

	public void elementReplaced(Object source, int fromIndex, int toIndex,
			int newSize) {

	}

	public void elementReplaced(Object source, int fromIndex, int newFromSize,
			Object to, int toIndex) {

	}

	public void elementSwapped(Object newParam, int index1, int index2) {

	}

	public void elementSwapped(Object source, int index1, Object other,
			int index2) {

	}

	public void elementsAdded(Object source,
			Collection<? extends ElementType> element, int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementsCleared(Object source) {
		// TODO Auto-generated method stub

	}

	public void addListener(
			EventTrapper<Observer, ObservableEventGenerator> observer)
			throws Exception {
		if (!(observer instanceof Observer)) {
			throw new Exception("Ill Defined Trapper: The trapper "
					+ observer.toString()
					+ "must be an instance of an observer and an observable");
		}
		super.addObserver((Observer) observer);

	}

	public void removeListener(
			EventTrapper<Observer, ObservableEventGenerator> observer) {
		if (observer instanceof Observer) {
			super.deleteObserver((Observer) observer);
		}

	}

	public Visualizer getVisualizer() {
		return visualizer;
	}

//	public void elementAdded(Object source, ElementType element, int newSize) {
//		// TODO Auto-generated method stub
//		
//	}

	public void elementInserted(Object source, ElementType element, int pos,
			int newSize) {
		// TODO Auto-generated method stub
		
	}

	public void elementChanged(Object source, ElementType element, int pos) {
		// TODO Auto-generated method stub
		
	}

	public void elementRemoved(Object source, ElementType element, int newSize, int pos) {
		// TODO Auto-generated method stub
		
	}

	public void elementCopiedToUserObject(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		
	}

	public void elementCopiedFromUserObject(Object source, int fromIndex) {
		// TODO Auto-generated method stub
		
	}

//	public void elementAdded(Object source, ElementType element, int newSize) {
//		// TODO Auto-generated method stub
//		
//	}

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
