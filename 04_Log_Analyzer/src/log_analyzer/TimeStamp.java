package log_analyzer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TimeStamp {

	public Short year,month,day,hour,minute,second;

	public TimeStamp(String time) {
		Pattern pattern = Pattern
		        .compile("(\\d{2})\\/(\\w*)\\/(\\d{4}):(\\d{2}):(\\d{2}):(\\d{2})\\s[+-]\\d{4}");
		Matcher matcher = pattern.matcher(time);

		try {
			matcher.matches();
			day = new Short(matcher.group(1));
			setMonth(matcher.group(2));
			year = new Short(matcher.group(3));
			hour = new Short(matcher.group(4));
			minute = new Short(matcher.group(5));
			second = new Short(matcher.group(6));
		} catch (PatternSyntaxException e) {
			System.out.println("Regexp matching error!");
		}

	}

	//convert month-name in Eng to Int
	public void setMonth(String monthInEn) {
		if (monthInEn.equals("Jan")) month = 1;
		else if (monthInEn.equals("Feb")) month = 2;
		else if (monthInEn.equals("Mar")) month = 3;
		else if (monthInEn.equals("Apr")) month = 4;
		else if (monthInEn.equals("May")) month = 5;
		else if (monthInEn.equals("Jun")) month = 6;
		else if (monthInEn.equals("Jul")) month = 7;
		else if (monthInEn.equals("Aug")) month = 8;
		else if (monthInEn.equals("Sep")) month = 9;
		else if (monthInEn.equals("Oct")) month = 10;
		else if (monthInEn.equals("Nov")) month = 11;
		else if (monthInEn.equals("Dec")) month = 12;
	}

	public void print() {
		System.out.print("Time stamp: " + year + "-" + month + "-" + day + " ");
		System.out.println(hour + ":" + minute + ":" + second);
	}
}
