package fi.javanainen.migraineapp;

/**
 * Class represents a single migraine attack.
 * A migraine must have two or more events and it can have triggers.
 * @author Jenni Javanainen
 */

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.ArrayList;

@Entity(tableName = "migraine_data")
public class Migraine {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String date;
    private String time;
    private String treatment;
    private String trigger;
    private int pain;

    public Migraine(String date, String time, String treatment, String trigger) {
        this.date = date;
        this.time = time;
        this.treatment = treatment;
        this.trigger = trigger;
        this.pain = pain;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getTrigger() {
        return trigger;
    }
    public int getPain() {
        return pain;
    }




    private ArrayList<String> triggers;
    private ArrayList<MigraineEvent> events;


    public Migraine(ArrayList<String> triggers, MigraineEvent event) {
        this.triggers = triggers;
        this.events = new ArrayList<>();
        events.add(event);
    }

    /**
     *
     * @param date
     * @param time
     * @param pain
     */
    public void addEvent(Date date, Time time, int pain, ArrayList<String> symptoms, ArrayList<String> medicines, ArrayList<String> treatments) {
        events.add(new MigraineEvent(date, time, pain, symptoms, medicines, treatments));
    }

    public ArrayList<String> getTriggers() {
        return triggers;
    }

    public ArrayList<MigraineEvent> getEvents() {
        return events;
    }
}
