package fi.javanainen.migraineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Opening activity, which will collect SharedPreferences information
 * In 5 seconds the mainActivity will automatically open.
 * @author Jenni Javanainen
 */

public class StartActivity extends AppCompatActivity {
    private AttributeList attributes;
    private MigraineList migraineList;
    private ArrayList<String> triggers;
    private ArrayList<String> symptoms;
    private ArrayList<String> medicines;
    private ArrayList<String> treatments;
    private ArrayList<Migraine> migraines;
    private String savedMigraines;
    private String savedTriggers;
    private String savedSymptoms;
    private String savedMedicines;
    private String savedTreatments;
    private boolean activeMigraineExists;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        attributes = AttributeList.getInstance();
        migraineList = MigraineList.getInstance();

        //Run the app with the two lines to empty Shared Preferences
        SharedPreferences settings = StartActivity.this.getSharedPreferences("MigrainePref", Activity.MODE_PRIVATE);
        settings.edit().clear().commit();

        triggers = new ArrayList<>();
        symptoms = new ArrayList<>();
        medicines = new ArrayList<>();
        treatments = new ArrayList<>();
        migraines = new ArrayList<>();

        // Collecting information from SharedPref
        SharedPreferences prefGet = getSharedPreferences("MigrainePref", Activity.MODE_PRIVATE);
        savedTriggers = prefGet.getString("triggers", "[dehydration]");
        savedSymptoms = prefGet.getString("symptoms", "[nausea]");
        savedMedicines = prefGet.getString("medicines", "[Paracetamol500mg]");
        savedTreatments = prefGet.getString("treatments", "[nap]");
        savedMigraines = prefGet.getString("migraineList", "[]");
        activeMigraineExists = prefGet.getBoolean("migraineExists", false);
        TypeToken<List<String>> token = new TypeToken<List<String>>() {};

        gson = new Gson();

        // Converting json string into correct form
        triggers = gson.fromJson(savedTriggers, token.getType());
        for (String trigger: triggers) {
            attributes.addTrigger(trigger);
        }
        symptoms = gson.fromJson(savedSymptoms, token.getType());
        for (String symptom: symptoms) {
            attributes.addSymptom(symptom);
        }
        medicines = gson.fromJson(savedMedicines, token.getType());
        for (String medicine: medicines) {
            attributes.addMedicine(medicine);
        }
        treatments = gson.fromJson(savedTreatments, token.getType());
        for (String treatment: treatments) {
            attributes.addTreatment(treatment);
        }

        TypeToken<List<Migraine>> token2 = new TypeToken<List<Migraine>>() {};
        migraines = gson.fromJson(savedMigraines, token2.getType());

        migraineList.addMigrainesToList(migraines);
        migraineList.setActiveMigraineExists(activeMigraineExists);

        nextActivity(4000);
    }

    /**
     * Method opens next activity automatically after given time.
     * @param mills Integer Milliseconds before opening new Activity
     */
    public void nextActivity(int mills)
    {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, mills);

    }


}