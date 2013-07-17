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
	public String getGroup() throws ClassNotFoundException, SQLException{
		String groupInfo = "";
		String sql = "select * from yq_group";
		Connection conn = DBUtil.getDBUtil().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			groupInfo = groupInfo + rs.getInt("gaccount")+"_"+rs.getString("gnick")+"_"+rs.getString("gtrends")+" ";
		}
		return groupInfo;
	}
	
	/**获得指定用户组昵称*/
	public String getGroupNick(int gaccount) throws Exception, SQLException{
		String groupNick = "";
		String sql = "select * from yq_group where gaccount="+gaccount;
		Connection conn = DBUtil.getDBUtil().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			groupNick=rs.getString("gnick");
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
