package com.example.buslo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Homie extends Fragment implements View.OnClickListener {
    AutoCompleteTextView acTV;

    String inputs[]={"Prashant Vihar","Rohini Court Subway","Madhuban Chowk","Pitampura Metro","Kohat","ND Block","Netaji Subhash Place"};


    TextView buses, stops;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        buses = (TextView) v.findViewById(R.id.busesButton);

        acTV =(AutoCompleteTextView) v.findViewById(R.id.autocomp) ;
        buses.setOnClickListener(this);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,inputs);
        acTV.setAdapter(adapter);

        stops = (TextView) v.findViewById(R.id.stopsButt);
        stops.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }


    public void clicked(View view){
        Fragment fragment = new Buses();
        if(fragment!=null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentFrame, fragment);
            ft.commit();
        }
    }

    public void stops(View view){
        startActivity(new Intent(getContext(), Bus_Stops.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.busesButton:
                clicked(view);
                break;
            case R.id.stopsButt:
                stops(view);
                break;
        }
    }
}
