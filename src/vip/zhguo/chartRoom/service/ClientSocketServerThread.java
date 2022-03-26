package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;
import vip.zhguo.chartRoom.common.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;

public class ClientSocketServerThread extends Thread {
    private Socket socket;

    public ClientSocketServerThread(Socket socket) {
        this.socket = socket;
    }
    //对外提供方法，拿到该Socket

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        //与服务端一直保持链接
        while (true) {
            try {
                System.out.println("与服务端保持连接");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
//                System.out.println(message.getContent() + "========" + message.getMessagerType());
                if (message.getMessagerType().equals(MessageType.MESSAGE_RETURN_ONLINE_FRIENDS)) {
                    String content = message.getContent();
                    String[] split = content.split(",");
                    System.out.println("==========当前用户在线列表==========");
                    for (String a : split) {
                        System.out.println("用户:" + a);
                    }
                }else if (message.getMessagerType().equals(MessageType.MESSAGE_COMMON_MSG)){
                    System.out.println();
                    System.out.println(message.getSendTime() + ":<" + message.getSender() + "> 对 <" + message.getGetter() + "> 说：\"" + message.getContent() + "\"");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
