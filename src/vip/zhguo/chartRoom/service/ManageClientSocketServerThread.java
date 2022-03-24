package vip.zhguo.chartRoom.service;

import java.util.HashMap;

public class ManageClientSocketServerThread {
    private static HashMap<String, ClientSocketServerThread> map = new HashMap();

    public static void addCSST(String key, ClientSocketServerThread ccst) {
        map.put(key, ccst);
    }

    public static ClientSocketServerThread getCCST(String key) {
        return map.get(key);
    }
}
