package fi.javanainen.migraineapp;

/**
 * Class represents a single migraine attack.
 * A migraine must have two or more events and it can have triggers.
 * @author Jenni Javanainen
 */

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Calendar;

public class Migraine {

    private ArrayList<String> triggers;
    private ArrayList<MigraineEvent> events;


    public Migraine(ArrayList<String> triggers, MigraineEvent event) {
        this.triggers = triggers;
        this.events = new ArrayList<>();
        events.add(event);
    }

    /**
     * Method creates and adds new MigraineEvent in Array
     * @param date date of event
     * @param time time of event
     * @param pain pain in int
     * @param symptoms list of symptoms
     * @param medicines list of medicines
     * @param treatments list of treatments
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

    /**
     * Method compares the first and last Event in Array and returns the difference
     * @return Time the difference in hours and minutes
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Time getLength() {
        Calendar first = events.get(0).getCalendar();
        Calendar last = events.get(events.size() - 1).getCalendar();
        long millis1 = last.getTimeInMillis();
        long millis2 = first.getTimeInMillis();
        long diff = Math.abs(millis2 - millis1);
        int hours = Math.toIntExact(diff / (60 * 60 * 1000));
        int minutes = Math.toIntExact(diff / (60 * 1000) % 60);

        return new Time(hours, minutes);
    }

    /**
     * Method returns the last MigraineEvent in Array
     * @return MigraineEvent last MigraineEvent
     */
    public MigraineEvent getLastEvent() {
        return events.get(events.size() - 1);
    }
}
