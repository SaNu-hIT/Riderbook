package com.example.angeo.newproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.angeo.newproject.App.ApiClient;
import com.example.angeo.newproject.App.ApiInterface;
import com.example.angeo.newproject.Retrofit.CreateRydeBike.BikePool;
import com.example.angeo.newproject.Retrofit.CreateRydeBike.BikePoolStatus;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Kichu on 25-02-2018.
 */

public class OfferRide_Activity extends AppCompatActivity {


    EditText RegEdt,CategryEdt,Featuresedt,PriceEdt,SeatEdt;
    Button ConfirmBtn;
    Spinner PoolSelection;
    String PoolString,StartLati,StartLongi,DestLati,DestiLongi,OfferDate,OfferTime;

    double startlati,startlongi,destlati,destlongi;
    EditText DateEdt,TimeEdt;
    Calendar myCalendar = Calendar.getInstance();
    PlaceAutocompleteFragment places;
    PlaceAutocompleteFragment places2;

    String Startname,Destination;
    RadioGroup rdGroup;
    RadioButton offerbtn,findbtn;
    String RideScheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_layout);

        RegEdt=(EditText)findViewById(R.id.regid);
        CategryEdt=(EditText)findViewById(R.id.catid);
        Featuresedt=(EditText)findViewById(R.id.features);
        PriceEdt=(EditText)findViewById(R.id.price);
        SeatEdt=(EditText)findViewById(R.id.seats);
        DateEdt=(EditText)findViewById(R.id.date);
        TimeEdt=(EditText)findViewById(R.id.time);
        offerbtn=(RadioButton) findViewById(R.id.offer);
        rdGroup=(RadioGroup) findViewById(R.id.group);
        findbtn=(RadioButton) findViewById(R.id.findride);

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (offerbtn.isChecked()){

                    RideScheme="1";

                }else {

                    RideScheme="0";

                }


            }
        });

        PoolSelection=(Spinner) findViewById(R.id.spinner);
        ConfirmBtn=(Button)findViewById(R.id.confirm);

         places= (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        places.setHint("Starting LocationClass");

         places2= (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);
        places2.setHint("Destination");
        places.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Toast.makeText(getApplicationContext(),place.getName(),Toast.LENGTH_SHORT).show();
                LatLng queriedLocation = place.getLatLng();
                 startlati=queriedLocation.latitude;
                 startlongi=queriedLocation.longitude;
                System.out.println("SHANIL : places2  ::::: "+startlati+"         "+startlongi);
                Startname=place.getName().toString();
                Destination=place.getName().toString();
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });

       places2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Toast.makeText(getApplicationContext(),place.getName(),Toast.LENGTH_SHORT).show();
                LatLng queriedLocation = place.getLatLng();
                 destlati=queriedLocation.latitude;
                 destlongi=queriedLocation.longitude;
                System.out.println("SHANIL : places  ::::: "+destlati+"         "+destlongi);
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();

        places.setFilter(typeFilter);

        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Car");
        list.add("Bike");

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PoolSelection.setAdapter(adapter);

        PoolSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                PoolString=PoolSelection.getSelectedItem().toString();
                if (PoolString.equals("Car")){



                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        DateEdt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(OfferRide_Activity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TimeEdt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               /* Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                int ampm = mcurrentTime.get(Calendar.AM_PM);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(OfferRide_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        TimeEdt.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
*/

                timeformat();
            }
        });

        ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PoolString.equals("")||RegEdt.getText().toString().equals("")||DateEdt.getText().toString().equals("")||TimeEdt.getText().toString().equals("")||CategryEdt.getText().toString().equals("")||
                        Featuresedt.getText().toString().equals("")||PriceEdt.getText().toString().equals("")||
                        SeatEdt.getText().toString().equals("")){

                    Toast.makeText(OfferRide_Activity.this, "Please Fill All The Fields To Countinue...", Toast.LENGTH_SHORT).show();

                }else {

                    CreateRyde();

                }


            }
        });

    }

    private  void timeformat(){

        TimePickerDialog mTimePicker;

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        mTimePicker = new TimePickerDialog(OfferRide_Activity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {



                String time = selectedHour + ":" + selectedMinute;

                SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
                Date date = null;
                try {
                    date = fmt.parse(time );
                } catch (ParseException e) {

                    e.printStackTrace();
                }

                SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa");

                String formattedTime=fmtOut.format(date);

                TimeEdt.setText(formattedTime);
            }
        }, hour, minute, false);//No 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        DateEdt.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                System.out.println( "Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                System.out.println( status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private void CreateRyde() {


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        StartLati= String.valueOf(startlati);
        StartLongi= String.valueOf(startlongi);
        DestLati= String.valueOf(destlati);
        DestiLongi= String.valueOf(destlongi);

        Call<BikePool> call=apiService.getbikepool("enterpoolbikedetails.php","1",CategryEdt.getText().toString(),
                RegEdt.getText().toString(),StartLati,StartLongi,DestLati,DestiLongi,DateEdt.getText().toString(),TimeEdt.getText().toString(),PriceEdt.getText().toString());

        call.enqueue(new Callback<BikePool>() {
            @Override
            public void onResponse(Call<BikePool> call, retrofit2.Response<BikePool> response) {

                String URL=call.request().url().toString();
                System.out.println("Retrofit URL : "+URL);
//                ProgressBarrr.setVisibility(View.GONE);
                BikePoolStatus registerStatus=response.body().getStatus();
                String code=registerStatus.getCode();
                System.out.println("SHANIL : 1"+registerStatus);

                if (code.equals("200")){
                    System.out.println("SHANIL : 2");
                    Toast.makeText(OfferRide_Activity.this, "Successfully Created...", Toast.LENGTH_SHORT).show();

                    Intent in=new Intent(getApplicationContext(),RouteMap.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bn=new Bundle();
                    bn.putString("StartLati",StartLati);
                    bn.putString("StartLongi",StartLongi);
                    bn.putString("DestLati",DestLati);
                    bn.putString("DestiLongi",DestiLongi);
                    bn.putString("StartLocation",Startname);
                    bn.putString("Destination",Destination);
                    in.putExtras(bn);
                    startActivity(in);

                }else if(code.equals("403")){

                    Toast.makeText(OfferRide_Activity.this, "phone number already used...", Toast.LENGTH_SHORT).show();
                }else if (code.equals("204")){

                    Toast.makeText(OfferRide_Activity.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                }else if (code.equals("402")){

                    Toast.makeText(OfferRide_Activity.this, "username already used...", Toast.LENGTH_SHORT).show();

                }else if (code.equals("500")){

                    Toast.makeText(OfferRide_Activity.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<BikePool> call, Throwable t) {

                Log.i("SHANIL ","Call Failed"+call+ "  ");
                t.printStackTrace();
                Toast.makeText(OfferRide_Activity.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();
//                ProgressBarrr.setVisibility(View.GONE);
            }
        });

    }

}
