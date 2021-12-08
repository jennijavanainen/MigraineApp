package fi.javanainen.migraineapp;

/**
 * Activity is responsible for adding a new Migraine if no active Migraine exists.
 * Activity will also create a single MigraineEvent. The user chooses the parameters.
 * If active Migraine exists, the activity will use it and create only a new MigraineEvent.
 * @author Jenni Javanainen
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

public class AddMigraineActivity extends AppCompatActivity {
    private EditText editTextDate;
    private EditText editTextTime;
    private EditText editTextTreatment;
    private EditText editTextTrigger;
    private Slider painSlider;

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

    private MigraineViewModel migraineViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_migraine);
        migraineList = MigraineList.getInstance();
        // get activemigraineexists

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MigraineAdapter adapter = new MigraineAdapter();
        recyclerView.setAdapter(adapter);

        migraineViewModel = new ViewModelProvider(this).get(MigraineViewModel.class);
        migraineViewModel.getAllMigraines().observe(this, new Observer<List<Migraine>>() {
            @Override
            public void onChanged(List<Migraine> migraines) {
                adapter.setMigranes(migraines);
            }
        });
        editTextDate = findViewById(R.id.edit_date);
        editTextTime = findViewById(R.id.edit_time);
        editTextTreatment = findViewById(R.id.edit_treatment);
        editTextTrigger = findViewById(R.id.edit_trigger);
        painSlider = findViewById(R.id.pain_slider);

        painSlider.setMinimumHeight(0);
        painSlider.setMinimumHeight(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Migraine");
    }


    // Adding triggers
    // Adding pain
    // Adding symptoms
    // Adding medicines
    // Adding treatments


    /**
     * Method creates Date, Time and MigraineEvent objects with parameters given by the user.
     * If active Migraine does not exist, the method creates it as well.
     * After this the Activity closes and user returns to MainActivity with information about activeMigraineExists.
     * @param view saveButton
     */
    public void saveButtonClicked(View view) {
        //date = new Date(4, 12, 2021);   // Tiedot haetaan
        //time = new Time(14, 06);           // Tiedot haetaan
        event = new MigraineEvent(date, time, pain, symptoms, medicines, treatments);
        if (activeMigraineExists) {
            migraineList.returnLast().addEvent(date, time, pain, symptoms, medicines, treatments);
        } else {
            migraine = new Migraine(triggers, event);
            migraineList.addMigraine(migraine);
            activeMigraineExists = true;
        }

        //MigraineViewModel = ViewModelProvider(get(MigraineViewModel);

        // Save info to database
        insertDataToDatabase();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void insertDataToDatabase() {

        }

    }

