package yaas.commands.vector;

import util.models.ListenableVector;
import yaas.buffers.vector.ALinearBuffer;
import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;

public class ACopyElementFromUserObjectCommand<ElementType> implements Command{
	
	
	private ListenableVector<ElementType> buffer;
	private ElementType oldVal;
	int toPosition;
	
	public ACopyElementFromUserObjectCommand(ListenableVector<ElementType> buf, int aToPosition) {
		buffer = buf;
		toPosition = aToPosition;
	}
	public Object execute() {
		oldVal = (ElementType) buffer.get(toPosition);
		buffer.copyFromUserObject(toPosition);
		return null;
	}
	public void undo() {
		buffer.set(toPosition, oldVal);
	}
	public Object getObject(){
		
		return null;
	}
//	public int getToPosition(){
//		
//		return toPosition;
//	}
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
