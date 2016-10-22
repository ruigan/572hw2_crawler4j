package advanced;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import models.Node1;
import models.Node2;
import models.Node3;

public class CrawlStat {
	public static long totalLinks = 0;
	public static long fetchLinks = 0;
	public static long visitLinks = 0;
	public static long totalTextSize = 0;
	
	
	
	private static String fetchFileName = "fetch_NewsSite1.csv";
	private static String visitFileName = "visit_NewsSite1.csv";
	private static String urlsFileName = "urls_Newsite1.csv";
	
	
	public static HashMap<Integer, Integer> StatusCodeMap = new HashMap<>();
	public static int[] fileSize = {1024, 10240, 102400, 1024 * 1024};
	public static int[] fileSizeCounter = new int[5];
	public static HashSet<String> contentTypeSet = new HashSet<>();
	
	public static ArrayList<Node1> fetchList = new ArrayList<>();
	public static ArrayList<Node2> visitList = new ArrayList<>();
	public static ArrayList<Node3> urlsList = new ArrayList<>();
	
	
	
	/*
	 * Content-Type
	 */
	public static void recordContentType(String myType) {
		if (!contentTypeSet.contains(myType)) {
			contentTypeSet.add(myType);
		}
	}
	
	
	
	/*
	 * Status Code
	 */
	public static void recordStatusCode(int key) {
		
		if (StatusCodeMap.containsKey(key)) {
			StatusCodeMap.replace(key, StatusCodeMap.get(key) + 1);
		} else {
			StatusCodeMap.put(key, 1);
		}
	}
	
	
	/*
	 * File Size
	 */
	public static void recordFileSize(int mySize) {
		
		if (mySize < fileSize[0]) {		   // < 1KB
			fileSizeCounter[0]++;
		} else if (mySize < fileSize[1]) { // 1KB ~ <10KB
			fileSizeCounter[1]++;
		} else if (mySize < fileSize[2]) { // 10KB ~ <100KB
			fileSizeCounter[2]++;
		} else if (mySize < fileSize[3]) { // 100KB ~ <1MB
			fileSizeCounter[3]++;
		} else {						   // >= 1MB
			fileSizeCounter[4]++;
		}
	}
	
	
	/*
	 * Record fetch item
	 */
	public static void recordToFetchList(Node1 node) {
		if (node == null) return;
		if (fetchList.size() == 50) {
			dumpFetch(fetchFileName);
			fetchList.clear();
		}
	}
	
	
	/*
	 * Record visit item
	 */
	public static void recordToVisitList(Node2 node){
		if (node == null) return;
		if (visitList.size() == 50) {
			dumpVisit(visitList, visitFileName);
			visitList.clear();
		}
	}
	
	/*
	 * Record urls
	 */
	public static void recordToUrlList(Node3 node) {
		if (node == null) return;
		if (urlsList.size() == 50) {
			dumpUrls(urlsList, urlsFileName);
			urlsList.clear();
		}
	}
	
	/*
	 * Dump Urls
	 */
	public static void dumpUrls(ArrayList<Node3> list, String filename) {
		if (list.size() == 0) {
			return;
		}
		// open file to append new lines
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
			
			for (Node3 node : list) {
				bw.write(node.toString());
				bw.newLine();
			}
			
			bw.flush();
			bw.close();
			System.out.println("Write to file successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/*
	 * dump fetch list
	 */
	public static void dumpFetch(String filename) {
		// in normal cases, there should be 50 records in list
		if (fetchList.size() == 0) {
			return;
		}
		
		// open file to append new lines
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
			
			for (Node1 node : fetchList) {
				bw.write(node.toString());
				bw.newLine();
			}
			
			bw.flush();
			bw.close();
			System.out.println("Fetch list write to file " + filename + " successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Dump Visit List
	 */
	public static void dumpVisit(ArrayList<Node2> list, String filename) {
		if (list.size() == 0) {
			return;
		}
		// open file to append new lines
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
			
			for (Node2 node : list) {
				bw.write(node.toString());
				bw.newLine();
			}
			
			bw.flush();
			bw.close();
			System.out.println("Write to file successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
