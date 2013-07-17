package com.apple.im.common;

public class InfoType {
	public static final String SUCCESS="1";//表明是否成功
	public static final String FAIL="2";//表明失败
	public static final String COM_MES="3";//普通信息包
	public static final String GET_ONLINE_FRIENDS="4";//要求在线好友的包
	public static final String RET_ONLINE_FRIENDS="5";//返回在线好友的包
	public static final String LOGIN="7";//请求验证登陆
	public static final String ADD_BUDDY="8";//添加好友
	public static final String DEL_BUDDY="9";//删除好友
	public static final String GROUP_MES="10";//群消息
	public static final String ACCOUNT_HAS_BEEN_REGISTERED = "11";//帐号已被注册
	public static final String REGISTER_SUCCESSFULLY = "12";//注册成功
	public static final String REGISTER_FAIL = "13";//因为其他原因注册失败。
}
