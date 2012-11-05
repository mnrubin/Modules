/**
 * COntains the text of a course description
 * @author michael
 *
 */
public class CourseDescription {

	private String desc;
	
	public CourseDescription(String text)
	{
		desc = text;
	}
	
	
	public String getDescription()
	{
		return desc;
	}
	
	public String toString()
	{
		return getDescription();
	}
	
}
