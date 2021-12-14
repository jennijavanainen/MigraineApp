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
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * MainActivity provides information about ongoing Migraine attack and statistics from previous Migraines.
 * From the activity user can also add a new Migraine or update existing Migraine by opening AddMigraineActivity.
 * @author Jenni Javanainen
 */

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navBar;
    private MigraineList migraineList;
    private boolean activeMigraineExists;

    private TextView averageNumber;
    private TextView lastMigraine;
    private TextView migrainesTotalNumber;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        migraineList = MigraineList.getInstance();
        addNavbar();

        SharedPreferences prefGet = getSharedPreferences("MigrainePref", Activity.MODE_PRIVATE);
        activeMigraineExists = prefGet.getBoolean("activeMigraineExists", false);
        migraineList.setActiveMigraineExists(activeMigraineExists);

        averageNumber = findViewById(R.id.averageNumber);
        averageNumber.setText(migraineList.toStringForm(migraineList.getAvgInMinutes()));
        lastMigraine = findViewById(R.id.lastMigraineNumber);
        lastMigraine.setText(migraineList.getLast().getLastEvent().getDate().toString());
        migrainesTotalNumber = findViewById(R.id.migrainesTotalNumber);
        migrainesTotalNumber.setText(Integer.toString(migraineList.howManyMigraines(30)));

    }



    /**
     * Adds bottom navbar to the Activity.
     * Main icon https://freeicons.io/material-icons-action/home-icon-15944
     * History icon https://freeicons.io/common-style-icons-14/stats-icon-14529
     * Settings icon https://freeicons.io/free-setting-and-configuration-icons/settings-icon-9631
     * Navbar tutorial used: https://material.io/components/bottom-navigation/android#using-bottom-navigation
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
     * Starts AddMigraineActivity when button is clicked
     * @param view View
     */
    public void addNewMigraineButtonClicked(View view) {
        Intent intent = new Intent(this, AddMigraineActivity.class);
        startActivity(intent);
    }
}