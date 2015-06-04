package chn.halo.twiceconfirmdemo.util;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * 
 *
 * @description 点击两次返回退出程序
 *
 * @author Halo-CHN
 *
 * @mail halo-chn@outlook.com
 *
 * @date 2015年6月3日
 *
 * @version 1.0
 *
 */
public class TwiceConfirmExitUtil {

	private static TwiceConfirmExitUtil instance;

	private TwiceConfirmExitUtil() {
	}

	public static TwiceConfirmExitUtil getInstance() {
		if (null == instance) {
			synchronized (TwiceConfirmExitUtil.class) {
				if (null == instance) {
					instance = new TwiceConfirmExitUtil();
				}
			}
		}
		return instance;
	}

	/* 此处设置程序确认退出时间间隔 */
	private final int FINALSECOND = 3;// 单位：秒

	private int time = 0;

	/**
	 * 计数器
	 */
	Timer timer = new Timer();

	Toast toast = null;

	/**
	 * 
	 * @param context
	 *            当前页
	 * @param activities
	 *            当前应用中已经打开的Activity
	 */
	public void showToast(Context context, List<? extends Activity> activities) {
		if (time == 0) {//计数为0，重新开始计时
			toast = Toast.makeText(context, "再按一次返回将退出程序", FINALSECOND * 1000);
			toast.show();
			timeGos();
		} else {//计数为0，说明在限定时间内在此按了返回键，退出程序
			timer = null;
			toast.cancel();
			// finish掉所有已经打开的Activity
			if (null != activities && activities.size() > 0) {
				for (Activity ac : activities) {
					if (null != ac)
						ac.finish();
				}
			}
			System.exit(0);
		}
	}

	/**
	 * 计时器运行
	 */
	private void timeGos() {
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				time++;
				if (time == FINALSECOND) {
					time = 0;
					this.cancel();
				}
			}
		}, 0, 1000);
	}
}
