package com.example.angeo.newproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class EnterOTP extends AppCompatActivity {
TextView one,two,three,four,five;
TextView six,seven,eight,nine,zero;
String getotp;
TextView enterotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enter_otp);
        enterotp=(TextView)findViewById(R.id.otpenter);
        one=(TextView)findViewById(R.id.one);
        two=(TextView)findViewById(R.id.two);
        three=(TextView)findViewById(R.id.three);
        four=(TextView)findViewById(R.id.four);
        five=(TextView)findViewById(R.id.five);
        six=(TextView)findViewById(R.id.six);
        seven=(TextView)findViewById(R.id.seven);
        eight=(TextView)findViewById(R.id.eight);
        nine=(TextView)findViewById(R.id.nine);
        zero=(TextView)findViewById(R.id.zero);


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendnum("1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendnum("2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendnum("3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendnum("4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendnum("5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendnum("6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendnum("7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendnum("8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendnum("9");
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendnum("0");
            }
        });



    }

    public void appendnum(String n){
        int count=0;
getotp=enterotp.getText().toString();

        for (int i = 0, len = getotp.length(); i < len; i++) {
            if (Character.isDigit(getotp.charAt(i))) {
                count++;
            }
        }

        if(count==0){
            enterotp.setText(n+" - - -");
        }else if(count==1){
            String otpnew=getotp.substring(0,1);
            enterotp.setText(otpnew+" "+n+" - -");
        }
        else if(count==2){
            String otpnew=getotp.substring(0,3);
            enterotp.setText(otpnew+" "+n+" -");
        }
        else if(count==3){
            String otpnew=getotp.substring(0,5);
            enterotp.setText(otpnew+" "+n);
            Toast.makeText(EnterOTP.this,"create password page",Toast.LENGTH_LONG).show();
        }
        else
        {}
    }
}
