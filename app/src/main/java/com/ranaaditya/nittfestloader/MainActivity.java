package com.ranaaditya.nittfestloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;

import android.animation.Animator;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.ranaaditya.nittfestloader.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoaderView loader=findViewById(R.id.loaderview);
        Thread t = new Thread(loader);
        t.start();
    }
}



