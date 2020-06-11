package de.ur.mi.android.sensorapp.sensors;

public interface SensorHelperListener {

    void onSensorDiscovered(SensorState state);
    void onSensorUpdated(SensorState state);
}
