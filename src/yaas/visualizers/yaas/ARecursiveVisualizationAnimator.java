package yaas.visualizers.yaas;

import java.util.Observer;
import java.util.Observable;

import shapes.AttributedShape;

import yaas.layout.VisualizationBasedLayoutManager;
import yaas.visualizers.observer.AnObserverAnimator;
import yaas.visualizers.observer.ObservableEventGenerator;

public class ARecursiveVisualizationAnimator extends AnObserverAnimator{

	private static final long serialVersionUID = 3304598343198436078L;

	public ARecursiveVisualizationAnimator(
			VisualizationBasedLayoutManager<ObservableEventGenerator> layoutManager,
			ObservableEventGenerator observable) throws Exception {
		super(layoutManager, observable);
		this.visualize(observable, null);
	}

//	@Override
	protected ObservableEventGenerator createBuffer() {
		
		ARecursiveVisualizationBuffer buffer = new ARecursiveVisualizationBuffer(this);
		buffer.setBufferData(originalData.get(0));
		return buffer;
	}

//	public void addReplayMethodListener(Observer anObserver) {
//		((Observable) getRootBuffer()).addObserver(anObserver);
//		
//	}

	@Override
	protected void visualizeBuffer(ObservableEventGenerator vector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addObserver(ObservableEventGenerator anObservable,
			Observer anObserver) {
		((Observable) anObservable).addObserver(anObserver);
		
	}

	
}
