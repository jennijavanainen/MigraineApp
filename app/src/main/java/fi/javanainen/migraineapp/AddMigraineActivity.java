package fi.javanainen.migraineapp;

/**
 * Activity is responsible for adding a new Migraine if no active Migraine exists.
 * Activity will also create a single MigraineEvent. The user chooses the parameters.
 * If active Migraine exists, the activity will use it and create only a new MigraineEvent.
 * @see https://www.journaldev.com/9976/android-date-time-picker-dialog
 * @author Jenni Javanainen
 */

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class AddMigraineActivity extends AppCompatActivity {
    private final Calendar c = Calendar.getInstance();

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
    private AttributeList attributeList;


    // Views
    TextView txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_migraine);
        migraineList = MigraineList.getInstance();
        txtDate=(TextView) findViewById(R.id.in_date);
        txtTime=(TextView) findViewById(R.id.in_time);

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        // Luodaan uudi Date-olio
        date = new Date(mDay,mMonth,mYear);
        txtDate.setText(date.toString());
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        // Luodaan uusi Time-olio
        time = new Time(mHour,mMinute);
        txtTime.setText(time.toString());
        attributeList = AttributeList.getInstance();

        addTriggerButtons();
        addSymptomsButtons();
        addTreatmentsButtons();
        addMedicinesButtons();

        triggers = new ArrayList<>();
        symptoms = new ArrayList<>();
        medicines = new ArrayList<>();
        treatments = new ArrayList<>();



        // get activemigraineexists
    }

    // Adding triggers
    // Adding pain
    // Adding symptoms
    // Adding medicines
    // Adding treatments

    /**
     * Method creates MigraineEvent object with parameters given by the user.
     * If active Migraine does not exist, the method creates it as well.
     * Information will be saved in database and the user will return to MainActivity.
     * @param view saveButton
     */
    public void saveButtonClicked(View view) {
        event = new MigraineEvent(date, time, pain, symptoms, medicines, treatments);
        if (activeMigraineExists) {
            migraineList.getLast().addEvent(date, time, pain, symptoms, medicines, treatments);
        } else {
            migraine = new Migraine(triggers, event);
            migraineList.addMigraine(migraine);
            activeMigraineExists = true;
        }

        String jsonList = migraineList.listToJson();
        SharedPreferences prefPut = getSharedPreferences("MigrainePref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("migraineList", jsonList);
        prefEditor.commit();

        // Save info to database
        // (also activeMigraineExists)

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    /**
     * Creates a DatePicker where the user can easily select the date. Default value is current date.
     * @param view View
     */
    public void selectDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view1, year, monthOfYear, dayOfMonth) -> {
                    date.setDay(dayOfMonth);
                    date.setMonth(monthOfYear + 1);
                    date.setYear(year);
                    txtDate.setText(date.toString());
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    /**
     * Creates a TimePicker in 24h form, where the user can easily select the time. Default value is current time.
     * @param view View
     */
    public void selectTime(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view1, hourOfDay, minute) ->  {
                    time.setHours(hourOfDay);
                    time.setMinutes(minute);
                    txtTime.setText(time.toString());
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    public void addTriggerButtons(){

        for (String trigger:attributeList.getTriggers()) {


            Button triggerButton = new Button(this);
            triggerButton.setText(trigger);

            LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.addView(triggerButton, lp);

            triggerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!triggers.contains(trigger)) {
                        symptoms.add(trigger);
                    }
                }
            });
        }
    }

    public void addSymptomsButtons(){

        for (String symptom:attributeList.getSymptoms()) {


            Button symptomsButton = new Button(this);
            symptomsButton.setText(symptom);

            LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.addView(symptomsButton, lp);

            symptomsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!symptoms.contains(symptom)) {
                        symptoms.add(symptom);
                    }

                }
            });

        }
    }
    public void addTreatmentsButtons(){

        for (String treatment:attributeList.getTreatments()) {


            Button treatmentsButton = new Button(this);
            treatmentsButton.setText(treatment);

            LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.addView(treatmentsButton, lp);



            treatmentsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(treatments.contains(treatment)) {
                        treatments.add(treatment);
                    }
                }
            });

        }
    }

    public void addMedicinesButtons(){

        for (String medicine:attributeList.getMedicines()) {


            Button medicinesButton = new Button(this);
            medicinesButton.setText(medicine);

            LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.addView(medicinesButton, lp);

            medicinesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!medicines.contains(medicine)) {
                        medicines.add(medicine);
                    }
                }
            });

        }
    }
    //onclick tämän luokan listaan!

    //tarkista ettei nappia voi painaa useasti!!


}