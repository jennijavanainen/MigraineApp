package fi.javanainen.migraineapp;

import java.util.ArrayList;
import java.util.Calendar;

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

    /**
     * Getter for Date
     * @return date
     */
    public Date getDate() { return date; }

    /**
     * Getter for Time
     * @return Time
     */
    public Time getTime() {
        return time;
    }

    /**
     * Getter for pain
     * @return Integer pain
     */
    public int getPain() {
        return pain;
    }

    /**
     * Getter for symptoms Array
     * @return List of symptoms
     */
    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    /**
     * Getter for medicines
     * @return List of medicines
     */
    public ArrayList<String> getMedicines() {
        return medicines;
    }

    /**
     * Getter for treatments
     * @return List of treatments
     */
    public ArrayList<String> getTreatments() {
        return treatments;
    }

    /**
     * Method creates a new Calendar object from the Event information and returns it.
     * Calendar object has information about the date and time.
     * @return Calendar
     */
    public Calendar getCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(date.getYear(), date.getMonth() - 1, date.getDay(), time.getHours(), time.getMinutes());
        return c;
    }
}
