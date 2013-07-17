package com.apple.im.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.apple.im.common.User;

/**�û���Ϣ������*/
public class UserInfoUtil {
	/**��¼��֤�û�����
	 * @throws SQLException 
	 * @throws ClassNotFoundException */
	public boolean login(int account,String password) throws ClassNotFoundException, SQLException{
		//�ж�������ʺ��Ƿ�ƥ��
		String sql = "select * from yq_user where uaccount=? and upassword=?";
		Connection conn = DBUtil.getDBUtil().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, account);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		//rs.next()�����ǰ����Ч�򷵻�true,���û���У��򷵻�false
		if (rs != null && rs.next() == true) {
			return true;
		}
		return false;
	}
	/**ע���û���Ϣ*/
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
		//���ظ��µ�����
		int r = ps.executeUpdate();
		if (r > 0) {
			return true;
		}
		return false;
	}
	
	/**ɾ������*/
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
	
	/**���ָ���ʺŵ����к����˻���Ϣ*/
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
	
	/**ָ���ʺŻ���û���Ϣ*/
	public String getUserFromDB(int account){
		String res="";
		try {
			String sql = "select * from yq_user where uaccount="+account;
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			//rs.next()ָ��ӵ�ǰλ������һ�У����ָ��λ����Ч�У��򷵻� true�����������в������κ��У��򷵻� false 
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
	/**�ı�ָ���ʺŵĵ�¼״̬*/
	public boolean changeStateFromDB(int account,int state){
		try {
			String sql = "update yq_user set uisonline=? where uaccount=?";
			Connection conn = DBUtil.getDBUtil().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, state);
			ps.setInt(2, account);
			//������
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
