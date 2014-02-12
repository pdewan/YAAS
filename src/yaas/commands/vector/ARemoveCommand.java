package yaas.commands.vector;

import util.models.ListenableVector;
import yaas.buffers.vector.ALinearBuffer;
import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;

public class ARemoveCommand<ElemenType>  implements Command {

	private ListenableVector<ElemenType> buffer;
	private int position;
	private ElemenType obj;

	public ARemoveCommand(ListenableVector<ElemenType> theBuffer, int pos) {
		buffer = theBuffer;
		position = pos;
	}

	public ARemoveCommand(ListenableVector<ElemenType> theBuffer, ElemenType _obj) {
		buffer = theBuffer;
		obj = _obj;
	}

	public Object execute() {
		if (null == obj) {
			obj = (buffer.elementAt(position));
		}
		buffer.remove(obj);
		return null;
	}

	public void undo() {
		buffer.insertElementAt(obj, position);
	}

	public Object getObject() {

		return obj;
	}

	public int getPosition() {

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
