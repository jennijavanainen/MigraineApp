package fi.javanainen.migraineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teemu Pennanen
 */

public class HistoryActivity extends AppCompatActivity{
    private BottomNavigationView navBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        addNavbar();

        DisplayItems();
    }
    RecyclerView recyclerView;
    MigraineList migraineList;
    CustomAdapter customAdapter;

    private void DisplayItems(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        migraineList = MigraineList.getInstance();

        //???
        //migraineList.add(migraineList());

        customAdapter = new CustomAdapter(getApplicationContext(), migraineList);

        customAdapter.setOnItemClickedListener(new SelectListener() {
            @Override
            public void onItemClicked(int position) {
                Migraine migraine = migraineList.getMigraine(position);
                Log.d("katti",migraine.getLastEvent().getDate().toString());
            }
        });

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

}