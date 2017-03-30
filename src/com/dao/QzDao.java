package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utils.DBCon;

public class QzDao {
	public int insert(String id,String dqqzi,String dqlocation,String target,String targetLocation,String targeColor,String createBy){
		Connection dbCon = DBCon.getConnection();
		 int res = 0;
		 String sql = "insert into chess (id,dqqzi,dqlocation,targetName,targeColor,targetLocation,createDate,createBy) values(?,?,?,?,?,?,?,?)";
		 PreparedStatement pstmt;
		    try {
		        pstmt = (PreparedStatement) dbCon.prepareStatement(sql);
		        pstmt.setString(1, "adminjie");
		        pstmt.setString(2, dqqzi);
		        pstmt.setString(3, dqlocation);
		        pstmt.setString(4, target);
		        pstmt.setString(5, (target!=null&&target!="")?targeColor:"");
		        pstmt.setString(6, (target!=null&&target!="")?targetLocation:"");
		        pstmt.setString(7,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		        pstmt.setString(8, createBy);
		        res = pstmt.executeUpdate();
		        pstmt.close();
		        dbCon.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return res;
	}
	public List<Map<String,Object>> find(String id){
		Connection dbCon = DBCon.getConnection();
		String sql = "select * from chess where id='"+"adminjie"+"' order by createDate desc limit 2";
		 PreparedStatement pstmt;
		    try {
		        pstmt = (PreparedStatement) dbCon.prepareStatement(sql);
		        ResultSet res =  pstmt.executeQuery(sql);
		        ResultSetMetaData rsmd = res.getMetaData();
		        
		        // 取得结果集列数
	            int columnCount = rsmd.getColumnCount();

	            // 构造泛型结果集
	            List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
	            Map<String, Object> data = null;

	            // 循环结果集
	            while (res.next()) {
	                data = new HashMap<String, Object>();
	                // 每循环一条将列名和列值存入Map
	                for (int i = 1; i < columnCount; i++) {
	                    data.put(rsmd.getColumnLabel(i), res.getObject(rsmd
	                            .getColumnLabel(i)));
	                }
	                // 将整条数据的Map存入到List中
	                datas.add(data);
	            }
	            pstmt.close();
		        dbCon.close();
	            return datas;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return null;
	}
	 public static void main(String[] args) {
	        // 实例化对象
		 QzDao  f= new QzDao();
	    	f.find("1490675574442");
	    }
}
