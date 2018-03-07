package com.example.angeo.newproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.angeo.newproject.PoolModule.DriverActivity;
import com.example.angeo.newproject.PoolModule.PassengerActivity;

public class Homepagenewtwo extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    public static int OVERLAY_PERMISSION_REQ_CODE_CHATHEAD = 1234;
    public static int OVERLAY_PERMISSION_REQ_CODE_CHATHEAD_MSG = 5678;
    WindowManager.LayoutParams   params;

    ImageView sosbtn,registerbtn,registertripbtn,profilebtn,makeurtripbtn,nearbybtn,forumbtn,userguidebtn,traavelkitbtn,PoolSection,courier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homepagenewtwo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        startService(new Intent(this, MyService.class));
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
//            params = new WindowManager.LayoutParams(
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.TYPE_PHONE,
//                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                    PixelFormat.TRANSLUCENT);
//        }else{
//            params = new WindowManager.LayoutParams(
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
//                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                    PixelFormat.TRANSLUCENT);
//        }

        if (android.os.Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {   //Android M Or Over
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE_CHATHEAD);
            return;
        }

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }


        invalidateOptionsMenu();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.inflateHeaderView(R.layout.navigationheader);
        ImageView imgvw = (ImageView)hView.findViewById(R.id.imageView);

        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
        editor1.putString("home","true");
        editor1.commit();
        if(Utils.canDrawOverlays(Homepagenewtwo.this)) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) { startChatHead();
        }
        else{
            // requestPermission(OVERLAY_PERMISSION_REQ_CODE_CHATHEAD);
            Toast.makeText(Homepagenewtwo.this,"permission not given",Toast.LENGTH_LONG).show();
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
        courier=(ImageView)findViewById(R.id.courierservice);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Homepagenewtwo.this,Registertrippage.class);
                startActivity(intent);
            }
        });

        sosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Homepagenewtwo.this,Sosactivity.class);
                startActivity(intent);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Homepagenewtwo.this,ProfilePage.class);
                startActivity(intent);
            }
        });
        nearbybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Homepagenewtwo.this,NearByActivity.class);
                startActivity(intent);
            }
        });


        PoolSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Homepagenewtwo.this,MainActivity.class);
                startActivity(intent);
            }
        });
        courier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Homepagenewtwo.this,"Coming Soon.....",Toast.LENGTH_LONG).show();
            }
        });
    }}

    private void startChatHead(){
        startService(new Intent(Homepagenewtwo.this, BubbleHeadService.class));
    }
    private void showChatHeadMsg(){
        java.util.Date now = new java.util.Date();
        String str = "Angeo" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);

        Intent it = new Intent(Homepagenewtwo.this, ChatHeadService.class);
        it.putExtra(Utils.EXTRA_MSG, str);
        startService(it);
    }

    private void needPermissionDialog(final int requestCode){
        AlertDialog.Builder builder = new AlertDialog.Builder(Homepagenewtwo.this);
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
            if (!Utils.canDrawOverlays(Homepagenewtwo.this)) {
                needPermissionDialog(requestCode);
            }else{
                startChatHead();
            }

        }else if(requestCode == OVERLAY_PERMISSION_REQ_CODE_CHATHEAD_MSG){
            if (!Utils.canDrawOverlays(Homepagenewtwo.this)) {
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
            Intent intent=new Intent(Homepagenewtwo.this,ProfilePage.class);
            startActivity(intent);
        } else if (id == R.id.nav_register) {

            Intent intent=new Intent(Homepagenewtwo.this,Registertrippage.class);
            startActivity(intent);
        } else if (id == R.id.nav_sos) {
            Intent intent=new Intent(Homepagenewtwo.this,Sosactivity.class);
            startActivity(intent);

        }
//        else if (id == R.id.nav_forum) {
//
//            Toast.makeText(this,"forum",Toast.LENGTH_SHORT).show();
//
//        } else if (id == R.id.nav_userguide) {
//            Toast.makeText(this,"user guide",Toast.LENGTH_SHORT).show();
//
//
//        }
        else if (id == R.id.nav_pool) {
            Intent intent=new Intent(Homepagenewtwo.this,MainActivity.class);
            startActivity(intent);

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