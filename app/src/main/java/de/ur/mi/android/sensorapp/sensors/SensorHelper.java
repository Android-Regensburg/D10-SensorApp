package de.ur.mi.android.sensorapp.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.List;

import de.ur.mi.android.sensorapp.ui.SensorAdapter;

public class SensorHelper implements SensorEventListener {

    private Context context;
    private SensorHelperListener listener;
    private SensorManager sensorManager;

    public SensorHelper(Context context, SensorHelperListener listener) {
        this.context = context;
        this.listener = listener;
        initSensors();
    }

    private void initSensors() {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> availableSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor: availableSensors) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            SensorState state = new SensorState(sensor.getName(),sensor.getMinDelay(),sensor.getPower(), null);
            listener.onSensorDiscovered(state);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        SensorState state = new SensorState(sensor.getName(), sensor.getMinDelay(), sensor.getPower(), event.values);
        listener.onSensorUpdated(state);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
