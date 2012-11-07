import java.io.IOException;


public class Tester {

	public static void main(String[] args) throws IOException, WebsiteNotSupportedException{
		if(args.length!=2)
			throw new RuntimeException("Usage: <subject> <opp_subject>");
		ModuleFinder mf=new ModuleFinder(args[0],args[1]);
	}
}
