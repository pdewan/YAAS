package yaas.visualizers.bean.date;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import util.models.PropertyListenerRegisterer;
import yaas.common.Util;
import yaas.trappers.EventTrapper;
import yaas.visualizers.bean.ABeanEventTrapper;
import yaas.visualizers.bean.BeanEventGenerator;
import yaas.visualizers.bean.ABeanMethodReplayer;


public class ABeanDateMethodReplayer extends ABeanMethodReplayer implements
		PropertyChangeListener
//		EventTrapper<PropertyChangeListener, BeanEventGenerator>
{

	private ASimplifiedBeanDateLayoutManager layoutManager;

	public ABeanDateMethodReplayer(ASimplifiedBeanDateLayoutManager layoutManager,
			PropertyListenerRegisterer bean) {
		super(bean);
		this.layoutManager = layoutManager;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void propertyChange(PropertyChangeEvent event) {

		String methodName = event.getPropertyName();
		Object newVal = event.getNewValue();
		Class param = newVal.getClass();
		Class alternateParam = Util.convertWrapperClassToPrimitiveClass(newVal
				.getClass());
		try {
			Method changeProperty;
			try {
				changeProperty = bean.getClass().getMethod(methodName, param);

			} catch (NoSuchMethodException e) {
				changeProperty = bean.getClass().getMethod(methodName,
						alternateParam);
			}
			System.out.println("Why invoke this setDate method?");
			changeProperty.invoke(bean, newVal);

			layoutManager.update(event);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
