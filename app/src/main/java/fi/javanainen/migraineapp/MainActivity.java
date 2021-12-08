package fi.javanainen.migraineapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * Navbar tutorial used: https://material.io/components/bottom-navigation/android#using-bottom-navigation
 */

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navBar;
    private TextView test;
    private MigraineList migraineList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        migraineList = MigraineList.getInstance();
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