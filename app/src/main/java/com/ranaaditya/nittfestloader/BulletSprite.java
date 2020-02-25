package com.ranaaditya.nittfestloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class BulletSprite {

    private Bitmap bulletBitmap;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int mX=screenWidth/2;
    private long  x ;
    private int y=screenHeight/2;
    private long mFadeOutTime;
    private float  xAcceleration =2.0f;
    private float  xVelocity=10.0f ;
    private long  mStarttimeMillis ;
    private Boolean isAnimationRunning=false;
    private Paint mFadeOutPaint=new Paint() ;

    public BulletSprite(Bitmap bitmap){
        bulletBitmap=bitmap;
    }

    public void init(){
        mStarttimeMillis=0L;
        isAnimationRunning=false;
        x=screenWidth/2;
        y=screenHeight/2;
        mX=screenWidth/2;
        xAcceleration=0.5f;
        xVelocity=10;
        mFadeOutPaint.setAlpha(255);
    }

    private void update(){
        if (!isAnimationRunning){
            mStarttimeMillis=System.currentTimeMillis();
            isAnimationRunning=true;
        }
        long realTime=(System.currentTimeMillis() - mStarttimeMillis)/30;
        Log.d("realtime",String.valueOf(realTime));
        if (x>screenWidth){
            init();
        }
        x= (long) (mX + xVelocity*realTime +xAcceleration*realTime*realTime);
        Log.d("X ----->>>>>" , String.valueOf(x));
    }
    public void draw(Canvas canvas){
        if (canvas!=null && bulletBitmap!=null) {
            Log.d("bitmap",String.valueOf(bulletBitmap));
            update();
            if (x<screenWidth)
            canvas.drawBitmap(bulletBitmap,x,y,mFadeOutPaint);
        }

    }
}