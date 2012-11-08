import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;


public class PhraseParserImpl implements PhraseParser {

	private LinkedList<String> des;
	private ArrayList<ConcurrentHashMap<String, Integer>> maps;
	
	public PhraseParserImpl(Webpage w){
		des=new LinkedList<String>();
		maps=new ArrayList<ConcurrentHashMap<String, Integer>>();
		parse(w);
	}

	private void parse(Webpage w) {
		LinkedList<CourseDescription> cdes=w.getCourseDescriptions();
		for(CourseDescription c:cdes){
			des.add(c.getDescription());
		}
		generateMap(1);
		generateMap(2);
		generateMap(3);
		generateMap(4);
	}
	
	private void generateMap(int length){
		ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<String, Integer>();
		ArrayList<String> phrases=generatePhrases(length);
		for(String s: phrases){
			if(map.containsKey(s))
				map.put(s, map.get(s)+1);
			else map.put(s, 1);
		}
		maps.add(map);
	}
	
	private ArrayList<String> generatePhrases(int length){
		ArrayList<String> result=new ArrayList<String>();
		ArrayList<String> temp=new ArrayList<String>();
		for(String s: des){
			String[] array=s.split("\\.|,|;|:|[0-9]+[^ ]*");
			for(String ss: array){
				temp.add(ss.trim().toLowerCase());
			}
		}
		for(String s: temp){
			String[] array=s.split(" ");
			int index=0;
			while(array.length-index>=length){
				StringBuilder phrase=new StringBuilder();
				for(int i=index; i<index+length; i++){
					if(array[i].trim().endsWith("s") && i == index+length-1 /*last word in phrase*/){
						array[i]=array[i].substring(0,array[i].length()-1);
					}
					phrase.append(array[i]+" ");
				}
				result.add(phrase.toString());
				index++;
			}
		}
		return result;
	}
	
	private static boolean find(ArrayList<ConcurrentHashMap<String, Integer>> maps, String s,int i){
		boolean found=false;
		for(int j=i+1;j<maps.size();j++){
			for(String temp: maps.get(j).keySet()){
				if(temp.indexOf(s)!=-1)
					found=true;
			}
		}
		return found;
	}
	
	@Override
	
	public ConcurrentHashMap<String, Integer> getPhrases(int length) {
		// TODO Auto-generated method stub
		return maps.get(length-1);
	}
	
	public ArrayList<ConcurrentHashMap<String, Integer>> getPhrase(){
		return maps;
	}

	@Override
	public String getDescription(String title) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void filterRepeated(ArrayList<ConcurrentHashMap<String, Integer>> maps){
		for(int i=0;i<maps.size()-1;i++){
			for(String s: maps.get(i).keySet()){
				if(find(maps,s,i))
					maps.get(i).remove(s);
			}
		}
	}

}
