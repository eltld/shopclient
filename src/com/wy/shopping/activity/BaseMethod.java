package com.wy.shopping.activity;

import java.io.Serializable;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;

public interface BaseMethod {

	void toast(Object obj, int dur);

	void skip(Class<? extends Activity> cls);

	void skip(String action);

	void skip(String action, Serializable... serializ);

	void skip(Class<? extends Activity> cls, Serializable... serializ);

	Serializable getVo(String key);

	View makeView(int resId);

	String getTextRes(int id);

	Drawable getDrawableRes(int id);

	int getColorRes(int id);

	BaseActivity getActivity();
}
