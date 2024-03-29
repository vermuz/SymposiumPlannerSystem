package ws.prova.mule.impl;

import java.util.ArrayList;
import java.util.List;

import ws.prova.kernel2.ProvaConstant;

public class SubmissionDecision {

	public static ProvaConstant appropriate = null;

	public static String[] europeLocations = { "Europe", "France", "Montpellier" };
	//public static String[] europeLocations = { "Europe", "Spain", "Barcelona" };
	//public static String[] uslocations = { "USA", "US", "America", "BRF 2011" };
	//public static String[] uslocations = {"USA", "Canada" , "America" , "RuleML Struct"};
	public static String[] brfSubmissionCategory = { "BRF2011", "Demo",
			"Full paper" };
	public static String[] ijcaiSubmissionCategory = { "phd paper",
			"Full paper" };
	public static String[] ecaiSubmissionCategory = { "phd paper",
	        "Full paper" };

	private static double brfevent = 0;
	private static double ijcaievent = 0;
	private static double ecaievent = 0;

	public static List decision(Object xid, Object country,
			Object submissionCategory, Object keywords, Object playLoad) {

		brfevent = 0;
		ijcaievent = 0;
		ecaievent = 0;

		computeLocation(country.toString());
		computeSubmissionCategory(submissionCategory.toString());
		computeKeyWords(keywords.toString(), playLoad);

		List list = new ArrayList();
		
		if (brfevent != 0) {
			List temp = new ArrayList();
			temp.add("BRF2011");
			temp.add(brfevent);
			list.add(temp);
		}

		if (ijcaievent != 0) {
			List temp = new ArrayList();
			temp.add("IJCAI2011");
			temp.add(ijcaievent);
			list.add(temp);
		}
		
		if (ecaievent != 0) {
			List temp = new ArrayList();
			temp.add("ECAI2012");
			temp.add(ecaievent);
			list.add(temp);
		}

		return list;

	}

	private static void computeKeyWords(String keywords, Object playLoad) {
		// TODO Auto-generated method stub
		List answerMessageList = ((List) playLoad);
		String[] keys = keywords.toString().split(";");
		for (int i = 0; i < answerMessageList.size() - 1; i++) {
			String temp = answerMessageList.get(i).toString();
			for (int j = 0; j < keys.length; j++) {
				String key = keys[j].trim();
				if (temp.contains(key)) {
					if (temp.contains("brf_2011"))
						brfevent += 1.5;
					else if (temp.contains("ijcai_2011"))
						ijcaievent += 1.5;
					else if (temp.contains("ecai_2012"))
						ecaievent += 1.5;
				}
			}
		}

	}

	private static void computeSubmissionCategory(String submissionCategory) {
		// TODO Auto-generated method stub
		if (findInArray(submissionCategory, brfSubmissionCategory))
			brfevent += 1;

		if (findInArray(submissionCategory, ijcaiSubmissionCategory))
			ijcaievent += 1;
		
		if (findInArray(submissionCategory, ecaiSubmissionCategory))
			ecaievent += 1;


	}

	private static void computeLocation(String country) {
		// TODO Auto-generated method stub
		//if (findInArray(country, europeLocations))
			//ijcaievent += 1;
		
		if (findInArray(country, europeLocations))
			ecaievent += 1;

		//if (findInArray(country, uslocations))
			//brfevent += 1;
	}

	private static boolean findInArray(String key, String[] arrays) {
		for (int m = 0; m < arrays.length; m++) {
			if (key.equalsIgnoreCase(arrays[m]))
				return true;
		}
		return false;
	}

	public static ProvaConstant isPermit() {
		return appropriate;
	}

}
