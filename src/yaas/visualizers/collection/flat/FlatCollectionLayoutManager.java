package yaas.visualizers.collection.flat;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;









import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import util.misc.Common;
import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.collection.ACollectionLayoutManager;
import yaas.collection.CollectionLayoutManager;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.layout.gui.APanelGenerator;
import yaas.layout.nodes.AFlatElement;
import yaas.layout.nodes.FlatElement;
import yaas.shapemappers.ACirclePointerShapeManager;
import yaas.shapemappers.LabelMapper;
import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.shapemappers.PointerShapeCreator;
import yaas.visualization.collection.CollectionVisualization;
import yaas.visualizers.collection.CollectionVisualizer;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.shapes.ListenableShapeVector;

public interface FlatCollectionLayoutManager<ElementType> extends 
//		CompositeLayoutManager<ListenableVector<ElementType>, ElementType> {
CollectionLayoutManager<ElementType> {
	
	
	
	
	public CollectionVisualizer<ElementType> getVisualizer() ;
	// this one creates the flat element around the basic shape
	public BoundedShape createShape(ListenableVector parent, ElementType val, Integer index, Rectangle initBounds, ListenableShapeVector containingShapes) ;
//	public BoundedShape createShape(ListenableVector aParent, Object anElement, Integer anIndex, Rectangle initBounds);

	
	
	public  Point normalizedToRealLocation (ListenableShapeVector aContainingShapes, BoundedShape aShape, Point aNormalizedLocation) ;
	

	public ListenableVector<AttributedShape> constructPseudoCode() ;

	public ListenableVector<AttributedShape> getPseudoCode() ;

	public int getPseudoCodeMarker() ;

	public int getScaleFactor() ;

	public int getBaseLine() ;

	
	public ObjectToShapeTranslator<ElementType> getElementToShapeTranslator() ;
	public void setElementToShapeTranslator(ObjectToShapeTranslator<ElementType> newVal) ;
	public Point getLabelShapeLocation (BoundedShape aContentShape, BoundedShape aLabelShape) ;
	public BoundedShape getLabelShape(ListenableVector parent,  int index);
	public void updateLabelShape(BoundedShape aCompositeShape, ListenableVector parent,  int index);
	public void updateLabelShapes(ListenableShapeVector aListenableShapeVector, ListenableVector aParent);
	public Object getLabelInfo (BoundedShape anElementShape) ;
	public void restoretLabelInfo (BoundedShape anElementShape, Object aLabelnfo) ;

	
	


	
}
