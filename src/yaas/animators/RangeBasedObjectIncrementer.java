package yaas.animators;

public interface RangeBasedObjectIncrementer<ObjectType, PropertyType>  
	extends ObjectIncrementer<ObjectType, PropertyType>{
	public void init(ObjectType anObject, PropertyType aFinalValue);
//	public boolean isCirculate() ;
//	public void setCirculate(boolean newVal) ;

}
