package yaas;
// vestigal class

import java.awt.Component;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import bus.uigen.shapes.ListenableShapeVector;
import shapes.AttributedShape;
import shapes.FlexibleShape;
import util.misc.ObjectColorManager;
import util.models.AListenableVector;
import util.models.IndexedElementChecker;
import util.models.ListenableVector;
import util.models.VectorChangeSupport;
import util.models.VectorListener;
import util.models.VectorMethodsListener;
import yaas.buffers.vector.ObjectToBufferRegistry;
import yaas.commands.CommandHistory;
import yaas.controller.ButtonPressTrapper;
import yaas.controller.Control;
import yaas.layout.CompositeLayoutManager;
import yaas.layout.DefaultLayoutManager;
import yaas.layout.LayoutManager;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.trappers.*;
import yaas.visualization.code.CodeLayoutManager;
import yaas.visualization.code.MethodsCodeVisualization;




public class ADelegatingAnimator<Observer, ObservableDataType /*extends EventGenerator<Observer, ObservableDataType>*/>
		implements Visualizer<Observer, ObservableDataType> {

	protected AVisualizer<Observer, ObservableDataType> visualizer;
	private ListenableVector<FlexibleShape> vector = new AListenableVector<FlexibleShape>();

	public ADelegatingAnimator(AVisualizer<Observer, ObservableDataType> visualizer) {
		this.visualizer = visualizer;
	}

	@util.annotations.Visible(false)
	public Control getController() {
		return visualizer.getController();
	}

	@util.annotations.Visible(false)
	public void changeAnimationPauseTime(int newTime) {
		visualizer.changeAnimationPauseTime(newTime);

	}

	@util.annotations.Visible(false)
	public boolean getCanProceed() {
		return visualizer.getCanProceed();
	}

	@util.annotations.Visible(false)
	public CommandHistory getUndoer() {
		return visualizer.getUndoer();
	}

	@util.annotations.Visible(false)
	public void notifyVisualizer() {
		visualizer.notifyVisualizer();

	}

	@util.annotations.Visible(false)
	public HashMap<Integer, ObservableDataType> getOriginalData() {
		return visualizer.getOriginalData();
	}

	@util.annotations.Visible(false)
	public void setCanProceed(boolean b) {
		visualizer.setCanProceed(b);

	}

	@util.annotations.Visible(false)
	public void visualize(ObservableDataType data, LayoutManager aLayoutManager) throws Exception {
		visualizer.visualize(data, null);

	}

	@util.annotations.Visible(false)
	public void waitForNextBufferThreadStep() {
		visualizer.waitForNextBufferThreadStep();

	}

	public ButtonPressTrapper initializeButtonPressTrapper() {
		// TODO Auto-generated method stub
		return visualizer.initializeButtonPressTrapper();
	}

	public void addTrapper(EventTrapper<Observer, ObservableDataType> trapper)
			throws Exception {
		visualizer.addTrapper(trapper);
		
	}

	public void removeTrapper(EventTrapper<Observer, ObservableDataType> trapper)
			throws Exception {
		visualizer.removeTrapper(trapper);
		
	}
	
	public VisualizationBasedLayoutManager getLayoutManager(){
		return new DefaultLayoutManager<Observer, ObservableDataType>(visualizer);
		
	}
//	public void setLayoutManager(VisualizationBasedLayoutManager<ObservableDataType> newVal) {
//		visualizer.setLayoutManager(newVal);
//	}
//	public ListenableVector<SimpleShape> getShapes(){
//		return visualizer.getShapes();
//	}
	/*****************************************************/
	public ListenableVector<FlexibleShape> deepClone() {
		return null;
	}

	public VectorChangeSupport<FlexibleShape> getVectorChangeSupport() {
		return vector.getVectorChangeSupport();
	}

	public void setVectorChangeSupport(VectorChangeSupport<FlexibleShape> arg0) {
		vector.setVectorChangeSupport(arg0);
		
	}

	public void addVectorListener(VectorListener vectorListener) {
		vector.addVectorListener(vectorListener);
		
	}

	public void removeVectorListener(VectorListener vectorListener) {
		vector.removeVectorListener(vectorListener);
	}

	public void addVectorMethodsListener(VectorMethodsListener vectorListener) {
		vector.addVectorMethodsListener(vectorListener);
	}

	public void removeVectorMethodsListener(VectorMethodsListener vectorListener) {
		vector.removeVectorMethodsListener(vectorListener);
	}

	public void addElement(FlexibleShape c) {
		vector.addElement(c);
	}

	public boolean addAll(Collection<? extends FlexibleShape> elements) {
		return vector.addAll(elements);
	}

	public void insertElementAt(FlexibleShape element, int pos) {
		vector.insertElementAt(element, pos);
	}

	public boolean removeElement(FlexibleShape c) {
		return vector.removeElement(c);
	}

	public void removeElementAt(int pos) {
		vector.removeElementAt(pos);
	}

	public void setElementAt(FlexibleShape element, int pos) {
		vector.setElementAt(element, pos);
	}

	public int size() {
		return vector.size();
	}

	public void removeAllElements() {
		vector.removeAllElements();
	}

	public FlexibleShape elementAt(int i) {
		return vector.elementAt(i);
	}

	public IndexedElementChecker<FlexibleShape> getIndexedElementChecker() {
		return vector.getIndexedElementChecker();
	}

	public void setIndexedElementChecker(IndexedElementChecker<FlexibleShape> theChecker) {
		vector.setIndexedElementChecker(theChecker);
	}

	public void open(FlexibleShape element) {
		vector.open(element);
	}

	public boolean preElementAt(int index) {
		return vector.preElementAt(index);
	}

	public Object getUserObject() {
	return vector.getUserObject();
	}

	public void setUserObject(Object newValue) {
		vector.setUserObject(newValue);
	}

	public util.models.ListenableVector getParent() {
		return vector.getParent();
	}

	public void setIsEditable(int index, boolean status) {
		vector.setIsEditable(index, status);
		
	}

	public boolean isEditable(int index) {
		return vector.isEditable(index);
	}

	public boolean isColumnEditable(int index) {
		return vector.isColumnEditable(index);
	}

	public boolean preRemoveElement(FlexibleShape element) {
		return vector.preRemoveElement(element);
	}

	public boolean preRemoveElementAt(int index) {
		return vector.preRemoveElementAt(index);
	}

	public boolean preAddElement(FlexibleShape element) {
		return vector.preAddElement(element);
	}

	public boolean preSetElementAt(FlexibleShape element, int pos) {
	return vector.preSetElementAt(element, pos);
	}

	public boolean preInsertElementAt(FlexibleShape element, int pos) {
	return vector.preInsertElementAt(element, pos);
	}

	public void initSerializedObject() {
		vector.initSerializedObject();
	}

	public void swap(int index1, int index2) {
		vector.swap(index1, index2);
	}

	public void swap(int index1, List<FlexibleShape> other, int index2) {
		vector.swap(index1, other, index2);
	}

	public void move(int fromIndex, int toIndex) {
		vector.move(fromIndex, toIndex);
	}

	public void move(int fromIndex, List<FlexibleShape> to, int toIndex) {
		vector.move(fromIndex, to, toIndex);
	}

	public void copy(int fromIndex, int toIndex) {
		vector.copy(fromIndex, toIndex);
	}

	public void copy(int fromIndex, List<FlexibleShape> to, int toIndex) {
		vector.copy(fromIndex, to, toIndex);
	}

	public void replace(int fromIndex, int toIndex) {
		vector.replace(fromIndex, toIndex);
	}

	public void replace(int fromIndex, List<FlexibleShape> to, int toIndex) {
		vector.replace(fromIndex, to, toIndex);
	}

	public boolean add(FlexibleShape arg0) {
		return vector.add(arg0);
	}

	public void add(int arg0, FlexibleShape arg1) {
		vector.add(arg0,arg1);
	}

	public boolean addAll(int arg0, Collection<? extends FlexibleShape> arg1) {
		return vector.addAll(arg0, arg1);
	}

	public void clear() {
		vector.clear();		
	}

	public boolean contains(Object arg0) {
		return vector.contains(arg0);
	}

	public boolean containsAll(Collection<?> arg0) {
		return vector.containsAll(arg0);
	}

	public FlexibleShape get(int arg0) {
		return vector.get(arg0);
	}

	public int indexOf(Object arg0) {
		return vector.indexOf(arg0);
	}

	public boolean isEmpty() {
		return vector.isEmpty();
	}

	public Iterator<FlexibleShape> iterator() {
		return vector.iterator();
	}

	public int lastIndexOf(Object arg0) {
		return vector.lastIndexOf(arg0);
	}

	public ListIterator<FlexibleShape> listIterator() {
		return vector.listIterator();
	}

	public ListIterator<FlexibleShape> listIterator(int arg0) {
		return vector.listIterator(arg0);
	}

	public boolean remove(Object arg0) {
		return vector.remove(arg0);
	}

	public FlexibleShape remove(int arg0) {
		return vector.remove(arg0);
	}

	public boolean removeAll(Collection<?> arg0) {
		return vector.removeAll(arg0);
	}

	public boolean retainAll(Collection<?> arg0) {
		return vector.retainAll(arg0);
	}

	public FlexibleShape set(int arg0, FlexibleShape arg1) {
		return vector.set(arg0, arg1);
	}

	public List<FlexibleShape> subList(int arg0, int arg1) {
		return vector.subList(arg0, arg1);
	}

	public Object[] toArray() {
		return vector.toArray();
	}

	public <T> T[] toArray(T[] arg0) {
		return vector.toArray(arg0);
	}

	public void addListener(
			EventTrapper<VectorMethodsListener<FlexibleShape>, ListenableVector<FlexibleShape>> observer)
			throws Exception {
//		vector.addListener(observer);
		
	}

	public void removeListener(
			EventTrapper<VectorMethodsListener<FlexibleShape>, ListenableVector<FlexibleShape>> observer) {
//		vector.removeListener(observer);
		
	}

	public Object getVisualization1() {
		// TODO Auto-generated method stub
		return visualizer.getVisualization();
	}

//	public void addReplayMethodListener(ObservableDataType anObservable, Observer anObserver) {
//		visualizer.addReplayMethodListener(null, anObserver);
////		vector.addR
//		
//	}

//	public Component getDataPanel() {
//		// TODO Auto-generated method stub
//		return visualizer.getDataPanel();
//	}
//
//	public ListenableVector<AttributedShape> getPseudoCode() {
//		// TODO Auto-generated method stub
//		return visualizer.getPseudoCode();
//	}

	public void setLayoutManager(LayoutManager newVal) {
		// TODO Auto-generated method stub
		
	}

	public boolean getShowPsuedoCode() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setShowPseudoCode(boolean newVal) {
		// TODO Auto-generated method stub
		
	}

	public boolean getShowData() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setShowData(boolean newVal) {
		// TODO Auto-generated method stub
		
	}

	public boolean getShowControls() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setShowControls(boolean newVal) {
		// TODO Auto-generated method stub
		
	}

	public CommandHistory getCommandHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCommandHistory(CommandHistory commandHistory) {
		// TODO Auto-generated method stub
		
	}

	public ObjectToBufferRegistry getObjectToBufferRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setObjectToBufferRegistry(
			ObjectToBufferRegistry objectToBufferRegistry) {
		// TODO Auto-generated method stub
		
	}

	public MethodsCodeVisualization getMethodsCodeVisualization() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMethodsCodeVisualization(MethodsCodeVisualization codeVisualization) {
		// TODO Auto-generated method stub
		
	}

	public boolean isShowCode() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setShowCode(boolean showCode) {
		// TODO Auto-generated method stub
		
	}

	public CodeLayoutManager getCodeLayoutManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCodeLayoutManager(CodeLayoutManager codeLayoutManager) {
		// TODO Auto-generated method stub
		
	}

	public ObservableDataType getRootBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectColorManager getObjectColorManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setObjectColorManager(ObjectColorManager objectColorManager) {
		// TODO Auto-generated method stub
		
	}

	public Visualization getVisualization() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setShapes(Visualization newVal) {
		// TODO Auto-generated method stub
		
	}

	public void setVisualization(Object newVal) {
		// TODO Auto-generated method stub
		
	}

	public ListenableShapeVector createShapeVector() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addReplayMethodListeners(ObservableDataType anObservable) {
		// TODO Auto-generated method stub
		
	}

	public List<ObservableDataType> getRootBuffers() {
		// TODO Auto-generated method stub
		return null;
	}

	public LayoutManager getLayoutManagerOfBuffer(ObservableDataType aRootBuffer) {
		// TODO Auto-generated method stub
		return null;
	}

//	public void setLayoutManagerOfRootObject(ObservableDataType aRootBuffer,
//			LayoutManager newVal) {
//		// TODO Auto-generated method stub
//		
//	}

	public Component getDataPanel(ObservableDataType aRootBuffer) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListenableVector<AttributedShape> getPseudoCode(
			ObservableDataType aRootBuffer) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObservableDataType getBuffer(ObservableDataType anOriginal) {
		// TODO Auto-generated method stub
		return null;
	}

	public ListenableVector<AttributedShape> getPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addReplayMethodListenerOfObjectTree(
			ObservableDataType anObservable, Observer anObserver) {
		// TODO Auto-generated method stub
		
	}

	public void addReplayMethodListenersToBuffer(ObservableDataType aBuffer) {
		// TODO Auto-generated method stub
		
	}

	public LayoutManager getLayoutManagerOfRootObject(ObservableDataType aRootObject) {
		// TODO Auto-generated method stub
		return null;
	}

	public LayoutManager getFirstLayoutManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLayoutManagerOfRootObject(ObservableDataType aRootBuffer,
			LayoutManager<ObservableDataType> newVal) {
		// TODO Auto-generated method stub
		
	}

	public CompositeLayoutManager getCompositeLayoutManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCompositeLayoutManager(CompositeLayoutManager newVal) {
		// TODO Auto-generated method stub
		
	}

	
}