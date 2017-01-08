//package com.example.sanjay.idi_app;

package com.example.new_fowarder_idi_app;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by sanjay on 22/8/15.
 */




public class SMS_Data_Sender_Lisner
{

    Cursor All_Data;
    ConnectionDetector CD;
    String SMS_Send_Status;
    Boolean isInternetPresent;
    UserListDbHelper uldh;

    public SMS_Data_Sender_Lisner()
    {
        isInternetPresent = Boolean.valueOf(false);
    }

    public void Send_Store_Data(Context context)
    {
        CD = new ConnectionDetector(context);
        uldh = new UserListDbHelper(context, null, null, 1);
        uldh.createTable();

        isInternetPresent = Boolean.valueOf(CD.isConnectingToInternet());

        if (isInternetPresent.booleanValue())
        {
            All_Data = uldh.getData();
            if (All_Data.getCount() == 0)
            {
                System.out.print("No sms data available.");
                Toast.makeText(context, "No Data Available ", Toast.LENGTH_SHORT).show();
                return;
            }
            All_Data.moveToFirst();
            for (; !All_Data.isAfterLast(); All_Data.moveToNext())
            {
                String s = All_Data.getString(All_Data.getColumnIndex("Lead_Generated_via")).toString();
                String s1 = All_Data.getString(All_Data.getColumnIndex("Leads_Name")).toString();
                String s2 = All_Data.getString(All_Data.getColumnIndex("Mobile_No")).toString();
                String s3 = All_Data.getString(All_Data.getColumnIndex("Course_Searched_For")).toString();
                String s4 = All_Data.getString(All_Data.getColumnIndex("Location_Searched_for")).toString();
                String s5 = All_Data.getString(All_Data.getColumnIndex("Date_Time_of_search"));


                RequestParams requestparams = new RequestParams();
                requestparams.put("Lead_Generated_via", s);
                requestparams.put("Leads_Name", s1);
                requestparams.put("Mobile_No", s2);
                requestparams.put("Course_Searched_For", s3);
                requestparams.put("Location_Searched_for", s4);
                requestparams.put("Date_Time_of_search", s5);
                Toast.makeText(context, (new StringBuilder()).append("Names ").append(s1).toString(), Toast.LENGTH_SHORT).show();
                System.out.println((new StringBuilder()).append("User Request::: ").append(requestparams).toString());
                (new AsyncHttpClient()).post("http://idroidindia.com/web_portal/apis/new_data.php", requestparams, new JsonHttpResponseHandler() {

                   // final SMS_Data_Sender_Lisner this;

                    public void onFailure(int i, Throwable throwable, JSONObject jsonobject)
                    {
                        super.onFailure(i, throwable, jsonobject);
                        SMS_Send_Status = "hot";
                        System.out.println((new StringBuilder()).append("Failure Response ::: ").append(jsonobject).toString());
                    }

                    public void onSuccess(int i, Header aheader[], JSONObject jsonobject)
                    {
                        super.onSuccess(i, aheader, jsonobject);
                        SMS_Send_Status = "Cool";
                        System.out.println((new StringBuilder()).append("Success Response ::: ").append(jsonobject).toString());
                    }


                 /*  {
                        this = SMS_Data_Sender_Lisner.this;
                        super();
                    }*/
                });
            }

            if (All_Data != null && !All_Data.isClosed())
            {
                All_Data.close();
            }
            uldh.delete();
            Toast.makeText(context, "Data Submtted ", Toast.LENGTH_SHORT).show();
            return;
        } else
        {
            Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}