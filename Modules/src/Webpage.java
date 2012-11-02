import java.util.LinkedList;


public class Webpage {

	private LinkedList<CourseDescription> descriptions;
	
	
	public Webpage()
	{
		descriptions = new LinkedList<CourseDescription>();
	}
	
	public void addDescription(CourseDescription c)
	{
		descriptions.add(c);
	}
	
	public LinkedList<CourseDescription> getCourseDescriptions()
	{
		return descriptions;
	}
	
}
