import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
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
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		/* department we are making modules for */
		String department = args[0];
		String url = "http://www.ucsd.edu/catalog/courses/"+department+".html";
		
		Webpage w = new Webpage(url);
		
		/* different department to check against */
		String department2 = args[1];
		String url2 = "http://www.ucsd.edu/catalog/courses/"+department2+".html";
		
		Webpage w2 = new Webpage(url2);
		
		PhraseParserImpl ppi = new PhraseParserImpl(w);
		
		/*String subject = "Computer Science";
		String link_css = "html body div#wrapper div#content p span.courseFacLink a";
		String base_url = "http://ucsd.edu/catalog/front/courses.html";
		Document document = Jsoup.connect(url).get();
		Elements elements = document.select(link_css);
		
		for (Element el : elements)
		{
			System.out.println(el.text());
		}
		System.out.println(elements.size());
		*/
		
		ConcurrentHashMap<String, Integer> phrasemap = ppi.getPhrase(2);
		System.out.println(phrasemap);
		System.out.println(phrasemap.size());
		FilterMaps.Filter_By_Opp_Url(phrasemap, w2);
		System.out.println(phrasemap);
		System.out.println(phrasemap.size());
		FilterMaps.Filter_By_Wiki(phrasemap);
		System.out.println("");
		System.out.println(phrasemap);
		System.out.println(phrasemap.size());
		
		/*
		System.out.println(w);
		System.out.println(w2);
		
		FileWriter fstream = new FileWriter("out.txt");
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(w.toString()+" ");
		out.write(w2.toString());*/
	}

}
