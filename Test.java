
/**
 * this class is the main class to test the matching system
 * @author Jiangbin Wang 728392
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Test {

	// this method is used to count the number of words in an string
	private static int splitNum(String str) {
		String[] temp = str.split(" ");
		return temp.length;
	}

	// this method is used to output subsets of a given string with given number
	// of words
	private static ArrayList<String> ngrams(int n, String str) {
		ArrayList<String> ngrams = new ArrayList<String>();
		String[] words = str.split(" ");
		for (int i = 0; i < words.length - n + 1; i++)
			ngrams.add(concat(words, i, i + n));
		return ngrams;
	}

	// concatenation
	private static String concat(String[] words, int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < end; i++)
			sb.append((i > start ? " " : "") + words[i]);
		return sb.toString();
	}

	// main method, to output all the approximate matches that are found in each
	// review and one of them may be the best match
	public static void main(String argv[]) {
		String filePath = "E:\\COMP90049 proj1\\film_titles.txt";
		Readtxt.readTitle(filePath);
		String encoding = "UTF-8";
		try {
			for (int i = 0; i < 50000; i++) {
				File file = new File(
						"E:\\COMP90049 proj1\\revs\\" + i + ".txt");
				if (file.isFile() && file.exists()) {
					InputStreamReader read = new InputStreamReader(
							new FileInputStream(file), encoding);
					BufferedReader br = new BufferedReader(read);
					String line;
					while ((line = br.readLine()) != null) {
						System.out.print("Review " + i + ", local: \t");
						for (int j = 0; j < Readtxt.lineTitle.size(); j++) {
							String title = Readtxt.lineTitle.get(j);
							int dist = ApproxMatch
									.localDistance(" " + title + " ", line);
							if (dist > (title.length() + 2) * 0.9) {
								System.out.print(title + "... ");
							}
						}
						System.out.println("");
						System.out.print("n-grams + levenshtein: \t");
						for (int j = 0; j < Readtxt.lineTitle.size(); j++) {
							String title = Readtxt.lineTitle.get(j);
							ArrayList<String> newLine = ngrams(splitNum(title),
									line);
							for (int k = 0; k < newLine.size(); k++) {
								int dist = ApproxMatch.levenshteinDistance(
										title, newLine.get(k));
								if (dist < 1) {
									System.out.print(title + "... ");
									break;
								}
							}
						}
						System.out.println("");
					}
					read.close();
					System.out.println("");
				}
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		System.out.println("Done.");
	}
}
