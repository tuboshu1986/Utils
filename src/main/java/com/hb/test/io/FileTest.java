package com.hb.test.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTest {
	public static void main(String[] args) {
		File d = new File("E:/huangbo/");
		File[] fs = d.listFiles();
		long totalSize = 0;
		for (File f : fs) {
			long fsSize = fSize(getSubFiles(f));
			totalSize += fsSize;
			System.out.println(f.getName() + "-【" + fsSize + "M】");
		}
		System.out.println("【" + totalSize + "M】");
	}

	public static List<File> getSubFiles(File parentDirectory) {
		List<File> rst = new ArrayList<File>();
		File[] fs = parentDirectory.listFiles();
		if (fs != null && fs.length > 0) {
			for (File f : fs) {
				if (f.isFile()) {
					rst.add(f);
				} else if (f.isDirectory()) {
					rst.addAll(getSubFiles(f));
				}
			}
		}
		return rst;
	}

	public static long fSize(List<File> fs) {
		long rst = 0;
		for (File f : fs) {
			rst += f.length();
		}
		return rst / 1024 / 1024;
	}

}
