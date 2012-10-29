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


/**
 * A webpage, containing all the words on it and its sub-links
 * @author michael
 *
 */
public class Webpage {

	TreeMap<String, Integer> words;

	public Webpage(String url) throws IOException
	{

		Document doc;
		doc = Jsoup.connect(url).get();
		String txt = doc.text();

		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		ValueComparator vc = new ValueComparator(hm);
		words = new TreeMap<String, Integer>(vc);

		for(String word : txt.split(" "))
		{
			if(hm.containsKey(word))
			{
				hm.put(word, hm.get(word)+1);
			}
			else 
			{
				hm.put(word, 1);
			}
		}

		/* sub links */

		LinkedList<String> linkList = new LinkedList<String>();
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			linkList.add(link.attr("abs:href"));
		}
		for(String link : linkList)
		{
			Document innerDoc;
			try {
				innerDoc = Jsoup.connect(link).get();
				String txt2 = innerDoc.text();
				for(String word : txt2.split(" "))
				{
					if(hm.containsKey(word))
					{
						hm.put(word, hm.get(word)+1);
					}
					else 
					{
						hm.put(word, 1);
					}
				}
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}

		words.putAll(hm);
		//System.out.println(words.toString());

	}//end constructor

	public String toString()
	{
		return this.words.toString();
	}

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
