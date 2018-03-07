package com.example.angeo.newproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.angeo.newproject.Contact;
import com.example.angeo.newproject.DatabaseHandler;
import com.example.angeo.newproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by techxform on 27-Feb-18.
 */

public class Contacts_list_adapter extends RecyclerView.Adapter<Contacts_list_adapter.ViewHolder> {

    private List<String> Body = new ArrayList<>();
    JSONObject dataobjects,jsonobjct;


    String Log;
    private final int rowLayout;
    private final Context mContext;

    DatabaseHandler db;
    public Contacts_list_adapter(String Log, Activity activity, int rowLayout, Context context) {
        this.Log = Log;


        Activity activity1 = activity;
        this.rowLayout = R.layout.notificationitem_layout;
        this.mContext = context;

        db = new DatabaseHandler(activity1);

    }

    @Override
    public Contacts_list_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new Contacts_list_adapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final Contacts_list_adapter.ViewHolder holder, final int position) {

        System.out.println("CONTACTS : "+Log);

        List<Contact> contacts = db.getAllContacts();
        System.out.println("SHANIL KICHU :::: "+Log);

        for (Contact cn : contacts) {
           String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log

            System.out.println("Name: 111111"+cn.getName());
            holder.NumberTxt.setText(" "+cn.getName());
        }





      

    }


    @Override
    public int getItemCount() {
        return Log.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView NameTxt;
        final TextView NumberTxt;
        final LinearLayout linearlayout;


        public ViewHolder(View itemView) {
            super(itemView);

            NameTxt = itemView.findViewById(R.id.txtid);
            NumberTxt = itemView.findViewById(R.id.txtid2);

            linearlayout = itemView.findViewById(R.id.linearlayout);


        }
    }
}
