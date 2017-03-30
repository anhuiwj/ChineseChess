package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utils.DBCon;

public class UserDao {
	
	/**
	 * 根据用户名称查询用户
	 * @param name
	 * @return
	 */
	public String findByName(String name){
		Connection dbCon = DBCon.getConnection();
		String password = "";
         try {
        	 Statement stmt = dbCon.createStatement();  
             String sql = "select password from user where user_name='"+name+"'";  
             ResultSet rs = stmt.executeQuery(sql);  
             while(rs.next()){  
                 password = rs.getString(1);  
             }  
			stmt.close();
			dbCon.close();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
	public int insert(String username,String password){
		Connection dbCon = DBCon.getConnection();
		 int res = 0;
		 String sql = "insert into user (id,user_name,password) values(?,?,?)";
		 PreparedStatement pstmt;
		    try {
		        pstmt = (PreparedStatement) dbCon.prepareStatement(sql);
		        pstmt.setString(1, System.currentTimeMillis()+"");
		        pstmt.setString(2, username);
		        pstmt.setString(3, password);
		        res = pstmt.executeUpdate();
		        pstmt.close();
		        dbCon.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return res;
	}
	
	public static void main(String[] args){
		UserDao dao = new UserDao();
		dao.findByName("admin");
	}
}
