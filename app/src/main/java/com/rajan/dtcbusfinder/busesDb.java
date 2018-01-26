package com.rajan.dtcbusfinder;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class busesDb {

	//Database Stuff 
	public static final String KEY_ROWID = "_id";
	public static final String KEY_BUS = "bus_number";
	public static final String KEY_STATION = "station_name";

	private static final String DATABASE_NAME = "busthree";
	private static final String DATABASE_TABLE = "busTable3";
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private static Context ourContext;
	ArrayAdapter<String> arrayadapter;
	ArrayList<String> arraylist = new ArrayList<String>();
	private SQLiteDatabase ourDatabase;

	//inner class for database management
	private static class DbHelper extends SQLiteOpenHelper {

		TextView tvStationList;
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			//	tvStationList.setText("DbHelper");
			//this.tvStationList = tvStationList;

			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_BUS + " TEXT NOT NULL," +
							KEY_STATION + " TEXT NOT NULL);"
			);

			//tvStationList.setText("byee");
			//info.open(tvStationList);/*

			AssetManager assetManager = ourContext.getAssets();

			try {
				InputStream inputStream = assetManager.open("out1.txt");
				InputStreamReader streamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(streamReader);

				String line="";


				while((line = bufferedReader.readLine())!= null)
				{

					String parts[] = line.split("-");

					ContentValues cv = new ContentValues();
					cv.put(KEY_BUS, parts[0]);
					cv.put(KEY_STATION, parts[1]);
					db.insert(DATABASE_TABLE, null, cv);
						
						/*db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES('" + 
								parts[0] + "' ,' " + 
									parts[1] + "');"
									);*/

					//map.put(parts[0], parts[1]);
					//tv.setText(parts[0] + " " + parts[1]);
				}

				/************file2*************************/
				inputStream = assetManager.open("out2.txt");
				streamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(streamReader);

				line="";


				while((line = bufferedReader.readLine())!= null)
				{

					String parts[] = line.split("-");

					ContentValues cv = new ContentValues();
					cv.put(KEY_BUS, parts[0]);
					cv.put(KEY_STATION, parts[1]);
					db.insert(DATABASE_TABLE, null, cv);
					
					/*db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES('" + 
							parts[0] + "' ,' " + 
								parts[1] + "');"
								);*/

					//map.put(parts[0], parts[1]);
					//tv.setText(parts[0] + " " + parts[1]);
				}


				/************file3*************************/
				inputStream = assetManager.open("out3.txt");
				streamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(streamReader);

				line="";


				while((line = bufferedReader.readLine())!= null)
				{

					String parts[] = line.split("-");

					ContentValues cv = new ContentValues();
					cv.put(KEY_BUS, parts[0]);
					cv.put(KEY_STATION, parts[1]);
					db.insert(DATABASE_TABLE, null, cv);
				
				/*db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES('" + 
						parts[0] + "' ,' " + 
							parts[1] + "');"
							);*/

					//map.put(parts[0], parts[1]);
					//tv.setText(parts[0] + " " + parts[1]);
				}


				/************file4*************************/
				inputStream = assetManager.open("out4.txt");
				streamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(streamReader);

				line="";


				while((line = bufferedReader.readLine())!= null)
				{

					String parts[] = line.split("-");

					ContentValues cv = new ContentValues();
					cv.put(KEY_BUS, parts[0]);
					cv.put(KEY_STATION, parts[1]);
					db.insert(DATABASE_TABLE, null, cv);
			
			/*db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES('" + 
					parts[0] + "' ,' " + 
						parts[1] + "');"
						);*/

					//map.put(parts[0], parts[1]);
					//tv.setText(parts[0] + " " + parts[1]);
				}

				/************file5*************************/
				inputStream = assetManager.open("out5.txt");
				streamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(streamReader);

				line="";


				while((line = bufferedReader.readLine())!= null)
				{

					String parts[] = line.split("-");

					ContentValues cv = new ContentValues();
					cv.put(KEY_BUS, parts[0]);
					cv.put(KEY_STATION, parts[1]);
					db.insert(DATABASE_TABLE, null, cv);
		
		/*db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES('" + 
				parts[0] + "' ,' " + 
					parts[1] + "');"
					);*/

					//map.put(parts[0], parts[1]);
					//tv.setText(parts[0] + " " + parts[1]);
				}


				/************file6*************************/
				inputStream = assetManager.open("out6.txt");
				streamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(streamReader);

				line="";


				while((line = bufferedReader.readLine())!= null)
				{

					String parts[] = line.split("-");

					ContentValues cv = new ContentValues();
					cv.put(KEY_BUS, parts[0]);
					cv.put(KEY_STATION, parts[1]);
					db.insert(DATABASE_TABLE, null, cv);
	
	/*db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES('" + 
			parts[0] + "' ,' " + 
				parts[1] + "');"
				);*/

					//map.put(parts[0], parts[1]);
					//tv.setText(parts[0] + " " + parts[1]);
				}

/************file7*************************/
				inputStream = assetManager.open("out7.txt");
				streamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(streamReader);

				line="";


				while((line = bufferedReader.readLine())!= null)
				{

					String parts[] = line.split("-");

					ContentValues cv = new ContentValues();
					cv.put(KEY_BUS, parts[0]);
					cv.put(KEY_STATION, parts[1]);
					db.insert(DATABASE_TABLE, null, cv);

/*db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES('" + 
		parts[0] + "' ,' " + 
			parts[1] + "');"
			);*/

//map.put(parts[0], parts[1]);
//tv.setText(parts[0] + " " + parts[1]);
				}

/************file8*************************/
				inputStream = assetManager.open("out8.txt");
				streamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(streamReader);

				line="";


				while((line = bufferedReader.readLine())!= null)
				{

					String parts[] = line.split("-");

					ContentValues cv = new ContentValues();
					cv.put(KEY_BUS, parts[0]);
					cv.put(KEY_STATION, parts[1]);
					db.insert(DATABASE_TABLE, null, cv);

/*db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES('" + 
		parts[0] + "' ,' " + 
			parts[1] + "');"
			);*/

//map.put(parts[0], parts[1]);
//tv.setText(parts[0] + " " + parts[1]);
				}


/************file9*************************/
				inputStream = assetManager.open("out9.txt");
				streamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(streamReader);

				line="";


				while((line = bufferedReader.readLine())!= null)
				{

					String parts[] = line.split("-");

					ContentValues cv = new ContentValues();
					cv.put(KEY_BUS, parts[0]);
					cv.put(KEY_STATION, parts[1]);
					db.insert(DATABASE_TABLE, null, cv);

/*db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES('" + 
		parts[0] + "' ,' " + 
			parts[1] + "');"
			);*/

//map.put(parts[0], parts[1]);
//tv.setText(parts[0] + " " + parts[1]);
				}

/************file*************************/
				inputStream = assetManager.open("out.txt");
				streamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(streamReader);

				line="";


				while((line = bufferedReader.readLine())!= null)
				{

					String parts[] = line.split("-");

					ContentValues cv = new ContentValues();
					cv.put(KEY_BUS, parts[0]);
					cv.put(KEY_STATION, parts[1]);
					db.insert(DATABASE_TABLE, null, cv);

/*db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES('" + 
		parts[0] + "' ,' " + 
			parts[1] + "');"
			);*/

//map.put(parts[0], parts[1]);
//tv.setText(parts[0] + " " + parts[1]);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//tvStationList.setText("onCreate");

			Log.i("hinss", "oncreate");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.i("bbbb", "oncreate");
			db.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE);
			onCreate(db);
		}

	}

	public busesDb(Context c){
		ourContext = c;
	}

	public busesDb open() throws SQLException {
		Log.i("ajeeeb", "success");

		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		//tvStationList.setText("open");
		return this;
	}

	public void close(){
		ourHelper.close();
	}

	public long createEntry(String bus, String station) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_BUS, bus);
		cv.put(KEY_STATION, station);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public ArrayList<String> getStations(String bus) {
		// TODO Auto-generated method stub
		String result = "a";
		String[] columns = new String[]{KEY_ROWID, KEY_BUS, KEY_STATION};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_BUS+"='"+bus+"'" , null, null, null, null);

		int i=0;
		int iBus = c.getColumnIndex(KEY_BUS);
		int iStation = c.getColumnIndex(KEY_STATION);
		c.moveToFirst();

		for(; !c.isAfterLast() ;c.moveToNext()){
			i++;
			//if(i==3) break;
			Log.i("rajan",c.getString(iStation));
			result=c.getString(iStation);
			arraylist.add(result);
		}

		ViewRouteFragment vrf=new ViewRouteFragment();
		vrf.sendCursor(c);
		return arraylist;


	}

	public ArrayList<String> findBus(String source, String destination) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_BUS, KEY_STATION};

		//Cursor c = ourDatabase.query(DATABASE_TABLE, columns , KEY_STATION+" IN ( '"+source+"','"+destination+"' )" , null, null, null, null);
		Cursor c1 = ourDatabase.query(DATABASE_TABLE, new String[]{"bus_number"}, KEY_STATION+ "='"+source+"'" +  " INTERSECT SELECT bus_number from " + DATABASE_TABLE + " WHERE " + KEY_STATION+"='"+destination+"'", null,null,null,null);
		int i=0;
		int iBus = c1.getColumnIndex(KEY_BUS);
		int iStation = c1.getColumnIndex(KEY_STATION);
		c1.moveToFirst();
		String result="There is no buses between above two mentioned stations";
		String sno = "Sno \t\t\t\t\t\t\t\t\t\t\t Bus No ";
		arraylist.add(sno);
		for(; !c1.isAfterLast() ;c1.moveToNext()){
			i++;
			//if(i==3) break;

			if(i<10)
				result = i + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + c1.getString(iBus);
			else if(i>=10 & i<=99)
				result = i + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t " + c1.getString(iBus);
			else
				result = i + "\t\t\t\t\t\t\t\t\t\t\t\t\t " + c1.getString(iBus);
			arraylist.add(result);
		}
		//MainActivity m = new MainActivity();
		//m.sendCursor(c1);
		ViewBusFragment vbf = new ViewBusFragment();
		vbf.sendCursor(c1);
		return arraylist;
	}
	public String[] getBuses()
	{
		Cursor cur = ourDatabase.query(true, DATABASE_TABLE, new String[]{KEY_BUS}, null,null,null,null,null,null);


		if(cur.getCount() >= 0)
		{
			String[] str = new String[cur.getCount()];
			int a=0;
			while (cur.moveToNext())
			{
				str[a] = cur.getString(cur.getColumnIndex(KEY_BUS));
				a++;
			}
			return str;
		}
		else
			return new String[] {};
	}
	public String[] getStations()
	{
		Cursor cur1 = ourDatabase.query(true, DATABASE_TABLE , new String[]{KEY_STATION}, null,null,null,null,null,null);
		if(cur1.getCount()>0)
		{
			String[] str1 = new String[cur1.getCount()];
			int i=0;
			while (cur1.moveToNext())
			{
				str1[i] = cur1.getString(cur1.getColumnIndex(KEY_STATION));
				i++;
			}
			return str1;
		}
		else
			return new String[]{};
	}


}
