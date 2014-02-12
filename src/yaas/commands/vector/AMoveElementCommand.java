package yaas.commands.vector;

import util.models.ListenableVector;
import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;

public class AMoveElementCommand<ElementType> implements Command{
	
	
	private ListenableVector<ElementType> buffer;
	private ElementType newVal, oldVal;
	private int toPosition;
	int fromPosition;
	
	public AMoveElementCommand(ListenableVector<ElementType> buf, int aFromPosition, int pos) {
		buffer = buf;
		toPosition = pos;
		fromPosition = aFromPosition;
	}
	public Object execute() {
		oldVal = buffer.get(toPosition);
		buffer.move(fromPosition, toPosition);
		return null;
	}
	public void undo() {
	
		if (toPosition < fromPosition)
		  buffer.move(toPosition, fromPosition);
		else 
			buffer.move(toPosition, fromPosition);
//		buffer.insertElementAt(newVal, fromPosition);
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
