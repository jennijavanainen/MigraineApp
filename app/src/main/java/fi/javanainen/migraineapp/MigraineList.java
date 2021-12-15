package fi.javanainen.migraineapp;



import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Singleton class holding a list of Migraines
 * @author Jenni Javanainen
 */
public class MigraineList {
    private static final MigraineList ourInstance = new MigraineList();
    private ArrayList<Migraine> migraineList;
    private boolean activeMigraineExists;
    private Calendar c;
    private Gson gson;


    public MigraineList() {
        migraineList = new ArrayList<>();

        ArrayList<String> triggers = new ArrayList<>();
        ArrayList<String> symptoms = new ArrayList<>();
        ArrayList<String> medicines = new ArrayList<>();
        ArrayList<String> treatments = new ArrayList<>();
        gson = new Gson();

        c = Calendar.getInstance();
    }

    /**
     * returns the MigraineList singleton
     * @return MigraineList
     */
    public static MigraineList getInstance() {
        return ourInstance;
    }

    /**
     * Converts the List Object into json String
     * @return String list of Migraines in json form
     */
    public String listToJson() {
        String jsonList = gson.toJson(migraineList);
        return jsonList;
    }

    /**
     * Method adds all the Migraines in the list to the migraineList Singleton
     * @param list of Migraines
     */
    public void addMigrainesToList(ArrayList<Migraine> list) {
        for (Migraine migraine: list
             ) {
            migraineList.add(migraine);
        }
    }

    /**
     * Adds new Migraine to the Array
     * @param migraine Migraine
     */
    public void addMigraine(Migraine migraine) {
        migraineList.add(migraine);
    }

    /**
     * Returns the n:th Migraine in the Array
     * @param index int
     * @return Migraine
     */
    public Migraine getMigraine(int index){
        return migraineList.get(index);
    }

    /**
     * Returns the last Migraine in the Array
     * @return Migraine
     */
    public Migraine getLast() {
        return migraineList.get(migraineList.size() - 1);
    }

    /**
     * Getter for activeMigraineExists
     * @return Boolean
     */
    public boolean getActiveMigraineExists() {
        return activeMigraineExists;
    }

    /**
     * Setter for activeMigraineExists
     * @param a
     */
    public void setActiveMigraineExists(boolean a) { this.activeMigraineExists = a; }

    /**
     * Returns a list of Migraines
     * @return list of Migraines
     */
    public ArrayList<Migraine> getMigraines() {
        return migraineList;
    }

    /**
     * Counts and returns the average Migraine length in minutes
     * @return Integer minutes
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getAvgInMinutes() {
        int minutes = 0;
        for (Migraine migraine: migraineList) {
            minutes += migraine.getLength();
        }
        return minutes;
    }

    /**
     * Returns given parameter converted into hours and minutes
     * @param minutes Integer
     * @return String minutes in hours and minutes
     */
    public String toStringForm(int minutes) {
        int hours = minutes / 60;
        int min = minutes % 60;
        return hours + " h " + min + " min";
    }

    /**
     * Counts and returns how many Migraines are found on the last counting back from this moment.
     * The period of time in days is given as parametet.
     * @param period Integer days
     * @return Integer number of Migraines
     */
    public int howManyMigraines(int period) {
        int migraineNumber = 0;
        long todayMillis = c.getTimeInMillis();
        long pastMillis = todayMillis -  ((long) period * 24 * 60 * 60 * 1000);
        for (Migraine migraine: migraineList) {
            long comparable = migraine.getFirstEvent().getCalendar().getTimeInMillis();
            if (comparable >= pastMillis) {
                migraineNumber++;
            }
        }
        return migraineNumber;
    }





}
