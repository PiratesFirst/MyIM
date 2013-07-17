package com.apple.im.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import android.content.Context;
import android.util.Log;

import com.apple.im.common.IMMessage;
import com.apple.im.common.InfoType;
import com.apple.im.common.User;
import com.apple.im.control.ManageClientConnectServer;
import com.apple.im.view.main.MainActivity;

/**登录注册工具类*/
public class LogoOrRegister {
	private Context context;
	public Socket socket;
	public LogoOrRegister(Context context){
		this.context=context;
		socket = new Socket();
		try {
			socket.connect(new InetSocketAddress("10.0.2.2",8000),2000);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**发送登录信息,成功发送则返回true,未成功发送则返回false*/
	public boolean sendLoginInfo(Object obj){
		boolean b=false;
		Log.e("LogoOrRegister.sendLoginInfo","socket成功连接服务器");
				//连接服务器超时
			ObjectOutputStream oos;
			IMMessage ms = null;
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(obj);
				ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
			    ms=(IMMessage)ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return b;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return b;
			}

			if(ms.getType().equals(InfoType.SUCCESS)){
				//个人信息
				MainActivity.myInfo=ms.getContent();
				//创建一个该账号和服务器保持连接的线程
				ClientConnectServerThread ccst=new ClientConnectServerThread(context,socket);
				//启动该通信线程
				ccst.start();
				//加入到管理类中
				ManageClientConnectServer.addClientConServerThread(((User)obj).getAccount(), ccst);
				b=true;
			}else if(ms.getType().equals(InfoType.FAIL)){
				b=false;
			}
		return b;
	}
	/**发送注册信息,如果出现异常，则返回null*/
	public String sendRegisterInfo(Object obj){
			IMMessage ms = null;
			ObjectOutputStream oos;
			try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(obj);
			ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
			ms =(IMMessage)ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			if(ms.getType().equals(InfoType.REGISTER_SUCCESSFULLY)){
				return InfoType.REGISTER_SUCCESSFULLY;
			}else if(ms.getType().equals(InfoType.REGISTER_FAIL)){
				return InfoType.REGISTER_FAIL;
			}else{
				return InfoType.ACCOUNT_HAS_BEEN_REGISTERED;
			}
	}
}
