package com.test.library;

import java.util.Arrays;
import java.util.Locale;

public class LocalTest {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Locale locale = Locale.getDefault();
		System.out.println(locale.getCountry());
		System.out.println(locale.getDisplayCountry());
		System.out.println(locale.getLanguage());
		System.out.println(locale.getDisplayLanguage());
		System.out.println(Arrays.toString(locale.getISOCountries()));
		System.out.println(Arrays.toString(locale.getISOLanguages()));
		System.out.println(Arrays.toString(locale.getAvailableLocales()));

		System.out.println(">>>>*****************************************");

	}
}
