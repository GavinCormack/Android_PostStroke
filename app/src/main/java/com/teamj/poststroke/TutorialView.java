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
 * the images in dynamically and easily. Similar to the MyDrawView Class.
 */
public class TutorialView extends View { //Custom View for the Tutorial, same as MyDrawView, with minor changes (how background image is read in)

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
    
    ArrayList<Path> paths = new ArrayList<Path>();
	ArrayList<Paint> cols = new ArrayList<Paint>();

    public TutorialView(Context c, AttributeSet attrs)
    {
        super(c, attrs);
        
        mPath = new Path();
        
        circlePath = new Path();
        
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.rgb(75, 148, 132));
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(4f); 
        
        redPaint = new Paint();
	    redPaint.setAntiAlias(true);
	    redPaint.setDither(true);
	    redPaint.setColor(Color.RED);
	    redPaint.setStyle(Paint.Style.STROKE);
	    redPaint.setStrokeJoin(Paint.Join.ROUND);
	    redPaint.setStrokeCap(Paint.Cap.ROUND);
	    redPaint.setStrokeWidth(7);
	    
	    greenPaint = new Paint();
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.tutorial);
        
        mBitmap = Bitmap.createScaledBitmap(bm, w, h, false);
        mCanvas = new Canvas(mBitmap);

    }
    
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i=0; i<paths.size(); i++){
            canvas.drawPath(paths.get(i), cols.get(i));
        }

        canvas.drawPath( mPath,  mPaint);
        
        canvas.drawPath( circlePath,  circlePaint);
        
        
        
    }

    
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
             mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;

            circlePath.reset();
            circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
        }
    }
    
    private void touch_up() {
        mPath.lineTo(mX, mY);
        circlePath.reset();
        // commit the path to our offscreen
        mCanvas.drawPath(mPath,  mPaint);
        // kill this so we don't double draw
       // mPath.reset();
        
        cols.add(mPaint);
        paths.add(mPath);
        mPaint = new Paint(mPaint);
        mPath = new Path(); 
        
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        
        
        pixel = mBitmap.getPixel((int)x, (int)y);
        if(pixel == Color.rgb(0, 0, 0))
        {
        	if(previous == 0)
        	{
        		cols.add(mPaint);
                paths.add(mPath);
                mPaint = new Paint(mPaint);
                mPath = new Path(); 
                mPath.moveTo(x, y);
        		mPaint = greenPaint;
        	}
        	mPaint = greenPaint;
        	mPaint = new Paint(mPaint);
        	previous = 1;
        	
        }
        else if(pixel == Color.rgb(255, 255, 255))
        {
        	if(previous == 1)
        	{
        		cols.add(mPaint);
                paths.add(mPath);
                mPaint = new Paint(mPaint);
                mPath = new Path(); 
                mPath.moveTo(x, y);
        		mPaint = redPaint;
        	}
        	mPaint = redPaint;
        	mPaint = new Paint(mPaint);
        	previous = 0;
        }
           
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }  

    

}
