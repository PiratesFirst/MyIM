package com.apple.im.view.register;

import android.app.Activity;
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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.apple.im.R;
import com.apple.im.common.ClientTime;
import com.apple.im.common.InfoType;
import com.apple.im.common.User;
import com.apple.im.model.LogoOrRegister;
import com.apple.im.view.login.LoginActivity;

public class RegisterActivity extends Activity{
	String sex = "男";
	String operatResult = null;
	User user = new User();
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		Button registerButton = (Button) findViewById(R.id.register_btn);
		RadioGroup group = (RadioGroup)findViewById(R.id.register_radiogroup);
		//性别选择
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup arg0,int id) {
				if(id==R.id.register_radio_nv){
					sex="女";
				}
			}
		});
		
		
		registerButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText accountEt = (EditText)findViewById(R.id.register_account);
				EditText passwordEt=(EditText) findViewById(R.id.register_password);
				EditText nickEt=(EditText) findViewById(R.id.register_nick);

				
				
				if (accountEt.getText().equals("") || passwordEt.getText().equals("")){
					Toast.makeText(RegisterActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
				}else{
					user.setAccount(Integer.parseInt(accountEt.getText().toString()));
					user.setPassword(passwordEt.getText().toString());
					user.setNick(nickEt.getText().toString());
					user.setTrends("该用户暂时没有动态");
					user.setSex(sex);
					user.setAvatar(4);
					user.setLev(0);
					user.setAge(0);
					user.setTime(ClientTime.geTimeNoSecond());
					user.setOperation("register");
					
					final Handler handler = new Handler(){
						 public void handleMessage(Message msg) {  
					            switch (msg.what) {  
					            case 0:
									if(operatResult.equals(InfoType.REGISTER_SUCCESSFULLY)){
										//注册成功跳转到登陆
										Toast.makeText(RegisterActivity.this, "恭喜你，注册成功 ！", Toast.LENGTH_SHORT).show();
										startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
										finish();
									}else if (operatResult.equals(InfoType.ACCOUNT_HAS_BEEN_REGISTERED)){
										Log.e("error","帐号被使用");
										Toast.makeText(RegisterActivity.this, "帐号被使用，请重新选择帐号", Toast.LENGTH_SHORT).show();
									}else{
										Log.e("error","注册失败，请联系服务器管理员");
										Toast.makeText(RegisterActivity.this, "注册失败，请联系服务器管理员", Toast.LENGTH_SHORT).show();
									}
					            }
						 }
					};
					
				    Runnable runnable = new Runnable() {
				        public void run() {
							operatResult = new LogoOrRegister(RegisterActivity.this).sendRegisterInfo(user);
							Message msg = new Message();
							msg.what = 0;
							handler.sendMessage(msg);
				        }
				    };
				    
				    new Thread(runnable).start();
				}
			}
		});
	}
}