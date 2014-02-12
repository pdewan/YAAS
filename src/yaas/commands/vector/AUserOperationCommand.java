package yaas.commands.vector;

import util.models.ListenableVector;
import yaas.buffers.vector.ALinearBuffer;
import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;

public class AUserOperationCommand<ElementType> implements Command{
	
	
	private ListenableVector<ElementType> buffer;
	private Integer position;
	Object userOperation;
	
	public AUserOperationCommand(ListenableVector<ElementType> buf, 
			 Integer aPosition, Object theOperation) {
		buffer = buf;
		position = aPosition;
		userOperation = theOperation;
	}
	public Object execute() {
		
		buffer.userOperation(position, userOperation);
		return null;
	}
	public void undo() {
//		buffer.set(position, oldVal);
	}
	public Object getObject(){
		
		return userOperation;
	}
	public int getPosition(){
		
		return position;
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
