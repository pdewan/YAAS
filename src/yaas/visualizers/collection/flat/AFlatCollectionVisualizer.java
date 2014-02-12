package yaas.visualizers.collection.flat;

import shapes.AttributedShape;
import shapes.FlexibleShape;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.AVisualizationtBasedVisualizer;
import yaas.VestigalVisualizer;
import yaas.buffers.vector.ALinearBuffer;
import yaas.controller.AGenericButtonPressTrapper;
import yaas.controller.ButtonPressTrapper;
import yaas.layout.LayoutManager;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.visualization.collection.ACollectionVisualization;
import yaas.visualization.collection.CollectionVisualization;
import yaas.visualizers.collection.ACollectionVisualizer;
import yaas.visualizers.collection.tree.TreeLayoutManager;


public  class AFlatCollectionVisualizer<ElementType>
		extends ACollectionVisualizer<ElementType>
//		AShapeBasedVisualizer<VectorMethodsListener<Integer>, ListenableVector<Integer>> 

//		AVisualizer<VectorMethodsListener<Integer>, ListenableVector<Integer>> 
//		implements VectorMethodsListener<Integer>
//implements VestigalVisualizer<VectorMethodsListener<Integer>, ListenableVector<Integer>, CollectionVisualization>
{
//	SimpleShape userObjectShape = new ARectangleModel(0, 0, 0, 0);
//	SimpleShape copiedObjectShape = new ARectangleModel(0, 0, 0, 0);
//	SimpleShape pointerShape =  new ARectangleModel(0, 0, 0, 0);
//	AStringModel statusShape = new AStringModel("");
//	IntegerBarChartVisualization shapes = new AnIntegerBarChartVisualization();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409522039257517809L;
//	public synchronized void visualize(ListenableVector<ElementType> data, LayoutManager aLayoutManager)
//			throws Exception {
//		super.visualize(data, aLayoutManager);
////		data.addVectorMethodsListener(this);
//		
//	}

//	public AFlatCollectionVisualizer() throws Exception {
//		super();
////		shapes = new ACollectionVisualization();
////		getVisualization().getShapes().get(0).setX(100);
////		getVisualization().getShapes().get(0).setY(100);
////		layoutManager = new AFlatCollectionLayoutManager(this);
//		
//	}
	
//	public FlatCollectionLayoutManager getFlatCollectionLayoutManager() {
//		return (FlatCollectionLayoutManager)  getLayoutManager();
//	}
	
	
//	@Override
//	protected ListenableVector<ElementType> createBuffer() {
//		return new ALinearBuffer<ElementType>(this);
//	}

//	@Override
//	public ButtonPressTrapper initializeButtonPressTrapper() {
//		return new AGenericButtonPressTrapper(this.getController(), this, 40);
//	}

//	public int theStepCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//// used?
//	public boolean removeElement(FlexibleShape c) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
//	public SimpleShape getUserObjectShape() {
//		return userObjectShape; 
//	}
//	
//	public void setUserObjectShape(SimpleShape newVal) {
//		 userObjectShape = newVal; 
//	}
//	
//	
//	public SimpleShape getCopiedObjectShape() {
//		return copiedObjectShape; 
//	}
//	
//	public void setCopiedObjectShape(SimpleShape newVal) {
//		copiedObjectShape = newVal; 
//	}
//	
//	public AStringModel getStatusShape() {
//		return statusShape; 
//	}
//	
//	public void setStatusShape(AStringModel newVal) {
//		statusShape = newVal; 
//	}
//	
//	public SimpleShape getPointerShape() {
//		return pointerShape; 
//	}
//	
//	public void setPointerShape(SimpleShape newVal) {
//		pointerShape = newVal; 
//	}

//	public void addReplayMethodListener(
//			VectorMethodsListener<ElementType> anObserver) {
//		getRootBuffer().addVectorMethodsListener(anObserver);
//	}

//	public boolean removeElement(AttributedShape c) {
//		// TODO Auto-generated method stub
//		return false;
//	}

//	public CollectionVisualization getVisualization() {
//		// TODO Auto-generated method stub
//		return (CollectionVisualization) shapes;
//	}

//	@Override
	// needs to be shared with AShapeBasedVisualizer
//	protected void visualizeBuffer(ListenableVector<ElementType> vector) {
//		bufferData.put(collectionIndex, vector);
//
//		shapes.addAll(getLayoutManager().display(vector));
//		collectionIndex++;
//		
//	}
//	public VisualizationBasedLayoutManager<ListenableVector<ElementType>> getLayoutManager() {
//		return (VisualizationBasedLayoutManager<ListenableVector<ElementType>>) layoutManager;
//	}
//	public void setLayoutManager (VisualizationBasedLayoutManager<ListenableVector<ElementType>> newVal) {
//		layoutManager = newVal;
//	}


	

//	public void elementAdded(Object source, Integer element, int newSize) {
////		OEShape shape = ((AnIntegerBarChartLayoutManager) layoutManager).nextShape(element);
//		buffer.add(element);
////		add(shape);
//	}
//
//	public void elementsAdded(Object source,
//			Collection<? extends Integer> element, int newSize) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementInserted(Object source, Integer element, int pos,
//			int newSize) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementChanged(Object source, Integer element, int pos) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementRemoved(Object source, int pos, int newSize) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementRemoved(Object source, Integer element, int newSize) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementsCleared(Object source) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementSwapped(Object newParam, int index1, int index2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementSwapped(Object source, int index1, Object other,
//			int index2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementMoved(Object source, int fromIndex, int toIndex) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementMoved(Object source, int fromIndex, int fromNewSize,
//			Object to, int toIndex) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementCopied(Object source, int fromIndex, int toIndex,
//			int newSize) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementCopied(Object source, int fromIndex, int fromNewSize,
//			Object to, int toIndex) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementReplaced(Object source, int fromIndex, int toIndex,
//			int newSize) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void elementReplaced(Object source, int fromIndex, int newFromSize,
//			Object to, int toIndex) {
//		// TODO Auto-generated method stub
//		
//	}

}
