package edu.upenn.cis573.hwk1;

import static org.junit.Assert.*;

import org.junit.Test;


public class EncryptTest {

	/**
	 * test encrypt function
	 */
	@Test
    public void testEncrypt1() {
		StringBuffer sb = new StringBuffer();
		sb.append("defGH");
		StringBuffer result = Encrypt.encrypt(sb);
		//expect abcde
		assertEquals("abcde", result.toString());
	}	

	/**
	 * test encrypt function
	 */
	@Test
    public void testEncrypt2() {
		for (char i = 'd'; i < 'z'; i++) {
			assertEquals(Encrypt.encrypt(i), i - 3);
		}
		for (char i = 'D'; i < 'Z'; i++) {
			assertEquals(Encrypt.encrypt(i), i -'A' + 'a' - 3);
		}
	}
}
