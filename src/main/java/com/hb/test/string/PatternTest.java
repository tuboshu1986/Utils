package com.hb.test.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
	public static void main(String[] args) throws IOException {
		Map<String, Integer> map1 = fileMapContent("C:/Users/DELL/Desktop/new 1.txt");
		Map<String, Integer> map2 = fileMapContent("E:/huangbo/temp/log/AAAA.log");
		System.out.println(mapValueSum(map1));
		System.out.println(mapValueSum(map2));
		System.out.println(compare(map1, map2));
		System.out.println(compareStr(map1, map2));
	}
	
	public static <T> Integer mapValueSum(Map<T, Integer> map){
		if(map == null || map.size() == 0)
			return 0;
		int sum = 0;
		for (T key : map.keySet()) {
			sum += map.get(key);
		}
		return sum;
	}

	public static <T, K> String compareStr(Map<T, K> map1, Map<T, K> map2){
		StringBuffer rst = new StringBuffer("");
		map2 = new HashMap<T, K>(map2);
		for (T key : map1.keySet()) {
			K map2Value = map2.get(key);
			String map2StrValue = "";
			if(map2Value != null){
				map2StrValue = map2Value.toString();
				map2.remove(key);
			}
			rst.append(key + "\t" + map1.get(key) + "\t" + map2StrValue);
			rst.append("\n");
		}
		for (T key : map2.keySet()) {
			rst.append(key + "\t#####\t" + map2.get(key));
			rst.append("\n");
		}
		return rst.toString();
	}

	public static <T, K> boolean compare(Map<T, K> map1, Map<T, K> map2) {
		if (map1 == null || map2 == null || map1.size() != map2.size())
			return false;
		for (T key : map1.keySet()) {
			if (!map1.get(key).equals(map2.get(key))) {
				return false;
			}
		}
		return true;
	}

	public static Map<String, Integer> fileMapContent(String fpath)
			throws IOException {
		return fileRstMap(fileContent(fpath));
	}

	public static List<String> fileContent(String fpath) throws IOException {
		List<String> rst = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				fpath)));
		String line = null;
		while ((line = reader.readLine()) != null) {
			rst.add(line);
		}
		reader.close();
		return rst;
	}

	public static Map<String, Integer> fileRstMap(List<String> rows) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String row : rows) {
			if (row != null && !"".equals(row.trim()))
				map.put(fileName(row), Integer.parseInt(fileRstCount(row)));
		}
		return map;
	}

	public static String fileRstCount(String str) {
		Pattern p = Pattern.compile("：\\d+$");
		Matcher m = p.matcher(str);
		String rst = null;
		while (m.find()) {
			rst = m.group();
			break;
		}
		return rst.substring(1);
	}

	public static String fileName(String str) {
		Pattern p = Pattern.compile("【[\\s\\S]+】");
		Matcher m = p.matcher(str);
		String rst = null;
		while (m.find()) {
			rst = m.group();
			break;
		}
		return rst.substring(1, rst.length() - 1);
	}

}
