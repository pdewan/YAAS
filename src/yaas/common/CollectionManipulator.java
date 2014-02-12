package yaas.common;

import java.util.Collection;
import java.util.List;

import util.models.ListenableVector;

public class CollectionManipulator 
{
	@SuppressWarnings("unchecked")
	public static ListenableVector<Object> findSuperParent(ListenableVector<Object>  v)
	{
		if(v.getParent()==null)
			return v;
		return findSuperParent(v.getParent());
	}
	@SuppressWarnings("unchecked")
	public static int findNumberOfParents(ListenableVector<Object>  v, int count)
	{
		if(v.getParent()==null)
			return count;
		return findNumberOfParents(v.getParent(),++count);
	}
	@SuppressWarnings("unchecked")
	public static int findNumberOfChildren(Collection<Object>  collection, int num)
	{
		int numb=num+collection.size();
		for(Object ob:collection)
		{
			if(ob instanceof Collection)
				numb=findNumberOfChildren((Collection<Object> )ob,numb);
		}
		return numb;
	}
	@SuppressWarnings("unchecked")
	public static List<Object>  getChildren(Collection<Object>  collection,List<Object>  list)
	{
		for(Object o:collection)
		{
			if(o instanceof Collection)
			{
				list.add(o);
				getChildren((Collection<Object> )o,list);
			}
			else
				list.add(o);
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public static Collection<Object>  clone(Collection<Object>  theCollection)
	{
		Collection<Object>  coll;
		try 
		{
			coll = theCollection.getClass().newInstance();
			for(Object object:theCollection)
				coll.add(object);
			return coll;
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
