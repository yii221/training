package com.student_management;

import java.sql.SQLException;
import java.util.Scanner;

public class basicfunction {

    Scanner sc = new Scanner(System.in);
    public void start(){
        System.out.println("===========================");
        System.out.println("       学生选课管理系统");
        System.out.println("===========================");
        System.out.println("          1. 登录");
        System.out.println("          2. 注册");
        System.out.println("          3. 退出");
        System.out.println("    请选择操作（输入 1-3）：");
        int command = sc.nextInt();
        switch(command){
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("已退出，谢谢使用");
                System.exit(0); //退出虚拟机
                break;
            default:
                System.out.println("没有该操作，请重新选择");
        }
    }
    private void login(){
        Scanner sc1 = new Scanner(System.in);
        System.out.println("========== 用户登录 ==========");
        System.out.println("        请输入用户名：");
        String name = sc1.nextLine();
        System.out.println("        请输入密码：");
        String psw = sc1.next();
        try {
            MenuOperateManager.ulogin(name,psw);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private void register(){
        Scanner sc1 = new Scanner(System.in);
        System.out.println("========== 用户注册 ==========");
        System.out.println("        请输入用户名：");
        String name = sc1.nextLine();
        System.out.println("        请输入密码：");
        String psw = sc1.nextLine();
        System.out.println("请输入身份选择（1是学生 2是管理员）：");
        int judge = sc1.nextInt();
        System.out.println("        请输入手机号码：");
        String phonenumber = sc1.next();
        MenuOperateManager.uregister(name,psw,judge,phonenumber);




    }
    }





