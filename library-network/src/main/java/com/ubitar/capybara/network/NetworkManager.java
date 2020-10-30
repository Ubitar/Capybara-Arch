package com.ubitar.capybara.network;

import java.util.HashMap;

public class NetworkManager {

    private HashMap<String, HostCreator> creators = new HashMap();
    private static NetworkManager mInstance;

    private NetworkManager() {

    }

    public NetworkManager addHostCreator(String tag, HostCreator creator) {
        creators.put(tag, creator);
        return this;
    }

    public <T> T createService(String tag, Class<T> c) {
        if (!creators.containsKey(tag)) throw new RuntimeException("please add host");
        HostCreator creator=creators.get(tag);
        return creator.createService(c);
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
