package com.translator;

//aishwarya's changes 10 30 pm 22 nd march
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RuleParsing extends DefaultHandler {

	String[] LCAT = new String[15];
	static String[] transwords = new String[15];
	String new_seq = null;
	static int final_seq[] = new int[15];
	String str;
	int cnt = 0;
	int line_num = 0;
	boolean bInf, bInf2;
	String data;
	Word attributes[] = new Word[15];
	String temp_str[] = new String[15];

	public RuleParsing(String reset) {
		transwords = new String[15];
		final_seq = new int[15];
		System.out.println("reset in RuleParsing:" + reset);
	}

	public RuleParsing(String LCAT[], String transwords[], Word[] attrib,
			int count, int line_num) {
		this.LCAT = LCAT;
		this.line_num = line_num;
		this.transwords = transwords;
		bInf = false;
		System.out.println("+++count=+" + count);
		cnt = count;
		data = "";

		attributes = attrib;
		/*
		 * System.out.println(
		 * "------------------------------------------------------------------"
		 * ); for(int i=0;attributes[i]!=null;i++)
		 * System.out.println("atrib[1]"+attributes[i]); System.out.println(
		 * "-------------------------------------------------------------------"
		 * );
		 */int i;
		if (transwords[1].equals("land") && transwords[0].equals("valley")) {
			transwords[1] = "descending";
			// attributes[1].SUFFIX="AAA";
		}
		str = "";
		System.out.println("==========in rule parsing cnt=" + cnt);
		for (i = 0; i < cnt; i++)
			System.out.println("LCAT[i] is:" + LCAT[i]);
		handle_suffix();
		handle_past_v();
		handle_sin_plu();

		for (i = 0; i < cnt; i++) {
			// cnt++;
			if (i == cnt - 1)
				str = (str + LCAT[i]);
			else
				str = (str + LCAT[i] + " ");

		}
		System.out.println("LCAt in str:*" + str + "*");
	}

	public void rule_not_found() {
		for (int i = 0; i < cnt; i++) {
			if (i != 0)
				data = data + " " + i;
			else
				data = data + i;
		}
		System.out.println("``````````data in constrcutor:" + data);
		temp_str = (data.split(" "));
		for (int i = 0; i < cnt; i++) {
			final_seq[i] = Integer.parseInt(temp_str[i]);
			// System.out.print(" " + final_seq[i]);
		}
	}

	public void handle_past_v() {
		for (int i = 0; i < cnt; i++) {
			if (transwords[i].equals("is")
					&& transwords[0].equals("Lakshadweep")) {
				transwords[i] = "has";// for lakshadweepla and last word ahe
			} // can make it generalise as proper nouns are encountered
			if (LCAT[i].equals("v")
					&& (attributes[i].SUFFIX.equals("ला")
							|| attributes[i].SUFFIX.equals("ली") || attributes[i].SUFFIX
								.equals("लो"))) {
				if (transwords[i].equals("reach"))
					transwords[i] = "reached";
				if (transwords[i].equals("go"))
					transwords[i] = "went";
				if (transwords[i].equals("meet"))
					transwords[i] = "met";
				if (transwords[i].equals("win"))
					transwords[i] = "won";
			}
			if ((!attributes[i].SUFFIX.equals("ते") && !attributes[i].SUFFIX
					.equals("तो"))
					&& !attributes[i].SUFFIX.equals("णं")
					&& transwords[i].equals("eat")) {

				System.out.println("entered eat");
				if (transwords[i + 1] != null
						&& (transwords[i + 1].equals("was(occured)") || transwords[i + 1]
								.equals("is"))) {
					transwords[i] = "eating";
				} else
					transwords[i] = "ate";
			}
			if (transwords[i].equals("make"))
				transwords[i] = "made";
			if (transwords[i].equals("speaking"))
				transwords[i] = "spoken";
			if (transwords[i].equals("stickying"))
				transwords[i] = "sticky";
			if (transwords[i].equals("get"))
				transwords[i] = "got";
			if (LCAT[i].equals("v") && attributes[i].NUM.equals("pl")) {
				if ((transwords[i].equals("was") || transwords[i]
						.equals("was(occured)"))
						&& !attributes[i].SUFFIX.equals("ता")
						&& !attributes[i].ROOT.equals("होता"))
					transwords[i] = "were";
				// System.out.println("root of was= " + attributes[i].ROOT);
			}
		}
		if (cnt > 5) {
			if (transwords[5].equals("was(occured)")
					&& transwords[1].equals("rise")) {
				transwords[1] = "rose";
				transwords[5] = "";
			}
		}
		if (cnt == 4 && transwords[3].equals("speak")
				&& transwords[2].equals("language")) {
			transwords[3] = "is spoken";
		}
		if (cnt == 3 && LCAT[0].equals("n") && LCAT[1].equals("avy")
				&& LCAT[2].equals("v")) {
			if (transwords[1].equals("a lot of")) {
				transwords[1] = "a lot";
			}
		}
		if (cnt == 3 && LCAT[0].equals("n") && LCAT[1].equals("n")
				&& LCAT[2].equals("v")) {
			if (transwords[2].equals("is")
					&& (transwords[0].equals("forest")
							|| transwords[0].equals("garden") || transwords[0]
								.equals("temple")))
				transwords[1] = transwords[1] + "ed";
			else if (transwords[2].equals("was(occured)")
					&& (transwords[1].equals("camel") || transwords[1]
							.equals("modak"))) {
				System.out.println("it is were now");
				transwords[2] = "were";
			}
			if (transwords[1].equals("anciented")) {
				transwords[1] = "ancient";
			}
		}
		if (cnt == 3 && transwords[2].equals("was(occured)")
				&& transwords[1].equals("holiday")) {
			transwords[2] = "had";
		}
		System.out.println("%%%%%%%%" + transwords[2]);
		if (cnt == 3 && transwords[2].equals("to fall")
				&& transwords[1].equals("ill")) {
			System.out.println("------------------");
			transwords[2] = ("fell");
		}
	}

	public void handle_sin_plu() {
		/*
		 * for(int i=0;attributes[i]!=null;i++) {
		 * System.out.println("!!!!!!!! "+attributes[i].ACTUAL); }
		 */
		if (attributes[cnt] == null) // means hota hoti not there
		{
			// handling rules pp v and pn v
			if (cnt == 2) {
				if ((LCAT[0].equals("pn") || LCAT[0].equals("n"))
						&& (LCAT[1].equals("v"))) {
					if (attributes[0].NUM.equals("sg")
							&& !transwords[0].equals("you")
							&& !transwords[0].equals("I")) {
						// attach s to 2nd word
						attach_s(1); // passing the index where we have to
										// attach
					}

					else {
						// do nothing for AAA
					}
				} else if (LCAT[0].equals("pp") && LCAT[1].equals("v")
						&& attributes[1].NUM.equals("sg")) {
					attach_s(1);
				}
			}//
			else if (cnt == 3) {// pn n v or n n v
				if ((LCAT[0].equals("pn") || LCAT[0].equals("n"))
						&& (LCAT[1].equals("n") && LCAT[2].equals("v"))) {
					System.out.println("in nnv or pnnv ");
					if (attributes[0].NUM.equals("sg")) {
						if (!attributes[1].ACTUAL.equals("मुलगी")
								&& !attributes[1].ACTUAL.equals("पर्यटक")
								&& (!transwords[0].equals("I"))) {
							if ((!transwords[2].equals("is"))
									&& (!transwords[2].equals("was"))
									&& (!transwords[2].equals("was(occured)"))
									&& !transwords[2].equals("are")
									&& (!transwords[2].equals("went") && !transwords[2]
											.equals("had"))
											&& !transwords[2].equals("cry")
											&& !transwords[2].equals("cries")) {
								System.out.println("entered attachins");
								attach_s(2);
							} else

							{
								System.out.println("vibhakti is:"
										+ attributes[0].VIBHAKTI);
								if (transwords[2].equals("is")
										&& (attributes[0].SUFFIX.equals("AAA"))
										&& line_num == cnt
										&& !transwords[1].equals("sea"))
									transwords[2] = "has";
								else if ((transwords[2].equals("is") || transwords[2]
										.equals("was(occured)"))
										&& ((attributes[0].SUFFIX.equals("ला")
												|| attributes[0].SUFFIX
														.equals("स") || attributes[0].SUFFIX
													.equals("त"))
												&& !transwords[1]
														.equals("crowd")
												&& !transwords[1].equals("sea") && !transwords[0]
													.equals("in forest"))) {
									System.out.println("in sa");
									transwords[2] = "has";
								} else if (transwords[2].equals("is")
										&& !(attributes[0].SUFFIX.equals("AAA")))
									transwords[2] = "is";
								else if (transwords[2].equals("was")
										&& !transwords[2].equals("walk")
										&& !transwords[0].equals("car")
										&& !transwords[1].equals("modak")
										&& !transwords[1].equals("camel")
										&& !transwords[1]
												.equalsIgnoreCase("dolphin")
												&& !transwords[2].equals("cry")
												&& !transwords[2].equals("cries")) {// not
									// handled
									// chck the tense.. not handled
									System.out.println("i jus added had");
									transwords[2] = "had";
								}

							}
						}
					} else if (attributes[0].NUM.equals("pl")) {
						attach_s(0);
					} else {
						if (transwords[2].equals("is")
								&& !(attributes[0].VIBHAKTI.equals("AAA")))
							transwords[2] = "has";
						else if (transwords[2].equals("is")
								&& (attributes[0].VIBHAKTI.equals("AAA")))
							transwords[2] = "is";
						else if (!transwords[2].equals("going")
								&& !transwords[2].equals("walk")
								&& !transwords[2].equals("get")
								&& !transwords[2].equals("go")
								&& !transwords[2].equals("not")
								&& !transwords[1].equals("modak")
								&& !transwords[2].equals("did")
								&& !transwords[2].equals("do")
								&& !transwords[1].equals("camel")
								&& !transwords[1].equalsIgnoreCase("dolphin")
								&& !transwords[1].equals("chikki")
								&& !transwords[0].equals("Hedvi's"))
							// chck the tense.. not handled
							transwords[2] = "had";
					}
					if (attributes[1].NUM.equals("pl")) {

						attach_s(1);
					}
				}
				// n adv v
				else if (LCAT[0].equals("n") && LCAT[1].equals("adv")
						&& LCAT[2].equals("v")) {
					if (attributes[0].NUM.equals("sg")) {
						attach_s(2);
					}
				}

				// for n v v
				else if ((LCAT[0].equals("n") || LCAT[0].equals("pn"))
						&& LCAT[1].equals("v") && LCAT[2].equals("v")
						&& (!transwords[0].equals("I"))) {
					System.out.println("in n v v ");
					if (!transwords[2].equals("is")
							&& !transwords[2].equals("was"))
						attach_s(2);
					else {
						if (transwords[2].equals("is") && line_num == cnt)
							transwords[2] = "has";
						else if (transwords[2].equals("were")
								&& (!transwords[1].equals("camel") || transwords[1]
										.equals("modak"))
								&& !transwords[1].equals("chikki"))// unhandled
							transwords[2] = "had";
					}

				}
				// pp n v
				if (LCAT[0].equals("pp") && LCAT[1].equals("n")
						&& LCAT[2].equals("v")) {
					if (!transwords[2].equals("is")
							&& !transwords[2].equals("was")
							&& !transwords[2].equals("were")
							&& !transwords[2].equals("are")) {
						attach_s(2);
					}
				}

			}
			if (cnt == 4) {// avy n adv v
				if (LCAT[1].equals("n") && LCAT[0].equals("avy")
						&& LCAT[2].equals("adv") && LCAT[3].equals("v")) {
					if (attributes[1].NUM.equals("pl")) {
						if (transwords[1].equals("child")) {
							transwords[1] = "children";
						} else {
							attach_s(1);
						}
						if (transwords[3].equals("was")
								|| transwords[3].equals("was(occured)")) {
							transwords[3] = "were";
						} else if (transwords[3].equals("is")) {
							transwords[3] = "are";
						} else {
							// do nothing
						}// else
					}
				}
				// n num n v
				else if (LCAT[0].equals("n") && LCAT[1].equals("num")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& !transwords[1].equals("one")) {
					attach_s(2);
					if (transwords[3].equals("is")
							&& transwords[2].equals("hands")) {
						transwords[3] = "are";
					}
				}

				else if (LCAT[1].equals("n") && LCAT[0].equals("avy")
						&& LCAT[2].equals("avy") && LCAT[3].equals("v")) {
					if (attributes[1].NUM.equals("pl")) {
						attach_s(1);
						if (transwords[3].equals("is")) {
							transwords[3] = "are";
						} else if (transwords[3].equals("was")) {
							transwords[3] = "were";
						}
					}
				}
				
				/*
				 * if (transwords[3].equals("is") &&
				 * (attributes[0].ACTUAL.equals("औरंगाबादला"))) { transwords[3]
				 * = "has"; // System.out.println("aurangabad lcat=" + LCAT[3]);
				 * }
				 */

				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("v") && LCAT[2].equals("v")) {
					if (attributes[1].NUM.equals("pl")) {
						attach_s(1);
					}
				}
			}
			if (cnt == 5) {
				if (LCAT[0].equals("n") && LCAT[1].equals("v")
						&& LCAT[2].equals("adj") && LCAT[3].equals("n")
						&& LCAT[4].equals("v")) {
					if (transwords[3].equals("stairs")) {
						transwords[4] = "are";
					}
				}
				// n num n adv v
				if (LCAT[0].equals("n") && LCAT[1].equals("num")
						&& LCAT[2].equals("n") && LCAT[3].equals("adv")
						&& LCAT[4].equals("v")) {
					if (!transwords[1].equals("one")) {
						attach_s(2);
					}
				}
				
				if (LCAT[0].equals("nst") && LCAT[1].equals("n")
						&& LCAT[2].equals("v") && LCAT[3].equals("n")
						&& LCAT[4].equals("n")) {
					if (transwords[2].equals("live's")) {
						transwords[2] = "living's";
					}
					if (transwords[2].equals("eat's")) {
						transwords[2] = "eating's";
					}
				}
				if (LCAT[0].equals("pn") && LCAT[1].equals("v")
						&& LCAT[2].equals("v") && LCAT[3].equals("num")
						&& LCAT[4].equals("n")) {
					if (transwords[1].equals("live's")) {
						transwords[1] = "living's";
					}
					if (transwords[2].equals("eat's")) {
						transwords[2] = "eating's";
					}
				}
				
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("num") && LCAT[3].equals("v")
						&& (LCAT[4].equals("v") || LCAT[4].equals("n"))) {
					if ((transwords[3].equals("going") || transwords[3]
							.equals("go")) && transwords[1].contains("'s")) {
						transwords[3] = "species";
						transwords[4] = "are";
					}
				}
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("n")
						&& LCAT[4].equals("v")) {
					for (int i = 0; i < cnt; i++) {
						if (attributes[i].NUM.equals("pl")
								&& (transwords[i].equals("vehicle") || transwords[i]
										.equals("rupee"))) {
							attach_s(i);
						}
						
						if (attributes[i].SUFFIX.equals("ई")
								&& transwords[i].equalsIgnoreCase("Manali")) {
							transwords[i] = "not allowed";
						}
					}
				}
			}
			if (cnt == 6) {
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("n")
						&& LCAT[4].equals("adj") && LCAT[5].equals("v")) {
					if (attributes[3].NUM.equals("pl")
							&& transwords[3].equals("year")) {
						transwords[3] = transwords[3] + "s";
					}

				}
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("pn") && LCAT[3].equals("adv")
						&& LCAT[4].equals("n") && LCAT[5].equals("v")) {
					if (transwords[1].equals("from point of view")) {
						transwords[0] = "from religious";
						transwords[1] = "point of view";
					}

				}
				// if(LCAT[0].equals("n") && LCAT[1].equals("n") &&
				// LCAT[2].equals("n") && )
			}
		} else // continuous tense with aahe hoti at the end
		{
			System.out.println("!@#!@#last word der");
			if (LCAT[1].equals("n") && attributes[1].NUM.equals("pl")) {
				System.out.println("@#@# in if");
				attach_s(1);
			}
		}
		if (cnt == 6 && LCAT[0].equals("pn") && LCAT[0].equals("n")
				&& LCAT[0].equals("n") && LCAT[0].equals("adv")
				&& LCAT[0].equals("n") && LCAT[0].equals("v")
				&& transwords[2].equals("monkeys")
				&& transwords[5].equals("is")) {
			transwords[5] = "are";
		}
		// pn n num n n v
		if (cnt == 6 && LCAT[0].equals("pn") && LCAT[1].equals("n")
				&& LCAT[2].equals("num") && LCAT[3].equals("n")
				&& LCAT[4].equals("n") && LCAT[5].equals("v")) {
			if (transwords[1].equals("in temple")
					&& attributes[5].ACTUAL.equals("आहेत")
					&& transwords[3].equals("planets")) {
				transwords[5] = "are";
				transwords[3] = "planets'";
				attach_s(4);
			}
		}
		
		if (cnt == 6 && LCAT[0].equals("pn") && LCAT[1].equals("pn")
				&& LCAT[2].equals("n") && LCAT[3].equals("n")
				&& LCAT[4].equals("v") && LCAT[5].equals("v")
				&& transwords[5].equals("take")) {
			transwords[5] = "can";
		}
	}

	public void handle_suffix() {
		System.out.println("in handle suffix");
		for (int i = 0; i < cnt; i++) {
			// new rule for पासून
			if (attributes[i].SUFFIX.equals("पासून")) {
				if (transwords[i].equals("caravansary")) {
					transwords[i] = "from Dharmasala";
				} else {
					transwords[i] = "from " + transwords[i];
				}
			}

			if (attributes[i].SUFFIX.equals("ित")) {
				if (transwords[i].equals("centre"))
					transwords[i] = "centre governed";
			}
			if (attributes[i].SUFFIX.equals("ली_च्या_साठी")
					&& transwords[i].equals("sing")) {
				transwords[i] = "for carpets";
			}
			if (transwords[i].equals("temple")
					&& attributes[i].NUM.equals("pl")) {
				attach_s(i);
			}
			if (transwords[i].equals("man") && attributes[i].NUM.equals("pl")) {
				transwords[i] = "men";
			}
			if (attributes[i].SUFFIX.equals("ा")
					&& transwords[i].equals("wake")) {
				transwords[i] = "place";
			}
			if (attributes[i].SUFFIX.equals("लं")
					&& transwords[i].equals("break")) {
				transwords[i] = "broken";
			}
			if (attributes[i].SUFFIX.equals("णं")
					&& transwords[i].equals("play")) {
				transwords[i] = "toy";
			}
			if (attributes[i].SUFFIX.equals("झा") && transwords[i].equals("I")) {
				transwords[i] = "my";
			}
			if (attributes[i].SUFFIX.equals("ईन")
					&& transwords[i].equals("and")) {
				transwords[i] = "wine";
			}
			if (transwords[i].equals("hot")) {
				if (i == 0) {
					transwords[i] = "summer";
				}
			}
			if (attributes[i].SUFFIX.equals("ायला")) {
				if (transwords[i].equals("swim")) {
					transwords[i] = "swimming";
				}
				if (cnt == 4 && transwords[i].equals("see")
						&& transwords[i + 2].equals("came")) {
					transwords[i] = "to see";
					transwords[i + 2] = "was";
				}
			}
			if (transwords[i].equals("price")
					&& (transwords[i + 1].equals("year"))) {
				transwords[i] = "every"; // for every year,can add for every
											// time
			}
			if (attributes[i].SUFFIX.equals("लेला")
					&& transwords[i].equals("confusion")) {
				transwords[i] = "confused";
			}
			if ((attributes[i].SUFFIX.equals("लेला") || attributes[i].SUFFIX
					.equals("लोय")) && transwords[i].equals("tire")) {
				transwords[i] = "tired";
			}
			if (attributes[i].SUFFIX.equals("त") && transwords[i].equals("is")
					&& !LCAT[i - 1].equals("v")) {
				System.out.println("attached are");
				transwords[i] = "are";
			}
			if (attributes[i].SUFFIX.equals("तोय")
					&& transwords[i].equals("feel")) {
				attach_s(i);
			}
			if (attributes[i].SUFFIX.equals("हून")
					&& attributes[i].LCAT.equals("n")) {
				transwords[i] = "from " + transwords[i];
			}

			if (attributes[i].SUFFIX.equals("तील")
					&& transwords[i].equals("world")) {
				transwords[i] = "in the " + transwords[i];
			}
			if (attributes[i].SUFFIX.equals("ला")) {
				if (transwords[i].equals("take")) {
					transwords[i] = "took";
				}
			}
			if (attributes[i].SUFFIX.equals("झी") && transwords[i].equals("I")) {
				transwords[i] = "my";
			}

			if (attributes[i].SUFFIX.equals("वरी")
					&& transwords[i].equals("Goda")) {
				transwords[i] = "Godavari";
			}
			if (transwords[i].equals("walk")
					&& attributes[i].SUFFIX.equals("लो") && cnt == 3) {
				transwords[i] = "going";
			}
			if (attributes[i].SUFFIX.equals("मध्ये")
					&& !transwords[i].equals("comes")) {
				transwords[i] = "in " + transwords[i];
			} else if (attributes[i].SUFFIX.equals("खाली")) {
				transwords[i] = "under the " + transwords[i];
			} else if (attributes[i].SUFFIX.equals("वर")
					&& !transwords[i].equals("distance")) {
				transwords[i] = "on " + transwords[i];
			} else if (attributes[i].SUFFIX.equals("ची")
					&& transwords[i].equals("plane")) {

				transwords[i] = "plane's";
			} else if (attributes[i].SUFFIX.equals("ने")
					&& !transwords[i].equals("road")
					&& !transwords[i].equals("big")
					&& !transwords[i].equals("girl")
					&& !transwords[i].equals("darshan")
					&& !transwords[i].equals("she")
					&& !transwords[i].equals("Shivaji")) {
				transwords[i] = "with the " + transwords[i];
			} else if (attributes[i].SUFFIX.equals("प्रमाणे")) {
				transwords[i] = transwords[i] + " like";
			} else if (attributes[i].SUFFIX.equals("चा")
					|| attributes[i].SUFFIX.equals("चे")
					|| attributes[i].SUFFIX.equals("ची")) {
				/*
				 * if(transwords[i].equals("pond")){ transwords[i]="pond's"; }
				 */
				if ((transwords[i].equals("metal") || transwords[i]
						.equals("both")) && cnt == 5)
					transwords[i] = "of " + transwords[i];
				else if (transwords[i].equals("nearby")
						|| transwords[i].equals("both")) {
					System.out.println("hello");
					if (transwords[i].equals("both"))
						transwords[i] = "of " + transwords[i];
				} else if (transwords[i].equals("Japan")) {
					transwords[i] = "Japan's";
				}

				else if (transwords[i].equals("they"))
				{
					if (attributes[i].NUM.equals("sg"))
						transwords[i] = "his";
					else
						transwords[i] = "their";
				} else if (transwords[i].equals("he")) {
					transwords[i] = "his";
				} else if (transwords[i].equals("she")) {
					transwords[i] = "her";
				} else if (transwords[i].equals("from sand")) {
					// do nothing
				} else {
					transwords[i] = transwords[i] + "'s";
				}

			} else if (attributes[i].SUFFIX.equals("मागे")) {
				transwords[i] = "behind the " + transwords[i];
			} else if (attributes[i].SUFFIX.equals("तून")) {
				if (transwords[i].equals("lake")
						&& transwords[i - 1].equals("small")
						&& transwords[i - 2].equals("one")) {
					transwords[i - 2] = "from " + transwords[i - 2];
				} else
					transwords[i] = "from " + transwords[i];
			} else if (attributes[i].SUFFIX.equals("समोर")
					|| attributes[i].equals("पुढे")) {
				transwords[i] = "in front of  the " + transwords[i];
			} else if (attributes[i].SUFFIX.equals("त") && ((i - 2) > 0)) {
				if (transwords[i].equals("temple")
						&& transwords[i - 1].equals("Mahadev")
						&& transwords[i - 2].equals("Jvyaleshwar")
						&& transwords[i].equals("school")) {
					transwords[i - 2] = "In " + transwords[i - 2];
				}
			} else if (attributes[i].SUFFIX.equals("त")
					&& (!transwords[i].equals("acre")
							&& !transwords[i].equals("India")
							&& !transwords[i].equals("is")
							&& !transwords[i].equals("are")
							&& !transwords[i].equals("book")
							&& !transwords[i].equals("school")
							&& !transwords[i].equals("home")
							&& !transwords[i].equals("looking")
							&& !transwords[i].equals("eating")
							&& !transwords[i].equals("sleeping")
							&& !transwords[i].equals("doing")
							&& !transwords[i].equals("was")
							&& !transwords[i].equals("growing")
							&& !transwords[i].equals("going")
							&& !transwords[i].equals("again")
							&& !transwords[i].equals("dancing")
							&& !transwords[i].equals("peeling") && !transwords[i]
								.equals("eat"))) {
				transwords[i] = "in " + transwords[i];
			} else if (attributes[i].SUFFIX.equals("त")
					&& transwords[i].equals("is") && !LCAT[i - 1].equals("v")) {
				System.out.println("attached are");
				transwords[i] = "are";
			} else if (attributes[i].SUFFIX.equals("भर")) {
				if (transwords[i].equals("country")) {
					transwords[i] = transwords[i] + "wide";
				} else
					transwords[i] = "full " + transwords[i];
			} else if (attributes[i].SUFFIX.equals("ी")
					&& !transwords[i].equals("second")
					&& !transwords[i].equals("storey")
					&& !transwords[i].equals("bhakari")
					&& !transwords[i].equals("courageous")) {
				if (transwords[i].equals("bury"))
					transwords[i] = "vehicle";
				else if (transwords[i].equals("night")) {
					transwords[i] = "at " + transwords[i];
				} else if (transwords[i].equals("place")
						|| transwords[i].equals("brave")
						|| transwords[i].equals("year")
						|| transwords[i].equals("before")
						|| transwords[i].equals("orange")) {

				} else {
					transwords[i] = "in the " + transwords[i];
				}
			} else if (attributes[i].SUFFIX.equals("च्या")
					&& attributes[i].LCAT.equals("nst")) {
				transwords[i] = "to a " + transwords[i];
			} else if ((transwords[i].equals("lake") || transwords[i]
					.equals("basin"))
					&& attributes[i].SUFFIX.equals("च्या")
					&& transwords[i - 1].equals("small")) {
				transwords[i - 1] = "to the " + transwords[i - 1];
			} else if (transwords[i].equals("Patnitop")
					&& attributes[i + 1].LCAT.equals("pn")) {
				transwords[i] = "in " + transwords[i];// for handling proper
														// nouns like place
			} else if (attributes[i].SUFFIX.equals("तात")
					&& !transwords[i].equals("speak")) {
				System.out.println("in else of तात////////////////////");
				if (transwords[i].equals("sold")) {
					if (transwords[1].equals("mango"))
						transwords[i] = "is " + transwords[i];
					else
						transwords[i] = "are " + transwords[i];
				} else if (transwords[i].equals("like")) {
					if ((i > 0) && transwords[i - 1].equals("bird")) {
						attach_s(i - 1);
					}
				} else if (transwords[i].equals("gather")) {
					if ((i > 0) && attributes[i - 1].LCAT.equals("n")) {
						attach_s(i - 1);
					}
				}
			} else if (attributes[i].SUFFIX.equals("नंतर")
					&& attributes[i].VIBHAKTI.equals("्या_नंतर")) {
				transwords[i] = "after that";
			} else if (attributes[i].SUFFIX.equals("साठी")) {
				transwords[i] = "for " + transwords[i];
			} else if ((i + 1) <= cnt) {
				if (attributes[i].SUFFIX.equals("ला")
						&& !transwords[i].equals("Aurangabad")
						&& !transwords[i].equals("look")
						&& !transwords[i].equals("come")
						&& !transwords[i].equals("this")
						&& !transwords[i].equals("go")
						&& !transwords[i].equals("she")
						&& !transwords[i].equals("school")
						&& !transwords[i].equals("win")
						&& !transwords[i].equals("Seema")
						&& !transwords[i].equals("is")
						&& !transwords[i].equals("change")
						&& !transwords[i].equals("has")
						&& !transwords[i].equals("was(occured)")
						&& !transwords[i].equals("open")
						&& !transwords[i].equals("take")
						&& !transwords[i].equals("which")
						&& !transwords[i].equals("Bhavan")
						&& !transwords[i].equals("took")) {
					transwords[i] = "to " + transwords[i];
				}
			} else if (attributes[i].SUFFIX.equals("ला")) {
				if (transwords[i].equals("change")
						&& transwords[i + 1].equals("is")) {
					transwords[i + 1] = "has";
					transwords[i] = "changed";
				} else if (attributes[i].SUFFIX.equals("ला")) {
					if (transwords[i].equals("take")) {
						transwords[i] = "took";
					}
					if (transwords[i].equals("she")) {
						if (i != 0) {
							transwords[i] = "her";
						}
					}
					if (transwords[i].equals("open")) {
						transwords[i] = "opened";
					}

				}
			} else if (attributes[i].SUFFIX.equals("ही")
					&& transwords[i].equals("is")) {
				transwords[i] = transwords[i] + " also";
			} else if (attributes[i].SUFFIX.equals("तो")
					&& transwords[i].equals("do")) {
				transwords[i] = transwords[i] + "es";
			} else if (attributes[i].SUFFIX.equals("तो")
					&& transwords[i].equals("read")) {
				transwords[i] = "reads";
			} else if (attributes[i].SUFFIX.equals("तो")
					&& transwords[i].equals("finish")) {
				attach_s(i);
			} else if (attributes[i].SUFFIX.equals("तात")
					&& transwords[i].equals("speak")) {
				transwords[i] = "called";
			}
			// to change manali to consider root word man
			else if (attributes[i].SUFFIX.equals("ले")
					&& transwords[i].equals("Manaliing")) {
				transwords[i] = "considered";
			} else if (attributes[i].SUFFIX.equals("ले")
					|| attributes[i].SUFFIX.equals("ली")) {
				if (transwords[i].equals("do")) {
					System.out.println("in did-------------");
					transwords[i] = "did";
				}
			} else if (attributes[i].SUFFIX.equals("पासून")) {
				if (transwords[i].equals("caravansary")) {
					transwords[i] = "from Dharmasala";
				} else {
					transwords[i] = "from " + transwords[i];
				}
			}
			
			else if (attributes[i].SUFFIX.equals("पूर्वी")) {
				transwords[i] = transwords[i] + " back";
			} else if (attributes[i].SUFFIX.equals("च्या")
					&& attributes[i].LCAT.equals("n")
					&& !transwords[i].equals("palace")
					&& !transwords[i].equals("this")) {
				System.out.println("added apostrohe s");
				transwords[i] = transwords[i] + "'s";
			} else if (attributes[i].SUFFIX.equals("लेले")
					&& transwords[i].equals("spread")) {
				transwords[i] = transwords[i] + " over";
			} else if (attributes[i].SUFFIX.equals("हून")
					&& attributes[i].LCAT.equals("n")) {
				transwords[i] = "from " + transwords[i];
			}
			
			else if (attributes[i].SUFFIX.equals("झी")
					&& transwords[i].equals("I")) {
				transwords[i] = "my";
			}
			if (attributes[i].SUFFIX.equals("तो")
					&& transwords[i].equals("finish")) {
				attach_s(i);
			}
			if (attributes[i].SUFFIX.equals("ले")
					&& transwords[i].equals("Manaliing")) {
				transwords[i] = "considered";
			} else if (attributes[i].SUFFIX.equals("ले")
					|| attributes[i].SUFFIX.equals("ली")) {
				if (transwords[i].equals("do")) {
					System.out.println("in did-------------");
					transwords[i] = "did";
				}
			}

		}
		if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("n")
				&& LCAT[2].equals("n") && LCAT[3].equals("v")) {
			if (transwords[3].equals("get")
					&& attributes[3].SUFFIX.equals("तो")) {
				transwords[3] = "gives";
			}
		}

		
		else if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("num")
				&& LCAT[2].equals("n") && LCAT[3].equals("n")) {
			if (attributes[2].NUM.equals("pl")) {
				attach_s(2);
			}

		} else if (cnt == 5
				&& (LCAT[0].equals("n") || LCAT[0].equals("pp") || LCAT[0]
						.equals("pn")) && LCAT[1].equals("num")
				&& LCAT[2].equals("n") && LCAT[3].equals("n")
				&& LCAT[4].equals("v")) {
			if (attributes[3].NUM.equals("pl")) {
				attach_s(3);
			}
		} else if (cnt == 5
				&& (LCAT[0].equals("n") || LCAT[0].equals("pp") || LCAT[0]
						.equals("pn")) && LCAT[1].equals("n")
				&& LCAT[2].equals("num") && LCAT[3].equals("n")
				&& LCAT[4].equals("n")) {

			if (attributes[3].NUM.equals("pl")) {
				attach_s(3);
			}

		} else if (cnt == 5
				&& (LCAT[0].equals("n") || LCAT[0].equals("pp") || LCAT[0]
						.equals("pn")) && LCAT[1].equals("num")
				&& LCAT[2].equals("n") && LCAT[3].equals("n")
				&& LCAT[4].equals("n")) {
			if (attributes[3].NUM.equals("pl")) {
				attach_s(3);
			}
		} else if (cnt >= 5) {
			int pl = 0, i;
			for (i = cnt - 1; i >= 0; i--) {
				if (i != 0 && LCAT[i - 1].equals("num")
						&& attributes[i].NUM.equals("pl")) {
					attach_s(i);
					break;
				}
			}
			if (i != -1) {
				for (i = cnt - 1; i >= 0; i--) {
					if (LCAT[i].equals("pp") || LCAT[i].equals("pn"))
						break;
					else if (attributes[i].NUM.equals("pl"))
						pl = i;
				}
				if (i == -1) {
					attach_s(pl);
				}
			}
		}

		if (cnt == 6 && (LCAT[0].equals("pp") || LCAT[0].equals("pn"))
				&& LCAT[1].equals("n") && LCAT[2].equals("num")
				&& LCAT[3].equals("n") && LCAT[4].equals("n")
				&& LCAT[5].equals("n")) {

			transwords[0] = "This";

		}

		

	}

	public void attach_s(int pos) {
		if (!transwords[pos].equals("fell")) {
			String temp6;
			String temp4 = new String(transwords[pos]);
			char temp5[] = temp4.toCharArray();
			if (temp5[temp5.length - 1] == 'y') {
				System.out.println("last letter is y");
				if (temp5[temp5.length - 2] == 'a'
						|| (temp5[temp5.length - 2] == 'e')
						|| (temp5[temp5.length - 2] == 'i')
						|| (temp5[temp5.length - 2] == 'o')
						|| (temp5[temp5.length - 2] == 'u')) {

					temp4 = temp4 + "s";
				} else {
					temp6 = "";
					for (int i = 0; i < temp5.length - 1; i++) {
						temp6 = temp6 + temp5[i];
					}
					temp6 = temp6 + "ies";
					System.out.println("temp6=" + temp6);
					temp4 = temp6;
				}
			} else if (temp5[temp5.length - 1] == 'f') {
				if (temp5[temp5.length - 2] == 'f') {
					temp4 = temp4 + "s";
				} else {// attach alpha+ves
					temp6 = "";
					for (int i = 0; i < temp5.length - 1; i++) {
						temp6 = temp6 + temp5[i];
					}
					temp6 = temp6 + "ves";
					temp4 = temp6;
				}
			} else if (temp5[temp5.length - 1] == 'h') {
				if (temp5[temp5.length - 2] == 'c'
						|| temp5[temp5.length - 2] == 's') {
					System.out.println("n es2");
					temp4 = temp4 + "es";
				} else {
					temp4 = temp4 + "s";
				}
			} else if (temp5[temp5.length - 1] == 'o'
					|| temp5[temp5.length - 1] == 's'
					|| temp5[temp5.length - 1] == 'x'
					|| temp5[temp5.length - 1] == 'z') // o add es to pos
			{
				if (temp5[temp5.length - 2] == '\'') {
					temp6 = "";
					for (int i = 0; i < temp5.length - 2; i++) {
						temp6 = temp6 + temp5[i];
					}
					System.out.println("temp6=" + temp6);
					transwords[pos] = temp6;
					attach_s(pos);
					// temp4=temp4+"'s";
					temp4 = transwords[pos] + "'s";
					System.out.println("after attaching 's=" + transwords[pos]);
				} else if (!temp4.equals("reads") && !temp4.equals("men")
						&& !temp4.equals("temples") && !temp4.equals("is")
						&& !temp4.equalsIgnoreCase("rupees")
						&& !temp4.equals("years")
						&&!temp4.equals("airport")
						/*&& !temp4.equals("airports")
						&& !temp4.equals("ports")
						&&!temp4.equals("seas")*/
						) {
					System.out.println("in es1");
					temp4 = temp4 + "es";
				}
			} else if (!temp4.equals("men") && !temp4.equals("small")
					&& !temp4.equals("came") && !temp4.equals("won")
					&& !temp4.equals("was(occured)")) {
				System.out.println("attaching s");
				temp4 = temp4 + "s";
			}
			transwords[pos] = temp4;
		}
	}

	public void goto_redirect() {
		System.out.println("in function for going to redirect");
		// do suffix processing
		for (int i = 0; i < cnt; i++) {
			System.out.println(" " + transwords[i]);
			System.out.println(" " + final_seq[i]);
		}
		new Redirect(transwords, final_seq, cnt);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("rule")) {
			new_seq = attributes.getValue("sequence");
			// System.out.println("in starteleemtn");

		}
		if (str.equals(new_seq) && qName.equalsIgnoreCase("rule")) {
			System.out.println("new_seq= " + new_seq);
			// System.out.println("start element: "+qName);
			// System.out.println("cnyt="+cnt);
			if (cnt == 4) {
				// `
				if (LCAT[0].equals("n") && LCAT[1].equals("adj")
						&& LCAT[2].equals("v") && LCAT[3].equals("v")) {
					/*
					 * if(transwords[3].equals("is")) { transwords[3]="has"; }
					 */
				}

				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& !transwords[3].equals("is")
						&& !transwords[3].equals("was")
						&& !transwords[3].equals("were")
						&& transwords[1].equals("Idli")
						&& !transwords[3].equals("was")
						&& !transwords[3].equals("has")) {
					System.out.println("in new rule......!***&&&***= ");
					new_seq = "0 1 3 2";
					System.out.println(new_seq);
				}
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& transwords[0].equals("Aurangabad")) {
					new_seq = "0 3 1 2";
					System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnn");
				}
				if (LCAT[0].equals("n")
						&& LCAT[1].equals("n")
						&& LCAT[2].equals("n")
						&& LCAT[3].equals("v")
						&& (transwords[0].equals("Harihareshwar's") || transwords[0]
								.equals("Kerala's"))) {
					System.out.println("in new rule......!***&&&***= ");
					new_seq = "0 1 3 2";
					System.out.println(new_seq);
				}

				if (LCAT[0].equals("pn") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")) {
					if (transwords[1].equals("every")
							|| transwords[2].equals("watch")) {

					} else {
						System.out.println("in new rule......!***&&&***= ");
						new_seq = "0 1 2 3";
						System.out.println(new_seq);
					}
				}
			}
			/*
			 * if(cnt==5){
			 * if(LCAT[0].equals("n")&&LCAT[1].equals("n")&&LCAT[2].equals
			 * ("n")&&LCAT[3].equals("n")&&LCAT[4].equals("v") &&
			 * transwords[0].equals("every")&&transwords[1].equals("place")){
			 * System.out.println("in new rule......!***&&&***= ");
			 * new_seq="2 4 3 0 1"; System.out.println(new_seq); } }
			 */
			
			/*
			 * if (LCAT[0].equals("pn") && LCAT[1].equals("v") &&
			 * LCAT[2].equals("n") && LCAT[3].equals("n") ) {
			 * if(transwords[0].equals("here") ||
			 * transwords[0].equals("there")){ if(transwords[3].equals("has"))
			 * transwords[3]="are"; }
			 * 
			 * } }
			 */
			if (LCAT[0].equals("n")
					&& LCAT[1].equals("n")
					&& LCAT[2].equals("n")
					&& LCAT[3].equals("n")
					&& (transwords[1].contains("'s")
							|| transwords[1].equals("industrial") || transwords[1]
								.equals("agrarian"))) {
				data = "0 3 1 2";
				System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnn");
			}
			if (LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")) {
				if (transwords[3].equals("has")
						&& (transwords[2].contains("world") || transwords[2]
								.equals("popular")))
		
					if (this.attributes[1].NUM.equals("pl")) {
						attach_s(1);
						transwords[3] = "are";
					} else {
						transwords[3] = "is";
					}
		
			}
		

			if (cnt == 6) {
				if (LCAT[0].equals("n") && LCAT[1].equals("num")
						&& LCAT[2].equals("avy") && LCAT[3].equals("n")
						&& LCAT[4].equals("n") && LCAT[5].equals("v")
						&& transwords[0].equals("earth's")) {
					System.out.println("in new rule......!***&&&***= ");
					new_seq = "0 1 2 3 5 4";
					System.out.println(new_seq);
				}
			}
			bInf = true;
			bInf2 = true;

		}

	}

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {
		if (bInf) {
			// System.out.println("in characters");
			data = (new String(ch, start, length));
			if (cnt == 5) {
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("n")
						&& LCAT[4].equals("v")
						&& transwords[1].equals("sarees")) {
					data = "0 1 4 3 2";
				}
			}
			// n num avy n n v
			if (cnt == 6) {
				if (LCAT[0].equals("n") && LCAT[1].equals("num")
						&& LCAT[2].equals("avy") && LCAT[3].equals("n")
						&& LCAT[4].equals("n") && LCAT[5].equals("v")
						&& transwords[0].equals("Risium")) {
					data = "0 5 1 2 3 4";
				}

				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("nst") && LCAT[3].equals("n")
						&& LCAT[4].equals("n") && LCAT[5].equals("v")
						&& transwords[0].equals("Thaneshwar")) {
					data = "3 4 5 2 0 1";
				}
			}
			if (cnt == 5) {
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("num") && LCAT[3].equals("n")
						&& LCAT[4].equals("v") && transwords[0].equals("cave")) {
					data = "0 1 2 4 3";
				}
			}

			if (cnt == 6) {
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("num") && LCAT[3].equals("n")
						&& LCAT[4].equals("n") && LCAT[5].equals("v")
						&& transwords[0].equals("cave")) {
					data = "0 1 2 5 3 4";
				}
			}
			if (cnt == 7) {
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("num")
						&& LCAT[4].equals("n") && LCAT[5].equals("n")
						&& LCAT[6].equals("v") && transwords[2].equals("road")
						&& transwords[5].equals("distance")) {
					transwords[1] = "of " + transwords[1];
				}
			}
			if (cnt == 6) {
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("n")
						&& LCAT[4].equals("n") && LCAT[5].equals("v")
						&& transwords[2].equals("trout")
						&& transwords[3].equals("for fish")) {
					transwords[2] = "for " + transwords[2];
					transwords[3] = "fish";
					data = "0 1 5 4 2 3";
				}
				if (LCAT[0].equals("pn") && LCAT[1].equals("pn")
						&& LCAT[2].equals("n") && LCAT[3].equals("n")
						&& LCAT[4].equals("v") && LCAT[5].equals("v")
						&& transwords[5].equals("can")
						&& transwords[3].equals("for travel")) {

					data = "0 5 4 1 2 3";
				}
			}
			if (cnt == 4) {
				System.out.println("entered cnt==4");
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("v") && LCAT[3].equals("v")
						&& transwords[0].equals("my")
						&& transwords[1].equals("eyes")
						&& transwords[2].equals("tire")
						&& transwords[3].equals("are")) {
					System.out.println("entering here");
					transwords[2] = "tired";
					data = "0 1 3 2";

				}
				if (LCAT[0].equals("avy") && LCAT[1].equals("v")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& transwords[0].equals("they")
						&& transwords[1].equals("eat")
						&& transwords[2].equals("cold")
						&& transwords[3].equals("is")) {
					transwords[0] = "that";
					transwords[1] = "food";

				}
				if (LCAT[0].equals("pp") && LCAT[1].equals("n")
						&& LCAT[2].equals("adj") && LCAT[3].equals("adj")
						&& transwords[0].equals("he")
						&& transwords[1].equals("towel")
						&& transwords[2].equals("wet")
						&& transwords[3].equals("is")) {
					transwords[0] = "that";

				}
				if (LCAT[0].equals("n") && LCAT[1].equals("avy")
						&& LCAT[2].equals("v") && LCAT[3].equals("v")
						&& transwords[0].equals("I")
						&& transwords[1].equals("this")
						&& transwords[2].equals("place")
						&& transwords[3].equals("like")) {
					// transwords[3]="are";
					data = "0 3 1 2";

				}
				if (LCAT[0].equals("pn") && LCAT[1].equals("adv")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& transwords[0].equals("you")
						&& transwords[1].equals("very")
						&& transwords[2].equals("rude")
						&& transwords[3].equals("is")) {
					transwords[3] = "are";
					data = "0 3 1 2";

				}
				
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("n"))

				{
					if (transwords[1].contains("'s")) {
						data = "0 3 1 2";
					}
					if (transwords[1].equals("in tourist")) {
						transwords[1] = "among tourists";
						data = "0 3 2 1";
					}
					if (attributes[2].ACTUAL.equals("प्रदेश")) {
						data = "0 3 1 2";
					}
				}
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("v") && LCAT[3].equals("v")) {
					if (transwords[2].equals("spoken")) {
						data = "1 3 2 0";
					}
				}
				if (LCAT[0].equals("pn") && LCAT[1].equals("v")
						&& LCAT[2].equals("n") && LCAT[3].equals("n")) {
					if (attributes[2].SUFFIX.equals("ई")
							&& transwords[2].equalsIgnoreCase("manali")) {
						transwords[2] = "not allowed";
						if (attributes[1].SUFFIX.equals("स")
								&& transwords[1].equals("bus")) {
							transwords[1] = "sitting";
						}

						data = "0 1 3 2";
					}
				}
				if (LCAT[0].equals("nst") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")) {
					if (transwords[0].equals("nearby")
							&& !transwords[1].equalsIgnoreCase("Narmada")) {
						data = "0 1 3 2";
					}
				}
				if (LCAT[0].equals("pn") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("n")) {

					if ((transwords[3].equals("has") || transwords[3]
							.equals("is"))
							&& transwords[2].equals("prohibited")) {
						if (attributes[1].NUM.equals("pl")) {
							transwords[3] = "are";
						} else {
							transwords[3] = "is";
						}

						data = "0 1 3 2";
						if (transwords[1].contains("'s")) {
							data = "0 3 1 2";
						}
					}
					if (transwords[1].contains("'s")) {
						data = "0 3 1 2";
					}

				}

				// n n n v
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& !transwords[3].equals("is")
						&& !transwords[3].equals("was")
						&& !transwords[3].equals("were")
						&& !transwords[3].equals("was")
						&& !transwords[3].equals("like")
						&& !transwords[0].equals("Janjira")) {
					System.out.println("in data.....!***&&&***= ");
					data = "0 1 3 2";
					System.out.println(data);
				}
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& transwords[2].equals("in month")) {
					System.out.println("in data.....!***&&&***= ");
					transwords[2] = transwords[2] + " of";
					data = "3 0 2 1";
					System.out.println(data);
				}
				if (LCAT[0].equals("n")
						&& LCAT[1].equals("n")
						&& LCAT[2].equals("n")
						&& LCAT[3].equals("v")
						&& (transwords[0].equals("Harihareshwar's")
								|| transwords[0].equals("Assam's")
								|| transwords[0].equals("Manipur's")
								|| transwords[0].equals("Afghanistan's")
								|| transwords[0].equals("Germany's")
								|| transwords[0].equals("Goa's")
								|| transwords[0].equals("Hedvi's")
								|| transwords[2].equals("artistic")
								|| transwords[0].equals("Kashi's")
								|| transwords[0].equals("Agra's")
								|| transwords[0].equals("mountain's") || transwords[0]
									.equals("Mumbai's"))) {
					System.out.println("in new rule......!***&&&***= ");
					data = "0 1 3 2";
					System.out.println(data);
				}
				if (LCAT[0].equals("pn") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")) {

					if ((transwords[1].equals("every") || transwords[2]
							.equals("watch"))
							|| (transwords[1].equals("Lord Hanuman's") && transwords[2]
									.equals("temple"))) {

					} else {
						
						if (transwords[2].equals("food")
								&& transwords[3].equals("got")) {
							transwords[3] = "get";
							data = "0 3 1 2";
						} else if (!(LCAT[0].equals("pn")
								&& LCAT[1].equals("v") && LCAT[2].equals("n") && LCAT[3]
									.equals("n"))) {
							System.out.println("in data.....!***&&&***= ");
							data = "0 1 3 2";
						}
						System.out.println(data);
					}
				}

				// n adj n v
				// 1 2 3 0
				if (LCAT[0].equals("n") && LCAT[1].equals("adj")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")) {
					System.out.println("in new rule@@@+++");
					if (transwords[1].equals("big")
							&& !(transwords[3].equals("is")))
						data = "1 2 3 0";
					else if (transwords[3].equals("is")
							&& transwords[1].equals("big"))
						data = "0 3 1 2";
					else if ((transwords[1].equals("old")
							|| transwords[1].equals("simple") || transwords[1]
								.equals("difficult"))
							&& transwords[2].equals("language")) {
						data = "0 3 1 2";
					}
				}
				// n adv n v 0 1 2 3

				if (LCAT[0].equals("n") && LCAT[1].equals("adv")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& cnt == 4) {
					if (transwords[0].equals("in Rajasthan")
							&& transwords[1].equals("very")) {
						transwords[0] = "Rajasthan";
					}
					if (transwords[1].equals("very")
							&& !transwords[2].equals("green")
							&& !transwords[2].equals("tall")
							&& !transwords[2].equals("rain")
							&& !transwords[2].equals("hot")
							&& !transwords[0].equals("Bajnamath")) {
						data = "0 1 2 3";

					} else if (transwords[0].equals("Bajnamath")) {
						data = "0 3 1 2";
					}

				}
				// 0312 n n v v
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("v") && LCAT[3].equals("v")) {
					if (cnt == 4 && !transwords[3].equals("is")
							&& !transwords[3].equals("was(occured)")
							&& !transwords[3].equals("are")
							&& !transwords[3].equals("was")) {
						System.out.println("in data.....!***&&&***= ");
						data = "0 3 1 2";
						System.out.println("-----------------");
						System.out.println(data);
					} else if (cnt == 4 && transwords[0].equals("in Kerala")) {
						data = "0 3 1 2";
					}
					if (LCAT[0].equals("n") && LCAT[1].equals("n")
							&& LCAT[2].equals("v") && LCAT[3].equals("v")) {
						if (transwords[1].equals("from hills")) {
							data = "0 2 3 1";
						}
					} else if (cnt == 4 && transwords[0].equals("in Kokan")) {
						data = "1 3 2 0";
					}

				}
				if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& transwords[2].equals("popular")
						&& !transwords[0].equals("Goa's")) {
					System.out.println("in modified rule++++++++++++");
					data = "0 3 2 1";
				}
				if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("v")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& transwords[2].equals("popular")
						&& !transwords[0].equals("Goa's")
						&& !transwords[0].equals("Konkan's")) {
					System.out.println("in modified rule++++++++++++");
					data = "0 3 2 1";
				}
				if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("v")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")) {
					if (transwords[0].contains("'s")
							&& transwords[2].equals("close")) {
						transwords[2] = "closed";
					}
				}
				if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("nst") && LCAT[3].equals("v")
						&& attributes[1].SUFFIX.equals("पासून")) {
					System.out.println("in modified rule++++++++++++");
					data = "0 3 2 1";
				}
				if (cnt == 4
						&& LCAT[0].equals("n")
						&& LCAT[1].equals("n")
						&& LCAT[2].equals("n")
						&& LCAT[3].equals("v")
						&& (transwords[2].equals("tasty") || transwords[2]
								.equals("ancient"))) {
					data = "0 1 3 2";
				}
				// n num n n
				// to remove in from in tripura has 150 temples
				if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("num")
						&& LCAT[2].equals("n") && LCAT[3].equals("n")) {
					if (transwords[0].contains("in ")
							&& transwords[3].equals("has")) {
						transwords[0] = transwords[0].replace("in ", "");
					}
					/*if (!transwords[1].equals("1")
							&& !transwords[1].equals("one")) {
						attach_s(2);
					}*/
				}
				// n pn n v 0 1 3 2
				if (LCAT[0].equals("n") && LCAT[1].equals("pn")
						&& LCAT[2].equals("n") && LCAT[3].equals("v")
						&& (transwords[0].equals("In May")))

				{
					data = "0 1 3 2";
				}

			}
			if (cnt == 5) {
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("n")
						&& LCAT[4].equals("v") && transwords[0].equals("every")
						&& transwords[1].equals("place")) {
					for (int i = 0; i < cnt; i++) {
						if (transwords[i].equals("every")
								&& transwords[i + 1].equals("place")) {
							transwords[i] = "everywhere";
							transwords[i + 1] = "";
							data = "2 4 3 0 1";
						}
					}

					System.out.println(data);
				}
			}
			// n n n n v 0 1 2 4 3
			if (cnt == 5
					&& LCAT[0].equals("n")
					&& LCAT[1].equals("n")
					&& LCAT[2].equals("n")
					&& LCAT[3].equals("n")
					&& LCAT[4].equals("v")
					&& (transwords[1].equals("Mahadev")
							|| transwords[1].equals("temple")
							|| transwords[0].equals("Solarium")
							|| transwords[0].equals("Lakot") || transwords[0]
								.equals("grand"))
					&& !transwords[0].equals("Vishwanath")) {
				data = "0 1 2 4 3";
				if (transwords[1].equals("tower")) {
					data = "0 1 4 2 3";
				}
				if (transwords[1].equals("fort")) {
					data = "0 1 4 2 3";
				}
				if (transwords[1].equals("temple")
						&& attributes[1].SUFFIX.equals("जवळ")) {
					{
						transwords[0] = "near " + transwords[0];
						data = "0 1 4 2 3";
					}
				}
				if ((transwords[0].equalsIgnoreCase("laxman") && transwords[1]
						.equals("temple"))) {
					data = "0 1 4 3 2";
				}
				if (transwords[2].equals("in temple")) {
					transwords[2] = "temple";
					transwords[0] = "in " + transwords[0];
				}
			}
			// n num n n n
			if (cnt == 5 && LCAT[0].equals("n") && LCAT[1].equals("num")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("n")) {
				if (transwords[0].contains("in ")
						&& transwords[4].equals("has")) {
					transwords[0] = transwords[0].replace("in ", "");
				}
				if (attributes[2].SUFFIX.equals("पूर्वी")
						&& transwords[2].equals("year")) {
					if (!transwords[1].equals("1")
							&& !transwords[1].equals("one"))
						attach_s(2);
					transwords[2] = transwords[2] + " back";
				}
			}
			if (cnt == 5 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("v") && transwords[1].equals("national")
					&& transwords[2].equals("language")) {
				data = "0 1 2 4 3";
			}
			if (cnt == 5 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("v") && transwords[1].equals("Konark")
					&& transwords[2].equals("beach")) {
				data = "1 2 4 3 0";
			}
			if (cnt == 5 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("v") && transwords[1].equals("SCUBA")
					&& transwords[2].equals("diving")) {
				data = "0 1 2 4 3";
			}
			// n n n n v 0 1 4 3 2
			if (cnt == 5 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("v")
					&& (transwords[0].equals("Lakshman"))) {
				data = "0 1 4 3 2";
			}
			if (cnt == 5 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("v") && (transwords[0].equals("Nehru"))
					&& transwords[1].equals("park's")) {
				data = "0 1 2 4 3";
			}
			if (cnt == 5 && LCAT[0].equals("pn") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("v")
					&& (transwords[3].equals("sanctuary"))) {
				transwords[4] = "is";
				data = "0 4 1 2 3";
			}
			// nnnnn
			if (cnt == 5 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("n") && (transwords[0].equals("Kochi"))) {
				// transwords[4] = "is";
				data = "0 4 1 2 3";
				if (attributes[3].SUFFIX.equals("ही")
						&& transwords[1].equals("Kerala's")) {
					transwords[1] = "also " + transwords[1];
				}
			}
			// n n num v v
			if (cnt == 5) {
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("num") && LCAT[3].equals("v")
						&& (LCAT[4].equals("v") || LCAT[4].equals("n"))) {
					if (attributes[1].SUFFIX.equals("च्या")
							&& (transwords[3].equals("going") || transwords[3]
									.equals("go"))) {
						transwords[1] = transwords[1] + "'s";
						transwords[3] = "specie";
						if (!(transwords[2].equals("1") || transwords[2]
								.equals("one"))) {
							attach_s(3);
							if (transwords[4].equals("is")
									|| transwords[4].equals("has")) {
								transwords[4] = "are";
							}
						}
					}
				}
			}
			// pn num n n v
			if (cnt == 5 && LCAT[0].equals("pn") && LCAT[1].equals("num")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("v") && (transwords[4].equals("found"))) {
				transwords[4] = "are " + transwords[4];
				if (transwords[2].equals("type's")) {
					transwords[2] = "types of";
				}
			}
			// n n n v v
			if (cnt == 5 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("v")
					&& LCAT[4].equals("v") && (transwords[0].equals("Mandova"))) {
				// transwords[4] = "is";
				transwords[0] = "over Mandova";
				transwords[1] = "river";
				transwords[3] = "stream";
				data = "0 1 4 2 3";
			}
			// n avy v v
			if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("avy")
					&& LCAT[2].equals("v") && LCAT[3].equals("v")
					&& transwords[2].equals("lake")) {
				data = "1 2 3 0";
			}
			if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("avy")
					&& LCAT[2].equals("v") && LCAT[3].equals("v")
					&& transwords[1].equals("this")) {
				data = "0 3 1 2";
			}
			// n n n v 1 0 3 2
			if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("v")
					&& (transwords[0].equals("Rajasthan's"))
					&& (transwords[1].equals("dalbhati"))) {
				if (transwords[1].equals("dalbhati"))
					transwords[0] = "of Rajasthan";
				data = "1 0 3 2";
			}
			if (cnt == 4
					&& LCAT[0].equals("n")
					&& LCAT[1].equals("n")
					&& LCAT[2].equals("n")
					&& LCAT[3].equals("v")
					&& (transwords[1].equals("currency") || transwords[1]
							.equals("capital"))) {
				data = "0 1 3 2";
			}
			if (cnt == 4 && LCAT[0].equals("avy") && LCAT[1].equals("num")
					&& LCAT[2].equals("n") && LCAT[3].equals("v")) {
				if (transwords[0].equals("they")
						&& transwords[2].equals("table")) {
					transwords[0] = "it";
				}
			}
			if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("v")
					&& !transwords[0].equals("Janjira")) {
				System.out.println("+_+_+_+_+_+" + data);
				if ((transwords[0].equals("Kelkar") && transwords[1]
						.equals("museum"))
						|| (transwords[0].equals("Sambhaji") && transwords[1]
								.equals("garden"))
						|| (transwords[0].equals("Bharatnatya") && transwords[1]
								.equals("theater"))
						|| (transwords[0].equals("Kalidas")
								&& transwords[1].equals("theater") || (transwords[0]
								.equals("my") && transwords[1].equals("father")))) {
					System.out.println("dare to chnage");
					data = "0 1 3 2";
				}
				if (transwords[0].contains("'s")) {
					data = "0 1 3 2";
					if (transwords[2].equals("close")) {
						transwords[2] = "closed";
					}
				}

			}
			// pn v n n
			if (cnt == 4
					&& LCAT[0].equals("pn")
					&& LCAT[1].equals("v")
					&& LCAT[2].equals("n")
					&& LCAT[3].equals("n")
					&& (transwords[0].equals("here") && transwords[2]
							.equals("not allowed"))) {
				data = "0 1 3 2";
				if (transwords[1].equals("swim")) {
					transwords[1] = "swimming";
				}
			}

			// pn n n v
			if (cnt == 4 && LCAT[0].equals("pn") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("v")

					&& transwords[2].equals("language")
					&& transwords[0].equals("there")) {
				if (transwords[2].equals("watch")) {

				} else {
					System.out.println("entered here");
					data = "0 1 2 3";
				}
			}
			// 0 1 2 6 3 4 5 n n n num n n v
			if (cnt == 7 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("num")
					&& LCAT[4].equals("n") && LCAT[5].equals("n")
					&& LCAT[6].equals("v") && transwords[0].equals("Swiss")) {
				data = "0 1 2 6 3 4 5";
			}
			// pn n n n
			if (cnt == 4 && LCAT[0].equals("pn") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")

					&& transwords[1].equals("Mahayantra")) {
				data = "0 1 3 2";
			}
			if (cnt == 4 && LCAT[0].equals("nn") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")

					&& transwords[1].equals("Gujarat's")) {
				data = "0 3 1 2";
			}
			// n adv v v
			if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("adv")
					&& LCAT[2].equals("v") && LCAT[3].equals("v")

					&& transwords[1].equals("very")
					&& transwords[2].equals("tree")
					&& transwords[3].equals("is")) {
				transwords[1] = "many";
				transwords[2] = "trees";
				transwords[3] = "are";
			}
			// pp adv n n v
			if (cnt == 5 && LCAT[0].equals("pp") && LCAT[1].equals("adv")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("v") && transwords[0].equals("he")
					&& transwords[3].equals("country")
					&& transwords[1].equals("very")) {
				transwords[0] = "that";
				transwords[1] = "a " + transwords[1];
			}
			// pn n adv n v
			if (cnt == 5 && LCAT[0].equals("pn") && LCAT[1].equals("n")
					&& LCAT[2].equals("adv") && LCAT[3].equals("n")
					&& LCAT[4].equals("v")
					&& transwords[1].equals("in country")
					&& transwords[0].equals("she")) {
				transwords[0] = "that";
				transwords[1] = "country";
			}
			// n n avy n v
			if (cnt == 5 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("avy") && LCAT[3].equals("n")
					&& LCAT[4].equals("v") && transwords[2].equals("many")
					&& transwords[4].equals("was(occured)")) {
				transwords[4] = "were";

				if (transwords[3].equals("tree")) {
					transwords[3] = "trees";
					transwords[1] = "of dates";
				}
			}
			if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("avy")
					&& LCAT[2].equals("n") && LCAT[3].equals("v")
					&& transwords[2].equals("industries")) {
				if (transwords[3].equals("is")) {
					transwords[3] = "are";
				}
				data = "1 2 3 0";
			}
			if (cnt == 3 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("v")) {
				if (transwords[0].equals("to Nagpur")
						&& transwords[2].equals("is")) {
					transwords[0] = "Nagpur";
				}
				if (transwords[1].equals("and")
						&& transwords[2].equals("walks")) {
					transwords[1] = "awry";
				}
			}
			// pn n v
			if (cnt == 3 && LCAT[0].equals("pn") && LCAT[1].equals("n")
					&& LCAT[2].equals("v")) {
				if (transwords[1].equals("school")
						&& transwords[2].equals("is")) {
					transwords[1] = "in school";
				}
				if (transwords[0].equals("I") && transwords[2].equals("reads")) {
					transwords[2] = "read";
				}
			}
			// pn n n n v
			if (cnt == 5 && LCAT[0].equals("pn") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& LCAT[4].equals("v") && transwords[3].equals("popular")
					&& transwords[4].equals("is")) {
				data = "0 1 4 3 2";
			}
			
			if (cnt == 6 && transwords[2].equals("in all")
					&& transwords[3].equals("big")) {
				transwords[2] = "the";
				transwords[3] = "biggest";
			}
			if (cnt == 6 && LCAT[2].equals("num")
					&& transwords[3].equals("acre")
					&& attributes[3].SUFFIX.equals("त")) {
				transwords[2] = "over " + transwords[2];
			}
			if (cnt == 4 && LCAT[0].equals("pn") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& transwords[2].equals("available")) {
				data = "0 1 3 2";
			}
			

			// avy n n v
			if (cnt == 4 && LCAT[0].equals("avy") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("v")
					&& transwords[0].equals("they")
					&& transwords[1].equals("business")
					&& transwords[2].equals("port")
					&& transwords[3].equals("is")) {
				transwords[0] = "that";
				transwords[1] = "a " + transwords[1];

				data = "0 3 1 2";
			}
			if (cnt == 4 && LCAT[0].equals("avy") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("v")
					&& transwords[0].equals("they")
					&& transwords[1].equals("our")
					&& transwords[2].equals("home")
					&& transwords[3].equals("is")) {
				transwords[0] = "that";

				data = "0 3 1 2";
			}
			if (cnt == 4 && LCAT[0].equals("avy") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("v")
					&& transwords[0].equals("they")
					&& transwords[1].equals("city")) {
				transwords[0] = "that";

			}
			if (cnt == 6) {
				if (LCAT[0].equals("n") && LCAT[1].equals("num")
						&& LCAT[2].equals("avy") && LCAT[3].equals("n")
						&& LCAT[4].equals("n") && LCAT[5].equals("v")
						&& transwords[0].equals("earth's")) {
					System.out.println("in new rule......!***&&&***= ");
					data = "0 1 2 3 5 4";
					System.out.println(data);
				}
			}
			// n n n avy n v
			if (cnt == 6) {
				if (LCAT[0].equals("n") && LCAT[1].equals("n")
						&& LCAT[2].equals("n") && LCAT[3].equals("avy")
						&& LCAT[4].equals("n") && LCAT[5].equals("v")) {
					if (transwords[5].equals("do")
							&& transwords[3].equals("all")) {
						transwords[5] = "does";
					}
				}
			}
			// pn n n pn v
			if (cnt == 5 && (LCAT[0].equals("pn") || LCAT[0].equals("n"))
					&& LCAT[1].equals("n") && LCAT[2].equals("n")
					&& LCAT[3].equals("pn") && LCAT[4].equals("v")) {
				if (transwords[4].equals("speak")
						&& attributes[3].ACTUAL.equals("असेही")) {
					transwords[4] = "also called";
				}
			}
			if (cnt == 4) {
				if (transwords[3].equals("was")
						|| transwords[3].equals("was(occured)")) {
					if (transwords[2].equals("start")) {
						transwords[2] = "started";
					}
				}
			}
			if (cnt == 3) {
				if (transwords[0].equals("they")
						&& transwords[1].equals("there")
						&& transwords[2].equals("put")) {
					transwords[0] = "it";
				}
			}
			if (cnt == 3) {
				if (transwords[0].equals("they")
						&& transwords[1].equals("different")
						&& transwords[2].equals("is")) {
					transwords[0] = "it";
				}
			}
			if (cnt == 3) {
				if (transwords[0].equals("he")
						&& transwords[1].equals("change")
						&& transwords[2].equals("is")) {
					transwords[1] = "changed";
					transwords[2] = "has";
				}
			}
			if (cnt == 3) {
				if (transwords[2].equals("are")
						&& (transwords[1].equals("lion")
								|| transwords[1].equals("tiger")
								|| transwords[1].equals("elephant")
								|| transwords[1].equals("bird") || transwords[1]
									.equals("wild boar"))) {
					attach_s(1);
				}
			}
			if (cnt == 4 && transwords[0].equalsIgnoreCase("Kashmiri")) {
				System.out.println("in kash*(**********");
				if (attributes[3].SUFFIX.equals("तात")) {
					if (transwords[1].equals("girl")) {
						transwords[1] = "girls";
					}
					if (transwords[3].equals("is")) {
						transwords[3] = "are";
					}
				}
			}
			for (int i = 0; i < cnt; i++) {
				System.out.println("%%%%%%%%");
				if (attributes[i].LCAT.equals("num")
						&& ((i + 1) <= cnt)
						&& (!transwords[i].equals("one") && !transwords[i]
								.equals("1"))) {
					System.out.println("in plural*(*******************");
					if (transwords[i + 1].equals("meter")) {
						transwords[i + 1] = "meters";
					} else if (transwords[i + 1].equals("foot")) {
						transwords[i + 1] = "feet";
					} else if (transwords[i + 1].equals("km")) {
						transwords[i + 1] = "kms";
					} else if (transwords[i + 1].equals("kilometer")) {
						transwords[i + 1] = "kilometers";
					} else if (transwords[i + 1].equals("country")) {
						transwords[i + 1] = "countries";
					} else if (transwords[i + 1].equals("year")) {
						transwords[i + 1] = "years";
					} else if (transwords[i + 1].equals("feeting")) {
						transwords[i + 1] = "feet";
					}else if (transwords[i + 1].equalsIgnoreCase("tiger")) {
						transwords[i + 1] = "tigers";
					}else if (transwords[i + 1].equalsIgnoreCase("lions")) {
						transwords[i + 1] = "lions";
					}else if (transwords[i + 1].equalsIgnoreCase("elephant")) {
						transwords[i + 1] = "elephant";
					}
				}
			}
			if (cnt == 3 && transwords[0].equals("worlds")) {
				transwords[0] = "world";
			}
			if (cnt == 3 && transwords[1].equals("truth")
					&& transwords[2].equals("speak")) {
				transwords[2] = "speaks";
			}
			if (cnt == 3 && transwords[0].equals("he")
					&& transwords[1].equals("nice")
					&& transwords[2].equals("play")) {
				transwords[2] = "plays";
			}
			if (cnt == 5 && transwords[1].equalsIgnoreCase("Lakshmi's")
					&& transwords[0].equals("in temple")) {
				transwords[1] = "of Lakshmi";
			}
			if (cnt == 3 && transwords[0].equalsIgnoreCase("he")
					&& transwords[1].equals("nearby")
					&& transwords[2].equals("live")) {
				transwords[2] = "lives";
			}
			if (cnt == 6 && transwords[3].equals("times")
					&& transwords[4].equals("big")) {
				transwords[4] = "bigger";
			}
			if (cnt == 4 && transwords[2].equals("state")
					&& attributes[2].NUM.equals("pl")) {
				transwords[2] = "states";
			}
			if (cnt == 5 && transwords[2].equals("chief")
					&& transwords[3].equals("province")
					&& transwords[4].equals("was(occured)")) {
				transwords[4] = "were";
				transwords[3] = "provinces";
			}
			if (cnt == 5 && transwords[3].equals("river")
					&& !transwords[1].equals("1")
					&& !transwords[1].equals("one")) {
				transwords[3] = "rivers";
			}
			if (cnt == 4 && transwords[0].equals("here")
					&& transwords[1].equals("for live")
					&& transwords[2].equals("hotel")
					&& transwords[3].equals("has")) {
				transwords[1] = "for living";
				transwords[3] = "is";
			}
			// n n n n 0 3 1 2
			if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& transwords[1].equals("industrial")) {
				data = "0 3 1 2";
			}
			if (cnt == 3 && transwords[1].equals("very")
					&& transwords[2].equals("write")) {
				transwords[1] = "alot";
				transwords[2] = "writes";
			}
			if (cnt == 3 && transwords[0].equals("rubber")
					&& transwords[1].equals("stretching")
					&& transwords[2].equals("is")) {
				transwords[1] = "stretched";
			}
			for (int i = 0; i < cnt; i++) {
				if (transwords[i].equals("in flowing")) {
					transwords[i] = "flowing";
				}
				if (transwords[i].equals("nices")) {
					transwords[i] = "nice";
				}
				if (transwords[i].equals("this's")) {
					transwords[i] = "this";
				}

			}
			if (cnt == 6 && transwords[0].equals("Buddha's")
					&& transwords[2].equals("eight")
					&& transwords[4].equals("incident")) {
				transwords[4] = "incidents";
			}
			if (cnt == 6 && transwords[1].equalsIgnoreCase("all")
					&& transwords[2].equalsIgnoreCase("type")
					&& transwords[0].equals("to Himalaya")) {
				transwords[0] = "in Himalayas";
				transwords[2] = "types";
				transwords[5] = "are";
			}
			if (transwords[0].equalsIgnoreCase("we")
					&& transwords[cnt - 1].equals("is")
					&& transwords[cnt - 2].equals("going")) {
				transwords[cnt - 1] = "are";
			}

			if (cnt == 5 && transwords[0].equalsIgnoreCase("vishwanath")
					&& transwords[1].equals("temple")
					&& transwords[3].equals("popular")) {
				data = "0 1 4 3 2";
			}
			if (cnt == 4 && transwords[0].equals("America")
					&& transwords[1].equals("very")
					&& transwords[2].equals("clean")) {
				data = "0 3 1 2";
			}
			if (cnt == 4 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("n") && LCAT[3].equals("n")
					&& transwords[1].equals("disrict")) {
				data = "2 3 0 1";
			}
			// n n avy n v 0 4 1 2 3
			if (cnt == 5 && LCAT[0].equals("n") && LCAT[1].equals("n")
					&& LCAT[2].equals("avy") && LCAT[3].equals("n")
					&& LCAT[4].equals("v")
					&& transwords[0].equalsIgnoreCase("navratri")) {
				data = "0 4 1 2 3";
			}
			// pn n n n v
			if (cnt == 5 && transwords[0].equals("here")
					&& transwords[1].equals("popular")
					&& transwords[3].equals("temple")
					&& transwords[4].equals("is")) {
				data = "0 4 1 2 3";
			}
			if (cnt == 6
					&& attributes[0].SUFFIX.equals("पासून")
					&& (transwords[4].equals("far") || transwords[4]
							.equals("distance")) && transwords[5].equals("is")) {
				if (transwords[0].equals("caravansary"))
					transwords[0] = "from Dharamsala";
				if (transwords[0].equals("Ahmedabad")) {
					transwords[0] = "from " + transwords[0];
				}
			}
			if (cnt == 6 && (attributes[1].SUFFIX.equals("पासून"))) {
				if (transwords[1].equals("Shimla"))
					transwords[1] = "from Shimla";
			}
			if (cnt == 6 && (attributes[0].SUFFIX.equals("पासून"))) {
				if (transwords[0].equals("Shimla"))
					transwords[0] = "from Shimla";
			}
			if (cnt == 4 && transwords[1].equals("footwear")
					&& transwords[3].equals("are")) {
				System.out.println("*********************");
				transwords[3] = "is";
			}
			if (cnt == 6 && transwords[0].equals("she")
					&& transwords[1].equals("school")
					&& transwords[2].equals("sector")
					&& transwords[4].equals("in") && transwords[5].equals("is")) {
				transwords[0] = "that";
			}
			if (cnt == 4 && transwords[0].equals("they")
					&& transwords[2].equals("fruit")
					&& transwords[1].equals("one")
					&& transwords[3].equals("is")) {
				transwords[0] = "that";
			}
			if(cnt==5 &&
					transwords[0].equalsIgnoreCase("rajasthan's")
					&&transwords[3].equalsIgnoreCase("rajasthan's")
					&& transwords[2].equalsIgnoreCase("language")
					&& transwords[1].equalsIgnoreCase("chief")
					)
			{
				transwords[0]="rajasthani";
				transwords[3]="of Rajasthan";
			}
			if(cnt==5 
					&& transwords[0].equalsIgnoreCase("Ramdev's")
					&& transwords[1].equalsIgnoreCase("in fare")
			
					&& transwords[3].equalsIgnoreCase("men")
					&& transwords[4].equalsIgnoreCase("come"))
			{
				System.out.println("112345678987654323456789765432134");
				data="2 3 4 0 1";
				transwords[0]="to "+transwords[0];
				transwords[1]="fare";
			}
			System.out.println("#####ai");
			if(cnt==3 && transwords[1].equals("temple's")&& transwords[2].equals("important")
				&& transwords[0].equalsIgnoreCase("Kamakhya"))
			{
				System.out.println("imporatnce+++++");
				transwords[2]="importance";
			}
			if(cnt==5 && transwords[0].equalsIgnoreCase("Amritsar")
					&& transwords[1].equalsIgnoreCase("Suvarna")
					&& transwords[2].equals("temple's")
					&& transwords[3].equals("city"))
			{
				transwords[1]="golden";
			}
			if(cnt==4 &&transwords[0].equalsIgnoreCase("china")&&
					transwords[1].equalsIgnoreCase("agrarian")
					&& transwords[2].equals("country")
					&&(transwords[3].equals("is")||transwords[3].equals("was(occured)")))
			{
				data="0 3 1 2";
				transwords[1]="an "+transwords[1];
			}
			if(cnt==4 
					&& transwords[0].equals("tourism")
					&&transwords[1].equals("nice")
					&&transwords[2].equals("hobby")
					&&transwords[3].equals("is"))
			{
				data="0 3 1 2";
				transwords[1]="a "+transwords[1];
			}
			for(int i=0;i<cnt ;i++)
			{
				if(transwords[i].equals("stairses"))
				{
					transwords[i]="stairs";
				}
				if(transwords[i].equals("stepses"))
				{
					transwords[i]="stairs";
				}
			}
			if(cnt==4 
					&&transwords[0].equalsIgnoreCase("i")
					&&transwords[1].equalsIgnoreCase("she")
					&&transwords[2].equalsIgnoreCase("again")
					&&transwords[3].equalsIgnoreCase("met"))
			{
				transwords[1]="her";
			}
			
			if(cnt==4 
					&&transwords[0].equalsIgnoreCase("i")
					&&transwords[1].equalsIgnoreCase("one")
					&&transwords[2].equalsIgnoreCase("eye")
					&&transwords[3].equalsIgnoreCase("open"))
			{
				transwords[3]="opened";
			}
			
			if(cnt==3
					&&transwords[0].equalsIgnoreCase("time")
					&&transwords[1].equalsIgnoreCase("change")
					&&transwords[2].equalsIgnoreCase("is"))
			{
				transwords[1]="changed";
				transwords[2]="has";
			}
			if(cnt==7 
					&& transwords[4].equals("square")
					&&transwords[5].equals("foot"))
			{
				if(!transwords[3].equals("one")&&!transwords[3].equals("1"))
				{
					transwords[5]="feet";
				}
			}

			System.out.println("Translation is: " + data);
			temp_str = (data.split(" "));

			System.out
					.println("in string form the final seq is:" + temp_str[1]);
			System.out.println("final seq in int fomr******%%%%%:");
			for (int i = 0; i < cnt; i++) {
				final_seq[i] = Integer.parseInt(temp_str[i]);
				System.out.print(" " + final_seq[i]);
			}
			int cnt2=0;
			for (int i = 0; i < cnt; i++) {
				System.out.println("chcking condition");
				
				
			
					System.out.println("cndition satisfied-----------i="+i+"  i+1=");
					if (transwords[final_seq[i]].equals("I")
							&& transwords[final_seq[i + 1]].equals("is")) {
						transwords[final_seq[i + 1]] = "am";
					}
						}
		}
		bInf = false;
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equalsIgnoreCase("class")) {

			if (bInf2 == false) {
				rule_not_found();
			}
		}
	}
}
