package de.ur.mi.android.sensorapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import de.ur.mi.android.sensorapp.R;
import de.ur.mi.android.sensorapp.sensors.SensorState;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.SensorViewHolder> {

    private ArrayList<SensorState> sensorStates;

    class SensorViewHolder extends RecyclerView.ViewHolder {

        public View sensorView;

        public SensorViewHolder(@NonNull View itemView) {
            super(itemView);
            sensorView = itemView;
        }
    }

    public SensorAdapter() {
        this.sensorStates = new ArrayList<SensorState>();
    }

    private SensorState getSensorStateByName(String name) {
        for (SensorState state : sensorStates) {
            if(state.name.equals(name)) {
                return state;
            }
        }
        return null;
    }

    public void addOrUpdateSensorState(SensorState state) {
        SensorState targetState = getSensorStateByName(state.name);
        if(targetState != null) {
            int index = sensorStates.indexOf(targetState);
            sensorStates.set(index, state);
        } else {
            sensorStates.add(state);
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensor_view, parent, false);
        SensorViewHolder vh = new SensorViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        SensorState state = sensorStates.get(position);
        TextView name = holder.sensorView.findViewById(R.id.sensor_name);
        TextView info = holder.sensorView.findViewById(R.id.sensor_info);
        TextView values = holder.sensorView.findViewById(R.id.sensor_values);
        name.setText(state.name);
        info.setText(String.format("Delay: %s ms | Power: %s mA", state.minDelayInMS, state.powerConsumptionInMA));
        if(state.currentValues != null) {
            String valueString = String.format("Aktuelle Werte: %s", Arrays.toString(state.currentValues));
            values.setText(valueString);
        }
    }

    @Override
    public int getItemCount() {
        return sensorStates.size();
    }
}
