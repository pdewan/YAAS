package yaas.common.comparators;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class AComparator<T> implements Comparator<T>{

	public int compare(T o1, T o2) {

		if (o1 instanceof Comparable && o2 instanceof Comparable)
			return ((Comparable<T>)o1).compareTo(o2);
		return 0;
	}

}
