package fi.javanainen.migraineapp;

/**
 * Class represents a date (day/month/year)
 * @author Jenni Javanainen
 */

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Getter for day
     * @return Integer day
     */
    public int getDay() {
        return day;
    }

    /**
     * Getter for month
     * @return Integer month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getter for year
     * @return Integer year
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for day
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Setter for month
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Setter for year
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the date in text form
     * @return String Date in text form
     */
    @Override
    public String toString() {
        return this.day + "." + this.month + "." + this.year;
    }
}
