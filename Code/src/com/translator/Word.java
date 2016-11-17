package com.translator;

public class Word {

	public String ACTUAL;
	public String TYPE;
	public String ROOT;
	public String LCAT;
	public String PERS;
	public String NUM;
	public String CASE;
	public String GEND;
	public String SUFFIX;
	public String VIBHAKTI;
	@Override
	public String toString() {
		return "Word [ACTUAL=" + ACTUAL + ", TYPE=" + TYPE + ", ROOT=" + ROOT
				+ ", LCAT=" + LCAT + ", PERS=" + PERS + ", NUM=" + NUM
				+ ", CASE=" + CASE + ", GEND=" + GEND + ", SUFFIX=" + SUFFIX
				+ ", VIBHAKTI=" + VIBHAKTI + "]";
	}
	public Word() {
		super();
	}
	public Word(String aCTUAL, String tYPE, String rOOT, String lCAT, String pERS,
			String nUM, String cASE, String gEND, String sUFFIX, String vIBHAKTI) {
		super();
		ACTUAL = aCTUAL;
		TYPE = tYPE;
		ROOT = rOOT;
		LCAT = lCAT;
		PERS = pERS;
		NUM = nUM;
		CASE = cASE;
		GEND = gEND;
		SUFFIX = sUFFIX;
		VIBHAKTI = vIBHAKTI;
	}
	
	
	
}
