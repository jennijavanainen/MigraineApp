package fi.javanainen.migraineapp;

/**
 * Activity is responsible for adding a new Migraine if no active Migraine exists.
 * Activity will also create a single MigraineEvent. The user chooses the parameters.
 * If active Migraine exists, the activity will use it and create only a new MigraineEvent.
 * @see https://www.journaldev.com/9976/android-date-time-picker-dialog
 * @author Jenni Javanainen
 * @author Teemu Pennanen
 */

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
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

    LinearLayout layout;
    LinearLayout.LayoutParams lp;

    // Views
    TextView txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private SeekBar seekBar;


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
        // Create new Date Object
        date = new Date(mDay,mMonth,mYear);
        txtDate.setText(date.toString());
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        // Create new Time Object
        time = new Time(mHour,mMinute);
        txtTime.setText(time.toString());
        attributeList = AttributeList.getInstance();

        triggers = new ArrayList<>();
        symptoms = new ArrayList<>();
        medicines = new ArrayList<>();
        treatments = new ArrayList<>();

        layout = (LinearLayout) findViewById(R.id.linear_layout);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if (!migraineList.getActiveMigraineExists()) {
            addTriggerButtons();
        }
        addSymptomsButtons();
        addTreatmentsButtons();
        addMedicinesButtons();

        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        pain = seekBar.getProgress();

    }

    /**
     * Method creates MigraineEvent object with parameters given by the user.
     * If active Migraine does not exist, the method creates it as well.
     * Information will be saved in database/shared pref and the user will return to MainActivity.
     * @author Jenni Javanainen
     * @param view saveButton
     */
    public void saveButtonClicked(View view) {
        event = new MigraineEvent(date, time, pain, symptoms, medicines, treatments);
        if (migraineList.getActiveMigraineExists()) {
            migraineList.getLast().addEvent(date, time, pain, symptoms, medicines, treatments);;
        } else  {
            migraine = new Migraine(triggers, event);
            migraineList.addMigraine(migraine);
            migraineList.setActiveMigraineExists(true);
        }

        // Json conversion and saving tha data on SharedPref
        String jsonList = migraineList.listToJson();
        SharedPreferences prefPut = getSharedPreferences("MigrainePref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("migraineList", jsonList);
        prefEditor.putBoolean("migraineExists", migraineList.getActiveMigraineExists());
        prefEditor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    /**
     * Creates a DatePicker where the user can easily select the date. Default value is current date.
     * https://www.journaldev.com/9976/android-date-time-picker-dialog
     * @author Jenni Javanainen
     * @param view View
     */
    public void selectDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view1, year, monthOfYear, dayOfMonth) -> {
                    date.setDay(dayOfMonth);
                    date.setMonth(monthOfYear +1);
                    date.setYear(year);
                    txtDate.setText(date.toString());
                }, mYear, (mMonth -1), mDay);
        datePickerDialog.show();

    }

    /**
     * Creates a TimePicker in 24h form, where the user can easily select the time. Default value is current time.
     * https://www.journaldev.com/9976/android-date-time-picker-dialog
     * @author Jenni Javanainen
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

    /**
     *
     * author Jenni Javanainen
     * https://stackoverflow.com/questions/8629535/implementing-a-slider-seekbar-in-android
     */
    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    /**
     * Method for adding buttons for migraine attributes. Users inputs determine the text of the buttons and the amount of them.
     * In total 4 different methods for the 4 attributes.
     * @author Teemu Pennanen
     */

    public void addTriggerButtons(){

        TextView header = new TextView(this);
        header.setText("Your triggers");
        header.setGravity(Gravity.CENTER);
        layout.addView(header, lp);

        for (String trigger:attributeList.getTriggers()) {


            Button triggerButton = new Button(this);
            triggerButton.setText(trigger);

            LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.addView(triggerButton, lp);

            triggerButton.setOnClickListener(new View.OnClickListener() {
                int clicks = 0;
                @Override
                public void onClick(View v) {

                    if (clicks % 2 == 0) {
                        triggerButton.setBackgroundColor(Color.CYAN);
                        if(!triggers.contains(trigger)) {
                            symptoms.add(trigger);
                        }
                    } else {
                        triggerButton.setBackgroundColor(Color.LTGRAY);
                        symptoms.remove(trigger);
                    }

                    clicks++;
                }
            });
        }
    }

    public void addSymptomsButtons(){

        TextView header = new TextView(this);
        header.setText("Your symptoms");
        header.setGravity(Gravity.CENTER);
        layout.addView(header, lp);

        for (String symptom:attributeList.getSymptoms()) {


            Button symptomsButton = new Button(this);
            symptomsButton.setText(symptom);

            layout.addView(symptomsButton, lp);

            symptomsButton.setOnClickListener(new View.OnClickListener() {
                int clicks = 0;
                @Override
                public void onClick(View v) {

                    if (clicks % 2 == 0) {
                        symptomsButton.setBackgroundColor(Color.CYAN);
                        if(!triggers.contains(symptom)) {
                            symptoms.add(symptom);
                        }
                    } else {
                        symptomsButton.setBackgroundColor(Color.LTGRAY);
                        symptoms.remove(symptom);
                    }

                    clicks++;

                }
            });

        }
    }
    public void addTreatmentsButtons(){

        TextView header = new TextView(this);
        header.setText("Your treatments");
        header.setGravity(Gravity.CENTER);
        layout.addView(header, lp);

        for (String treatment:attributeList.getTreatments()) {


            Button treatmentsButton = new Button(this);
            treatmentsButton.setText(treatment);

            LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.addView(treatmentsButton, lp);



            treatmentsButton.setOnClickListener(new View.OnClickListener() {
                int clicks = 0;
                @Override
                public void onClick(View v) {

                    if (clicks % 2 == 0) {
                        treatmentsButton.setBackgroundColor(Color.CYAN);
                        if(!triggers.contains(treatment)) {
                            symptoms.add(treatment);
                        }
                    } else {
                        treatmentsButton.setBackgroundColor(Color.LTGRAY);
                        symptoms.remove(treatment);
                    }

                    clicks++;


                }
            });

        }
    }

    public void addMedicinesButtons(){

        TextView header = new TextView(this);
        header.setText("Your medicines");
        header.setGravity(Gravity.CENTER);
        layout.addView(header, lp);

        for (String medicine:attributeList.getMedicines()) {


            Button medicinesButton = new Button(this);
            medicinesButton.setText(medicine);

            LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.addView(medicinesButton, lp);

            medicinesButton.setOnClickListener(new View.OnClickListener() {
                int clicks = 0;
                @Override
                public void onClick(View v) {

                    if (clicks % 2 == 0) {
                        medicinesButton.setBackgroundColor(Color.CYAN);
                        if(!triggers.contains(medicine)) {
                            symptoms.add(medicine);
                        }
                    } else {
                        medicinesButton.setBackgroundColor(Color.LTGRAY);
                        symptoms.remove(medicine);
                    }

                    clicks++;
                }
            });

        }
    }


}