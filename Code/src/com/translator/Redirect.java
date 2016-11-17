package com.translator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Redirect")
public class Redirect extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// private static final long serialVersionUID = 1L;
	static String input;
	static HttpServletRequest req;
	static HttpServletResponse resp;
	String translated[] = new String[15];
	int seq[] = new int[15];
	int count = 0;

	public Redirect() {
		super();

	}

	public void warning_word(String warning) {
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'><html><head><meta http-equiv='Content-Type' content='text/html;charset=UTF-8'><title>Translator</title><style type='text/css'>body {background-color:#f4f4f4;color: #5a5656;font-family: 'Open Sans', Arial, Helvetica, sans-serif;font-size: 20px;line-height: 1.5em;}a { text-decoration: none; }h1 { font-size: 1em; }h1,p {margin-bottom: 10px;}strong {font-weight: bold;}.uppercase { text-transform:uppercase; }/* ---------- LOGIN ---------- */#login {margin: 50px auto;width: 300px;}form fieldset input[type='textarea'],textarea {background-color: #e5e5e5;border:none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;color:#5a5656;font-family: 'Open Sans', Arial, Helvetica, sans-serif;font-size:20px;height: 50px;outline: none;padding: 0px 10px;width: 280px;-webkit-appearance:none;}form fieldset input[type='submit'] {background-color:#008dde;border: none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;color: #f4f4f4;cursor: pointer;font-family: 'Open Sans', Arial,Helvetica, sans-serif;height: 50px;text-transform: uppercase;width: 300px;-webkit-appearance:none;}formfieldset a {color: #5a5656;font-size: 10px;}form fieldset a:hover { text-decoration:underline; }.btn-round {background-color: #5a5656;border-radius: 50%;-moz-border-radius: 50%;-webkit-border-radius: 50%;color: #f4f4f4;display: block;font-size: 12px;height: 50px;line-height: 50px;margin: 30px 125px;text-align: center;text-transform: uppercase;width: 50px;}</style></head><body><div id='login'><center><h1><strong>MARATHI TO ENGLISH TRANSLATOR</strong> </h1></center><form name='homepageform' id='form1' action='Redirect' method='post'><fieldset><p>INPUT:<br>"
					+warning);
			out.println("</div><div><center><pre><font size=2 face='arial'>Created by- Adesh Gupta  Aishwarya Desai Nikhil Mehta  Apurva Ravetkar</font></pre></center></div></body></html>");
		//	out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN''http://www.w3.org/TR/html4/loose.dtd'><html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Translator</title></head><body  bgcolor=#E9967A><form name='homepageform' id='form1' method='post' action='Redirect'><center><h1>MARATHI TO ENGLISH TRANSLATOR</h1></center><br><br><br><center>INPUT<br><textarea name='input' cols='50' rows='3'></textarea><br><br><br><br><br></center><center>"
			//		+ warning);
			// <%@ page language='java'
			// contentType='text/html;charset=UTF-8'pageEncoding='UTF-8'%>
			//out.print("</center><div STYLE = 'POSITION: absolute; TOP: 500px; LEFT:425px'><pre><font size=2 face='arial'>Created by- AdeshGupta  Aishwarya Desai  Nikhil Mehta  Apurva Ravetkar</font></pre></div></form></body></html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(-1);
	}
	
	public void warning(String warning) {
		PrintWriter out;
		try {
			out = resp.getWriter();

			//out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN''http://www.w3.org/TR/html4/loose.dtd'><html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Translator</title></head><body  bgcolor=#E9967A><form name='homepageform' id='form1' method='post' action='Redirect'><center><h1>MARATHI TO ENGLISH TRANSLATOR</h1></center><br><br><br><center>INPUT<br><textarea name='input' cols='50' rows='3'></textarea><br><br><br><br><br></center><center>"
				//	+ warning);
			out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'><html><head><meta http-equiv='Content-Type' content='text/html;charset=UTF-8'><title>Translator</title><style type='text/css'>body {background-color:#f4f4f4;color: #5a5656;font-family: 'Open Sans', Arial, Helvetica, sans-serif;font-size: 20px;line-height: 1.5em;}a { text-decoration: none; }h1 { font-size: 1em; }h1,p {margin-bottom: 10px;}strong {font-weight: bold;}.uppercase { text-transform:uppercase; }/* ---------- LOGIN ---------- */#login {margin: 50px auto;width: 300px;}form fieldset input[type='textarea'],textarea {background-color: #e5e5e5;border:none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;color:#5a5656;font-family: 'Open Sans', Arial, Helvetica, sans-serif;font-size:20px;height: 50px;outline: none;padding: 0px 10px;width: 280px;-webkit-appearance:none;}form fieldset input[type='submit'] {background-color:#008dde;border: none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;color: #f4f4f4;cursor: pointer;font-family: 'Open Sans', Arial,Helvetica, sans-serif;height: 50px;text-transform: uppercase;width: 300px;-webkit-appearance:none;}formfieldset a {color: #5a5656;font-size: 10px;}form fieldset a:hover { text-decoration:underline; }.btn-round {background-color: #5a5656;border-radius: 50%;-moz-border-radius: 50%;-webkit-border-radius: 50%;color: #f4f4f4;display: block;font-size: 12px;height: 50px;line-height: 50px;margin: 30px 125px;text-align: center;text-transform: uppercase;width: 50px;}</style></head><body><div id='login'><center><h1><strong>MARATHI TO ENGLISH TRANSLATOR</strong> </h1></center><form name='homepageform' id='form1' action='Redirect' method='post'><fieldset><p>INPUT:<br>"
					+warning);
			// <%@ page language='java'
			// contentType='text/html;charset=UTF-8'pageEncoding='UTF-8'%>
			//out.print("</center><div STYLE = 'POSITION: absolute; TOP: 500px; LEFT:425px'><pre><font size=2 face='arial'>Created by- AdeshGupta  Aishwarya Desai  Nikhil Mehta  Apurva Ravetkar</font></pre></div></form></body></html>");
			out.println("</div><div><center><pre><font size=2 face='arial'>Created by- Adesh Gupta  Aishwarya Desai Nikhil Mehta  Apurva Ravetkar</font></pre></center></div></body></html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(-1);
	}

	public Redirect(String[] transwords, int[] final_seq, int cnt) {
		super();
		System.setProperty("file.encoding", "UTF-8");
		try {
			count = cnt;
			System.out
					.println("in finalllll redirect.. ready to print on jsp page");
			translated = transwords;
			seq = final_seq;
			doGet(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("in init of redirect");

	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req = request;
		resp = response;
		request.setCharacterEncoding("UTF-8");

		doPost(request, response);
	}

	protected void flush_all(HttpServletResponse response) {
		try {
			System.out.println("i flushed all values");
			input = null;
			req = null;
			resp = null;
			NewDictionary1 nd1 = new NewDictionary1("flush");
			NewDictionary nd = new NewDictionary("flush");
			RuleParsing rp = new RuleParsing("flush");
			RuleProcessing rp1 = new RuleProcessing("flush");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'><html><head><meta http-equiv='Content-Type' content='text/html;charset=UTF-8'><title>Translator</title><style type='text/css'>body {background-color:#f4f4f4;color: #5a5656;font-family: 'Open Sans', Arial, Helvetica, sans-serif;font-size: 20px;line-height: 1.5em;}a { text-decoration: none; }h1 { font-size: 1em; }h1,p {margin-bottom: 10px;}strong {font-weight: bold;}.uppercase { text-transform:uppercase; }/* ---------- LOGIN ---------- */#login {margin: 50px auto;width: 300px;}form fieldset input[type='textarea'],textarea {background-color: #e5e5e5;border:none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;color:#5a5656;font-family: 'Open Sans', Arial, Helvetica, sans-serif;font-size:20px;height: 50px;outline: none;padding: 0px 10px;width: 280px;-webkit-appearance:none;}form fieldset input[type='submit'] {background-color:#008dde;border: none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;color: #f4f4f4;cursor: pointer;font-family: 'Open Sans', Arial,Helvetica, sans-serif;height: 50px;text-transform: uppercase;width: 300px;-webkit-appearance:none;}formfieldset a {color: #5a5656;font-size: 10px;}form fieldset a:hover { text-decoration:underline; }.btn-round {background-color: #5a5656;border-radius: 50%;-moz-border-radius: 50%;-webkit-border-radius: 50%;color: #f4f4f4;display: block;font-size: 12px;height: 50px;line-height: 50px;margin: 30px 125px;text-align: center;text-transform: uppercase;width: 50px;}</style></head><body><div id='login'><center><h1><strong>MARATHI TO ENGLISH TRANSLATOR</strong> </h1></center><form name='homepageform' id='form1' action='Redirect' method='post'><fieldset><p>INPUT:<br><textarea name='input' id='input' required></textarea></p><p><input type='submit' name='SUBMIT' value='SUBMIT'></p><p>OUTPUT:<br><textarea name='output' id='output'></textarea></p><p><!--<input type='submit' value='Translate Again'>--></p></fieldset></form></div><div><center><pre><font size=2 face='arial'>Created by- Adesh Gupta  Aishwarya Desai Nikhil Mehta  Apurva Ravetkar</font></pre></center></div></body></html>");
			//out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN''http://www.w3.org/TR/html4/loose.dtd'><html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Translator</title></head><body  bgcolor=#E9967A><form name='homepageform' id='form1' method='post' action='Redirect'><center><h1>MARATHI TO ENGLISH TRANSLATOR</h1></center><br><br><br><center>INPUT<br><input type='textarea' rows='3' cols='40'name='input' id='input' ><br><br><br><input type='submit' name='SUBMIT' value='SUBMIT'><br><br><br></center><center>OUTPUT<br><input type='textarea' rows='3' cols='40' name='output' id='output' ></center><div STYLE = 'POSITION: absolute; TOP: 500px; LEFT:425px'><pre><font size=2 face='arial'>Created by- AdeshGupta  Aishwarya Desai  Nikhil Mehta  Apurva Ravetkar</font></pre></div></form></body></html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		//out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN''http://www.w3.org/TR/html4/loose.dtd'><html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Translator</title></head><body  bgcolor=#E9967A><form name='homepageform' id='form1' method='post' action='Redirect'><center><h1>MARATHI TO ENGLISH TRANSLATOR</h1></center><br><br><br><center>INPUT<br><textarea name='input' cols='40' rows='3'>"
			//	+ input
				//+ "</textarea><br><br><br><br><br><br></center><center>OUTPUT<br><textarea name='output' cols='40' rows='3'>");
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'><html><head><meta http-equiv='Content-Type' content='text/html;charset=UTF-8'><title>Translator</title><style type='text/css'>body {background-color:#f4f4f4;color: #5a5656;font-family: 'Open Sans', Arial, Helvetica, sans-serif;font-size: 20px;line-height: 1.5em;}a { text-decoration: none; }h1 { font-size: 1em; }h1,p {margin-bottom: 10px;}strong {font-weight: bold;}.uppercase { text-transform:uppercase; }/* ---------- LOGIN ---------- */#login {margin: 50px auto;width: 300px;}form fieldset input[type='textarea'],textarea {background-color: #e5e5e5;border:none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;color:#5a5656;font-family: 'Open Sans', Arial, Helvetica, sans-serif;font-size:20px;height: 50px;outline: none;padding: 0px 10px;width: 280px;-webkit-appearance:none;}form fieldset input[type='submit'] {background-color:#008dde;border: none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;color: #f4f4f4;cursor: pointer;font-family: 'Open Sans', Arial,Helvetica, sans-serif;height: 50px;text-transform: uppercase;width: 300px;-webkit-appearance:none;}formfieldset a {color: #5a5656;font-size: 10px;}form fieldset a:hover { text-decoration:underline; }.btn-round {background-color: #5a5656;border-radius: 50%;-moz-border-radius: 50%;-webkit-border-radius: 50%;color: #f4f4f4;display: block;font-size: 12px;height: 50px;line-height: 50px;margin: 30px 125px;text-align: center;text-transform: uppercase;width: 50px;}</style></head><body><div id='login'><center><h1><strong>MARATHI TO ENGLISH TRANSLATOR</strong> </h1></center><form name='homepageform' id='form1' action='Redirect' method='post'><fieldset><p>INPUT:<br><textarea name='input' id='input' required>" +
				input +"</textarea></p><p><!--<input type='submit' name='SUBMIT' value='SUBMIT'>--></p><p>OUTPUT:<br><textarea name='output' id='output'>");
						
		// <%@ page language='java'
		// contentType='text/html;charset=UTF-8'pageEncoding='UTF-8'%>
		String a=translated[seq[0]];
		String b=a.substring(0,1).toUpperCase()+a.substring(1);
		translated[seq[0]]=b;
		for (int i = 0; i < count; i++) {

			out.printf(translated[seq[i]] + " ");
		}
		out.print("</textarea></p><p><form name='form2' method='post'action='Redirect'><input type='submit' name='translateagain' value='Translate Again'></form></p></fieldset></form></div><div><center><pre><font size=2 face='arial'>Created by- Adesh Gupta  Aishwarya Desai Nikhil Mehta  Apurva Ravetkar</font></pre></center></div></body></html>");
		//out.print("</textarea><br><form name='form2' method='post' action='Redirect'><input type='submit' name='translateagain' value='Translate Again'></form></center><div STYLE = 'POSITION: absolute; TOP: 500px; LEFT:425px'><pre><font size=2 face='arial'>Created by- AdeshGupta  Aishwarya Desai  Nikhil Mehta  Apurva Ravetkar</font></pre></div></form></body></html>");
		System.out.println("the sequence is: ");
		System.out.println("@@@@@@@count in redirect doget=" + count);
		for (int i = 0; i < count; i++) {

			System.out.println(translated[seq[i]] + " ");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("translateagain") == null) {
			try {
				// Fetch the input from textarea
				// String inputString = request.getParameter("input");
				request.setCharacterEncoding("UTF-8");
				String inputString = request.getParameter("input");
				input = inputString;
				if (input.equals("") || inputString.equals(null)) {
					System.out.println("no input");
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'><html><head><meta http-equiv='Content-Type' content='text/html;charset=UTF-8'><title>Translator</title><style type='text/css'>body {background-color:#f4f4f4;color: #5a5656;font-family: 'Open Sans', Arial, Helvetica, sans-serif;font-size: 20px;line-height: 1.5em;}a { text-decoration: none; }h1 { font-size: 1em; }h1,p {margin-bottom: 10px;}strong {font-weight: bold;}.uppercase { text-transform:uppercase; }/* ---------- LOGIN ---------- */#login {margin: 50px auto;width: 300px;}form fieldset input[type='textarea'],textarea {background-color: #e5e5e5;border:none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;color:#5a5656;font-family: 'Open Sans', Arial, Helvetica, sans-serif;font-size:20px;height: 50px;outline: none;padding: 0px 10px;width: 280px;-webkit-appearance:none;}form fieldset input[type='submit'] {background-color:#008dde;border: none;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;color: #f4f4f4;cursor: pointer;font-family: 'Open Sans', Arial,Helvetica, sans-serif;height: 50px;text-transform: uppercase;width: 300px;-webkit-appearance:none;}formfieldset a {color: #5a5656;font-size: 10px;}form fieldset a:hover { text-decoration:underline; }.btn-round {background-color: #5a5656;border-radius: 50%;-moz-border-radius: 50%;-webkit-border-radius: 50%;color: #f4f4f4;display: block;font-size: 12px;height: 50px;line-height: 50px;margin: 30px 125px;text-align: center;text-transform: uppercase;width: 50px;}</style></head><body><div id='login'><center><h1><strong>MARATHI TO ENGLISH TRANSLATOR</strong> </h1></center><form name='homepageform' id='form1' action='Redirect' method='post'><fieldset><p>INPUT:<br><textarea name='input' id='input' required>" +
							input +"</textarea></p><p><input type='submit' name='SUBMIT' value='SUBMIT'></p><p>OUTPUT:<br><textarea name='output' id='output'>");
					out.print("</textarea></p><p><form name='form2' method='post'action='Redirect'><input type='submit' name='translateagain' value='Translate Again'></form></p></fieldset></form></div><div><center><pre><font size=2 face='arial'>Created by- Adesh Gupta  Aishwarya Desai Nikhil Mehta  Apurva Ravetkar</font></pre></center></div></body></html>");
					//	out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN''http://www.w3.org/TR/html4/loose.dtd'><html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><title>Translator</title></head><body  bgcolor=#E9967A><form name='homepageform' id='form1' method='post' action='Redirect'><center><h1>MARATHI TO ENGLISH TRANSLATOR</h1></center><br><br><br><center>INPUT<br><textarea name='input' cols='50' rows='3'>"
					//		+ input
						//	+ "</textarea><br><br><br><input type='submit' name='SUBMIT' value='SUBMIT'><br><br><br></center><center>OUTPUT<br><textarea name='output' cols='40' rows='3'>");
				//	out.print("</textarea></center><div STYLE = 'POSITION: absolute; TOP: 500px; LEFT:425px'><pre><font size=2 face='arial'>Created by- AdeshGupta  Aishwarya Desai  Nikhil Mehta  Apurva Ravetkar</font></pre></div></form></body></html>");
				}
				// Charset.forName("UTF-8").encode(inputString);
				// Charset charset=StandardCharsets.UTF_8;
				// String content=new String(inputString.getBytes(),charset);
				System.out.println("inputstring is: " + inputString);
				// inputString="बरेच ढग दिसत आहेत";
				File file = new File("/home/adeshgupta/INPFILE");
				PrintWriter pw = new PrintWriter("/home/adeshgupta/INPFILE",
						"UTF-8");
				pw.println(inputString);
				// /home/adeshgupta/sampark/shallow_parser_mar/INPFILE
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
					System.out.println("new file is created for INPFILE");
				}

				System.out.println("Done writing in file");
				pw.close();
				// fw.close();
				Runtime rt = Runtime.getRuntime();
				// Process proc = rt.exec("sh /home/adeshgupta/shell1.sh");
				// Process proc=new
				// ProcessBuilder("/bin/bash","-c","/home/adeshgupta/shell.sh").start();
				Process proc = rt.exec("shallow_parser_mar INPFILE OUTFILE");
				proc.waitFor();
				// rt.exec("sh /home/adeshgupta/shell2.sh");
				// proc.waitFor();
				// rt.exec("sh /home/adeshgupta/shell.sh");
				// proc.waitFor();
				BufferedReader br1 = new BufferedReader(new InputStreamReader(
						proc.getErrorStream()));
				System.out.println("the system is :" + br1.readLine());
				System.out.println("the parsing is done");
				File outfile = new File("/home/adeshgupta/OUTFILE");
				FileReader readFrom = new FileReader(outfile);
				BufferedReader br = new BufferedReader(readFrom);
				String line = br.readLine();
				String[] temp = new String[15];
				int i = 0;
				String[] data = new String[15];
				while (line != null) {
					// System.out.println("line="+line);
					if (line.toCharArray()[1] == '.') {
						line = line.replaceAll(",,", ",AAA,");
						line = line.replaceAll(",,", ",AAA,");
						line = line.replaceAll(",'", ",AAA'");
						line = line.replaceAll(",unk,", ",n,");
						System.out.println("line with dot=" + line);
						data = line.split("'");
						temp[i] = new String();
						temp[i] = data[1];
						i++;
						System.out.println("data[1]=" + data[1]);
					}
					line = br.readLine();
				}
				File file1 = new File(
						"/home/adeshgupta/workspace/Translator3.1/temp1.txt");
				PrintWriter pw1 = new PrintWriter(file1, "UTF-8");
				i = 0;
				while (temp[i] != null) {
					pw1.printf(temp[i]);
					if (temp[i + 1] != null)
						pw1.printf("\n");
					i++;
				}
				pw1.close();
				RequestDispatcher rd = request
						.getRequestDispatcher("NewDictionary1");
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		} else {
			System.out.println("post was executed");
			flush_all(response);
		}
	}

}
