package com.ranaaditya.nittfestloader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class BulletSprite {

    private Bitmap rightbulletBitmap,leftbulletBitmap;
    Bitmap rightgunBitmap,leftgunBitmap;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int mXleft =screenWidth/2;
    private int mxright;
    private long xleft,xright;
    private int y=screenHeight/2;
    private long mFadeOutTime;
    private float  xAcceleration =2.0f;
    private float  xVelocity=10.0f ;
    private long  mStarttimeMillis ;
    private Boolean isAnimationRunning=false;
    private Paint mFadeOutPaint=new Paint() ;

    public BulletSprite(Bitmap bitmap , Bitmap bitmap1,Bitmap bitmap2,Bitmap bitmap3){
        rightbulletBitmap =bitmap;
        rightgunBitmap =bitmap1;
        leftbulletBitmap=bitmap2;
        leftgunBitmap=bitmap3;
    }

    public void init(){
        mStarttimeMillis=0L;
        isAnimationRunning=false;
        xleft =screenWidth/2;
        xright=screenWidth;
        y=screenHeight/2;
        mXleft =150;
        mxright=screenWidth/2+leftgunBitmap.getWidth()/2;
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
        if (xright <screenWidth/2 || xleft>screenWidth/2-rightbulletBitmap.getWidth()/2){
            init();
        }
        xleft = (long) (mXleft + xVelocity*realTime +xAcceleration*realTime*realTime);
        xright=(long) (mxright-(xVelocity*realTime+xAcceleration*realTime*realTime));
        Log.d("X ----->>>>>" , String.valueOf(xleft));
    }
    public void draw(Canvas canvas){
        if (canvas!=null && rightbulletBitmap !=null) {
            Log.d("bitmap",String.valueOf(rightbulletBitmap));
            update();
            Log.d("SIZE OF BITMAP : ",String.valueOf(leftgunBitmap.getWidth()));
            canvas.drawBitmap(rightgunBitmap,screenWidth/2 - rightgunBitmap.getWidth()*1.25f,screenHeight/2- rightgunBitmap.getHeight()/2.50f,mFadeOutPaint);
            canvas.drawBitmap(leftgunBitmap,screenWidth-leftgunBitmap.getWidth()+150,screenHeight/2- leftgunBitmap.getHeight()/2.50f,mFadeOutPaint);


            if (xleft <screenWidth && xright>0) {
                canvas.drawBitmap(rightbulletBitmap, xleft, y, mFadeOutPaint);
                canvas.drawBitmap(leftbulletBitmap,xright,y,mFadeOutPaint);
            }
        }

    }
}