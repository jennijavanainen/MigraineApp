package fi.javanainen.migraineapp;

import java.util.ArrayList;

/**
 * Class represents single event in a migraine attack.
 * An event must have date, time and pain measure.
 * It can also have describing attributes
 * @author Jenni Javanainen
 */

public class MigraineEvent {
    private Date date;
    private Time time;
    private int pain;
    private ArrayList<String> symptoms;
    private ArrayList<String> medicines;
    private ArrayList<String> treatments;


    public MigraineEvent(Date date, Time time, int pain, ArrayList<String> symptoms, ArrayList<String> medicines, ArrayList<String> treatments) {
        this.date = date;
        this.time = time;
        this.pain = pain;
        this.symptoms = symptoms;
        this.medicines = medicines;
        this.treatments = treatments;
    }


    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getPain() {
        return pain;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public ArrayList<String> getMedicines() {
        return medicines;
    }

    public ArrayList<String> getTreatments() {
        return treatments;
    }
}
