package com.apple.im.view.chat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apple.im.R;

public class ChatAdapter extends BaseAdapter{
	private Context context;
	private List<ChatEntity> list;
	LayoutInflater inflater;
	private int[] avatar=new int[]{0,R.drawable.h001,R.drawable.h002,R.drawable.h003,
			R.drawable.h004,R.drawable.h005,R.drawable.h006};
	
	public ChatAdapter(Context context,List<ChatEntity> list){
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView avatar;
		TextView content;
		TextView time;
		ChatEntity ce=list.get(position);
		if(ce.isLeft()){
			convertView = inflater.inflate(R.layout.chat_listview_item_left, null);
			
			avatar=(ImageView) convertView.findViewById(R.id.avatar_chat_left);
			content=(TextView) convertView.findViewById(R.id.message_chat_left);
			time=(TextView) convertView.findViewById(R.id.sendtime_chat_left);
			int id=ce.getAvatar();
			avatar.setImageResource(ChatActivity.avatar[id]);
			content.setText(ce.getContent());
			time.setText(ce.getTime());
			
			TextView tv_nick=(TextView) convertView.findViewById(R.id.chat_left_nick);
			tv_nick.setText(ce.getNick());
		}else{
			convertView=inflater.inflate(R.layout.chat_listview_item_right, null);
			
			avatar=(ImageView) convertView.findViewById(R.id.avatar_chat_right);
			content=(TextView) convertView.findViewById(R.id.message_chat_right);
			time=(TextView) convertView.findViewById(R.id.sendtime_chat_right);
			int id=ce.getAvatar();
			avatar.setImageResource(ChatActivity.avatar[id]);
			content.setText(ce.getContent());
			time.setText(ce.getTime());
		}

		return convertView;
	}
}
