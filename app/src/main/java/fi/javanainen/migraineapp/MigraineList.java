package fi.javanainen.migraineapp;



import java.util.ArrayList;

/**
 *
 * @author Jenni Javanainen
 */
public class MigraineList {
    private static final MigraineList ourInstance = new MigraineList();
    private ArrayList<Migraine> migraineList;


    public MigraineList() {
        migraineList = new ArrayList<>();

        // Testausta varten
        ArrayList<String> triggers = new ArrayList<>();
        triggers.add("aurinko");
        triggers.add("darra");
        ArrayList<String> symptoms = new ArrayList<>();
        ArrayList<String> medicines = new ArrayList<>();
        ArrayList<String> treatments = new ArrayList<>();

        //migraineList.add(new Migraine(triggers, new MigraineEvent(new Date(1, 12, 2021), new Time(11, 55), 5, symptoms, medicines, treatments )));
        //migraineList.add(new Migraine(triggers, new MigraineEvent(new Date(2, 12, 2021), new Time(12, 45), 5, symptoms, medicines, treatments )));
        //getLast().addEvent(new Date(3,12,2021), new Time(12,55), 5, symptoms, medicines, treatments);
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

}
