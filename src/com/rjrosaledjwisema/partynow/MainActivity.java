package com.rjrosaledjwisema.partynow;

import com.parse.Parse;
import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button post, find;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Parse.initialize(this, "q4gB8a9Fr30V0T0Rq7Cak26YpSEJeGnD4ShknpGn", "Su7l9n816K3fI1hILnErpWjOBwxfWSUAC7XRvpnZ");
		
		post = (Button)findViewById(R.id.postEvent);
		find = (Button)findViewById(R.id.findEvent);
		
		initButtonListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initButtonListeners() {
		post.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				MainActivity.this.startActivity(new Intent(MainActivity.this, TitleScreen.class));
			}
		});
		
		find.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				MainActivity.this.startActivity(new Intent(MainActivity.this, MapActivity.class));
			}
		});
	}
}
