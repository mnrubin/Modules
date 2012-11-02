import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FilterMaps {
	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> phrases = new HashMap<String, Integer>();
		phrases.put("Ediscover", 5);
		String opp_subject = "HIST";
		String opp_url = "http://www.ucsd.edu/catalog/courses/"+opp_subject+".html";
		Document opp_doc = Jsoup.connect(opp_url).get();
		String doc_text = opp_doc.text();
		for (String key : phrases.keySet()) 
		{
			if (doc_text.indexOf(key) == -1)
			{
				phrases.remove(key);
			}
			String[] words = key.split(" ");
			String searchterm = "";
			for (int i=0; i<words.length; ++i)
			{
				searchterm += words[i];
				if (i < words.length - 1)
					searchterm += "_";
			}
			Response wiki_response = Jsoup.connect("http://en.wikipedia.org/wiki/"+searchterm).ignoreHttpErrors(true).execute();
			if (wiki_response.statusCode() == 404) {
				phrases.remove(key);
			}
		}
	}
}
