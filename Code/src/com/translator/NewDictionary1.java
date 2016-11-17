package com.translator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

@WebServlet("/NewDictionary1")
public class NewDictionary1 extends HttpServlet {
	private static final long serialversionUID = 1L;
	String number=new String();
	public NewDictionary1(String reset){
		System.out.println("reset in NewDicitionary1:"+reset);
	}
	public NewDictionary1() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		System.out.println("in init of newdictionary");
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			File inputFile = new File(
					"/home/adeshgupta/workspace/Translator3.1/WebContent/NewDictionary.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxparser = factory.newSAXParser();
			File openfile = new File(
					"/home/adeshgupta/workspace/Translator3.1/temp1.txt");
			FileReader fr = new FileReader(openfile);
			LineNumberReader lnr = new LineNumberReader(fr);
			int line_num = 0;
			while (lnr.readLine() != null) {
				line_num++;
			}
			// to find count
			// line_num--;
			System.out.println("@@@@@in new dict1 Total lines" + line_num);
			lnr.close();
			String org_sen[] = new String[15];

			String t = request.getParameter("input");
			org_sen = t.split(" ");
			// file logic
			String line, temp[] = null;
			File openfile1 = new File(
					"/home/adeshgupta/workspace/Translator3.1/temp1.txt");
			FileReader readFrom = new FileReader(openfile1);
			BufferedReader br = new BufferedReader(readFrom);
			// while(!br.readLine().equals(null))

			System.out.println("@@@@@orig_sen len=" + org_sen.length);
			// new code
			for (int i = 0; i < org_sen.length; i++){
				System.out.println("ORG SEN="+org_sen[i]);
			}
			for (int i = 0; i < org_sen.length; i++) {

				line = br.readLine();
				System.out.println("************line=\n" + line);
				// j=0;
				// if last word is equal to hote
				System.out.println("000000i=" + i);
				if (i==line_num) // no attribs of ahe found
				{
					System.out.println("line is null");
					
					if ((org_sen[i].equals("होती"))
							|| (org_sen[i].equals("होतो"))) {
						temp[0] = "होती";
					} else if ((org_sen[i].equals("आहे"))) {
						System.out.println("in ahee");
						temp[0] = "आहे";
					} else if ((org_sen[i].equals("होते"))) {
						temp[0] = "होते";
					} else if ((org_sen[i].equals("होता"))) {
						System.out.println("in hota");
						temp[0] = "होता";
					}
					 else if ((org_sen[i].equals("जाते"))) {
							System.out.println("in जाते");
							temp[0] = "जाते";
						}
					 else if((org_sen[i].equals("येते"))){
						 System.out.println("in येते");
						 temp[0]="येते";
					 }
					 else if((org_sen[i].equals("आहेत"))){
						 System.out.println("in आहेत");
						 temp[0]="आहेत";
					 }
					 else if((org_sen[i].equals("जाते"))){
						 System.out.println("in जाते");
						 temp[0]="जाते";
					 }
					 else if(org_sen[i].equals("असते")){
						 System.out.println("in असते");
						 temp[0]="असते";
					 }else if(org_sen[i].equals("आले")){
						 System.out.println("in आले");
						 temp[0]="आले";
					 }
					
				}
				else {
					System.out.println("splitting !!!");
					temp = line.split(",");
				}
				
				/*
				 * if((!org_sen[i].equals("होती"))||(!org_sen[i].equals("ahe"))||
				 * (!org_sen[i].equals("hoto"))||(!org_sen[i].equals("hote"))){
				 * System.out.println("splitting !!!"); temp =line.split(","); }
				 * else if() { temp[0]="होती"; }
				 */
	
				if(temp[1].equals("num"))
				{
					System.out.println("NUMBER FOUND!!!"+org_sen[i]);
					char digits[]=org_sen[i].toCharArray();
					if(digits[0]>=2406 && digits[0]<=2415)
					{
						System.out.println("its a number");
						number="";
						for(int num=0;num<digits.length;num++)
						{
							//System.out.println("digits[num]="+digits[num]);
							digits[num]=(char)(digits[num]-2358);
							//System.out.println("changed="+digits[num]);
							number=number+digits[num];
						}
						
					}
					else
					{
						number=new NumProcessing().send(temp[0]);
					//number=new NumProcessing().send(org_sen[i]);
					}
					System.out.println("NUMBER IN NEW DICTIONARY1="+number);
					new NewDictionary().setWords(org_sen[i],
						line_num, temp, number);
					
				}
			
			
else{
					System.out.println("IN ELSE PART OF NEW DICTIONARY1"); //this line is also my code
				System.out.println("org	_sen[i]" + org_sen[i]);
				System.out.println("temp= " + temp[0]);
				NewDictionary userhandler = new NewDictionary(org_sen[i],
						line_num, temp);
				saxparser.parse(inputFile, userhandler);
}
				/*
				 * if(i+1==org_sen.length) { userhandler.call(); }
				 */
			}
			//handle suffix
			
			NewDictionary nd = new NewDictionary();
			nd.call();

			// here we get the translated words not sentence.
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
