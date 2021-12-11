package fi.javanainen.migraineapp;

import static android.view.View.generateViewId;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

public class EditAttributesActivity extends AppCompatActivity {
    private AttributeList attributes;
    private ArrayList<String> triggers;
    private ArrayList<String> symptoms;
    private ArrayList<String> medicines;
    private ArrayList<String> treatments;

    //Views
    EditText userInput;
    TextView inputInfoText;

    private String attribute;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_attributes);
        attributes = AttributeList.getInstance();
        triggers = AttributeList.getInstance().getTriggers();
        symptoms = AttributeList.getInstance().getSymptoms();
        medicines = AttributeList.getInstance().getMedicines();
        treatments = AttributeList.getInstance().getTreatments();

        userInput = findViewById(R.id.userInput);
        inputInfoText = findViewById(R.id.inputInfoText);

        Intent intent = getIntent();
        id = intent.getStringExtra(SettingsActivity.SEND_MESSAGE);

        setInputText(id);

        // Adding Listener to the user input field
        userInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    attribute = userInput.getText().toString();
                    addButton(attribute);
                    addToList(attribute, id);

                    return true;
                }
                return false;
            }
        });

    }

    public void addButton(String input){
        Button newButton = new Button(this);
        Flow flow = findViewById(R.id.flow);
        newButton.setText(input);
        newButton.setId(generateViewId());
        ConstraintLayout layout = findViewById(R.id.layout);
        newButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        newButton.setText(input);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFromList(newButton.getText().toString(), id);
                flow.removeView(newButton);
                layout.removeView(newButton);
            }
        });

        layout.addView(newButton);
        flow.addView(newButton);
    }


    public void setInputText(String id) {
        switch (id) {
            case "triggers":
                inputInfoText.setText(R.string.triggers_text);
                userInput.setHint(R.string.triggers_hint);
                break;
            case "symptoms":
                inputInfoText.setText(R.string.symptoms_text);
                userInput.setHint(R.string.symptoms_hint);
                break;
            case "medicines":
                inputInfoText.setText(R.string.medicines_text);
                userInput.setHint(R.string.medicines_hint);
                break;
            case "treatments":
                inputInfoText.setText(R.string.treatments_text);
                userInput.setHint(R.string.treatments_hint);
                break;
        }
    }

    public void addToList(String attribute, @NonNull String id) {
        switch (id) {
            case "triggers":
                triggers.add(attribute);
                break;
            case "symptoms":
                symptoms.add(attribute);
                break;
            case "medicines":
                medicines.add(attribute);
                break;
            case "treatments":
                treatments.add(attribute);
                break;
        }
    }

    public void removeFromList(String attribute, @NonNull String id) {
        switch (id) {
            case "triggers":
                triggers.remove(attribute);
                break;
            case "symptoms":
                symptoms.remove(attribute);
                break;
            case "medicines":
                medicines.remove(attribute);
                break;
            case "treatments":
                treatments.remove(attribute);
                break;
        }
    }


    public void saveButtonClicked(View view) {


        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }




}