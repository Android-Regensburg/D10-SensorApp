package de.ur.mi.android.sensorapp.sensors;

public class SensorState {

    public final String name;
    public final int minDelayInMS;
    public final float powerConsumptionInMA;
    public final float[] currentValues;

    public SensorState(String name, int delay, float powerConsumption, float[] values) {
        this.name = name;
        this.minDelayInMS = delay;
        this.powerConsumptionInMA = powerConsumption;
        this.currentValues = values;
    }
}
