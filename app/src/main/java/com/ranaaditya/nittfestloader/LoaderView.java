package com.ranaaditya.nittfestloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class LoaderView extends View implements Runnable {
    private Context mcontext;
    private Bitmap rightbulletbitmap,leftbulletBitmap, rightgunbitmap,leftgunBitmap;
    private BulletSprite bulletSprite;

    public LoaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
      init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255, 255, 255);
        if (rightbulletbitmap != null && rightgunbitmap !=null) {
            Log.d("BITMAP",String.valueOf(rightbulletbitmap.toString()));

            bulletSprite.draw(canvas);
        }else{
            Log.d("ERROR","bitmap is empty");
        }
        super.onDraw(canvas);

    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
            }
            ((Activity) mcontext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    invalidate();
                }
            });
        }
    }


    private void  init() {
         rightbulletbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);
         rightbulletbitmap =getResizedBitmap(rightbulletbitmap, rightbulletbitmap.getWidth()/40, rightbulletbitmap.getHeight()/30);
         rightgunbitmap =BitmapFactory.decodeResource(getResources(),R.drawable.gun);
         rightgunbitmap =getResizedBitmap(rightgunbitmap, rightgunbitmap.getWidth()/10, rightgunbitmap.getHeight()/10);
         leftbulletBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.leftbullet);
         leftbulletBitmap=getResizedBitmap(leftbulletBitmap,leftbulletBitmap.getWidth()/40,leftbulletBitmap.getHeight()/30);
         leftgunBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.leftgunn);
         leftgunBitmap=getResizedBitmap(leftgunBitmap,leftgunBitmap.getWidth()/10,leftgunBitmap.getHeight()/10);
         bulletSprite=new BulletSprite(rightbulletbitmap, rightgunbitmap,leftbulletBitmap,leftgunBitmap);
         bulletSprite.init();

    }
    private Bitmap getResizedBitmap(Bitmap bm, float newWidth, float newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = (newWidth) / width;
        float scaleHeight = (newHeight) / height;
        // Create a matrix for manipulation
        Matrix matrix = new Matrix();
        // Resize the bitmap
        matrix.postScale(scaleWidth, scaleHeight);

        // "Recreate" the new bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

}
