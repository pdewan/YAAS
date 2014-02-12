package yaas.commands.vector;

import java.util.ArrayList;
import java.util.List;

import util.models.ListenableVector;
import yaas.buffers.vector.ALinearBuffer;
import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;

public class AClearCommand<ElementType>  implements Command {

	private ListenableVector<ElementType> buffer;
	private ElementType obj;
	List<ElementType> previousElements = new ArrayList();

	public AClearCommand(ListenableVector<ElementType> theBuffer) {
		buffer = theBuffer;
		previousElements.addAll(buffer);
		
	}

	public Object execute() {
		
		buffer.clear();
		return null;
	}

	public void undo() {
		buffer.addAll(previousElements);
//		buffer.insertElementAt(obj, position);
	}

	public Object getObject() {

		return previousElements;
	}

//	public int getPosition() {
//
//		return position;
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
