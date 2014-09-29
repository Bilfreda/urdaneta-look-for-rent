package edu.ucuccs.lookforrent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView img_Create_Account;
	ImageView img_forRent;
	ImageView img_forSale;
	ImageView img_Location;
	ImageView img_LogIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		img_forRent = (ImageView) findViewById(R.id.imageView2);
		img_forSale = (ImageView) findViewById(R.id.imageView3);
		img_Location = (ImageView) findViewById(R.id.imageView4);

	}

	public void img_rent(View v) {

		Intent uni = new Intent(MainActivity.this, ForRent.class);
		startActivity(uni);

	}

	public void img_sale(View v) {

		Intent uni = new Intent(MainActivity.this, ForSale.class);
		startActivity(uni);

	}

	public void img_location(View v) {

		Intent uni = new Intent(MainActivity.this, Location.class);
		startActivity(uni);

	}

	/*
	 * 
	 * public void img_create_account(View v) {
	 * 
	 * Intent uni = new Intent(MainActivity.this, SignUp.class);
	 * startActivity(uni);
	 * 
	 * }
	 * 
	 * public void img_login(View v) {
	 * 
	 * Intent uni = new Intent(MainActivity.this, LogIn.class);
	 * startActivity(uni);
	 * 
	 * }
	 */
}
