package com.student_management;

import com.student_management.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AdminFunction {
    public static void AdminMenu(String name){
        Scanner sc = new Scanner(System.in);
        System.out.println("===== 管理员菜单 =====");
        System.out.println("  1. 查询所有学生");
        System.out.println("  2. 修改学生手机号");
        System.out.println("  3. 查询所有课程");
        System.out.println("  4. 修改课程学分");
        System.out.println("  5. 查询某课程的学生名单 ");
        System.out.println("  6. 查询某学生的选课情况");
        System.out.println("  7. 添加课程信息");
        System.out.println("  8. 退出");
        System.out.println("请选择操作（输入 1-8）：");
        int command = sc.nextInt();
        switch(command){
            case 1:
                SearchStudent();
                break;
            case 2:
                ChangeStuPhonenumber();
                break;
            case 3:
                SearchAllLesson();
                break;
            case 4:
                ChangeLessonGrade();
                break;
            case 5:
                SearchLessonStu();
                break;
            case 6:
                SearchSelect();
                break;
            case 7:
                AddLesson();
                break;
            case 8:
                System.out.println("已退出，谢谢使用");
                System.exit(0); //退出虚拟机
                break;
            default:
                System.out.println("没有该操作，请重新选择");
        }
    }

    public static void SearchStudent(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql = "SELECT * FROM student";

            rs = st.executeQuery(sql);
            while (rs.next()){
                System.out.println("名字：" + rs.getObject("usersname"));
                System.out.println("手机号：" + rs.getObject("phonenumber"));
                String i=rs.getString("lesson1");
                if(i !=null)
                {
                    System.out.println("lesson1=" + rs.getObject("lesson1"));
                }
                i=rs.getString("lesson2");
                if(i !=null)
                {
                    System.out.println("lesson2=" + rs.getObject("lesson2"));
                }
                i=rs.getString("lesson3");
                if(i !=null)
                {
                    System.out.println("lesson3=" + rs.getObject("lesson3"));
                }
                i=rs.getString("lesson4");
                if(i !=null)
                {
                    System.out.println("lesson4=" + rs.getObject("lesson4"));
                }
                i=rs.getString("lesson5");
                if(i !=null)
                {
                    System.out.println("lesson5=" + rs.getObject("lesson5"));
                }

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }

    public static void ChangeStuPhonenumber(){
        System.out.println("请输入想要修改的学生姓名：");
        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();
        System.out.println("请输入修改后的手机号码：");
        String num;
        Scanner sc = new Scanner(System.in);
        num=sc.nextLine();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql = "UPDATE `student` SET `phonenumber` = '"+num+"' WHERE usersname like '"+name+"'";

            int i = st.executeUpdate(sql);
            if(i>0)
            {
                System.out.println("修改成功！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }

    public static void SearchAllLesson(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql = "SELECT * FROM courses";

            rs = st.executeQuery(sql);
            int i=1;
            while (rs.next()){

                System.out.println("课程" +i+": "+rs.getObject("coursesname"));
                i++;

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }

    public static void ChangeLessonGrade(){
        Scanner sc0 = new Scanner(System.in);
        System.out.println("请输入想要修改的学生姓名：");
        String name = sc0.nextLine();
        System.out.println("该学生的选课情况为：");
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql = "SELECT `usersname`,`lesson1`,`lesson2`,`lesson3`,`lesson4`,`lesson5` FROM  student WHERE usersname LIKE '"+name+"'";
            rs = st.executeQuery(sql);
            if(rs.next())
            {
                String i=rs.getString("lesson1");
                if(i !=null)
                {
                    System.out.println("lesson1=" + rs.getObject("lesson1"));
                }
                i=rs.getString("lesson2");
                if(i !=null)
                {
                    System.out.println("lesson2=" + rs.getObject("lesson2"));
                }
                i=rs.getString("lesson3");
                if(i !=null)
                {
                    System.out.println("lesson3=" + rs.getObject("lesson3"));
                }
                i=rs.getString("lesson4");
                if(i !=null)
                {
                    System.out.println("lesson4=" + rs.getObject("lesson4"));
                }
                i=rs.getString("lesson5");
                if(i !=null)
                {
                    System.out.println("lesson5=" + rs.getObject("lesson5"));
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
        //展示该学生的选课情况

        Scanner sc1 = new Scanner(System.in);
        System.out.println("请输入你想要修改的课程编号（如：lesson1）：");
        String lesson = sc1.nextLine();
        Scanner sc2 = new Scanner(System.in);
        System.out.println("请输入修改后的成绩：");
        String grade = sc2.nextLine();
        //UPDATE `result` SET lesson1 = '40' WHERE studentname LIKE 'dyy';

        conn = null;
        st = null;
        rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql = "UPDATE `result` SET "+lesson+ "="+grade+" WHERE studentname LIKE '"+name+"';";
            int i= st.executeUpdate(sql);
            if(i>0)
            {
                System.out.println("修改成功！");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }

    public static void SearchLessonStu(){
        System.out.println("请输入想要查询的课程姓名：");
        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql = "SELECT * FROM student";

            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String lesson1=rs.getString("lesson1");
                String lesson2=rs.getString("lesson2");
                String lesson3=rs.getString("lesson3");
                String lesson4=rs.getString("lesson4");
                String lesson5=rs.getString("lesson5");
                String stuname=rs.getString("usersname");

                if(name.equals(lesson1) ||  name.equals(lesson2) || name.equals(lesson3) || name.equals(lesson4) || name.equals(lesson5))
                {
                    System.out.println(stuname);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }


    public static void SearchSelect(){
        Scanner sc0 = new Scanner(System.in);
        System.out.println("请输入想要查询的学生姓名：");
        String name = sc0.nextLine();
        System.out.println("该学生的选课情况为：");
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql = "SELECT `usersname`,`lesson1`,`lesson2`,`lesson3`,`lesson4`,`lesson5` FROM  student WHERE usersname LIKE '"+name+"'";
            rs = st.executeQuery(sql);
            if(rs.next())
            {
                String i=rs.getString("lesson1");
                if(i !=null)
                {
                    System.out.println("lesson1=" + rs.getObject("lesson1"));
                }
                i=rs.getString("lesson2");
                if(i !=null)
                {
                    System.out.println("lesson2=" + rs.getObject("lesson2"));
                }
                i=rs.getString("lesson3");
                if(i !=null)
                {
                    System.out.println("lesson3=" + rs.getObject("lesson3"));
                }
                i=rs.getString("lesson4");
                if(i !=null)
                {
                    System.out.println("lesson4=" + rs.getObject("lesson4"));
                }
                i=rs.getString("lesson5");
                if(i !=null)
                {
                    System.out.println("lesson5=" + rs.getObject("lesson5"));
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }

    public static void AddLesson(){
        System.out.println("请输入想要插入的课程姓名：");
        Scanner scn = new Scanner(System.in);
        String name = scn.nextLine();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();
            //INSERT INTO `courses` (`coursesname`) VALUES ('usuful')
            String sql = "INSERT INTO `courses` (`coursesname`) VALUES ('"+name+"')";

            int i = st.executeUpdate(sql);
            if(i>0)
            {
                System.out.println("添加成功！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }
}

