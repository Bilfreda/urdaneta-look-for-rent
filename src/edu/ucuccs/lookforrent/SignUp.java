package edu.ucuccs.lookforrent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends Activity {

	EditText editText_name, editText_pass, edit_name;
	Button btn_finished;
	ParseObject parse_obj;
	final String APPLICATION_ID = "5gEy3nUyaJDyjdRGkhMJW7Iysebr11M94JEympCd";
	final String CLIENT_KEY = "wsvtwN7dNSEZyqVXIjXpcscoqiIFEwcR5wYVTERa";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		editText_name = (EditText) findViewById(R.id.editText_username);
		editText_pass = (EditText) findViewById(R.id.editText_password);
		btn_finished = (Button) findViewById(R.id.button1);

		Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

	}

	public void btn_finished(View v) {

		// Retrieve the text entered from the EditText
		String usernametxt = editText_name.getText().toString();
		String passwordtxt = editText_pass.getText().toString();

		if ((usernametxt.equals("") && passwordtxt.equals(""))) {

			AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
			builder.setMessage("Failed to sign up")
					.setTitle("Please fill up all")
					.setPositiveButton(android.R.string.ok, null);
			AlertDialog dialog = builder.create();
			dialog.show();

		} else {

			ParseUser user = new ParseUser();

			user.setUsername(usernametxt);
			user.setPassword(passwordtxt);
			user.saveInBackground();

			user.signUpInBackground(new SignUpCallback() {
				public void done(ParseException e) {
					if (e == null) {
						// Show a simple Toast message upon successful
						// registration

						AlertDialog.Builder builder = new AlertDialog.Builder(
								SignUp.this);
						builder.setMessage("Succesfully saved")
								.setTitle("Succesful")
								.setPositiveButton(android.R.string.ok,
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int which) {

												Intent intent = new Intent(
														SignUp.this,
														LogIn.class);
												startActivity(intent);
											}

										});
						AlertDialog dialog = builder.create();
						dialog.show();

					}
				}
			});

		}

	}
}
