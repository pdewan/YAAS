package yaas.layout;

import shapes.FlexibleShape;
import util.models.AListenableVector;
import util.models.ListenableVector;
import bus.uigen.shapes.*;

public class AShapeFactory implements ShapeFactory
{
	FlexibleShape theShape;
	public AShapeFactory(FlexibleShape shape)
	{
		theShape=shape;
	}
	public FlexibleShape create()
	{
		try 
		{
			if(theShape instanceof APointModel)
				return new APointModel(0,0);
			if(theShape instanceof CompositeShape){
				ListenableVector<FlexibleShape> v = new AListenableVector<FlexibleShape>();
				for(FlexibleShape shape : ((CompositeShape)theShape).getShapes()){
					FlexibleShape s1 = shape.getClass().newInstance();
					s1.setHeight(shape.getHeight());
					s1.setWidth(shape.getWidth());
					s1.setX(shape.getX());
					s1.setY(shape.getY());
					v.add(s1);
				}
				CompositeShape s = new ACompositeShape(v);
				return s;
			}
			return theShape.getClass().newInstance();
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
