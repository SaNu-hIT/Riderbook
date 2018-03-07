package com.example.angeo.newproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class HomepagenewOne extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static int OVERLAY_PERMISSION_REQ_CODE_CHATHEAD = 1234;
    public static int OVERLAY_PERMISSION_REQ_CODE_CHATHEAD_MSG = 5678;
    ImageView sosbtn,registerbtn,registertripbtn,profilebtn,makeurtripbtn,nearbybtn,forumbtn,userguidebtn,traavelkitbtn,PoolSection;
    ImageView menubtn;
    WindowManager.LayoutParams   params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homepagenewone);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
        }else{
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);
        }


        if (android.os.Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {   //Android M Or Over
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE_CHATHEAD);
            return;
        }

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }
        SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
        editor1.putString("home","true");
        editor1.commit();
        if(Utils.canDrawOverlays(HomepagenewOne.this)) {
            startChatHead();
        }
        else{
            // requestPermission(OVERLAY_PERMISSION_REQ_CODE_CHATHEAD);
            Toast.makeText(HomepagenewOne.this,"permission not given",Toast.LENGTH_LONG).show();
        }






        sosbtn=(ImageView)findViewById(R.id.sosbtn);
        registerbtn=(ImageView)findViewById(R.id.registertripbtn);
        profilebtn=(ImageView)findViewById(R.id.profilebtn);
        makeurtripbtn=(ImageView)findViewById(R.id.makeurtripbtn);
        nearbybtn=(ImageView)findViewById(R.id.nearbybtn);
       // forumbtn=(ImageView)findViewById(R.id.forumbtn);
        userguidebtn=(ImageView)findViewById(R.id.userguidebtn);
        traavelkitbtn=(ImageView)findViewById(R.id.traavelkitbtn);
        PoolSection=(ImageView)findViewById(R.id.poolsection);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomepagenewOne.this,Registertrippage.class);
                startActivity(intent);
            }
        });

        sosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomepagenewOne.this,Sosactivity.class);
                startActivity(intent);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomepagenewOne.this,ProfilePage.class);
                startActivity(intent);
            }
        });
        nearbybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomepagenewOne.this,MapsActivity.class);
                startActivity(intent);
            }
        });


        nearbybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomepagenewOne.this,PoolSection_Activity.class);
                startActivity(intent);
            }
        });

           }

    private void startChatHead(){
        startService(new Intent(HomepagenewOne.this, ChatHeadService.class));
    }
    private void showChatHeadMsg(){
        java.util.Date now = new java.util.Date();
        String str = "Angeo" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);

        Intent it = new Intent(HomepagenewOne.this, ChatHeadService.class);
        it.putExtra(Utils.EXTRA_MSG, str);
        startService(it);
    }

    private void needPermissionDialog(final int requestCode){
        AlertDialog.Builder builder = new AlertDialog.Builder(HomepagenewOne.this);
        builder.setMessage("You need to allow permission");
        builder.setPositiveButton("OK",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        requestPermission(requestCode);
                    }
                });
        builder.setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        builder.setCancelable(false);
        builder.show();
    }



    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();


    }

    private void requestPermission(int requestCode){
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OVERLAY_PERMISSION_REQ_CODE_CHATHEAD) {
            if (!Utils.canDrawOverlays(HomepagenewOne.this)) {
                needPermissionDialog(requestCode);
            }else{
                startChatHead();
            }

        }else if(requestCode == OVERLAY_PERMISSION_REQ_CODE_CHATHEAD_MSG){
            if (!Utils.canDrawOverlays(HomepagenewOne.this)) {
                needPermissionDialog(requestCode);
            }else{
                showChatHeadMsg();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //Toast.makeText(this,Integer.toString(id),Toast.LENGTH_SHORT).show();
        if (id == R.id.nav_profile) {
            Toast.makeText(this,"profile",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_register) {

            Toast.makeText(this,"register",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_sos) {
            Toast.makeText(this,"sos",Toast.LENGTH_SHORT).show();


        }
        //else if (id == R.id.nav_forum) {
//
//            Toast.makeText(this,"forum",Toast.LENGTH_SHORT).show();
//
//        } else if (id == R.id.nav_userguide) {
//            Toast.makeText(this,"user guide",Toast.LENGTH_SHORT).show();
//
//
//        }
        else if (id == R.id.nav_pool) {
            Toast.makeText(this,"pool",Toast.LENGTH_SHORT).show();


        }
        else if (id == R.id.nav_settings) {
            Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show();


        }
        else if (id == R.id.nav_logout) {
            Toast.makeText(this,"logout",Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
