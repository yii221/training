package com.student_management;

import com.student_management.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public  class StudentFunction {
    public static void StudentMenu(String name)  {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== 学生菜单 =====");
        System.out.println("  1. 查看可选课程");
        System.out.println("   2. 选择课程");
        System.out.println("   3. 退选课程");
        System.out.println("  4. 查看已选课程");
        System.out.println("   5. 修改手机号");
        System.out.println("    6. 退出");
        System.out.println("请选择操作（输入 1-6）：");
        int command = sc.nextInt();
        switch(command){
            case 1:
                SearchOptional(name);
                break;
            case 2:
                ChooseSourse(name);
                break;
            case 3:
                DeleteLesson(name);
                break;
            case 4:
                SearchLesson(name);
                break;
            case 5:
                ChangePhonenumber(name);
                break;
            case 6:
                System.out.println("已退出，谢谢使用");
                System.exit(0); //退出虚拟机
                break;
            default:
                System.out.println("没有该操作，请重新选择");
        }
    }

    public static void SearchOptional (String name)  {
        Connection conn = null;
        Statement st = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql1 = "SELECT `usersname`,`lesson1`,`lesson2`,`lesson3`,`lesson4`,`lesson5` FROM student WHERE usersname LIKE '"+name+"'";
            rs1 = st.executeQuery(sql1);
            if(rs1.next())
            {
                String lesson1=rs1.getString("lesson1");
                String lesson2=rs1.getString("lesson2");
                String lesson3=rs1.getString("lesson3");
                String lesson4=rs1.getString("lesson4");
                String lesson5=rs1.getString("lesson5");

                String sql2 = "SELECT * FROM courses";
                rs2 = st.executeQuery(sql2);

                while(rs2.next())
                {
                    String course = rs2.getString("coursesname");
                    if((!course.equals(lesson1)) && (!course.equals(lesson2)) && (!course.equals(lesson3)) && (!course.equals(lesson4)) && (!course.equals(lesson5)))
                    {
                        System.out.println(course);
                    }
                }
            }




        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs1);
            JdbcUtils.release(conn,st,rs2);
        }
    }

    public static void ChooseSourse(String name) {
        //UPDATE `student` SET `lesson3` = '狂神' WHERE usersname LIKE 'dyy';
        System.out.println("请输入你要选择的课程：");
        String lesson;
        Scanner sc = new Scanner(System.in);
        lesson=sc.nextLine();
        Connection conn = null;
        Statement st = null;
        ResultSet rs1 = null;
        int rs2;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql1 = "SELECT `usersname`,`lesson1`,`lesson2`,`lesson3`,`lesson4`,`lesson5` FROM student WHERE usersname LIKE '"+name+"'";
            rs1 = st.executeQuery(sql1);
            if(rs1.next())
            {
                String lesson1=rs1.getString("lesson1");
                if(lesson1 == null)
                {
                    String sql2="UPDATE `student` SET `lesson1` = '"+lesson+"' WHERE usersname LIKE '"+name+"';";
                    rs2 = st.executeUpdate(sql2);
                    if(rs2>0) System.out.println("选课成功！");
                }
                else{
                    String lesson2=rs1.getString("lesson2");
                    if(lesson2 == null)
                    {
                        String sql2="UPDATE `student` SET `lesson2` = '"+lesson+"' WHERE usersname LIKE '"+name+"';";
                        rs2 = st.executeUpdate(sql2);
                        if(rs2>0) System.out.println("选课成功！");
                    }
                    else {
                        String lesson3=rs1.getString("lesson3");
                        if(lesson3 == null)
                        {
                            String sql2="UPDATE `student` SET `lesson3` = '"+lesson+"' WHERE usersname LIKE '"+name+"';";
                            rs2 = st.executeUpdate(sql2);
                            if(rs2>0) System.out.println("选课成功！");
                        }
                        else{
                            String lesson4=rs1.getString("lesson4");
                            if(lesson4 == null)
                            {
                                String sql2="UPDATE `student` SET `lesson4` = '"+lesson+"' WHERE usersname LIKE '"+name+"';";
                                rs2 = st.executeUpdate(sql2);
                                if(rs2>0) System.out.println("选课成功！");
                            }
                            else{
                                String lesson5=rs1.getString("lesson5");
                                if(lesson5 == null)
                                {
                                    String sql2="UPDATE `student` SET `lesson5` = '"+lesson+"' WHERE usersname LIKE '"+name+"';";
                                    rs2 = st.executeUpdate(sql2);
                                    if(rs2>0) System.out.println("选课成功！");
                                }
                                else System.out.println("每人最多选五门课程，超出限额，选课失败！");
                            }
                        }
                    }
                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs1);

        }
    }
    public static void DeleteLesson(String name){
        //UPDATE student SET lesson1 = NULL WHERE lesson1 like 'def';
        System.out.println("请输入你想要删除的课程序号：");
        String lessonnum,lesson;
        Scanner sc = new Scanner(System.in);
        lessonnum=sc.nextLine();
        System.out.println("请输入你想要删除的课程名称：");
        Scanner sci = new Scanner(System.in);
        lesson=sci.nextLine();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            String sql = "UPDATE student SET `"+lessonnum+"`"+"= NULL WHERE "+ lessonnum+" LIKE '"+lesson+"' AND usersname LIKE '"+name+"'";
            int i = st.executeUpdate(sql);
            if(i>0)
            {
                System.out.println("删除成功！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }

    public static void SearchLesson(String name){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            //select usersname from student where usersname LIKE 'abc'
            String sql ="SELECT `usersname`,`lesson1`,`lesson2`,`lesson3`,`lesson4`,`lesson5` FROM student WHERE usersname LIKE '"+name+"'";

            rs = st.executeQuery(sql);
            if(rs.next())
            {
                String lesson1=rs.getString("lesson1");
                if(!(lesson1 == null))
                {
                    System.out.println("lesson1:"+lesson1);
                }
                String lesson2=rs.getString("lesson2");
                if(lesson2!=null)
                {
                    System.out.println("lesson2:"+lesson2);
                }
                String lesson3=rs.getString("lesson3");
                if(lesson3!=null)
                {
                    System.out.println("lesson3:"+lesson3);
                }
                String lesson4=rs.getString("lesson4");
                if(lesson4!=null )
                {
                    System.out.println("lesson4:"+lesson4);
                }
                String lesson5=rs.getString("lesson5");
                if(lesson5!=null)
                {
                    System.out.println("lesson5:"+lesson5);
                }
            }

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }
    public static void ChangePhonenumber(String name){
        //update `student` set `phonenumber` = '12345' where usersname like 'abc'
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
    }





