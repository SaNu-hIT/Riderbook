package com.example.angeo.newproject;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.angeo.newproject.Adapters.HomeAdapter;
import com.example.angeo.newproject.App.ApiClient;
import com.example.angeo.newproject.App.ApiInterface;
import com.example.angeo.newproject.Retrofit.HomePage.HomeCode;
import com.example.angeo.newproject.Retrofit.HomePage.HomeModule;
import com.example.angeo.newproject.Retrofit.HomePage.HomeStatus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class Homepagenew extends AppCompatActivity {
ImageView sosbtn,registerbtn,Profile;


    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    ProgressBar ProgBar;
    Activity activity;

    private List<String> contentid = new ArrayList<>();
    private List<String> contentname = new ArrayList<>();
    private List<String> imageurl = new ArrayList<>();
    private List<String> visiblestatus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homepagenew);

        activity=this;
        mRecyclerView=(RecyclerView)findViewById(R.id.listviewid);
        ProgBar=(ProgressBar)findViewById(R.id.progressbar);

//        sosbtn=(ImageView)findViewById(R.id.sosbtn);
//        registerbtn=(ImageView)findViewById(R.id.registertripbtn);
//        Profile=(ImageView)findViewById(R.id.profilebtn);
//
//        registerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(HomepagenewOne.this,Registertrippage.class);
//                startActivity(intent);
//            }
//        });
//
//        sosbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(HomepagenewOne.this,sospage.class);
//                startActivity(intent);
//            }
//        });
//        Profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(HomepagenewOne.this,ProfilePage.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProgBar.setVisibility(View.VISIBLE);
        HomeList();
        SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
        editor1.putString("home","true");
        editor1.commit();
    }

    private void HomeList()
    {



        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<HomeModule> call=apiService.gethome("fetchmoduledetails.php");

        call.enqueue(new Callback<HomeModule>() {
            @Override
            public void onResponse(retrofit2.Call<HomeModule> call, Response<HomeModule> response) {

                ProgBar.setVisibility(GONE);
                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);
                HomeStatus status=response.body().getStatus();
                String code=status.getCode();
                if (code.equals("200")){

                    contentid.clear();
                    contentname.clear();
                    imageurl.clear();
                    visiblestatus.clear();


                    Log.i("shanil ", String.valueOf(response.body().getStatus()));
                    List<HomeCode> relationship=response.body().getCode();
                    if (relationship.size()>0) {
                        for (int i = 0; i < relationship.size(); i++) {

                            HomeCode relncode=relationship.get(i);
                            contentid.add(relncode.getContentid());
                            contentname.add(relncode.getContentname());
                            imageurl.add(relncode.getImageurl());
                            visiblestatus.add(relncode.getVisiblestatus());

                        }
                        try {
                            System.out.println("ArraySizeHere " + contentid.size());
                            if (contentid.size() > 0) {
                                // err.setVisibility(View.GONE);




                                mRecyclerView.setVisibility(View.VISIBLE);

                                mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
                                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                                mRecyclerView.setNestedScrollingEnabled(false);
                                mAdapter = new HomeAdapter(contentid,contentname,imageurl,visiblestatus, activity, R.layout.menu_listitem, getApplicationContext());
                                mRecyclerView.setAdapter(mAdapter);

                            } else {
                                mRecyclerView.setVisibility(GONE);

                            }
                        } catch (Exception e) // no guruji
                        {
                            System.out.println("ExceptiomHere "+e.toString());
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
            public void onFailure(retrofit2.Call<HomeModule> call, Throwable t) {
                ProgBar.setVisibility(GONE);
                mRecyclerView.setVisibility(GONE);

            }


        });

    }



}
