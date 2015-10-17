package com.example.mapdemo;



import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mapdemo.GoogleType;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class GoogleSearchType extends Activity {
	
	private SlidingMenu slidingMenu ;

	 private AutoCompleteTextView autoComplete;
	 private MultiAutoCompleteTextView multiAutoComplete;
	 private ArrayAdapter<String> adapter;
	 private ArrayAdapter<String> distance_adapter;
	 private Button searchButton; 
	 private String[] google_types;
	 private String[] distance;
	 private Spinner radius_spinner;
	 private  String spinner_selection = "";
	 private  Integer spinner_selection_integer = 0;
	 public  static final String  PAR_KEY = "Modustri-DabaleArrozalazorraelABad";
	 private GoogleType  googletypeinstance = new GoogleType();
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_googletypes_search);
		
		ActionBar actionBar = getActionBar();
	    
		
		searchButton = (Button) findViewById(R.id.search_button);
		// get the defined string-array 
		google_types = getResources().getStringArray(R.array.google_search_types);
	
		radius_spinner = (Spinner)findViewById(R.id.radius_search);
		           
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,google_types);
		
		distance = getResources().getStringArray(R.array.radius_in_miles);
		distance_adapter = new ArrayAdapter<String>(this,android. R.layout.simple_spinner_dropdown_item, distance);
		 
		 
		autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete);
		multiAutoComplete = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoComplete);
	//	radius_selection = (AutoCompleteTextView) findViewById(R.id.radius_search);
		
		// set adapter for the auto complete fields
		radius_spinner.setAdapter(distance_adapter);
		radius_spinner.setPrompt("Within miles");
		autoComplete.setAdapter(adapter);
		multiAutoComplete.setAdapter(adapter);
		
		// specify the minimum type of characters before drop-down list is shown
	//	radius_selection.setThreshold(1);
		autoComplete.setThreshold(1);
		multiAutoComplete.setThreshold(1);
		//
	
		// comma to separate the different colors
		multiAutoComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		
		slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
        slidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu);
        
      //  getActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
		
		
    searchButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {

            // Starting new intent
            Intent i = new Intent(getApplicationContext(),
                    MainActivity.class);
         // passing user selection to MainActivity
            String selection= multiAutoComplete.getText().toString();
            
            Log.i("Places Status", "" + selection);
            String google_types = selection.replace(",", "|").replace(" ", "");
            
            Log.i("Places Status", "" +  google_types);
			 i.putExtra("user_selection", google_types);
			 i.putExtra("radius", spinner_selection);

			 
			 googletypeinstance.setStrValue(selection);
			 googletypeinstance.setIntValue(spinner_selection_integer);
			// Bundle mBundle = new Bundle(); 
			// mBundle.putParcelable(PAR_KEY, googletypeinstance);
			 // 3. put instance in intent data
			 i.putExtra("GoogleType", googletypeinstance);

            startActivity(i); 
        }    
        
    	});
	
	
    radius_spinner.setOnItemSelectedListener(
            new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3) {
                  spinner_selection_integer = arg2;
                  spinner_selection = arg0.getItemAtPosition(arg2).toString();
//                  Toast.makeText(getApplicationContext(),"You have selected "+celebrities[+position],Toast.LENGTH_LONG).show();
                    // TODO Auto-generated method stub
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            }
        );
}

	// Override the onBackPressed method : if the SlidingMenu is showing, 
	// it will close else the activity will close :
	
	@Override
    public void onBackPressed() {
        if ( slidingMenu.isMenuShowing()) {
            slidingMenu.toggle();
        }
        else {
            super.onBackPressed();
        }
    }
	
//	Override the onKeyDown method : to show/hide the slidingmenu when the menu key is touched.
	
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_MENU ) {
            this.slidingMenu.toggle();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
	
     // Override the onOptionsItemSelected method : 
	// to show/hide the SlidingMenu when the home icon of the action bar is touched.
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            this.slidingMenu.toggle();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
}