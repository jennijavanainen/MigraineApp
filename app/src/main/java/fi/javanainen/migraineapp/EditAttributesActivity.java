package fi.javanainen.migraineapp;

import static android.view.View.generateViewId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import com.google.gson.Gson;



/**
 * In the activity user can add and remove their personal migraine attributes.
 * The activity shows different information depending on which button was clicked on SettingsActivity.
 * Tutorial used: https://stackoverflow.com/questions/57100375/can-i-add-number-of-buttons-in-runtime-depending-on-a-variable-and-not-in-xml/57100637
 * @author Jenni Javanainen
 */

public class EditAttributesActivity extends AppCompatActivity {
    private AttributeList attributes;
    private Gson gson;
    private String savedTriggers;
    private String savedSymptoms;
    private String savedMedicines;
    private String savedTreatments;

    //Views
    EditText userInput;
    TextView inputInfoText;
    TextView infoPanel;
    Button saveButton;

    private String attribute;
    private String id;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_attributes);
        attributes = AttributeList.getInstance();
        gson = new Gson();

        // Id will be received from SettingsActivity
        Intent intent = getIntent();
        id = intent.getStringExtra(SettingsActivity.SEND_MESSAGE);
        list = getCorrectList(id);
        userInput = findViewById(R.id.userInput);
        inputInfoText = findViewById(R.id.inputInfoText);
        infoPanel = findViewById(R.id.infoPanel);
        saveButton = findViewById(R.id.saveButton);

        setInputText(id);

        // Save button listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                savedTriggers = gson.toJson(attributes.getTriggers());
                savedSymptoms = gson.toJson(attributes.getSymptoms());
                savedMedicines = gson.toJson(attributes.getMedicines());
                savedTreatments = gson.toJson(attributes.getTreatments());

                SharedPreferences prefPut = getSharedPreferences("MigrainePref",Activity.MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = prefPut.edit();
                prefEditor.putString("triggers", savedTriggers);
                prefEditor.putString("symptoms", savedSymptoms);
                prefEditor.putString("medicines", savedMedicines);
                prefEditor.putString("treatments", savedTreatments);
                prefEditor.commit();

                // Back to SettingsActivity
                Intent intent = new Intent(EditAttributesActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Adding Listener to the user input field
        userInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    attribute = userInput.getText().toString();
                    userInput.setNextFocusDownId(userInput.getId());

                    // Input validation

                    // Empty input
                    if (attribute.trim().isEmpty()) {
                        infoPanel.setVisibility(View.VISIBLE);
                        infoPanel.setText(R.string.info_empty);
                    }
                    // Max 20 characters in one input
                     else if (attribute.length() > 20) {
                        infoPanel.setVisibility(View.VISIBLE);
                        infoPanel.setText(R.string.info_long_word);
                    }
                    // Duplicate input
                    else if (list.contains(attribute.trim())) {
                        infoPanel.setVisibility(View.VISIBLE);
                        infoPanel.setText(R.string.info_item_exists);
                    }
                    // Max 10 attributes allowed
                    else if (list.size() >= 10) {
                        infoPanel.setVisibility(View.VISIBLE);
                        infoPanel.setText(R.string.info_exceeds);
                    }
                    // Valid input
                    else {
                        infoPanel.setVisibility(View.INVISIBLE);
                        addButton(attribute);
                        addToList(attribute, id);
                        userInput.getText().clear();
                        userInput.requestFocus();
                        return true;
                    }
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
        flow.setWrapMode(Flow.WRAP_ALIGNED );
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
                return attributes.getTriggers();
            case "symptoms":
                return attributes.getSymptoms();
            case "medicines":
                return attributes.getMedicines();
            case "treatments":
                return attributes.getTreatments();
        }
        return null;
    }

    /**
     * Method adds the given parameter String to the list used in this Activity
     * @param attribute String
     */
    public void addToList(String attribute, String id) {
        switch (id) {
            case "triggers":
                attributes.addTrigger(attribute);
                break;
            case "symptoms":
                attributes.addSymptom(attribute);
                break;
            case "medicines":
                attributes.addMedicine(attribute);
                break;
            case "treatments":
                attributes.addTreatment(attribute);
                break;
        }
    }

    /**
     * Method removes the given parameter String from the list used in this Activity
     * @param attribute String
     */
    public void removeFromList(String attribute) {
        switch (id) {
            case "triggers":
                attributes.removeTrigger(attribute);
                break;
            case "symptoms":
                attributes.removeSymptom(attribute);
                break;
            case "medicines":
                attributes.removeMedicine(attribute);
                break;
            case "treatments":
                attributes.removeTreatment(attribute);
                break;
        }
    }



}
