package com.example.angeo.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.angeo.newproject.App.ApiClient;
import com.example.angeo.newproject.App.ApiInterface;
import com.example.angeo.newproject.Retrofit.RegisterUser.RegisterStatus;
import com.example.angeo.newproject.Retrofit.RegisterUser.RegisterUserModule;

import retrofit2.Call;
import retrofit2.Callback;

public class Registration extends AppCompatActivity {
Button btn;

    EditText NameEdt,LastName,EmailEdt,PasswordEdt,PhoneEdt;

    EditText GenderSpinner;
    String GenderString;
    ProgressBar ProgressBarrr;

    String[] GENDER = {"MALE", "FEMALE", "OTHER"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        btn=(Button) findViewById(R.id.regbutton);
        NameEdt=(EditText) findViewById(R.id.nameedt);
        LastName=(EditText) findViewById(R.id.lastnameedt);
        EmailEdt=(EditText) findViewById(R.id.emailedt);
        PasswordEdt=(EditText) findViewById(R.id.passwordedt);
        PhoneEdt=(EditText) findViewById(R.id.phoneedt);
        GenderSpinner=(EditText) findViewById(R.id.genderedt);

        ProgressBarrr=(ProgressBar) findViewById(R.id.probar);


    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (NameEdt.getText().toString().equals("")||LastName.getText().toString().equals("")||
                    EmailEdt.getText().toString().equals("")||PasswordEdt.getText().toString().equals("")||PhoneEdt.getText().toString().equals(""))
            {

                Toast.makeText(Registration.this, "Please fill all the fields to countinue", Toast.LENGTH_SHORT).show();

            }else {
                ProgressBarrr.setVisibility(View.VISIBLE);
                CreateRegister();

            }


        }
    });
        SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
        editor1.putString("Register","false");
        editor1.commit();
    }

    private void CreateRegister() {


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<RegisterUserModule> call=apiService.getregister("registernewuser.php",NameEdt.getText().toString(),LastName.getText().toString(),GenderSpinner.getText().toString(),EmailEdt.getText().toString(),PasswordEdt.getText().toString(),PhoneEdt.getText().toString());

        call.enqueue(new Callback<RegisterUserModule>() {
            @Override
            public void onResponse(Call<RegisterUserModule> call, retrofit2.Response<RegisterUserModule> response) {

                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);
                ProgressBarrr.setVisibility(View.GONE);
                RegisterStatus registerStatus=response.body().getStatus();
                String code=registerStatus.getCode();
                System.out.println("SHANIL : 1"+registerStatus);

                if (code.equals("200")){
                    System.out.println("SHANIL : 2");
                    Toast.makeText(Registration.this, "Successfully Registered...", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                    editor1.putString("Register","true");
                    editor1.commit();
                    Intent in=new Intent(getApplicationContext(),Homepagenewtwo.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    finish();


                }else if(code.equals("403")){
                    SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                    editor1.putString("Register","false");
                    editor1.commit();
                    Toast.makeText(Registration.this, "phone number already used...", Toast.LENGTH_SHORT).show();
                }else if (code.equals("204")){
                    SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                    editor1.putString("Register","false");
                    editor1.commit();
                    Toast.makeText(Registration.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                }else if (code.equals("402")){
                    SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                    editor1.putString("Register","false");
                    editor1.commit();
                    Toast.makeText(Registration.this, "username already used...", Toast.LENGTH_SHORT).show();

                }else if (code.equals("500")){
                    SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                    editor1.putString("Register","false");
                    editor1.commit();
                    Toast.makeText(Registration.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RegisterUserModule> call, Throwable t) {
                SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                editor1.putString("Register","false");
                editor1.commit();
                Log.i("SHANIL ","Call Failed"+call+ "  ");
                t.printStackTrace();
                Toast.makeText(Registration.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();
                ProgressBarrr.setVisibility(View.GONE);
            }
        });

    }
}
