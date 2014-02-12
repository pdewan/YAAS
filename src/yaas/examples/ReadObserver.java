package yaas.examples;

import yaas.common.AReadObservableListenableVector;
import yaas.common.ReadableObserverTester;

public class ReadObserver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AReadObservableListenableVector<Integer> vec = new AReadObservableListenableVector<Integer>();
		
		vec.add(1);
		vec.add(2);
		
		vec.addVectorMethodsListener(new ReadableObserverTester());
		
		vec.get(0);
		vec.get(1);
	}

}
