package me.ramuta.inspirista.classes;

import java.util.ArrayList;
import me.ramuta.inspirista.object.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ImageHolder {
	private static final String TAG = "ImageHolder";
	private static ArrayList<Image> streamImages = new ArrayList<Image>();
	private static ArrayList<Image> streamShoes = new ArrayList<Image>();
	private static ArrayList<Image> streamBags = new ArrayList<Image>();
	private static ArrayList<Image> streamFashion = new ArrayList<Image>();
	private static String response;
	private String shoesResponse;
    private String bagsResponse;
    private String fashionResponse;

	public ImageHolder() {
		super();
	}
	
	/** TEST STREAM MAKER 
	public void setTestStream() {
		streamImages.add(new Image("1111", "a", "http://1.bp.blogspot.com/-l0nYvRvRw8U/T_6PM6gwgbI/AAAAAAAAA5Q/8S20m48Ia2o/s1600/alexander_mcqueen_shoes.jpg"));
		streamImages.add(new Image("2222", "a", "http://www.vendio.com/stores/.template/fashionparade/current/mycontent/Alexander-McQueen-shoes-black-white.gif"));
		streamImages.add(new Image("3333", "a", "http://do-while.com/img/life/weird-shoes/weird-shoes02.jpg"));
	}
	*/

	/**
	 * @return the streamImages
	 */
	public ArrayList<Image> getStreamImages() {
		return streamImages;
	}

	/**
	 * @param streamImages the streamImages to set
	 */
	public void setStreamImages(ArrayList<Image> streamImages) {
		this.streamImages = streamImages;
	}
	
	/**
	 * @return the streamShoes
	 */
	public ArrayList<Image> getShoesImages() {
		return streamShoes;
	}

	/**
	 * @param streamShoes the streamShoes to set
	 */
	public void setShoesImages(ArrayList<Image> streamShoes) {
		this.streamShoes = streamShoes;
	}
	
	/**
	 * @return the streamBags
	 */
	public ArrayList<Image> getBagsImages() {
		return streamBags;
	}

	/**
	 * @param streamBags the streamBags to set
	 */
	public void setBagsImages(ArrayList<Image> streamBags) {
		this.streamBags = streamBags;
	}
	
	/**
	 * @return the streamFashion
	 */
	public ArrayList<Image> getFashionImages() {
		return streamFashion;
	}

	/**
	 * @param streamFashion the streamFashion to set
	 */
	public void setFashionImages(ArrayList<Image> streamFashion) {
		this.streamFashion = streamFashion;
	}
	
	/** Convert response from Instagram. */
	public void convertResponse(String response, String type) {
		//streamImages.clear();
		//streamShoes.clear();
		//streamBags.clear();
		//streamFashion.clear();
		this.response = response;

		try {
			JSONObject object = new JSONObject(response);
			JSONArray jArray = object.getJSONArray("data");
			//Log.i(TAG, "jArray: "+jArray);
			//object.toJSONArray(jArray);
			JSONObject jObject = null;
			for (int x = 0; x < jArray.length(); x++) {
				jObject = jArray.getJSONObject(x);
				//Log.i(TAG, "jObject in for loop: "+jObject);
				String ID = jObject.getString("id");
				//JSONArray images = new JSONArray(jObject.getString("images"));
				JSONObject images = new JSONObject(jObject.getString("images"));
				//Log.i(TAG, "images: "+images);
				JSONObject lowRes = new JSONObject(images.getString("low_resolution"));
				String linkLowRes = lowRes.getString("url");
				//Log.i(TAG, "linkLowRes: "+linkLowRes);
				JSONObject standRes = new JSONObject(images.getString("standard_resolution"));
				String linkStandRes = standRes.getString("url");
				
				JSONObject thumbRes = new JSONObject(images.getString("thumbnail"));
				String linkThumbRes = thumbRes.getString("url");
				
				if (type.equals("shoes")) {
					streamShoes.add(new Image(ID, linkLowRes, linkStandRes, linkThumbRes));
				}
				else if (type.equals("bags")) {
					streamBags.add(new Image(ID, linkLowRes, linkStandRes, linkThumbRes));
				}
				else if (type.equals("fashion")) {
					streamFashion.add(new Image(ID, linkLowRes, linkStandRes, linkThumbRes));
				}
				
			}
		} catch (JSONException e) {
			Log.e(TAG, "JSON ex2: "+e);
		}
	}
	
	public String getResponse() {
		return response;
	}
	
	public String getShoesResponse() {
		return shoesResponse;
	}
	
	public String getBagsResponse() {
		return bagsResponse;
	}
	
	public String getFashionResponse() {
		return fashionResponse;
	}
}
