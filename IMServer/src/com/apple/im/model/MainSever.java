package com.apple.im.model;

import com.apple.im.data.DBUtil;

public class MainSever {
	private static IMServer server = null;
	
	public static void main(String[] args) {
		start();
	}
	public synchronized static void start(){
		if(server==null)
			server=new IMServer();
	}
}
