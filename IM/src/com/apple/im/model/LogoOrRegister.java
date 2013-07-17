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

/**��¼ע�Ṥ����*/
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
	/**���͵�¼��Ϣ,�ɹ������򷵻�true,δ�ɹ������򷵻�false*/
	public boolean sendLoginInfo(Object obj){
		boolean b=false;
		Log.e("LogoOrRegister.sendLoginInfo","socket�ɹ����ӷ�����");
				//���ӷ�������ʱ
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
				//������Ϣ
				MainActivity.myInfo=ms.getContent();
				//����һ�����˺źͷ������������ӵ��߳�
				ClientConnectServerThread ccst=new ClientConnectServerThread(context,socket);
				//������ͨ���߳�
				ccst.start();
				//���뵽��������
				ManageClientConnectServer.addClientConServerThread(((User)obj).getAccount(), ccst);
				b=true;
			}else if(ms.getType().equals(InfoType.FAIL)){
				b=false;
			}
		return b;
	}
	/**����ע����Ϣ,��������쳣���򷵻�null*/
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
