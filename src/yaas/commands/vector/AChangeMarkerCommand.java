package yaas.commands.vector;

import util.models.ListenableVector;
import yaas.buffers.vector.ALinearBuffer;
import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;

public class AChangeMarkerCommand<ElementType> implements Command{
	
	
	private ListenableVector<ElementType> buffer;
	private int position;
	Integer oldVal;
	
	public AChangeMarkerCommand(ListenableVector<ElementType> buf, int pos) {
		buffer = buf;
		position = pos;
	}
	public Object execute() {
		
		oldVal = buffer.getPointer();
		buffer.setPointer(position);
		return null;
	}
	public void undo() {
		 buffer.setPointer (oldVal);
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
