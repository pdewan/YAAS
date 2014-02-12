package yaas.commands.vector;

import util.models.ListenableVector;
import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;

public class ACopyInsertRemoteElementCommand<ElementType> implements Command{
	
	
	private ListenableVector<ElementType> buffer, remoteBuffer;
	private ElementType newVal, oldVal;
	private int toPosition;
	int fromPosition;
	
	public ACopyInsertRemoteElementCommand(ListenableVector<ElementType> buf, int aFromPosition, ListenableVector<ElementType> aRemoteBuffer, int pos) {
		buffer = buf;
		toPosition = pos;
		fromPosition = aFromPosition;
		remoteBuffer = aRemoteBuffer;
	}
	public Object execute() {
//		oldVal = remoteBuffer.get(toPosition);
		buffer.copyAndInsert(fromPosition, remoteBuffer, toPosition);
		return null;
	}
	public void undo() {
		remoteBuffer.remove(toPosition);
	}
	public Object getObject(){
		
		return newVal;
	}
	public int getToPosition(){
		
		return toPosition;
	}
	public void redo() {
		// TODO Auto-generated method stub
		
	}
	public Command clone(Object arg0, Object[] arg1, uiFrame arg2,
			CommandListener arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	public MethodProxy getMethod() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean getNotUndoablePurgesUndoHistory() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isNoOp() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isUndoable() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isVoid() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setNotUndoablePurgesUndoHistory(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}
