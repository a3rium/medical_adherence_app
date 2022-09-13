package com.example.medicationadherencesystem;

import java.util.Calendar;
import java.util.Date;

public class Medication {
    private String name;
    private String description;
    private Date expectedTime;
    private Date timeTakem;
    private int dosage;
    private Database database;

    public Medication() {
    }

    public Medication(String name, String desc, Date expectedTime, int dosage, Database database) {
        this.setName(name);
        this.setDescription(desc);
        this.setExpectedTime(expectedTime);
        this.setDosage(dosage);
        this.database = database;
    }

    public boolean checkValidTime(Date timeTaken) {
        Calendar cal = Calendar.getInstance();
        Date dateNow = new Date();
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        Date pastDate = cal.getTime();

        if (timeTaken.after(dateNow)) {
            return false;
        }
        else if (timeTaken.before(pastDate)) {
            return false;
        }
        return  true;
    }

    public void addMedication() {
        database.addToMedication(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setExpectedTime(Date expectedTime) {
        this.expectedTime = expectedTime;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }
}
