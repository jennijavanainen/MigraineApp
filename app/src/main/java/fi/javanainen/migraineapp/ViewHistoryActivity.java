package fi.javanainen.migraineapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 *
 * @author Teemu Pennanen
 */

public class ViewHistoryActivity extends AppCompatActivity {

    TextView textDate, textTime, textTreatment, textTrigger, textMedicine;

    public void setTextDate(){
        this.textDate = textDate;
    }

    public void setTextTime(TextView textTime) {
        this.textTime = textTime;
    }

    public void setTextTreatment(TextView textTreatment) {
        this.textTreatment = textTreatment;
    }

    public void setTextTrigger(TextView textTrigger) {
        this.textTrigger = textTrigger;
    }

    public void setTextMedicine(TextView textMedicine) {
        this.textMedicine = textMedicine;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_migraine_history);
        addGraph();

    }



    public void addGraph() {

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{

                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }

}
