package com.example.moreapps;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.List;

public class MoreAppsAlert extends Dialog {

    Activity activity;
    List<MoreAppsGetSet> arryList;
    GridView grid;
    CustMoreAppsGrid custMoreAppsGrid;
    ImageView imgClose;


    public MoreAppsAlert(@NonNull Context context, Activity activity, List<MoreAppsGetSet> arryList) {
        super(context);
        this.activity = activity;
        this.arryList = arryList;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_layout_moreapps);

        grid=(GridView)findViewById(R.id.gridView);
        imgClose=(ImageView) findViewById(R.id.imgClose);

        custMoreAppsGrid  = new CustMoreAppsGrid(activity, arryList);
        grid.setAdapter(custMoreAppsGrid);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Uri uri=Uri.parse(arryList.get(position).get_playURL());

                Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW,uri );
                try {
                    activity.startActivity(myAppLinkToMarket);
                } catch (ActivityNotFoundException e) {
                   // Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
                }
            }
        });





    }


}
