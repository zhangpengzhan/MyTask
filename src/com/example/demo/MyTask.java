package com.example.demo;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

@SuppressLint("NewApi")
public class MyTask extends AsyncTask<Runnable, Integer, Void> {

	private static String TAG = "MyTask";

	private static MyTask myTask;

	public static MyTask getInstance(Runnable run) {
		if (null != myTask) {
			Log.d(TAG, "getInstance::" + 2);
			if(null != run){
				myTask.doInBackground(run);
			}
			return myTask;
		} else {
			synchronized ("lock") {
				if (null == myTask) {
					Log.d(TAG, "getInstance::" + 0);
					Executor executor = new ThreadPoolExecutor(5, 200, 10, 
							TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
					myTask = new MyTask();
					if(null == run){
						run = myTask.creatRun();
					}
					myTask.executeOnExecutor(executor,run);
					return myTask;
				}else{
					Log.d(TAG, "getInstance::" + 1);
					if(null != run){
						myTask.doInBackground(run);
					}
					return myTask;
				}
			}
		}

	}

	protected Void doInBackground(Runnable... params) {
		params[0].run();
		return null;
	}

	private Runnable creatRun() {
		Runnable run = new Runnable() {
			public void run() {
			}
		};
		return run;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		Log.d(TAG, "onPostExecute==>result::" + result);
	}

	@SuppressLint("NewApi")
	@Override
	protected void onCancelled(Void result) {
		super.onCancelled(result);
		Log.d(TAG, "onCancelled==>result::" + result);
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		Log.d(TAG, "onCancelled");
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		Log.d(TAG, "onProgressUpdate==>value::" + values[0]);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d(TAG, "onPreExecute");
	}

}
