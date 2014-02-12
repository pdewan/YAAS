package yaas.commands.vector;

import util.models.ListenableVector;
import yaas.buffers.vector.ALinearBuffer;
import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;

@SuppressWarnings("rawtypes")
public class ASwapRemoteElementCommand<ElementType> implements Command {

	private ListenableVector<ElementType> buffer, otherBuffer;
	private int index1;
	private int index2;

	public ASwapRemoteElementCommand(ListenableVector<ElementType> thebuffer, int indx1, ListenableVector<ElementType> theBuffer2, int indx2) {
		buffer = thebuffer;
		index1 = indx1;
		index2 = indx2;
		otherBuffer = theBuffer2;
	}

	public Object execute() {
		buffer.swap(index1, otherBuffer, index2);
		return null;
	}

	public void undo() {
		execute();
	}

	public int getIndexOne() {

		return index1;
	}

	public int getIndexTwo() {

		return index2;
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

	public Object getObject() {
		// TODO Auto-generated method stub
		return null;
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
