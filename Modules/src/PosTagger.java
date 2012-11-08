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
		    if (k.trim().indexOf(" ") == -1) {
		
		        String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(k);
		        String[] tags = tagger.tag(whitespaceTokenizerLine);
		
		        POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
		        System.out.println(sample.toString());
		
		        //perfMon.incrementCounter();
		    }
		    //perfMon.stopAndPrintFinalResult();
	    }
	}
}