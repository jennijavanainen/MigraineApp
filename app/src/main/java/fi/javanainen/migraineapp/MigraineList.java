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
    }

    /**
     *
     * @return
     */
    public static MigraineList getInstance() {
        return ourInstance;
    }

    public void addMigraine(Migraine migraine) {
        migraineList.add(migraine);
    }

    public Migraine returnLast() {
        return migraineList.get(migraineList.size() - 1);
    }

    public Migraine returnFirst() {
        return migraineList.get(0);
    }


}
