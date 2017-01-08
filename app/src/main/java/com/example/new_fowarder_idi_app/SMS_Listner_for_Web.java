package com.example.new_fowarder_idi_app;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.Toast;
 
public class SMS_Listner_for_Web extends BroadcastReceiver {

	Boolean isInternetPresent = false;
	ConnectionDetector CD;
	String date_time_of_sms;
	String SMS_Send_Status;
    String Message_Body = "", Message_Sender_Number ;//, Candidate_Interested_Course, Candiate_Mobile_Number, Candidate_Name;
    String Message_Body_For_Sulekha;
	UserListDbHelper uldh;

	String Candidates_Name="",Candidates_Number="",Candidates_Location="",Candidates_Interested_Course="" ,Message_for_Candidate= "";


	
    @SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
    public void onReceive(Context context, Intent intent) {


		//uldh = new UserListDbHelper(context, null, null, 3);
		//uldh.createTable();
    	
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
        
        	
        		for (android.telephony.SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
             
            		Message_Body = Message_Body + smsMessage.getMessageBody();
            		Message_Sender_Number = smsMessage.getOriginatingAddress();
                
            		//Toast.makeText(context, "Sender's Number : "+Message_Sender_Number+"\n"+"SMS Body : "+Message_Body, Toast.LENGTH_LONG).show();
       	     		//System.out.println("System SMS Body 1 " + Message_Body); 
       	     
        		}
        		 
       	     //	Calendar C = Calendar.getInstance();
       	     
			//  SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       	    // 	date_time_of_sms = DF.format(C.getTime());
       	     	   
       	     	
       	     //	CD = new ConnectionDetector(context);
        	
       	     	// get Internet status
       	     //	isInternetPresent = CD.isConnectingToInternet();
                
               if(Message_Sender_Number.equalsIgnoreCase("+918588892879"))
			   {
            	

				  // Message_Body_For_Sulekha = Message_Body.substring(0,158);

				   //get_candidate_details_for_forwarding_sms(Message_Body_For_Sulekha, context);

				   get_candidate_details_for_forwarding_sms(Message_Body, context);

					Log.e("Interested Course ", Candidates_Interested_Course);
					Log.e("Candidates Number ", Candidates_Number);
					Log.e("Candidates Name ", Candidates_Name);


					 Message_for_Candidate = "Hi " + Candidates_Name + ",\niDroidIndia.com (A S/w Training Hub) offers your desired" + Candidates_Interested_Course + " Program. Plz confirm ur SEAT, at: 8588892879";


            	   try {
                	       @SuppressWarnings("deprecation")
						   SmsManager SM = SmsManager.getDefault();

					   		Log.e("Complete Message", Message_for_Candidate);
						   //SM.sendTextMessage("9999065781", null, Message_Body_For_Sulekha, null, null);
					         SM.sendTextMessage(Candidates_Number, null, Message_for_Candidate, null, null);

					   /*if ((Candidates_Interested_Course.equalsIgnoreCase("Advanced PHP Training")  || Candidates_Interested_Course.equalsIgnoreCase("Web Development Training") || Candidates_Interested_Course.equalsIgnoreCase("Mobile Application Development Training") || Candidates_Interested_Course.equalsIgnoreCase("Android Application Development Training") || Candidates_Interested_Course.equalsIgnoreCase("HTML Training") || Candidates_Interested_Course.equalsIgnoreCase("Web Designing Training"))) {

						   SmsManager SMSS = SmsManager.getDefault();
						   SMSS.sendTextMessage(Candidates_Number, null, Message_for_Candidate, null, null);
						   Log.i("SMS Forwarded to ", Message_for_Candidate);
					   	}*/
                	    } catch (Exception E) {
                	    	Log.i("SMS not forwarded ", Candidates_Number);
                	       E.printStackTrace();
                	    }

						/*Log.e("Interested Course ", Candidates_Interested_Course);
						Log.e("Candidates Number ", Candidates_Number);
						Log.e("Candidates Name ", Candidates_Name);*/

					   /* if (Candidates_Interested_Course.equalsIgnoreCase("Web Development Training")) { //|| Candidate_Interested_Course.equalsIgnoreCase("Mobile Application Development Training") || Candidate_Interested_Course.equalsIgnoreCase("Android Application Development Training") || Candidate_Interested_Course.equalsIgnoreCase("HTML Training") || Candidate_Interested_Course.equalsIgnoreCase("Web Designing Training")) {

                        Log.e("Interested Course ", Candidates_Interested_Course);

                        String Message_for_Candidate = "Hi " + Candidates_Name + "\n iDroidIndia.com (A Software  Training Hub) offers " + Candidates_Interested_Course + ". Fresh batches starting soon. To confirm ur seat, plz call 8588892879.";
						try {
								@SuppressWarnings("deprecation")
								SmsManager SM = SmsManager.getDefault();
		                        SM.sendTextMessage(Candidates_Number, null, Message_for_Candidate, null, null);

						} catch (Exception E) {
							Log.i("SMS not forwarded ", Candidates_Number);
								E.printStackTrace();
						}

                        Toast.makeText(context, "SMS successfully send to "+Candidates_Name+" at " + Candidates_Number, Toast.LENGTH_SHORT).show();
                    } */


            	
                	/*if (isInternetPresent)
                	{
            	
                		postWebservice_for_Sulekha(Message_Body, context); 
                		Toast.makeText(context, "Data Submitted successfully for Sulekha from Forwarder App", Toast.LENGTH_SHORT).show();


        			
        			}
					else
					{

						Save_Data_for_Sulekha(Message_Body,context);
						//save to local host
					}*/


                 /*   if (Candidate_Interested_Course.equalsIgnoreCase("Web Development Training")) { //|| Candidate_Interested_Course.equalsIgnoreCase("Mobile Application Development Training") || Candidate_Interested_Course.equalsIgnoreCase("Android Application Development Training") || Candidate_Interested_Course.equalsIgnoreCase("HTML Training") || Candidate_Interested_Course.equalsIgnoreCase("Web Designing Training")) {

                        Log.e("Interested Course ", Candidate_Interested_Course);

                        String Message_for_Candidate = "Hi " + Candidate_Name + "\n iDroidIndia.com (A Software  Training Hub) offers " + Candidate_Interested_Course + ". Fresh batches starting soon. To confirm ur seat, plz call 8588892879.";
                        android.telephony.SmsManager SM = android.telephony.SmsManager.getDefault();

                        SM.sendTextMessage(Candiate_Mobile_Number, null, Message_for_Candidate, null, null);

                        Toast.makeText(context, "SMS successfully send to "+Candidate_Name+" at " + Candiate_Mobile_Number, Toast.LENGTH_SHORT).show();
                    }*/


                }
            	/*else if((Message_Sender_Number.equals("VK-UBNPRO")) || (Message_Sender_Number.equals("VM-UBNPRO")))
            	{	
            			try {
            				
            				@SuppressWarnings("deprecation")
            				SmsManager SM = SmsManager.getDefault();
            				SM.sendTextMessage("9999065781", null, Message_Body, null, null);
            				Log.i("SMS Forwarded to ", "9999065781");
             	     
            				} catch (Exception E) {
            				Log.i("SMS not forwarded ", "9999065781");
            				E.printStackTrace();
            			}           
         	
            			if (isInternetPresent) 
            			{
            		
            					postWebservice_for_Urban_pro(Message_Body, context);
            					Toast.makeText(context, "Data Submitted successfully for UrbanPro ", Toast.LENGTH_SHORT).show();
            			}
					else
						{
							//Save to local host

                            Save_Local_Data_for_Urban_pro(Message_Body, context);


						}


            	}*/
            	else 
            				Toast.makeText(context, "Some other number hence no data send from Forwarder App", Toast.LENGTH_SHORT).show();
        	}
        }


	/************************************************************/
	/************************************************************/

    
	/*	public void postWebservice_for_Urban_pro(String Message_Body, Context Con)
		{
			  int length=Message_Body.length();
              Toast.makeText(Con,""+length,Toast.LENGTH_SHORT).show();
              
              int i=19;
              char ch=Message_Body.charAt(i);
              String name="",number="",location="",course="";


              while(Message_Body.charAt(i)!='-')
              {
                  ch=Message_Body.charAt(i);
                  name=name+ch;
                  //  System.out.print(ch);
                  i++;
              }


             i=i+2;
             while(Message_Body.charAt(i)!='-')
             {
                  i++;
             }

             i=i+8;

              while(Message_Body.charAt(i)!='.')
              {
                  ch=Message_Body.charAt(i);
                  System.out.print(ch);
                  course=course+ch;
                  i++;

              }
              i=i+6;
              while(Message_Body.charAt(i)!=' ')
              {
                  ch=Message_Body.charAt(i);
                  System.out.print(ch);
                  number=number+ch;
                  i++;

              }


               
          	RequestParams params = new RequestParams();
          	params.put("Lead_Generated_via", "UrbanPro");
          	params.put("Leads_Name", name);
          	params.put("Mobile_No", number);
          	params.put("Course_Searched_For", course);
           	params.put("Date_Time_of_search", date_time_of_sms); 
           
          	System.out.println("User Request::: "+params);
       
          	String url="http://idroidindia.com/web_portal/apis/new_data.php";
          	
          	AsyncHttpClient client = new AsyncHttpClient();

          	client.post(url, params, new JsonHttpResponseHandler(){
          	
          		@Override
          		public void onFailure(int statusCode, Throwable e,
          				JSONObject errorResponse) {
          			// TODO Auto-generated method stub
          			super.onFailure(statusCode, e, errorResponse);
          			
          			System.out.println("Failure Response ::: "+errorResponse);

          		}
          		@Override
          		public void onSuccess(int statusCode,
          				org.apache.http.Header[] headers, JSONObject response) {
          			// TODO Auto-generated method stub
          			super.onSuccess(statusCode, headers, response);

          			SMS_Send_Status = "Cool";
          			System.out.println("Success Response ::: "+response); 	 
          		}

          	});

		} */


	/***************************************/
	/***************************************/

   //URBAN_PRO Local Data


/*	public void Save_Local_Data_for_Urban_pro(String Message_Body, Context Con)
	{
		int length=Message_Body.length();
		Toast.makeText(Con,""+length,Toast.LENGTH_SHORT).show();

		int i=19;
		char ch=Message_Body.charAt(i);
		String name="",number="",location="",course="";


		while(Message_Body.charAt(i)!='-')
		{
			ch=Message_Body.charAt(i);
			name=name+ch;
			//  System.out.print(ch);
			i++;
		}


		i=i+2;
		while(Message_Body.charAt(i)!='-')
		{
			i++;
		}

		i=i+8;

		while(Message_Body.charAt(i)!='.')
		{
			ch=Message_Body.charAt(i);
			System.out.print(ch);
			course=course+ch;
			i++;

		}
		i=i+6;
		while(Message_Body.charAt(i)!=' ')
		{
			ch=Message_Body.charAt(i);
			System.out.print(ch);
			number=number+ch;
			i++;

		}


        try {
			uldh.insertData("UrbanPro", name, number, course, location, date_time_of_sms);
		}
		catch (Exception e)
		{
			System.out.print("Error "+e);
		}

	} */








	/***************************************/
	/***************************************/
/*
	private void postWebservice_for_Sulekha(String Message_Body, Context con) {
			// TODO Auto-generated method stub
    
    	   int length=Message_Body.length()-1;
    
           System.out.print("Hello "+length);
           int i=24;
           char ch=Message_Body.charAt(i);
           String name="",number="",location="",course="";


           while(Message_Body.charAt(i)!='(')
           {
               ch=Message_Body.charAt(i);
               name=name+ch;
               i++;
           }


           i++;
           while(Message_Body.charAt(i)!=')')
           {
               ch=Message_Body.charAt(i);
               location=location+ch;
               i++;
           }

           i=i+9;
           while(Message_Body.charAt(i)!=' ')
           {
               ch=Message_Body.charAt(i);
               number=number+ch;
               i++;

           }


           i=i+11;
           String temp="";
           int j=i;           
           
           while (i<length)
           {
               i++;
               ch=Message_Body.charAt(i);
               
               if(temp.equals("Training"))
               {    
            	   break; 
               }
               if(ch==' ')
               { 
            	   temp="";

               }
               else 
               {
               		temp = temp + ch;
               }
           course=course+ch;

           }
           
          // System.out.println("Course Name "+course);



        //Candidate_Interested_Course = course;
        //Candiate_Mobile_Number = number;
        //Candidate_Name =  name;

        
	RequestParams params = new RequestParams();
	params.put("Lead_Generated_via", "Sulekha");
	params.put("Leads_Name", name);
	params.put("Mobile_No", number);
	params.put("Course_Searched_For", course);
	params.put("Location_Searched_for", location);
	params.put("Date_Time_of_search", date_time_of_sms); 
 
	System.out.println("User Request::: "+params);

	String url="http://idroidindia.com/web_portal/apis/new_data.php";
	
	AsyncHttpClient client = new AsyncHttpClient();

	client.post(url, params, new JsonHttpResponseHandler(){
	
		@Override
		public void onFailure(int statusCode, Throwable e,
				JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(statusCode, e, errorResponse);
			
			SMS_Send_Status = "hot";
			System.out.println("Failure Response ::: "+errorResponse);

		}
		@Override
		public void onSuccess(int statusCode,
				org.apache.http.Header[] headers, JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);

			SMS_Send_Status = "Cool";
			System.out.println("Success Response ::: "+response);
		}

	});
	
    }

	/***********************************************************/
	/***********************************************************/
/*	private void Save_Data_for_Sulekha(String Message_Body, Context con) {
		// TODO Auto-generated method stub

		int length=Message_Body.length()-1;

		System.out.print("Hello "+length);
		int i=24;
		char ch=Message_Body.charAt(i);
		String name="",number="",location="",course="";


		while(Message_Body.charAt(i)!='(')
		{
			ch=Message_Body.charAt(i);
			name=name+ch;
			i++;
		}


		i++;
		while(Message_Body.charAt(i)!=')')
		{
			ch=Message_Body.charAt(i);
			location=location+ch;
			i++;
		}

		i=i+9;
		while(Message_Body.charAt(i)!=' ')
		{
			ch=Message_Body.charAt(i);
			number=number+ch;
			i++;

		}


		i=i+11;
		String temp="";
		int j=i;

		while (i<length)
		{
			i++;
			ch=Message_Body.charAt(i);

			if(temp.equals("Training"))
			{
				break;
			}
			if(ch==' ')
			{
				temp="";

			}
			else
			{
				temp = temp + ch;
			}
			course=course+ch;

		}


          try {
			  uldh.insertData("Sulekha", name, number, course, location, date_time_of_sms);
		  }
			  catch (Exception e)
		{
			System.out.print("Error "+e);
		}



	}
*/

	public void get_candidate_details_for_forwarding_sms(String Message_Body, Context con)
	{

		int length=Message_Body.length()-1;

		System.out.print("Hello "+length);
		int i=24;
		char ch=Message_Body.charAt(i);



		while(Message_Body.charAt(i)!='(')
		{
			ch=Message_Body.charAt(i);
			Candidates_Name=Candidates_Name+ch;
			i++;
		}


		i++;
		while(Message_Body.charAt(i)!=')')
		{
			ch=Message_Body.charAt(i);
			Candidates_Location=Candidates_Location+ch;
			i++;
		}

		i=i+9;
		while(Message_Body.charAt(i)!=' ')
		{
			ch=Message_Body.charAt(i);
			Candidates_Number=Candidates_Number+ch;
			i++;

		}


		i=i+11;
		String temp="";
		int j=i;

		while (i<length)
		{
			i++;
			ch=Message_Body.charAt(i);

			if(temp.equals("Training"))
			{
				break;
			}
			if(ch==' ')
			{
				temp="";

			}
			else
			{
				temp = temp + ch;
			}
			Candidates_Interested_Course = Candidates_Interested_Course + ch;

		}
	}


}

