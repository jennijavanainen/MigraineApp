package fi.javanainen.migraineapp;

/**
 * Class represents single event in a migraine attack.
 * An event must have date, time and pain measure.
 * It can also have event tag and other descibing attributes
 * @author Jenni Javanainen
 */

public class MigraineEvent {
    private Date date;
    private Time time;
    private int pain;
    private EventTag tag;

    public MigraineEvent(Date date, Time time, int pain, EventTag tag) {
        this.date = date;
        this.time = time;
        this.pain = pain;
        this.tag = tag;
    }




}
