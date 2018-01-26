package com.rajan.dtcbusfinder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ayush goel on 21/10/2015.
 */
public class ViewRouteFragment extends Fragment implements View.OnClickListener {
    static AutoCompleteTextView actvBusNumber;
    static Button bBusNumber;
    static ListView lvStationList;
    static Cursor c;
    static int counter = 0;
    static int counter1 = 0;
    static Toast toast;
    static SimpleCursorAdapter cursorAdapter;
    Context context;
    private busesDb info;
    ArrayList<String> arraylist = new ArrayList<String>();
    ArrayAdapter<String> arrayadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(com.rajan.dtcbusfinder.R.layout.view_route,container,false);
        actvBusNumber = (AutoCompleteTextView) rootView.findViewById(com.rajan.dtcbusfinder.R.id.etBusNumber);
        bBusNumber = (Button) rootView.findViewById(com.rajan.dtcbusfinder.R.id.bBusNumber);
        lvStationList = (ListView) rootView.findViewById(com.rajan.dtcbusfinder.R.id.tvStationList1);
        bBusNumber.setOnClickListener(this);
        //  busesDb b =new busesDb(getActivity());
        bBusNumber.performClick();

        return rootView;

    }


    @Override
    public void onClick(View v) {


        String busNumber = actvBusNumber.getText().toString();
        busNumber=busNumber.trim();
        counter++;
        info = new busesDb(getActivity());

        //tvStationList.setText("byee");
        info.open();

        String[] buses  = info.getBuses();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), com.rajan.dtcbusfinder.R.layout.list_item, buses);
        actvBusNumber.setAdapter(adapter);
        String in = actvBusNumber.getText().toString().toUpperCase();
        //String data = info.getStations(in);
        arraylist= info.getStations(in);
        if(c.getCount()==0&& counter>1)
        {
            //counter=0;
            Context context = getActivity();
            int duration = toast.LENGTH_SHORT;
            toast.makeText(getActivity(), "Please Enter A valid Bus Number", duration).show();
        }

        String[] from = {busesDb.KEY_STATION};
        int[] to = {com.rajan.dtcbusfinder.R.id.tv_stations};
        // cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.row , c, from , to);
        arrayadapter = new ArrayAdapter<String>(getActivity(), com.rajan.dtcbusfinder.R.layout.row2, arraylist);

        lvStationList.setAdapter(arrayadapter);
        lvStationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String data = (String) parent.getItemAtPosition(position);
                // data=data.trim();
                final ViewBusFragment vbf = new ViewBusFragment();
                CharSequence options[] =new CharSequence[] {"Source Stand", "Destination Stand"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select as:");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0)
                        {
                            vbf.etSource.setText(data);
                            toast.makeText(getActivity(), data + "is selected as source station swipe right to view the details", toast.LENGTH_SHORT).show();
                        }
                        else if(which==1)
                        {
                            vbf.etDestination.setText(data);
                            toast.makeText(getActivity(), data + "is selected as destination station swipe right to view the details", toast.LENGTH_SHORT).show();
                            vbf.bFindBuses.performClick();
                        }
                    }
                });
                builder.show();

                /*if(counter1==0) {
                    vbf.etSource.setText(data);
                    toast.makeText(getActivity(), data + "is selected as source station swipe right to view the details", toast.LENGTH_SHORT).show();
                    counter1++;
                }
                else
                {
                    vbf.etDestination.setText(data);
                    toast.makeText(getActivity(), data + "is selected as destination station swipe right to view the details", toast.LENGTH_SHORT).show();
                    counter1=0;
                }
                vbf.bFindBuses.performClick();*/

            }
        });

        //tvStationList.setText(data);
        //String result = info.getStations("182A");



        //tvStationList.setText(data);
        info.close();


    }

    @Override
    public void onPause() {
        super.onPause();
        counter=0;
    }

    public void sendCursor(Cursor c5) {
        this.c=c5;
    }
}
