package com.apple.im.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**�ͻ���ʱ����*/
public class ClientTime {
	/**��ÿͻ���ʱ�䣨�����룩*/
	public static String geTimeNoSecond(){
		Date date=new Date();   
		SimpleDateFormat df=new SimpleDateFormat("MM-dd HH:mm");   
		String time=df.format(date);
		return time;
	}
	/**��ÿͻ���ʱ�䣨���룩*/
	public static String geTime(){
		Date date=new Date();   
		SimpleDateFormat df=new SimpleDateFormat("MM-dd HH:mm:ss");   
		String time=df.format(date);
		return time;
	}
}
