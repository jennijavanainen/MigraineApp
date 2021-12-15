package fi.javanainen.migraineapp;

import java.util.ArrayList;

/**
 * Singleton class that contains four ArrayLists that hold the information about user's personal Migraine attributes.
 * @author Jenni Javanainen
 */

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
     * Returns the Attributes singleton.
     * @return Attributes
     */
    public static AttributeList getInstance() {
        return ourInstance;
    }


    /**
     * Adds new String to triggers-Array
     * @param trigger String
     */
    public void addTrigger(String trigger) {
        triggers.add(trigger);
    }

    /**
     * Adds new String to symptoms-Array
     * @param symptom String
     */
    public void addSymptom(String symptom) {
        symptoms.add(symptom);
    }

    /**
     * Adds new String to medicines-Array
     * @param medicine String
     */
    public void addMedicine(String medicine) {
        medicines.add(medicine);
    }

    /**
     * Adds new String to treatments-Array
     * @param treatment String
     */
    public void addTreatment(String treatment) {
        treatments.add(treatment);
    }

    /**
     * Getter for triggers
     * @return triggers
     */
    public ArrayList<String> getTriggers() {
        return triggers;
    }

    /**
     * Getter for symptoms
     * @return symptoms
     */
    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    /**
     * Getter for medicines
     * @return medicines
     */
    public ArrayList<String> getMedicines() {
        return medicines;
    }

    /**
     * Getter for treatments
     * @return treatments
     */
    public ArrayList<String> getTreatments() {
        return treatments;
    }

    /**
     * Removes String parameter from triggers
     * @param attribute attribute
     */
    public void removeTrigger(String attribute) {
        triggers.remove(attribute);
    }

    /**
     * Removes String parameter from symptoms
     * @param attribute attribute
     */
    public void removeSymptom(String attribute) {
        symptoms.remove(attribute);
    }

    /**
     * Removes String parameter from medicines
     * @param attribute attribute
     */
    public void removeMedicine(String attribute) {
        medicines.remove(attribute);
    }

    /**
     * Removes String parameter from treatments
     * @param attribute attribute
     */
    public void removeTreatment(String attribute) {
        treatments.remove(attribute);
    }

}

