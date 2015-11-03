package com.example.aur_kit.app;


import com.example.aur_kit.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class MainSelector extends Activity implements OnItemSelectedListener{
	
	private Spinner spinnerFloor;
	private Spinner spinnerClass;
	private CheckBox elev;
	private Button btnIr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_selector);
		
		this.spinnerFloor = (Spinner)findViewById(R.id.spinnerFloor);
		this.spinnerClass = (Spinner)findViewById(R.id.spinnerClass);
		
		loadSpinner();
		
		this.elev = (CheckBox)findViewById(R.id.checkboxElev);
        this.btnIr = (Button)findViewById(R.id.button_go);
        this.btnIr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent =
                         new Intent(MainSelector.this, MainActivity.class);
                 //Create the information for the intent
                 Bundle b = new Bundle();
                 b.putString("DESTINATION", spinnerClass.getSelectedItem().toString());
                 b.putBoolean("ELEVATOR", elev.isChecked());
                 //Add to the intent
                 intent.putExtras(b);
                 //Initiate new activity
                 startActivity(intent);				
			}
		});
	}

	private void loadSpinner() {
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.array_floor, android.R.layout.simple_spinner_item);
	    // Specify the layout to use when the list of choices appears
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    // Apply the adapter to the spinner
	    this.spinnerFloor.setAdapter(adapter);	 
	    // This activity implements the AdapterView.OnItemSelectedListener
	    this.spinnerFloor.setOnItemSelectedListener(this);
	    this.spinnerClass.setOnItemSelectedListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_selector, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {		
		switch (parent.getId()) {
        	case R.id.spinnerFloor:
        		// Retrieves an array
        		TypedArray arrayClass = getResources().obtainTypedArray(
        				R.array.floor_to_class);
        		CharSequence[] classSp = arrayClass.getTextArray(position);
        		arrayClass.recycle();
        		// Create an ArrayAdapter using the string array and a default
        		// spinner layout
        		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
        				this, android.R.layout.simple_spinner_item,
        				android.R.id.text1, classSp);
        		// Specify the layout to use when the list of choices appears
        		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        		
        		// Apply the adapter to the spinner
        		this.spinnerClass.setAdapter(adapter);
        		break;
        	case R.id.spinnerClass:
        		break;
        }
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

}
