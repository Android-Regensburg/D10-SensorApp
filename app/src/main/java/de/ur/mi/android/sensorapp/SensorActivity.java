package de.ur.mi.android.sensorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import de.ur.mi.android.sensorapp.sensors.SensorHelper;
import de.ur.mi.android.sensorapp.sensors.SensorHelperListener;
import de.ur.mi.android.sensorapp.sensors.SensorState;
import de.ur.mi.android.sensorapp.ui.SensorAdapter;

public class SensorActivity extends AppCompatActivity implements SensorHelperListener {

    private SensorHelper sensorHelper;
    private SensorAdapter sensorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initSensors();
    }

    private void initUI() {
        setContentView(R.layout.activity_sensor);
        sensorAdapter = new SensorAdapter();
        RecyclerView sensorStateList = findViewById(R.id.sensor_list);
        sensorStateList.setAdapter(sensorAdapter);
    }

    private void initSensors() {
        sensorHelper = new SensorHelper(this, this);
    }

    @Override
    public void onSensorDiscovered(SensorState state) {
        sensorAdapter.addOrUpdateSensorState(state);

    }

    @Override
    public void onSensorUpdated(SensorState state) {
        sensorAdapter.addOrUpdateSensorState(state);
    }
}
