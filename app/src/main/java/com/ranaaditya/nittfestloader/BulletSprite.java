package com.ranaaditya.nittfestloader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public class BulletSprite {

    Bitmap rightgunBitmap, leftgunBitmap;
    float theta;
    private Bitmap rightbulletBitmap, leftbulletBitmap;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int mXleft = screenWidth / 2;
    private int mxright;
    private long xleft, xright;
    private long yleft ,yright;
    private int y = screenHeight / 2;
    private long mFadeOutTime;
    private float xAcceleration = 2.0f;
    private float xVelocity = 10.0f;
    private long mStarttimeMillis;
    private Boolean isAnimationRunning = false;
    private Paint mFadeOutPaint = new Paint();

    public BulletSprite(Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2, Bitmap bitmap3) {
        rightbulletBitmap = bitmap;
        rightgunBitmap = bitmap1;
        leftbulletBitmap = bitmap2;
        leftgunBitmap = bitmap3;
    }

    public void init() {
        mStarttimeMillis = 0L;
        isAnimationRunning = false;
        xleft = screenWidth / 2;
        xright = screenWidth/2 - leftgunBitmap.getWidth()/2;
        //y = screenHeight / 2;
        mXleft = screenWidth/2;
        mxright = screenWidth / 2 + leftgunBitmap.getWidth() / 2;
        xAcceleration = 0.5f;
        xVelocity = 10;
        mFadeOutPaint.setAlpha(255);
    }

    private void update() {

        if (!isAnimationRunning) {
            mStarttimeMillis = System.currentTimeMillis();
            isAnimationRunning = true;
        }

        long realTime = (System.currentTimeMillis() - mStarttimeMillis) / 30;

        Log.d("realtime", String.valueOf(realTime));

        if (xright > screenWidth) {
            init();
        }

        xleft = (long)  (mXleft  - (xVelocity * realTime + xAcceleration * realTime * realTime));
        xright = (long) (mxright + (xVelocity * realTime + xAcceleration * realTime * realTime));

        Log.d("X ----->>>>>", String.valueOf(xleft));
    }

    public void draw(Canvas canvas) {
        if (canvas != null && rightbulletBitmap != null) {
            Log.d("bitmap", String.valueOf(rightbulletBitmap));

            //updating the position of the bullets ...
            update();

            Log.d("SIZE OF BITMAP : ", String.valueOf(leftgunBitmap.getWidth()));

            //LOGS ...
            Log.d("LEFT GUN  WIDTH SIZE : ", String.valueOf(leftgunBitmap.getWidth()));
            Log.d("LEFT GUN HEIGHT SIZE : ", String.valueOf(leftgunBitmap.getHeight()));
            Log.d("RIGHT GUN WIDTH SIZE : ", String.valueOf(rightgunBitmap.getWidth()));
            Log.d("RIGHT GUN HEIGHT SIZE :", String.valueOf(rightgunBitmap.getHeight()));

            //positioning of bullets ...
            float leftwidthposition= screenWidth/2 - leftgunBitmap.getWidth();
            float rightwidthposition =screenWidth/2 ;
            float leftheightposition = screenHeight/2 - leftgunBitmap.getHeight()/1.5f;
            float rightheightposition = screenHeight/2 - rightgunBitmap.getHeight()/1.5f;
            canvas.drawBitmap(rightgunBitmap ,rightwidthposition ,rightheightposition,mFadeOutPaint);
            canvas.drawBitmap(leftgunBitmap,leftwidthposition,leftheightposition,mFadeOutPaint);


            if (xright<screenWidth) {
                canvas.drawBitmap(rightbulletBitmap, xright, y, mFadeOutPaint);
                canvas.drawBitmap(leftbulletBitmap,xleft,y,mFadeOutPaint);
            }
        }

    }
}