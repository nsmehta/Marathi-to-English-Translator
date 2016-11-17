package com.translator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NumParsing extends DefaultHandler{
	
	String new_seq = null;
	String str;
	Boolean bInf=false;
	String data;
	
	public NumParsing(String temp)
	{
		System.out.println("temp printed"+temp);
		str=temp;
		bInf=false;
	}
	
	public String getNumber()
	{
		return data;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//System.out.println("in start element of num parsing+"+str);
		if (qName.equalsIgnoreCase("rule")) {
			new_seq = attributes.getValue("sequence");

		}
		if (str.equals(new_seq) && qName.equalsIgnoreCase("rule")) {
			System.out.println("NUMBER= " + new_seq);
			// System.out.println("start element: "+qName);
			bInf = true;
			
		}
		
	}

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {
		if (bInf) {
			data = (new String(ch, start, length));
			System.out.println("NUMBER is: " + data);
			bInf = false;
		}

	}

}
