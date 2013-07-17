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
	
	/**������˷�����Ϣ
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
	
	/**���ͺ����б�*/
	public static void sendBuddyList(IMMessage mes){
		//�������ݿ⣬���غ����б�˳��Ⱥ�б�
		String res = userInfoUtil.getBuddyFromDB(mes.getSender()) + "," + groupInfoUtil.getGroup();
		//���ͺ����б��ͻ���
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
	
	/**ɾ������
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
	
	/**�����ߵ����Ա������Ϣ��(�����Լ�����)*/
	public static void sendGroupMes(IMMessage m){
		try{
			List<Integer> list=groupInfoUtil.getGroupMember(m.getReceiver());
			for(int receiverAccount:list){
				//��ֻ֧�������ߵ�Ⱥ��Ա������Ϣ
				if(ManageServerConnectThread.isOnline(receiverAccount)){
					System.out.println(receiverAccount+":"+ManageServerConnectThread.isOnline(receiverAccount));
					//������Լ��򲻷���
					if(receiverAccount!=m.getSender()){
						System.out.println(m.getSender()+"-"+receiverAccount+"�ѷ���");
						ServerConnectClientThread scc=ManageServerConnectThread.getClientThreadFromMap(receiverAccount);
						ObjectOutputStream oos=new ObjectOutputStream(scc.socket.getOutputStream());
						//ֻ��ı�����ߺͷ�������Ϣ
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
