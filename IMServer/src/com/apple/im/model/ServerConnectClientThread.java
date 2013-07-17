package com.apple.im.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.apple.im.common.IMMessage;
import com.apple.im.common.InfoType;

public class ServerConnectClientThread extends Thread{
	Socket socket;
	public ServerConnectClientThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		while(true){
			ObjectInputStream ois = null;
			IMMessage mes = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				System.out.println("��ȡ����ɹ�");
				mes = (IMMessage) ois.readObject();
				System.out.println("��ȡ����ɹ�");
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					ois.close();
					socket.close();
					System.out.println("��ȡ����ʧ��");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			if(mes.getType().equals(InfoType.COM_MES)){//�������ͨ��Ϣ��
					controlAndSendMessage.sendMessageToReceiver(mes);
			}else if(mes.getType().equals(InfoType.GROUP_MES)){ //�����Ⱥ��Ϣ
					controlAndSendMessage.sendGroupMes(mes);
			}else if(mes.getType().equals(InfoType.GET_ONLINE_FRIENDS)){//�������������б�
					controlAndSendMessage.sendBuddyList(mes);
			}else if(mes.getType().equals(InfoType.DEL_BUDDY)){ //�����ɾ������
					controlAndSendMessage.delBuddy(mes);
			}	
		}
	}
}
