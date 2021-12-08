package fi.javanainen.migraineapp;

/**
 * Activity is responsible for adding a new Migraine if no active Migraine exists.
 * Activity will also create a single MigraineEvent. The user chooses the parameters.
 * If active Migraine exists, the activity will use it and create only a new MigraineEvent.
 * @see https://www.journaldev.com/9976/android-date-time-picker-dialog
 * @author Jenni Javanainen
 */

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

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
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_migraine);
        migraineList = MigraineList.getInstance();

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

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

        // Save info to database

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    /**
     * Creates a DatePicker where the user can easily select the date. Default value is current date.
     * @param view View
     */
    public void selectDate(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    /**
     * Creates a TimePicker in 24h form, where the user can easily select the time. Default value is current time.
     * @param view View
     */
    public void selectTime(View view) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        txtTime.setText(hourOfDay + "." + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }


}