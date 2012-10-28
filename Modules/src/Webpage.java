import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Webpage {

	TreeMap<String, Integer> words;

	public Webpage(String url) throws IOException
	{

		Document doc = Jsoup.connect(url).get();

		LinkedList<String> linkList = new LinkedList<String>();

		String txt = doc.text();
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		ValueComparator vc = new ValueComparator(hm);
		words = new TreeMap<String, Integer>(vc);

		for(String ss : txt.split(" "))
		{
			if(hm.containsKey(ss))
			{
				hm.put(ss, hm.get(ss)+1);
			}
			else 
			{
				hm.put(ss, 1);
			}
		}


		/* sub links */


		Elements links = doc.select("a[href]");
		for (Element link : links) {
			linkList.add(link.attr("abs:href"));
		}
		for(String str : linkList)
		{
			Document doc2;


			doc2 = Jsoup.connect(str).get();

			String txt2 = doc2.text();
			for(String ss : txt2.split(" "))
			{
				if(hm.containsKey(ss))
				{
					hm.put(ss, hm.get(ss)+1);
				}
				else 
				{
					hm.put(ss, 1);
				}
			}


		}

		words.putAll(hm);
		System.out.println(words.toString());

	}//end constructor

}//end class



/**
 * Sort by highest to lowest word-count
 * @author michael
 * from StackOverflow
 */
class ValueComparator implements Comparator<String> {

	Map<String, Integer> base;
	public ValueComparator(Map<String, Integer> base) {
		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with equals.    
	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}
