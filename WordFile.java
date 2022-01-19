package ie.gmit.dip;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

// Import the File class
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class WordFile {
	
	private File textFile;
	private Map<String, Integer> hashMap = new HashMap<>();
	private String data;
	private File ignoreWords = new File("src/ie/gmit/dip/ignorewords.txt");
	private boolean found = false;
	
	public WordFile(File wordFile) {
		
		this.textFile = wordFile;
		
	}
	
	/** Parses a word document and counts the frequency of each word.
	 * Ignores words that are found in the document ignorewords.txt
	 * Stores words and the frequency they occur in a hashmap
	 * 
	 * @return hashMap
	 * 
	 * <i>I estimate that the time complexity of this method is O(1) as we
	 * are adding elements to a hashmap</i>
	 */
	
	public Map<String, Integer> parse() {
		
		

		try {
			File myObj = textFile;
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();

				String[] words = data.split(" ");
				for (String word : words) {

					word = word.toLowerCase();
					if (word.endsWith(",")||word.endsWith(".")||word.endsWith("]")) {
						word = word.substring(0, word.length() - 1);
					}
					// Finding out whether the HashMap contains
					// key and returns null if not.


					try(BufferedReader br = new BufferedReader(new FileReader("src/ie/gmit/dip/ignorewords.txt"))) {

						String line = br.readLine();

						while (line != null) {

							line = br.readLine();
							if(word.equals(line)) {
								found = true;

							}
						}

					} catch(Exception e) {
						
					}


					if((word.length()>1)&(found==false) ) {

						Integer integer = hashMap.get(word);

						if (integer == null)
							// Store word as key and its
							// frequency as value if word not already present
							hashMap.put(word, 1);

						else {
							// Incrementing value by one if word
							// already present
							hashMap.put(word, integer + 1);
						}
					}

					found = false;



				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}


		

		return hashMap;




	}
	
	
	
	

}
