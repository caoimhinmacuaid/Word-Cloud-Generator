package ie.gmit.dip;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException; 
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/** Runner class containing console-based menu-driven UI
 * 
 * @author Kevin Wade
 *
 */

public class Runner {
	
	/**
	 * Main method of Runner class
	 * @param args
	 * @throws Exception
	 */

	public static void main(String args[]) throws Exception{


		Scanner scanner = new Scanner(System.in);
		int amountOfWords = 0;
		String inputName;
		String outputName;
		boolean validInput = false;


		

		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*           Word Cloud Generator V0.1           *");
		System.out.println("*     H.Dip in Science (Software Development)     *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");



		System.out.println("Enter File Name:"); // Asking user to specify the file to process
		inputName = scanner.nextLine();
		System.out.println();

		while(validInput ==false) {
			System.out.println("How many words would you like to display? (Max = 25)"); // Asking user if they would like to save the file
			amountOfWords = Integer.valueOf(scanner.nextLine());											  // to a specific or a default location
			if(amountOfWords>25) {
				System.out.println(amountOfWords + " exceeds maximum limit. Please try again");
			} else {
				validInput = true;
			}
			
		}
		
		System.out.println();
		System.out.println("What would you like to call the output file? (e.g. image.png)");
		outputName = scanner.nextLine(); // specific location option
		
		File myObj = new File(inputName);
		
		
		WordFile wf = new WordFile(myObj);
		Map<String, Integer> hashMap = wf.parse();
		
		
		FrequencyTable ft = new FrequencyTable(hashMap);
		Map<String,Integer> sortedHashMap = ft.sort();
		
		
		WordCloud wc = new WordCloud(sortedHashMap, amountOfWords,outputName);
		wc.generateWordCloud();
		
		System.out.println("Word cloud saved as " + outputName);
		
		
		
		

	}	

	


}









