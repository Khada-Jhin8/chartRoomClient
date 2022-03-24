package vip.zhguo.chartRoom.service;

import vip.zhguo.chartRoom.common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

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
        while (true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                System.out.println(message.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
