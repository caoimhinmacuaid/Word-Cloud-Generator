package ie.gmit.dip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

public class WordCloud {

	private int numOfWords;
	private Map<String, Integer> frequencyTable;
	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 62);
	private BufferedImage image = new BufferedImage(800, 400, BufferedImage.TYPE_4BYTE_ABGR);
	private int x=200;
	private int y=175;
	private int fontSize = 45;
	private Graphics graphics = image.getGraphics();
	private int count =0;
	private Random RANDOM = new Random();
	private String[] availableFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.ENGLISH);
	private String  outputName;


	public WordCloud(Map<String, Integer> frequencyTable, int numOfWords,String outputName){
		this.frequencyTable = frequencyTable;
		this.numOfWords = numOfWords;
		this.outputName = outputName;
		
		



	}
	
	/**
	 *  Uses a frequency table containing words and their frequencies to
	 *  graphically display a word cloud. Number of words displayed and output
	 *  are taken in via the object constructor
	 *  
	 *  <i> I estimate that the time complexity of this method is O(n)
	 *  as we are iterating through a hashmap </i>
	 */

	public void generateWordCloud() {
		
		
		Iterator it = frequencyTable.keySet().iterator();
		Iterator it2 = frequencyTable.values().iterator();
		int spiralCount =0;
		int originalFontSize = fontSize;

		while (it.hasNext()&& count <numOfWords) {

			String stringValue = String.valueOf(it2.next());
			double value = Double.valueOf(stringValue);
			fontSize = (int)(value*0.5); //font size dependent on frequency of word
			
			
			String randomFont = availableFonts[RANDOM.nextInt(availableFonts.length)];
			font = new Font(randomFont, Font.ITALIC, fontSize); //generate random font
			Color random = new Color((int)(Math.random() * 0x1000000)); // generate random colour
			graphics.setColor(random);
			graphics.setFont(font);
			String word = String.valueOf(it.next());
			
			
			
			
			graphics.drawString(word, x, y); //draw string on image


			
			// my best attempt at spiralling each word out from the centre of the page
			if (spiralCount%4 == 0) {
				x = (int)(x+(originalFontSize*1.5));
				y = (int)(y+(originalFontSize*1.5));
			} else if (spiralCount %4 ==1) {
				x = (int)(x+(originalFontSize*1.5));
				y = (int)(y-(originalFontSize*1.5)-fontSize);
				
			} else if (spiralCount %4 ==2) {
				x = (int)(x-(originalFontSize*1.5)-fontSize*2.5);
				y  = (int)(y-(originalFontSize*1.5));
		
			} else {
				x = (int)(x-(originalFontSize*1.5));
				y = (int)(y+(originalFontSize*1.5));
			}
			
			
			
			
			if(x<0) {
				x = x + 600;
			} if(y<0) {
				y = y + 300;
			}
			
			
			count++;
			spiralCount++;
			
			
			

		}



		graphics.dispose();
		try{
			ImageIO.write(image, "png", new File(outputName)); // write to file
		} catch(Exception e) {

		}
	}
}





