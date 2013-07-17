package com.apple.im.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.apple.im.common.IMMessage;
import com.apple.im.common.InfoType;
import com.apple.im.common.User;
import com.apple.im.data.UserInfoUtil;

public class IMServer {
	public IMServer(){
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8000);
			System.out.println("������������ in "+new Date());
			while(true){
				Socket socket=serverSocket.accept();
				System.out.println("IMServer"+"socket:"+socket);
				//���ܿͻ��˷�������Ϣ
				ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
				User u=(User)ois.readObject();
				IMMessage m=new IMMessage();
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		        if(u.getOperation().equals("login")){ //��¼
		        	int account=u.getAccount();
		        	UserInfoUtil userInfoUtil=new UserInfoUtil();
		        	boolean b=userInfoUtil.login(account, u.getPassword());//�������ݿ���֤�û�
					if(b){
						System.out.println("["+account+"]�����ˣ�");
						//�������ݿ��û�״̬
						userInfoUtil.changeStateFromDB(account, 1);
						//�õ�������Ϣ
						String user=userInfoUtil.getUserFromDB(account);
						//����һ���ɹ���½����Ϣ����������������Ϣ
						m.setType(InfoType.SUCCESS);
						m.setContent(user);
						oos.writeObject(m);
						ServerConnectClientThread cct=new ServerConnectClientThread(socket);//����һ���̣߳��ø��߳���ÿͻ��˱�������
						ManageServerConnectThread.addClientThreadToMap(u.getAccount(),cct);
						cct.start();//������ÿͻ���ͨ�ŵ��߳�
					}else{
						m.setType(InfoType.FAIL);
						oos.writeObject(m);
					}
		        }else if(u.getOperation().equals("register")){
		        	UserInfoUtil userInfoUtil=new UserInfoUtil();
		        	String temp = userInfoUtil.register(u);
		        	if(temp.equals(InfoType.REGISTER_SUCCESSFULLY)){
		        		//����һ��ע��ɹ�����Ϣ��
						m.setType(InfoType.REGISTER_SUCCESSFULLY);
						oos.writeObject(m);
		        	}else if(temp.equals(InfoType.REGISTER_FAIL)){
		        		m.setType(InfoType.REGISTER_FAIL);//ע��ʧ���򷵻�һ��ע��ʧ�ܵ���Ϣ��
						oos.writeObject(m);
		        	}else{
		        		m.setType(InfoType.ACCOUNT_HAS_BEEN_REGISTERED);//�ʺ��ѱ�ע��
						oos.writeObject(m);
		        	}
		        }
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
