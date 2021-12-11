package fi.javanainen.migraineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;



import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


/**
 * @author Jenni Javanainen
 */

public class SettingsActivity extends AppCompatActivity {
    private BottomNavigationView navBar;
    public static final String SEND_MESSAGE = "listItem";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        addNavbar();

    }

    /**
     * Adds bottom navbar to the Activity.
     */
    public void addNavbar() {
        navBar = findViewById(R.id.bottom_navigation);
        navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                        return true;
                    case R.id.settings:
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * Method resolves which button was pressed, and opens Editor activity.
     * Message will be sent containing information which info will be added to the next Activity.
     * @param view Button pressed
     */
    public void openAttributeEditor(View view) {
        Intent intent = new Intent(this, EditAttributesActivity.class);
        String message = "";
        switch (view.getId()) {
            case R.id.triggersBtn:
                message = "triggers";
                break;
            case R.id.symptomsBtn:
                message = "symptoms";
                break;
            case R.id.medicinesBtn:
                message = "medicines";
                break;
            case R.id.treatmentsBtn:
                message = "treatments";
                break;
        }
        intent.putExtra(SEND_MESSAGE, message);
        startActivity(intent);


    }


}