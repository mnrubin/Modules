import java.util.ArrayList;
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
	
	  public static int getEdgeVal(ArrayList<String> nodes, Node pre_node, Node post_node, int[][] result) {
		  int pre_index = nodes.indexOf(pre_node.name);
		  int post_index = nodes.indexOf(post_node.name);
		  return result[pre_index][post_index];
	  }
}
