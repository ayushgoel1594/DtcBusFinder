package com.rajan.dtcbusfinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2/21/2016.
 */
public class ViewTrainsFragment extends Fragment implements View.OnClickListener {

    static AutoCompleteTextView etSource;
    static AutoCompleteTextView etDestination;
    static Button bSearch;
    static ListView lvStations;
    ArrayList<String> arraylist = new ArrayList<String>();
    ;
    ArrayAdapter<String> arrayadapter;
    HashMap<String, TrainInformation> trainInfo = new HashMap<>();
    ProgressDialog loadingdialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(com.rajan.dtcbusfinder.R.layout.view_trains, container, false);

        etSource = (AutoCompleteTextView) rootView.findViewById(com.rajan.dtcbusfinder.R.id.tvSourceTrain);
        etDestination = (AutoCompleteTextView) rootView.findViewById(com.rajan.dtcbusfinder.R.id.tvDestTrain);
        bSearch = (Button) rootView.findViewById(com.rajan.dtcbusfinder.R.id.bSearch);
        lvStations = (ListView) rootView.findViewById(com.rajan.dtcbusfinder.R.id.lvTrainList);

        bSearch.setOnClickListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Auto Complete Station Codes
        String[] stationCodes = getStationCodes(getActivity());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), com.rajan.dtcbusfinder.R.layout.list_item, stationCodes);
        etSource.setAdapter(adapter);
        etDestination.setAdapter(adapter);

        lvStations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String info = (String) parent.getItemAtPosition(position);

                // train[0] = train Nunmber
                //train[1] = train Name
                String train[] = info.split("\t");
                TrainInformation obj = trainInfo.get(train[0]);

                String data = "Train Number :\n" + obj.number + "\n\n" +
                        "Train Name   :\n" + obj.name + "\n\n" +
                        "Source Departure Time :\n" + obj.src_departure + "\n\n" +
                        "Destination Arrival Time :\n" + obj.dest_arrival + "\n\n" +
                        "Total journey time(hh:mm) :\n" + obj.totalTime + "\n\n" +
                        "Running Days :\n" + obj.runningDays;

              

                // CharSequence options[] = new CharSequence[]{"Source Stand", "Destination Stand"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(Html.fromHtml("<b><u>Train Information</u></b>"));

                builder.setMessage(data);
                builder.show();

            }
        });

        return rootView;
    }

    public static String[] getStationCodes(Activity act) {

        AssetManager assetManager = act.getAssets();
        String[] stringArr = null;
        try {
            InputStream inputStream = assetManager.open("stationList.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String line = "";

            List<String> list = new ArrayList<String>();

            while ((line = bufferedReader.readLine()) != null) {

                list.add(line);

            }

            stringArr = list.toArray(new String[0]);

        } catch (Exception e) {

        }
        return stringArr;
    }


    // to check whether connected to internet
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name

            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

    }


    @Override
    public void onClick(View v) {


        if (!isInternetAvailable()) {
            Toast.makeText(getActivity(), "Please check your Internet Connection",
                    Toast.LENGTH_LONG).show();
            return;
        }

        arraylist.clear();




        // Log.e("runontithread1:"," start ");
        loadingdialog = new ProgressDialog(getActivity());

        //Log.e("runontithread1:"," complete ");
        String srArr[] = etSource.getText().toString().split(" ");
        String sr = srArr[srArr.length - 1];

        String dstArr[] = etDestination.getText().toString().split(" ");
        String dst = dstArr[dstArr.length - 1];

        new LongOperation().execute(sr, dst);

    }


     private class LongOperation extends AsyncTask<String, Void, String> {

         int total;
    @Override
    protected String doInBackground(String... params) {

        Date d = Calendar.getInstance().getTime(); // Current time
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM"); // Set your date format
        String currentData = sdf.format(d);
        Log.e("Train_date:", currentData);
        String url = "http://api.railwayapi.com/between/source/" + params[0] + "/dest/" + params[1] + "/date/" + currentData + "/apikey/pxbbh2260/";
        //JSONParser jsonParser = new JSONParser();

        //res.setText("Check your internet connection");

        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpGet httppost = new HttpGet(url);

// Depends on your web service
        httppost.setHeader("Content-type", "application/json");

        InputStream inputStream = null;
        String result = null;
        try {
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();

            // json is UTF-8 by default
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");

            }
            result = sb.toString();
            // res.setText(result);
            JSONObject jObject = new JSONObject(result);
             total = Integer.parseInt(jObject.getString("total"));

            if (total == 0) {
                Toast.makeText(getActivity(), "No trains between the given stations",
                        Toast.LENGTH_LONG).show();
                return "";
            }

            JSONArray jArray = jObject.getJSONArray("train");


            //res.setText(jArray.toString());
            for (int i = 0; i < jArray.length(); i++) {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    TrainInformation info = new TrainInformation();
                    // Pulling items from the array
                    String trainName = oneObject.getString("name");
                    String trainNumber = oneObject.getString("number");
                    String resp = trainNumber + "\t\t\t\t\t\t\t\t\t" + trainName;

                    JSONArray jArray2 = oneObject.getJSONArray("days");

                    String days = "";
                    for (int j = 0; j < jArray2.length(); j++) {
                        JSONObject inObject = jArray2.getJSONObject(j);
                        if (inObject.getString("runs").equals("Y")) {
                            days += inObject.getString("day-code") + "\t\t";
                        }
                    }


                    info.name = trainName;
                    info.number = trainNumber;
                    info.dest_arrival = oneObject.getString("dest_arrival_time");
                    info.src_departure = oneObject.getString("src_departure_time");
                    info.totalTime = oneObject.getString("travel_time");
                    info.runningDays = days;

                    trainInfo.put(trainNumber, info);
                    arraylist.add(resp);

                    //String oneObjectsItem2 = oneObject.getString("anotherSTRINGNAMEINtheARRAY");
                } catch (JSONException e) {
                    // Oops galti ho gayi
                }
            }
            arrayadapter = new ArrayAdapter<String>(getActivity(), com.rajan.dtcbusfinder.R.layout.row_trains, arraylist);

            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    lvStations.setAdapter(arrayadapter);
                }
            });

            //res.setText(resp);



        } catch (Exception e) {
            // Oops
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (Exception squish) {
            }
        }


        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {

        loadingdialog.dismiss();
        if (total == 0) {
            Toast.makeText(getActivity(), "No trains between the given stations",
                    Toast.LENGTH_LONG).show();

        }

    // into onPostExecute() but that is upto you
    }

    @Override
    protected void onPreExecute() {
        loadingdialog.setTitle("In Progress");
        loadingdialog.setMessage("Loading...");
        loadingdialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {}
    }

}
