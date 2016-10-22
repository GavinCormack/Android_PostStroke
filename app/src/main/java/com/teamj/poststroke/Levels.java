package com.teamj.poststroke;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



/**
 * 
 * @author Gavin, Stephen, Eanna
 * 
 * Gavin worked on linking the graphical side to the java side to display the menu nicely.
 * He also designed the layout of the page.
 * 
 * Stephen worked on the SharedPreferences part so we could store and retrieve 
 * details about the user. He also designed the layout of the page.
 * 
 * Eanna worked on the Panic button side, sending a text message and displaying the user's 
 * details afterwards. He also designed the layout of the page.
 * 
 * @Reference http://developer.android.com/reference/android/content/BroadcastReceiver.html
 *
 */
public class Levels extends Activity {

	//buttons for the levels
	Button 	level1, 	level2, 	level3, 	level4, 
			level5, 	level6, 	level7, 	level8, 
			level9, 	level10, 	level11, 	level12, 
			level13,	level14, 	level15, 	level16, 
			level17, 	level18, 	level19, 	level20;
	Button bPanic, bBack;
	
	String difficulty;
	
	SharedPreferences sharedPrefs;
	SharedPreferences.Editor editor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levels);
		
		Bundle bundle = getIntent().getExtras();
		difficulty = bundle.getString("diff");
		
		
		sharedPrefs = getSharedPreferences("com.DrawTest", Activity.MODE_PRIVATE);
		editor = sharedPrefs.edit();
		
	}
	
	
	protected void sendMsg(String myNum, String myMsg) {
		String SENT = "Message Sent";
		String DELIVERED = "Message Delivered";
		
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
		
		registerReceiver(new BroadcastReceiver()
		{
			public void onReceive(Context arg0, Intent arg1)
			{
				switch(getResultCode())
				{
				case Activity.RESULT_OK:
					Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(), "Generic Failure", Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), "No Service", Toast.LENGTH_LONG).show();
					break;
				}
			}
		}, new IntentFilter(SENT));
		
		
		registerReceiver(new BroadcastReceiver()
		{
			public void onReceive(Context arg0, Intent arg1)
			{
				switch(getResultCode())
				{
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "SMS delivered", Toast.LENGTH_LONG).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(), "SMS not delivered", Toast.LENGTH_LONG).show();
				}
			}
		}, new IntentFilter(DELIVERED));
		
		
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(myNum, null, myMsg, sentPI, deliveredPI);
	}
	
	
	public void bPanic(View v)
	{
		String myMsg = "This is a message from PostStroke";
		String myNum = sharedPrefs.getString("userAge", "");
		sendMsg(myNum, myMsg);
		
		Intent i = new Intent(this, Details.class);
		startActivity(i);
	}
	
	public void bBack(View v)
	{
		onBackPressed();
	}
	
	
	//methods for all the button clicks
	public void level1(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "one");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level2(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "two");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level3(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "three");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level4(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "four");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level5(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "five");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level6(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "six");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level7(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "seven");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level8(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "eight");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level9(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "nine");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level10(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "ten");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level11(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "eleven");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level12(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "twelve");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level13(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "thirteen");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level14(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "fourteen");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level15(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "fifteen");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level16(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "sixteen");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level17(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "seventeen");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level18(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "eighteen");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level19(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "nineteen");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	public void level20(View v)
	{
		Intent i = new Intent(this, Drawing.class);
		i.putExtra("level", "twenty");
		i.putExtra("diff", difficulty);
		startActivity(i);
	}
	
}
