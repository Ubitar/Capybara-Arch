package com.ubitar.capybara.network;

import java.util.HashMap;

public class NetworkManager {

    private HashMap<String, Server> servers = new HashMap();
    private static NetworkManager mInstance;

    private NetworkManager() {

    }

    public NetworkManager addServer(String tag, Server creator) {
        servers.put(tag, creator);
        return this;
    }

    public <T> T createRepository(String tag, Class<T> service) {
        if (!servers.containsKey(tag)) throw new RuntimeException("please add server host");
        Server creator= servers.get(tag);
        return creator.createService(service);
    }

    public static NetworkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetworkManager.class) {
                if (mInstance == null) {
                    mInstance = new NetworkManager();
                }
            }
        }
        return mInstance;
    }


}
