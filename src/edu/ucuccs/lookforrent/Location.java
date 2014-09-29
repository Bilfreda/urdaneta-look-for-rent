package edu.ucuccs.lookforrent;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Location extends Activity {

	private GoogleMap GMap;
	private static final LatLng URDANETA = new LatLng(15.9751895, 120.5703162);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);

		// Getting reference to the SupportMapFragment of activity_main.xml
		MapFragment G_Map = (MapFragment) getFragmentManager()
				.findFragmentById(R.id.map);

		// Getting GoogleMap object from the fragment
		GMap = G_Map.getMap();
		
		GMap.moveCamera(CameraUpdateFactory.newLatLng(URDANETA));
		GMap.animateCamera(CameraUpdateFactory.zoomIn());
		GMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


	}

}
