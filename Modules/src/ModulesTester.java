import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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



		/* output to file*/
		try{
			// Create file 
			FileWriter fstream = new FileWriter("out.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			for(Webpage w : webpages)
			{
				out.write(w.toString());
			}
			//Close the output stream
			out.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}


	} //end main



} //end class


