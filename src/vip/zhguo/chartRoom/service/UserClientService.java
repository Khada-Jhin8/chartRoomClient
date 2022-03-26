package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;
import vip.zhguo.chartRoom.common.MessageType;
import vip.zhguo.chartRoom.common.User;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserClientService {
    private User user = new User();

    /**
     * @param uID
     * @param pwd
     * @return
     */
    public boolean cheackUser(String uID, String pwd) {
        //封装对象
        user.setuID(uID);
        user.setPassword(pwd);
        boolean flag = false;
        try {
            //建立链接，发送
            Socket socket = new Socket("192.168.3.155", 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            //判断服务器验证数据是否成功，成功则开启一个线程与服务器保持链接。
            if (message.getMessagerType().equals(MessageType.MESSAGE_LOGIN_SUCCESS) ) {
                //创建与服务器保持连接的线程。
                ClientSocketServerThread clientSocketServerThread = new ClientSocketServerThread(socket);
                clientSocketServerThread.start();
                //将与服务器保持连接的线程保存到集合中
                ManageClientSocketServerThread.addCSST(user.getuID(), clientSocketServerThread);
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

}
