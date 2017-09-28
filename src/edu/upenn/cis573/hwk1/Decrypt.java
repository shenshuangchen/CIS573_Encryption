package edu.upenn.cis573.hwk1;

import java.util.Map;

public class Decrypt {

	/**
	 * decrypt one char using the map
	 * @param c
	 * @param map
	 * @return
	 */
	public static char decrypt(char c, Map<Character, Character> map) {
		if (map.containsKey(c))
			return map.get(c);
		return c;
	}
	
	/**
	 * decrypt a string using the map
	 * @param str
	 * @param map
	 * @return
	 */
	public static StringBuffer decrypt(StringBuffer str, Map<Character, Character> map) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			sb.append(decrypt(str.charAt(i), map));
		}		
		return sb;
	}
}
