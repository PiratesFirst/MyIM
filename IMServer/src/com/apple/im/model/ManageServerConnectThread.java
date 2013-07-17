package com.apple.im.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/**管理服务器与客户端连接的线程*/
public class ManageServerConnectThread {
	public static HashMap hm =new  HashMap <Integer, ServerConnectClientThread>();
	
	/**添加一个客户端通信线程在map中*/
	public static void addClientThreadToMap(int account,ServerConnectClientThread scct){
		hm.put(account,scct);
	}
	/**删除一个客户端通信线程*/
	public static void delClientThreadFromMap(int account){
		hm.remove(account);
	}
	/**得到一个客户端通信线程*/
	public static ServerConnectClientThread getClientThreadFromMap(int account){
		return (ServerConnectClientThread)hm.get(account);
	}
	
	/**返回当前在线人的情况*/
	public static List getAllOnLineUserid(){
		List list=new ArrayList();
		//使用迭代器完成
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			list.add((int) it.next());
		}
		return list;
	}
	
	/**根据线程连接情况判断哪些用户在线*/
	public static boolean isOnline(int account){
		//使用迭代器完成
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			int a=(int) it.next();
			if(a==account){
				return true;
			}
		}
		return false;
	}
}
