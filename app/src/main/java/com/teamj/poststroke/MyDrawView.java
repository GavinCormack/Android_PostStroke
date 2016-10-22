package com.teamj.poststroke;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;



/**
 * 
 * @author Gavin
 *
 * Gavin worked on the getting the canvas to draw where the finger touches the screen, 
 * saving the paths of each stroke and their respective colours. He came up with the 
 * new algorithm for analyzing the user's attempt and he also came up with how to read 
 * the images in dynamically and easily.
 */
public class MyDrawView extends View { //Custom View used to draw on
	
    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint circlePaint;
    private Path circlePath;

	private Paint redPaint, greenPaint, mPaint;

    private int pixel;
    
    
    private int previous;
    
    ArrayList<Path> paths = new ArrayList<Path>(); //stores all the paths drawn
	ArrayList<Paint> cols = new ArrayList<Paint>(); //stores the colors of the corresponding paths

    public MyDrawView(Context c, AttributeSet attrs)
    {
    	//Basic Constructor
        super(c, attrs);
        
        mPath = new Path();
        
        circlePath = new Path();
        
        circlePaint = new Paint(); //shows where user is touching screen
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.rgb(75, 148, 132));
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(4f); 
        
        redPaint = new Paint(); //paint for outside of image
	    redPaint.setAntiAlias(true);
	    redPaint.setDither(true);
	    redPaint.setColor(Color.RED);
	    redPaint.setStyle(Paint.Style.STROKE);
	    redPaint.setStrokeJoin(Paint.Join.ROUND);
	    redPaint.setStrokeCap(Paint.Cap.ROUND);
	    redPaint.setStrokeWidth(7);
	    
	    greenPaint = new Paint(); //paint for inside image
	    greenPaint.setAntiAlias(true);
	    greenPaint.setDither(true);
	    greenPaint.setColor(Color.GREEN);
	    greenPaint.setStyle(Paint.Style.STROKE);
	    greenPaint.setStrokeJoin(Paint.Join.ROUND);
	    greenPaint.setStrokeCap(Paint.Cap.ROUND);
	    greenPaint.setStrokeWidth(7);
	    
	    mPaint = new Paint();
  
	    previous = 3;   // 0 = red,		1 = green
    } 

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { //incase the canvas changes size
        super.onSizeChanged(w, h, oldw, oldh);
        
        Bitmap bm = BitmapFactory.decodeResource(getResources(), Drawing.imgId);
        
        mBitmap = Bitmap.createScaledBitmap(bm, w, h, false); //sets the image of the canvas background
        mCanvas = new Canvas(mBitmap);

    }
    
    @Override
    protected void onDraw(Canvas canvas) { //draws the canvas
        super.onDraw(canvas);

        for (int i=0; i<paths.size(); i++){ //loop for drawing the stored paths and colors
            canvas.drawPath(paths.get(i), cols.get(i));
        }

        canvas.drawPath( mPath,  mPaint);
        
        canvas.drawPath( circlePath,  circlePaint);
        
        
        
    }

    
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    

    private void touch_start(float x, float y) { //user touches screen
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x, float y) { //user moves finger
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
             mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;

            
            if(Drawing.bDone.getVisibility() == View.GONE)
            {
            	Drawing.bDone.setVisibility(View.VISIBLE);
            }


            circlePath.reset();
            circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
        }
    }
    
    private void touch_up() { //user lifts finger
        mPath.lineTo(mX, mY);
        circlePath.reset();

        mCanvas.drawPath(mPath,  mPaint);
        
        cols.add(mPaint);
        paths.add(mPath);
        mPaint = new Paint(mPaint);
        mPath = new Path(); 
        
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { //detects touches
        float x = event.getX();
        float y = event.getY();

        
        
        pixel = mBitmap.getPixel((int)x, (int)y); //gets color of pixel
        
        if(pixel == Color.rgb(0, 0, 0)) //checks if pixel is black (on top of image)
        {
        	if(previous == 0) //if previous color was red, change color to green
        	{
        		cols.add(mPaint);
                paths.add(mPath); //save the path
                mPaint = new Paint(mPaint);
                mPath = new Path(); //make a new path where the previous one stopped
                mPath.moveTo(x, y);
        		mPaint = greenPaint;
        	}
        	mPaint = greenPaint;
        	mPaint = new Paint(mPaint);
        	previous = 1; //sets previous equal to green
        	
        }
        else if(pixel == Color.rgb(255, 255, 255)) //checks if pixel is white
        {
        	if(previous == 1) //if previous was green, change color to red
        	{
        		cols.add(mPaint);
                paths.add(mPath); //save the path
                mPaint = new Paint(mPaint);
                mPath = new Path();  //make a new path where the previous one stopped
                mPath.moveTo(x, y);
        		mPaint = redPaint;
        	}
        	mPaint = redPaint;
        	mPaint = new Paint(mPaint);
        	previous = 0; //sets previous equal to red
        }
           
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //user touches screen
                touch_start(x, y);
                invalidate(); //redraw canvas
                break;
            case MotionEvent.ACTION_MOVE: //user moves finger
                touch_move(x, y);
                invalidate(); //redraw canvas
                break;
            case MotionEvent.ACTION_UP: //users lifts finger
                touch_up();
                invalidate(); //redraw canvas
                break;
        }
        return true;
    }  
}