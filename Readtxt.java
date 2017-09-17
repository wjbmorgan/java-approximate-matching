
/**
 * this class is used to read title file
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Readtxt {
	
	// this array list is used to store the titles when read
	public static ArrayList<String> lineTitle = new ArrayList<String>();

	// this method is used to read title file
	// and output titles to an array list
	public static void readTitle(String filePath) {
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader br = new BufferedReader(read);
				String line;
				while ((line = br.readLine()) != null) {
					lineTitle.add(line);
				}
				read.close();
			} else {
				System.out.println("file not found");
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

}
