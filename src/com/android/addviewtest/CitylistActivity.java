package com.android.addviewtest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class CitylistActivity extends Activity implements OnClickListener{

	private Button backButton;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_addlistview);
		
		backButton = (Button) findViewById(R.id.back_city);
		backButton.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.back_city:
			String data = "beijing";
			Intent intent = new Intent(CitylistActivity.this,MainActivity.class);
			intent.putExtra("county_code", data);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
	
	
}
