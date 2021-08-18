/*
Application made for Visma programming test.
The test initially asked for Docker implementation, but as I had never used the software before,
I eventually decided on a different approach to the task.
The application fetches a queue of vacation requests from a database, and stores the data in an Arraylist.
I chose to store the data in a list, as I figured if was easier to work with the data this way.
The application sends a request to the list to get the next request in the queue. From here you can either
approve or decline the request. The approved requests gets stored in an approved-list, while the declined requests
gets stored in a request log. I added a couple buttons so the user can view the logs in the "console" in the app.
*/
package com.test;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class App  {
    private JPanel panelMain;
    private JButton btnDecline;
    private JButton btnNext;
    private JButton btnApprove;
    private JTextArea txtData;
    private JTextArea txtConsole;
    private JButton btnProcessedLog;
    private JButton btnApprovedLog;
    ArrayList<Requests> requestsList;
    ArrayList<String> processed = new ArrayList<>();
    ArrayList<String> approved = new ArrayList<>();

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public App() throws SQLException{
        //Initializes the request-list and gets all the requests from the database
        requestsList = RequestHandler.getAllRequests();
        //Gets the request that's first in the queue
        btnNext.addActionListener(e -> txtData.setText(RequestHandler.peekRequest(requestsList)));
        //If the request is approved, the request gets deleted from the queue and the message ID is sent to the
        //approved log.
        btnApprove.addActionListener(e -> {
            //A simple check so that the user can't approve requests without viewing them first, and to avoid
            //null pointer exception.
            if (!txtData.getText().isEmpty() && !txtData.getText().equals("The queue is empty!")) {
                RequestHandler.approvedLog(requestsList, approved);
                txtConsole.setText("Approved");
                RequestHandler.deleteRequest(requestsList);
                txtData.setText("");
            }
        });
        btnDecline.addActionListener(e -> {
            //A check so that the user can't decline vacation requests that doesn't exceed the employees
            //available vacation days. However, I couldn't get it to work properly, and it caused an exception when
            //the queue was empty.
/*            int daysAvailable = requestsList.get(0).getAvailableDays();
            int daysRequested = requestsList.get(0).getRequestedDays();
            if ( daysAvailable >= daysRequested) {*/
                //Same check and behaviour as above. The only difference is that the message ID is sent
                //to a processed log instead.
                if (!txtData.getText().isEmpty() && !txtData.getText().equals("The queue is empty!")) {
                    RequestHandler.processedLog(requestsList, processed);
                    txtConsole.setText("Declined");
                    RequestHandler.deleteRequest(requestsList);
                    txtData.setText("");
                }
/*            } else {
                txtData.setText("The employee has enough days and should get approved");
            }*/
        });
        //Buttons to view the log in the app "console".
        btnProcessedLog.addActionListener(e -> {
            txtConsole.setText(processed.toString());
        });
        btnApprovedLog.addActionListener(e -> {
            txtConsole.setText(approved.toString());
        });
    }
}
