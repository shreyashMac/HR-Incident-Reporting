package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
     DBHandler myhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myhandler = new DBHandler(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId())
       {
           case R.id.reportIncident:

               Intent reportIntent = new Intent(MainActivity.this,ReportIncident.class);
               startActivity(reportIntent);
               return  true;

           case R.id.viewIncident:

               Intent viewIntent = new Intent(MainActivity.this,ViewIncident.class);
               startActivity(viewIntent);
               return true;

            default:

                return super.onOptionsItemSelected(item);


       }




    }
}
