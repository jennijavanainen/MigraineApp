package fi.javanainen.migraineapp;

import java.util.ArrayList;

public class AttributeList {
    private static final AttributeList ourInstance = new AttributeList();
    private ArrayList<String> triggers;
    private ArrayList<String> symptoms;
    private ArrayList<String> medicines;
    private ArrayList<String> treatments;

    public AttributeList() {
        triggers = new ArrayList<>();
        symptoms = new ArrayList<>();
        medicines = new ArrayList<>();
        treatments = new ArrayList<>();
    }

    /**
     * returns the Attributes singleton
     * @return Attributes
     */
    public static AttributeList getInstance() {
        return ourInstance;
    }

    public void addTrigger(String trigger) {
        triggers.add(trigger);
    }

    public void addSymptom(String symptom) {
        symptoms.add(symptom);
    }

    public void addMedicine(String medicine) {
        medicines.add(medicine);
    }

    public void addTreatment(String treatment) {
        treatments.add(treatment);
    }

    public ArrayList<String> getTriggers() {
        return triggers;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public ArrayList<String> getMedicines() {
        return medicines;
    }

    public ArrayList<String> getTreatments() {
        return treatments;
    }

}
