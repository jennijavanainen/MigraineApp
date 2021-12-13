package fi.javanainen.migraineapp;



import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Jenni Javanainen
 */
public class MigraineList {
    private static final MigraineList ourInstance = new MigraineList();
    private ArrayList<Migraine> migraineList;
    private boolean activeMigraineExists;
    private Calendar c;


    public MigraineList() {
        migraineList = new ArrayList<>();

        // Testausta varten
        ArrayList<String> triggers = new ArrayList<>();
        triggers.add("aurinko");
        triggers.add("darra");
        ArrayList<String> symptoms = new ArrayList<>();
        symptoms.add("päänsärky");
        symptoms.add("oksetus");
        symptoms.add("neusea");
        symptoms.add("känkkäränkkä");
        ArrayList<String> medicines = new ArrayList<>();
        medicines.add("ibuprofeiini");
        medicines.add("miranax");
        ArrayList<String> treatments = new ArrayList<>();

        migraineList.add(new Migraine(triggers, new MigraineEvent(new Date(1, 12, 2021), new Time(11, 55), 5, symptoms, medicines, treatments )));
        migraineList.add(new Migraine(triggers, new MigraineEvent(new Date(2, 12, 2021), new Time(12, 45), 5, symptoms, medicines, treatments )));
        getLast().addEvent(new Date(3,12,2021), new Time(12,55), 5, symptoms, medicines, treatments);
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
     * Adds new Migraine to the Array
     * @param migraine Migraine
     */
    public void addMigraine(Migraine migraine) {
        migraineList.add(migraine);
    }

    /**
     * Returns the last Migraine in the Array
     * @return Migraine
     */
    public Migraine getLast() {
        return migraineList.get(migraineList.size() - 1);
    }

    public boolean getActiveMigraineExists() {
        return activeMigraineExists;
    }

    public ArrayList<Migraine> getMigraines(){
        return migraineList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getAvgInMinutes() {
        int minutes = 0;
        for (Migraine migraine: migraineList) {
            minutes += migraine.getLength();
        }
        return minutes;
    }

    public String toStringForm(int minutes) {
        int hours = minutes / 60;
        int min = minutes % 60;
        return hours + " h " + min + " min";
    }

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
