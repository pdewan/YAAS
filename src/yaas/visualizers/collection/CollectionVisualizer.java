package yaas.visualizers.collection;

import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.VestigalVisualizer;
import yaas.VisualizationBasedVisualizer;
import yaas.collection.CollectionLayoutManager;
import yaas.shapemappers.LabelMapper;
import yaas.visualization.collection.CollectionVisualization;

public interface CollectionVisualizer<ElementType> extends VisualizationBasedVisualizer <VectorMethodsListener<ElementType>, ListenableVector<ElementType>>
//	VisualizationBasedVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>, CollectionVisualization>


//      ShapeBasedVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> 

{
	public CollectionVisualization getVisualization() ;
	LabelMapper getCollectionLabelMapper();
	public CollectionLayoutManager<ElementType> getLayoutManagerOfBuffer(ListenableVector<ElementType> aSource);
	public CollectionLayoutManager<ElementType> getLayoutManagerOfRootObject(ListenableVector<ElementType> aSource);
	public boolean isDisplayName() ;

	public void setDisplayName(boolean isDisplayName) ;


}
