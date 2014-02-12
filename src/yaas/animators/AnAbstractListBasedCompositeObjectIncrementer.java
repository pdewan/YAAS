package yaas.animators;

import java.util.ArrayList;
import java.util.List;

import shapes.Rotatable;
import util.trace.Tracer;

public abstract class AnAbstractListBasedCompositeObjectIncrementer<ObjectType, ValueType>
	extends AnAbstractObjectIncrementer
	implements ListBasedCompositeIncrementer<ObjectType, ValueType>{
	List<ListBasedObjectIncrementer<ObjectType, ValueType>> componentIncrementers;
	
//	public ACompositeIncrementer(List<ObjectIncrementer<ObjectType, ValueType>> anObjectIncrementers) {
//		componentIncrementers = anObjectIncrementers;
//	}
	
	public void init(List<? extends ObjectType> anObjects, List<ValueType[]> aListOfValueLists) {
		List<ListBasedObjectIncrementer<ObjectType, ValueType>> anIncrementers = new ArrayList();
		for (int i = 0; i < anObjects.size(); i++) {
//			anIncrementers.add(new AListBasedShapeAngleIncrementer());
			anIncrementers.add(createObjectIncrementer());

		}
		init (anIncrementers, anObjects, aListOfValueLists);
	}
	
	abstract ListBasedObjectIncrementer<ObjectType, ValueType> createObjectIncrementer();

	public void animationStart(int aMaxSteps) {
		for (ObjectIncrementer componentIncrementer:componentIncrementers) {
			componentIncrementer.animationStart(aMaxSteps);
		}
		
	}

	public boolean nextStep(int aStepNumber, int aMaxSteps) {
		boolean retVal = true;
		for (ObjectIncrementer componentIncrementer:componentIncrementers) {
			retVal &= componentIncrementer.nextStep(aStepNumber, aMaxSteps);
		}
		
		return retVal;
	}

	public void animationEnd(int aNumSteps, int aMaxSteps) {
		for (ObjectIncrementer componentIncrementer:componentIncrementers) {
			componentIncrementer.animationEnd(aNumSteps, aMaxSteps);
		}
		
	}
	public void init(
			List<ListBasedObjectIncrementer<ObjectType, ValueType>> anObjectIncrementers) {
		
		componentIncrementers = anObjectIncrementers;
		
		
	}

	public void init(
			List<ListBasedObjectIncrementer<ObjectType, ValueType>> anObjectIncrementers,
			List<? extends ObjectType> anObjects, List<ValueType[]> aListOfValueLists) {
		if (anObjectIncrementers.size() != anObjects.size() || anObjectIncrementers.size() != aListOfValueLists.size()) {
			Tracer.error("Mismatched sizes of " + anObjectIncrementers + ", "  + anObjects + "," + aListOfValueLists);
		}
		componentIncrementers = anObjectIncrementers;
		for (int i= 0; i < anObjectIncrementers.size(); i++) {
			anObjectIncrementers.get(i).init(anObjects.get(i), aListOfValueLists.get(i));
		}
		
	}
//	public void init(
//			List<ObjectType> anObjects, List<ValueType> aFinalValues) {
//		if (componentIncrementers.size() != anObjects.size() || componentIncrementers.size() != aFinalValues.size()) {
//			Tracer.error("Mismatched sizes of " + componentIncrementers + ", "  + anObjects + "," + aFinalValues);
//		}
//		for (int i= 0; i < componentIncrementers.size(); i++) {
//			componentIncrementers.get(i).init(anObjects.get(i), aFinalValues.get(i));
//		}
//		
//	}

//	public void setList(
//			List<ObjectIncrementer<List<ObjectType>, List<ValueType>>> anObjectIncrementers,
//			List<List<ObjectType>> anObjects, List<List<ValueType>> aFinalValues) {
//		componentIncrementers = anObjectIncrementers;
//		
//	}


	
}
