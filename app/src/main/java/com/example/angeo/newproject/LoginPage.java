package com.example.angeo.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angeo.newproject.App.ApiClient;
import com.example.angeo.newproject.App.ApiInterface;
import com.example.angeo.newproject.Retrofit.LoginUser.LoginCode;
import com.example.angeo.newproject.Retrofit.LoginUser.LoginStatus;
import com.example.angeo.newproject.Retrofit.LoginUser.LoginUser;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginPage extends AppCompatActivity {
    TextView forgot,newacct;
    EditText username,password;
    String usr,pass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);
        forgot=(TextView)findViewById(R.id.forgotpass);
        newacct=(TextView)findViewById(R.id.newacct);
        username=(EditText)findViewById(R.id.username1);
        password=(EditText)findViewById(R.id.password1);
        login=(Button)findViewById(R.id.loginbtn);
        String forgotp="FORGOT PASSWORD?";
        String getnew="GET NEW!";
        forgot.setText(Html.fromHtml(forgotp+"<b>" + getnew + "</b>" ));
        String noacct="DON'T HAVE AN ACCOUNT?";
        String signup="SIGN UP!";
        newacct.setText(Html.fromHtml(noacct+"<b>" + signup + "</b>" ));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usr=username.getText().toString();
                pass=password.getText().toString();

                if((usr.isEmpty())||(pass.isEmpty())){
                    Toast.makeText(LoginPage.this,"Enter credentials!!",Toast.LENGTH_LONG).show();

                }
                else {

                    CreateRegister(usr,pass);


                }

            }
        });
        SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
        editor1.putString("Login","false");
        editor1.commit();

        newacct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPage.this,Registration.class);
                startActivity(intent);
            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPage.this,EnterOTP.class);
                startActivity(intent);
            }
        });

    }



    private void CreateRegister(String user,String pass) {


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<LoginUser> call=apiService.getlogin("login.php",user,pass);

        call.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, retrofit2.Response<LoginUser> response) {

                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);
//                ProgressBarrr.setVisibility(View.GONE);
                LoginStatus registerStatus=response.body().getStatus();
                String code=registerStatus.getCode();
                System.out.println("SHANIL : 1"+registerStatus);

                if (code.equals("200")){
                    System.out.println("SHANIL : 2");



                    LoginCode code1=response.body().getCode();
                    String Userid=code1.getUserid();
                    String ContactNumber=code1.getContact();

                    SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                    editor1.putString("Login","true");
                    editor1.putString("Userid","Userid");
                    editor1.commit();

                    Intent in=new Intent(getApplicationContext(),Homepagenewtwo.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    finish();


                }else if(code.equals("403")){

                    SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                    editor1.putString("Login","false");
                    editor1.commit();
                    Toast.makeText(LoginPage.this, "phone number already used...", Toast.LENGTH_SHORT).show();
                }else if (code.equals("204")){
                    SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                    editor1.putString("Login","false");
                    editor1.commit();
                    Toast.makeText(LoginPage.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                }else if (code.equals("402")){
                    SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                    editor1.putString("Login","false");
                    editor1.commit();
                    Toast.makeText(LoginPage.this, "username already used...", Toast.LENGTH_SHORT).show();

                }else if (code.equals("500")){
                    SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                    editor1.putString("Login","false");
                    editor1.commit();
                    Toast.makeText(LoginPage.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                SharedPreferences.Editor editor1 = getSharedPreferences("CampusWallet", MODE_PRIVATE).edit();
                editor1.putString("Login","false");
                editor1.commit();
                Log.i("SHANIL ","Call Failed"+call+ "  ");
                t.printStackTrace();
                Toast.makeText(LoginPage.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();
//                ProgressBarrr.setVisibility(View.GONE);
            }
        });

    }
}
