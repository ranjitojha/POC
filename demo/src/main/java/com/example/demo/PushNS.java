package com.example.demo;

import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.demo.model.NotificationData;
import com.example.demo.model.NotificationRequestModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Firebase Cloud Messaging (FCM) snippets for documentation.
 */
public class PushNS {
	/*
	 * @Autowired static FirebaseMessaging firebaseMessaging;
	 */



	/*static FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource("json/google-services.json").getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions.builder().setCredentials(googleCredentials).build();

		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions);

		return FirebaseMessaging.getInstance(app);
	}

	public static void sendToToken() throws FirebaseMessagingException {
		// [START send_to_token]
		// This registration token comes from the client FCM SDKs.
		String registrationToken = "ehOb6e3mRoiutk9bvSypbm:APA91bFPDsOFdaxzeSDoa02M3nZG6vxj9y42kF3FFbftV_zyDFntzDr8eerg8v-zM0LuJEqoiQw11UhcDQhMStZQvu6UxzI_qIz2ZEjnY5DhtLLV7JodvBnWYO-lulk9lfS-W8peQHGp";

		// See documentation on defining a message payload.
		Message message = Message.builder().putData("score", "850").putData("time", "2:45").setToken(registrationToken)
				.build();

		// Send a message to the device corresponding to the provided
		// registration token.
		if (firebaseMessaging == null) {
			try {
				firebaseMessaging = firebaseMessaging();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String response = firebaseMessaging.send(message);
		// Response is a message ID string.
		System.out.println("Successfully sent message: " + response);
		// [END send_to_token]
	}
*/
	public static void sendHttp() throws ParseException, IOException {
		 DefaultHttpClient httpClient = new DefaultHttpClient();
		 HttpPost postRequest = new HttpPost("https://fcm.googleapis.com/fcm/send");
		
		NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
		NotificationData notificationData = new NotificationData();

		notificationData.setDetail("this is firebase push notification from java client (server)");
		notificationData.setTitle("Hello Firebase Push Notification");
		notificationRequestModel.setData(notificationData);
		notificationRequestModel.setTo(
				"ehOb6e3mRoiutk9bvSypbm:APA91bFPDsOFdaxzeSDoa02M3nZG6vxj9y42kF3FFbftV_zyDFntzDr8eerg8v-zM0LuJEqoiQw11UhcDQhMStZQvu6UxzI_qIz2ZEjnY5DhtLLV7JodvBnWYO-lulk9lfS-W8peQHGp");

		Gson gson = new Gson();
		Type type = new TypeToken<NotificationRequestModel>() {
		}.getType();

		String json = gson.toJson(notificationRequestModel, type);

		StringEntity input = new StringEntity(json);
		input.setContentType("application/json");
		postRequest.addHeader("Authorization",
				"key=AAAAVt5oz6k:APA91bFohV48tiFaAkekw-rQdZoGziIVZNleE_mX3j3v-WaXuGQugqEbmGgdc4rchgGg-vNrVB43IrqbSQmQiy-dOcLEn3-csN8X6Y8owgTp4i-3pfZnXrRwLmW8S1IDVWIeRYsoVO0B");
		postRequest.setEntity(input);

		System.out.println("reques:" + json);

		HttpResponse response = null;
		try {
			response = httpClient.execute(postRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			} else if (response.getStatusLine().getStatusCode() == 200) {

				System.out.println("response:" + EntityUtils.toString(response.getEntity()));

			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	/*public static void sendDryRun() throws FirebaseMessagingException {
		Message message = Message.builder().putData("score", "850").putData("time", "2:45").setToken("token").build();

		// [START send_dry_run]
		// Send a message in the dry run mode.
		boolean dryRun = true;
		String response = firebaseMessaging.send(message, dryRun);
		// Response is a message ID string.
		System.out.println("Dry run successful: " + response);
		// [END send_dry_run]
	}

	public Message androidMessage() {
		// [START android_message]
		Message message = Message.builder().setAndroidConfig(AndroidConfig.builder().setTtl(3600 * 1000) // 1 hour in
																											// milliseconds
				.setPriority(AndroidConfig.Priority.NORMAL)
				.setNotification(AndroidNotification.builder().setTitle("$GOOG up 1.43% on the day")
						.setBody("$GOOG gained 11.80 points to close at 835.67, up 1.43% on the day.")
						.setIcon("stock_ticker_update").setColor("#f45342").build())
				.build()).setTopic("industry-tech").build();
		// [END android_message]
		return message;
	}*/

	/*
	 * public static void main(String[] args) throws FirebaseMessagingException {
	 * //sendToToken(); sendDryRun(); }
	 */

}
