package me.ramuta.inspirista;

import me.ramuta.inspirista.classes.ImageHolder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainImagesFragment extends ListFragment {
	private static final String TAG = "MainImagesFragment";
	public static final String POSITION = "position";
	private ImageLoaderAdapter loaderAdapter;
	private ImageHolder holder = new ImageHolder();
	private ListView list;
	
	/** MainImagesFragment Constructor */
	public MainImagesFragment() {
		super();
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Log.i(TAG, "main fragment, dobi position");
		Bundle args = getArguments();
        int i = args.getInt(POSITION);
        
        Log.i(TAG, "main fragment, nastavi lazy adapter");
        switch (i) {
        case 0:
        	loaderAdapter = new ImageLoaderAdapter(getActivity(), R.layout.main_list_item, holder.getShoesImages());
        	break;
        case 1:
        	loaderAdapter = new ImageLoaderAdapter(getActivity(), R.layout.main_list_item, holder.getBagsImages());
        	break;
        case 2:
        	loaderAdapter = new ImageLoaderAdapter(getActivity(), R.layout.main_list_item, holder.getFashionImages());
        	break;
        }
        
		setListAdapter(loaderAdapter);
		Log.i(TAG, "main fragment, lazy nastavljen!");
	}
}
