package com.example.angeo.newproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Kichu on 29-12-2017.
 */

public class SplashScreenFirst extends Activity {
    private static final int TIME = 1 * 3000;// 4 seconds
    Boolean isInternetPresent = false;

    String version;

    // Connection detector class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                PackageManager manager = getApplicationContext().getPackageManager();
                PackageInfo info = null;
                try {
                    info = manager.getPackageInfo(
                            getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                version = info.versionName;

                System.out.println("Version code : "+version);

                SharedPreferences sharedpreferences = getSharedPreferences("CampusWallet", Context.MODE_PRIVATE);
                String HomeFlag =sharedpreferences.getString("home", "");
                String IntroFlag =sharedpreferences.getString("IntroActivity", "");
                String LoginFlag =sharedpreferences.getString("Login", "");
                String RegisterFlag =sharedpreferences.getString("Register", "");



                        if (HomeFlag.equals("true")){

                            Intent intent = new Intent(SplashScreenFirst.this, Homepagenewtwo.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }else if (IntroFlag.equals("false")){

                            Intent intent = new Intent(SplashScreenFirst.this, IntroActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }else if (IntroFlag.equals("true")){

                            Intent intent = new Intent(SplashScreenFirst.this, IntroActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }else if (LoginFlag.equals("false")){

                            Intent intent = new Intent(SplashScreenFirst.this, LoginPage.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }else if (RegisterFlag.equals("false")){

                            Intent intent = new Intent(SplashScreenFirst.this, Registration.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }else {

                            Intent intent = new Intent(SplashScreenFirst.this, IntroActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }


            }
        }, TIME);

    }



    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

}
