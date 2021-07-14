package com.example.ims_mobile_client.conversation;

import com.example.ims_mobile_client.common.MessageType;

public class Message {
    public String text;
    public User sender;
    public long createdAt;
    public MessageType type;


    public String getTimeStamp() {
        // TODO: implement properly
        // return createdAt.toStr();
        return "12:00";
    }
}
