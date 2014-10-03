package edu.ucuccs.lookforrent;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ForgetParsePassword extends Activity {

	EditText et_forgetpassword = null;
	Button btn_submitforgetpassword = null;
	String password = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_parse_password);
		
		et_forgetpassword = (EditText) findViewById(R.id.et_forgetpassword);
		btn_submitforgetpassword = (Button) findViewById(R.id.btn_submitforgetpassword);
		
		btn_submitforgetpassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				password = et_forgetpassword.getText().toString();
				checkEmailID();
				
				
			}
		});
		
	}
	
	protected void checkEmailID() {
		// TODO Auto-generated method stub
		if (TextUtils.isEmpty(password)) {
			et_forgetpassword.setError(getString(R.string.error_field_required));
		} else if (!password.contains("@")) {
			et_forgetpassword.setError(getString(R.string.error_invalid_email));
		}
		else
			forgotPassword(password);
	}

	public void forgotPassword(String email) {
		//postEvent(new UserForgotPasswordStartEvent());
		ParseUser.requestPasswordResetInBackground(email, new UserForgotPasswordCallback());
	}
	
	private class UserForgotPasswordCallback extends RequestPasswordResetCallback{
		public UserForgotPasswordCallback(){
			super();
		}
		
		@Override
		public void done(ParseException e) {
			if (e == null) {
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ForgetParsePassword.this);
				builder.setMessage("Successfully sent link to your email for reset Password")
						.setTitle("Successful")
						.setPositiveButton(android.R.string.ok, null);
				AlertDialog dialog = builder.create();
				dialog.show();
				
				
				
				
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(ForgetParsePassword.this);
					builder.setMessage("Failed to sent link to your email for reset Password")
							.setTitle("Failed")
							.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
					
				
			}
		}		
	}

}
