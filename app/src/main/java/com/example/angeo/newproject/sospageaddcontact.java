package com.example.angeo.newproject;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.angeo.newproject.Adapters.Contacts_list_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class sospageaddcontact extends AppCompatActivity {

    LinearLayout AddContact;
    ListView listviewcontacts;
    Contacts_list_adapter mAdapter;
    private static final int RESULT_PICK_CONTACT = 65535;
    public static final int R_PERM = 123;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    public static int PERMISSION_SEND_SMS = 123;

    DatabaseHandler db = new DatabaseHandler(this);
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sospage_addcontact);

        activity=this;


        listviewcontacts=(ListView) findViewById(R.id.listviewid);
        AddContact=(LinearLayout)findViewById(R.id.addcontactbtn1);

        AddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pickContact(view);

            }
        });



    }

    public boolean CheckPermission(Context context, String Permission) {
        if (ContextCompat.checkSelfPermission(context,
                Permission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    public void pickContact(View v)
    {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }
    /**
     * Query the Uri and read contact details. Handle the picked contact data.
     * @param data
     */
    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null ;
            String name = null;
            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            // Set the value to the textviews
//            textView1.setText(name);
//            textView2.setText(phoneNo);
            System.out.println("SHANIL CONTACTS : "+name);
            System.out.println("SHANIL CONTACTS2 : "+phoneNo);

            List<String> dynamicText2Elements;
            List<String> staticText1Elements;
            dynamicText2Elements = new ArrayList<String>();
            staticText1Elements = new ArrayList<String>();
            dynamicText2Elements.add(phoneNo);
            staticText1Elements.add(name);


            db.addContact(new Contact(name, phoneNo));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element;
        List<Contact> contacts = db.getAllContacts();
        System.out.println("SHNAIL CONTACTS AAA: "+contacts);
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            System.out.println("SHNAIL CONTACTS AAAA: "+log);
            element = new HashMap<String, String>();
            element.put("text1", (cn.getName()));
            element.put("text2", cn.getPhoneNumber());
            dataList.add(element);
        }
//            int length = (staticText1Elements).size();
//            System.out.println("LENGTH TOAST  :: "+contacts.size());
        for (int i = 0; i < dataList.size(); i++) {

            ListAdapter myAdapter = new SimpleAdapter(this, dataList, android.R.layout.simple_expandable_list_item_2, new String[]{"text1", "text2"}, new int[]{android.R.id.text1, android.R.id.text2});
            listviewcontacts.setAdapter(myAdapter);

        }

        }




    public void sendSMS() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("8129043228", null, "SHANIL KICHU", null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

}
