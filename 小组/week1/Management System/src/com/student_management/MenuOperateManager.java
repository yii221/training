package com.student_management;

import com.student_management.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenuOperateManager {

    //注册功能
    public static void uregister(String name, String psw, int judge, String phonenumber) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql = "insert into users(`username`,`pwd`,`judge`,`phonenumber`)" + "VALUES('"+name+"','"+psw+"','"+judge+"','"+phonenumber+"')";

            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("插入成功！");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }


    //登录功能
    public static void ulogin(String name,String psw) throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql = "SELECT * FROM users WHERE username='"+name+"'AND pwd='"+psw+"'";

            rs = st.executeQuery(sql);
            if(!rs.isBeforeFirst()){
                System.out.println("登录失败，请重新尝试！");
            }
            else
            {
                if(rs.next())
                {
                    String i=rs.getString("judge");
                    if("1".equals(i))
                    {
                        System.out.println("登录成功，你的身份是学生！");
                        while(true){
                            StudentFunction.StudentMenu(name);
                        }
                    }
                     else
                    {
                        System.out.println("登录成功，你的身份是管理员！");
                        while(true){
                            AdminFunction.AdminMenu(name);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }


    }
}
