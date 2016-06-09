package edu.uth.clamp.support;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.uima.util.FileUtils;

import edu.uth.clamp.nlp.core.ClampSentDetector;
import edu.uth.clamp.nlp.core.ClampTokenizer;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.tokenize.Tokenizer;

public class DemoTokenizer {

	
	public static void main( String[] argv ) throws IOException {
		SentenceDetector sentDetector = ClampSentDetector.getDefault();
		Tokenizer tokenizer = ClampTokenizer.getDefault();

		File inputDir = new File( "data/input/mtsamples/" );
		String outputDir = "data/output/mtsamples/";
		for( File file : inputDir.listFiles() ) {
			if( !file.isFile() 
					|| file.getName().startsWith( "." )
					|| !file.getName().endsWith( ".txt" ) ) {
				continue;
			}
			String content = FileUtils.file2String( file );
			
			FileWriter writer = new FileWriter( new File( outputDir + File.separator + file.getName() ) );
			for( String sent : sentDetector.sentDetect( content ) ) {
				// detect sentence;
				for( String token : tokenizer.tokenize( sent ) ) {
					writer.write( token + " " );
				}
				writer.write( "\n" );
			}
			writer.close();
			System.out.println( file.getName() + " is processed.." );
		}
		
		return;
	}
}
