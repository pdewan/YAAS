package yaas.animators;

public interface ObjectIncrementer<ObjectType, PropertyType>  extends AnimationStepListener{
//	public void init(ObjectType anObject, PropertyType aFinalValue);
	public boolean isCirculate() ;
	public void setCirculate(boolean newVal) ;
//	public boolean isToggleDirection();
//
//
//	public void setToggleDirection(boolean toggleDirection);

}
