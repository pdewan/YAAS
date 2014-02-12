package yaas.visualization.collection;

import shapes.AttributedShape;
import shapes.BoundedShape;
import util.annotations.IsAtomicShape;
import util.annotations.IsCompositeShape;
import util.models.AListenableVector;
import util.models.ListenableVector;
import yaas.AVisualization;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.shapes.AStringModel;
import bus.uigen.shapes.AnAddressSpaceTransformingListenableShapeVector;
@IsCompositeShape(true)
//public class ACollectionVisualization extends AListenableVector<BoundedShape> implements CollectionVisualization{
//public class ACollectionVisualization /*extends AnAddressSpaceTransformingListenableShapeVector*/ implements CollectionVisualization{
	public class ACollectionVisualization extends AVisualization implements CollectionVisualization{


	public ACollectionVisualization(int initialSize, BoundedShape aReference) {
//		super(initialSize, aReference);
//		setShapes(new AnAddressSpaceTransformingListenableShapeVector());
		// TODO Auto-generated constructor stub
	}
	
	public ACollectionVisualization() {
		super();
		// TODO Auto-generated constructor stub
	}

	Object userObjectShape = new ARectangleModel(0, 0, 0, 0);
	Object copiedObjectShape = new ARectangleModel(0, 0, 0, 0);
//	BoundedShape pointerShape =  new ARectangleModel(0, 0, 0, 0);
//	AStringModel statusShape = new AStringModel("");
////	ListenableVector<BoundedShape> shapes = new AnAddressSpaceTransformingListenableShapeVector();
//	ListenableVector<BoundedShape> shapes = new AListenableVector();

//	public ListenableVector<BoundedShape> getShapes() {
//		return shapes;
//	}
//	public void setShapes(ListenableVector<BoundedShape> newVal) {
//		shapes = newVal;
//	}
//	public Object getUserObjectShape() {
//		return userObjectShape; 
//	}
//	
//	public void setUserObjectShape(Object newVal) {
//		 userObjectShape =  newVal; 
//	}
	
	
	public Object getCopiedObjectShape() {
		return copiedObjectShape; 
	}
	
	public void setCopiedObjectShape(Object newVal) {
		copiedObjectShape = newVal; 
	}
	
//	public AStringModel getStatusShape() {
//		return statusShape; 
//	}
//	
//	public void setStatusShape(AStringModel newVal) {
//		statusShape = newVal; 
//	}
//	
//	public BoundedShape getPointerShape() {
//		return pointerShape; 
//	}
	
//	public void setPointerShape(BoundedShape newVal) {
//		pointerShape = newVal; 
//	}


}
