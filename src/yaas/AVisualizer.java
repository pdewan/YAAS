package yaas;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bus.uigen.attributes.AttributeNames;
import bus.uigen.shapes.AListenableShapeVector;
import bus.uigen.shapes.AStackOptimizedListenableShapeVector;
import bus.uigen.shapes.AnAddressSpaceTransformingListenableShapeVector;
import bus.uigen.shapes.ListenableShapeVector;
import shapes.AttributedShape;
import shapes.BoundedShape;
import util.annotations.Visible;
import util.misc.AnObjectColorManager;
import util.misc.ObjectColorManager;
import util.models.AListenableVector;
import util.models.Hashcodetable;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import util.trace.Tracer;
import yaas.buffers.vector.AnObjectToBufferRegistry;
import yaas.buffers.vector.ObjectToBufferRegistry;
import yaas.commands.AHistoryUndoer;
import yaas.commands.CommandHistory;
import yaas.controller.AController;
import yaas.controller.ButtonPressTrapper;
import yaas.controller.Control;
import yaas.controller.Controller;
import yaas.layout.CompositeLayoutManager;
import yaas.layout.DefaultLayoutManager;
import yaas.layout.LayoutManager;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.trappers.ATrapperChainSupporter;
import yaas.trappers.EventGenerator;
import yaas.trappers.EventTrapper;
import yaas.trappers.TrapperChainSupporter;
import yaas.visualization.code.ACodeLayoutManager;
import yaas.visualization.code.AMethodCodeVisualization;
import yaas.visualization.code.AMethodsCodeVisualization;
import yaas.visualization.code.CodeLayoutManager;
import yaas.visualization.code.MethodCodeVisualization;
import yaas.visualization.code.MethodsCodeVisualization;
import yaas.visualizers.bean.ASimplifiedBeanBuffer;

@SuppressWarnings("unchecked")
public abstract class AVisualizer<ObserverDataType, ObservableDataType /*extends EventGenerator<Observer, ObservableDataType>*/>
//		extends AListenableVector<SimpleShape> 
		implements
		Visualizer<ObserverDataType, ObservableDataType> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1701393485380127014L;
	CompositeLayoutManager compositeLayoutManager;

	protected int internalIndex, collectionIndex;
	protected int animationPauseTime, animationStep;
	protected boolean canProceed;
	protected ObservableDataType input;
	protected boolean showCode;
	protected LayoutManager<ObservableDataType> firstLayoutManager; // kludge for now
//	protected Object rootObject;
	
	ObjectColorManager objectColorManager = new AnObjectColorManager();

	protected HashMap<Integer, ObservableDataType> bufferData = new HashMap<Integer, ObservableDataType>();
	protected HashMap<Integer, ObservableDataType> originalData = new HashMap<Integer, ObservableDataType>();
	
//	protected Hashcodetable<ObservableDataType, ObservableDataType> rootObjectToRoot

	protected Control controller = new AController<ObservableDataType>();
	protected CommandHistory ui;
	boolean showPseudoCode = false;
	boolean showData = true;
	boolean showControls = true;
	CodeLayoutManager codeLayoutManager;
	
	protected ObjectToBufferRegistry<ObservableDataType> objectToBufferRegistry = new AnObjectToBufferRegistry();

	private TrapperChainSupporter<ObserverDataType, ObservableDataType> trapperChain;
//	protected ShapeBasedLayoutManager<ObservableDataType> layoutManager = new DefaultLayoutManager<Observer, ObservableDataType>(
//			this);
//	protected LayoutManager layoutManager = new DefaultLayoutManager<Observer, ObservableDataType>(
//			this);
	
//	protected LayoutManager layoutManager;
//	protected ObservableDataType rootBuffer;
	List<ObservableDataType> rootBuffers = new ArrayList();
//	protected ObservableDataType rootObject;
//	protected ListenableVector<AttributedShape> visualization = new AListenableVector(); 
	protected MethodsCodeVisualization codeVisualization = new AMethodsCodeVisualization();
	
//	protected Hashcodetable<ObservableDataType, LayoutManager> bufferToLayoutmanager = new Hashcodetable();
	protected Hashcodetable<ObservableDataType, LayoutManager> rootObjectToLayoutManager = new Hashcodetable();

	protected Hashcodetable<ObservableDataType, List<ObserverDataType>> objectToReplayListeners = new Hashcodetable();

	protected CommandHistory commandHistory = new AHistoryUndoer();
//	List<ObserverDataType> replayListeners = new ArrayList();

	//	List<Observer> replayMethodsListener = new ArrayList();
	
	
	//private Observable proxyObservable = new Observable();

	
	/********************* Initialization ********************************/
	protected AVisualizer() {

		// should initialize controller and layoutManager here
		animationPauseTime = 0;
		animationStep = 1;
		internalIndex = 0;
		collectionIndex = 0;
//		AnimationUtil.setAnimationPauseTime(animationPauseTime);
//		AnimationUtil.setAnimationStep(animationStep);
//		rootBuffer = createBuffer();
		shapes = createVisualization();
		codeLayoutManager = new ACodeLayoutManager(this);
		objectColorManager.setSurroundingColors(new Color[] {AttributeNames.CAROLINA_BLUE, Color.BLACK});

	}
	
//	public ObservableDataType getRootBuffer() {
//		return rootBuffer;
//	}
//	

	/**
	 * 
	 * @return: This method must be called by the subclass to initialize the
	 *          buffer. The buffer must be an instance of Buffer, an instance of
	 *          ObservableDataType, and an instance of Observer. The required
	 *          methods for the Buffer interface are in the class ABuffer, which
	 *          should be delegated.
	 * 
	 *          The buffer must implement add and remove listener methods as
	 *          well as an update method. If these are not implemented correctly
	 *          then events will not ultimately reach ACollectionVisualizer.
	 */
//	protected abstract ObservableDataType createBuffer();

	/**
	 * 
	 * @return This method must be called by the subclass to initialize the
	 *         button trapper. The trapper must be an instance of
	 *         ButtonPressTrapper.
	 */
	public abstract ButtonPressTrapper initializeButtonPressTrapper();

	@SuppressWarnings("rawtypes")
	protected ObservableDataType setBufferData(ObservableDataType data) {
		ObservableDataType rootBuffer = getLayoutManagerOfRootObject(data).createBuffer();
		((Buffer) rootBuffer).setBufferData(data);
		objectToBufferRegistry.putBuffer(data, rootBuffer);

		rootBuffers.add(rootBuffer);
//		addReplayMethodListenersToBuffer(rootBuffer);
		
		return rootBuffer;
	}

	/******************
	 * Visualization
	 * 
	 * @throws Exception
	 **************************/

	@SuppressWarnings("rawtypes")
	public synchronized void visualize(ObservableDataType data, LayoutManager aLayoutManager)
			/*throws Exception*/ {
		if (firstLayoutManager == null)
			firstLayoutManager = aLayoutManager;

		controller.init(this);
//		rootObject = data;
//		if (!(buffer instanceof EventTrapper)) {
//			throw new Exception(
//					"Buffer Initialization Error: Buffer must be an instance of EventTrapper");
//		}
		if (rootObjectToLayoutManager.get(data) != null) {
			Tracer.error("Object:" + data + " visualized twice. Ignoring second visualization.");
			return;
		}
		rootObjectToLayoutManager.put(data, aLayoutManager);

		ObservableDataType rootBuffer =	this.setBufferData(data);
//		bufferToLayoutmanager.put(rootBuffer, aLayoutManager);
		addReplayMethodListenersToBuffer(rootBuffer);
//		objectToBufferRegistry.putBuffer(data, rootBuffer);

		originalData.put(collectionIndex, data);
//		if (firstLayoutManager == null)
//			firstLayoutManager = aLayoutManager;
		
		try {

		if (null == trapperChain) {
			trapperChain = new ATrapperChainSupporter<ObserverDataType, ObservableDataType>(
					(EventGenerator<ObserverDataType, ObservableDataType>) rootBuffer);
		}
		} catch (Exception e) {
			
		}
		

		((Buffer) rootBuffer).getCommandHistory().getCommands()
				.addVectorMethodsListener((Controller) getController());
		ui = ((Buffer) rootBuffer).getCommandHistory();

		visualizeBuffer(rootBuffer);
		((Buffer) rootBuffer).putBufferThread(this, controller);
	}
//	public ObservableDataType getRootObject() {
//		return rootObject;
//	}
//
//	public void setRootObject(ObservableDataType rootObject) {
//		this.rootObject = rootObject;
//	}

	protected abstract  void visualizeBuffer(ObservableDataType vector) ;

//	protected synchronized void visualizeBuffer(ObservableDataType vector) {
//		bufferData.put(collectionIndex, vector);
//
//		this.addAll(layoutManager.display(vector));
//		collectionIndex++;
//	}

	/********************* Thread Management ********************************/
	public void changeAnimationPauseTime(int newTime) {
		animationPauseTime = newTime;
	}

	public synchronized void waitForNextBufferThreadStep() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void notifyVisualizer() {
		this.notify();
	}

	public synchronized void setCanProceed(boolean b) {
		canProceed = b;
	}

	@util.annotations.Visible(false)
	public synchronized boolean getCanProceed() {
		return canProceed;
	}

	@util.annotations.Visible(false)
	public int getAnimationPauseTime() {
		return animationPauseTime;
	}

	@util.annotations.Visible(false)
	public void setAnimationPauseTime(int newVal) {
		animationPauseTime = newVal;
	}

	/************************** Access Methods ***************************************/
	@util.annotations.Visible(false)
	public HashMap<Integer, ObservableDataType> getOriginalData() {
		return originalData;
	}

	@util.annotations.Visible(false)
	public ObservableDataType getRootBuffer() {
//		return rootBuffer;
		if (rootBuffers.size() == 0) 
			rootBuffers.add(firstLayoutManager.createBuffer());
//		}	else
				return rootBuffers.get(0);
	}
	public void setRootBuffer(ObservableDataType newVal) {
//		rootBuffer = newVal;
		if (rootBuffers.size() == 0)
		rootBuffers.add(newVal);
		else
			rootBuffers.set(0,  newVal);
	}
	@util.annotations.Visible(false)
	public List<ObservableDataType> getRootBuffers() {
		return rootBuffers;
	}

	@util.annotations.Visible(false)
	public Control getController() {
		return controller;
	}
	
//	@util.annotations.Visible(false)
//	public ListenableVector<SimpleShape> getShapes() {
//		return this;
//	}
	
//	public Object getVisualization() {
//		return visualization;
//	}
	
	public Object getVisualization() {
		return shapes;
	}


	@util.annotations.Visible(false)
	public CommandHistory getUndoer() {

		return ui;
	}

	public void addTrapper(EventTrapper<ObserverDataType, ObservableDataType> trapper)
			throws Exception {
		if (null != trapperChain) {
			trapperChain.addTrapper(trapper);
		} else {
			throw new Exception(
					"Data must be visualized before trappers can be added");
		}
	}
	
//	public abstract void addReplayMethodListener(Observer anObserver);

	public void removeTrapper(EventTrapper<ObserverDataType, ObservableDataType> trapper)
			throws Exception {
		if (null != trapperChain) {
			trapperChain.removeTrapper(trapper);
		}

	}
//	@Visible(false)
	public LayoutManager<ObservableDataType>  getLayoutManagerOfBuffer(ObservableDataType aBuffer) {
		ObservableDataType rootBuffer = getRootBuffer(aBuffer);
		ObservableDataType rootObject = (ObservableDataType) ((Buffer) rootBuffer).getBufferData();
		return rootObjectToLayoutManager.get(rootObject);
//		return bufferToLayoutmanager.get(getRootBuffer(aBuffer));
	}
	public LayoutManager<ObservableDataType> getLayoutManagerOfRootObject(ObservableDataType aRootObject) {
		return rootObjectToLayoutManager.get(aRootObject);
		
		//		return getLayoutManagerOfBuffer(objectToBufferRegistry.getBuffer(aRootObject));
	}
	public void setLayoutManagerOfRootObject (ObservableDataType aRootObject, LayoutManager<ObservableDataType>  newVal) {
		rootObjectToLayoutManager.put(aRootObject, newVal);
//		bufferToLayoutmanager.put(objectToBufferRegistry.getBuffer(aRootObject), newVal);
		if (firstLayoutManager == null)
			firstLayoutManager = newVal;
	}
	public Component getDataPanel(ObservableDataType aRootObject) {
		return getLayoutManagerOfRootObject(aRootObject).displayInPanel();
	}
	public ListenableVector<AttributedShape> getPseudoCode() {
		return firstLayoutManager.getPseudoCode();
	}
	public boolean getShowPsuedoCode() {
		return showPseudoCode;
	}
	public void setShowPseudoCode (boolean newVal) {
		showPseudoCode = newVal;
	}
	
	public boolean getShowData() {
		return showData;
	}
	public void setShowData (boolean newVal) {
		showData = newVal;
	}
	
	public boolean getShowControls() {
		return showControls;
	}
	public void setShowControls (boolean newVal) {
		showControls = newVal;
	}

	public CommandHistory getCommandHistory() {
		return commandHistory;
	}

	public void setCommandHistory(CommandHistory commandHistory) {
		this.commandHistory = commandHistory;
	}

	public ObjectToBufferRegistry getObjectToBufferRegistry() {
		return objectToBufferRegistry;
	}

	public void setObjectToBufferRegistry(
			ObjectToBufferRegistry objectToBufferRegistry) {
		this.objectToBufferRegistry = objectToBufferRegistry;
	}
	public MethodsCodeVisualization getMethodsCodeVisualization() {
		return codeVisualization;
	}

	public void setMethodsCodeVisualization(MethodsCodeVisualization codeVisualization) {
		this.codeVisualization = codeVisualization;
	}

	public boolean isShowCode() {
		return showCode;
	}

	public void setShowCode(boolean showCode) {
		this.showCode = showCode;
	}

	public CodeLayoutManager getCodeLayoutManager() {
		return codeLayoutManager;
	}

	public void setCodeLayoutManager(CodeLayoutManager codeLayoutManager) {
		this.codeLayoutManager = codeLayoutManager;
	}

	public ObjectColorManager getObjectColorManager() {
		return objectColorManager;
	}

	public void setObjectColorManager(ObjectColorManager objectColorManager) {
		this.objectColorManager = objectColorManager;
	}
//	ListenableVector<BoundedShape> shapes = new AListenableVector();
//	Visualization shapes = new AListenableVector();
//	protected Object shapes = new AVisualization();
	protected Object shapes;;



//	public ListenableVector<BoundedShape> getShapes() {
//		return shapes;
//	}
//	public void setShapes(ListenableVector<BoundedShape> newVal) {
//		shapes = newVal;
//	}
	
	protected Visualization createVisualization() {
		return  new AVisualization();
	}

	public Object getVisualization1() {
		return shapes;
	}
	public void setVisualization(Object newVal) {
		shapes = newVal;
	}
	
	public ListenableShapeVector createShapeVector() {
//		return new AListenableShapeVector();
//		return new AStackOptimizedListenableShapeVector();
		return new AnAddressSpaceTransformingListenableShapeVector();


	}
	
	public abstract void addObserver (ObservableDataType anObservable, ObserverDataType anObserver);

//	public void addReplayMethodListener(ObserverDataType anObserver) {
//		((ASimplifiedBeanBuffer) getRootBuffer()).addPropertyChangeListener(anObserver);
//		
//	}
	
	public ObservableDataType getRootBuffer(ObservableDataType aChildBuffer) {
		return aChildBuffer;
	}
	
//	public void addReplayMethodListener(ObservableDataType anObservable, ObserverDataType anObserver) {
//	//((ASimplifiedBeanBuffer) getRootBuffer()).addPropertyChangeListener(anObserver);
////		addObserver(rootBuffer, anObserver);
////		getReplayListenersOfBuffer(rootBuffer).add(anObserver);
////		for (ObservableDataType rootBuffer: getRootBuffers()) {
//////		addObserver(getRootBuffer(), anObserver);
////			addObserver(rootBuffer, anObserver);
////		}
////		replayListeners.add(anObserver);
//	
//	}
	public void addReplayMethodListenerOfObjectTree(ObservableDataType anObservable, ObserverDataType anObserver) {
		//((ASimplifiedBeanBuffer) getRootBuffer()).addPropertyChangeListener(anObserver);
			
//			for (ObservableDataType rootBuffer: getRootBuffers()) {
////			addObserver(getRootBuffer(), anObserver);
//				addObserver(rootBuffer, anObserver);
//			}
//			replayListeners.add(anObserver);
//		ObservableDataType rootBuffer = getBuffer(anObservable);
//		addObserver(rootBuffer, anObserver);
		getReplayListenersOfObject(anObservable).add(anObserver);
		
		}
	
	public void addReplayMethodListenersToBuffer(ObservableDataType aBuffer) {
//		ObservableDataType rootBuffer = getRootBuffer(anObservable);
//		ObservableDataType originalRoot = (ObservableDataType) ((Buffer) rootBuffer).getBufferData();
//		List<ObserverDataType> replayListeners = getReplayListenersOfObject(originalRoot);
		List<ObserverDataType> replayListeners = getReplayListenersOfBuffer(aBuffer);
		for (ObserverDataType replayListener:replayListeners)
			addObserver(aBuffer, replayListener );
		
	}
	
	public List<ObserverDataType> getReplayListenersOfBuffer(ObservableDataType aBuffer) {
		ObservableDataType rootBuffer = getRootBuffer(aBuffer);
		ObservableDataType originalRoot = (ObservableDataType) ((Buffer) rootBuffer).getBufferData();
		return getReplayListenersOfObject(originalRoot);

	}

	
//	public void addReplayMethodListenersOfBuffer(ObservableDataType anObservable) {
//		for (ObserverDataType replayListener:replayListeners)
//			addObserver(anObservable, replayListener );
//		
//	}
//	public List<ObserverDataType> getReplayMethodListeners(ObservableDataType anObservable) {
//		return replayListeners;
//	}
	
	public ObservableDataType getBuffer(ObservableDataType anOriginal) {
		return objectToBufferRegistry.getBuffer(anOriginal);
	}

	public List<ObserverDataType> getReplayListenersOfObject(ObservableDataType anOriginal) {
		List<ObserverDataType> retVal = objectToReplayListeners.get(anOriginal);
		if (retVal == null) {
			retVal = new ArrayList();
			objectToReplayListeners.put(anOriginal, retVal);
		}
		return retVal;
		
	}
	
	public LayoutManager getFirstLayoutManager() {
		return firstLayoutManager;
	}
	
	public CompositeLayoutManager getCompositeLayoutManager() {
		return compositeLayoutManager;
	}
	

	public void setCompositeLayoutManager(CompositeLayoutManager newVal) {
		 compositeLayoutManager = newVal;
	}



}
