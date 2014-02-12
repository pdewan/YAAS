package yaas.common.comparators;

import java.util.Collection;
import java.util.Comparator;

public class CollectionSizeComparator implements Comparator<Collection<Object>>
{
	public int compare(Collection<Object> c1, Collection<Object> c2)
	{
		int s1=c1.size();
		int s2=c2.size();
		
		if(s1<s2)
			return -1;
		if(s1>s2)
			return 1;
		return 0;
	}
}
