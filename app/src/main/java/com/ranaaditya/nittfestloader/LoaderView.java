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
    private Bitmap bulletbitmap,gunbitmap;
    private BulletSprite bulletSprite;

    public LoaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
      init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(255, 255, 255);
        if (bulletbitmap != null && gunbitmap!=null) {
            Log.d("BITMAP",String.valueOf(bulletbitmap.toString()));

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
         bulletbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);
         bulletbitmap=getResizedBitmap(bulletbitmap,bulletbitmap.getWidth()/40,bulletbitmap.getHeight()/30);
         gunbitmap=BitmapFactory.decodeResource(getResources(),R.drawable.gun);
         gunbitmap=getResizedBitmap(gunbitmap,gunbitmap.getWidth()/10,gunbitmap.getHeight()/10);
        //gunbitmap=getResizedBitmap(gunbitmap, 350,350);


        bulletSprite=new BulletSprite(bulletbitmap,gunbitmap);
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
