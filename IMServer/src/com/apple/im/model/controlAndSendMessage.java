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
	
	/**������˷�����Ϣ
	 * @throws IOException */
	public static void sendMessageToReceiver(IMMessage mes) throws IOException{
		ServerConnectClientThread serverConnectClientThread = 
				ManageServerConnectThread.getClientThreadFromMap(mes.getReceiverAccount());
		ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.socket.getOutputStream());
		oos.writeObject(mes);
	}
	
	/**���ͺ����б�*/
	public static void sendBuddyList(IMMessage mes) throws ClassNotFoundException, Exception{
		//�������ݿ⣬���غ����б�˳��Ⱥ�б�
		String res = userInfoUtil.getBuddyFromDB(mes.getSenderAccount()) + "," + groupInfoUtil.getGroup();
		//���ͺ����б��ͻ���
		ServerConnectClientThread scct = ManageServerConnectThread.getClientThreadFromMap(mes.getSenderAccount());
		ObjectOutputStream oos = new ObjectOutputStream(scct.socket.getOutputStream());
		IMMessage ms = new IMMessage();
		ms.setType(IMMessageType.RET_ONLINE_FRIENDS);
		ms.setContent(res);
		oos.writeObject(ms);		
	}
	
	/**ɾ������
	 * @throws IOException */
	public static void delBuddy(IMMessage mes) throws IOException{
		if (userInfoUtil.delBuddyFromDB(mes.getSenderAccount(), mes.getReceiverAccount())){
			ServerConnectClientThread scct = ManageServerConnectThread.getClientThreadFromMap(mes.getSenderAccount());
			ObjectOutputStream oos = new ObjectOutputStream(scct.socket.getOutputStream());
			IMMessage ms = new IMMessage();
			oos.writeObject(ms);
		}
	}
	
	/**�����ߵ����Ա������Ϣ��(�����Լ�����)*/
	public static void sendGroupMes(IMMessage m){
		try{
			List<Integer> list=groupInfoUtil.getGroupMember(m.getReceiverAccount());
			for(int receiverAccount:list){
				//��ֻ֧�������ߵ�Ⱥ��Ա������Ϣ
				if(ManageServerConnectThread.isOnline(receiverAccount)){
					System.out.println(receiverAccount+":"+ManageServerConnectThread.isOnline(receiverAccount));
					//������Լ��򲻷���
					if(receiverAccount!=m.getSenderAccount()){
						System.out.println(m.getSenderAccount()+"-"+receiverAccount+"�ѷ���");
						ServerConnectClientThread scc=ManageServerConnectThread.getClientThreadFromMap(receiverAccount);
						ObjectOutputStream oos=new ObjectOutputStream(scc.socket.getOutputStream());
						//ֻ��ı�����ߺͷ�������Ϣ
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
