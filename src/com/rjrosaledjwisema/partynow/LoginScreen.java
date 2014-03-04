package com.rjrosaledjwisema.partynow;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.view.MenuInflater;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginScreen extends SherlockActivity {
	private Button login, register; 
	private EditText user, pass;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		Parse.initialize(this, "q4gB8a9Fr30V0T0Rq7Cak26YpSEJeGnD4ShknpGn", "Su7l9n816K3fI1hILnErpWjOBwxfWSUAC7XRvpnZ");
		
		login = (Button)findViewById(R.id.login_button);
		register = (Button)findViewById(R.id.signup_button);
		user = (EditText)findViewById(R.id.user_field);
		pass = (EditText)findViewById(R.id.pass_field);
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
		login.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				String username = user.getText().toString();
				String password = pass.getText().toString();
				
				if (username != null && !username.isEmpty()) {
					if (password != null && !password.isEmpty()) {
						ParseUser.logInInBackground(username, password, new LogInCallback() {
							@Override
							public void done(ParseUser user, ParseException e) {
								if (user != null) {
									Toast.makeText(LoginScreen.this, "Login Successful!", Toast.LENGTH_SHORT).show();
									Intent intent = new Intent(LoginScreen.this, Main.class);
									intent.putExtra("userID", user.getObjectId());
									LoginScreen.this.startActivity(intent);
									// Hooray! The user is logged in.
								} else {
									Toast.makeText(LoginScreen.this, "Login Failed", Toast.LENGTH_SHORT).show();
									// Signup failed. Look at the ParseException to see what happened.
								}
							}
						});
					}
					else 
						Toast.makeText(LoginScreen.this, "Password Required", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(LoginScreen.this, "Username Required", Toast.LENGTH_SHORT).show();
			}
		});

		register.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				LoginScreen.this.startActivity(new Intent(LoginScreen.this, RegistrationScreen.class));
			}
		});
	}
}
