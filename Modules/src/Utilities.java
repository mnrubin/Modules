import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;


public class Utilities {
	/**
	 * Make a deep copy of a ConcurrentHashMap<String, Integer>.
	 * @param to_clone The CHM
	 * @return The deep clone
	 */
	public static ConcurrentHashMap<String, Integer> deepClone(ConcurrentHashMap<String, Integer> to_clone)
	{
		ConcurrentHashMap<String, Integer> Modules = new ConcurrentHashMap<String, Integer>();
		for (Entry<String, Integer> e : to_clone.entrySet())
		{
			java.lang.String s = "" + e.getKey();
			Modules.put(s, e.getValue());
		}
		return Modules;
	}
}
