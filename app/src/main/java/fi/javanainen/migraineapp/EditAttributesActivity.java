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

/**
 * In the activity user can add and remove their personal migraine attributes.
 * The activity shows different information depending on which button was clicked on SettingsActivity.
 * Tutorial used: https://stackoverflow.com/questions/57100375/can-i-add-number-of-buttons-in-runtime-depending-on-a-variable-and-not-in-xml/57100637
 * @author Jenni Javanainen
 */

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
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_attributes);
        attributes = AttributeList.getInstance();
        triggers = attributes.getTriggers();
        symptoms = attributes.getSymptoms();
        medicines = attributes.getMedicines();
        treatments = attributes.getTreatments();

        // Id will be received from SettingsActivity
        Intent intent = getIntent();
        id = intent.getStringExtra(SettingsActivity.SEND_MESSAGE);
        list = getCorrectList(id);

        userInput = findViewById(R.id.userInput);
        inputInfoText = findViewById(R.id.inputInfoText);

        setInputText(id);

        // Adding Listener to the user input field
        userInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    attribute = userInput.getText().toString();
                    addButton(attribute);
                    addToList(attribute);
                    return true;
                }
                return false;
            }
        });

        // Adding previous attributes from the list
        for (String attribute: list) {
            addButton(attribute);
        }

    }

    /**
     * Method takes user input and creates a new button to be displayed in the flow-section of the app.
     * OnClick-method is created for each button, so that they can be removed by clicking the button again.
     * @param input String user input
     */
    public void addButton(String input){
        Button newButton = new Button(this);
        Flow flow = findViewById(R.id.flow);
        newButton.setText(input);
        newButton.setId(generateViewId());
        ConstraintLayout layout = findViewById(R.id.layout);
        // Add params for the button
        newButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        newButton.setText(input);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFromList(newButton.getText().toString());
                flow.removeView(newButton);
                layout.removeView(newButton);
            }
        });

        layout.addView(newButton);
        flow.addView(newButton);
    }

    /**
     * Method receives an id, that defines what information is shown in the activity
     * @param id String identifier from previous activity
     */
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

    /**
     * Method returns the ArrayList that will be used in this activity, based on the id
     * @param id String identifier from previous activity
     * @return ArrayList
     */
    public ArrayList<String> getCorrectList(String id) {
        switch (id) {
            case "triggers":
                return triggers;
            case "symptoms":
                return symptoms;
            case "medicines":
                return medicines;
            case "treatments":
                return treatments;
        }
        return null;
    }

    /**
     * Method adds the given parameter String to the list used in this Activity
     * @param attribute String
     */
    public void addToList(String attribute) {
        // check if exists!

        list.add(attribute);
    }

    /**
     * Method removes the given parameter String from the list used in this Activity
     * @param attribute String
     */
    public void removeFromList(String attribute) {
        list.remove(attribute);
    }

    /**
     * When button is clicked, SettingsActivity opens
     * @param view Button
     */
    public void saveButtonClicked(View view) {


        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }




}