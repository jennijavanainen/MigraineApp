package fi.javanainen.migraineapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

/**
 * Navbar tutorial used: https://material.io/components/bottom-navigation/android#using-bottom-navigation
 */

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navBar;
    private TextView test;

    public static final int ADD_MIGRAINE_REQUEST = 1;

    private MigraineViewModel migraineViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAddMigraine = findViewById(R.id.button_add_migraine);
        buttonAddMigraine.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddMigraineActivity.class);
            startActivityForResult(intent, ADD_MIGRAINE_REQUEST);
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MigraineAdapter adapter = new MigraineAdapter();
        recyclerView.setAdapter(adapter);

        migraineViewModel = new ViewModelProvider(this).get(MigraineViewModel.class);
        migraineViewModel.getAllMigraines().observe(this, new Observer<List<MigraineEvent>>() {
            @Override
            public void onChanged(@Nullable List<MigraineEvent> migraineEvents) {
                adapter.setMigraines(migraineEvents);
            }

        });
    }

    //OnActivityResult

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




}