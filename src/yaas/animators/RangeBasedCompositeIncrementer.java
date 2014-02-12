package yaas.animators;

import java.util.List;

import util.trace.Tracer;

public interface RangeBasedCompositeIncrementer<ObjectType, ValueType> 
//	extends ObjectIncrementer<List<ObjectType>, List<ValueType>> {
extends AnimationStepListener {
//	extends ObjectIncrementer<List<ObjectType>, List<ValueType>> {
//	void foo();
//	
//    void init (List<ObjectIncrementer<ObjectType, ValueType>> anObjectIncrementers);
  void init (List<RangeBasedObjectIncrementer<ObjectType, ValueType>> anObjectIncrementers,List<? extends ObjectType> anObjects, List<ValueType> aFinalValues);


//    void init (List<ObjectIncrementer<ObjectType, ValueType>> anObjectIncrementers,List<ObjectType> anObjects, List<ValueType> aFinalValues);
	
}

