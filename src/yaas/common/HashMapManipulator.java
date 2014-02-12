package yaas.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapManipulator 
{
	public static <ValueType> List<ValueType> valueArray(HashMap<Object, ValueType> map)
	{
		List<ValueType> values=new ArrayList<ValueType>();
		for(Object key:map.keySet())
		{
			if(key!=null)
			{
				values.add(map.get(key));
			}
		}
		return values;
	}
	public static <KeyType> List<KeyType> keyArray(HashMap<KeyType,Object> map)
	{
		List<KeyType> keys=new ArrayList<KeyType>();
		for(KeyType object:map.keySet())
		{
			if(object!=null)
			{
				keys.add(object);
			}
		}
		return keys;
	}
	public static <KeyType,ValueType> KeyType key(HashMap<KeyType,ValueType> map,Object object)
	{
		for(KeyType key:map.keySet())
			if(map.get(key)==object)
				return key;
		return null;
	}
	public static <KeyType,ValueType> HashMap<KeyType,ValueType> clone(HashMap<KeyType,ValueType> map)
	{
		HashMap<KeyType,ValueType> myMap=new HashMap<KeyType, ValueType>();
		for(KeyType key:map.keySet())
			myMap.put(key, map.get(key));
		return myMap;
	}
}
