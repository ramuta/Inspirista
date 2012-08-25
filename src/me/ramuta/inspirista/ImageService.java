package me.ramuta.inspirista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import me.ramuta.inspirista.MainActivity.ResponseReceiver;
import me.ramuta.inspirista.classes.ImageHolder;
import me.ramuta.inspirista.classes.InstagramData;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ImageService extends IntentService {
	private static final String TAG = "ImageService";
	//private String REQUEST_URL = "https://api.instagram.com/v1/tags/louboutin/media/recent?client_id="+InstagramData.CLIENT_ID;
	private String SHOES_URL = "https://api.instagram.com/v1/tags/louboutin/media/recent?client_id="+InstagramData.CLIENT_ID;
	private String BAGS_URL = "https://api.instagram.com/v1/tags/handbag/media/recent?client_id="+InstagramData.CLIENT_ID;
	private String FASHION_URL = "https://api.instagram.com/v1/tags/hautecouture/media/recent?client_id="+InstagramData.CLIENT_ID;
	public static final String NAILS_OUT = "nailsout";
	public static final String SHOES_OUT = "shoesout";
	public static final String BAGS_OUT = "bagsout";
	public static final String FASHION_OUT = "fashionout";
	private String finalResponse = null;
	private String shoesResponse = null;
    private String bagsResponse = null;
    private String fashionResponse = null;
    private ImageHolder holder = new ImageHolder();

	public ImageService() {
		super("ImageService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(TAG, "Image service started");
		//instagramImages();
		shoesImages();
		bagsImages();
		fashionImages();
		
		Log.i(TAG, "Shoes: "+shoesResponse);
		Log.i(TAG, "Bags: "+bagsResponse);
		Log.i(TAG, "Fashion: "+fashionResponse);

		// pošlji linke prek intenta
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
		broadcastIntent.addCategory(Intent.ACTION_DEFAULT);
		broadcastIntent.putExtra(BAGS_OUT, bagsResponse);
		broadcastIntent.putExtra(SHOES_OUT, shoesResponse);
		broadcastIntent.putExtra(FASHION_OUT, fashionResponse);
		sendBroadcast(broadcastIntent);
	}
	
	/** Request for shoes images */
	public void shoesImages() {		    	
		try {
			Log.i(TAG, "Starting shoes connection");
			URLConnection connection = new URL(SHOES_URL).openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()), 1024 * 16);
			StringBuffer builder = new StringBuffer();
			String line;
			
			while ((line = reader.readLine()) != null) {
			  builder.append(line).append("\n");
			}	
			
			shoesResponse = builder.toString();
			Log.i(TAG, "response: "+shoesResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "IO ex: " + e);
		} 
    	Log.i(TAG, "THE END");
	}
	
	/** Request for bags images */
	public void bagsImages() {		    	
		try {
			Log.i(TAG, "Starting bags connection");
			URLConnection connection = new URL(BAGS_URL).openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()), 1024 * 16);
			StringBuffer builder = new StringBuffer();
			String line;
		
			while ((line = reader.readLine()) != null) {
			  builder.append(line).append("\n");
			}
			
			bagsResponse = builder.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "IO ex: " + e);
		} 

    	Log.i(TAG, "THE END");
	}
	
	/** Request for fashion images */
	public void fashionImages() {		    	
		try {
			Log.i(TAG, "Starting fashion connection");
			URLConnection connection = new URL(FASHION_URL).openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()), 1024 * 16);
			StringBuffer builder = new StringBuffer();
			String line;
		
			while ((line = reader.readLine()) != null) {
			  builder.append(line).append("\n");
			}
			
			fashionResponse = builder.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "IO ex: " + e);
		} 
    	Log.i(TAG, "THE END");
	}
}
