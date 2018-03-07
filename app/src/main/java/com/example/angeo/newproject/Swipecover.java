package com.example.angeo.newproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Swipecover extends AppCompatActivity {
ImageView img1;
    int flag=1;
    TextView tvnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main);
//        img1=(ImageView) findViewById(R.id.changecover);
//        img1.setBackgroundResource(R.drawable.change1);
//        tvnew=(TextView) findViewById(R.id.gettext);
tvnew.setVisibility(View.INVISIBLE);
tvnew.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(Swipecover.this,Socialmedialogin.class);
        startActivity(intent);
    }
});
        img1.setOnTouchListener(new OnSwipeTouchListener(Swipecover.this) {
            public void onSwipeTop() {
                //Toast.makeText(DetailsActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                if(flag==1){
//                    flag=1;
//                    img1.setBackgroundResource(R.drawable.img2);
                }
                else if(flag==2){
                    flag=1;
                    tvnew.setVisibility(View.INVISIBLE);

                    img1.setBackgroundResource(R.drawable.change1);
                }
                else if(flag==3){
                    flag=2;
                    tvnew.setVisibility(View.INVISIBLE);

                    img1.setBackgroundResource(R.drawable.change2);
                }
                else if(flag==4){
                    flag=3;
                    tvnew.setVisibility(View.INVISIBLE);

                    img1.setBackgroundResource(R.drawable.change3);
                }

            }
            public void onSwipeLeft() {
                //Toast.makeText(DetailsActivity.this, "left", Toast.LENGTH_SHORT).show();
                if(flag==1){
                    flag=2;
                    tvnew.setVisibility(View.INVISIBLE);

                    img1.setBackgroundResource(R.drawable.change2);
                }
                else if(flag==2){
                    flag=3;
                    tvnew.setVisibility(View.INVISIBLE);

                    img1.setBackgroundResource(R.drawable.change3);
                }
                else if(flag==3){
                    flag=4;
                    tvnew.setVisibility(View.VISIBLE);

                    img1.setBackgroundResource(R.drawable.change4);
                }
                else if(flag==4){
//                    flag=2;
//                    img1.setBackgroundResource(R.drawable.img3);
                }





            }
            public void onSwipeBottom() {
                //Toast.makeText(DetailsActivity.this, "bottom", Toast.LENGTH_SHORT).show();

            }

        });
    }
}
