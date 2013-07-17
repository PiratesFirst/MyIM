package com.apple.im.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**组群操作类*/
public class GroupInfoUtil {
	/**获得所有用户组信息,帐号，昵称，动态
	 * @throws SQLException 
	 * @throws ClassNotFoundException */
	public String getGroup() {
		String groupInfo = "";
		String sql = "select * from yq_group";
		Connection conn = null;
		conn = DBUtil.getDBUtil().getConnection();
		if (conn == null){
			return groupInfo;
		}
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				groupInfo = groupInfo + rs.getInt("gaccount")+"_"+rs.getString("gnick")+"_"+rs.getString("gtrends")+" ";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupInfo;
	}
	
	/**获得指定用户组昵称
	 * @throws SQLException 
	 * @throws ClassNotFoundException */
	public String getGroupNick(int gaccount) {
		String groupNick = "";
		String sql = "select * from yq_group where gaccount="+gaccount;
		Connection conn;
		conn = DBUtil.getDBUtil().getConnection();
		ResultSet rs =null;
		PreparedStatement ps = null;
		try {
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()){
			groupNick=rs.getString("gnick");
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groupNick;
	}
	
	/**获得指定组帐号的组成员号码*/
	public List<Integer> getGroupMember(int gaccount){
		List<Integer> res=new ArrayList<Integer>();
		try {
			String sql = "select * from yq_group_member where gaccount="+gaccount;
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res.add(rs.getInt("gmember"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
