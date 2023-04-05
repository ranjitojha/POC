package com.example.demo.controller;


import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;


/*import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;*/

public class Steganography {

  // embed secret information/TEXT into a "cover image"\
	public static File encode(BufferedImage image, String text) {
		File file = null;
		int bitMask = 0x00000001;	// define the mask bit used to get the digit\
		int bit;				// define a integer number to represent the ASCII number of a character\
		int x = 0;				// define the starting pixel x\
		int y = 0;				// define the starting pixel y\
		for(int i = 0; i < text.length(); i++) {
			System.out.println("charAt  "+i +" : "+text.charAt(i));
			bit = (int) text.charAt(i);		// get the ASCII number of a character\
			for(int j = 0; j < 8; j++) {
				int flag = bit & bitMask;	// get 1 digit from the character\
				if(flag == 1) {	
					if(x < image.getWidth()) {
						image.setRGB(x, y, image.getRGB(x, y) | 0x00000001); 	// store the bit which is 1 into a pixel's last digit\
						x++;
					}
					else {
						x = 0;
						y++;
						image.setRGB(x, y, image.getRGB(x, y) | 0x00000001); 	// store the bit which is 1 into a pixel's last digit\
					}
				} 
				else {	
					if(x < image.getWidth()) {
						image.setRGB(x, y, image.getRGB(x, y) & 0xFFFFFFFE);	// store the bit which is 0 into a pixel's last digit\
						x++;
					}
					else {
						x = 0;
						y++;
						image.setRGB(x, y, image.getRGB(x, y) & 0xFFFFFFFE);	// store the bit which is 0 into a pixel's last digit\
					}
				}
				bit = bit >> 1;				// get the next digit from the character\
			}			
		}
		
		// save the image which contains the secret information to another image file\
		try {
			 file = new File("textEmbedded.JPG");	
			ImageIO.write(image, "png", file);	
		} catch (IOException e) {
			
		}		
		return file;
	}
	
	// extract secret information/Text from a "cover image"\
	public static void decode(BufferedImage image, int length) {
		System.out.print("Extracting: ");
		int bitMask = 0x00000001;	// define the mask bit used to get the digit\
		int x = 0;					// define the starting pixel x\
		int y = 0;					// define the starting pixel y\
		int flag;
		char[] c = new char[length] ;	// define a character array to store the secret information\
		String decodeVal ="";
		for(int i = 0; i < length; i++) {	
			int bit = 0;
			
			// 8 digits form a character\
			for(int j = 0; j < 8; j++) {	
				if(x < image.getWidth()) {
					flag = image.getRGB(x, y) & bitMask;	// get the last digit of the pixel\
					x++;
				}
				else {
					x = 0;
					y++;
					flag = image.getRGB(x, y) & bitMask;	// get the last digit of the pixel\
				}
				
				// store the extracted digits into an integer as a ASCII number\
				if(flag == 1) {					
					bit = bit >> 1;	
					bit = bit | 0x80;
				} 
				else {			
					bit = bit >> 1;
				}	
			}
			c[i] = (char) bit;	// represent the ASCII number by characters\
			System.out.println(c[i]);
			decodeVal = decodeVal+c[i];
			
		}
		System.out.println("decodeVal : "+ decodeVal);
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub\
				
		BufferedImage originalImageText = null;
		BufferedImage coverImageText = null;
		BufferedImage originalImage = null;
		BufferedImage coverImage = null;
		BufferedImage secretImage = null;
		File file = null;
		
		
		// show a user interface to display the original image and the cover image with the secret information included\
		try {
			originalImageText = ImageIO.read(new File("C:\\Users\\Ranjit\\Desktop\\image\\png2jpg\\cover.JPG"));
			coverImageText = ImageIO.read(new File("C:\\Users\\Ranjit\\Desktop\\image\\png2jpg\\cover1.JPG"));
			//originalImage = ImageIO.read(new File("cover2.jpg"));
			//coverImage = ImageIO.read(new File("cover2.jpg"));
			//secretImage = ImageIO.read(new File("secret.jpg"));
			
			String s;
			Scanner scan = new Scanner(System.in);
			System.out.print("Embedding: ");
			s = scan.nextLine();
			file = encode(coverImageText, s);								// embed the secret information\
			decode(ImageIO.read(new File("textEmbedded.JPG")), s.length());		// extract the secret information\
			
		} catch(IOException e) {		
			
		}		
	}
}
