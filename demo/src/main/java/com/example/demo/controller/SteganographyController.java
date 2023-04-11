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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.PushNS;
import org.apache.commons.io.IOUtils;

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
	
	@SuppressWarnings("unchecked")
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
        				
			try {
				//PushNS.sendToToken();
				PushNS.sendHttp(file.getName());
			} catch (Exception e) { 

				//e.getCause();
				e.printStackTrace();
				return new ResponseEntity("File Error  : ", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
     		
        }
        return new ResponseEntity("Send OTP Six Digit Number : "+SixDigitNumber,HttpStatus.OK);
	
	}
	
	
	@RequestMapping(method = RequestMethod.POST,
			value="/validate"
			)
	//public ResponseEntity validateOTP(@RequestParam("otp") String otp) throws IOException {
	public ResponseEntity validateOTP(@RequestParam("otp") String otp) throws IOException {
		System.out.println("----------------" + otp);
		

		/*InputStream inputStream = file.getInputStream();
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
		
		BufferedImage classpathImage = ImageIO.read(targetFile);*/
		//Steganography.decode(classpathImage, 6);
		
		return new ResponseEntity("Successfully OTP validated",HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value="/image",
			produces = MediaType.IMAGE_JPEG_VALUE
			)
	public @ResponseBody byte[] getImage(@RequestParam("url") String url) throws IOException {
		
		/*InputStream in = getClass()
			      .getResourceAsStream("/com/baeldung/produceimage/image.jpg");
			    return IOUtils.toByteArray(in);*/
		
		System.out.println("----------------" + url);
		
		File file = new File(System.getProperty("java.io.tmpdir")+"/"+url);
		FileInputStream fis = new FileInputStream(file);
		return IOUtils.toByteArray(fis);
	   /* if(file.exists()) {
	        String contentType = "application/octet-stream";
	        response.setContentType(contentType);
	        OutputStream out = response.getOutputStream();
	        FileInputStream in = new FileInputStream(file);
	        // copy from in to out
	        IOUtils.copy(in, out);
	        out.close();
	        in.close();
	    }else {
	        throw new FileNotFoundException();
	    }*/

		
		
		
		
	}

	public static String getRandomNumberString() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}

	
}
