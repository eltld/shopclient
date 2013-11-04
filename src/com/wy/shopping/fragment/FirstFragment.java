package com.wy.shopping.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wy.shopping.R;
import com.wy.shopping.adapter.MyExpandAdapter;
import com.wy.shopping.adapter.MyFlipperAdapter;
import com.wy.shopping.pojo.FoodsDetailVo;
import com.wy.shopping.pojo.HotelDetailVo;

public class FirstFragment extends Fragment {

	private int layoutId;

	private ExpandableListView list;

	private ViewPager flipper;

	/** 装载滚动提示的小按钮图标 */
	private LinearLayout tips;

	/** 控制列表的展开，用于每次都定位于展开的项 */
	private int sign = -1;

	/** Flipper滚动对应的下面的提示 */
	private TextView flipperText;

	private List<List<HotelDetailVo>> child;

	private Context context;

	List<FoodsDetailVo> data;

	static int temp = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("有没有："+getActivity());
		
		View view = inflater.inflate(getLayoutId(), container, false);

		flipper = (ViewPager) view.findViewById(R.id.foods_flipper);
		list = (ExpandableListView) view.findViewById(R.id.foods_list);

		tips = (LinearLayout) view.findViewById(R.id.foods_tips);
		flipperText = (TextView) view.findViewById(R.id.foods_flipper_text);

		child = getChildData();
		final MyExpandAdapter expandAdapter = new MyExpandAdapter(context,
				getParentData(), getChildData());
		list.setAdapter(expandAdapter);
		list.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				HotelDetailVo item = (HotelDetailVo) expandAdapter.getChild(
						groupPosition, childPosition);
				// skip("HotelDetailAct", item);
				return true;
			}
		});
		// 控制list只展开一项
		list.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				for (int i = 0; i < expandAdapter.getGroupCount(); i++) {
					if (groupPosition != i) {
						list.collapseGroup(i);
					}
				}
			}
		});
		// 控制list展开的一项为最前面
		list.expandGroup(0);
		list.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {

				if (!getChildData().get(groupPosition).isEmpty()) {// isEmpty没有
					if (sign == -1) {
						list.expandGroup(groupPosition);
						list.setSelectedGroup(groupPosition);
						sign = groupPosition;
					} else if (sign == groupPosition) {
						list.collapseGroup(groupPosition);
						sign = -1;
					} else {
						list.collapseGroup(sign);
						list.expandGroup(groupPosition);
						list.setSelectedGroup(groupPosition);
						sign = groupPosition;
					}
					return true;
				} else {
					// activity.toast("无餐厅数据",0);
					return false;
				}
			}
		});
		data = getFoods();

		initTips();
		MyFlipperAdapter<FoodsDetailVo> adapter = new MyFlipperAdapter<FoodsDetailVo>(
				data, getListView());
		flipper.setAdapter(adapter);
		flipper.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(final int position) {
				temp = position;
				changeTipsState(position);
				flipperText.setText(data.get(position).getName());
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		for (int i = 0; i < flipper.getChildCount(); i++) {
			flipper.getChildAt(i).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// activity.skip("MenuDetailsAct",data.get(temp));
				}
			});
		}

		return view;
	}

	private List<FoodsDetailVo> getFoods() {
		List<FoodsDetailVo> items = new ArrayList<FoodsDetailVo>();
		items.add(new FoodsDetailVo("name", "imageUrl", 25, "介绍"));
		items.add(new FoodsDetailVo("name", "imageUrl", 25, "介绍"));
		items.add(new FoodsDetailVo("name", "imageUrl", 25, "介绍"));
		items.add(new FoodsDetailVo("name", "imageUrl", 25, "介绍"));
		items.add(new FoodsDetailVo("name", "imageUrl", 25, "介绍"));
		return items;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public int getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}

	private List<List<HotelDetailVo>> getChildData() {
		child = new ArrayList<List<HotelDetailVo>>();
			child.add(getChildren1());
			child.add(getChildren2());
			child.add(getChildren3());
			child.add(getChildren4());
			child.add(getChildren5());
			child.add(getChildren6());
			child.add(getChildren7());
		return child;
	}

	/**
	 * @return
	 * @author WangYi
	 * @createtime 2013-3-14 上午11:17:19 最后修改时间 : 更新记录:
	 */
	private List<String> getParentData() {
		List<String> data = new ArrayList<String>();
		data.add("避孕套");//新品，热卖，打折
		data.add("性保健用品");//品牌男装，热卖，打折
		data.add("妇科用药");
		data.add("排卵验孕");
		data.add("润滑剂");
		data.add("避孕药");
		data.add("延时持久");
		return data;
	}

	private void initTips() {
		for (int i = 0; i < data.size(); i++) {
			ImageView iv = new ImageView(context);
			iv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			if (i == 0) {
				iv.setImageResource(R.drawable.point_selected);
				flipperText.setText(data.get(i).getName());
			} else {
				iv.setImageResource(R.drawable.point_selected2);
			}
			iv.setPadding(7, 0, 7, 0);
			tips.addView(iv, i);
		}
	}

	private List<ImageView> getListView() {
		List<ImageView> list = new ArrayList<ImageView>();
		// FinalBitmap bitmap =activity.getBitmap();
		// bitmap.configLoadingImage(R.drawable.loading_image);
		// bitmap.configLoadfailImage(R.drawable.loading_faild);
		for (int i = 0; i < data.size(); i++) {
			ImageView view = new ImageView(context);
			view.setLayoutParams(new LayoutParams(100, 100));
			// bitmap.display(view, data.get(i).getImageUrl());
			view.setImageResource(R.drawable.loading_07);
			list.add(i, view);
		}
		return list;
	}

	/**
	 * 
	 * @author WangYi
	 * @createtime 2013-3-13 下午06:13:30 最后修改时间 : 更新记录:
	 */
	protected void changeTipsState(int position) {
		for (int i = 0; i < tips.getChildCount(); i++) {
			if (i == position) {
				((ImageView) tips.getChildAt(i))
						.setImageResource(R.drawable.point_selected);
			} else {
				((ImageView) tips.getChildAt(i))
						.setImageResource(R.drawable.point_selected2);
			}
		}
	}

	private List<HotelDetailVo> getChildren1(){
		List<HotelDetailVo> items = new ArrayList<HotelDetailVo>();
		HotelDetailVo v1 = new HotelDetailVo();
		v1.setName("杜蕾斯");
		HotelDetailVo v2 = new HotelDetailVo();
		v2.setName("杰斯帮");
		HotelDetailVo v3 = new HotelDetailVo();
		v3.setName("多乐士");
		HotelDetailVo v4 = new HotelDetailVo();
		v4.setName("第六感");
		HotelDetailVo v5 = new HotelDetailVo();
		v5.setName("其他");
		items.add(v1);
		items.add(v2);
		items.add(v3);
		items.add(v4);
		items.add(v5);
		return items;
	}
	
	private List<HotelDetailVo> getChildren2(){
		List<HotelDetailVo> items = new ArrayList<HotelDetailVo>();
		HotelDetailVo v1 = new HotelDetailVo();  
		v1.setName("震动夹");
		HotelDetailVo v2 = new HotelDetailVo();
		v2.setName("起搏器  ");
		HotelDetailVo v3 = new HotelDetailVo();
		v3.setName("自慰器  ");
		HotelDetailVo v4 = new HotelDetailVo();
		v4.setName("震动环  ");
		HotelDetailVo v5 = new HotelDetailVo();
		v5.setName("跳蛋 ");
		HotelDetailVo v6 = new HotelDetailVo();
		v6.setName("震动器  ");
		items.add(v1);
		items.add(v2);
		items.add(v3);
		items.add(v4);
		items.add(v5);
		items.add(v6);
		return items;
	}
	private List<HotelDetailVo> getChildren3(){
		List<HotelDetailVo> items = new ArrayList<HotelDetailVo>();
		HotelDetailVo v1 = new HotelDetailVo();  
		v1.setName("中成药");
		HotelDetailVo v2 = new HotelDetailVo();
		v2.setName("西药 ");
		items.add(v1);
		items.add(v2);
		return items;
	}
	private List<HotelDetailVo> getChildren4(){
		List<HotelDetailVo> items = new ArrayList<HotelDetailVo>();
		HotelDetailVo v1 = new HotelDetailVo();  
		v1.setName("早孕试纸");
		HotelDetailVo v2 = new HotelDetailVo();
		v2.setName("排卵试纸");
		items.add(v1);
		items.add(v2);
		return items;
	}
	private List<HotelDetailVo> getChildren5(){
		List<HotelDetailVo> items = new ArrayList<HotelDetailVo>();
		HotelDetailVo v1 = new HotelDetailVo();  
		v1.setName("无香型");
		HotelDetailVo v2 = new HotelDetailVo();
		v2.setName("水果香型 ");
		HotelDetailVo v3 = new HotelDetailVo();
		v3.setName("花香型   ");
		HotelDetailVo v4 = new HotelDetailVo();
		v4.setName("其他 ");
		items.add(v1);
		items.add(v2);
		items.add(v3);
		items.add(v4);
		return items;
	}
	private List<HotelDetailVo> getChildren6(){
		List<HotelDetailVo> items = new ArrayList<HotelDetailVo>();
		HotelDetailVo v1 = new HotelDetailVo();  
		v1.setName("短效避孕  ");
		HotelDetailVo v2 = new HotelDetailVo();
		v2.setName("紧急避孕  ");
		HotelDetailVo v3 = new HotelDetailVo();
		v3.setName("外用避孕     ");
		items.add(v1);
		items.add(v2);
		items.add(v3);
		return items;
	}
	
	private List<HotelDetailVo> getChildren7(){
		List<HotelDetailVo> items = new ArrayList<HotelDetailVo>();
		HotelDetailVo v1 = new HotelDetailVo();  
		v1.setName("男用延時持久喷剂");
		HotelDetailVo v2 = new HotelDetailVo();
		v2.setName("延时持久难用湿巾");
		HotelDetailVo v3 = new HotelDetailVo();
		v3.setName("外用延时持久男用");
		HotelDetailVo v4 = new HotelDetailVo();
		v4.setName("延时持久男用不麻木");
		HotelDetailVo v5 = new HotelDetailVo();
		v5.setName("延时持久男用套");
		HotelDetailVo v6 = new HotelDetailVo();
		v6.setName("延时防早泄");
		items.add(v1);
		items.add(v2);
		items.add(v3);
		items.add(v4);
		items.add(v5);
		items.add(v6);
		return items;
	}
}
