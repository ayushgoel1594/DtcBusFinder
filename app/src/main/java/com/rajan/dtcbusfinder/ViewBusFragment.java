package com.rajan.dtcbusfinder;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ayush goel on 21/10/2015.
 */
public class ViewBusFragment extends Fragment implements View.OnClickListener {
    static AutoCompleteTextView etSource;
    static AutoCompleteTextView etDestination;
    static ListView tvBusList;
    static Toast toast1;
    static Button bFindBuses;
    static int counter = 0;
    ArrayAdapter<String> arrayadapter;
    static Cursor c1;
    ArrayList<String> arraylist = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(com.rajan.dtcbusfinder.R.layout.view_bus, container, false);
        bFindBuses = (Button) rootView.findViewById(com.rajan.dtcbusfinder.R.id.bFindBuses);
        etSource = (AutoCompleteTextView) rootView.findViewById(com.rajan.dtcbusfinder.R.id.etSource);
        etDestination = (AutoCompleteTextView) rootView.findViewById(com.rajan.dtcbusfinder.R.id.etDestination);
        tvBusList = (ListView) rootView.findViewById(com.rajan.dtcbusfinder.R.id.tvBusList);
        bFindBuses.setOnClickListener(this);
        bFindBuses.performClick();

        return rootView;

    }

    @Override
    public void onClick(View v) {
        counter++;

        busesDb findBuses = new busesDb(getActivity());
        findBuses.open();

        String[] stations = findBuses.getStations();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), com.rajan.dtcbusfinder.R.layout.list_item, stations);
        etSource.setAdapter(adapter1);
        etDestination.setAdapter(adapter1);
        String source = etSource.getText().toString();
        String destination = etDestination.getText().toString();
        String result = "";

        arraylist = findBuses.findBus(source, destination);

        //tvBusList.setText(result);
        //String[] from1 = {busesDb.KEY_BUS};
        //int[] to1 = {R.id.tv_buses};
        if (c1.getCount() == 0 && counter > 1) {
            Context context = getActivity();
            int duration = toast1.LENGTH_SHORT;
            toast1.makeText(getActivity(), "There are no buses between the two mentioned stations", duration).show();
        }
        arrayadapter = new ArrayAdapter(getActivity(), com.rajan.dtcbusfinder.R.layout.row2, arraylist);
        //cursorAdapter1 = new SimpleCursorAdapter(this, R.layout.row2 , c1, from1, to1);
        tvBusList.setAdapter(arrayadapter);
        tvBusList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TabActivity tabActivity = (TabActivity) getParent();

                String data = (String) parent.getItemAtPosition(position);
                //String ayush = data.replace(data.charAt(1),'\0');
//						data = data.replace(data.charAt(0),' ');
//						data = data.replace(data.charAt(1),' ');
//						data = data.replace('\t',' ');
                //data = data.replace(' ','\0');
                String[] aayush = data.split("\t");
                ViewRouteFragment vrf = new ViewRouteFragment();
                vrf.actvBusNumber.setText(aayush[aayush.length - 1].trim());
                vrf.bBusNumber.performClick();
                toast1.makeText(getActivity(),"You can swipe left to see the route of the selected bus",toast1.LENGTH_SHORT).show();



            }
        });
        findBuses.close();
    }

    public void sendCursor(Cursor c5) {
        this.c1 = c5;
    }

    @Override
    public void onPause() {
        super.onPause();
        counter=0;
    }
}