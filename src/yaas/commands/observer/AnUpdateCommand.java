package yaas.commands.observer;

import java.lang.reflect.Method;

import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;

public class AnUpdateCommand implements Command {

	private Object target;
	private Method execute, undo;
	private Object[] exeParams, undoParams;

	public AnUpdateCommand(Object target, Method execute, Object[] exeParams,
			Method undo, Object[] undoParams) {
		this.target = target;
		this.execute = execute;
		this.exeParams = exeParams;

		this.undo = undo;
		this.undoParams = undoParams;
	}
	public Object execute() {
		try {

			execute.invoke(target, exeParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	public void undo() {
		try {
			undo.invoke(target, undoParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
