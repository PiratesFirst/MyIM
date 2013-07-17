package com.apple.im.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/**�����������ͻ������ӵ��߳�*/
public class ManageServerConnectThread {
	public static HashMap hm =new  HashMap <Integer, ServerConnectClientThread>();
	
	/**���һ���ͻ���ͨ���߳���map��*/
	public static void addClientThreadToMap(int account,ServerConnectClientThread scct){
		hm.put(account,scct);
	}
	/**ɾ��һ���ͻ���ͨ���߳�*/
	public static void delClientThreadFromMap(int account){
		hm.remove(account);
	}
	/**�õ�һ���ͻ���ͨ���߳�*/
	public static ServerConnectClientThread getClientThreadFromMap(int account){
		return (ServerConnectClientThread)hm.get(account);
	}
	
	/**���ص�ǰ�����˵����*/
	public static List getAllOnLineUserid(){
		List list=new ArrayList();
		//ʹ�õ��������
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			list.add((int) it.next());
		}
		return list;
	}
	
	/**�����߳���������ж���Щ�û�����*/
	public static boolean isOnline(int account){
		//ʹ�õ��������
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
