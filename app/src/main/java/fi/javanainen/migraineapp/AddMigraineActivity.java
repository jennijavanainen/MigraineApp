package fi.javanainen.migraineapp;

/**
 * Activity is responsible for adding a new Migraine if no active Migraine exists.
 * Activity will also create a single MigraineEvent. The user chooses the parameters.
 * If active Migraine exists, the activity will use it and create only a new MigraineEvent.
 * @see https://www.journaldev.com/9976/android-date-time-picker-dialog
 * @author Jenni Javanainen
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.EditText;

import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMigraineActivity extends AppCompatActivity {
    //Teemun juttuja
    //private EditText editTextDate;
    //private EditText editTextTime;
    //private EditText editTextTreatment;
    //private EditText editTextTrigger;
    //private Slider painSlider;

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
    TextView txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    private MigraineViewModel migraineViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_migraine);
        migraineList = MigraineList.getInstance();
        txtDate=(TextView) findViewById(R.id.in_date);
        txtTime=(TextView) findViewById(R.id.in_time);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        txtDate.setText(mDay + "." + mMonth + "." +mYear);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        txtTime.setText(mHour + "." + mMinute);

        // get activemigraineexists

        //Teemun RecyclerView:

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
     * Information will be saved in database and the user will return to MainActivity.
     * @param view saveButton
     */
    public void saveButtonClicked(View view) {
        date = new Date(3, 12, 2021);   // Tiedot haetaan
        time = new Time(14, 06);           // Tiedot haetaan
        event = new MigraineEvent(date, time, pain, symptoms, medicines, treatments);
        if (activeMigraineExists) {
            migraineList.getLast().addEvent(date, time, pain, symptoms, medicines, treatments);
        } else {
            migraine = new Migraine(triggers, event);
            migraineList.addMigraine(migraine);
            activeMigraineExists = true;
        }

        //MigraineViewModel = ViewModelProvider(get(MigraineViewModel);

        // Save info to database

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    /**
     * Creates a DatePicker where the user can easily select the date. Default value is current date.
     * @param view View
     */
    public void selectDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view1, year, monthOfYear, dayOfMonth) -> txtDate.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year), mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    /**
     * Creates a TimePicker in 24h form, where the user can easily select the time. Default value is current time.
     * @param view View
     */
    public void selectTime(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view1, hourOfDay, minute) -> txtTime.setText(hourOfDay + "." + minute), mHour, mMinute, true);
        timePickerDialog.show();
    }


}