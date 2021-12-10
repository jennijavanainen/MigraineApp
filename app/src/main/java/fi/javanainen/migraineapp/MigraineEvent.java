package fi.javanainen.migraineapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class represents single event in a migraine attack.
 * An event must have date, time and pain measure.
 * It can also have describing attributes
 * @author Jenni Javanainen
 */
@Entity(tableName = "migraine_data")
public class MigraineEvent {

    @PrimaryKey(autoGenerate = true)
    public int id;

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
    public void setId(int id) {
        this.id = id;
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
