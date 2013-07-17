package com.apple.im.view.buddy;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apple.im.R;
import com.apple.im.view.chat.ChatActivity;

/**好友activity信息适配器*/
public class BuddyAdapter extends BaseAdapter{
	private Context context;
	private List<BuddyEntity> list;
	LayoutInflater inflater;
	
	public BuddyAdapter(Context context, List<BuddyEntity> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
	@Override
	/**计算出item的数量*/
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
	/**获得指定位置的item*/
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}
	@Override
	/**获得Item的*/
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.buddy_listview_item, null);
		
		ImageView avatar = (ImageView)convertView.findViewById(R.id.iv_avatar);
		TextView nick = (TextView)convertView.findViewById(R.id.tv_nick);
		TextView trends=(TextView) convertView.findViewById(R.id.tv_trends);
		
		BuddyEntity buddyEntity=list.get(position);
		int id = buddyEntity.getAvatar();
		avatar.setImageResource(ChatActivity.avatar[id]);
		nick.setText(buddyEntity.getNick());
		trends.setText(buddyEntity.getTrends());
				
		return convertView;
	}
	
}
