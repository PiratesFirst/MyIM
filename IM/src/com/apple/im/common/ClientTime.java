package com.apple.im.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**客户端时间类*/
public class ClientTime {
	/**获得客户端时间（不含秒）*/
	public static String geTimeNoSecond(){
		Date date=new Date();   
		SimpleDateFormat df=new SimpleDateFormat("MM-dd HH:mm");   
		String time=df.format(date);
		return time;
	}
	/**获得客户端时间（含秒）*/
	public static String geTime(){
		Date date=new Date();   
		SimpleDateFormat df=new SimpleDateFormat("MM-dd HH:mm:ss");   
		String time=df.format(date);
		return time;
	}
}
