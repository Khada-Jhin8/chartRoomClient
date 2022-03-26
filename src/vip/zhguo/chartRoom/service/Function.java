package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;
import vip.zhguo.chartRoom.common.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
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
