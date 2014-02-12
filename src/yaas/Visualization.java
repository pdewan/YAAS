package yaas;

import shapes.BoundedShape;
import shapes.BoundedShape;
import shapes.BoundedTextShape;
import util.models.ListenableVector;
import bus.uigen.shapes.ListenableShapeVector;

public interface Visualization 
    /*extends ListenableVector<BoundedShape>*/{
//	public Object getUserObjectShape();
//	
//	public void setUserObjectShape(Object newVal);
//	public Object getCopiedObjectShape() ;
//	
//	public void setCopiedObjectShape(Object newVal) ;
	public BoundedTextShape getStatusShape() ;
	
	public void setStatusShape(BoundedTextShape newVal) ;
	
	public BoundedShape getPointerShape() ;
	
	public void setPointerShape(BoundedShape newVal) ;
	public ListenableVector<ListenableShapeVector> getShapes();
	public void setShapes(ListenableVector<ListenableShapeVector> newVal);
	public BoundedShape getAnnotationShape() ;
	public void setAnnotationShape(BoundedShape newVal) ;

}
