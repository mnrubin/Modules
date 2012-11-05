import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class ReferenceDictCS implements ReferenceDict {
	HashSet<String> map;
	Document doc;
	
	public ReferenceDictCS(String url) throws IOException{
		map=new HashSet<String>();
		doc = Jsoup.connect(url).timeout(0).get();
		String s=doc.text();
		addToMap(s);
		System.out.println(map);
	}
	
	public ReferenceDictCS(){
		
		
	}
	
	private void addToMap(String s){
		String[] raw=s.split("\\.");
		for(String ss: raw){
			if(ss.matches(".+:.+"))
				ss=ss.split(":")[1];
			if(ss.matches(".+ - .+"))
				map.add(ss.split(" - ")[0].trim().toLowerCase());
		}
	}

	@Override
	public boolean containsKey(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getValue(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] s) throws IOException{
		ReferenceDictCS rcs=new ReferenceDictCS("http://www.labautopedia.org/mw/index.php/List_of_programming_and_computer_science_terms");
	}
}
