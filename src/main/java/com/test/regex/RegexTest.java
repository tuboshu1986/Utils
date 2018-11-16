package com.test.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("\\s(?<time>(?<longTime>\\d{1,2}:\\d{1,2}:\\d{1,2})|(?<shortTime>\\d{1,2}:\\d{1,2}))$");
		//Pattern pattern = Pattern.compile("(\\d{4})-((\\d{2})-(\\d{2}))");
		Matcher matcher = pattern.matcher("sdf 20:12");
		
		System.out.println(matcher.groupCount());
		if(matcher.find()){
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
			System.out.println("--------------------------");
			System.out.println(matcher.group("time"));
			System.out.println(matcher.group("longTime"));
			System.out.println(matcher.group("shortTime"));
			System.out.println(matcher.group("other"));
		}
//		while(matcher.find()){
//			System.out.println(matcher.group());
//		}
	}

	public static void test2() {
		Pattern pattern = Pattern.compile("([a-z]+)(\\d+)");
		Matcher matcher = pattern.matcher("da23sfs34");

		System.out.println(matcher.groupCount());

		System.out.println(matcher.find(1));
		System.out.println(matcher.group());

		System.out.println(matcher.find());
		System.out.println(matcher.group());

		System.out.println(">>>>---------------------------------------");

		matcher = pattern.matcher("da23sfs34");

		while (matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) {
				System.out.println(matcher.group(i + 1));
			}
		}

		System.out.println(">>>>---------------------------------------");

		matcher.reset("fsd343hdf5464gd");
		while (matcher.find()) {
			for (int i = 0; i < matcher.groupCount(); i++) {
				System.out.println(matcher.group(i + 1));
			}
		}

		System.out.println(">>>>---------------------------------------");

		matcher.reset();
		if (matcher.lookingAt()) {
			System.out.println(matcher.group());
			for (int i = 0; i < matcher.groupCount(); i++) {
				System.out.println(matcher.group(i + 1));
			}
		}

		System.out.println(">>>>---------------------------------------");

		matcher.reset("fsd343sd");
		if (matcher.matches()) {
			System.out.println(matcher.group());
			for (int i = 0; i < matcher.groupCount(); i++) {
				System.out.println(matcher.group(i + 1));
			}
		}
	}
	
	public static void test1() {

		Pattern pattern = Pattern.compile("\\d+");
		System.out.println(pattern.pattern());

		System.out.println(">>>>******************************");

		System.out.println(Pattern.matches("\\d+", "1234"));

		System.out.println(">>>>******************************");

		System.out.println(Arrays.toString(pattern
				.split("12fgsd234er43frwer43354t43")));

		System.out.println(">>>>******************************");

		Matcher matcher = pattern.matcher("dfs123");
		System.out.println(matcher.matches());
		System.out.println(matcher.lookingAt());
		System.out.println(matcher.find());

		System.out.println(">>>>******************************");

		matcher = pattern.matcher("dfs123sdfs56");
		while (matcher.find()) {
			// System.out.println(matcher.start());
			// System.out.println(matcher.end());
			System.out.println(matcher.group());
		}

		System.out.println(">>>>******************************");

		matcher = pattern.matcher("dfs123sdfs56");
		matcher.find();
		System.out.println(matcher.groupCount());

		System.out.println(">>>>******************************");

		matcher = pattern.matcher("123sdfs56");
		if (matcher.lookingAt()) {
			System.out.println(matcher.start());
			System.out.println(matcher.end());
			System.out.println(matcher.group());
		}

		System.out.println(">>>>******************************");

		matcher = pattern.matcher("12356");
		if (matcher.matches()) {
			System.out.println(matcher.start());
			System.out.println(matcher.end());
			System.out.println(matcher.group());
		}

	}
}
