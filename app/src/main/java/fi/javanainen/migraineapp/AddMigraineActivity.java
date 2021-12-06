package fi.javanainen.migraineapp;

/**
 * Activity is responsible for adding a new Migraine if no active Migraine exists.
 * Activity will also create a single MigraineEvent. The user chooses the parameters.
 * If active Migraine exists, the activity will use it and create only a new MigraineEvent.
 * @author Jenni Javanainen
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AddMigraineActivity extends AppCompatActivity {

    // Migraine attributes
    private boolean activeMigraineExists;
    private MigraineList migraineList;
    private ArrayList<String> triggers;
    private Date date;
    private Time time;
    private int pain;
    private ArrayList<String> symptoms;
    private ArrayList<String> medicines;
    private ArrayList<String> treatments;
    private MigraineEvent event;
    private Migraine migraine;

    // Views


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_migraine);
        migraineList = MigraineList.getInstance();
        // get activemigraineexists
    }

    // Adding triggers
    // Adding pain
    // Adding symptoms
    // Adding medicines
    // Adding treatments


    /**
     * Method creates Date, Time and MigraineEvent objects with parameters given by the user.
     * If active Migraine does not exist, the method creates it as well.
     * Information will be saved in database and the user will return to MainActivity.
     * @param view saveButton
     */
    public void saveButtonClicked(View view) {
        date = new Date(4, 12, 2021);   // Tiedot haetaan
        time = new Time(14, 06);           // Tiedot haetaan
        event = new MigraineEvent(date, time, pain, symptoms, medicines, treatments);
        if (activeMigraineExists) {
            migraineList.returnLast().addEvent(date, time, pain, symptoms, medicines, treatments);
        } else {
            migraine = new Migraine(triggers, event);
            migraineList.addMigraine(migraine);
            activeMigraineExists = true;
        }

        // Save info to database

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}