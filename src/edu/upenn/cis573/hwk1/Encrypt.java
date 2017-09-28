package edu.upenn.cis573.hwk1;

public class Encrypt {
		
	
	/**
	 * encrypt a char using Caesar cipher 
	 * @param c
	 * @return
	 */
	public static char encrypt(char c) {
		c = Document.toLower(c);
		if (c == 'a')
			return 'x';
		if (c == 'b')
			return 'y';
		if (c == 'c')
			return 'z';
		if (Document.isLetter(c))
			return (char)(c - 3);
		return c;
	}
		
	/**
	 * encrypt a string using Caesar cipher 
	 * @param str
	 * @return
	 */
	public static StringBuffer encrypt(StringBuffer str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) 
			sb.append(encrypt(str.charAt(i)));	
		return sb;
	}
}
