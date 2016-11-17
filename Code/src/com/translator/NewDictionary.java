package com.translator;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NewDictionary extends DefaultHandler {

	boolean bEnglish = false;
	boolean bLastName = false;
	boolean bNickName = false;
	boolean bInf = false;
	boolean bInf2=false;
	int i = 0, finalWordCount;
	int count1;

	static int j;
	static int count = 0;
	static String array[] = new String[15];
	String root, str, inf, inf1;
	String data;
	String w;
	int line_num=0;
	static Word words[] = new Word[15];
	
	public NewDictionary(String reset){
		j=0;
		count=0;
		words=new Word[15];
		array=new String[15];
		System.out.println("reset in NewDicitionary:"+reset);
	}
	public NewDictionary(String w, int countWords, String temp[]) {
		/*
		 * System.out.println("marathi array is"); for(int i=0;a[i]!=null;i++) {
		 * System.out.println(a[i]); }
		 */
		this.w=w;
		finalWordCount = countWords;
		line_num=countWords;
		System.out.println("finaword cnt="+finalWordCount);
		 str=temp[0];
		System.out.println("#########NewDictionary.java str=" + str);
		// File inputFile=new
		// File("/Translator3.1/WebContent/NewDictionary.xml");
		// File openfile=new
		// File("/home/adeshgupta/workspace1/Translator3.1/temp1.txt");
		// FileReader readFrom= new FileReader(openfile);
		// BufferedReader br= new BufferedReader(readFrom);
		// String line=br.readLine();
		// j=0;
		// temp =line.split(",");
		words[j] = new Word();
		words[j].ACTUAL = w;
		words[j].TYPE = "0";
		 //str=temp[0];
		System.out.println("new dictionary.java temp[0]=" + temp[0]);
		words[j].ROOT = temp[0];
		words[j].LCAT = temp[1];
		words[j].PERS = temp[2];
		words[j].NUM = temp[3];
		words[j].CASE = temp[4];
		words[j].GEND = temp[5];
		words[j].SUFFIX = temp[6];
		words[j].VIBHAKTI = temp[7];
		j++;

	}

	public NewDictionary() {
		super();
	}
		
	public void setWords(String w, int countWords, String temp[], String number){
		this.w = w;
		finalWordCount = countWords;
		line_num = countWords;
		System.out.println("finaword cnt=" + finalWordCount);
		str = temp[0];
		System.out.println("#########NewDictionary.java str=" + str);
		// File inputFile=new
		// File("/Translator3.1/WebContent/NewDictionary.xml");
		// File openfile=new
		// File("/home/adeshgupta/workspace1/Translator3.1/temp1.txt");
		// FileReader readFrom= new FileReader(openfile);
		// BufferedReader br= new BufferedReader(readFrom);
		// String line=br.readLine();
		// j=0;
		// temp =line.split(",");
		words[j] = new Word();
		words[j].ACTUAL = w;
		words[j].TYPE = "0";
		// str=temp[0];
		System.out.println("new dictionary.java temp[0]=" + temp[0]);
		words[j].ROOT = temp[0];
		words[j].LCAT = temp[1];
		words[j].PERS = temp[2];
		words[j].NUM = temp[3];
		words[j].CASE = temp[4];
		words[j].GEND = temp[5];
		words[j].SUFFIX = temp[6];
		words[j].VIBHAKTI = temp[7];
		j++;
		array[count] = number;
		System.out.println("array[count]" + array[count]);
		count++;
	}
	
	
	
	

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("marathi")) {
			root = attributes.getValue("root");
			// System.out.println("root= "+ root);
		}
		if (str.equals(root) && qName.equalsIgnoreCase("marathi")) {
			// System.out.println("start element: "+qName);
			bInf = true;
			bInf2=true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equalsIgnoreCase("class")) {

			if(bInf2==false)
			{
				//word not found
				System.out.println("word not found"+str);
				//String warning=new String("word not found: "+str);
				//new Redirect().warning_word(warning);
				array[count] = new String();
				array[count] = w;
				System.out.println("array[count]" + array[count]);
				count++;
			}
			if (finalWordCount == count) {
				System.out.println("NewDictionary array= ");
				for (int i = 0; i < count; i++) {
					System.out.println(array[i]);
				}

			}
		}
	}

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (bInf) {
			data = (new String(ch, start, length));
			System.out.println("Translation is: " + data);
			array[count] = new String();
			array[count] = data;
			System.out.println("array[count]" + array[count]);
			count++;
			bInf = false;
		}

	}

	public void call() {
		for(i=0;array[i]!=null;i++)
		{
			System.out.println("array[i]test:"+array[i]);
		}
	 count1=i;
	
	 System.out.println("!!!!!!!!!array length="+count1);
		try {

			System.out.println("in newdictionary &*&*&*&");
			for (int i = 0; i<count1; i++) {

				System.out.println("\n" + array[i] );
			}

			
			if(((words[count1-2].LCAT.equals("v")))&&(array[(count1)-1].equals("was")||array[(count1)-1].equals("is")||array[(count1)-1].equals("were") || array[count1-2].equals("look")) && !array[count1-2].equals("called")&&!array[count1-2].equals("spread")&& !array[count1-2].equals("experience")&& !array[2].equals("garden")&& !array[count1-2].equals("change") && !array[count1-2].equals("bury")){
				//rule for experience added by nikhil 7 march
				System.out.println("090909090909090in if");
			
				
					//String temp3=array[count1-1]+" "+array[(count1)-2]+"ing";
					//array[count1-2]=temp;
				
				if(!array[count-2].equals("break")&& !array[count-2].equals("play")&&!array[count-2].equals("confusion")&& !array[count-2].equals("come") &&!array[count-2].equals("lake")&& !array[count-2].equals("tree")
						&&!array[count-2].equals("developed"))
				array[count1-2]=array[count1-2]+"ing";
					//System.out.println("temp3="+temp3+" array[eeeee]"+array[count1-2]);
				if(array[count1-2].equals("coloring"))
				{
					array[count1-2]="colorful";
				}
				if(array[count1-2].equals("danceing"))
				{
					array[count1-2]="dancing";
				}
				//count1=finalWordCount;
			}
			
			System.out.println("New Dictionary Length = "+ count1);
			/*for (int i = 0; i<array.length; i++) {

				System.out.println("\n" + array[i] + " " + words.length);
			}*/
			
			new RuleProcessing(array, words,count1,line_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
