package com.example.angeo.newproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

/**
 * Created by techxform on 04-Mar-18.
 */

public class ListOnlineViewHolder extends RecyclerView.ViewHolder{

    public TextView txtemail;

    public ListOnlineViewHolder(View itemView) {
        super(itemView);

        txtemail=(TextView)itemView.findViewById(R.id.txt_email);

    }

}
