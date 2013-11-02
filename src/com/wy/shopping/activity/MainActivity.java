package com.wy.shopping.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wy.shopping.R;
import com.wy.shopping.adapter.FragAdapter;
import com.wy.shopping.anim.StretchAnimation;
import com.wy.shopping.fragment.FirstFragment;

public class MainActivity extends FragmentActivity implements OnClickListener,
		StretchAnimation.AnimationListener {

	private FragAdapter adapter;

	private ViewPager pager;

	private Button page1, page2, page3, page4;

	// 屏幕宽度
	private int screentWidth = 0;

	private int screentHeight = 0;

	// View可伸展最长的宽度
	private int maxSize;

	// View可伸展最小宽度
	private int minSize;

	// 当前点击的View
	private View currentView;

	// 显示最长的那个View
	private View preView;

	// 主布局ViewGroup
	private LinearLayout mainContain;

	private StretchAnimation stretchanimation;

	private static View tempView = null;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.layout_main);
		pager = (ViewPager) findViewById(R.id.main_pager);
		page1 = (Button) findViewById(R.id.button1);
		page2 = (Button) findViewById(R.id.button2);
		page3 = (Button) findViewById(R.id.button3);
		page4 = (Button) findViewById(R.id.button4);

		mainContain = (LinearLayout) this.findViewById(R.id.main_contain);

		initCommonData();

		initViewData(0);

		initViewPage();
		initListener();
	}

	private void initCommonData() {
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		screentWidth = metric.widthPixels; // 屏幕宽度（像素）
		screentHeight = metric.heightPixels;
		//
		measureSize(screentWidth);
		stretchanimation = new StretchAnimation(maxSize, minSize,
				StretchAnimation.TYPE.horizontal, 400);
		// 你可以换不能给的插值器
		stretchanimation.setInterpolator(new BounceInterpolator());
		// 动画时间
		stretchanimation.setDuration(400);
		// 回调
		stretchanimation.setOnAnimationListener(this);
	}

	private void initViewData(int index) {

		View child;
		int sizeValue = 0;
		LayoutParams params = null;
		int childCount = mainContain.getChildCount();
		if (index < 0 || index >= childCount) {
			throw new RuntimeException("index 超出范围");
		}

		for (int i = 0; i < childCount; i++) {

			child = mainContain.getChildAt(i);
			child.setOnClickListener(this);
			params = child.getLayoutParams();

			if (i == index) {
				preView = child;
				sizeValue = maxSize;
			} else {
				sizeValue = minSize;
			}
			if (stretchanimation.getmType() == StretchAnimation.TYPE.horizontal) {
				params.width = sizeValue;
			} else if (stretchanimation.getmType() == StretchAnimation.TYPE.vertical) {
				params.height = sizeValue;
			}

			child.setLayoutParams(params);
			currentView = child;
		}
	}

	private void measureSize(int layoutSize) {
		int halfWidth = layoutSize / 2;
		maxSize = halfWidth - 50;
		minSize = (layoutSize - maxSize) / (mainContain.getChildCount() - 1);

	}

	private void initViewPage() {
		List<Fragment> fragments = new ArrayList<Fragment>();
		FirstFragment one = new FirstFragment();
		one.setLayoutId(R.layout.main_frag_one);
//		one.setContext(this);

		FirstFragment two = new FirstFragment();
		two.setLayoutId(R.layout.main_frag_two);
//		two.setContext(this);

		FirstFragment three = new FirstFragment();
		three.setLayoutId(R.layout.main_frag_three);
//		three.setContext(this);

		FirstFragment four = new FirstFragment();
		four.setLayoutId(R.layout.main_frag_three);
//		four.setContext(this);

		fragments.add(one);
		fragments.add(two);
		fragments.add(three);
		fragments.add(four);
		adapter = new FragAdapter(getSupportFragmentManager(), fragments);
		pager.setAdapter(adapter);
		pager.setCurrentItem(0);

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				tempView = mainContain.getChildAt(position);
				changeButton(tempView);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private void initListener() {
		page1.setOnClickListener(this);
		page2.setOnClickListener(this);
		page3.setOnClickListener(this);
		page4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.button1:
			pager.setCurrentItem(0);
			tempView = mainContain.getChildAt(0);
			break;
		case R.id.button2:
			pager.setCurrentItem(1);
			tempView = mainContain.getChildAt(1);
			break;
		case R.id.button3:
			pager.setCurrentItem(2);
			tempView = mainContain.getChildAt(2);
			break;
		case R.id.button4:
			pager.setCurrentItem(3);
			tempView = mainContain.getChildAt(3);
			break;
		}

		changeButton(tempView);

	}

	private void changeButton(View tempView) {
		if (tempView == preView) {
			return;
		} else {
			currentView = tempView;
		}
		clickEvent(currentView);
		onOffClickable(false);
		stretchanimation.startAnimation(currentView);
	}

	@Override
	public void animationEnd(View v) {
		onOffClickable(true);
	}

	private void onOffClickable(boolean isClickable) {
		View child;
		int childCount = mainContain.getChildCount();
		for (int i = 0; i < childCount; i++) {
			child = mainContain.getChildAt(i);
			child.setClickable(isClickable);
		}
	}

	private void clickEvent(View view) {
		View child;
		int childCount = mainContain.getChildCount();
		LinearLayout.LayoutParams params;
		for (int i = 0; i < childCount; i++) {
			child = mainContain.getChildAt(i);
			if (preView == child) {
				params = (android.widget.LinearLayout.LayoutParams) child
						.getLayoutParams();

				if (preView != view) {
					params.weight = 1.0f;
				}
				child.setLayoutParams(params);

			} else {
				params = (android.widget.LinearLayout.LayoutParams) child
						.getLayoutParams();
				params.weight = 0.0f;
				if (stretchanimation.getmType() == StretchAnimation.TYPE.horizontal) {
					params.width = minSize;
				} else if (stretchanimation.getmType() == StretchAnimation.TYPE.vertical) {
					params.height = minSize;
				}

				child.setLayoutParams(params);
			}
		}
		preView = view;

	}

}
