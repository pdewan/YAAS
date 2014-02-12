package yaas.visualizers.collection;

import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.AVisualization;
import yaas.AVisualizationtBasedVisualizer;
import yaas.AVisualizer;
import yaas.VestigalVisualizer;
import yaas.Visualization;
import yaas.collection.CollectionLayoutManager;
import yaas.controller.AGenericButtonPressTrapper;
import yaas.controller.ButtonPressTrapper;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.shapemappers.ADefaultVectorLabelMapper;
import yaas.shapemappers.LabelMapper;
import yaas.visualization.collection.ACollectionVisualization;
import yaas.visualization.collection.CollectionVisualization;


public  class ACollectionVisualizer<ElementType> extends 
	AVisualizationtBasedVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>>
	implements CollectionVisualizer<ElementType>
	{
	
	boolean isDisplayName = true;

//	CollectionVisualization visualization = new ACollectionVisualization();
	LabelMapper collectionLabelMapper = new ADefaultVectorLabelMapper();
	
	protected Visualization createVisualization() {
		return  new  ACollectionVisualization();
	}

	public ACollectionVisualizer() {
//		visualization =  new ACollectionVisualization();
	}
	
	@Override
	public CollectionVisualization getVisualization() {
		return (CollectionVisualization) super.getVisualization();
	}
	public LabelMapper getCollectionLabelMapper() {
		return collectionLabelMapper;
		
	}
	public void setCollectionLabelMapper(LabelMapper newVal) {
		collectionLabelMapper = newVal;
	}
	@Override
	public void addObserver(ListenableVector<ElementType> anObservable,
			VectorMethodsListener<ElementType> anObserver) {
		anObservable.addVectorMethodsListener(anObserver);
	}
	public CollectionLayoutManager<ElementType> getLayoutManagerOfBuffer(ListenableVector<ElementType> aBuffer) {
		return (CollectionLayoutManager<ElementType>) super.getLayoutManagerOfBuffer(aBuffer);
	}
//	public CollectionLayoutManager<ElementType> getLayoutManagerOfRootObject(ListenableVector<ElementType> aBuffer) {
//		return getLayoutManagerOfBuffer(objectToBufferRegistry.getBuffer(aBuffer));
//	}
	public CollectionLayoutManager<ElementType> getLayoutManagerOfRootObject(ListenableVector<ElementType> aBuffer) {
	return  (CollectionLayoutManager<ElementType>)super.getLayoutManagerOfRootObject(aBuffer);
}
	public ListenableVector<ElementType> getRootBuffer(ListenableVector<ElementType> aChildBuffer) {
		return aChildBuffer.getRoot();
	}
	
	public boolean isDisplayName() {
		return isDisplayName;
	}
//	@Override
//	@Implement

	public void setDisplayName(boolean isDisplayName) {
		this.isDisplayName = isDisplayName;
	}
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new AGenericButtonPressTrapper(this.getController(), this, 40);
	}
	
	
}
