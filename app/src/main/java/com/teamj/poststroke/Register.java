package com.teamj.poststroke;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


/**
 * 
 * @author Gavin, Stephen, Eanna
 * 
 * Gavin worked on linking the graphical side to the java side to display the details nicely.
 * 
 * Stephen worked on the SharedPreferences part so we could store and retrieve 
 * details about the user. He also worked on the graphical layout.
 *
 * Eanna worked on the graphical layout of this page. He also worked with team members John 
 * and Sun to decide which questions should be asked and which should not.
 * 
 * @Reference http://developer.android.com/reference/android/content/BroadcastReceiver.html
 * 
 */
public class Register extends Activity{

	EditText fName, sName, age, height, weight, nokPhone, nokName;
	
	SharedPreferences userDetails;
	SharedPreferences.Editor editor;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		//Where the user inputs their details
		fName = (EditText) findViewById(R.id.etUserFName);
		sName = (EditText) findViewById(R.id.etUserSName);
		age = (EditText) findViewById(R.id.etUserAge);
		height = (EditText) findViewById(R.id.etUserHeight);
		weight = (EditText) findViewById(R.id.etUserWeight);
		nokPhone = (EditText) findViewById(R.id.etNOKPhone);
		nokName = (EditText) findViewById(R.id.etNOKName);
		
		
		
		userDetails = getSharedPreferences("com.DrawTest", Activity.MODE_PRIVATE);
		editor = userDetails.edit();
		
	}
	
	public void bRegister(View v)
	{
		//Retrieves the text from the EditTextBoxes and stores them
		editor.putString("firstName", fName.getText().toString().trim());
		editor.putString("surName", sName.getText().toString().trim());
		editor.putString("userAge", age.getText().toString().trim());
		editor.putString("userHeight", height.getText().toString().trim());
		editor.putString("userWeight", weight.getText().toString().trim());
		editor.putString("NOKPhone", nokPhone.getText().toString().trim());
		editor.putString("NOKName", nokName.getText().toString().trim());
		
		editor.putBoolean("Registered", true); //stores a boolean to show the user has registered
		
		editor.commit();
		
		
		
		finish();
		
		
	}
	
	
}
