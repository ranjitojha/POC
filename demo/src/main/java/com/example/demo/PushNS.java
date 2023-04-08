package com.example.demo;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.SendResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Firebase Cloud Messaging (FCM) snippets for documentation.
 */
public class PushNS {

    public static void sendToToken() throws FirebaseMessagingException {
        // [START send_to_token]
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "ehOb6e3mRoiutk9bvSypbm:APA91bFPDsOFdaxzeSDoa02M3nZG6vxj9y42kF3FFbftV_zyDFntzDr8eerg8v-zM0LuJEqoiQw11UhcDQhMStZQvu6UxzI_qIz2ZEjnY5DhtLLV7JodvBnWYO-lulk9lfS-W8peQHGp";

        // See documentation on defining a message payload.
        Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(registrationToken)
                .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        String response = FirebaseMessaging.getInstance().send(message);
        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);
        // [END send_to_token]
    }



    public static void sendDryRun() throws FirebaseMessagingException {
        Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken("token")
                .build();

        // [START send_dry_run]
        // Send a message in the dry run mode.
        boolean dryRun = true;
        String response = FirebaseMessaging.getInstance().send(message, dryRun);
        // Response is a message ID string.
        System.out.println("Dry run successful: " + response);
        // [END send_dry_run]
    }



    public Message androidMessage() {
        // [START android_message]
        Message message = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(3600 * 1000) // 1 hour in milliseconds
                        .setPriority(AndroidConfig.Priority.NORMAL)
                        .setNotification(AndroidNotification.builder()
                                .setTitle("$GOOG up 1.43% on the day")
                                .setBody("$GOOG gained 11.80 points to close at 835.67, up 1.43% on the day.")
                                .setIcon("stock_ticker_update")
                                .setColor("#f45342")
                                .build())
                        .build())
                .setTopic("industry-tech")
                .build();
        // [END android_message]
        return message;
    }

	/*
	 * public static void main(String[] args) throws FirebaseMessagingException {
	 * //sendToToken(); sendDryRun(); }
	 */
    
    

}
