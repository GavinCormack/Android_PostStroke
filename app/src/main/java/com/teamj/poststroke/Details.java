package com.teamj.poststroke;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;


/**
 * 
 * @author Gavin, Stephen, Eanna
 * 
 * Gavin worked on linking the graphical side to the java side to display the details nicely.
 * 
 * Stephen worked on the SharedPreferences part so we could store and retrieve 
 * details about the user. He also worked on the graphical layout.
 *
 * Eanna worked on the graphical layout of this page, what should be displayed and how it 
 * should be displayed.
 * 
 * @Reference http://developer.android.com/reference/android/content/BroadcastReceiver.html
 * 
 */
public class Details extends Activity{

	
	TextView userName, userAge, userWeight, userHeight, nokName, nokPhone;
	
	SharedPreferences userDetails; //stores user's details
	SharedPreferences.Editor editor;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		
		userName = (TextView) findViewById(R.id.tvUserName);
		userAge = (TextView) findViewById(R.id.tvUserAge);
		userWeight = (TextView) findViewById(R.id.tvUserWeight);
		userHeight = (TextView) findViewById(R.id.tvUserHeight);
		nokName = (TextView) findViewById(R.id.tvNOKName);
		nokPhone = (TextView) findViewById(R.id.tvNOKPhone);
		
		
		
		userDetails = getSharedPreferences("com.DrawTest", Activity.MODE_PRIVATE);
		editor = userDetails.edit();
		
		
		//Retrieves the users details and sets them to TextViews
		userName.setText(userDetails.getString("firstName", "") + " "+ userDetails.getString("surName", ""));
		userAge.setText(userDetails.getString("userAge", "") + " years old");
		userWeight.setText(userDetails.getString("userWeight", "") + " lbs");
		userHeight.setText(userDetails.getString("userHeight", "") + " cm");
		nokName.setText(userDetails.getString("NOKName", ""));
		nokPhone.setText(userDetails.getString("NOKPhone", ""));
		
		
	}
	
}
