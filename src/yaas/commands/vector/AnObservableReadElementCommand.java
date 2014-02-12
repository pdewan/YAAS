package yaas.commands.vector;

import util.models.ListenableVector;
import yaas.buffers.vector.ALinearBuffer;
import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;

public class AnObservableReadElementCommand<ElementType> implements Command{
	
	
	private ListenableVector<ElementType> buffer;
	Integer lastPosition;
	private int position;
	ElementType valueRead;
	
	public AnObservableReadElementCommand(ListenableVector<ElementType> buf, ElementType value, int pos) {
		buffer = buf;
		position = pos;
		valueRead = value;
//		lastPosition = buffer.lastObservableGetIndex(); // have to do this before execute
	}
	public Object execute() {
		lastPosition = buffer.lastObservableGetIndex(); // have to do this before execute, not in constructor as buffer is not uptdate when command is created

		if (position ==  -1)
			buffer.observableGetUserObject();
		else if (position == -2) {
			buffer.observableGetTemp();
		}
		else
		
		buffer.observableGet(position);
		return null;
	}
	public void undo() {
		
		 if (lastPosition != null && lastPosition < 0)
			buffer.observableGetUserObject();
		else
		buffer.observableGet(lastPosition);
	}
	public Object getObject(){
		
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
