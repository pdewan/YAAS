package yaas.collection;

import java.io.Serializable;
import java.util.Collection;

import shapes.BoundedTextShape;
import shapes.FlexibleTextShape;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.visualizers.collection.flat.AnIntegerBarChartVisualizer;
import bus.uigen.shapes.AStringModel;

public class ATextualCollectionVectorMethodsAnimator<ElementType>
		implements
		VectorMethodsListener<ElementType> , Serializable{
	
	protected AnIntegerBarChartVisualizer visualizer;
//	protected AnIntegerBarChartLayoutManager layoutManager;
	
	public static final String ASSIGN = ":=";
	public static final String ADD= "+=";	
	public static final String REMOVE = "-=";
	public static final String REMOVE_INDEX = "--";
//	public static final String GET = "get()";
	public static final String GET = "==";
	public static final String USER_OPERATION = ":";

	public static final String MOVE = "->";


	public static final String SWAP = "<->";
	public static final String EMPTY = "{}";
	public static final String POSITION_DESIGNATOR = "@";
	public static final String USER_OBJECT_NAME = ".value";
	public static final String TEMP_NAME = ".temp";

	BoundedTextShape statusShape;

	public ATextualCollectionVectorMethodsAnimator(
			BoundedTextShape aStatusShape) {
		this.visualizer = visualizer;
//		this.layoutManager = layoutManager;
//		statusShape = visualizer.getShapes().getStatusShape();
		statusShape = aStatusShape;
	}
	
	
	private static final long serialVersionUID = 3971272244005861176L;
	private static final int COPY_DELTA_X = 5;
	
	String getName(Object source) {
		return ((ListenableVector) source).getName();
	}
	
	ElementType get(Object source, int aPosition) {
		return ((ListenableVector<ElementType>)source).get(aPosition);
	}

//	Integer getUserObject(Object source) {
//		return (Integer)((ListenableVector<Integer>) source).getUserObject();
//		
//	}
	Object getUserObject(Object source) {
		return ((ListenableVector<Integer>) source).getUserObject();
		
	}
	Object getTemp(Object source) {
		return ((ListenableVector<Integer>) source).getTemp();
		
	}
	public void elementAdded(Object source, ElementType element, int newSize) {
		String text = getName(source) + ADD + element;
		statusShape.setText(text);
//		System.out.println(text);

//		

	}
	public void elementInserted(Object source, ElementType element, int pos,
			int newSize) {
		String text = getName(source) + POSITION_DESIGNATOR + pos+  ADD + element;
		statusShape.setText(text);

	}
	public void elementCopiedAndInserted(Object source, int fromIndex,
			int toIndex, int newSize) {
		String text = getName(source) + 
				POSITION_DESIGNATOR + 
				toIndex +  
				ADD +
				elementToString(source, toIndex);

		
	}
	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub
		String text = getName(source) + 
				POSITION_DESIGNATOR + 
				toIndex  + MOVE + elementToString(to, toIndex) ;

		statusShape.setText(text);

	}
	
	public void elementCopiedAndInserted(Object source, int fromIndex,
			int fromNewSize, Object to, int toIndex) {
		String text = getName(source) + 
				POSITION_DESIGNATOR + 
				toIndex  + ASSIGN + elementToString(to, toIndex) ;

		statusShape.setText(text);
		// TODO Auto-generated method stub
		
	}
	public void elementRemoved(Object source, ElementType element, int newSize, int pos) {
		String text = getName(source) + REMOVE + element;
		statusShape.setText(text);
	}
	
	public void elementsCleared(Object source) {
		String text = getName(source) + ASSIGN + EMPTY;
		statusShape.setText(text);
	}

public void elementRemoved(Object source, int pos, int newSize) {
	
//	String text = getName(source) + REMOVE+ elementToString(source, pos);
	String text = getName(source) + POSITION_DESIGNATOR + pos + REMOVE_INDEX;

	
	statusShape.setText(text);



	}

	String elementToString(Object source, int index) {
		return getName(source) + "[" + index + "]";
	}
	
	String userObjectToString(Object source) {
		return getName(source) + USER_OBJECT_NAME;
	}
	String tempToString(Object source) {
		return getName(source) + TEMP_NAME;
	}


	public void elementChanged(Object source, ElementType element, int pos) {
		String text = getName(source) + "[" + pos + "] := " + toIdentifier(element);
		statusShape.setText(text);
//		System.out.println(text);
		
	}
	public void userObjectChanged(Object source, Object newVal) {
		String text = getName(source) + USER_OBJECT_NAME + ASSIGN +  toIdentifier(newVal);
		statusShape.setText(text);

//		System.out.println(text);
		
		
	}
	
	public void tempChanged(Object source, Object newVal) {
		String text = getName(source) + TEMP_NAME + ASSIGN +  toIdentifier(newVal);
		statusShape.setText(text);

//		System.out.println(text);
		
		
	}
	
	String toIdentifier(Object element) {
		if (element == null) {
			return "null";
		}
		if (element instanceof ListenableVector) {
			return ((ListenableVector) element).getName();
		} else {
			return element.toString();
		}
	}
	
	String toRHSValue(Object aSource, Integer anIndex) {
		if (anIndex < ((ListenableVector) aSource).size())
			return toIdentifier(get(aSource, anIndex));
		else
			return "end";
	}
	
	String rhsElementToString (Object aSource, Integer anIndex) {
//		return elementToString(aSource, anIndex) + "(" + toIdentifier(get(aSource, anIndex)) + ")";
		return elementToString(aSource, anIndex) + "(" + toRHSValue(aSource, anIndex)+ ")";

	}
	
	String rhsUserObjectToString (Object aSource) {
		return userObjectToString(aSource) + "(" + getUserObject(aSource) + ")";
	}
	
	String rhsTempToString (Object aSource) {
		return tempToString(aSource) + "(" + getTemp(aSource) + ")";
	}
	

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {
		
//		String text = getName(source) + "[" + toIndex + "] := " +  getName(source) + "[" + fromIndex + "]";
		String text = elementToString(source, toIndex) + ASSIGN + rhsElementToString(source, fromIndex);
		statusShape.setText(text);

//		System.out.println(text);
		

	}
	


public void elementCopiedToUserObject(Object aSource, int fromIndex) {
	String text = userObjectToString(aSource) + ASSIGN + elementToString(aSource, fromIndex)   ;
	statusShape.setText(text);

//	System.out.println(text);

		
	}
public void elementCopiedFromUserObject(Object source, int toIndex) {
//	String text = getName(source) + ".temp" +  " := " +  getName(source) + "[" + fromIndex + "]" + "(" + get(source, fromIndex) + ")" ;
	String text = elementToString(source, toIndex) + ASSIGN + rhsUserObjectToString(source);
	statusShape.setText(text);

//	System.out.println(text);
	
}

public void elementCopiedToTemp(Object aSource, int fromIndex) {
	String text = tempToString(aSource) + ASSIGN + elementToString(aSource, fromIndex)   ;
	statusShape.setText(text);

//	System.out.println(text);

		
	}
public void elementCopiedFromTemp(Object source, int toIndex) {
//	String text = getName(source) + ".temp" +  " := " +  getName(source) + "[" + fromIndex + "]" + "(" + get(source, fromIndex) + ")" ;
	String text = elementToString(source, toIndex) + ASSIGN + rhsTempToString(source);
	statusShape.setText(text);

//	System.out.println(text);
	
}




	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		String text = elementToString(to, toIndex) + ASSIGN + elementToString(source, fromIndex);

		statusShape.setText(text);

	}
	
	

	public void elementMoved(Object source, int fromIndex, int toIndex) {
		String text = elementToString(source, fromIndex) + MOVE + elementToString(source, toIndex);
		statusShape.setText(text);


	}

	
	

	

	
	public void elementReplaced(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementReplaced(Object source, int fromIndex, int newFromSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public synchronized void elementSwapped(Object newParam, int index1,
			int index2) {

		elementSwapped (newParam, index1, newParam, index2);


	}

	public void elementSwapped(Object source, int index1, Object other,
			int index2) {
//		String text = SWAP + "(" + rhsElementToString(source, index1) + "," +  rhsElementToString(other, index2) + ")";
		String text =  rhsElementToString(source, index1) + SWAP +  rhsElementToString(other, index2);

		statusShape.setText(text);

	}

	public void elementsAdded(Object source,
			Collection<? extends ElementType> element, int newSize) {

	}

	

	public boolean removeElement(Object c) {
		// TODO Auto-generated method stub
		return false;
	}
	public void elementRead(Object source, ElementType element, Integer pos) {
		String text = "";
		if (pos == null)
			text = "";
		
		else if (pos == -1) {
//			text = userObjectToString(source) + "." + GET + "(" + getUserObject(source) + ")";
			text = userObjectToString(source) + GET +  getUserObject(source);

		}  else if (pos == -2) {
			text = userObjectToString(source) + GET +  getTemp(source);

		} else {
//			text = elementToString(source, pos) + "." + GET + "(" + get(source, pos) + ")";
			text = elementToString(source, pos) + GET + get(source, pos);

		}
		statusShape.setText(text);

//		System.out.println(text);
		
		
	}

	public void userOperationOccured(Object source, Integer aTargetIndex,
			Object anOperation) {
//		String text = elementToString(source, aTargetIndex) + USER_OPERATION + anOperation;
		String text = rhsElementToString(source, aTargetIndex) + USER_OPERATION + anOperation;

		statusShape.setText(text);
	}

	public void userObjectCopiedToTemp(Object source, Object copiedValue) {
		// TODO Auto-generated method stub
		
	}

	public void tempCopiedToUserObject(Object source, Object copiedValue) {
		// TODO Auto-generated method stub
		
	}

	public void userObjectRead(Object source, Object readValue) {
		String text = userObjectToString(source) + GET +  getUserObject(source);
		statusShape.setText(text);

		
	}

	public void tempRead(Object source, Object readValue) {
		String text =tempToString(source) + GET +  getTemp(source);
		statusShape.setText(text);


		
	}

	

	

	public void pointerChanged(Object source, Integer pointerValue) {
		String text = getName(source) + 
			POSITION_DESIGNATOR +  "" + pointerValue;
		statusShape.setText(text);
		
	}

	public void pointer2Changed(Object source, Integer pointerValue) {
		String text = getName(source) + 
				POSITION_DESIGNATOR + "(2) " + pointerValue ;
			statusShape.setText(text);
			
		
	}
	



	
	

	
	
}
