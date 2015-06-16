package com.android.addviewtest;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	
	private ViewPager viewPager;
	private ArrayList<View> listViews;
	private TextView cityName;
	//private LayoutInflater inflater;
	private Button button;
	private MyPagerAdapter adapter;
	private Toast mToast;
	private static int count;
	private String countyCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //inflater = LayoutInflater.from(this);
        //this.viewPager = (ViewPager) findViewById(R.id.testViewPager);
        button = (Button) findViewById(R.id.switch_city);
        
//        if (!TextUtils.isEmpty(countyCode)) {
//        	Log.d("MainActivity", "=" + countyCode );
//        	
//        	
//			count++;	
//		}
        initListViews(count);
        viewPager = (ViewPager) findViewById(R.id.testViewPager);
        cityName = (TextView) findViewById(R.id.city_name);
        viewPager.setOnPageChangeListener(pageChangeListener);
        adapter = new MyPagerAdapter(listViews);
        adapter.setListViews(listViews);
		adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
		
        button.setOnClickListener(this);
        
        
       
    }
    /*
    @Override
    protected void onRestart(){
    	super.onRestart();
    	if (!TextUtils.isEmpty(countyCode)) {
        	Log.d("MainActivity", "=" + countyCode );
        	
			count++;	
			initListViews(count);
			adapter.setListViews(listViews);
			adapter.notifyDataSetChanged();
		}
    }
    */
    class MyPagerAdapter extends PagerAdapter{

    	private ArrayList<View> listViews;
    	private int size;
    	public MyPagerAdapter(ArrayList<View> listViews){ //construct
    		this.listViews = listViews;
    		size = listViews == null ? 0 : listViews.size();
    	}
    	
    	public void setListViews(ArrayList<View> listViews){
    		this.listViews = listViews;
    		size = listViews == null ? 0 : listViews.size();
    	}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return size;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2){
			((ViewPager) arg0).removeView(listViews.get(arg1 % size));
		}
		@Override
		public void finishUpdate(View arg0){
			
		}
		@Override
		public Object instantiateItem(View arg0, int arg1){
			try {
				((ViewPager) arg0).addView(listViews.get(arg1 % size), 0);
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("Adapter", "exception" + e.getMessage());
			}
			return listViews.get(arg1 % size);
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
    	
    }
    
    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
		   	
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			/*
			System.out.println("==========" + arg0);
			if (arg0 == viewPager.getAdapter().getCount() - 1) {
				count ++;
				initListViews(count);
				adapter.setListViews(listViews);
				adapter.notifyDataSetChanged();
			}
			*/
			
			if (mToast == null) {
				mToast = Toast.makeText(MainActivity.this, "翻到了第" +
			(arg0 + 1) + "页", Toast.LENGTH_SHORT);

			}else{
				mToast.setText("翻到了第" +(arg0 + 1) + "页");
				
			}
			
			cityName.setText("第" + (arg0 + 1) + "页");
			mToast.show();
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	private void initListViews(int count){
		if (listViews == null) {
			listViews = new ArrayList<View>();
		}
		TextView textView = new TextView(this);
		textView.setText("第" + (count + 1) + "页");
		if (null != countyCode) {
			
			Toast.makeText(MainActivity.this, countyCode, Toast.LENGTH_SHORT).show();
			//adapter.notifyDataSetChanged();
		}
		
		textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		textView.setGravity(Gravity.CENTER);
		listViews.add(textView);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		switch (requestCode	) {
		case 1:
			if (resultCode == RESULT_OK) {
				countyCode = data.getStringExtra("county_code");
				count++;	
				initListViews(count);
				adapter.setListViews(listViews);
				adapter.notifyDataSetChanged();
			}
			break;

		default:
			break;
		}
	}
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.switch_city:
		
			Intent intent = new Intent(MainActivity.this,CitylistActivity.class);
			
			startActivityForResult(intent, 1);
			//finish();
//			count++;	
//			initListViews(count);
//			adapter.setListViews(listViews);
//			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}
    
}
