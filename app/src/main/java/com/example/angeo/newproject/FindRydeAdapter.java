package com.example.angeo.newproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kichu on 12-02-2018.
 */

public class FindRydeAdapter extends RecyclerView.Adapter<FindRydeAdapter.ViewHolder> {

    private List<String> name = new ArrayList<>();
    private List<String> contact = new ArrayList<>();
    private List<String> startlat = new ArrayList<>();
    private List<String> startlon = new ArrayList<>();
    private List<String> destlat = new ArrayList<>();
    private List<String> destlon = new ArrayList<>();
    private List<String> offerdate = new ArrayList<>();
    private List<String> offertime = new ArrayList<>();
    private List<String> bikemodel = new ArrayList<>();
    private List<String> logdate = new ArrayList<>();
    private List<String> logtime = new ArrayList<>();
    private List<String> regno = new ArrayList<>();
    private List<String> seats = new ArrayList<>();
    private List<String> vehicle = new ArrayList<>();
    private List<String> features = new ArrayList<>();


    private int rowLayout;
    String PINN;
    int pos;
    String parameters;
    Activity activity1;

    public FindRydeAdapter(List<String> name, List<String> contact, List<String> startlat,
                           List<String> startlon,List<String> destlat,List<String> destlon,List<String> offerdate,
                           List<String> offertime,List<String> bikemodel,List<String> logdate,List<String> logtime,
                           List<String> regno,List<String> seats,List<String> vehicle,List<String> features,
                           Activity activity, int rowLayout, Context context) {
        this.name = name;
        this.contact = contact;
        this.startlat = startlat;
        this.startlon = startlon;
        this.destlat = destlat;
        this.destlon = destlon;
        this.offerdate = offerdate;
        this.offertime = offertime;
        this.bikemodel = bikemodel;
        this.logdate = logdate;
        this.logtime = logtime;
        this.regno = regno;
        this.seats = seats;
        this.vehicle = vehicle;
        this.features = features;

         activity1 = activity;
        this.rowLayout = rowLayout;
        Context mContext = context;



    }

    public List<String> getStudentist() {
        return name;
    }

    @Override
    public FindRydeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new FindRydeAdapter.ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(final FindRydeAdapter.ViewHolder holder, final int position) {

        holder.cdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(activity1,PoolOrderDetails.class);
                Bundle bn=new Bundle();
                bn.putString("","");
                bn.putString("","");
                bn.putString("","");
                bn.putString("","");
                bn.putString("","");
                bn.putString("","");
                bn.putString("","");

            }
        });

        holder.NameTxt.setText(" "+name.get(position));
        holder.Datetxt.setText(" "+offerdate.get(position));
        holder.TimeTxt.setText(" "+offertime.get(position));
        holder.VehicleTxt.setText(" "+vehicle.get(position));


    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView NameTxt,VehicleTxt,TimeTxt,Datetxt;
        CardView cdView;


        public ViewHolder(View itemView) {
            super(itemView);

            NameTxt=(TextView)itemView.findViewById(R.id.nameid);
            VehicleTxt=(TextView)itemView.findViewById(R.id.vehicleid);
            TimeTxt=(TextView)itemView.findViewById(R.id.timeid);
            Datetxt=(TextView)itemView.findViewById(R.id.dateid);
            cdView=(CardView) itemView.findViewById(R.id.cardviewid);

        }

    }
}
