package com.translator;

import java.io.File;

import javax.servlet.http.HttpServlet;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class NumProcessing extends HttpServlet{

	String number;
	public NumProcessing()
	{
	
		
	}
	public String send(String temp)
	{
		number=new String("number");
		try {
			System.out.println("IN NUM PROCESSING");
			File inputFile = new File(
					"/home/adeshgupta/workspace/Translator3.1/WebContent/Num.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			
			NumParsing userhandler = new NumParsing(temp);
				saxParser.parse(inputFile, userhandler);
				number=userhandler.getNumber();
				System.out.println("NUMBER FOUND IN NUM PROCESSING= "+number);
				//userhandler.goto_redirect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
		return number;
	}
}
