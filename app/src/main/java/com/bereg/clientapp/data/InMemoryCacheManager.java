package com.bereg.clientapp.data;

import android.support.v4.util.LruCache;
import android.util.Log;

import com.bereg.clientapp.models.MessageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 19.05.2018.
 */

public class InMemoryCacheManager {

    private static final String TAG = InMemoryCacheManager.class.getSimpleName();
    public LruCache<Integer, MessageModel> lruCache;
    private Integer key;

    public InMemoryCacheManager() {

        key = 0;
        int cacheSize = 100;
        lruCache = new LruCache<>(cacheSize);
    }

    public void addMessageToMemoryCache(MessageModel message) {
        Log.e(TAG, "addMessageToMemoryCache" + message);
        if (getMessageFromMemoryCache(++key) == null) {    //TODO: is it necessary to check for null?
            lruCache.put(key, message);
        }
    }

    public MessageModel getMessageFromMemoryCache(Integer key) {
        Log.e(TAG, "getMessageFromMemoryCache" + String.valueOf(key));
        return lruCache.get(key);
    }

    public List<MessageModel> getAllMessagesFromMemoryCache() {

        List<MessageModel> messages = new ArrayList<>();
        for (int i=1; i<=key; i++) {
            messages.add(getMessageFromMemoryCache(i));
        }
        return messages;
    }
}
