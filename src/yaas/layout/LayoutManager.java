package yaas.layout;

import java.awt.Component;
import java.awt.Container;

import shapes.AttributedShape;
import shapes.BoundedShape;
import util.models.ListenableVector;
import yaas.shapemappers.PointerShapeCreator;


public interface LayoutManager<ObservableDataType> {
	Component displayInPanel();
	
	Container getPanel();

	ListenableVector<AttributedShape> constructPseudoCode();

	ListenableVector<AttributedShape> getPseudoCode();

	int getPseudoCodeMarker();
	ObservableDataType createBuffer();
//	public PointerShapeCreator<ElementType>getPointerShapeCreator() ;
//	public void setPointerShapeCreator(PointerShapeCreator<ElementType> newVal) ;

}
