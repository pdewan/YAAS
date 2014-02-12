package yaas.OE;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import util.models.ListenableVector;



public class Sorting {
	private final String[] animations = { "Bubble Sort", "Insertion Sort",
			"Shell Sort", "Merge Sort", "Quick Sort" };

	private HashMap<String, String> stringToMethodNameMap = new HashMap<String, String>();
	private Object data;

	public Sorting(Object data) {
		this.data = data;
		stringToMethodNameMap.put(animations[0], "bubbleSort");
		stringToMethodNameMap.put(animations[1], "insertionSort");
		stringToMethodNameMap.put(animations[2], "shellSort");
		stringToMethodNameMap.put(animations[3], "mergeSort");
		stringToMethodNameMap.put(animations[4], "quickSort");
	}

	public String[] getDynamicCommands() {
		return animations;
	}

	public void invokeDynamicCommand(String commandName) {
		String methodName = stringToMethodNameMap.get(commandName);
		try {
			Method m = yaas.common.Algorithms.class.getMethod(methodName, ListenableVector.class);
			m.invoke(null, data);
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
