import java.io.File;
import java.io.FileNotFoundException;
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

public class ModulesTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("...");
		File f = new File("cs.list");
		Scanner sc;
		LinkedList<Webpage> webpages = new LinkedList<Webpage>();
		try {
			sc = new Scanner(f);

			while(sc.hasNextLine() == true)
			{
				Webpage w = new Webpage(sc.nextLine()); 
				webpages.add(w);
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*Scanner sc;
		TreeMap<String, Integer> tm;
		try {
			sc = new Scanner(f);
			String s = sc.nextLine();
			Document doc = Jsoup.connect(s).get();

			LinkedList<String> linkList = new LinkedList<String>();


			String txt = doc.text();
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
			ValueComparator bv = new ValueComparator(hm);
			tm = new TreeMap<String, Integer>(bv);

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


			 sub links 


			Elements links = doc.select("a[href]");
			for (Element link : links) {
				linkList.add(link.attr("abs:href"));
			}
			for(String str : linkList)
			{
				Document doc2;
				try {
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
				catch(Exception e){;}
			}

			tm.putAll(hm);
			System.out.println(tm.toString());




			// Elements links = doc.select("a[href]");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/




		//			Elements newsHeadlines = doc2.select("#mp-itn b a");
		//			System.out.println(newsHeadlines.toString());


	} //end main



} //end class

//from StackOverflow
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
