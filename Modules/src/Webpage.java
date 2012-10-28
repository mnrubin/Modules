import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
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

		String s = url;
		Document doc = Jsoup.connect(s).get();

		LinkedList<String> linkList = new LinkedList<String>();

		String txt = doc.text();
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		ValueComparator bv = new ValueComparator(hm);
		words = new TreeMap<String, Integer>(bv);

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

	}

}
