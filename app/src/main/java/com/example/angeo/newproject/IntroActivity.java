package com.example.angeo.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.cuneytayyildiz.onboarder.OnboarderActivity;
import com.cuneytayyildiz.onboarder.OnboarderPage;

import java.util.Arrays;
import java.util.List;

public class IntroActivity extends OnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        List<OnboarderPage> pages = Arrays.asList(


                new OnboarderPage.Builder()
                        .title("")
                        .description("")
                        .imageResourceId( R.drawable.a_soss)
                        .backgroundColor(R.color.red1)
                        .titleColor(R.color.colorPrimaryDark)
                        .descriptionColor(R.color.colorAccent)
                        .multilineDescriptionCentered(true)
                        .build(),

                new OnboarderPage.Builder()
                        .title("")
                        .description("")
                        .imageResourceId( R.drawable.b_nearby)
                        .backgroundColor(R.color.red1)
                        .titleColor(R.color.colorPrimaryDark)
                        .descriptionColor(R.color.colorAccent)
                        .multilineDescriptionCentered(true)
                        .build(),

                new OnboarderPage.Builder()
                        .title("")
                        .description("")
                        .imageResourceId( R.drawable.c_bikecarpool)
                        .backgroundColor(R.color.red1)
                        .titleColor(R.color.colorPrimaryDark)
                        .descriptionColor(R.color.colorAccent)
                        .multilineDescriptionCentered(true)
                        .build(),

                new OnboarderPage.Builder()
                        .title("")
                        .description("")
                        .imageResourceId( R.drawable.d_news)
                        .backgroundColor(R.color.red1)
                        .titleColor(R.color.colorPrimaryDark)
                        .descriptionColor(R.color.colorAccent)
                        .multilineDescriptionCentered(true)
                        .build(),

                new OnboarderPage.Builder()
                        .title("")
                        .description("")
                        .imageResourceId( R.drawable.e_courier)
                        .backgroundColor(R.color.red1)
                        .titleColor(R.color.colorPrimaryDark)
                        .descriptionColor(R.color.colorAccent)
                        .multilineDescriptionCentered(true)
                        .build(),

                new OnboarderPage.Builder()
                        .title("")
                        .description("")
                        .imageResourceId( R.drawable.f_homestayy)
                        .backgroundColor(R.color.red1)
                        .titleColor(R.color.colorPrimaryDark)
                        .descriptionColor(R.color.colorAccent)
                        .multilineDescriptionCentered(true)
                        .build(),

                new OnboarderPage.Builder()
                        .title("")
                        .description("")
                        .imageResourceId( R.drawable.g_packagee)
                        .backgroundColor(R.color.red1)
                        .titleColor(R.color.colorPrimaryDark)
                        .descriptionColor(R.color.colorAccent)
                        .multilineDescriptionCentered(true)
                        .build()


                );

        setSkipButtonTitle(getString(R.string.button_skip));
        setFinishButtonTitle(getString(R.string.button_finish));

        initOnboardingPages(pages);
        SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
        editor1.putString("IntroActivity","false");
        editor1.commit();

    }

    @Override
    public void onSkipButtonPressed() {
        super.onSkipButtonPressed();

        SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
        editor1.putString("IntroActivity","true");
        editor1.commit();

        Intent in=new Intent(getApplicationContext(),LoginPage.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
        finish();

    }

    @Override
    public void onFinishButtonPressed() {

        SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
        editor1.putString("IntroActivity","true");
        editor1.commit();

        Intent in=new Intent(getApplicationContext(),LoginPage.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
        finish();
    }
}


