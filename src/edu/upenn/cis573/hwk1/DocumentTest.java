package edu.upenn.cis573.hwk1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DocumentTest {
	
	/**
	 * test tolower function
	 */
	@Test
    public void testToLower() {
		assertEquals('a', Document.toLower('A'));
		assertEquals('.', Document.toLower('.'));
		assertEquals('a', Document.toLower('a'));
	}
	
	/**
	 * test isLetter function
	 */
	@Test
    public void testIsLetter() {
		assertEquals(true, Document.isLetter('A'));
		assertEquals(false, Document.isLetter('.'));
		assertEquals(true, Document.isLetter('a'));
	}


	/**
	 * test frequency function
	 */
	@Test
    public void testGetFrequency() {
		// expect frequency value
		int[] expectedc = {1,2,3,0,0,
					       0,0,0,0,0,
					       0,0,0,0,0,
					       0,0,0,0,0,
					       0,0,0,0,0,
					       0};
		StringBuffer sb = new StringBuffer();
		sb.append("abc324392874bc541389c....");
		int[] frequency = Document.getFrequency(sb);
		for (int i = 0; i < frequency.length; i++) {
			assertEquals(expectedc[i], frequency[i]);
		}
	}
}
