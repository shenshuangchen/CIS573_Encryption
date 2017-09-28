package edu.upenn.cis573.hwk1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;

public class Document {

	private static final int NUM = 26;

	/**
	 * build the frequency model
	 * 
	 * @param encryptIndex target encrypt index
	 * @param directory the directory path
	 * @param filelist	the file name list
	 * @return	the mode
	 */
	public static Map<Character, Character> buildFrequencyModel(int encryptIndex, String directory, ArrayList<String> filelist) {
		Map<Character, Character> map = new HashMap<Character, Character>();
		StringBuffer encrypt = Encrypt.encrypt(Document.readFile(directory + "/" + filelist.get(encryptIndex)));
		char[] corpus = getSortedFrequency(getFrequency(directory, filelist, encryptIndex));
		char[] document = getSortedFrequency(getFrequency(encrypt));
		for (int i = 0; i < NUM; i++)
			map.put(toLower(document[i]), corpus[i]);
		return map;
	}

	/**
	 * get the frequency array
	 * 
	 * @param sb
	 * @return
	 */
	public static int[] getFrequency(StringBuffer sb) {
		int[] frequency = new int[NUM];
		for (int i = 0; i < NUM; i++)
			frequency[i] = 0;
		for (int i = 0; i < sb.length(); i++) {
			if (!isLetter(sb.charAt(i)))
				continue;
			frequency[toLower(sb.charAt(i)) - 'a']++;
		}
		return frequency;
	}
	
	/**
	 * get the frequency array
	 * 
	 * @param directory
	 * @param file
	 * @return
	 */
	public static int[] getFrequency(String directory, String file) {
		return getFrequency(readFile(directory + "/" + file));
	}

	/**
	 * get the frequency array
	 * 
	 * @param directory
	 * @param filelist
	 * @return
	 */
	public static int[] getFrequency(String directory, ArrayList<String> filelist) {
		int[] frequency = new int[NUM];
		for (int i = 0; i < NUM; i++)
			frequency[i] = 0;
		for (int i = 0; i < filelist.size(); i++) {
			int[] temp = getFrequency(directory, filelist.get(i));
			for (int j = 0; j < NUM; j++)
				frequency[j] += temp[j];
		}
		return frequency;
	}

	/**
	 * get the frequency array
	 * 
	 * @param directory
	 * @param filelist
	 * @param excludedIndex
	 * @return
	 */
	public static int[] getFrequency(String directory, ArrayList<String> filelist, int excludedIndex) {
		if (excludedIndex < 0 || excludedIndex >= filelist.size() || filelist.size() == 0)
			return getFrequency(directory, filelist);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < filelist.size(); i++) {
			if (i != excludedIndex)
				list.add(filelist.get(i));
		}
		return getFrequency(directory, list);
	}

	/**
	 * read the file and save data to the StringBuffer
	 * 
	 * @param file
	 * @return
	 */
	public static StringBuffer readFile(String file) {
		StringBuffer sb = new StringBuffer();
		try {
			FileReader reader = new FileReader(file);
			int value = 0;
			while ((value = reader.read()) != -1)
				sb.append((char) value);
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("The specified directory cannot be opened for reading!");
			System.exit(1);
		}
		return sb;
	}
	
	/**
	 * check if the char is a letter
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isLetter(char c) {
		if ('a' <= c && c <= 'z')
			return true;
		if ('A' <= c && c <= 'Z')
			return true;
		return false;
	}
	
	/**
	 * to lower
	 * @param c
	 * @return
	 */
	public static char toLower(char c) {
		if ('A' <= c && c <= 'Z')
			return (char)(c - 'A' + 'a');
		return c;
	}

	/**
	 * sort the frequency and return the rank array
	 * @param frequency
	 * @return
	 */
	private static char[] getSortedFrequency(int[] frequency) {
		if (frequency.length != NUM)
			throw new RuntimeException("Error in frequency length!");
		char[] character = new char[NUM];
		for (int i = 0; i < NUM; i++)
			character[i] = (char) ('a' + i);
		for (int i = 0; i < NUM; i++) {
			for (int j = i + 1; j < NUM; j++) {
				if (frequency[i] < frequency[j]) {
					int temp = frequency[i];
					frequency[i] = frequency[j];
					frequency[j] = temp;
					char tempchar = character[i];
					character[i] = character[j];
					character[j] = tempchar;
				}
			}
		}
		return character;
	}
}
