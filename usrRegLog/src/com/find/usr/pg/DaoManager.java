package com.find.usr.pg;

import java.sql.*;

public class DaoManager {
	private static DaoManager daoManager = null;
	
	private DaoManager()
	{
		;
	}
	
	public static synchronized DaoManager getInstance()
	{
		if (daoManager == null)
		{
			daoManager = new DaoManager();
		}
		return daoManager;
	}
	
	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/test";
	    String username = "root";
	    String password = "";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	    	System.out.println("Class not Found!!");
	        e.printStackTrace();
	    } catch (SQLException e) {
	    	System.out.println("Connect Failed!!");
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	public static int insertAccount(UserAccount usrAccount) {
	    Connection conn = getConn();
	    if (conn == null)
	    {
	    	System.out.println("Conn Null!");
	    }
	    int i = 0;
	   
	    String sqlSelect = "select name from useraccount";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sqlSelect);
	        System.out.println(sqlSelect);
	        ResultSet rs = pstmt.executeQuery(sqlSelect);
	        while(rs.next())
	        {
	        	if (rs.getString(1).equals(usrAccount.getuName()))
	        	{
	        		System.out.println("This are data in the database!");
	        		pstmt.close();
	    	        conn.close();
	    	        return i;
	        	}
	        }
	        
	        System.out.println("You can insert the data in the databases!!");
	        pstmt.close();
	        String sql = "insert into useraccount (name,password) values(?,?)";
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	 	    pstmt.setString(1, usrAccount.getuName());
	 	    pstmt.setString(2, usrAccount.getuPassword());
	 	    i = pstmt.executeUpdate();    
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	public static boolean verityAccount(UserAccount usrAccount) {
	    Connection conn = getConn();
	    if (conn == null)
	    {
	    	System.out.println("Conn Null!");
	    }
	   
	    String sqlSelect = "select * from useraccount";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sqlSelect);
	        System.out.println(sqlSelect);
	        ResultSet rs = pstmt.executeQuery(sqlSelect);
	        while(rs.next())
	        {
	        	if (rs.getString(2).equals(usrAccount.getuName()) 
	        			&& rs.getString(3).equals(usrAccount.getuPassword()))
	        	{
	        		pstmt.close();
	    	        conn.close();
	    	        return true;
	        	}
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
