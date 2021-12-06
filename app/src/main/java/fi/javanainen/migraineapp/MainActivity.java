package fi.javanainen.migraineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BottomNavigationView navBar = findViewById(R.id.bottom_navigation);
        //navBar.setOnNavigationItemSelectedListener(navListener);
    }

    public void addNewMigraineButtonClicked(View view) {
        Intent intent = new Intent(this, AddMigraineActivity.class);
        startActivity(intent);
    }


}