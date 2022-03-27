package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;
import vip.zhguo.chartRoom.common.MessageType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
                } else if (message.getMessagerType().equals(MessageType.MESSAGE_TO_ALL_MSG)) {
                    System.out.println();
                    System.out.println(message.getSendTime() + ":<" + message.getSender() + "> 对 < 大家 > 说：\"" + message.getContent() + "\"");
                } else if (message.getMessagerType().equals(MessageType.MESSAGE_COMMON_MSG)) {
                    System.out.println();
                    System.out.println(message.getSendTime() + ":<" + message.getSender() + "> 对 <" + message.getGetter() + "> 说：\"" + message.getContent() + "\"");
                } else if (message.getMessagerType().equals(MessageType.MESSAGE_FILE_MSG)) {
                    //注释因为与前面控制台输入重合，导致两边出入出错。
//                    System.out.println(message.getSendTime() + message.getSender() + "给你发他一个文件，你是否要接收，请选择：\n\t1.接收\n\t2.拒绝");
//                    Scanner in = new Scanner(System.in);
//                    String choose = in.next();
//                    if (choose.equals("1")) {
//                        System.out.println("请输入要保存的路径（D\\）");
//                        String savePath = in.next();
//                        System.out.println("请输入保存的文件名（name）");
//                        String saveFileName = in.next();
                    File file = message.getFile();
                    String name = file.getName();
//                    取文件后缀，判断文件格式
                    String[] split = name.split("\\."); //必须转义，不然为空
//                        String saveFilePath = savePath + "\\" + saveFileName + "." + split;
                    String saveFilePath = "d:\\getFile" + "." + split[split.length-1];
                    File file1 = new File(saveFilePath);
                    FileOutputStream fos = new FileOutputStream(file1);
                    fos.write(message.getFiledata());
                    fos.close();
//                } else {
//                    System.out.println("文件被你拒收");
//                }
                } else if (message.getMessagerType().equals(MessageType.MESSAGE_GET_OFFLINE_MSG)){
                    System.out.println();
                    List<Message> offLineMsgs = message.getOffLineMsgs();
                    for (Message msg: offLineMsgs
                         ) {
                        System.out.println(msg.getSendTime() + ":<" + msg.getSender() + "> 对 <" + msg.getGetter() + "> 说：\"" + msg.getContent() + "\"");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
