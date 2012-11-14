import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class PosTagger {

	public void FilterByPos(ConcurrentHashMap<String,Integer> modules) throws IOException {
	    POSModel model = new POSModelLoader().load(new File("en-pos-maxent.bin"));
	    //PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
	    POSTaggerME tagger = new POSTaggerME(model);
	    
	    for (String k: modules.keySet()) {
		    if (k.trim().indexOf(" ") == -1) { //only takes 1 word phrases
		
		        String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(k);
		        String[] tags = tagger.tag(whitespaceTokenizerLine);
		
		        POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
		        int index = 0;
		        for(String tag : sample.getTags())
		        {
		        	if(!tag.equals("NNS") && !tag.equals("NN") && !tag.equals("NNP"))
		        	{
		        		modules.remove(whitespaceTokenizerLine[index].trim());
		        	}
		        	else {
		        		System.out.println(tag);
		        		System.out.println(whitespaceTokenizerLine[index]);
		        	}
		        	index++;
		        }
		        	
		        //System.out.println(sample.toString());
		
		        //perfMon.incrementCounter();
		    }
		    //perfMon.stopAndPrintFinalResult();
	    }
	}
}