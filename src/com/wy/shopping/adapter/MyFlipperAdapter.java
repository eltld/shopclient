/*
 * 创建日期 2013-3-13
 * 
 * 成都天和软件公司
 * 电话：028-85425861
 * 传真：028-85425861-8008
 * 邮编：610041
 * 地址：成都市武侯区航空路6号丰德万瑞中心B座1001
 * 版权所有
 */
package com.wy.shopping.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.wy.shopping.pojo.FoodsDetailVo;

/**
 * 
 * @author WangYi
 * @param <E>
 * @createtime 2013-3-13 下午05:28:09
 * 最后修改时间 : 
 * 更新记录:
 */
public class MyFlipperAdapter<E> extends PagerAdapter  {

	public List<FoodsDetailVo> data;
	
//	public BaseActivity activity;
	
	private List<ImageView> image;
	
	public MyFlipperAdapter(List<FoodsDetailVo> data,List<ImageView> image){
		this.data=data;
		this.image=image;
	}
	
	@Override
	public int getCount() {
		return image.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==(arg1);
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(image.get(position));
	}
	@Override
	public Object instantiateItem(View container, final int position) {
		((ViewPager) container).addView(image.get(position), 0);
		image.get(position).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//			activity.skip("MenuDetailsAct",data.get(position));
			}
		});
		return image.get(position);
	}
}
