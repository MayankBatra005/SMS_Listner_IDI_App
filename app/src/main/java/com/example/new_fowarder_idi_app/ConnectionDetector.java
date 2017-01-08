package com.example.new_fowarder_idi_app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;

public class ConnectionDetector {

	private Context _context;

	public ConnectionDetector(Context context){
		this._context = context;
	}

	public boolean isConnectingToInternet(){
		ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
		  if (connectivity != null)
		  {
			  NetworkInfo[] info = connectivity.getAllNetworkInfo();
			  if (info != null)
				  for (int i = 0; i < info.length; i++)
					  if (info[i].getState() == NetworkInfo.State.CONNECTED)
					  {
						  Log.i("Network Info", info[i].toString());
						  return true;
					  }

		  }
		  return false;
	}

	public boolean isOnline()
	{
		boolean flag = false;
		Runtime runtime = Runtime.getRuntime();
		int i;
		try
		{
			i = runtime.exec("/system/bin/ping -c 1 8.8.8.8").waitFor();
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
			return false;
		}
		catch (InterruptedException interruptedexception)
		{
			interruptedexception.printStackTrace();
			return false;
		}
		if (i == 0)
		{
			flag = true;
		}
		return flag;
	}
}
