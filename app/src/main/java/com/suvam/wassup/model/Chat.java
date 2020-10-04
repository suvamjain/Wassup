package com.suvam.wassup.model;

import java.io.Serializable;

public class Chat implements Serializable {

    private String name;
    private String msg;
    private String msgTime;
    private int newMsgCount;
    private MsgType lastMsgType;

    public enum MsgType {
        SENT, RECIEVED
    }

    public Chat() {}

    public Chat(String name, String msg, String msgTime, int newMsgCount, MsgType lastMsgType) {
        this.name = name;
        this.msg = msg;
        this.msgTime = msgTime;
        this.newMsgCount = newMsgCount;
        this.lastMsgType = lastMsgType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public int getNewMsgCount() {
        return newMsgCount;
    }

    public void setNewMsgCount(int newMsgCount) {
        this.newMsgCount = newMsgCount;
    }

    public MsgType getLastMsgType() {
        return lastMsgType;
    }

    public void setLastMsgType(MsgType lastMsgType) {
        this.lastMsgType = lastMsgType;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                ", msgTime='" + msgTime + '\'' +
                ", newMsgCount=" + newMsgCount +
                ", lastMsgType='" + lastMsgType + '\'' +
                '}';
    }
}
