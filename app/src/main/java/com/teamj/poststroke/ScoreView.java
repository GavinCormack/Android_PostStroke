package com.teamj.poststroke;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;



/**
 * 
 * @author Gavin
 *
 * Gavin worked on the getting the canvas to display the user's scores for each level, 
 * putting red dots at each score and joining each dot to make it look nicer. He also 
 * added the level number and percentage to the red dots as suggested by Eanna.
 */
public class ScoreView extends View{ //Custom view that shows user's scores in a graph

	 	public float width;
	    public float height;

		private Paint redPaint, greenPaint, mPaint;
		
		

	    ArrayList<Float> xs = new ArrayList<Float>(); 
	    ArrayList<Float> ys = new ArrayList<Float>();

	    public ScoreView(Context c, AttributeSet attrs) //Basic constructor
	    {
	        super(c, attrs);
	        
	        //color for the dots
	        redPaint = new Paint();
		    redPaint.setAntiAlias(true);
		    redPaint.setDither(true);
		    redPaint.setColor(Color.RED);
		    redPaint.setStyle(Paint.Style.STROKE);
		    redPaint.setStrokeJoin(Paint.Join.ROUND);
		    redPaint.setStrokeCap(Paint.Cap.ROUND);
		    redPaint.setStrokeWidth(7);
		    
		    
		    //color for the lines
		    greenPaint = new Paint();
		    greenPaint.setAntiAlias(true);
		    greenPaint.setDither(true);
		    greenPaint.setColor(Color.GREEN);
		    greenPaint.setStyle(Paint.Style.STROKE);
		    greenPaint.setStrokeJoin(Paint.Join.ROUND);
		    greenPaint.setStrokeCap(Paint.Cap.ROUND);
		    greenPaint.setStrokeWidth(7);
		    
		    //color for the text
		    mPaint = new Paint(greenPaint);
		    mPaint.setStrokeWidth(1);
		    mPaint.setColor(Color.BLACK);
	  
	    } 

	    @Override
	    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	        super.onSizeChanged(w, h, oldw, oldh);
	        
	        
	    }
	    
	    @Override
	    protected void onDraw(Canvas canvas) { //draws canvas
	        super.onDraw(canvas);
	        
	        width = canvas.getWidth();
	        height = canvas.getHeight();
	        
	        
	        for(int i=0; i < Scores.points.length; i++) //draws the dots
	        {
	        	canvas.drawCircle(((width/((Scores.points.length)+1))*(i+1))-5, height-(((height/100)*Scores.points[i])-5), 5, redPaint);
	        	xs.add(((width/((Scores.points.length)+1))*(i+1))-5);
	        	ys.add( height-(((height/100)*Scores.points[i])-5));
	        	
	        }
	        
	        
	        
	        for (int i=0; i < xs.size()-1; i++) //draws the lines
	        {
	            canvas.drawLine(xs.get(i), ys.get(i), xs.get(i+1), ys.get(i+1), greenPaint);
	            
	            
	        }
	        
	        for (int i=0; i < xs.size(); i++) //draws the text
	        {
	        	//level
	        	canvas.drawText("lvl "+(i+1), ((width/((Scores.points.length)+1))*(i+1))-5, height-(((height/100)*Scores.points[i])), mPaint);
	        	//score percentage
	        	canvas.drawText(String.valueOf((int)Scores.points[i])+"%", ((width/((Scores.points.length)+1))*(i+1))-5, height-(((height/100)*Scores.points[i])-12), mPaint);
	        }
	    }

}
