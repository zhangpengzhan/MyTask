package com.example.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends Activity {
	/**
	 * @Fields demo view
	 */
	private ImageView centerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		centerView = (ImageView) this.findViewById(R.id.imageView1);

		int i = 500 ;
		while(i-- > 0){
			handler.sendEmptyMessage(0);
		}
		
	}


	private BroadcastReceiver netStatusReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent intent) {

			if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
				Log.d(this.getClass().getSimpleName(), "ACTION_BOOT_COMPLETED");
			}
		}
	};

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			MyTask.getInstance(new Runnable() {

				@Override
				public void run() {
					int i = 0;
					while (i++ < 20) {
						Log.d(TAG, "runnable" + this);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		}
	};

	int t = 1;

	private String TAG = getClass().getSimpleName();

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 遥控器按键监听
		int keyCodeVaule = event.getKeyCode();

		switch (keyCodeVaule) {
		case KeyEvent.KEYCODE_DPAD_CENTER | 66:
			Log.d(TAG, "keyCodeVaule::" + keyCodeVaule);
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			Log.d(TAG, "keyCodeVaule::" + keyCodeVaule);
			handler.sendEmptyMessage(0);
			// animateScroll(t++);
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			animateScroll(t + 2);
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			animateScroll(t - 2);
			break;
		case KeyEvent.KEYCODE_DPAD_UP:
			
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void animateScroll(int t) {
		centerView.scrollTo(t, 2 * t);
		// ViewHelper.setTranslationY(centerView, 1*t);
		// ViewHelper.setScaleX(centerView, t*3);
		// ViewHelper.setScaleY(centerView, t*2);
		// ViewHelper.setTranslationX(centerView, t*2);

	}

	private void animatePull(int t) {

		ViewHelper.setScaleX(centerView, t);
		ViewHelper.setScaleY(centerView, t);
		ViewHelper.setTranslationX(centerView, t);
		ViewHelper.setTranslationY(centerView, t);
	}


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}

}
