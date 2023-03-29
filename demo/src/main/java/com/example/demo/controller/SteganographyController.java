package com.example.demo.controller;

import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class SteganographyController {
	
	@RequestMapping(method = RequestMethod.GET,
			value="/option/{wID}"
			)
	public ResponseEntity getSecurityPrefs(@PathVariable("wID") String wID) {
		//logger.info("Get Security Prefs");
		return new ResponseEntity("Get Security Prefs for PUSH Notification",HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.POST,
			value="/generate/{wID}"
			)
	public ResponseEntity sendOTP(@PathVariable("wID") String wID) {
		//logger.info("Get Security Prefs");
		String SixDigitNumber = getRandomNumberString();
		//logger.info("Six Digit OTP Send To Mobile");
		return new ResponseEntity("Send OTP Six Digit Number : "+SixDigitNumber,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST,
			value="/validate/{wID}"
			)
	public ResponseEntity validateOTP(@PathVariable("wID") String wID) {
		//logger.info("validate OTP");
		return new ResponseEntity("Successfully OTP validated",HttpStatus.OK);
		
	}

	public static String getRandomNumberString() {
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}

}
