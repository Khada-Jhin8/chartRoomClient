package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;
import vip.zhguo.chartRoom.common.MessageType;

import java.io.*;
import java.util.Date;

public class Function {


    //    1.显示在线用户列表
    public static void getOnlineFriends(String uId) {
        try {
            Message message = new Message();
            message.setMessagerType(MessageType.MESSAGE_GET_ONLINE_FRIENDS);
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientSocketServerThread.getCCST(uId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 群发消息
    public static void sendMessageToAll(String sender, String content) {
        Message message = new Message();
        message.setMessagerType(MessageType.MESSAGE_TO_ALL_MSG);
        message.setContent(content);
        message.setSender(sender);
        message.setSendTime(new Date().toString());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientSocketServerThread.getCCST(sender).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 发送普通消息
    public static void sendMessages(String sender, String getter, String content) {
        Message message = new Message();
        message.setMessagerType(MessageType.MESSAGE_COMMON_MSG);
        message.setContent(content);
        message.setSender(sender);
        message.setGetter(getter);
        message.setSendTime(new Date().toString());
        System.out.println(sender + " 对 " + getter + " 说 " + content);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientSocketServerThread.getCCST(sender).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 4.发送文件
    public static void sendFile(String uId, String getter, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在，发送中止！");
        }
        System.out.println(file.getName());
        Message message = new Message();
        message.setMessagerType(MessageType.MESSAGE_FILE_MSG);
        message.setSender(uId);
        message.setGetter(getter);
        message.setSendTime(new Date().toString());
        message.setFile(file);
        //设置文件大小
        byte[] fileSize = new byte[(int) file.length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileSize);
            if (fileInputStream != null)
                fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        message.setFiledata(fileSize);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientSocketServerThread.getCCST(uId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 获取离线消息
    public static void getOffLineMessages(String uId){
        Message message = new Message();
        message.setMessagerType(MessageType.MESSAGE_GET_OFFLINE_MSG);
        message.setSender(uId);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientSocketServerThread.getCCST(uId)
                    .getSocket()
                    .getOutputStream());
            oos.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 9.退出系统
    public static void exitSystem(String uId) {
        try {
            Message message = new Message();
            message.setMessagerType(MessageType.MESSAGE_CLIENT_EXIT);
            message.setSender(uId);
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientSocketServerThread.getCCST(uId).getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(uId + "退出了系统");
            //退出
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
