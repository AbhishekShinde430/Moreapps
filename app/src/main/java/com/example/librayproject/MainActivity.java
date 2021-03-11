package com.example.librayproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.moreapps.MoreAppsAlertClass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoreAppsAlertClass alertClass=new MoreAppsAlertClass(MainActivity.this,MainActivity.this);
        alertClass.FetchData("https://infiniteapps.000webhostapp.com/MoreApps.json");
    }
}