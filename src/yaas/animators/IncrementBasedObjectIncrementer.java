package yaas.animators;

public interface IncrementBasedObjectIncrementer<ObjectType, PropertyType>  
	extends ObjectIncrementer<ObjectType, PropertyType>{
	public void init(ObjectType anObject, PropertyType anIncrement, int numSteps);
//	public boolean isCirculate() ;
//	public void setCirculate(boolean newVal) ;

}
