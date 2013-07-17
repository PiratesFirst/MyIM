package com.apple.im.view.register;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.apple.im.R;
import com.apple.im.common.ClientTime;
import com.apple.im.common.User;
import com.apple.im.model.LogoOrRegister;
import com.apple.im.view.login.LoginActivity;

public class RegisterActivity extends Activity{
	String sex = "��";
	User user = new User();
	Button register;
	EditText accountEt;
	EditText passwordEt;
	EditText nickEt;
	RadioGroup group;
	myHandler handler;
	Context context;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.e("error","2");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		Log.e("error","3");
		
		register = (Button)findViewById(R.id.register_btn);
		accountEt=(EditText) findViewById(R.id.register_account);
		passwordEt=(EditText) findViewById(R.id.register_password);
		nickEt=(EditText) findViewById(R.id.register_nick);
		group = (RadioGroup)findViewById(R.id.register_radiogroup);
		
		Log.e("error","4");
		context = this;
		
		
		register.setOnClickListener(new OnClickListener(){
			
			public void onClick(View arg0){
				Log.e("error","5");
				//���Ա�ť���õ���¼�����
				group.setOnCheckedChangeListener(new OnCheckedChangeListener(){
					public void onCheckedChanged(RadioGroup arg0,int id){
						if (id == R.id.register_radio_nv){
							sex = "Ů";
						}
					}
				});
				Log.e("error","6");

				handler = new myHandler();
				
				//���ע�ᰴť�󣬻��������ʺ���Ϣ�������͸��������˽���ע��
				if (accountEt.getText().equals("") || passwordEt.getText().equals("")){
					Toast.makeText(RegisterActivity.this, "�˺Ż����벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
				}else{
					user.setAccount(Integer.parseInt(accountEt.getText().toString()));
					user.setPassword(passwordEt.getText().toString());
					user.setNick(nickEt.getText().toString());
					user.setTrends("���û���ʱû�ж�̬");
					user.setSex(sex);
					user.setAvatar(4);
					user.setLev(0);
					user.setAge(0);
					user.setTime(ClientTime.geTimeNoSecond());
					user.setOperation("register");
					Log.e("error","7");
			        Thread thread = new Thread(new myThread(handler));
			        thread.start();
			        
				}
				
			}	
		});
	}
	
	class myHandler extends Handler{
		public myHandler(){}
		
		public myHandler(Looper l){
			super(l);
		}
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			 switch(msg.what){
			 case 1: 
				 Toast.makeText(RegisterActivity.this, "��ϲ�㣬ע��ɹ� ��", Toast.LENGTH_SHORT).show();
				 startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
				 break;
			 default:		
				 Toast.makeText(RegisterActivity.this, "ע��ʧ��", Toast.LENGTH_SHORT).show();
				 break;
			 }
		 }
	}
	class myThread extends Thread{
		Handler handler;
		public myThread(Handler handler){
			this.handler = handler;
		}
		public void run() {
			// TODO Auto-generated method stub
			boolean b = false;
		
			try {
				b = new LogoOrRegister(RegisterActivity.this).sendRegisterInfo(user);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			Message msg = new Message();
			if (b){
				msg.what = 1;
				Log.e("error","9");
			}else{
				 Log.e("error","10");
				msg.what = 0;
			}
			handler.handleMessage(msg);
		}
	}
}


