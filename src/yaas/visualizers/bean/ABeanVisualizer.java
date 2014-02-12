package yaas.visualizers.bean;


import java.beans.PropertyChangeListener;

import shapes.AttributedShape;


import yaas.AVisualizationtBasedVisualizer;
import yaas.AVisualizer;
import yaas.VisualizationBasedVisualizer;
import yaas.controller.AGenericButtonPressTrapper;
import yaas.controller.ButtonPressTrapper;
import yaas.layout.VisualizationBasedLayoutManager;

public class ABeanVisualizer extends
		AVisualizationtBasedVisualizer<PropertyChangeListener, BeanEventGenerator>
		implements
		VisualizationBasedVisualizer<PropertyChangeListener, BeanEventGenerator> {

	private static final long serialVersionUID = 5651052060059133256L;
	private int pauseTimeInMilliseconds = 20;
	private BeanEventGenerator bean;

	public ABeanVisualizer(/*VisualizationBasedLayoutManager<BeanEventGenerator> layoutManager,*/ BeanEventGenerator bean) {
		super();
		this.bean = bean;
		((ABeanBuffer) getRootBuffer()).setBean(this.bean);
//		this.layoutManager = layoutManager;
		
	}
	
//	public void setLayoutManager(VisualizationBasedLayoutManager<BeanEventGenerator> newVal) {
//		layoutManager = newVal;
//	}

//	@Override
	protected BeanEventGenerator createBuffer() {
		return new ABeanBuffer(this);
	}

	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new AGenericButtonPressTrapper(controller, this,
				pauseTimeInMilliseconds);
	}

	public void addReplayMethodListener(BeanEventGenerator anObservable, PropertyChangeListener anObserver) {
//		((ABeanBuffer) buffer).addListener(anObserver);
		
	}

	@Override
	public void addObserver(BeanEventGenerator anObservable,
			PropertyChangeListener anObserver) {
//		((ABeanBuffer) anObservable).addListener(anObserver);
	}



}
