package com.snehpandya.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("MainActivity", "OnCreate");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v("MainActivity", "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v("MainActivity", "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v("MainActivity", "onDestroy");
    }

}
