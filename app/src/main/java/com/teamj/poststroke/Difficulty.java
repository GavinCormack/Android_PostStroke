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
 * 
 * Stephen worked on the SharedPreferences part so we could store and retrieve 
 * details about the user. 
 * 
 * Eanna worked on the Panic button side, sending a text message and displaying the user's 
 * details afterwards.
 * 
 * @Reference http://developer.android.com/reference/android/content/BroadcastReceiver.html
 *
 */
public class Difficulty extends Activity {

	
	SharedPreferences sharedPrefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.difficulty);
		
		
		sharedPrefs = getSharedPreferences("com.DrawTest", Activity.MODE_PRIVATE);
		
	}

	
	//Sends the user's Next of Kin a Text Message
	protected void sendMsg(String myNum, String myMsg) {
		String SENT = "Message Sent";
		String DELIVERED = "Message Delivered";
		
		
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
		
		
		//Checks if Text Message was sent
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
		
		
		//Checks if Text Message was delivered
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
	
	//Panic Button, calls sendMsg method
	public void bPanic(View v)
	{
		String myMsg = "This is a message from PostStroke";
		String myNum = sharedPrefs.getString("userAge", ""); 
		sendMsg(myNum, myMsg);
		
		Intent i = new Intent(this, Details.class);
		startActivity(i);
	}
	
	//Back button
	public void bBack(View v)
	{
		onBackPressed();
	}
	
	//Easy Button
	public void bEasy(View v)
	{
		Intent i = new Intent(this, Levels.class);
		i.putExtra("diff", "easy"); //passes value to next activity
		startActivity(i);
	}
	
	//Medium Button
	public void bMedium(View v)
	{
		Intent i = new Intent(this, Levels.class);
		i.putExtra("diff", "medium"); //passes value to next activity
		startActivity(i);
	}
	
	//Hard Button
	public void bHard(View v)
	{
		Intent i = new Intent(this, Levels.class);
		i.putExtra("diff", "hard"); //passes value to next activity
		startActivity(i);
	}
	
}
