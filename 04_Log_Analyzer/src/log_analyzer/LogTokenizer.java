package log_analyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class LogTokenizer {

	String hostIP;
	TimeStamp timeStamp;
	String requestURL;
	String status; //3-digit status code
	Integer bytes; //volume of flow

	public LogTokenizer(String log, String logGrammar) {
		// TODO Auto-generated constructor stub

		Pattern pattern = Pattern.compile(logGrammar);
		Matcher matcher = pattern.matcher(log);

		// System.out.println(log);

		try {

			if (!matcher.matches()) {
				System.out.println(log);
			}
			hostIP = matcher.group(1);
			timeStamp = new TimeStamp(matcher.group(3));
			requestURL = matcher.group(4);
			status = matcher.group(5);

			if (!matcher.group(6).equals("-")) {
				bytes = new Integer(matcher.group(6));
			} else {
				bytes = 0;
			}
		} catch (PatternSyntaxException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void print() {
		System.out.println("Host IP: " + hostIP);
		timeStamp.print();
		System.out.println("Request URL: " + requestURL);
		System.out.println("HTTP status: " + status);
		System.out.println("Bytes: " + bytes);
	}

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// String log =
	// "222.205.12.54 - - [18/Mar/2012:17:37:24 +0800] \"GET /showCourse.php?id=54%20and%20ascii(substring(load_file(0x2f7661722f646174612f6170616368652f6874646f63732f7375626d69742e706870),1697,1))%3E10%20and%201%3C2 HTTP/1.1\" 302 1604";
	// final String logGrammar =
	// "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|::1)\\s\\-\\s(\\-|\\w*)\\s\\[([^]]+)\\]\\s\"\\w*\\s([htp:/]*[\\w\\s~!@#$%&()-_+=:;'.,/?]*)\\s[\\w/.]*\"\\s(\\d{1,3})\\s(\\d*|-)";
	//
	// LogTokenizer lt = new LogTokenizer(log, logGrammar);
	//
	// lt.print();
	// }
}
