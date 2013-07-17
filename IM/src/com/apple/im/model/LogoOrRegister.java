package com.apple.im.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.apple.im.common.IMMessage;
import com.apple.im.common.IMMessageType;
import com.apple.im.common.User;
import com.apple.im.control.ManageClientConnectServer;
import com.apple.im.view.main.MainActivity;
import com.apple.im.view.register.RegisterActivity;

/**登录注册工具类*/
public class LogoOrRegister {
	private Context context;
	public Socket socket;
	
	public LogoOrRegister(Context context){
		this.context = context;
	}
	
	/**发送登录信息*/
	public boolean sendLoginInfo(Object obj) throws IOException{
		boolean b = false;
		try {
			socket=new Socket();
			try{
				socket.connect(new InetSocketAddress("10.0.2.2",5469),2000);
			}catch(SocketTimeoutException e){

				//连接服务器超时
				return false;
			}
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(obj);
			ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
			IMMessage ms=(IMMessage)ois.readObject();
			if(ms.getType().equals(IMMessageType.SUCCESS)){
				//个人信息
				MainActivity.myInfo=ms.getContent();
				//创建一个该账号和服务器保持连接的线程
				ClientConnectServerThread ccst=new ClientConnectServerThread(context,socket);
				//启动该通信线程
				ccst.start();
				//加入到管理类中
				ManageClientConnectServer.addClientConServerThread(((User)obj).getAccount(), ccst);
				b=true;
			}else if(ms.getType().equals(IMMessageType.FAIL)){
				b=false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return b;
	}
	/**发送注册信息
	 * @throws IOException 
	 * @throws ClassNotFoundException */
	public boolean sendRegisterInfo(Object obj) throws IOException, Exception{
		
		boolean b=false;
		
			socket=new Socket();
	
			try {
				Log.e("error","a");
				socket.connect(new InetSocketAddress("10.0.2.2",8000),3000);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				Log.e("error","b");
				return false;
			}
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(obj);
	
			ObjectInputStream ois;
			ois = new ObjectInputStream(socket.getInputStream());

			IMMessage ms;
			ms = (IMMessage)ois.readObject();
			if(ms.getType().equals(IMMessageType.SUCCESS)){
				Log.e("error","c");
				b=true;
			}else if(ms.getType().equals(IMMessageType.FAIL)){
				Log.e("error","d");
				b=false;
			}

		return b;
	}
}
