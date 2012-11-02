import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class NewTester {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String subject = args[0];
		String url = "http://www.ucsd.edu/catalog/courses/"+subject+".html";
		System.out.println("...");
		File f = new File("partialcs.list");
		Document doc = Jsoup.connect(url).get();
		Elements courseDescriptions = doc.select(".course-descriptions");
		for(Element e : courseDescriptions)
		{
			System.out.println(e.text());
		}
		
		
	}

}
