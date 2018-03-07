package com.example.angeo.newproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;


import static android.view.Gravity.CENTER;
import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;

public class Sosactivity extends AppCompatActivity {
    ImageView togbtn;
    LinearLayout layout1,EditContact;
    ProgressDialog dialog;
    Handler handler;
    EditText msgcontent;
    DatabaseHandler db = new DatabaseHandler(this);
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosactivity);

        togbtn=(ImageView) findViewById(R.id.sosbtn1);
        layout1=(LinearLayout) findViewById(R.id.toglayout);
        EditContact=(LinearLayout) findViewById(R.id.editcontact);
        msgcontent=(EditText) findViewById(R.id.msgcontent);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // request permission (see result in onRequestPermissionsResult() method)
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    1);
        }

        EditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in=new Intent(getApplicationContext(),sospageaddcontact.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);

            }
        });

        layout1.setOnTouchListener(new OnSwipeTouchListener(Sosactivity.this) {
            public void onSwipeTop() {
                //Toast.makeText(DetailsActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                //Toast.makeText(DetailsActivity.this, "right", Toast.LENGTH_SHORT).show();
                TranslateAnimation anim = new TranslateAnimation(-200, 0, 0, 0); //first 0 is start point, 150 is end point horizontal
                anim.setDuration(500); // 1000 ms = 1second
                togbtn.startAnimation(anim);
                handler=new Handler();
                layout1.setGravity(RIGHT);

                final ProgressDialog dialog = new ProgressDialog(Sosactivity.this);
                dialog.setMessage("Sending message..");
                dialog.show();

                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        layout1.setGravity(CENTER);
                    }
                };
                handler.postDelayed(runnable,1000);


            }
            public void onSwipeLeft() {
                //Toast.makeText(DetailsActivity.this, "left", Toast.LENGTH_SHORT).show();
                TranslateAnimation anim = new TranslateAnimation(200, 0, 0, 0); //first 0 is start point, 150 is end point horizontal
                anim.setDuration(500); // 1000 ms = 1second
                togbtn.startAnimation(anim);
                handler=new Handler();
                layout1.setGravity(LEFT);

                final ProgressDialog dialog = new ProgressDialog(Sosactivity.this);
                dialog.setMessage("Sending message..");
                dialog.show();

                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        layout1.setGravity(CENTER);
                    }
                };
                handler.postDelayed(runnable,1000);




            }
            public void onSwipeBottom() {
                //Toast.makeText(DetailsActivity.this, "bottom", Toast.LENGTH_SHORT).show();

            }

        });


        togbtn.setOnTouchListener(new OnSwipeTouchListener(Sosactivity.this) {
            public void onSwipeTop() {
                //Toast.makeText(DetailsActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                //Toast.makeText(DetailsActivity.this, "right", Toast.LENGTH_SHORT).show();
                TranslateAnimation anim = new TranslateAnimation(-200, 0, 0, 0); //first 0 is start point, 150 is end point horizontal
                anim.setDuration(500); // 1000 ms = 1second
                togbtn.startAnimation(anim);
                handler=new Handler();
                layout1.setGravity(RIGHT);

                List<Contact> contacts = db.getAllContacts();
                System.out.println("SHNAIL CONTACTS AAA: "+contacts);
                for (Contact cn : contacts) {
                    String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                    // Writing Contacts to log
                    System.out.println("SHNAIL CONTACTS AAAA: "+log);
                    final ProgressDialog dialog = new ProgressDialog(Sosactivity.this);
                    dialog.setMessage("Sending message..");
                    dialog.show();
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(cn.getPhoneNumber(), null,msgcontent.getText().toString() , null, null);
                        Toast.makeText(getApplicationContext(), "Message Sent",
                                Toast.LENGTH_LONG).show();
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                        ex.printStackTrace();
                    }
                    // sendMySMS();
                    Runnable runnable=new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            layout1.setGravity(CENTER);
                        }
                    };
                    handler.postDelayed(runnable,1000);

                    // layout1.setGravity(RIGHT);

                }



            }
            public void onSwipeLeft() {
                //Toast.makeText(DetailsActivity.this, "left", Toast.LENGTH_SHORT).show();
//                TranslateAnimation anim = new TranslateAnimation(200, 0, 0, 0); //first 0 is start point, 150 is end point horizontal
//                anim.setDuration(1000); // 1000 ms = 1second
//                togbtn.startAnimation(anim);
//                layout1.setGravity(LEFT);

                TranslateAnimation anim = new TranslateAnimation(200, 0, 0, 0); //first 0 is start point, 150 is end point horizontal
                anim.setDuration(500); // 1000 ms = 1second
                togbtn.startAnimation(anim);
                handler=new Handler();
                layout1.setGravity(LEFT);

                final ProgressDialog dialog = new ProgressDialog(Sosactivity.this);
                dialog.setMessage("Sending message..");
                dialog.show();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:7012217859"));
                intent.putExtra("sms_body",msgcontent.getText().toString() );
                startActivity(intent);
               // sendMySMS();
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        layout1.setGravity(CENTER);
                    }
                };
                handler.postDelayed(runnable,1000);



            }
            public void onSwipeBottom() {
                //Toast.makeText(DetailsActivity.this, "bottom", Toast.LENGTH_SHORT).show();

            }

        });






    }

    public void sendMySMS() {

        String phone = "7012217859";
        String message = msgcontent.getText().toString();

        //Check if the phoneNumber is empty
//        if (phone.isEmpty()) {
//            Toast.makeText(getApplicationContext(), "Please Enter a Valid Phone Number", Toast.LENGTH_SHORT).show();
//        } else {

            SmsManager sms = SmsManager.getDefault();
            // if message length is too long messages are divided
            List<String> messages = sms.divideMessage(message);
            for (String msg : messages) {

                PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0);
                PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);
                sms.sendTextMessage(phone, null, msg, sentIntent, deliveredIntent);

//            }
        }
    }
}
