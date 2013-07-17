package com.apple.im.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**获得与数据库的连接和关闭连接，只能获得一个实例*/
public class DBUtil {
	private static DBUtil dbUtil;
	
	private DBUtil(){	
	}
	
	/**返回一个数据库操作类的实例，并且只能有一个实例*/
	public synchronized static DBUtil getDBUtil(){
		if(dbUtil==null){
			dbUtil=new DBUtil();
		}
		return dbUtil;
	}
	
	/**获得数据库连接*/
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test","root","qq314507871");
		return connection;
	}
	
	/**关闭数据库连接*/
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
