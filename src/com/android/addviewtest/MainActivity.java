package com.android.addviewtest;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	
	private ViewPager viewPager;
	private List<View> viewList = new ArrayList<View>();
	private LayoutInflater inflater;
	private Button button;
	private static int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(this);
        this.viewPager = (ViewPager) findViewById(R.id.testViewPager);
        button = (Button) findViewById(R.id.switch_city);
        count = 3;
        for (int i = 0; i < count ; i++) {
			final View itemView = inflater.inflate(R.layout.activity_main, null);
			viewList.add(itemView);
		}
        /*
        String countyCode = getIntent().getStringExtra("county_code");
               
        if (!TextUtils.isEmpty(countyCode)) {
        	Log.d("MainActivity", "=" + countyCode );
        	count++;
			for (int i = 0; i < count + 1; i++) {
				final View itemView = inflater.inflate(R.layout.layout_addlistview, null);
				viewList.add(itemView);
			}
			count++;
		}
          */ 
        viewPager.setAdapter(new PagerAdapter() {
    		@Override
    		public boolean isViewFromObject(View arg0, Object arg1) {
    			// TODO Auto-generated method stub
    			return arg0 == arg1;
    		}

    		@Override
    		public void destroyItem(View container, int position, Object object) {
    			((ViewPager) container).removeView(viewList.get(position));
    		}

    		@Override
    		public Object instantiateItem(View container, int position) {
    			((ViewPager) container).addView(viewList.get(position));
    			return viewList.get(position);
    		}

    		@Override
    		public int getCount() {
    			return count;
    		}

    		@Override
    		public void finishUpdate(View arg0) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void restoreState(Parcelable arg0, ClassLoader arg1) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public Parcelable saveState() {
    			// TODO Auto-generated method stub
    			return null;
    		}

    		@Override
    		public void startUpdate(View arg0) {
    			// TODO Auto-generated method stub
    			
    		}

    	});
        
        button.setOnClickListener(this);
    }
    
    
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.switch_city:
			
			Intent intent = new Intent(MainActivity.this,CitylistActivity.class);
			
			startActivity(intent);
			break;

		default:
			break;
		}
	}
    
}
