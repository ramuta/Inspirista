package me.ramuta.inspirista;

import java.io.File;
import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import me.ramuta.inspirista.object.Image;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageLoaderAdapter extends BaseAdapter {
	private Activity activity;
    private ArrayList<Image> images = new ArrayList<Image>();
    private static LayoutInflater inflater=null;
    private static final String TAG = "ImageLoaderAdapter";
    private int ITEM_INFLATER;
    //private ProgressBar spinner;
    private File cacheDir;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
	
	public ImageLoaderAdapter(Activity a, int ITEM_INFLATER, ArrayList<Image> images) {
		this.activity = a;
		this.ITEM_INFLATER = ITEM_INFLATER;
		this.images = images;
		this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		cacheDir = StorageUtils.getOwnCacheDirectory(a.getApplicationContext(), "UniversalImageLoader/Cache");
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_launcher)
		.cacheInMemory()
		.cacheOnDisc()
		.build();
		imageLoader.init(ImageLoaderConfiguration.createDefault(a.getApplicationContext()));
	}

	public int getCount() {
		return images.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView (final int position, View convertView, ViewGroup parent) {    		    	
    	View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(ITEM_INFLATER, null); // R.layout.item
        final ImageView image=(ImageView)vi.findViewById(R.id.main_list_image); // R.id.image
        //spinner = (ProgressBar)vi.findViewById(R.id.main_list_spinner);
        //spinner.setVisibility(View.INVISIBLE);
        
        try {	
			imageLoader.displayImage(images.get(position).getLinkStandRes(), image, options, new ImageLoadingListener() {

				public void onLoadingStarted() {
					//spinner.setVisibility(View.VISIBLE);
				}
				
				public void onLoadingCancelled() {
					//spinner.setVisibility(View.GONE);
				}

				public void onLoadingComplete(Bitmap arg0) {
					//spinner.setVisibility(View.GONE);
				}

				public void onLoadingFailed(FailReason arg0) {
					//spinner.setVisibility(View.GONE);
				}
			});
		} catch (Exception e) {
			image.setImageResource(R.drawable.ic_launcher);
		} 
        
        return vi;
    }
}
