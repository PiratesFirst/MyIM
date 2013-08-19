package com.apple.im.view.buddy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.apple.im.R;
import com.apple.im.common.InfoType;
import com.apple.im.model.SendMessage;
import com.apple.im.view.chat.ChatActivity;
import com.apple.im.view.more.MoreActivity;

public class buddyActivity extends Activity  {
	ListView listView;
	public static String buddyStr = "";
	List<BuddyEntity> buddyEntityList = new ArrayList<BuddyEntity>();//�����б�
	BuddyAdapter buddyAdapter;//�����б��adapter
	BuddyEntity temp;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_buddy);
		
		ImageView avatarIv = (ImageView) findViewById(R.id.buddy_top_avatar);
		TextView nickTv=(TextView) findViewById(R.id.buddy_top_nick);
		nickTv.setText(MoreActivity.me.getNick());
		avatarIv.setImageResource(ChatActivity.avatar[MoreActivity.me.getAvatar()]);
		
		listView = (ListView) findViewById(R.id.listview);
		buddyAdapter = new BuddyAdapter(this,parse(buddyStr));
        listView.setAdapter(buddyAdapter);
        setListViewListener();
	}
	
	private void setListViewListener(){
		/**ע��������*/
		listView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> a, View v, int position,long l) {
				temp= (BuddyEntity) listView.getItemAtPosition(position);
				//������ҳ��
				Intent intent=new Intent(buddyActivity.this,ChatActivity.class);
				intent.putExtra("avatar", temp.getAvatar());
				intent.putExtra("account",temp.getAccount());
				intent.putExtra("nick", temp.getNick());
				startActivity(intent);
			}
        });
		
		/**item��������ע��*/
        listView.setOnItemLongClickListener(new OnItemLongClickListener(){
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int position, long arg3) {
				temp= (BuddyEntity) listView.getItemAtPosition(position);
				listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener(){
					public void onCreateContextMenu(ContextMenu menu,
							View arg1, ContextMenuInfo arg2) {
						menu.setHeaderTitle("����");
						menu.add(0,0,0,"����Ự");
						menu.add(0,1,0,"ɾ������");
						menu.add(0,2,0,"�鿴��������");
					}
				});
				return false;
			}
        });
	}
	
	/**�����Ĳ˵���ѡ��*/
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case 0:
			//������ҳ��
			Intent intent=new Intent(buddyActivity.this,ChatActivity.class);
			intent.putExtra("avatar", temp.getAvatar());
			intent.putExtra("account",temp.getAccount());
			intent.putExtra("nick", temp.getNick());
			startActivity(intent);
			break;
		case 1:
			//�����������һ��ɾ�����ѵİ�
			SendMessage.sendADbuddy(MoreActivity.me.getAccount(), 
					temp.getAccount(), 
					InfoType.DEL_BUDDY);
			//ɾ�������б��еĸú���
			for(int i=0;i<buddyEntityList.size();i++){
				if((buddyEntityList.get(i).getAccount())==temp.getAccount()){
					buddyEntityList.remove(i);
				}
			}
			listView = (ListView) findViewById(R.id.listview);
			buddyAdapter=new BuddyAdapter(this,buddyEntityList);
	        listView.setAdapter(buddyAdapter);
			break;
		case 2:
			//
			break;
		}
		return super.onContextItemSelected(item);
	}
	/**��BuddyEntityʵ����������Ϣ���н���*/
	private List<BuddyEntity> parse(String s){
        String ss[] = buddyStr.split(" ");
        for(String a: ss){
        	if(a!=""){
	        	String b[]=a.split("_");
	            buddyEntityList.add(new BuddyEntity(
	            		Integer.parseInt(b[2]), 
	            		Integer.parseInt(b[0]), 
	            		b[1], 
	            		b[3],
	            		Integer.parseInt(b[4])));
        	}
        }
		return buddyEntityList;
	}
}
