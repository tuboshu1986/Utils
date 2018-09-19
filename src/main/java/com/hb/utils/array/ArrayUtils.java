package com.hb.utils.array;

import java.util.Arrays;

public class ArrayUtils {
	@SuppressWarnings("unchecked")
	public static <T> String toString(T...objs) {
		if (objs == null || objs.length == 0)
			return null;
		return Arrays.toString(objs);
	}

	public static void main(String[] args) {
		Object[] objs = {12, 34};
		System.out.println(toString(objs));
	}
}
