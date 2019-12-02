package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class ReportIncident extends AppCompatActivity {

    EditText myId, myDate, myNumber, myName, myDepartment, myPosition;

    RadioButton rbMale, rbFemale;

    Spinner spinShift, spinInciType, spinBodypart;

    Button btnReport;

    ImageButton search;
   // ReportData mydata;

    List<String> myShifts,myIncidentTypes,myBodyParts;

    DBHandler reportHandler;

    ArrayAdapter myShiftAdapter,myInciTypeAdapter,myBodyPartsAdapter;

    SimpleDateFormat myInciDate;

    ReportData myRpData;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incident);

        myId = findViewById(R.id.edID);
        myDate = findViewById(R.id.eddate);
        myNumber = findViewById(R.id.ednumber);
        myName = findViewById(R.id.edname);
        myDepartment = findViewById(R.id.eddepartment);
        myPosition = findViewById(R.id.edPosition);

        rbMale = findViewById(R.id.rbmale);
        rbFemale = findViewById(R.id.rbfemale);

        spinShift = findViewById(R.id.spinshift);
        spinInciType = findViewById(R.id.spinIncident);
        spinBodypart = findViewById(R.id.spinInjured);

        btnReport = findViewById(R.id.report);
        search = findViewById(R.id.search);

       myShifts=Arrays.asList(getResources().getStringArray(R.array.shifts));
       myIncidentTypes=Arrays.asList(getResources().getStringArray(R.array.IncidentType));
        myBodyParts = new ArrayList<>();
       reportHandler = new DBHandler(ReportIncident.this);


      myRpData= new ReportData();

        fetchBodyParts();



      myShiftAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,myShifts);
      myShiftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinShift.setAdapter(myShiftAdapter);

      myInciTypeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,myIncidentTypes);
      myInciTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinInciType.setAdapter(myInciTypeAdapter);

      myBodyPartsAdapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item,myBodyParts);
      myBodyPartsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinBodypart.setAdapter(myBodyPartsAdapter);

      myInciDate = new SimpleDateFormat("dd MMM yyyy");
     // myDate.setText(myInciDate.format(new Date()));


      myRpData.setDate(myInciDate.format(new Date()));
      myDate.setText(myRpData.getDate());

      search.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              myRpData.setNumber(myNumber.getText().toString().trim());

                    Cursor mycursor = reportHandler.getEmployee(myRpData.getNumber());

                   // StringBuffer myBuffer = new StringBuffer();
                    while(mycursor.moveToNext())
                    {
                     /* myBuffer.append(mycursor.getString(0));
                      myBuffer.append(mycursor.getString(1));
                      myBuffer.append(mycursor.getString(2));
                      myBuffer.append(mycursor.getString(3));
                      myBuffer.append(mycursor.getString(4));*/

                     myRpData.setName(mycursor.getString(1));
                     myRpData.setDepartment(mycursor.getString(3));
                     myRpData.setPosition(mycursor.getString(4));
                    }

                    myName.setText(myRpData.getName());
                    myDepartment.setText(myRpData.getDepartment());
                    myPosition.setText(myRpData.getPosition());

                   // Toast.makeText(ReportIncident.this,myBuffer.toString(),Toast.LENGTH_LONG).show();



          }
      });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check;

                check = myRpData.checkData();
                if(check == true)
                {
                    boolean flag = reportHandler.saveReport(myRpData);
                    if(flag == true)
                    {
                        Bitmap b = Screenshot.takescreenshotOfRootView(getWindow().getDecorView().getRootView());
                        
//                        imageView.setImageBitmap(b);
//                        main.setBackgroundColor(Color.parseColor("#999999"));
                        Toast.makeText(ReportIncident.this,"Report Generated Successfully",Toast.LENGTH_LONG).show();
                    }
                    else
                        {
                            Toast.makeText(ReportIncident.this,"ERROR While Generating",Toast.LENGTH_LONG).show();
                        }
                }

                else {
                    Toast.makeText(ReportIncident.this,"Please Fill Empty Fields",Toast.LENGTH_LONG).show();

                }






            }
        });

        spinShift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                myRpData.setShift(adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                myRpData.setShift("Nothing Selected");

            }
        });

        spinInciType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                myRpData.setIncidentType(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                myRpData.setIncidentType("Nothing Selected");
            }
        });

        spinBodypart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                myRpData.setIncidentBodyParts(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







    }

    public void getGender(View v)
    {


           // String mycredit;
            if(rbMale.isChecked())
            {
                myRpData.setGender("Male");


            }
            else if(rbFemale.isChecked())
            {
                myRpData.setGender("Female");

            }
    }

    public void fetchBodyParts(){

        Cursor cursor = reportHandler.getBodyParts();
        /*while(cursor.moveToNext())
        {

            myBodyParts.add(cursor.getString(0));

        }*/

       if(cursor.moveToFirst())
       {

           while (!cursor.isAfterLast())
           {
               String name = cursor.getString(cursor.getColumnIndex("NAME"));
               myBodyParts.add(name);
               cursor.moveToNext();
           }
       }
    }

}
