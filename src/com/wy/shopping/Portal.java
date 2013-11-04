package com.wy.shopping;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.wy.shopping.activity.BaseActivity;
import com.wy.shopping.activity.MainActivity;

public class Portal extends BaseActivity {

	private ImageView startImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_portal);
		startImage = (ImageView) findViewById(R.id.start_image);
		startImage.setAnimation(AnimationUtils.loadAnimation(this,
				R.anim.main_start_anim));

		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = new Message();
				msg.what = 0;
				myHandler.sendMessage(msg);
			};
		}.start();

	}

	@SuppressLint("HandlerLeak")
	private Handler myHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			msg = obtainMessage();
			if (msg.what == 0) {
				skip(MainActivity.class);
				overridePendingTransition(R.anim.main_in_anim,
						R.anim.main_out_anim);
				Portal.this.finish();
			}
			super.handleMessage(msg);
		}
	};
}
