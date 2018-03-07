package com.example.angeo.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * Created by Kichu on 10-02-2018.
 */

public class ProfilePage extends AppCompatActivity {

    Button SubmButton;
    ProgressBar ProgressBarrr;
    ImageView imgIcon;
    private static int RESULT_LOAD_IMAGE = 1;
    RelativeLayout WalletLay,EditProfLay,MessagesLay,SettingsLay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        WalletLay = (RelativeLayout) findViewById(R.id.walletlay);
        SettingsLay = (RelativeLayout) findViewById(R.id.settingslay);
        EditProfLay = (RelativeLayout) findViewById(R.id.editprofilelay);
        MessagesLay = (RelativeLayout) findViewById(R.id.messagelay);
        SubmButton = (Button) findViewById(R.id.logout);

        WalletLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        MessagesLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        SettingsLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        EditProfLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getApplicationContext(), ProfileEdit.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();



    }

}
