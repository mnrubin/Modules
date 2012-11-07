import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * @args[0] Website we are making modules for
 * @args[1] Website for diff dept we are checking against
 * 
 * EXAMPLE:   CSE HIST
 * 
 * @author michael
 *
 */
public class CurrentTester {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws WebsiteNotSupportedException 
	 */
	public static void main(String[] args) throws IOException, WebsiteNotSupportedException {
		// TODO Auto-generated method stub

		/* department we are making modules for */
		String subject = "Mathematics";
		String opp_subject = "History";
		String berk_url = get_Berkeley_listings(subject);
		String ucsd_url = get_UCSD_listings(subject);
		String berk_opp_url = get_Berkeley_listings(opp_subject);
		String ucsd_opp_url = get_UCSD_listings(opp_subject);		
		
		Webpage ucsd_w = new Webpage(ucsd_url);
		Webpage berk_w = new Webpage(berk_url);
		Webpage ucsd_opp_w = new Webpage(ucsd_opp_url);
		Webpage berk_opp_w = new Webpage(berk_opp_url);
		
		PhraseParserImpl ucsd_ppi = new PhraseParserImpl(ucsd_w);
		PhraseParserImpl berk_ppi = new PhraseParserImpl(berk_w);
		
		ArrayList<ConcurrentHashMap<String, Integer>> ucsd_maps = ucsd_ppi.getPhrase();
		ArrayList<ConcurrentHashMap<String, Integer>> berk_maps = berk_ppi.getPhrase();
		
		for (int i=0; i<ucsd_maps.size(); ++i)
		{
			FilterMaps.Filter_By_Opp_Url(ucsd_maps.get(i), ucsd_opp_w);
			FilterMaps.Filter_By_Opp_Url(berk_maps.get(i), berk_opp_w);
			ConcurrentHashMap<String, Integer> modules = deepclone(ucsd_maps.get(i));
			System.out.println(modules);
			modules.keySet().retainAll(berk_maps.get(i).keySet());
			System.out.println(modules);
			FilterMaps.Filter_By_Wiki(modules);
			System.out.println();
			System.out.println(modules);
		}

	}
	public static ConcurrentHashMap<String, Integer> deepclone(ConcurrentHashMap<String, Integer> to_clone)
	{
		ConcurrentHashMap<String, Integer> Modules = new ConcurrentHashMap<String, Integer>();
		for (Entry<String, Integer> e : to_clone.entrySet())
		{
			java.lang.String s = "" + e.getKey();
			Modules.put(s, e.getValue());
		}
		return Modules;
		
	}
	public static String get_UCSD_listings(String subject)
	{
		String link_css = "html body div#wrapper div#content p span.courseFacLink a";
		String base_url = "http://ucsd.edu/catalog/front/courses.html";
		Document document = null;
		try {
			document = Jsoup.connect(base_url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements elements = document.select(link_css);
		String course_link = "";
		if (subject.matches("Biology"))
		{
			course_link = "http://www.ucsd.edu/catalog/courses/BIOL.html";
			return course_link;
		}
		for (Element el : elements)
		{
			if (el.attr("title").indexOf(subject) != -1 && el.text().indexOf("courses") == 0) {
				course_link = "http://www.ucsd.edu/catalog/courses/" + el.attr("href").substring(11);
				if (el.attr("title").length() == subject.length())
					return course_link;
			}
		}
		return course_link;
	}
	public static String get_Berkeley_listings(String subject)
	{
		String link_css = "html body center table tbody tr td p font a";
		String base_url = "http://general-catalog.berkeley.edu/gc/curricula.html";
		Document document = null;
		try {
			document = Jsoup.connect(base_url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String course_link = "";
		Elements elements = document.select(link_css);
		for (Element el : elements)
		{
			if (el.text().indexOf(subject) != -1) {
				int index = el.attr("href").indexOf("=");
				if (index != -1) {
					course_link = "http://general-catalog.berkeley.edu/catalog/gcc_list_crse_req?p_dept_cd=" + el.attr("href").substring(index+1) + "&p_path=l";
					if (el.text().length() == subject.length())
						return course_link;
				}
			}
		}
		return course_link;
	}
	
	public static void testDict(ConcurrentHashMap<String, Integer> map) throws IOException{
		ReferenceDictCS rcs=new ReferenceDictCS("http://www.labautopedia.org/mw/index.php/List_of_programming_and_computer_science_terms");
		HashSet<String> result=new HashSet<String>();
		for(String s: map.keySet()){
			if(rcs.containsKey(s))
					result.add(s);
		}
		System.out.println("----------Using external dictionary:-------");
		System.out.println(result);
		System.out.println("---------END-----------");
	}

}
