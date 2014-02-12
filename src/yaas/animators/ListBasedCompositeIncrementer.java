package yaas.animators;

import java.util.List;

import shapes.Rotatable;
import util.trace.Tracer;

public interface ListBasedCompositeIncrementer<ObjectType, ValueType> 
//	extends ObjectIncrementer<List<ObjectType>, List<ValueType>> {
extends AnimationStepListener {
//	extends ObjectIncrementer<List<ObjectType>, List<ValueType>> {
//	void foo();
//	
//    void init (List<ObjectIncrementer<ObjectType, ValueType>> anObjectIncrementers);
  void init (List<ListBasedObjectIncrementer<ObjectType, ValueType>> anObjectIncrementers,List<? extends ObjectType> anObjects, List<ValueType[]> aListOfValueLists);
	 void init(List<? extends ObjectType> anObjects, List<ValueType[]> aListOfValueLists);


//    void init (List<ObjectIncrementer<ObjectType, ValueType>> anObjectIncrementers,List<ObjectType> anObjects, List<ValueType> aFinalValues);
	
}

