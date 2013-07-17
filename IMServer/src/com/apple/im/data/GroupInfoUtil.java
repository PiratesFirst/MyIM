package com.apple.im.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**��Ⱥ������*/
public class GroupInfoUtil {
	/**��������û�����Ϣ,�ʺţ��ǳƣ���̬
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
	
	/**���ָ���û����ǳ�*/
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
	
	/**���ָ�����ʺŵ����Ա����*/
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
