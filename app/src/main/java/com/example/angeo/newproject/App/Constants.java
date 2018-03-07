package com.example.angeo.newproject.App;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Constants {

    public static final String StudentUrl = "";
    public static String PhoneNumber="";
    public static int Qty,Id;
    public static String UserId;
    public static String UserFullName;
    public static String UserPassword;
    public static String UserEmail;
    public static String StudentId="16";
    public static ArrayList<String> Items= new ArrayList<>();
    public static ArrayList<String> preorderItemTypeTimingItemIDnew = new ArrayList<>();
    public static ArrayList<String> Quantity= new ArrayList<>();
    public static ArrayList<String> Amount= new ArrayList<>();
    public static ArrayList<String> PriceList= new ArrayList<>();
    public static String TotalAmount;

//    public static String AppUrl="http://192.168.0.223/campuswallet/parent_app/api_1.4.3/";
//    public static String ImageUrl="http://192.168.0.223/campuswallettest/uploads/";
//    public static final String AppUrl="http://campuswallet.chillarcards.com/api/";
//    public static final String AppUrl="http://campuswallet.chillarcards.com/parent_app/api_1.4/";
//    public static final String AppUrl="http://campuswallet.chillarcards.com/parent_app/api_1.4.2/";
//    public static final String AppUrl="http://192.168.0.223/campuswallet/parent_app/api_1.4.5/";
      public static final String AppUrl="https://campuswallet.chillarcards.com/parent_app/api_2.5/";
//    public static final String AppUrl="http://campuswalletdev.chillarcards.com/parent_app/api_2.4.1/";
//    public static final String AppUrl="http://campuswallet.chillarcards.com/api_test/";
//    public static final String ImageUrl="http://campuswalletdev.chillarcards.com/uploads/";
      public static final String  ImageUrl="http://campuswallet.chillarcards.com/uploads/";
//    public static final String ImageUrlgallery="http://chillar/campuswallettest/uploads/gallery/";
      public static final String NewsLetterImage="http://campuswallet.chillarcards.com/uploads/newsletters/";
//    public static final String NewsLetterImage="http://campuswalletdev.chillarcards.com/uploads/newsletters/";
      public static final String AppUrlPayments="http://campuswallet.chillarcards.com/payments/";
      public static String createdBy;
      public static Date d;

    public static String TWITTER_KEY="8ytUX5FEM5jMKEmiPHHXbH5VR";
    public static final String TWITTER_SECRET = "HPyovtHwxJ7jc42P2upx4VfwQ2l59RdFGE9x3WtQu4fyxk71sQ";

    public static String userPhone="";

    public static String Phone="";
    public static String HomePopup;
    public static String HomeFlag="false";
    public static String HomePopupTitle;
    public static String RelativeFlag="";
    public static String uriImage="";
    public static long uriImageSize;

    public static File TAKEN_IMAGE_PATH=new File("/storage/sdcard0/temp1.jpg");
    public static String daySelected;

    public static void SnackbarDesign(CoordinatorLayout layout, String msg){

        Snackbar snackbar = Snackbar
                .make(layout, msg, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    //ID CARD....................................
    public static String SchoolID;
    public static String StdID;
    public static String StdDivID;
    public static String Std;
    public static String division;
    public static String StudentCode;
    public static String StudentName;
    public static String ParentName="";
    public static String ParentEmail="";
    public static String onesignal_imageurl="";
    public static String onesignal_weburl="";
    public static String onesignal_chillarAddsID="";
    public static String Phoneno="";
    public static String Address="";
    public static int IDFLAG=0;
//    public static int NotificationCount;
    public static int LOGFLAG=0;
    public static int IDNUMBERFLAG=0;


}
