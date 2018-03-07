package com.example.angeo.newproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.angeo.newproject.App.Constants;
import com.example.angeo.newproject.MapsActivity;
import com.example.angeo.newproject.ProfilePage;
import com.example.angeo.newproject.R;
import com.example.angeo.newproject.Registertrippage;
import com.example.angeo.newproject.sospage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kichu on 12-02-2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<String> contentid = new ArrayList<>();
    private List<String> contentname = new ArrayList<>();
    private List<String> imageurl = new ArrayList<>();
    private List<String> visiblestatus = new ArrayList<>();

    private int rowLayout;
    String PINN;
    int pos;
    String parameters;
    Activity activity1;

    public HomeAdapter(List<String> contentid,List<String> contentname,List<String> imageurl, List<String> visiblestatus, Activity activity, int rowLayout, Context context) {
        this.contentid = contentid;
        this.contentname = contentname;
        this.imageurl = imageurl;
        this.visiblestatus = visiblestatus;

         activity1 = activity;
        this.rowLayout = rowLayout;
        Context mContext = context;



    }

    public List<String> getStudentist() {
        return contentid;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new HomeAdapter.ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(final HomeAdapter.ViewHolder holder, final int position) {


//        holder.ContentName.setText(contentname.get(position));
        Glide.with(activity1).load(Constants.ImageUrl+imageurl.get(position)).into(holder.imgView);

        holder.Relatv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (contentname.get(position).equals("register trip")){

                    Intent intent=new Intent(activity1,Registertrippage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity1.startActivity(intent);

                }else if (contentname.get(position).equals("profile")){

                    Intent intent=new Intent(activity1,ProfilePage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity1.startActivity(intent);

                }else if (contentname.get(position).equals("make your own trip")){

                    Intent intent=new Intent(activity1,Registertrippage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity1.startActivity(intent);

                }else if (contentname.get(position).equals("sos")){

                    Intent intent=new Intent(activity1,sospage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity1.startActivity(intent);

                }else if (contentname.get(position).equals("nearby services")){

                    Intent intent=new Intent(activity1,MapsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity1.startActivity(intent);

                }else if (contentname.get(position).equals("forum")){

                    Intent intent=new Intent(activity1,sospage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity1.startActivity(intent);

                }else if (contentname.get(position).equals("user guide")){

                    Intent intent=new Intent(activity1,sospage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity1.startActivity(intent);

                }else if (contentname.get(position).equals("travelling kit")){

                    Intent intent=new Intent(activity1,sospage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity1.startActivity(intent);

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return contentid.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        int flag=0;
//        TextView ContentName,Cost,ItemName;
        ImageView imgView;
        RelativeLayout Relatv;


        public ViewHolder(View itemView) {
            super(itemView);

//            ContentName = itemView.findViewById(R.id.txtid);
            imgView = itemView.findViewById(R.id.imageviewid);
            Relatv = itemView.findViewById(R.id.layid);

        }

    }
}
