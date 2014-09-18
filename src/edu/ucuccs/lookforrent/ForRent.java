package edu.ucuccs.lookforrent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ForRent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_for_rent);
		
		ListView listview = (ListView) findViewById(R.id.list_for_Rent);

		// Defined Array values to show in ListView
				String[] values = new String[] { "Apartments", "Boarding House",
						"Space for Rent", "Lot space" };

				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, values);

				listview.setAdapter(adapter);

		
	}

	
}
