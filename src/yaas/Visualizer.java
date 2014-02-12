package yaas;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;

import bus.uigen.shapes.AListenableShapeVector;
import bus.uigen.shapes.ListenableShapeVector;

import shapes.AttributedShape;
import shapes.BoundedShape;
import util.misc.ObjectColorManager;
import util.models.ListenableVector;
import yaas.buffers.vector.ObjectToBufferRegistry;
import yaas.commands.CommandHistory;
import yaas.controller.ButtonPressTrapper;
import yaas.controller.Control;
import yaas.layout.CompositeLayoutManager;
import yaas.layout.LayoutManager;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.trappers.TrapperChainSupporter;
import yaas.visualization.code.CodeLayoutManager;
import yaas.visualization.code.MethodsCodeVisualization;

public interface Visualizer<ObserverDataType, ObservableDataType /*extends EventGenerator<Observer, ObservableDataType>*/>
//		extends DelegatingVisualizer<Observer, ObservableDataType>/*, ListenableVector<OEShape>*/ {
	extends TrapperChainSupporter<ObserverDataType, ObservableDataType> {
	
	// from original delegating visualizer
	void visualize(ObservableDataType anObservable, LayoutManager aLayoutManager) throws Exception;

	HashMap<Integer, ObservableDataType> getOriginalData();

	void setCanProceed(boolean b);

	boolean getCanProceed();

	void changeAnimationPauseTime(int n);

	void notifyVisualizer();

	void waitForNextBufferThreadStep();

	CommandHistory getUndoer();

	
	ButtonPressTrapper initializeButtonPressTrapper();
//	 void addReplayMethodListeners(ObservableDataType anObservable);

	
	// original methods

	LayoutManager<ObservableDataType>  getLayoutManagerOfBuffer(ObservableDataType aRootBuffer);
	LayoutManager<ObservableDataType>  getLayoutManagerOfRootObject(ObservableDataType aRootObject);

	void setLayoutManagerOfRootObject (ObservableDataType aRootBuffer, LayoutManager<ObservableDataType>  newVal);
	Control getController();
//	ListenableVector<SimpleShape> getShapes();
//	void addReplayMethodListener(ObservableDataType anObservable, ObserverDataType anObserver);
	Component getDataPanel(ObservableDataType aRootBuffer);
	ListenableVector<AttributedShape> getPseudoCode();
	boolean getShowPsuedoCode();
	void setShowPseudoCode (boolean newVal);
	public boolean getShowData();
	public void setShowData (boolean newVal);
	public boolean getShowControls() ;
	public void setShowControls (boolean newVal) ;
	public CommandHistory getCommandHistory();

	public void setCommandHistory(CommandHistory commandHistory) ;
	public ObjectToBufferRegistry getObjectToBufferRegistry() ;

	public void setObjectToBufferRegistry(
			ObjectToBufferRegistry objectToBufferRegistry);
	public MethodsCodeVisualization getMethodsCodeVisualization() ;

	public void setMethodsCodeVisualization(MethodsCodeVisualization codeVisualization) ;
	
	public boolean isShowCode() ;

	public void setShowCode(boolean showCode) ;
	public CodeLayoutManager getCodeLayoutManager() ;

	public void setCodeLayoutManager(CodeLayoutManager codeLayoutManager) ;
	public ObservableDataType getRootBuffer();
	public ObjectColorManager getObjectColorManager();
	public void setObjectColorManager(ObjectColorManager objectColorManager) ;
	public Object getVisualization() ;
	public void setVisualization(Object newVal) ;
	public ListenableShapeVector createShapeVector();
	public List<ObservableDataType> getRootBuffers();
	public ObservableDataType getBuffer(ObservableDataType anOriginal);
	public void addReplayMethodListenerOfObjectTree(ObservableDataType anObservable, ObserverDataType anObserver);
	public void addReplayMethodListenersToBuffer(ObservableDataType aBuffer);
	public LayoutManager<ObservableDataType>  getFirstLayoutManager();
	public CompositeLayoutManager getCompositeLayoutManager() ;
	

	public void setCompositeLayoutManager(CompositeLayoutManager newVal) ;


//	public ListenableVector<BoundedShape> getShapes();
//	public void setShapes(ListenableVector<BoundedShape> newVal);


}
