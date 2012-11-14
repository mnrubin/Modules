import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;
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
	
	  public static int getEdgeVal(ArrayList<String> nodes, String pre_node, String post_node, int[][] result) {
		  int pre_index = nodes.indexOf(pre_node);
		  int post_index = nodes.indexOf(post_node);
		  return result[pre_index][post_index];
	  }
	  
	  public static void blackList(String fn) {
		  File file = new File(fn);
		  File blacklist = new File("blacklist");
		  File temp = new File("temp");
		  PrintWriter fw = null;
		  try {
			fw = new PrintWriter(new FileWriter(temp));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  try {
			Scanner sc = new Scanner(file);
			Scanner sc2 = new Scanner(blacklist);
			HashSet hs = new HashSet();
			while (sc2.hasNextLine()) {
				hs.add(sc2.nextLine());
			}
			String[] phrases;
			while(sc.hasNextLine())
			{
				String line = sc.nextLine();
				if(line.contains("{}"))
				{
					continue;
				}
				String phrase=line.split("\\{|\\}")[1];
				phrases=phrase.split("( *=[0-9]+, )|( *=[0-9]+)"); //now we have each string separately
				System.out.println(Arrays.toString(phrases));
				ArrayList<String> phraseslist = new ArrayList<String>(Arrays.asList(phrases));
				for(String s:phrases) {
					if (hs.contains(s)) {
						phraseslist.remove(s);
					}
				}
				for (String s: phraseslist) {
					fw.println(s);
				}
			}
			temp.renameTo(new File(fn+"2"));
			fw.close();
			File file2 = new File(fn+"2");
			temp.renameTo(file2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  }

}
