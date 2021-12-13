package fi.javanainen.migraineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Opening activity, which will collect SharedPreferences information
 * In 5 seconds the mainActivity will automatically open.
 * @author Jenni Javanainen
 */

public class StartActivity extends AppCompatActivity {
    private AttributeList attributes;
    private ArrayList<String> triggers;
    private ArrayList<String> symptoms;
    private ArrayList<String> medicines;
    private ArrayList<String> treatments;
    private String savedTriggers;
    private String savedSymptoms;
    private String savedMedicines;
    private String savedTreatments;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        attributes = AttributeList.getInstance();

        triggers = new ArrayList<>();
        symptoms = new ArrayList<>();
        medicines = new ArrayList<>();
        treatments = new ArrayList<>();

        SharedPreferences prefGet = getSharedPreferences("MigrainePref", Activity.MODE_PRIVATE);
        savedTriggers = prefGet.getString("triggers", "[dehydration]");
        savedSymptoms = prefGet.getString("symptoms", "[nausea]");
        savedMedicines = prefGet.getString("medicines", "[Paracetamol500mg]");
        savedTreatments = prefGet.getString("treatments", "[nap]");

        TypeToken<List<String>> token = new TypeToken<List<String>>() {};
        gson = new Gson();
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

        nextActivity(5000);
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