package edu.upenn.cis573.hwk1;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class Main {

	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("The number of runtime arguments is not correct!");
			System.exit(1);
		}
		processCorpus(args[0], getFileList(args[0]));
	}

	/**
	 * get all file list
	 * 
	 * @param directory
	 * @return
	 */
	public static ArrayList<String> getFileList(String directory) {
		File file = new File(directory);
		if (!file.exists()) {
			System.out.println("The specified directory does not exist!");
			System.exit(1);
		}
		if (!file.isDirectory()) {
			System.out.println("The specified path is not a directory!");
			System.exit(1);
		}
		String[] list = file.list();
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < list.length; i++) {
			if (list[i].startsWith("."))
				continue;
			result.add(list[i]);
		}
		if (result.size() == 0) {
			System.out.println("The specified directory is empty!");
			System.exit(1);
		}
		return result;
	}

	/**
	 * process all corpus
	 * @param directory
	 * @param corpus
	 */
	public static void processCorpus(String directory, ArrayList<String> corpus) {
		int totalcorrect = 0, totalincorrect = 0;
		for (int i = 0; i < corpus.size(); i++) {
			Map<Character, Character> model = Document.buildFrequencyModel(i, directory, corpus);
			StringBuffer original = Document.readFile(directory + "/" + corpus.get(i));
			StringBuffer encrypt = Encrypt.encrypt(original);
			StringBuffer decrypt = Decrypt.decrypt(encrypt, model);			
			int[] result = getCorrectIncorrectNumber(original, decrypt);
			totalcorrect += result[0];
			totalincorrect += result[1];
			System.out.println(corpus.get(i) + ": " + result[0] + " correct, " + result[1] + " incorrect");
		}
		double accuracy = 0;
		if (totalcorrect + totalincorrect != 0)
			accuracy = 100.0 * (double) (totalcorrect) / (double) (totalcorrect + totalincorrect);
		System.out.println("Total: " + totalcorrect + " correct, " + totalincorrect + " incorrect");
		System.out.println(String.format("Accuracy: %.2f", accuracy) + "%");
	}

	/**
	 * get the correct and incorrect letter number
	 * @param original
	 * @param decrypt
	 * @return
	 */
	public static int[] getCorrectIncorrectNumber(StringBuffer original, StringBuffer decrypt) {
		int[] number = { 0, 0 };
		for (int i = 0; i < original.length() && i < decrypt.length(); i++) {
			if (!Document.isLetter(original.charAt(i)))
				continue;
			if (Document.toLower(original.charAt(i)) == Document.toLower(decrypt.charAt(i)))
				number[0]++;
			else
				number[1]++;
		}
		return number;
	}

}
