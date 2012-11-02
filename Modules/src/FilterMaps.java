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
		Filter_By_Opp_Url(phrases, opp_url);
		Filter_By_Wiki(phrases);
	}
	public static void Filter_By_Opp_Url(HashMap<String, Integer> phrases, String opp_url) {
		Document opp_doc = null;
		try {
			opp_doc = Jsoup.connect(opp_url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String doc_text = opp_doc.text();
		for (String key : phrases.keySet()) 
		{
			if (doc_text.indexOf(key) == -1)
			{
				phrases.remove(key);
			}
		}
	}
	public static void Filter_By_Wiki(HashMap<String, Integer> phrases) {
		for (String key : phrases.keySet()) 
		{
			String[] words = key.split(" ");
			String searchterm = "";
			for (int i=0; i<words.length; ++i)
			{
				searchterm += words[i];
				if (i < words.length - 1)
					searchterm += "_";
			}
			Response wiki_response = null;
			try {
				wiki_response = Jsoup.connect("http://en.wikipedia.org/wiki/"+searchterm).ignoreHttpErrors(true).execute();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (wiki_response.statusCode() == 404) {
				phrases.remove(key);
			}
		}
	}
	
}
