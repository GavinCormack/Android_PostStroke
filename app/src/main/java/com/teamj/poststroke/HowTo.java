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
import android.widget.TextView;
import android.widget.Toast;



/**
 * 
 * @author Gavin, Stephen, Eanna
 * 
 * Gavin worked on linking the graphical view to the java code.
 * 
 * Stephen worked on the SharedPreferences part so we could store and retrieve 
 * details about the user. He also designed the graphical layout.
 * 
 * Eanna worked on the Panic button side, sending a text message and displaying the user's
 * details afterwards. He also worked on designing the graphical layout, what the tutorial 
 * should say and which image should be used as the tutorial image.
 * 
 * @Reference http://developer.android.com/reference/android/content/BroadcastReceiver.html
 *
 */
public class HowTo extends Activity {

	Button bPanic, bOK;
	TextView tvTut;
	
	SharedPreferences sharedPrefs;
	SharedPreferences.Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.how_to);
		
		bPanic = (Button) findViewById(R.id.bPanic);
		bOK = (Button) findViewById(R.id.bOK);
		
		tvTut = (TextView) findViewById(R.id.tvTut);
		
		
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
	public void bOK(View v)
	{
		onBackPressed();
	}
}
