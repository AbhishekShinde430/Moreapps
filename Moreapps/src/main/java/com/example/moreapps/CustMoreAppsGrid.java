package com.example.moreapps;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustMoreAppsGrid extends BaseAdapter {

    private Context mContext;
    List<MoreAppsGetSet> arryList;

    public CustMoreAppsGrid(Context mContext, List<MoreAppsGetSet> arryList) {
        this.mContext = mContext;
        this.arryList = arryList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arryList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single_more_apps, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url=arryList.get(position).get_playURL();
                    Uri uri=Uri.parse(url);

                    Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW,uri );
                    try {
                        mContext.startActivity(myAppLinkToMarket);
                    } catch (ActivityNotFoundException e) {
                        // Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
                    }
                }
            });


            MoreAppsGetSet item=arryList.get(position);

            textView.setText(item.get_AppName());
            Glide.with(mContext).load(item.get_iconUrl()).into(imageView);




        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
