package com.apple.im.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.apple.im.common.User;

/**用户信息操作类*/
public class UserInfoUtil {
	/**登录验证用户密码
	 * @throws SQLException 
	 * @throws ClassNotFoundException */
	public boolean login(int account,String password) throws ClassNotFoundException, SQLException{
		//判断密码和帐号是否匹配
		String sql = "select * from yq_user where uaccount=? and upassword=?";
		Connection conn = DBUtil.getDBUtil().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, account);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		//rs.next()如果当前行有效则返回true,如果没有行，则返回false
		if (rs != null && rs.next() == true) {
			return true;
		}
		return false;
	}
	/**注册用户信息*/
	public boolean register(User user) throws  SQLException, Exception{
		String sql = "insert into yq_user values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DBUtil.getDBUtil().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, user.getAccount());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getNick());
		ps.setInt(4, user.getAvatar());
		ps.setString(5, user.getTrends());
		ps.setString(6, user.getSex());
		ps.setInt(7, user.getAge());
		ps.setInt(8, user.getLev());
		ps.setString(9, user.getTime());
		ps.setInt(10, 0);
		//返回更新的行数
		int r = ps.executeUpdate();
		if (r > 0) {
			return true;
		}
		return false;
	}
	
	/**删除好友*/
	public boolean delBuddyFromDB(int myAccount,int dfAccount){
		try {
			String sql = "delete  from yq_buddy where baccount=? and bbuddy=?";
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, myAccount);
			ps.setInt(2, dfAccount);
			int r = ps.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**获得指定帐号的所有好友账户信息*/
	public String getBuddyFromDB(int account){
		String res="";
		try {
			String sql = "select * from yq_buddy where baccount="+account;
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String s="";
				String sql2 = "select * from yq_user where uaccount="+rs.getInt("bbuddy");
				Connection conn2 = DBUtil.getDBUtil().getConnection();
				PreparedStatement ps2 = conn2.prepareStatement(sql2);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()){
					s=rs2.getInt("uaccount")+"_"+rs2.getString("unick")+"_"
							+rs2.getString("uavatar")+"_"+rs2.getString("utrends")+"_"+rs2.getInt("uisonline")+" ";
				}
				res+=s;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**指定帐号获得用户信息*/
	public String getUserFromDB(int account){
		String res="";
		try {
			String sql = "select * from yq_user where uaccount="+account;
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			//rs.next()指针从当前位置下移一行，如果指针位于有效行，则返回 true；如果结果集中不存在任何行，则返回 false 
			while(rs.next()){
				res=res+rs.getInt("uaccount")+"_"+rs.getString("unick")+"_"
						+rs.getString("uavatar")+"_"+rs.getString("utrends")+"_"
						+rs.getString("usex")+"_"+rs.getInt("uage")+"_"+rs.getInt("ulev");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	/**改变指定帐号的登录状态*/
	public boolean changeStateFromDB(int account,int state){
		try {
			String sql = "update yq_user set uisonline=? where uaccount=?";
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, state);
			ps.setInt(2, account);
			//返回行
			int r = ps.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
