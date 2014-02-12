package yaas.visualizers.bean;


import java.beans.PropertyChangeListener;



import shapes.AttributedShape;
import util.models.PropertyListenerRegisterer;
import yaas.AVisualizationtBasedVisualizer;
import yaas.AVisualizer;
import yaas.Buffer;
import yaas.VisualizationBasedVisualizer;
import yaas.controller.AGenericButtonPressTrapper;
import yaas.controller.ButtonPressTrapper;
import yaas.layout.LayoutManager;
import yaas.layout.VisualizationBasedLayoutManager;

public class ASimplifiedBeanVisualizer extends
		AVisualizationtBasedVisualizer<PropertyChangeListener, PropertyListenerRegisterer>
		implements
		VisualizationBasedVisualizer<PropertyChangeListener, PropertyListenerRegisterer> {

	private static final long serialVersionUID = 5651052060059133256L;
	private int pauseTimeInMilliseconds = 20;
	private PropertyListenerRegisterer bean;

	public ASimplifiedBeanVisualizer(/*VisualizationBasedLayoutManager<PropertyListenerRegisterer> layoutManager,*/ PropertyListenerRegisterer bean) {
		super();
//		firstLayoutManager = aLayoutManager;
		this.bean = bean;
		((ASimplifiedBeanBuffer) getRootBuffer()).setBean(this.bean);
//		this.layoutManager = layoutManager;
		
	}
//	public synchronized void visualize(PropertyListenerRegisterer data, LayoutManager aLayoutManager) {
//		super.visualize(data, aLayoutManager);
//		this.bean = data;
//		((ASimplifiedBeanBuffer) getRootBuffer()).setBean(this.bean);
//	}
	PropertyListenerRegisterer beanBuffer;
	
	public PropertyListenerRegisterer getRootBuffer() {
		if (beanBuffer == null)
			beanBuffer = createBuffer();
		return beanBuffer;
////		return rootBuffer;
//		if (rootBuffers.size() == 0) 
//			rootBuffers.add(firstLayoutManager.createBuffer());
////		}	else
//				return rootBuffers.get(0);
	}

	@SuppressWarnings("rawtypes")
	protected PropertyListenerRegisterer setBufferData(PropertyListenerRegisterer data) {
//		PropertyListenerRegisterer rootBuffer = createBuffer();
		PropertyListenerRegisterer rootBuffer = getRootBuffer();

		((Buffer) rootBuffer).setBufferData(data);
//		rootBuffers.add(rootBuffer);
		this.addReplayMethodListenersToBuffer(rootBuffer);
		return rootBuffer;
	}

	protected PropertyListenerRegisterer createBuffer() {
		return new ASimplifiedBeanBuffer(this);
	}

	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new AGenericButtonPressTrapper(controller, this,
				pauseTimeInMilliseconds);
	}

//	public void addReplayMethodListener(PropertyChangeListener anObserver) {
//		((ASimplifiedBeanBuffer) getRootBuffer()).addPropertyChangeListener(anObserver);
//		
//	}

	@Override
	public void addObserver(PropertyListenerRegisterer anObservable,
			PropertyChangeListener anObserver) {
		anObservable.addPropertyChangeListener(anObserver);
	}
//	public void addReplayMethodListener(
//			PropertyListenerRegisterer anObservable,
//			PropertyChangeListener anObserver) {
//		super.add
//	}



}
