package com.apple.im.model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.apple.im.common.IMMessage;
import com.apple.im.common.IMMessageType;
import com.apple.im.data.GroupInfoUtil;
import com.apple.im.data.UserInfoUtil;

public class controlAndSendMessage {
	static UserInfoUtil userInfoUtil = new UserInfoUtil();
	static GroupInfoUtil groupInfoUtil = new GroupInfoUtil();
	
	/**向接收人发送消息
	 * @throws IOException */
	public static void sendMessageToReceiver(IMMessage mes) throws IOException{
		ServerConnectClientThread serverConnectClientThread = 
				ManageServerConnectThread.getClientThreadFromMap(mes.getReceiverAccount());
		ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.socket.getOutputStream());
		oos.writeObject(mes);
	}
	
	/**发送好友列表*/
	public static void sendBuddyList(IMMessage mes) throws ClassNotFoundException, Exception{
		//操作数据库，返回好友列表，顺带群列表
		String res = userInfoUtil.getBuddyFromDB(mes.getSenderAccount()) + "," + groupInfoUtil.getGroup();
		//发送好友列表到客户端
		ServerConnectClientThread scct = ManageServerConnectThread.getClientThreadFromMap(mes.getSenderAccount());
		ObjectOutputStream oos = new ObjectOutputStream(scct.socket.getOutputStream());
		IMMessage ms = new IMMessage();
		ms.setType(IMMessageType.RET_ONLINE_FRIENDS);
		ms.setContent(res);
		oos.writeObject(ms);		
	}
	
	/**删除好友
	 * @throws IOException */
	public static void delBuddy(IMMessage mes) throws IOException{
		if (userInfoUtil.delBuddyFromDB(mes.getSenderAccount(), mes.getReceiverAccount())){
			ServerConnectClientThread scct = ManageServerConnectThread.getClientThreadFromMap(mes.getSenderAccount());
			ObjectOutputStream oos = new ObjectOutputStream(scct.socket.getOutputStream());
			IMMessage ms = new IMMessage();
			oos.writeObject(ms);
		}
	}
	
	/**向在线的组成员发送信息包(不向自己发送)*/
	public static void sendGroupMes(IMMessage m){
		try{
			List<Integer> list=groupInfoUtil.getGroupMember(m.getReceiverAccount());
			for(int receiverAccount:list){
				//暂只支持向在线的群成员发送消息
				if(ManageServerConnectThread.isOnline(receiverAccount)){
					System.out.println(receiverAccount+":"+ManageServerConnectThread.isOnline(receiverAccount));
					//如果是自己则不发送
					if(receiverAccount!=m.getSenderAccount()){
						System.out.println(m.getSenderAccount()+"-"+receiverAccount+"已发送");
						ServerConnectClientThread scc=ManageServerConnectThread.getClientThreadFromMap(receiverAccount);
						ObjectOutputStream oos=new ObjectOutputStream(scc.socket.getOutputStream());
						//只需改变接收者和发送者信息
						IMMessage mes=new IMMessage();
						
						mes.setGaccount(m.getReceiverAccount());
						mes.setGroupNick(groupInfoUtil.getGroupNick(m.getReceiverAccount()));
						mes.setSenderAccount(m.getReceiverAccount());
						mes.setSenderAvatar(m.getSenderAvatar());
						mes.setSenderNick(m.getSenderNick());
						mes.setReceiverAccount(receiverAccount);
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
