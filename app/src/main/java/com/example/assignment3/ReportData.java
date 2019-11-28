package com.example.assignment3;

import java.security.Principal;
import java.util.ArrayList;

public class ReportData {

   private int id;
   private String number="";
   private String date="";
   private String name="";
   private String gender="";
   private String shift="";
   private String department="";
   private String position="";
   private String incidentType="";
   private String incidentBodyParts="";



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getIncidentBodyParts() {
        return incidentBodyParts;
    }

    public void setIncidentBodyParts(String incidentBodyParts) {
        this.incidentBodyParts = incidentBodyParts;
    }

 public boolean checkData()
 {
     if(!number.equals("") && !name.equals("") && !gender.equals("") && !shift.equals("")
         && !department.equals("") && !position.equals("") && !incidentType.equals("") && !incidentBodyParts.equals(""))
     {
         return  true;
     }
     else {return false;}

 }

}
