import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Logger implements LoggerInterface {
	
	boolean debug=true;
	FileWriter bw;
	PrintWriter pw;
	File file;
	public Logger(String s){
		 file=new File(s+".txt");
		 try {
				bw=new FileWriter(file);
				pw=new PrintWriter(bw);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Override
	public void log(String s) {
		if(!debug)
			return;
		try{
			pw.println(s);
			pw.flush();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
