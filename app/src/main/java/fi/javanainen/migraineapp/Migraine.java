package fi.javanainen.migraineapp;

/**
 * Class represents a single migraine attack.
 * A migraine must have two or more events and it can have triggers.
 * @author Jenni Javanainen
 */

import java.util.ArrayList;

public class Migraine {
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
