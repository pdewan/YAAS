package yaas.layout;

import shapes.AttributedShape;
import shapes.BoundedShape;
import yaas.shapemappers.ObjectToShapeTranslator;
import bus.uigen.translator.Translator;

public interface CompositeShapeLayoutManager<CompositeDataType, ElementType/*, ElementShapeType*/> 
//extends RowColumnLayoutManager<CompositeDataType> {
extends VisualizationBasedLayoutManager<CompositeDataType> {
	ObjectToShapeTranslator<ElementType> getElementToShapeTranslator();
	void setElementToShapeTranslator(ObjectToShapeTranslator<ElementType> newVal);

}
