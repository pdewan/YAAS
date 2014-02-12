package yaas.commands.bean;

import java.beans.PropertyChangeEvent;
import java.lang.reflect.Method;

import yaas.common.Util;

import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;


@SuppressWarnings("rawtypes")
public class APropertyChangeCommand implements Command {

	private String methodName;
	private Object newVal, oldVal, bean;
	private Class param;
	private Class alternateParam;

	public APropertyChangeCommand(Object bean, PropertyChangeEvent event) {
		methodName = event.getPropertyName();
		newVal = event.getNewValue();
		oldVal = event.getOldValue();
		param = newVal.getClass();
		alternateParam = Util.convertWrapperClassToPrimitiveClass(newVal
				.getClass());

		this.bean = bean;
	}

	public Object execute() {

		try {
			Method changeProperty;
			try {
				changeProperty = bean.getClass().getMethod(methodName, param);
			} catch (NoSuchMethodException e) {
				changeProperty = bean.getClass().getMethod(methodName,
						alternateParam);
			}
			changeProperty.invoke(bean, newVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	public void undo() {
		try {
			Method changeProperty;
			try {
				changeProperty = bean.getClass().getMethod(methodName, param);
			} catch (NoSuchMethodException e) {
				changeProperty = bean.getClass().getMethod(methodName,
						alternateParam);
			}
			changeProperty.invoke(bean, oldVal);
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
