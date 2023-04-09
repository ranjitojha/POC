package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.PushNS;

@RestController
@RequestMapping("/v1")
public class SteganographyController {
	
	@RequestMapping(method = RequestMethod.GET,
			value="/option"
			)
	public ResponseEntity getSecurityPrefs() {
		//logger.info("Get Security Prefs");
		return new ResponseEntity("Get Security Prefs for PUSH Notification",HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value="/generate"
			)
	public ResponseEntity sendOTP() throws IOException {
		
		
		
		//logger.info("Get Security Prefs");
		String SixDigitNumber = getRandomNumberString();
		//File classPathInput = new File(SteganographyController.class.getResource("static\\image\\cover.png").getFile());
		//File resource = new ClassPathResource("cover.JPG").getFile();
		InputStream ip = getClass().getClassLoader().getResourceAsStream("cover.jpg");
		System.out.println(" ip ######### "+ip.toString());
		//File file = new File(getServletContext().getRealPath("/abc.txt"));
       
        if(ip!=null) {
        	 BufferedImage classpathImage = ImageIO.read(ip);
        	 File file = Steganography.encode(classpathImage, SixDigitNumber);
        	//need to fire send push notification using file
        	//logger.info("Six Digit OTP Send To Mobile");
			
			try {
				//PushNS.sendToToken();
				PushNS.sendHttp();
			} catch (Exception e) { 

				//e.getCause();
				e.printStackTrace();
				return new ResponseEntity("File Error  : " + SixDigitNumber, HttpStatus.METHOD_FAILURE);
			}
			
     		
        }
        return new ResponseEntity("Send OTP Six Digit Number : "+SixDigitNumber,HttpStatus.OK);
	
	}
	
	
	@RequestMapping(method = RequestMethod.POST,
			value="/validate"
			)
	public ResponseEntity validateOTP(@RequestParam("file") MultipartFile file) throws IOException {
		
		System.out.println("----------------" + file);
		if (file == null) {
			throw new RuntimeException("You must select the a file for uploading");
		}

		InputStream inputStream = file.getInputStream();
		String originalName = file.getOriginalFilename();
		String name = file.getName();
		System.out.println("Name" + name);
		String contentType = file.getContentType();
		long size = file.getSize();
		System.out.println(" size : "+size);
		
		
		byte[] buffer = new byte[inputStream.available()];
		inputStream.read(buffer);
		File targetFile = new File("cover.jpg");
		OutputStream outStream = new FileOutputStream(targetFile);
		outStream.write(buffer);
		
		BufferedImage classpathImage = ImageIO.read(targetFile);
		Steganography.decode(classpathImage, 6);
		
		return new ResponseEntity("Successfully OTP validated",HttpStatus.OK);
		
	}

	public static String getRandomNumberString() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}

	
}
