package fi.javanainen.migraineapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * MainActivity provides a graph about ongoing Migraine attack and statistics from previous Migraines.
 * From the activity user can also add a new Migraine or update existing Migraine by opening AddMigraineActivity.
 * User can also end the ongoing Migraine.
 * @author Jenni Javanainen
 */

public class MainActivity extends AppCompatActivity {
    private Calendar cal;

    private BottomNavigationView navBar;
    private MigraineList migraineList;

    private TextView averageNumber;
    private TextView lastMigraine;
    private TextView migrainesTotalNumber;
    private Button addMigraineButton;
    private Button endMigraineButton;

    GraphView graph;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        migraineList = MigraineList.getInstance();
        cal = Calendar.getInstance();
        addNavbar();
        endMigraineButton = findViewById(R.id.endMigraine);
        addMigraineButton = findViewById(R.id.addNewMigraineButton);
        averageNumber = findViewById(R.id.averageNumber);
        lastMigraine = findViewById(R.id.lastMigraineNumber);
        migrainesTotalNumber = findViewById(R.id.migrainesTotalNumber);
        graph = (GraphView) findViewById(R.id.graph);

        updateUI();
    }

    /**
     * Adds bottom navbar to the Activity.
     * Main icon https://freeicons.io/material-icons-action/home-icon-15944
     * History icon https://freeicons.io/common-style-icons-14/stats-icon-14529
     * Settings icon https://freeicons.io/free-setting-and-configuration-icons/settings-icon-9631
     * Navbar source: https://material.io/components/bottom-navigation/android#using-bottom-navigation
     */
    public void addNavbar() {
        navBar = findViewById(R.id.bottom_navigation);
        navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main:
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * Method adds the graph from the active Migraine attack. The graph shows pain as a function of time from all events in the Migraine.
     * Source: https://github.com/jjoe64/GraphView
     */
    public void addGraph() {
        ArrayList<MigraineEvent> activeMigraineEvents = migraineList.getLast().getEvents();
        DataPoint[] graphPoints = new DataPoint[activeMigraineEvents.size() + 1];
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10);

        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);

        int i = 0;
        int x = 0;
        int y = 0;
        for (MigraineEvent event: activeMigraineEvents) {
            y = event.getPain();
            graphPoints[i] = new DataPoint(x, y);
            x++;
            i++;
        }
        graphPoints[i] = new DataPoint(x, y);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(graphPoints);
        graph.setTitle("Current migraine progress");
        graph.addSeries(series);
    }

    /**
     * Starts AddMigraineActivity when button is clicked
     * @param view Button
     */
    public void addNewMigraineButtonClicked(View view) {
        Intent intent = new Intent(this, AddMigraineActivity.class);
        startActivity(intent);
    }

    /**
     * End the Migraine by setting one last event with pain value 0 and current date
     * @param view Button
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void endMigraine(View view) {
        Date date = new Date(cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.MONTH) + 1,cal.get(Calendar.YEAR));
        Time time = new Time(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));

        migraineList.getLast().addEvent(date, time, 0, new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
        migraineList.setActiveMigraineExists(false);

        // Json conversion and saving tha data on SharedPref
        String jsonList = migraineList.listToJson();
        SharedPreferences prefPut = getSharedPreferences("MigrainePref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString("migraineList", jsonList);
        prefEditor.putBoolean("migraineExists", migraineList.getActiveMigraineExists());
        prefEditor.commit();

        updateUI();

    }

    /**
     * Method updates the user interface and determines what views to show, based on activeMigraineExists boolean value
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateUI() {
        averageNumber.setText(migraineList.toStringForm(migraineList.getAvgInMinutes()));
        if (migraineList.getMigraines().isEmpty()) {
            lastMigraine.setText("-");
        } else {
            lastMigraine.setText(migraineList.getLast().getLastEvent().getDate().toString());
        }

        migrainesTotalNumber.setText(Integer.toString(migraineList.howManyMigraines(30)));

        if (migraineList.getActiveMigraineExists()) {
            endMigraineButton.setVisibility(View.VISIBLE);
            addMigraineButton.setText(R.string.edit_migraine_button);
            graph.setVisibility(View.VISIBLE);
            addGraph();
        } else {
            endMigraineButton.setVisibility(View.INVISIBLE);
            addMigraineButton.setText(R.string.add_new_migraine_button);
            graph.setVisibility(View.INVISIBLE);
        }

    }
}