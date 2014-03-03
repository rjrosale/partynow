package com.rjrosaledjwisema.partynow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegistrationScreen extends SherlockActivity{
	private EditText username, email, password, fullname, street, city, state, zip;
	private Button registerbutton;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_screen);


		username = (EditText)findViewById(R.id.editreg_user);
		email = (EditText)findViewById(R.id.editreg_email);
		password = (EditText)findViewById(R.id.editreg_pass);
		fullname = (EditText)findViewById(R.id.editreg_name);
		street = (EditText)findViewById(R.id.editreg_street);
		city = (EditText)findViewById(R.id.editreg_city);
		state = (EditText)findViewById(R.id.editreg_state);
		zip = (EditText)findViewById(R.id.editreg_zip);
		registerbutton = (Button)findViewById(R.id.register_button);
		initButtonListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return true;
	}

	private void initButtonListeners() {
		registerbutton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				ParseUser user = new ParseUser();
				String usern = username.getText().toString().toLowerCase();
				String passw = password.getText().toString().toLowerCase();
				String em = email.getText().toString().toLowerCase();
				if (usern != null && !usern.isEmpty()) {
					if (passw != null && !passw.isEmpty()) {
						if (em != null && !em.isEmpty()) {
							user.setUsername(usern);
							user.setPassword(passw);
							user.setEmail(em);

							user.signUpInBackground(new SignUpCallback() {
								public void done(ParseException e) {
									if (e == null) {
										// Hooray! Let them use the app now.
									} else {
										// Sign up didn't succeed. Look at the ParseException
										// to figure out what went wrong
									}
								}
							});
						}
						else
							Toast.makeText(RegistrationScreen.this, "Email Required", Toast.LENGTH_SHORT).show();
					}
					else
						Toast.makeText(RegistrationScreen.this, "Password Required", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(RegistrationScreen.this, "Username Required", Toast.LENGTH_SHORT).show();
			}
		});
	}
}

		//				if (user != null && !user.isEmpty()) {
		//					if (pass != null && !pass.isEmpty()) {
		//						ParseUser.logInInBackground(username, password, new LogInCallback() {
		//							@Override
		//							public void done(ParseUser user, ParseException e) {
		//								if (user != null) {
		//									Toast.makeText(LoginScreen.this, "Login Successful!", Toast.LENGTH_SHORT).show();
		//									Intent intent = new Intent(LoginScreen.this, MainActivity.class);
		//									LoginScreen.this.startActivity(intent);
		//									// Hooray! The user is logged in.
		//								} else {
		//									Toast.makeText(LoginScreen.this, "Login Failed", Toast.LENGTH_SHORT).show();
		//									// Signup failed. Look at the ParseException to see what happened.
		//								}
		//							}
		//						});
		//					}
		//					else 
		//						Toast.makeText(LoginScreen.this, "Password Required", Toast.LENGTH_SHORT).show();
		//				}
		//				else
		//					Toast.makeText(LoginScreen.this, "Username Required", Toast.LENGTH_SHORT).show();
		//			}
