package com.apple.im.control;

import java.util.HashMap;

import com.apple.im.model.ClientConnectServerThread;
/**�ͻ����̹߳�����*/
public class ManageClientConnectServer {
	private static HashMap hm = new HashMap<Integer,ClientConnectServerThread>();
	
	/**�Ѵ����õ�ClientConnectServerThread���뵽hm*/
	public static void addClientConServerThread(int account,ClientConnectServerThread ccst){
		hm.put(account, ccst);
	}
	
	/**����ͨ��accountȡ�ø��߳�*/
	public static ClientConnectServerThread getClientConnectServerThread(int account){
		return (ClientConnectServerThread)hm.get(account);
	}
	
	
	/**ɾ��һ���ͻ���ͨ���߳�*/
	public static void delClientThread(int account){
		hm.remove(account);
	}
}
