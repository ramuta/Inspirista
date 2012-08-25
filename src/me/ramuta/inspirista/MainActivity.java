package me.ramuta.inspirista;

/*
 * INSPIRISTA FASHION APP
 * Author: Matej Ramuta
 * 
 * Images from Instagram (through Instagram API).
 * Source code available on BitBucket. */

import me.ramuta.inspirista.classes.ImageHolder;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {
	private static final String TAG = "MainActivity";
	//private static final String RESPONSE_DATA = "responsedata";
	private static final String SHOES_DATA = "shoesdata";
	private static final String BAGS_DATA = "bagsdata";
	private static final String FASHION_DATA = "fashiondata";
    MainFragmentAdapter mainFragmentAdapter;
    ViewPager mViewPager;
    ImageHolder holder = new ImageHolder();
    String finalResponse;
    private String shoesResponse;
    private String bagsResponse;
    private String fashionResponse;
    ResponseReceiver receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = new Intent(MainActivity.this, ImageService.class);
        startService(intent);
        
     // instanciiraj in registriraj response receiver
  		IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
  		filter.addCategory(Intent.ACTION_DEFAULT);
  		receiver = new ResponseReceiver();
  		registerReceiver(receiver, filter);
        
        SharedPreferences sPref = getSharedPreferences("MainActivity", Activity.MODE_PRIVATE);
		shoesResponse = sPref.getString(SHOES_DATA, "");
		bagsResponse = sPref.getString(BAGS_DATA, "");
		fashionResponse = sPref.getString(FASHION_DATA, "");

        if (!shoesResponse.equals(null)) {			
        	setFragments();
        	Log.i(TAG, "Ni prazen: "+shoesResponse);
		}             
    }
    
    /** Response receiver for ImageService. */
    public class ResponseReceiver extends BroadcastReceiver {
    	public static final String ACTION_RESP = "test.ramuta.intent.action.MESSAGE_PROCESSED";
    	    	
		@Override
		public void onReceive(Context context, Intent intent) {
			//finalResponse = intent.getStringExtra(ImageService.NAILS_OUT);	
			try {
				Log.i(TAG, "receiver, spremeni stringe");
				bagsResponse = intent.getStringExtra(ImageService.BAGS_OUT);
				shoesResponse = intent.getStringExtra(ImageService.SHOES_OUT);
				fashionResponse = intent.getStringExtra(ImageService.FASHION_OUT);
				Log.i(TAG, "receiver, shrani response");
				saveResponse();
				Log.i(TAG, "receiver, nastavi fragmente");
				setFragments();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    private void setFragments() {
    	Log.i(TAG, "fragmenti, konvertiraj stringe");
    	holder.getShoesImages().clear();
    	holder.getBagsImages().clear();
    	holder.getFashionImages().clear();
    	holder.convertResponse(bagsResponse, "bags");
    	holder.convertResponse(shoesResponse, "shoes");    	
    	holder.convertResponse(fashionResponse, "fashion");
    	
    	Log.i(TAG, "fragmenti, nastavi adapter");
		mainFragmentAdapter = new MainFragmentAdapter(MainActivity.this.getSupportFragmentManager(), MainActivity.this);

		Log.i(TAG, "fragmenti, nastavi viewpager");
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mainFragmentAdapter);
    }
    
    private void saveResponse() {
    	// shrani trenutno stanje
    	SharedPreferences uiState = getSharedPreferences("MainActivity", 0);
    	SharedPreferences.Editor editor = uiState.edit();
    	editor.putString(SHOES_DATA, shoesResponse);
    	editor.putString(BAGS_DATA, bagsResponse);
    	editor.putString(FASHION_DATA, fashionResponse);
    	editor.commit(); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.menu_about:
    		// code
    		Log.i(TAG, "ABOUT klik");
    		Intent intent = new Intent(MainActivity.this, AboutActivity.class);
    		startActivity(intent);
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	//this.unregisterReceiver(receiver);
    	saveResponse();
    }
    
    @Override
    public void onDestroy() {
    	this.unregisterReceiver(receiver);
    	super.onDestroy();
    }
}
