package chn.halo.twiceconfirmdemo;

import java.util.ArrayList;
import java.util.List;

import chn.halo.twiceconfirmdemo.util.TwiceConfirmExitUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			List<Activity> activities = new ArrayList<Activity>();
			activities.add(MainActivity.this);
			TwiceConfirmExitUtil.getInstance().showToast(
					getApplicationContext(), activities);
			return true;// 消费掉后退键
		}
		return super.onKeyDown(keyCode, event);
	}
}
