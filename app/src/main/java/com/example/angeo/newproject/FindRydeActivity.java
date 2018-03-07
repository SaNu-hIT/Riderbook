package com.example.angeo.newproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.angeo.newproject.App.ApiClient;
import com.example.angeo.newproject.App.ApiInterface;
import com.example.angeo.newproject.Retrofit.FindRyde.FindRydeCode;
import com.example.angeo.newproject.Retrofit.FindRyde.FindRydeModule;
import com.example.angeo.newproject.Retrofit.FindRyde.FindRydeStatus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

/**
 * Created by techxform on 26-Feb-18.
 */

public class FindRydeActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private FindRydeAdapter mAdapter;
    ProgressBar ProgBar;
    Activity activity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findride_layout);

        mRecyclerView=(RecyclerView)findViewById(R.id.listview);
        activity=this;
        ProgBar=(ProgressBar)findViewById(R.id.progressbar);


    }

    @Override
    protected void onResume() {
        super.onResume();
        HomeList();
    }

    private void HomeList()
    {



        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<FindRydeModule> call=apiService.getfindryde("listpooldetails.php");

        call.enqueue(new Callback<FindRydeModule>() {
            @Override
            public void onResponse(retrofit2.Call<FindRydeModule> call, Response<FindRydeModule> response) {

                ProgBar.setVisibility(GONE);
                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);
                FindRydeStatus status=response.body().getStatus();
                String code=status.getCode();
                if (code.equals("200")){

                     name.clear();
                    contact.clear();
                    startlat.clear();
                   startlon.clear();
                   destlat.clear();
                    destlon.clear();
                    offerdate.clear();
                   offertime.clear();
                   bikemodel.clear();
                   logdate.clear();
                   logtime.clear();
                   regno.clear();
                   seats.clear();
                   vehicle.clear();
                   features.clear();


                    Log.i("shanil ", String.valueOf(response.body().getStatus()));
                    List<FindRydeCode> relationship=response.body().getCode();
                    if (relationship.size()>0) {
                        for (int i = 0; i < relationship.size(); i++) {

                            FindRydeCode relncode=relationship.get(i);
                            name.add(relncode.getName());
                            contact.add(relncode.getContact());
                            startlat.add(relncode.getStartlat());
                            startlon.add(relncode.getStartlon());
                            destlat.add(relncode.getDestlat());
                            destlon.add(relncode.getDestlon());
                            startlon.add(relncode.getStartlon());
                            offerdate.add(relncode.getOfferdate());
                            offertime.add(relncode.getOffertime());
                            bikemodel.add(relncode.getBikemodel());
                            logdate.add(relncode.getLogdate());
                            logtime.add(relncode.getLogtime());
                            regno.add(relncode.getRegno());
                            seats.add(relncode.getSeats());
                            vehicle.add(relncode.getVehicle());
                            features.add(relncode.getFeatures());


                        }
                        try {
                            System.out.println("ArraySizeHere " + name.size());
                            if (name.size() > 0) {
                                // err.setVisibility(View.GONE);




                                mRecyclerView.setVisibility(View.VISIBLE);

                                mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                                mRecyclerView.setNestedScrollingEnabled(false);
                                mAdapter = new FindRydeAdapter(name,contact,startlat,startlon,destlat,destlon,
                                offerdate,
                                offertime,
                                bikemodel,
                                logdate,
                                logtime,
                                regno,
                                seats,
                                vehicle,
                                features
                            , activity, R.layout.menu_listitempool, getApplicationContext());
                                mRecyclerView.setAdapter(mAdapter);

                            } else {
                                mRecyclerView.setVisibility(GONE);

                            }
                        } catch (Exception e) // no guruji
                        {
                            System.out.println("Exception Here "+e.toString());
                            e.printStackTrace();

                        }
                    }else {
                        mRecyclerView.setVisibility(GONE);
                    }

                }else if(code.equals("400")){
                    mRecyclerView.setVisibility(GONE);

                }else if (code.equals("204")){
                    mRecyclerView.setVisibility(GONE);

                }else if (code.equals("401")){
                    mRecyclerView.setVisibility(GONE);


                }else if (code.equals("500")){
                    mRecyclerView.setVisibility(GONE);


                }

            }

            @Override
            public void onFailure(retrofit2.Call<FindRydeModule> call, Throwable t) {
                ProgBar.setVisibility(GONE);
                mRecyclerView.setVisibility(GONE);

            }


        });

    }




}
