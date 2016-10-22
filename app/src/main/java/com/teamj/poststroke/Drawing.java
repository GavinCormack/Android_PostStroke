package com.teamj.poststroke;

import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * 
 * @author Gavin, Stephen, Eanna
 * 
 * Gavin worked on displaying the canvas in MyDrawView to the screen and did some testing to
 * see what the minimum value for each level should be to make it a valid attempt, so the 
 * user can't just draw a dot and get 100%
 * 
 * Stephen worked on the SharedPreferences part so we could store and retrieve 
 * details about the user. 
 * 
 * Eanna worked on the Panic button side, sending a text message and displaying the user's
 * details afterwards. He also worked on designing the images to fit the screen.
 * 
 * @Reference http://developer.android.com/reference/android/content/BroadcastReceiver.html
 *
 */
public class Drawing extends Activity {
 
	static ImageView iv;
	MyDrawView mView;
	Bitmap uAttempt;
	
	Bitmap ourAttempt;
	
	double  red = 0.0, green = 0.0;
	String diff, lev, image;
	
	static int imgId = R.drawable.tutorial;
	String PACKAGE_NAME;
	
	SharedPreferences savedData;
	SharedPreferences.Editor editor;
	
	
	static Button bDone;
	
	static int min;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.drawing);
	    
	    //Retrieves values from levels activity
	    Bundle bundle = getIntent().getExtras();
		diff = bundle.getString("diff"); 
		lev = bundle.getString("level");
		
		image = diff + lev;
		PACKAGE_NAME = getApplicationContext().getPackageName();
		//retrieves image with same name as 'image' variable
		imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+image , null, null);
	    
	    iv = (ImageView) findViewById(R.id.ivImage);
	    iv.setImageResource(imgId);
	    
	    mView = (MyDrawView) findViewById(R.id.canvas);
	    
	    bDone = (Button) findViewById(R.id.bDone);
	    bDone.setVisibility(View.GONE);


	    savedData = getSharedPreferences("com.DrawTest", Activity.MODE_PRIVATE);
		editor = savedData.edit();
	    
	    min = checkMin();
	}
	
	
	//Checks to make sure a valid attempt was made
	public int checkMin()
	{
		int value = 0;

		switch(image)
		{
		case "easyone":
			value = 500;
			break;
		case "easytwo":
			value = 550;
			break;
		case "easythree":
			value = 450;
			break;
		case "easyfour":
			value = 550;
			break;
		case "easyfive":
			value = 800;
			break;
		case "easysix":
			value = 500;
			break;
		case "easyseven":
			value = 500;
			break;
		case "easyeight":
			value = 300;
			break;
		case "easynine":
			value = 900;
			break;
		case "easyten":
			value = 600;
			break;
		case "easyeleven":
			value = 350;
			break;
		case "easytwelve":
			value = 1100;
			break;
		case "easythirteen":
			value = 1000;
			break;
		case "easyfourteen":
			value = 700;
			break;
		case "easyfifteen":
			value = 900;
			break;
		case "easysixteen":
			value = 300;
			break;
		case "easyseventeen":
			value = 500;
			break;
		case "easyeighteen":
			value = 900;
			break;
		case "easynineteen":
			value = 300;
			break;
		case "easytwenty":
			value = 350;
			break;
			
		case "mediumone":
			value = 400;
			break;
		case "mediumtwo":
			value = 1000;
			break;
		case "mediumthree":
			value = 800;
			break;
		case "mediumfour":
			value = 800;
			break;
		case "mediumfive":
			value = 1300;
			break;
		case "mediumsix":
			value = 1400;
			break;
		case "mediumseven":
			value = 600;
			break;
		case "mediumeight":
			value = 450;
			break;
		case "mediumnine":
			value = 900;
			break;
		case "mediumten":
			value = 400;
			break;
		case "mediumeleven":
			value = 700;
			break;
		case "mediumtwelve":
			value = 800;
			break;
		case "mediumthirteen":
			value = 800;
			break;
		case "mediumfourteen":
			value = 400;
			break;
		case "mediumfifteen":
			value = 750;
			break;
		case "mediumsixteen":
			value = 600;
			break;
		case "mediumseventeen":
			value = 800;
			break;
		case "mediumeighteen":
			value = 800;
			break;
		case "mediumnineteen":
			value = 800;
			break;
		case "mediumtwenty":
			value = 750;
			break;
			
		case "hardone":
			value = 1000;
			break;
		case "hardtwo":
			value = 1600;
			break;
		case "hardthree":
			value = 1600;
			break;
		case "hardfour":
			value = 1100;
			break;
		case "hardfive":
			value = 1500;
			break;
		case "hardsix":
			value = 800;
			break;
		case "hardseven":
			value = 800;
			break;
		case "hardeight":
			value = 1500;
			break;
		case "hardnine":
			value = 2000;
			break;
		case "hardten":
			value = 2400;
			break;
		case "hardeleven":
			value = 2000;
			break;
		case "hardtwelve":
			value = 1000;
			break;
		case "hardthirteen":
			value = 2000;
			break;
		case "hardfourteen":
			value = 2500;
			break;
		case "hardfifteen":
			value = 2000;
			break;
		case "hardsixteen":
			value = 2500;
			break;
		case "hardseventeen":
			value = 3000;
			break;
		case "hardeighteen":
			value = 2600;
			break;
		case "hardnineteen":
			value = 2500;
			break;
		case "hardtwenty":
			value = 3000;
			break;
			
		}
		
		return value;
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
		String myMsg = "This is a message from PostStroke";
		String myNum = savedData.getString("userAge", "");
		sendMsg(myNum, myMsg);
		
		Intent i = new Intent(this, Details.class);
		startActivity(i);
	}
	
	//Done Button
	public void bDone(View v)
	{
		float percent = 0;
		String result = "";
		
		//Retrieves the drawing from the user
		mView.setDrawingCacheEnabled(true);
		uAttempt = mView.getDrawingCache();
		
		
		//Checks to see if the user drew something
		if(uAttempt != null)
		{
			percent = (float)analyse(uAttempt); //calls analyse method
			result = String.valueOf(round(percent,2)) + "%" ;
			
			
		}
		
		//adds result to the users savedData so it can be graphed
		editor.putFloat(image, percent);
		
		editor.commit();
		
		if(percent != 0) //if a valid attempt was made
		{
			Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
			onBackPressed();
		}
		else //if an invalid attempt was made
		{
			Toast.makeText(this, "Please draw some more", Toast.LENGTH_SHORT).show();
		}
		
		
	}
	
	//Back Button
	public void bBack(View v)
	{
		onBackPressed();
	}
	
	//Analyse Method
	public double analyse(Bitmap img)
	{
		 	int width =  img.getWidth();
		    int height = img.getHeight();
		    
		    
		    //compares drawing pixel by pixel
		    for (int i = 0; i < width; i++) 
		    {
		      for (int j = 0; j < height; j++) 
		      {
		        int rgb = img.getPixel(i, j);
		        
		        
		        
		        if(rgb == Color.GREEN) //if pixel is green
		        {
		        	green++; 
		        }
		        
		        else if(rgb == Color.RED) //if pixel is red
		        {
		        	red++;
		        }
		        
		      }
		    }
		    
		    
		  
		   double result = (green/(green+red))*100; //percentage of green to (green+red)
		   
		    
		   if((green+red) < min) //if invalid attempt
		   {
			   result = 0;
		   }
		    
		    if(result != 0) //if valid attempt
		    {
		    	
		    	return result;
		    }
		    else
		    {
		    	return 0;
		    }
		    
		    
		    
		    
		    
	}
	
	//rounds percentage to 2 decimal places so it's more readable
	public static double round(double value, int places) {
	    if (places < 0) 
	    	throw new IllegalArgumentException();
	    

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	
}