package com.example.angeo.newproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.angeo.newproject.Maptrack;
import com.example.angeo.newproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techxform on 04-Mar-18.
 */

public class AdapterGoogle extends RecyclerView.Adapter<AdapterGoogle.ViewHolder> {

    String name;
    private Location mLastLocation;
    double lati,longi;


    private int rowLayout;

    Activity activity1;

    public AdapterGoogle(String name,double lati,double longi,Activity activity, int rowLayout, Context context) {
        this.name = name;
        this.lati = lati;
        this.longi = longi;


        activity1 = activity;
        this.rowLayout = rowLayout;
        Context mContext = context;



    }

    public String getStudentist() {
        return name;
    }

    @Override
    public AdapterGoogle.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AdapterGoogle.ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(final AdapterGoogle.ViewHolder holder, final int position) {

        holder.NameTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(activity1, Maptrack.class);
                Bundle bn=new Bundle();
                bn.putString("email","");
                bn.putDouble("lat",lati);
                bn.putDouble("lang",longi);
                in.putExtras(bn);
                activity1.startActivity(in);


            }
        });

        holder.NameTxt.setText(" "+name);

    }

    @Override
    public int getItemCount() {
        return name.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView NameTxt,VehicleTxt,TimeTxt,Datetxt;
        CardView cdView;


        public ViewHolder(View itemView) {
            super(itemView);

            NameTxt=(TextView)itemView.findViewById(R.id.txt_email);
            cdView=(CardView)itemView.findViewById(R.id.layout);

        }

    }
}
