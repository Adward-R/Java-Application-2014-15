package log_analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LogAnalyzer {

	private static final String logGrammar = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|::1)\\s\\-\\s(\\-|\\w*)\\s\\[([^]]+)\\]\\s\"\\w*\\s([htp:/]*[\\w\\s~!@#$%&()-_+=:;'.,/?]*)\\s[\\w/.]*\"\\s(\\d{1,3})\\s(\\d*|-)";

	File file;
	Map<String, Integer> cntURL;
	Map<String, Integer> cntIP;
	Set<String> brokenLinks;
	Long totalBytes;
	int cntVisit[];
	//private final Long totalBytes1;

	public LogAnalyzer() {
		file = null;
		cntURL = new HashMap<String, Integer>();
		cntIP = new HashMap<String, Integer>();
		brokenLinks = new HashSet<String>();
		totalBytes = (long) 0;
		//totalBytes1 = (long) 0; // initializes the counter
		cntVisit = new int[24];

	}

	public void readFile(String pathName) {
		file = new File(pathName);
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String tmpLine;// = null;

			while ((tmpLine = reader.readLine()) != null) {
				// System.out.println(tempLine);
				LogTokenizer lt = new LogTokenizer(tmpLine, logGrammar);
				// stores host IPs & their appearing frequency
				if (cntIP.containsKey(lt.hostIP)) {
					cntIP.put(lt.hostIP, cntIP.get(lt.hostIP) + 1);
				} else {
					cntIP.put(lt.hostIP, 1);
				}
				// stores visiting frequency per hour
				cntVisit[lt.timeStamp.hour]++;
				// stores requested URLs & frequency
				if (cntURL.containsKey(lt.requestURL)) {
					cntURL.put(lt.requestURL, cntURL.get(lt.requestURL) + 1);
				} else {
					cntURL.put(lt.requestURL, 1);
				}
				// stores requested URLs that has HTTP code 404 & frequency
				if (lt.status.equals("404")) {
					brokenLinks.add(lt.requestURL);
				}
				// Total amount of data indicator
				totalBytes += lt.bytes;
			}

			reader.close();

		} catch (IOException e) {
			System.out.println("Illegal file!");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					System.out.println("A run-time error occured when closing the file reader!");
				}
			}
		}

	}

	// Analyzes the appearing frequency of each HOST IP
	public void analyzeHostIP() {
		// sorts by value of the map
		ArrayList<Map.Entry<String, Integer>> IPlist = new ArrayList<Map.Entry<String, Integer>>(
		        cntIP.entrySet());
		Collections.sort(IPlist, new Comparator<Map.Entry<String, Integer>>() {
			@Override //in decending order
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		System.out.println("\nBusiest Host IP:");
		int i = 0, showNum = 10;
		for (Map.Entry<String, Integer> entry : IPlist) {
			System.out
			        .println("\t" + entry.getKey() + " : " + entry.getValue());
			if (++i >= showNum) {
				break;
			}
		}
	}

	//analyzes the appearing frequency of each request URL
	public void analyzeRequestURL() {
		ArrayList<Map.Entry<String, Integer>> URLlist = new ArrayList<Map.Entry<String, Integer>>(
		        cntURL.entrySet());
		Collections.sort(URLlist, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		System.out.println("\nPopular Pages:");
		int i = 0, showNum = 10;
		for (Map.Entry<String, Integer> entry : URLlist) {
			System.out.println("\t" + entry.getKey() + " : " + entry.getValue());
			if (++i >= showNum) {
				break;
			}
		}
	}

	public void analyzeBrokenLinks() {
		System.out.println("\nBroken Links: " + brokenLinks.size());
		for (String URL : brokenLinks) {
			System.out.println("\t" + URL);
		}
	}

	// analyzes volume of data flow
	public void analyzeBytes() {
		double dataMB = 1.0 * totalBytes / 1024 / 1024;
		DecimalFormat df = new DecimalFormat("#.0000"); // 只输出4位小数
		System.out.println("\nData Delivered:\n\t" + df.format(dataMB) + " MB");
	}

	public void analyzeRushHour() {
		Map<Integer, Integer> rushHour = new HashMap<Integer, Integer>();
		for (int i = 0; i < 24; i++) {
			rushHour.put(i, cntVisit[i]);
		}
		ArrayList<Map.Entry<Integer, Integer>> hourList = new ArrayList<Map.Entry<Integer, Integer>>(
		        rushHour.entrySet());
		Collections.sort(hourList, new Comparator<Map.Entry<Integer, Integer>>() {
			        @Override
			        public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				        return o2.getValue().compareTo(o1.getValue());
			        }
		});

		// result displaying
		System.out.println("\nRush Hours:");
		int i = 0, showNum = 5;
		for (Map.Entry<Integer, Integer> entry : hourList) {
			System.out
			        .println("\t" + entry.getKey() + " : " + entry.getValue());
			if (++i >= showNum) {
				break;
			}
		}

	}

	public void showAnalysis() {
		System.out.println("File Anaylyzed:\n\t" + file.getName());
		analyzeRequestURL();
		analyzeHostIP();
		analyzeBrokenLinks();
		analyzeBytes();
		analyzeRushHour();
	}

	public static void main(String[] args) {
		LogAnalyzer la = new LogAnalyzer();
		la.readFile("access.log");
		la.showAnalysis();
	}
}
