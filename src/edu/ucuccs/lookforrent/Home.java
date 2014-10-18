package edu.ucuccs.lookforrent;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Home extends Activity {

	private static final int PICK_IMAGE = 1;
	private static final int TAKE_PICTURE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.adding_building:

			startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),
					TAKE_PICTURE);

			break;

		}
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == TAKE_PICTURE) {
				Bitmap photo = (Bitmap) data.getExtras().get("data");
				Intent i = new Intent(getApplicationContext(),
						AddBuildings.class);
				i.putExtra("Image", photo);
				startActivity(i);
			} else if (requestCode == PICK_IMAGE) {
				Uri selectedImage = data.getData();
				String[] filePath = { MediaStore.Images.Media.DATA };
				Cursor c = getApplicationContext().getContentResolver().query(
						selectedImage, filePath, null, null, null);
				c.moveToFirst();
				int columnIndex = c.getColumnIndex(filePath[0]);
				String picturePath = c.getString(columnIndex);
				Bitmap photo2 = (BitmapFactory.decodeFile(picturePath));
				Intent v = new Intent(getApplicationContext(),
						AddBuildings.class);
				v.putExtra("Image", photo2);
				startActivity(v);
				// c.close();
			}

		}
		super.onActivityResult(requestCode, resultCode, data);

	}

}
