package yaas.layout;

import java.awt.Component;
import java.awt.Container;

import shapes.BoundedShape;
import util.models.ListenableVector;
import bus.uigen.shapes.ListenableShapeVector;
import bus.uigen.translator.Translator;

public interface VisualizationBasedLayoutManager<UserDataType> 
	extends LayoutManager<UserDataType> {

	ListenableVector<BoundedShape> display(UserDataType data);
	
	public int getPreferredHeight();

	public void setPreferredHeight(int newVal) ;
	
//	public int getX();
//	public void setX(int newVal);
//	public int getY();
//	public void setY(int newVal);
	public int getPreferredWidth();

	public void setPreferredWidth(int newVal) ;
	public ListenableShapeVector getContainingShapes(UserDataType data);

	

//	
//	Component displayInPanel();
//	
//	Container getPanel();
//
//	ListenableVector<SimpleShape> constructPseudoCode();
//
//	ListenableVector<SimpleShape> getPseudoCode();
//
//	int getPseudoCodeMarker();

}
