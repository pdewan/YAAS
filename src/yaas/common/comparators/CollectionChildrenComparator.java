package yaas.common.comparators;

import java.util.Collection;
import java.util.Comparator;

import yaas.common.*;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CollectionChildrenComparator implements Comparator
{
	public int compare(Object c1, Object c2)
	{
		int s1,s2;
		if(!(c1 instanceof Collection))
			s1=0;
		else
			s1=CollectionManipulator.findNumberOfChildren((Collection)c1, 0);
		if(!(c2 instanceof Collection))
			s2=0;
		else
			s2=CollectionManipulator.findNumberOfChildren((Collection)c2, 0);
		
		if(s1<s2)
			return -1;
		if(s1>s2)
			return 1;
		return 0;
	}
}
