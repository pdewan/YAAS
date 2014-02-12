package yaas.collection;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;

import bus.uigen.shapes.ListenableShapeVector;

import shapes.AttributedShape;
import shapes.BoundedShape;
import util.models.ListenableVector;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.layout.RowColumnLayoutManager;
import yaas.layout.VisualizationBasedLayoutManager;
import yaas.shapemappers.PointerShapeCreator;


public interface CollectionLayoutManager<ElementType> extends 
//RowColumnLayoutManager<ElementType> {
CompositeShapeLayoutManager<ListenableVector<ElementType>, ElementType>  {
//	VisualizationBasedLayoutManager<ListenableVector<ElementType>> {
//	Component displayInPanel();
//	
//	Container getPanel();
//
//	ListenableVector<AttributedShape> constructPseudoCode();
//
//	ListenableVector<AttributedShape> getPseudoCode();
//
//	int getPseudoCodeMarker();
//	public PointerShapeCreator<ElementType>getReadPointerShapeCreator() ;
//	public void setReadPointerShapeCreator(PointerShapeCreator<ElementType> newVal) ;
//	public abstract void setReadPointerShapeInitialCoordinates(BoundedShape aPointerShape);
//	public BoundedShape createShape(ListenableVector parent, ElementType val, Integer index, Rectangle initBounds, ListenableShapeVector containingShapes) ;

	public PointerShapeCreator<ElementType>getMarkingPointerShapeCreator() ;
	public void setMarkingPointerShapeCreator(PointerShapeCreator<ElementType> newVal) ;
	public  void setMarkingPointerShapeInitialCoordinates(ListenableShapeVector aListenableShapeVector, BoundedShape aPointerShape);
	
	public PointerShapeCreator<ElementType>getMarkingPointer2ShapeCreator() ;
	public void setMarkingPointer2ShapeCreator(PointerShapeCreator<ElementType> newVal) ;
	public  void setMarkingPointer2ShapeInitialCoordinates(ListenableShapeVector aListenableShapeVector, BoundedShape aPointerShape);




}
