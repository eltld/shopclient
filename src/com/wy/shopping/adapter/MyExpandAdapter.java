package com.wy.shopping.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wy.shopping.R;
import com.wy.shopping.pojo.HotelDetailVo;

/**
 * 
 * @author WangYi
 * @createtime 2013-3-14 上午10:12:29 最后修改时间 : 更新记录:
 */
public class MyExpandAdapter extends BaseExpandableListAdapter {

	private List<String> parentData;

	private List<List<HotelDetailVo>> childData;

	Context context;

	public MyExpandAdapter(Context context, List<String> parentData,
			List<List<HotelDetailVo>> childData) {
		this.childData = childData;
		this.parentData = parentData;
		this.context = context;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childData.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		convertView = makeView(R.layout.expand_list_child_item);
		ImageView image = (ImageView) convertView
				.findViewById(R.id.expand_image);
		TextView title = (TextView) convertView.findViewById(R.id.expand_title);
		// activity.getBitmap().display(image,
		// childData.get(groupPosition).get(childPosition).getUrl(),
		// R.drawable.loading_image, R.drawable.loading_faild);
		image.setImageResource(R.drawable.ic_launcher);
		title.setText(childData.get(groupPosition).get(childPosition).getName());
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childData.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return parentData.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return parentData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		convertView = makeView(R.layout.expand_list_item);
		ImageView image = (ImageView) convertView
				.findViewById(R.id.expand_image);
		TextView title = (TextView) convertView.findViewById(R.id.expand_title);
		title.setText(parentData.get(groupPosition));
		image.setVisibility(View.INVISIBLE);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	 public View makeView(int resId) {
	        LayoutInflater inflater = LayoutInflater.from(context);
	        return inflater.inflate(resId, null);
	    }
	 
}
