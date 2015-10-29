package com.you.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;


/**
 * 数据库方法类，主要用到的类有DriverManager，Connection，PreparedStatement，ResultSet
 * 包括数据库连接，数据库查询操作和更新操作，关闭数据库连接
 * @author YOU
 *
 */
public class DBUtil {

	
	private static final String CONNECTION_DRIVER_STR;
	private static final String CONNECTION_STR;
	private static final String DB_USERNAME;
	private static final String DB_PWD;
	
	static{
		PropertiesUtil.loadFile("dbconfig.properties");
		CONNECTION_DRIVER_STR = PropertiesUtil.getPropertyValue("driverName");
		CONNECTION_STR = PropertiesUtil.getPropertyValue("url");
		DB_USERNAME = PropertiesUtil.getPropertyValue("user");
		DB_PWD = PropertiesUtil.getPropertyValue("password");
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection con = null;
		try {
			Class.forName(CONNECTION_DRIVER_STR);
			con = DriverManager.getConnection(CONNECTION_STR);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;		
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @param st
	 * @param rs
	 */
	public static void closeConnection(Connection con,Statement st,ResultSet rs){
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(st != null)
				st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 查询
	 * @param sql
	 * @return
	 */
	public static Map[] runQuery(String sql){
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = null;
		ResultSet rs = null;
		Map[] rows = null;
		
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			Result result = ResultSupport.toResult(rs);
			rows = result.getRows();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeConnection(con, pre, rs);
		}
		
		return rows;		
	}
	
	/**
	 * 更新操作
	 * @param sql
	 * @return
	 */
	public static int runUpdate(String sql){
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pre = null;
		int result = 0;
		try {
			pre = con.prepareStatement(sql);
			result = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			DBUtil.closeConnection(con, pre, null);
		}
		return result;	
	}
}
