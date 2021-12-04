package fi.javanainen.migraineapp;

/**
 *
 */

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class AddMigraineActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_migraine);
        migraineList = MigraineList.getInstance();

    }

    public void sendButtonClicked() {
        date = new Date(4, 12, 2021);
        time = new Time(14, 06);
        event = new MigraineEvent(date, time, pain, symptoms, medicines, treatments);
        // ehtolause, joka tarkistaa luodaanko migreeniä vai pelkkä eventti
        migraine = new Migraine(triggers, event);
        migraineList.addMigraine(migraine);
    }


}