package vip.zhguo.chartRoom.view;

import java.util.Scanner;

public class ChartRoomView {
    private boolean loop = true;
    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        new ChartRoomView().loginView();

    }

    public void loginView() {
        while (loop) {
            System.out.println("==========您好，先输入您的选项==========");
            System.out.println("\t 1.登陆系统");
            System.out.println("\t 9.退出系统");
            System.out.print("请输入您的选择：");
            String getKey = in.next();
            switch (getKey) {
                case "1":
                    System.out.print("\t 请输入您的账号：");
                    String userId = in.next();
                    System.out.print("\t 请输入您的密码：");
                    String pwd = in.next();
                    login(userId, pwd);
                    break;
                case "9":
                    System.out.println("\t 退出成功");
                    this.loop = false;
                    break;
            }
        }

    }

    public void login(String userId, String pwd) {
        if (true) { // true变量需要根据服务器校验。
            System.out.println("登陆成功");
            System.out.println("==========欢迎" + userId + "登陆到聊天室==========");
            while (loop) {
                System.out.println("==========聊天室二级菜单(" + userId + ")==========");
                System.out.print("\t 1.显示在线用户列表");
                System.out.print("\t 2.群发消息");
                System.out.print("\t 3.私发消息");
                System.out.print("\t 4.发送文件");
                System.out.println("\t 9.退出系统");
                System.out.print("请输入您的选择:");
                String getKey = in.next();
                switch (getKey) {
                    case "1":
                        System.out.println("显示用户在线列表");
                        break;
                    case "2":
                        System.out.println("群发消息");
                        break;
                    case "3":
                        System.out.println("私发消息");
                        break;
                    case "4":
                        System.out.println("发送文件");
                        break;
                    case "9":
                        System.out.println("退出系统");
                        this.loop = false;
                        break;
                }
            }
        } else {
            System.out.println("登陆失败");
        }

    }
}
