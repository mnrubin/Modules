import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A webpage containing many course descriptions
 * @author michael
 *
 */
public class Webpage {

	private LinkedList<CourseDescription> descriptions;

	/**
	 * Contructs a webpage by parsing and holding the course descriptions
	 * @param url
	 * @throws IOException
	 * @throws WebsiteNotSupportedException 
	 */
	public Webpage(String url) throws IOException, WebsiteNotSupportedException
	{
		descriptions = new LinkedList<CourseDescription>();
		Document doc = Jsoup.connect(url).get();

		if(url.indexOf("ucsd") != -1)
		{
			Elements courseDescriptions = doc.select(".course-descriptions");
			for(Element e : courseDescriptions)
			{
				CourseDescription cd = new CourseDescription(e.text());
				//System.out.println(e.text());
				addDescription(cd);
			}
		}
		else if(url.indexOf("berkeley") != -1)
		{
			Elements courseDescriptions = doc.select("html body table tbody tr td font p");
			for(Element e : courseDescriptions)
			{
				CourseDescription cd = new CourseDescription(e.text());
				System.out.println(e.text());
				addDescription(cd);
			}
		}
		else throw new WebsiteNotSupportedException(url);

	}

	private void addDescription(CourseDescription c)
	{
		descriptions.add(c);
	}

	public LinkedList<CourseDescription> getCourseDescriptions()
	{
		return descriptions;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(CourseDescription cd : descriptions)
		{
			sb.append(cd.toString() + ". ");
		}
		return sb.toString();
	}

}
