package edu.uth.clamp.support;

import java.io.IOException;

import org.apache.uima.UIMAException;
import edu.uth.clamp.nlp.structure.ClampNameEntity;
import edu.uth.clamp.nlp.structure.ClampRelation;
import edu.uth.clamp.nlp.structure.ClampSentence;
import edu.uth.clamp.nlp.structure.ClampToken;
import edu.uth.clamp.nlp.structure.Document;
import edu.uth.clamp.nlp.structure.XmiUtil;

public class DemoDocumentParser {

	public static void main( String[] argv ) throws UIMAException, IOException {
		Document doc = new Document( "data/xmi/mtsamples/sample_1195.xmi" );
		
		System.out.println( "Filename of the document is [" + doc.getFileName() + "]" );
		System.out.println( "file length of the document is [" + doc.getFileContent().length() + "]" );
		System.out.println( "section count:[" + doc.getSections().size() + "]" );
		System.out.println( "sentence count:[" + doc.getSentences().size() + "]" );
		System.out.println( "token count:[" + doc.getTokens().size() + "]" );
		System.out.println( "Named entity count:[" + doc.getNameEntity().size() + "]" );
		System.out.println( "Relation count:[" + doc.getRelations().size() + "]" );
		
		for( ClampSentence sent : doc.getSentences() ) {
			
			System.out.println( "Sentence=[" + sent.textStr() + "], section=[" + sent.getSectionHeader() + "]" );			
			System.out.println( "Tokens:" );
			for( ClampToken token : sent.getTokens() ) {
				System.out.println( "\t" + token.getBegin() + "\t" + token.getEnd() + "\t" + token.textStr() + "\t" + token.getPartOfSpeech() + "\t" );
			}
			
			System.out.println( "Named entities:" );
			for( ClampNameEntity cne : sent.selectNE() ) {
				System.out.println( "\t" + cne.getBegin() + "\t" + cne.getEnd() + "\t" + cne.textStr() 
					+ "\t" + cne.getSemanticTag() 
					+ "\t" + cne.getUmlsCui()
					+ "\t" + cne.getUmlsCuiDesc()
					+ "\t" + cne.getAssertion() );
			}
			
			System.out.println( "Relations:" );
			for( ClampRelation rel : XmiUtil.selectRelation( doc.getJCas(), sent.getBegin(), sent.getEnd() ) ) {
				System.out.println( "\tsem=[" + rel.getSemanticTag() + "]. from=[" + rel.getEntFrom().textStr() + "],\tto=[" + rel.getEntTo().textStr() + "]" );
			}
			
			System.out.println( "" );
		}
		return;
	}

}
