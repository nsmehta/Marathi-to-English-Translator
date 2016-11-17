
package com.translator;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

public class RuleProcessing extends HttpServlet{

	private static final long serialVersionUID = 1L;
	Word attrib[]=new Word[15];
	String LCAT[] = new String[10];
	int sequence[] = new int[10];
	String transwords[]=new String [15];
	int count=0;
	int line_num=0;
	//Word attrib=new  Word[15];
	public RuleProcessing(String reset){
		System.out.println("reset in RuleProcessing:"+reset);
	}
	public RuleProcessing(String translatedWords[], Word attribute[], int count1,int line_num)
			throws Exception {
		count=count1;
		this.line_num=line_num;
		System.out.println("+++++count in processing is="+count1 +" "+count);
		System.out
				.println("RuleProcessing.java: Translated Words, attributes are: ");
		for (int i = 0; attribute[i]!=null; i++) {
			System.out.println(translatedWords[i] + " attribute= "
					+ attribute[i].LCAT);
			LCAT[i] = new String();
			LCAT[i] = attribute[i].LCAT;
			System.out.println("LCAT=*"+LCAT[i]+"*");
			transwords=translatedWords;
			attrib=attribute;
			
		}
	
		try {
			System.out.println("[][][][][][][in try of rule processing");
			File inputFile = new File(
					"/home/adeshgupta/workspace/Translator3.1/WebContent/Rules.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			
			RuleParsing userhandler = new RuleParsing(LCAT,transwords,attrib,count,line_num);
				saxParser.parse(inputFile, userhandler);
				userhandler.goto_redirect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		/*if (LCAT[0].equals("pn") && (LCAT[1].equals("n")|| LCAT[1].equals("unk")) && LCAT[2].equals("v")) {
			sequence[0] = 0;
			sequence[1] = 2;
			sequence[2] = 1;
			System.out.println("in if");
		}
	*/	// remaining rules....

		// rules end

		
		// displaying it on the page:

	}

	public RuleProcessing() {
		super();
	}

}
