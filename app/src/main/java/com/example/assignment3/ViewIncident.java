package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewIncident extends AppCompatActivity {
    ArrayList incis = new ArrayList();
    DBHandler db = new DBHandler(ViewIncident.this);
    ListView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_incident);
        Cursor cr = db.showRecords();
        while(cr.moveToNext()){
            incis.add(cr.getString(0));
        }
        content = findViewById(R.id.allCOntent);
        ArrayAdapter<String> progAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, incis);//creating array adapter for size
        content.setAdapter(progAdapter);
    }
}
