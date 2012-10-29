import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
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
				try{
					Webpage w = new Webpage(sc.nextLine()); 
					webpages.add(w);
				} catch (Exception e) {

				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

		/* find intersection of webpages */
		LinkedList<Set> toIntersect = new LinkedList<Set>(); //Sets of words in webpages. length is # of webpages
		for(Webpage w : webpages)
		{
			toIntersect.add(w.getWords().entrySet());
		}
		Set<String> matchingKeys = new HashSet<String>(toIntersect.getFirst());  //Set of intersecting words. length is # of words in all docs
		for(int i = 1; i < toIntersect.size(); i++)
		{
			try {
				matchingKeys.retainAll(toIntersect.get(i));
			}
			catch(Exception e) {
				System.out.println("retainAll returned null pointer");
				e.printStackTrace();
			}
		}
		//LinkedList<Integer> wordTotal = new LinkedList<Integer>();//number of times intersecting words appear in total. length is # of words in all webpages
		//Now you can use these keys to iterate the sets 
		HashMap<String, Integer> intersection = new HashMap<String, Integer>(); //hashmap of words in all webpages. 
		for (String key : matchingKeys)  
		{  
			int[] tempResult = new int[toIntersect.size()]; //Number of times each word appears in each doc. length is # of webpages
			for(int i = 0; i < toIntersect.size(); i++)
			{
				tempResult[i] = webpages.get(i).getWords().get(key);  
			}

			int wordTotal = 0;
			for(Integer intgr : tempResult)
			{
				wordTotal += intgr;  
			}
			//jj++;
			
			intersection.put(key, wordTotal);
		}


		/* output to file*/
		try{
			// Create file 
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			//for(Webpage w : webpages)
			//{
				out.write(intersection.toString());
			//}
			//Close the output stream
			out.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}


	} //end main



} //end class


