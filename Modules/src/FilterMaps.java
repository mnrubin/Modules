import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.ws.http.HTTPException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FilterMaps {
	
	public static final int PAGE_NOT_FOUND = 404;
	
	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> phrases = new HashMap<String, Integer>();
		phrases.put("Ediscover", 5);
		String opp_subject = "HIST";
		String opp_url = "http://www.ucsd.edu/catalog/courses/"+opp_subject+".html";
		//Filter_By_Opp_Url(phrases, opp_url);
		//Filter_By_Wiki(phrases);
	}
	public static void Filter_By_Opp_Url(ConcurrentHashMap<String, Integer> phrases, Webpage w2) {
		for (String k : phrases.keySet()) 
		{
			if (w2.toString().indexOf(k) != -1)
			{
				phrases.remove(k);
			}
			if (phrases.containsKey(k) && phrases.get(k) == 1)
			{
				//phrases.remove(k);
			}
		}
	}
	public static void Filter_By_Wiki(ConcurrentHashMap<String, Integer> phrases) {
		for (String key : phrases.keySet()) 
		{
			//if (phrases.get(key) == 1)
				//continue;
			System.out.print(".");
			String[] words = key.split(" ");
			String searchterm = "";
			for (int i=0; i<words.length; ++i)
			{
				searchterm += words[i];
				if (i < words.length - 1)
					searchterm += "_";
			}
			Response wiki_response = null;
			Response wiki_responseWithExtraS = null;
			try {
				System.out.print("/");
				wiki_response = Jsoup.connect("http://en.wikipedia.org/wiki/"+searchterm).timeout(0).ignoreHttpErrors(true).execute();
				wiki_responseWithExtraS = Jsoup.connect("http://en.wikipedia.org/wiki/"+searchterm+"s").timeout(0).ignoreHttpErrors(true).execute();
				//System.err.println(key+" : "+wiki_response.statusCode()+" : "+wiki_response2.statusCode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int status=wiki_response.statusCode();
			int statusWithExtraS=wiki_responseWithExtraS.statusCode();
			if (status == PAGE_NOT_FOUND && statusWithExtraS==PAGE_NOT_FOUND) {
				phrases.remove(key);
			}else if(status==PAGE_NOT_FOUND && statusWithExtraS!=PAGE_NOT_FOUND){
				int tempVal=phrases.get(key);
				phrases.remove(key);
				key=key.trim()+"s";
				phrases.put(key, tempVal);
			}else if(status!=PAGE_NOT_FOUND && statusWithExtraS!=PAGE_NOT_FOUND)
			{
				int tempVal=phrases.get(key);
				phrases.remove(key);
				key=key.trim()+"s";
				phrases.put(key, tempVal);
			}
		}
	}
	
}
