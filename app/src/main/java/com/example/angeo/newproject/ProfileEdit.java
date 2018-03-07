package com.example.angeo.newproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.angeo.newproject.App.ApiClient;
import com.example.angeo.newproject.App.ApiInterface;
import com.example.angeo.newproject.Retrofit.RegisterUser.RegisterStatus;
import com.example.angeo.newproject.Retrofit.RegisterUser.RegisterUserModule;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Kichu on 11-02-2018.
 */

public class ProfileEdit extends AppCompatActivity {

    EditText NameEdt, EmailEdt, PhoneEdt, CityEdt, StateEdt, PinEdt;
    Button EditButton;
    ProgressBar ProgressBarrr;
    ImageView imgIcon;

    private static final String TAG = "SampleActivity";
    boolean mStartedActivityFromFragment;
    private static final int REQUEST_SELECT_PICTURE = 0x01;
    private static final String SAMPLE_CROPPED_IMAGE_NAME = "SampleCropImage";
    private static int RESULT_LOAD_IMAGE = 1;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiledit_layout);

        NameEdt = (EditText) findViewById(R.id.nameedt);
        PhoneEdt = (EditText) findViewById(R.id.phoneedt);
        EmailEdt = (EditText) findViewById(R.id.emailedt);
        EditButton = (Button) findViewById(R.id.submitbtn);
        CityEdt = (EditText) findViewById(R.id.cityedt);
        StateEdt = (EditText) findViewById(R.id.statedt);
        PinEdt = (EditText) findViewById(R.id.pinedt);
        imgIcon = (ImageView) findViewById(R.id.imageid);

        ProgressBarrr = (ProgressBar) findViewById(R.id.progbar);

        EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditButton.setText("UPDATE");
                NameEdt.setEnabled(true);
                EmailEdt.setEnabled(true);

                if (EditButton.getText().toString().equals("UPDATE")) {

                    Toast.makeText(ProfileEdit.this, "Successfully Updated...", Toast.LENGTH_SHORT).show();

                }


            }
        });

        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
//        UpdatePhp();

    }

/*

    private void UpdatePhp() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<RegisterUserModule> call=apiService.getregister("profileupdate.php",NameEdt.getText().toString(),LastName.getText().toString(),GenderString,PhoneEdt.getText().toString(),PasswordEdt.getText().toString(),EmailEdt.getText().toString());

        call.enqueue(new Callback<RegisterUserModule>() {
            @Override
            public void onResponse(Call<RegisterUserModule> call, retrofit2.Response<RegisterUserModule> response) {

                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);
                ProgressBarrr.setVisibility(View.GONE);
                RegisterStatus registerStatus=response.body().getStatus();

                if (registerStatus.equals("200")){

                    Toast.makeText(ProfileEdit.this, "Successfully Registered...", Toast.LENGTH_SHORT).show();

                    Intent in=new Intent(getApplicationContext(),HomepagenewOne.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    finish();


                }else if(registerStatus.equals("400")){

                    Toast.makeText(ProfileEdit.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();
                }else if (registerStatus.equals("204")){

                    Toast.makeText(ProfileEdit.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                }else if (registerStatus.equals("401")){

                    Toast.makeText(ProfileEdit.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                }else if (registerStatus.equals("500")){

                    Toast.makeText(ProfileEdit.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RegisterUserModule> call, Throwable t) {

                Log.i("SHANIL ","Call Failed"+call+ "  ");
                t.printStackTrace();
                Toast.makeText(ProfileEdit.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();
                ProgressBarrr.setVisibility(View.GONE);
            }
        });


    }
*/


}
