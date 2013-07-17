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

/**��¼ע�Ṥ����*/
public class LogoOrRegister {
	private Context context;
	public Socket socket;
	
	public LogoOrRegister(Context context){
		this.context = context;
	}
	
	/**���͵�¼��Ϣ*/
	public boolean sendLoginInfo(Object obj) throws IOException{
		boolean b = false;
		try {
			socket=new Socket();
			try{
				socket.connect(new InetSocketAddress("10.0.2.2",5469),2000);
			}catch(SocketTimeoutException e){

				//���ӷ�������ʱ
				return false;
			}
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(obj);
			ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
			IMMessage ms=(IMMessage)ois.readObject();
			if(ms.getType().equals(IMMessageType.SUCCESS)){
				//������Ϣ
				MainActivity.myInfo=ms.getContent();
				//����һ�����˺źͷ������������ӵ��߳�
				ClientConnectServerThread ccst=new ClientConnectServerThread(context,socket);
				//������ͨ���߳�
				ccst.start();
				//���뵽��������
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
	/**����ע����Ϣ
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
