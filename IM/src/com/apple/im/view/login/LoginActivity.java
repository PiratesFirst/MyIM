package com.apple.im.view.login;


import java.io.IOException;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apple.im.R;
import com.apple.im.common.IMMessage;
import com.apple.im.common.InfoType;
import com.apple.im.common.User;
import com.apple.im.control.ManageActivity;
import com.apple.im.control.ManageClientConnectServer;
import com.apple.im.model.LogoOrRegister;
import com.apple.im.view.main.MainActivity;
import com.apple.im.view.register.RegisterActivity;



public class LoginActivity extends Activity{
	public static String userInfo = null;
	EditText accountEt = null,passwordEt = null;
	Dialog dialog = null;
	boolean b = false;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		accountEt = (EditText) findViewById(R.id.et_account);
		passwordEt = (EditText) findViewById(R.id.et_password);
		Button loginButton = (Button) findViewById(R.id.btn_login);
		Button registerButton = (Button)findViewById(R.id.btn_register);
		
		//给登录按钮设置点击事件监听
		loginButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
		
				if(accountEt.getText().equals("") || passwordEt.getText().equals("")){
					Toast.makeText(LoginActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
				}else{
					dialog = ProgressDialog.show(LoginActivity.this, null,  "正在登陆中 …", true, true);
					new Thread(new Runnable(){
						public void run(){
							b = login(Integer.parseInt(accountEt.getText().toString()), passwordEt.getText().toString());
							Message mes = new Message();
							mes.what = 1;
							handler.sendMessage(mes);						}
					}).start();
				}
			}
		});
		
		//给注册按钮设置点击事件监听
		registerButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Log.e("error","re");
				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
				Log.e("error","re2");
			}
		});
		
		//
		ManageActivity.addActiviy("LoginActivity", this);
	}
	
	boolean login(int a, String p) {
		User user=new User();
		user.setAccount(a);
		user.setPassword(p);
		user.setOperation("login");
		boolean b = false;
		b = new LogoOrRegister(this).sendLoginInfo(user);
		//登陆成功
		if(b){
			try {
				//发送一个要求返回在线好友的请求的Message
				ObjectOutputStream oos = new ObjectOutputStream	(ManageClientConnectServer.getClientConnectServerThread(user.getAccount()).getSocket().getOutputStream());
				IMMessage m=new IMMessage();
				m.setType(InfoType.GET_ONLINE_FRIENDS);
				m.setSender(user.getAccount());
				oos.writeObject(m);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.finish();
			return true;
		}else {
			return false;
		}
	}
	
	private Handler handler=new Handler(){  
        public void handleMessage(Message msg){  
            switch(msg.what){  
            case 1:
                dialog.dismiss();  
                if(b){
                	startActivity(new Intent(LoginActivity.this, MainActivity.class));
                	b = false;
                }else{
                	Toast.makeText(LoginActivity.this, "登陆失败！", Toast.LENGTH_SHORT).show();
                }
                break;  
             default:
                	break;
            }  
        }  
    }; 
}