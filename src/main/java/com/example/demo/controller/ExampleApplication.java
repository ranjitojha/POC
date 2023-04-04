package com.example.demo.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ExampleApplication {
    private File getIcon() throws IOException, URISyntaxException {
    	
    	
    	ExampleApplication instance = new ExampleApplication();
    	String SixDigitNumber = getRandomNumberString();
        
        File file = instance.getFile("cover.png");
       
        //File file = new File(new BufferedImage(imgList[0-300]));
        
        
        //Steganography.encode(file, SixDigitNumber);
         
        //validate file path
        System.out.println(file.getPath());
     
        //Read file
		/*
		 * List<String> lines = Files.readAllLines(file.toPath());
		 * 
		 * System.out.println(lines);
		 */
    	
		
		/*
		 * File classPathInput = new
		 * File(ExampleApplication.class.getResource("cover.png").getFile());
		 * BufferedImage classpathImage = ImageIO.read(classPathInput);
		 */
       // ReadFileFromClasspath instance = new ReadFileFromClasspath();
        
        //File file = instance.getFile("demo.txt");
         
        //validate file path
        //System.out.println(classPathInput.getPath());
    	
    	File resource = new ClassPathResource(
    		      "cover.png").getFile();
        System.out.println(" resource : "+ resource.getPath());
        BufferedImage classpathImage = ImageIO.read(resource);
		
		  File folderInput = new File("cover.png");
		  //BufferedImage folderImage = ImageIO.read(folderInput);
		  System.out.println(" resource : "+folderInput.getPath());
		 

        System.out.println(" folderImage : "+folderInput);
		return folderInput;
    }
    private File getFile(String fileName) throws IOException
    {
      ClassLoader classLoader = getClass().getClassLoader();
          URL resource = classLoader.getResource(fileName);
           
          if (resource == null) {
              throw new IllegalArgumentException("file is not found!");
          } else {
              return new File(resource.getFile());
          }
    }
    public static String getRandomNumberString() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}
    
    public static void main(String[] args) throws IOException, URISyntaxException {
    	ExampleApplication exampleApplication = new ExampleApplication();
    	exampleApplication.getIcon();
    }
}
