package edu.ucuccs.lookforrent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class AddBuildings extends Activity {

	GPSTracker gps;
	double latitude, longtitude;
	List<Address> addresses;

	EditText mNameEditText, mPriceEditText, mAddressEditText, mContactEditText;
	Button mSaveButton;

	String mAddress;
	String mname;
	String mPrice;
	String mContact;
	ImageView img;
	EditText txtTitle, txtDescription;

	Bitmap bitmap;

	private ProgressDialog pd;

	// flag for Internet connection status
	Boolean isInternetPresent = false;
	// Connection detector class
	ConnectionDetector cd;

	final String APPLICATION_ID = "5gEy3nUyaJDyjdRGkhMJW7Iysebr11M94JEympCd";
	final String CLIENT_KEY = "wsvtwN7dNSEZyqVXIjXpcscoqiIFEwcR5wYVTERa";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_buildings);
		// to initialize the parse
		Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

		// creating connection detector class instance
		cd = new ConnectionDetector(getApplicationContext());

		gps = new GPSTracker(AddBuildings.this);

		mNameEditText = (EditText) findViewById(R.id.edit_Name);
		mPriceEditText = (EditText) findViewById(R.id.edit_Price);
		mAddressEditText = (EditText) findViewById(R.id.edit_Address);
		mContactEditText = (EditText) findViewById(R.id.edit_Contact);

		img = (ImageView) findViewById(R.id.image_Picture);

		mSaveButton = (Button) findViewById(R.id.btn_Save);

		mSaveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				createAccount();
				// Save to parse..

				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				// get byte array here
				byte[] bytearray = stream.toByteArray();

				if (gps.canGetLocation()) {
					latitude = gps.getLatitude();
					longtitude = gps.getLongitude();
				} else {
					gps.showSettingsAlert();
				}

				ParseObject admin = new ParseObject("Buildings");
				ParseGeoPoint location = new ParseGeoPoint();
				location.setLatitude(latitude);
				location.setLongitude(longtitude);

				admin.put("name", mNameEditText.getText().toString());
				admin.put("price", mPriceEditText.getText().toString());
				admin.put("points", location);
				admin.put("contact", mContactEditText.getText().toString());
				// address
				admin.put("address",
						convertpointToLocation(latitude, longtitude));

				if (bytearray != null) {
					ParseFile file = new ParseFile(mNameEditText.getText()
							.toString().toLowerCase()
							+ ".png", bytearray);
					file.saveInBackground();
					admin.put("image", file);
				}

				admin.saveInBackground();
			}
		});

	}

	// save sa imageview
	@Override
	public void onStart() {
		super.onStart();
		img = (ImageView) findViewById(R.id.image_Picture);
		bitmap = (Bitmap) getIntent().getParcelableExtra("Image");
		img.setImageBitmap(bitmap);
	}

	private void createAccount() {
		/* clearErrors(); */

		boolean cancel = false;
		View focusView = null;

		// Store values at the time of the login attempt.
		mPrice = mPriceEditText.getText().toString();
		mname = mNameEditText.getText().toString();
		mAddress = mAddressEditText.getText().toString();
		mContact = mContactEditText.getText().toString();

		// Check for a valid Username.
		if (TextUtils.isEmpty(mname)) {
			mNameEditText.setError(getString(R.string.error_field_required));
			focusView = mNameEditText;
			cancel = true;
		}

		// Check for a valid confirm password.
		if (TextUtils.isEmpty(mPrice)) {
			mPriceEditText.setError(getString(R.string.error_field_required));
			focusView = mPriceEditText;
			cancel = true;
		}

		// Check for a valid password.
		if (TextUtils.isEmpty(mAddress)) {
			mAddressEditText.setError(getString(R.string.error_field_required));
			focusView = mAddressEditText;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mContact)) {
			mContactEditText.setError(getString(R.string.error_field_required));
			focusView = mContactEditText;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.

		}

	}

	// convertToLatLng
	public String convertpointToLocation(double lat, double lng) {

		String address = "";
		Geocoder geo = new Geocoder(this, Locale.getDefault());
		try {
			List<Address> addresses = geo.getFromLocation(lat, lng, 1);
			if (addresses.size() > 0) {
				for (int index = 0; index < addresses.get(0)
						.getMaxAddressLineIndex(); index++)
					address += addresses.get(0).getAddressLine(index) + ", ";
			}

		} catch (IOException e) {
			e.printStackTrace();

		}
		return address;

	}

	// address
	public String getPlaceByLatLng(double lat, double longi) {
		Geocoder geocoder = new Geocoder(AddBuildings.this, Locale.getDefault());
		String addressString = null;
		try {
			addresses = geocoder.getFromLocation(lat, longi, 1);
			StringBuilder place_name = new StringBuilder();
			if (addresses.size() < 0) {
				Address address = addresses.get(0);
				for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
					place_name.append(address.getAddressLine(i)).append("\n");
				place_name.append(address.getLocality()).append("\n");
				place_name.append(address.getPostalCode()).append("\n");
				place_name.append(address.getCountryName());

			}
			addressString = place_name.toString();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), e + " ", Toast.LENGTH_LONG)
					.show();

		}
		return addressString;

	}

	protected void onPreExecute() {
		pd.hide();
		showDone();

	}

	public void showDone() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Success");
		alert.setMessage("Report successfully save.");
		alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
				dialog.cancel();
			}

		});

		alert.show();

	}

	public void clickCancel(View v) {
		finish();
	}

}
