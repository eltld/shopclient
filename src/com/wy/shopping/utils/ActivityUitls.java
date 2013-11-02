package com.wy.shopping.utils;

import java.io.Serializable;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

public class ActivityUitls {

	public static void toast(Object obj, int dur,Activity act) {
		if (obj != null) {
            Toast.makeText(act, obj.toString(), dur).show();
        }else{
            Toast.makeText(act, "提示内容为空", dur).show();
        }
		
	}

	public static void skip(Class<? extends Activity> cls) {
		
	}

	public static void skip(String action) {
		// TODO Auto-generated method stub
		
	}

	public static void skip(String action, Serializable... serializ) {
		// TODO Auto-generated method stub
		
	}

	public static void skip(Class<? extends Activity> cls, Serializable... serializ) {
		// TODO Auto-generated method stub
		
	}

	public static Serializable getVo(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public static View makeView(int resId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getTextRes(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Drawable getDrawableRes(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getColorRes(int id) {
		// TODO Auto-generated method stub
		return 0;
	}


}
