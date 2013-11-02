
package com.wy.shopping.activity;

import java.io.Serializable;

import net.tsz.afinal.FinalActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


public class BaseActivity extends FinalActivity implements BaseMethod{

    protected BaseActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void toast(Object obj) {
        toast(obj, 0);
    }

    public void toast(Object obj, int dur) {
        if (obj != null) {
            Toast.makeText(this, obj.toString(), dur).show();
        }else{
            Toast.makeText(this, "提示内容为空", dur).show();
        }
    }

    public void skip(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }

    public void skip(String action) {
        startActivity(new Intent(action));
    }

    public void skip(String action, Serializable... serializ) {
        Intent intent = new Intent(action);
        Bundle extras = new Bundle();
        for (int i = 0; i < serializ.length; i++) {
            Serializable s = serializ[i];
            // 放对象的规则，以顺序为键
            extras.putSerializable(i + "", s);
        }
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void skip(Class<? extends Activity> cls, Serializable... serializ) {
        Intent intent = new Intent(this, cls);
        Bundle extras = new Bundle();
        for (int i = 0; i < serializ.length; i++) {
            Serializable s = serializ[i];
            // 放对象的规则，以顺序为键
            extras.putSerializable(i + "", s);
        }
        intent.putExtras(extras);
        startActivity(intent);
    }

    public Serializable getVo(String key) {
        Intent myIntent = getIntent();
        Bundle bundle = myIntent.getExtras();
        Serializable vo = bundle.getSerializable(key);
        return vo;
    }


    public View makeView(int resId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        return inflater.inflate(resId, null);
    }
    public String getTextRes(int id) {
        return getResources().getString(id);
    }

    public Drawable getDrawableRes(int id) {
        return getResources().getDrawable(id);
    }

    public int getColorRes(int id) {
        return getResources().getColor(id);
    }

    public BaseActivity getActivity() {
        return this;
    }

}
