package fi.javanainen.migraineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {
    private AttributeList attributes;
    private ArrayList<String> triggers;
    private ArrayList<String> symptoms;
    private ArrayList<String> medicines;
    private ArrayList<String> treatments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        attributes = AttributeList.getInstance();
    }
}