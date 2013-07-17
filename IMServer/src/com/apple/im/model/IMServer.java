package com.apple.im.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.apple.im.common.IMMessage;
import com.apple.im.common.IMMessageType;
import com.apple.im.common.User;
import com.apple.im.data.UserInfoUtil;

public class IMServer {
	public IMServer(){
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8000);
			System.out.println("服务器已启动 in "+new Date());
			while(true){
				Socket socket=serverSocket.accept();
				//接受客户端发来的信息
				ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
				User u=(User) ois.readObject();
				IMMessage m=new IMMessage();
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		        if(u.getOperation().equals("login")){ //登录
		        	int account=u.getAccount();
		        	UserInfoUtil userInfoUtil=new UserInfoUtil();
		        	boolean b=userInfoUtil.login(account, u.getPassword());//连接数据库验证用户
					if(b){
						System.out.println("["+account+"]上线了！");
						//更改数据库用户状态
						userInfoUtil.changeStateFromDB(account, 1);
						//得到个人信息
						String user=userInfoUtil.getUserFromDB(account);
						//返回一个成功登陆的信息包，并附带个人信息
						m.setType(IMMessageType.SUCCESS);
						m.setContent(user);
						oos.writeObject(m);
						ServerConnectClientThread cct=new ServerConnectClientThread(socket);//单开一个线程，让该线程与该客户端保持连接
						ManageServerConnectThread.addClientThreadToMap(u.getAccount(),cct);
						cct.start();//启动与该客户端通信的线程
					}else{
						m.setType(IMMessageType.FAIL);
						oos.writeObject(m);
					}
		        }else if(u.getOperation().equals("register")){
		        	UserInfoUtil userInfoUtil=new UserInfoUtil();
		        	if(userInfoUtil.register(u)){
		        		//返回一个注册成功的信息包
						m.setType(IMMessageType.SUCCESS);
						oos.writeObject(m);
		        	}else {
		        		m.setType(IMMessageType.FAIL);//注册失败则返回一个注册失败的信息包
						oos.writeObject(m);
		        	}
		        }
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
