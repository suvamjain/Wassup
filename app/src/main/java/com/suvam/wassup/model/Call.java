package com.suvam.wassup.model;

public class Call {

    private String name;
    private String callTime;
    private int callCount;
    private CallType callType;

    public enum CallType {
        INCOMING, OUTGOING, MISSED
    }

    public Call() { }

    public Call(String name, String callTime, int callCount, CallType callType) {
        this.name = name;
        this.callTime = callTime;
        this.callCount = callCount;
        this.callType = callType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public int getCallCount() {
        return callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }

    public CallType getCallType() {
        return callType;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    @Override
    public String toString() {
        return "Call{" +
                "name='" + name + '\'' +
                ", callTime='" + callTime + '\'' +
                ", callCount=" + callCount +
                ", callType=" + callType +
                '}';
    }
}
