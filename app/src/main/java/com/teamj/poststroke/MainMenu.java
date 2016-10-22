package com.teamj.poststroke;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
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
 * details about the user. He also designed the layout of the page and suggested the main
 * colour scheme.
 * 
 * Eanna worked on the Panic button side, sending a text message and displaying the user's 
 * details afterwards. He also worked on checking the user's phone credit.
 * 
 * @Reference http://developer.android.com/reference/android/content/BroadcastReceiver.html
 * @Reference http://developer.android.com/reference/android/telephony/TelephonyManager.html
 *
 */
public class MainMenu extends Activity {

	Button bPanic, bPlay, bHowTo, bScores;
	
	String carrierName ,code;
	private final String encodedHash = Uri.encode("#");
	String ussd;
	
	IntentFilter intentFilter;
	

	SharedPreferences sharedPrefs;
	SharedPreferences.Editor editor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		carrierName = manager.getNetworkOperatorName();
		
		
		
		bPanic = (Button) findViewById(R.id.bPanic);
		bPlay = (Button) findViewById(R.id.bPlay);
		bHowTo = (Button) findViewById(R.id.bHowTo);
		
		bScores = (Button) findViewById(R.id.bScores);
		
		ussd = checkNetworks();
		
		intentFilter = new IntentFilter();
		
		
		sharedPrefs = getSharedPreferences("com.DrawTest", Activity.MODE_PRIVATE);
		editor = sharedPrefs.edit();
		
		boolean registered = sharedPrefs.getBoolean("Registered", false);
		//checks if the user has registered before
		if(!registered) //if not
		{
			Intent i = new Intent(this, Register.class);
			startActivity(i);
		}
		
		
		
	}
	
	
	
	
	
	//Checks the devices network, and sets the USSD for checking credit
	private String checkNetworks()
    {
		
        if (carrierName.contains("odafone"))
        {
            code = (new StringBuilder()).append("*174").append(encodedHash).toString();
            
            return code;
        }
        if (carrierName.contains("2"))
        {
            code = (new StringBuilder()).append("*100").append(encodedHash).toString();

            return code;
        }
        if (carrierName.contains("eteor"))
        {
            code = (new StringBuilder()).append("*").append(encodedHash).append("100").append(encodedHash).toString();

            return code;
        }
        if (carrierName.contains("hree") || carrierName.contains("3") || carrierName.contains("THREE"))
        {
           Toast.makeText(this, "3 Ireland unavailable, please check my3account.three.ie",  Toast.LENGTH_SHORT).show();
           return "nil";
        }
        if (carrierName.contains("ostfone"))
        {
            code = (new StringBuilder()).append("*200").append(encodedHash).toString();

            return code;
        }
        if (carrierName.contains("esco"))
        {
            code = (new StringBuilder()).append("*100").append(encodedHash).toString();

            return code;
        }
        if (carrierName.contains("eMobile") || carrierName.contains("emobile") || carrierName.contains("Emobile") || carrierName.contains("EMOB") || carrierName.contains("e-m") || carrierName.contains("E-M") || carrierName.contains("e-M") || carrierName.contains("E-m"))
        {
            code = (new StringBuilder()).append("*").append(encodedHash).append("100").append(encodedHash).toString();

            return code;
        } else
        {
            Toast.makeText(this, "Unable to find phone Network", Toast.LENGTH_SHORT).show();
            return "nil";
        }
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
	
	
	//Panic Button
	public void bPanic(View v)
	{
		String myMsg = "This is a message from PostStroke, the Panic button was pressed. Please check up on the user";
		String myNum = sharedPrefs.getString("NOKPhone", "");
		sendMsg(myNum, myMsg);
		
		Intent i = new Intent(this, Details.class);
		startActivity(i);
	}
	
	//Play Button
	public void bPlay(View v)
	{
		Intent i = new Intent(this, Difficulty.class);
		startActivity(i);
	}
	
	//HowTo Button
	public void bHowTo(View v)
	{
		Intent i = new Intent(this, HowTo.class);
		startActivity(i);
	}
	
	//Scores Button
	public void bScores(View v)
	{
		Intent i = new Intent(this, Scores.class);
		startActivity(i);
	}
	
	//Credit Button
	public void checkBalance(View v)
	{
		Intent i=new Intent(android.content.Intent.ACTION_CALL,Uri.parse("tel:"+ussd));
	    startActivity(i);
	}
	
	//User Button
	public void userDetails(View v)
	{
		Intent i = new Intent(this, Details.class);
		startActivity(i);
		
	}
	
	
	
}
