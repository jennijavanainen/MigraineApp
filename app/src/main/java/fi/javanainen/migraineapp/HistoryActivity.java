package fi.javanainen.migraineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends AppCompatActivity implements SelectListener{
    private BottomNavigationView navBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        addNavbar();

        DisplayItems();
    }
    RecyclerView recyclerView;
    ArrayList<MigraineList> migraineList;
    CustomAdapter customAdapter;



    private void DisplayItems(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        migraineList = new ArrayList<>();

        //???
        //migraineList.add(migraineList());

        customAdapter = new CustomAdapter(this, migraineList, this);

        recyclerView.setAdapter(customAdapter);
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
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    public void onItemClicked(MigraineList migraineList) {
        Intent intent = new Intent(this, ViewHistoryActivity.class);

        startActivity(intent);
    }
}