package com.apple.im.control;

import java.util.HashMap;

import com.apple.im.model.ClientConnectServerThread;
/**客户端线程管理类*/
public class ManageClientConnectServer {
	private static HashMap hm = new HashMap<Integer,ClientConnectServerThread>();
	
	/**把创建好的ClientConnectServerThread放入到hm*/
	public static void addClientConServerThread(int account,ClientConnectServerThread ccst){
		hm.put(account, ccst);
	}
	
	/**可以通过account取得该线程*/
	public static ClientConnectServerThread getClientConnectServerThread(int account){
		return (ClientConnectServerThread)hm.get(account);
	}
	
	
	/**删除一个客户端通信线程*/
	public static void delClientThread(int account){
		hm.remove(account);
	}
}
