package com.test;
//Straight forward class for creation of requests. Quite self-explanatory.

public class Requests {
    private String messageId;
    private int employeeId;
    private int requestedDays;
    private int availableDays;

    public Requests(String messageId, int employeeId, int requestedDays, int availableDays) {
        this.messageId = messageId;
        this.employeeId = employeeId;
        this.requestedDays = requestedDays;
        this.availableDays = availableDays;
    }

    public String getMessageId() {
        return messageId;
    }

    public int getRequestedDays() { return requestedDays; }

    public int getAvailableDays() { return availableDays; }

    //Have to override the toString method because the Arraylist with requests are objects, and therefore
    //the builtin toString method from the object class won't print them properly.
    @Override
    public String toString() {
        return "Vacation request: " +
                "messageId= '" + messageId + '\'' +
                ", employeeId= " + employeeId +
                ", requestedDays= " + requestedDays +
                ", availableDays= " + availableDays +
                '.';
    }
}
