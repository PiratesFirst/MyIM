package com.apple.im.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.apple.im.common.IMMessage;
import com.apple.im.common.InfoType;
import com.apple.im.data.GroupInfoUtil;
import com.apple.im.data.UserInfoUtil;

public class controlAndSendMessage {
	static UserInfoUtil userInfoUtil = new UserInfoUtil();
	static GroupInfoUtil groupInfoUtil = new GroupInfoUtil();
	
	/**向接收人发送消息
	 * @throws IOException */
	public static void sendMessageToReceiver(IMMessage mes){
		ServerConnectClientThread serverConnectClientThread = 
				ManageServerConnectThread.getClientThreadFromMap(mes.getReceiver());
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(serverConnectClientThread.socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			oos.writeObject(mes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**发送好友列表*/
	public static void sendBuddyList(IMMessage mes){
		//操作数据库，返回好友列表，顺带群列表
		String res = userInfoUtil.getBuddyFromDB(mes.getSender()) + "," + groupInfoUtil.getGroup();
		//发送好友列表到客户端
		ServerConnectClientThread scct = ManageServerConnectThread.getClientThreadFromMap(mes.getSender());
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(scct.socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IMMessage ms = new IMMessage();
		ms.setType(InfoType.RET_ONLINE_FRIENDS);
		ms.setContent(res);
		try {
			oos.writeObject(ms);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**删除好友
	 * @throws IOException */
	public static void delBuddy(IMMessage mes){
		if (userInfoUtil.delBuddyFromDB(mes.getSender(), mes.getReceiver())){
			ServerConnectClientThread scct = ManageServerConnectThread.getClientThreadFromMap(mes.getSender());
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(scct.socket.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			IMMessage ms = new IMMessage();
			try {
				oos.writeObject(ms);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**向在线的组成员发送信息包(不向自己发送)*/
	public static void sendGroupMes(IMMessage m){
		try{
			List<Integer> list=groupInfoUtil.getGroupMember(m.getReceiver());
			for(int receiverAccount:list){
				//暂只支持向在线的群成员发送消息
				if(ManageServerConnectThread.isOnline(receiverAccount)){
					System.out.println(receiverAccount+":"+ManageServerConnectThread.isOnline(receiverAccount));
					//如果是自己则不发送
					if(receiverAccount!=m.getSender()){
						System.out.println(m.getSender()+"-"+receiverAccount+"已发送");
						ServerConnectClientThread scc=ManageServerConnectThread.getClientThreadFromMap(receiverAccount);
						ObjectOutputStream oos=new ObjectOutputStream(scc.socket.getOutputStream());
						//只需改变接收者和发送者信息
						IMMessage mes=new IMMessage();
						
						mes.setGaccount(m.getReceiver());
						mes.setGroupNick(groupInfoUtil.getGroupNick(m.getReceiver()));
						mes.setSender(m.getReceiver());
						mes.setSenderAvatar(m.getSenderAvatar());
						mes.setSenderNick(m.getSenderNick());
						mes.setReceiver(receiverAccount);
						mes.setContent(m.getContent());
						mes.setSendTime(m.getSendTime());
						mes.setType(m.getType());
						oos.writeObject(mes);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
