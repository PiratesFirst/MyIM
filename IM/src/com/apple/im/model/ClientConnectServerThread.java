package com.apple.im.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.apple.im.common.IMMessage;
import com.apple.im.common.InfoType;
import com.apple.im.view.buddy.buddyActivity;
import com.apple.im.view.group.GroupActivity;

/**客户端与服务器端连接的线程类*/
public class ClientConnectServerThread extends Thread{
	private Context context;
	private  Socket socket;
	
	/**获得socket实例*/
	public Socket getSocket() {
		return socket;
	}
	
	public ClientConnectServerThread(Context context,Socket socket){
		this.context=context;
		this.socket=socket;
	}
	
	public void run(){
		while(true){
			ObjectInputStream ois = null;
			IMMessage m;
			try{
				ois = new ObjectInputStream(socket.getInputStream());
				m = (IMMessage)ois.readObject();
				if(m.getType().equals(InfoType.COM_MES) || m.getType().equals(InfoType.GROUP_MES)){//如果是聊天消息
					//把从服务器获得的消息通过广播发送
					Intent intent = new Intent("com.apple.im.mes");
					String[] message = new String[]{
							m.getGaccount()+"",
							m.getGroupNick(),
							m.getSender()+"",
							m.getSenderNick(),
							m.getSenderAvatar()+"",
							m.getContent(),
							m.getSendTime(),
							m.getType()
					};
					intent.putExtra("message", message);
					context.sendBroadcast(intent);
				}else if (m.getType().equals(InfoType.RET_ONLINE_FRIENDS)){
					//如果是好友列表,更新好友，群
					String socket[] = m.getContent().split(",");
					buddyActivity.buddyStr=socket[0];
					GroupActivity.groupStr=socket[1];
				}
				if (m.getType().equals(InfoType.SUCCESS)){
					Toast.makeText(context, "操作成功！", Toast.LENGTH_SHORT);
				}
			}catch (Exception e) {
				//e.printStackTrace();
				try {
					if(socket!=null){
						socket.close();
					}
				} catch (IOException e1) {
					//e1.printStackTrace();
				}
			}
		}
	}
}
