package com.apple.im.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**��������ݿ�����Ӻ͹ر����ӣ�ֻ�ܻ��һ��ʵ��*/
public class DBUtil {
	private static DBUtil dbUtil;
	
	private DBUtil(){	
	}
	
	/**����һ�����ݿ�������ʵ��������ֻ����һ��ʵ��*/
	public synchronized static DBUtil getDBUtil(){
		if(dbUtil==null){
			dbUtil=new DBUtil();
		}
		return dbUtil;
	}
	
	/**������ݿ�����*/
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test","root","qq314507871");
		return connection;
	}
	
	/**�ر����ݿ�����*/
	public void closeConnection(Connection connection){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
