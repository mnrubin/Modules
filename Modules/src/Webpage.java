import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
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
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		ValueComparator vc = new ValueComparator(hm);
		words = new TreeMap<String, Integer>(vc);
		
		/* main link */
		Document doc = Jsoup.connect(url).get();
		String text = doc.text();
		parseWebpage(text, hm);

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
				text = innerDoc.text();
				parseWebpage(text, hm);
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

	public TreeMap<String, Integer> getWords()
	{
		return this.words;
	}


	/**
	 * Filters and puts words in HashMap hm, to later be put in TreeMap words
	 * @param txt
	 */
	private void parseWebpage(String text, HashMap<String, Integer> hm)
	{
		/* take only letters and spaces */
		String s = text;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) 
		{
			Character ch = s.charAt(i);
			if(Character.isSpaceChar(ch) || ((ch>=65)&&(ch<=90)) || ((ch>=97)&&(ch<=122))) 
			{
				sb = sb.append(Character.toLowerCase(s.charAt(i)));
			}
		}
		String txt = sb.toString();

		/* split  and place trimmed words in hashmap */
		StringTokenizer st = new StringTokenizer(txt, " ", false);
		while(st.hasMoreTokens())
		{
			String word = st.nextToken().trim();
			if(!Character.isSpaceChar(word.charAt(0)) && word.length() > 0 && word.length() < 15)
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
		}
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
