package fi.javanainen.migraineapp;

/**
 * Class represents a time in 24-hour form (hours/minutes)
 * @author Jenni Javanainen
 */

public class Time {
    private int hours;
    private  int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    /**
     * Getter for hours
     * @return Integer hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * Setter for hours
     * @param hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    /**
     * Getter for minutes
     * @return Integer minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Setter for minutes
     * @param minutes
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * Returns the time in text form
     * @return String time in text form
     */
    @Override
    public String toString() {
        return this.hours + "." + this.minutes;
    }
}
