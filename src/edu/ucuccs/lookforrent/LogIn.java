package edu.ucuccs.lookforrent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class LogIn extends Activity {

	Button btn_signUp;
	Button btn_logIn;
	EditText edit_userN;
	EditText edit_pass;

	ParseObject parse_Object;
	ParseUser user;
	final String APPLICATION_ID = "5gEy3nUyaJDyjdRGkhMJW7Iysebr11M94JEympCd";
	final String CLIENT_KEY = "wsvtwN7dNSEZyqVXIjXpcscoqiIFEwcR5wYVTERa";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);

		btn_signUp = (Button) findViewById(R.id.btn_signUp);
		btn_logIn = (Button) findViewById(R.id.btn_logIn);
		edit_userN = (EditText) findViewById(R.id.edit_username);
		edit_pass = (EditText) findViewById(R.id.edit_pass);

		Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

	}

	public void click_signUp(View v) {
		Intent uni1 = new Intent(LogIn.this, SignUp.class);
		startActivity(uni1);

	}

	public void click_logIn(View v) {

		/*
		 * parse_Object.logInInBackground("username", "password", new
		 * LogInCallback() { public void done(ParseUser user, ParseException e)
		 * { if (user != null) { // Hooray! The user is logged in. } else { //
		 * Signup failed. Look at the ParseException to see what happened. } }
		 * });;
		 */

		/*
		 * ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LogIn");
		 * query.whereEqualTo("username", edit_userN.getText().toString());
		 * query.whereEqualTo("password", edit_pass.getText().toString());
		 * 
		 * query.findInBackground(new FindCallback<ParseObject>() { public void
		 * done(List<ParseObject> arg0, ParseException e) { if (e == null) {
		 * AlertDialog.Builder builder = new AlertDialog.Builder( LogIn.this);
		 * builder.setMessage("LogIn Failed")
		 * .setTitle("Invalid Username and Password")
		 * .setPositiveButton(android.R.string.ok, null); AlertDialog dialog =
		 * builder.create(); dialog.show();
		 * 
		 * } else { // Signup failed. Look at the ParseException to see what //
		 * happened.
		 * 
		 * Intent uni = new Intent(LogIn.this, MainActivity.class);
		 * startActivity(uni);
		 * 
		 * } } });
		 */

		// Retrieve the text entered from the EditText
		String usernametxt = edit_userN.getText().toString();
		String passwordtxt = edit_pass.getText().toString();

		// Send data to Parse.com for verification
		ParseUser.logInInBackground(usernametxt, passwordtxt,
				new LogInCallback() {
					public void done(ParseUser user, ParseException e) {
						if (user != null) {
							// If user exist and authenticated, send
							// user to Welcome.class

							AlertDialog.Builder builder = new AlertDialog.Builder(
									LogIn.this);
							builder.setMessage("Successfully LogIn")
									.setTitle("Successful")
									.setPositiveButton(
											android.R.string.ok,
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int which) {

													Intent intent = new Intent(
															LogIn.this,
															MainActivity.class);
													startActivity(intent);

												}
											});
							AlertDialog dialog = builder.create();
							dialog.show();

						} else {

							AlertDialog.Builder builder = new AlertDialog.Builder(
									LogIn.this);
							builder.setMessage("Invalid Username and Password")
									.setTitle("LogIn Failed")
									.setPositiveButton(android.R.string.ok,
											null);
							AlertDialog dialog = builder.create();
							dialog.show();

						}
					}

				});
	}
}
