package yaas;

import shapes.BoundedShape;
import shapes.BoundedTextShape;
import util.annotations.Position;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.shapemappers.AnObjectToStringInACutout;
import yaas.shapemappers.ObjectToShapeTranslator;
import bus.uigen.shapes.AListenableShapeVector;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.shapes.AStringModel;
import bus.uigen.shapes.AnImageModel;
import bus.uigen.shapes.ListenableShapeVector;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AVisualization  implements Visualization {
//	Object userObjectShape = new ARectangleModel(0, 0, 0, 0);
//	Object copiedObjectShape = new ARectangleModel(0, 0, 0, 0);
	BoundedShape pointerShape =  new ARectangleModel(0, 0, 0, 0);
	BoundedTextShape statusShape = new AStringModel("");
	BoundedShape annotationShape;
//	ListenableVector<BoundedShape> shapes = new AnAddressSpaceTransformingListenableShapeVector();
	ListenableVector<ListenableShapeVector>  shapes = new AListenableVector();
	
	public AVisualization() {
//		shapes.add(new AListenableShapeVector());
//		annotationShape = new AnImageModel("data/flag.png");
//		annotationShape = new AnImageModel("data/cutout.png");
//		ObjectToShapeTranslator mapper = new AnObjectToStringInACutout();
//		BoundedShape shape = mapper.calculateNewShape(null, "foo bar");
//		annotationShape = shape;
//
//		annotationShape.setWidth(0);
//		annotationShape.setHeight(0);
	}
@Position(3)
	public ListenableVector<ListenableShapeVector>  getShapes() {
		return shapes;
	}
	public void setShapes(ListenableVector<ListenableShapeVector>  newVal) {
		shapes = newVal;
	}
//	public Object getUserObjectShape() {
//		return userObjectShape; 
//	}
//	
//	public void setUserObjectShape(Object newVal) {
//		 userObjectShape =  newVal; 
//	}
//	
//	
//	public Object getCopiedObjectShape() {
//		return copiedObjectShape; 
//	}
//	
//	public void setCopiedObjectShape(Object newVal) {
//		copiedObjectShape = newVal; 
//	}
	@Position(1)

	public BoundedTextShape getStatusShape() {
		return statusShape; 
	}
	
	public void setStatusShape(BoundedTextShape newVal) {
		statusShape = newVal; 
	}
	@Position(0)
	public BoundedShape getAnnotationShape() {
		return annotationShape; 
	}
	public void setAnnotationShape(BoundedShape newVal) {
		annotationShape = newVal; 
	}
	@Position(2)

	public BoundedShape getPointerShape() {
		return pointerShape; 
	}
	
	public void setPointerShape(BoundedShape newVal) {
		pointerShape = newVal; 
	}

}
