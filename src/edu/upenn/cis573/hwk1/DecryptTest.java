package edu.upenn.cis573.hwk1;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DecryptTest {

	/**
	 * test for decrypt function
	 */
	@Test
    public void testDecrypt1() {
		// build the model
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('c', 'a');
		// expect a
		assertEquals('a', Decrypt.decrypt('c', map));
		// expect .
		assertEquals('.', Decrypt.decrypt('.', map));
	}
	

	/**
	 * test for decrypt function
	 */
	@Test
    public void testDecrypt2() {
		StringBuffer sb = new StringBuffer();
		sb.append("abc425..");
		// build the model
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('c', 'd');
		map.put('b', 'e');
		map.put('a', 'f');
		// expect fed425..
		assertEquals("fed425..", Decrypt.decrypt(sb, map).toString());
	}
}
