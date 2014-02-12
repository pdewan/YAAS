package yaas.visualization.collection;

import shapes.BoundedShape;
import shapes.BoundedShape;
import shapes.BoundedTextShape;
import util.models.ListenableVector;
import yaas.Visualization;

public interface CollectionVisualization extends Visualization {
    /*extends ListenableVector<BoundedShape>*{*/
//	public Object getUserObjectShape();
//	
//	public void setUserObjectShape(Object newVal);
	public Object getCopiedObjectShape() ;
	
	public void setCopiedObjectShape(Object newVal) ;
	public BoundedTextShape getStatusShape() ;
	
	public void setStatusShape(BoundedTextShape newVal) ;
	
//	public BoundedShape getPointerShape() ;
//	
//	public void setPointerShape(BoundedShape newVal) ;
//	public ListenableVector<BoundedShape> getShapes();
//	public void setShapes(ListenableVector<BoundedShape> newVal);

}
