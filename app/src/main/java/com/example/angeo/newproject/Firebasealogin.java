package com.example.angeo.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

/**
 * Created by techxform on 04-Mar-18.
 */

public class Firebasealogin extends AppCompatActivity {

    EditText Edtlog;
    Button loginBtn;

    private final static int LOGIN_PERMISSION=10000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain_layout);

        Edtlog=(EditText)findViewById(R.id.edtid);
        loginBtn=(Button)findViewById(R.id.btnid);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivityForResult(
                        AuthUI.getInstance().createSignInIntentBuilder().setAllowNewEmailAccounts(true).build(),LOGIN_PERMISSION);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==LOGIN_PERMISSION){

            startNewActivity(resultCode,data);

        }

    }

    private void startNewActivity(int resultCode, Intent data) {

        if (resultCode==RESULT_OK){

            Intent in=new Intent(getApplicationContext(),ListOnline.class);
            startActivity(in);
            finish();

        }
        else {

            Toast.makeText(this, "Login Failed...", Toast.LENGTH_SHORT).show();

        }

    }
}
