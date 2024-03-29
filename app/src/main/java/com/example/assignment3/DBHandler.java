package com.example.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "HR_Incident.db";
    public static final String TABLE_INCI_HISTORY = "tbl_IncidentHistory";

    public static final String TABLE_BodyParts = "tbl_BodyParts";

    public static final String TABLE_Employee = "tbl_Employee";



    // sqlite column name
    public static final String ID = "ID";
    public static final String DATE = "DATE";
    public static final String NUMBER = "NUMBER";
    public static final String NAME = "NAME";
    public static final String GENDER = "GENDER";
    public static final String SHIFT = "SHIFT";
    public static final String DEPARTMENT = "DEPARTMENT";
    public static final String POSITION = "POSITION";
    public static final String INCITYPE = "INCITYPE";

    public static final String INCIBODYPARTS = "INCIBODYPARTS";
    //SQLiteDatabase db =this.getWritableDatabase();
    DBHandler(Context context)
    {
        super(context,DB_NAME,null,1);
        //SQLiteOpenHelper sqLiteOpenHelper =this.getWritableDatabase();
       //
        //
        // SQLiteDatabase db =this.getWritableDatabase();

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

      //  db= this.getWritableDatabase();
        createBodyParts(db);
        createEmployee(db);

        String CREATE_TABLE = ("Create table " + TABLE_INCI_HISTORY + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,DATE TEXT," +
                "NUMBER TEXT," +
                "NAME TEXT," +
                "GENDER TEXT," +
                "SHIFT TEXT,"+
                "DEPARTMENT TEXT,"+
                "POSITION TEXT,"+
                "INCITYPE TEXT,"+
                "INCIBODYPARTS TEXT)");
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_BodyParts");
        onCreate(sqLiteDatabase);
    }

    public void createBodyParts(SQLiteDatabase db)
    {

        String bodyParts = ("Create table " + TABLE_BodyParts + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT)");
        db.execSQL(bodyParts);
        bodyRecord(db);
        //Log.i("dd","dfdddddf");

    }

    public void createEmployee(SQLiteDatabase db)
    {
        String employee = ("Create table " + TABLE_Employee + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT," +
                " NUMBER TEXT,DEPARTMENT TEXT,POSITION TEXT)");
        db.execSQL(employee);
        empData(db);

    }

    public void bodyRecord(SQLiteDatabase mydb) {
        List<String> myBodyParts = new ArrayList<>();
        myBodyParts.add("Ankle-left");
        myBodyParts.add("Ankle-right");
        myBodyParts.add("Arm-Both");
        myBodyParts.add("Arm-Left Upper");


        // mydb = this.getWritableDatabase();
        ContentValues myvalues = new ContentValues();

        for (int i = 0; i < myBodyParts.size(); i++) {
            myvalues.put(NAME, myBodyParts.get(i));
            mydb.insert(TABLE_BodyParts, null, myvalues);
        }

    }

    public void empData(SQLiteDatabase mydb) {
       /* List<String> empList = new ArrayList<>();
        empList.add("Shreyash");
        empList.add("Sparsh");
        empList.add("Hardik");
        empList.add("Meet");

        ContentValues myvalues = new ContentValues();
        for (int i = 0; i < empList.size(); i++) {
            myvalues.put(NAME, empList.get(i));
            mydb.insert(TABLE_Employee, null, myvalues);

        }
        List<String> empNumber = new ArrayList<>();
        empList.add("EMP101");
        empList.add("EMP102");
        empList.add("EMP103");
        empList.add("EMP104");
        for (int i = 0; i < empNumber.size(); i++) {
            myvalues.put(NUMBER, empNumber.get(i));
            mydb.insert(TABLE_Employee, null, myvalues);

        }

        List<String> empPosition = new ArrayList<>();
        empList.add("Software Developer");
        empList.add("Software Developer");
        empList.add("Tester");
        empList.add("Technician");
        for (int i = 0; i < empNumber.size(); i++) {
            myvalues.put(DEPARTMENT, empPosition.get(i));
            mydb.insert(TABLE_Employee, null, myvalues);

        }*/

        ContentValues myvalues = new ContentValues();
        //storing all user values in  ContentValues object
        myvalues.put(NAME, "Shreyash");
        //  myvalues.put(ID, id);
        myvalues.put(NUMBER, "EMP101");
        myvalues.put(DEPARTMENT, "HR");
        myvalues.put(POSITION, "Coder");
        //myvalues.put(, course);


        mydb.insert(TABLE_Employee, null, myvalues);




    }

      public Cursor getBodyParts()
      {
          SQLiteDatabase db = this.getWritableDatabase();

          //select query
          Cursor cursor = db.rawQuery("select  * FROM " + TABLE_BodyParts, null);
          return cursor;

        //return cursor;
      }

      public Cursor getEmployee(String number)
      {
          //Table columns Array
          String[] columns = {ID,NAME,NUMBER,DEPARTMENT,POSITION};

          SQLiteDatabase db = this.getWritableDatabase();

          //query database using specific Course.
          Cursor cursor = db.query(TABLE_Employee,columns,"NUMBER = ?",new String[]{number},null,null,null);
          return cursor;

      }


      public  boolean  saveReport(ReportData rpd)
      {

        // ReportData rpd = new ReportData();
         SQLiteDatabase mydb = this.getWritableDatabase();
         ContentValues myvalues = new ContentValues();
   //      String a = rpd.getName();
         myvalues.put(DATE,rpd.getDate());
         myvalues.put(NUMBER ,rpd.getNumber());
         myvalues.put(NAME,rpd.getName());
         myvalues.put(GENDER,rpd.getGender());
         myvalues.put(SHIFT,rpd.getShift());
         myvalues.put(DEPARTMENT,rpd.getDepartment());
         myvalues.put(POSITION,rpd.getPosition());
         myvalues.put(INCITYPE,rpd.getIncidentType());
         myvalues.put(INCIBODYPARTS,rpd.getIncidentBodyParts());


        long result = mydb.insert(TABLE_INCI_HISTORY,null,myvalues);
        if(result == -1)
        {
           return false;
        }

        else
        {
            return true;
        }
      }

    public Cursor showRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("select  * FROM " + TABLE_INCI_HISTORY, null);

        return cr;

    }
}
