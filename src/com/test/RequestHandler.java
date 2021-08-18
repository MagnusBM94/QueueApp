package com.test;

import java.sql.*;
import java.util.ArrayList;

public class RequestHandler {

    //This method reads each row in the table "Requests" and creates Requests objects from the data. It then stores
    //the objects in an Arraylist.
    public static ArrayList<Requests> getAllRequests() throws SQLException {
        Connection conn = DBConnection.connect();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Requests";
        ResultSet rs;
        rs = stmt.executeQuery(sql);
        ArrayList<Requests> requestsList = new ArrayList<>();
        while (rs.next()){
            Requests requests = new Requests(
                    rs.getString("messageId"),
                    rs.getInt("employeeId"),
                    rs.getInt("requestedDays"),
                    rs.getInt("availableDays")
            );
            requestsList.add(requests);
        }
        return requestsList;
    }
    //Simple method for getting the first request in the queue as long as the queue is not empty
    public static String peekRequest(ArrayList<Requests> requests) {
        if (requests.size() > 0) {
            Requests vacReq = requests.get(0);
            return vacReq.toString();
        } else {
            return "The queue is empty!";
        }
    }
    //Deletes the request from the queue after it has been handled.
    public static void deleteRequest(ArrayList<Requests> requests) {
            if (requests.size() > 0){
                requests.remove(0);
            }
    }

    //Both of the methods below do the same more or less. When the requests get handled, the message ID is sent
    //to their respective list and also table in the database.
    public static void approvedLog(ArrayList<Requests> requests, ArrayList<String> approved){
        String messageId = requests.get(0).getMessageId();
        approved.add(messageId);
        Connection conn = DBConnection.connect();
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO vacation_approved (messageId)" + " values ('" + messageId + "')";
            stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void processedLog(ArrayList<Requests> requests, ArrayList<String> processed) {
        String messageId = requests.get(0).getMessageId();
        processed.add(messageId);
        Connection conn = DBConnection.connect();
        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO processed_log (messageId)" + " values ('" + messageId + "')";
            stmt.executeUpdate(sql);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
