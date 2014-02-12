package yaas.animators;

public interface ListBasedObjectIncrementer<ObjectType, PropertyType>  
	extends ObjectIncrementer<ObjectType, PropertyType>{
	public void init(ObjectType anObject, PropertyType[] aValueRange);
//	public boolean isToggleDirection();
//
//
//	public void setToggleDirection(boolean toggleDirection);
//	public boolean isCirculate() ;
//	public void setCirculate(boolean newVal) ;

}
