package yaas.visualizers.date;

import java.util.Observable;
import java.util.Observer;

import shapes.AttributedShape;
import shapes.FlexibleShape;
import util.models.PropertyListenerRegistrar;

import yaas.AVisualizationtBasedVisualizer;
import yaas.AVisualizer;
import yaas.VisualizationBasedVisualizer;
import yaas.controller.ButtonPressTrapper;



public class ADateVisualizer extends
		AVisualizationtBasedVisualizer<Observer, ObservableDate> implements
		VisualizationBasedVisualizer<Observer, ObservableDate>, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 553505402733826775L;

	public ADateVisualizer() {
		super();
		animationPauseTime = 10;

//		layoutManager = new ADateLayoutManager(this, 100, 100, 25);
		setRootBuffer (this.createBuffer());
	}
//	public PropertyListenerRegisterer getRootBuffer() {
//		if (beanBuffer == null)
//			beanBuffer = createBuffer();
//		return beanBuffer;
//////		return rootBuffer;
////		if (rootBuffers.size() == 0) 
////			rootBuffers.add(firstLayoutManager.createBuffer());
//////		}	else
////				return rootBuffers.get(0);
//	}

	public int theStepCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void update(Observable date, Object arg1) {
		
		((ADateLayoutManager) firstLayoutManager).update((ObservableDate) arg1);
	}

//	@Override
	protected ObservableDate createBuffer() {
		return new ABufferDate(this);
	}

	public boolean removeElement(FlexibleShape c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		// TODO Auto-generated method stub
		return null;
	}

//	public void addReplayMethodListener(Observer anObserver) {
//		getRootBuffer().addObserver(anObserver);
//		
//	}

	@Override
	public void addObserver(ObservableDate anObservable, Observer anObserver) {
		anObservable.addObserver(anObserver);
		
	}

	
}
