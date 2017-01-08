package com.example.new_fowarder_idi_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class UserListDbHelper extends SQLiteOpenHelper{
	
	
	public static final String DATABASE_NAME = "Android.db";
	public static final int VERSION = 3;
	public static final String TABLE = "iDi_SMS_Data";
	public static final String ID = "ID";
	public static final String Lead_Generated_via="Lead_Generated_via";
	public static final String Leads_Name="Leads_Name";
	public static final String Mobile_No="Mobile_No";
	public static final String Course_Searched_For="Course_Searched_For";
	public static final String Location_Searched_for="Location_Searched_for";
	public static final String Date_Time_of_search="Date_Time_of_search";


	public UserListDbHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, TABLE, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			createTable();

		} catch (Exception e) {

		}
	}

	public void createTable() {
		// TODO Auto-generated method stub
		
		SQLiteDatabase sdb = this.getWritableDatabase();
		
		String sql = "CREATE TABLE IF NOT EXISTS "+ TABLE + " (" +ID+
				" INTEGER PRIMARY KEY AUTOINCREMENT, " + Lead_Generated_via
				+ " VARCHAR NOT NULL, " +Leads_Name+ " VARCHAR NOT NULL, "+Mobile_No+ " VARCHAR NOT NULL, "+Course_Searched_For+ " VARCHAR NOT NULL, "+Location_Searched_for +" VARCHAR, " + Date_Time_of_search+" VARCHAR NOT NULL );" ;
		
		System.out.println(sql);
		
		sdb.execSQL(sql);
		
		sdb.close();
		
	}
	
	public void insertData(String lead_generated_via,String leads_name,String mobile_no,String course_searched_for,String location_searched_for ,String date_time_of_search){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(Lead_Generated_via, lead_generated_via);
		values.put(Leads_Name,leads_name );
		values.put(Mobile_No,mobile_no );
		values.put(Location_Searched_for,location_searched_for);
		values.put(Date_Time_of_search, date_time_of_search);


		db.insert(TABLE, null, values);
		System.out.println("Submitted...");

	}
	
	public Cursor getData(){
		String data1,data2;
		StringBuilder data=new StringBuilder("");
		int i=0;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor allData = query(db, "SELECT * From "+TABLE);
		System.out.println("Cursor values:" + allData);

		/*
		if (allData.moveToFirst()) {
			while(!allData.isAfterLast()){
				
				//System.out.println(allData.getString(allData.getColumnIndex(USERNAME)));
				//System.out.println(allData.getString(allData.getColumnIndex(PASSWORD)));
				
				data1=allData.getString(allData.getColumnIndex(USERNAME)).toString();
		//	data.concat(data1);
			System.out.println(data1);
			//data.concat("    ");
			//data.concat(allData.getString(allData.getColumnIndex(PASSWORD)).toString()+"\n");
			data2=allData.getString(allData.getColumnIndex(PASSWORD)).toString();
			System.out.println(data2);
				allData.moveToNext();
				data.append(data1+"    "+data2+   "\n");
				
				System.out.println(data);	
				
			}
			
			
		}
		
		if (allData != null && !allData.isClosed()) {
			allData.close();
		
		}*/
		System.out.println("Data Fetched..."+data);
		return allData;
	}
	
	public Cursor query(SQLiteDatabase db, String query) {

		Cursor cursor = db.rawQuery(query, null);
		System.out.println("Executing Query: " + query);
		return cursor;

	}
	

public void delete()
{
	SQLiteDatabase sdb = this.getWritableDatabase();

	String sql = "(DELETE FROM "+TABLE+");" ;

	System.out.println(sql);

	sdb.execSQL(sql);

	sdb.close();
}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
