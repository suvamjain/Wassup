package com.suvam.wassup;

import com.suvam.wassup.model.Call;
import com.suvam.wassup.model.Chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SampleData {

    private ArrayList<Object> allChats;
    private ArrayList<Object> allCalls;

    private static final int RECORDS_PER_PAGE = 15;

    private static final SampleData instance = new SampleData();

    public static SampleData getInstance() {
        return instance;
    }

    private SampleData() {
        allChats = new ArrayList<>();
        allCalls = new ArrayList<>();

        //-- Dummy Chats Data
        allChats.add(new Chat("Suvam Jain", "How are you?", "3:10 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Amit", "Are you coming to office?", "4:12 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Kushal", "Dummy Text 2", "4:18 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Kasid", "Dummy text 3", "4:32 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Sanjay", "Dummy text 4", "1:29 PM", 2, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Abhi", "Dummy text 5", "8:38 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Aman", "Dummy text 6", "2:41 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Khursid", "Dummy text 7", "8:01 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Rajeev", "Dummy text 8", "7:31 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Durgesh", "Dummy text 9", "3:08 PM", 3, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Ram kumar", "Dummy text 10", "8:29 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Rohit", "Dummy text 11", "1:29 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Sparsh", "Dummy text 12", "5:37 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Santosh", "Dummy text 13", "6:44 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Dinesh", "Dummy text 14", "2:12 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Gulshan", "Dummy text 15", "3:44 PM", 1, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Nausad", "Dummy text 16", "7:13 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Md. afsar", "Dummy text 17", "2:12 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Moti lal", "Dummy text 18", "4:02 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Raj kumar", "Dummy text 19", "6:43 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Chotelal", "Dummy text 20", "7:49 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Rupesh", "Dummy text 21", "4:57 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Midda", "Dummy text 22", "5:04 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Ram singh", "Dummy text 23", "1:31 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Sarain", "Dummy text 24", "1:17 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Sumit", "Dummy text 25", "4:47 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Arindra", "Dummy text 26", "9:10 PM", 2, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Vikash", "Dummy text 27", "3:14 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Hemant", "Dummy text 28", "2:59 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Shivam", "Dummy text 29", "4:53 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Aakash", "Dummy text 30", "5:11 PM", 8, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Chandesh", "Dummy text 31", "1:54 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Irfan", "Dummy text 32", "5:41 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Azaruddin", "Dummy text 33", "4:05 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Manoj", "Dummy text 34", "5:49 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Raja babu", "Dummy text 35", "3:47 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Pawan", "Dummy text 36", "6:38 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Sandeep", "Dummy text 37", "7:43 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Parvesh", "Dummy text 38", "4:33 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Yogita", "Goodnight 😴", "9:33 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Rajender", "Dummy text 40", "3:54 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Suraj", "Dummy text 41", "1:14 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Rizwan", "Dummy text 42", "8:06 PM", 3, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Deepak", "Dummy text 43", "6:35 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Rahul", "Dummy text 44", "9:04 PM", 3, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Abhishekh", "Dummy text 45", "7:40 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Ankit", "Hey Wassup?", "9:19 PM", 2, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Arjun", "Dummy text 47", "1:49 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Tammanne", "Dummy text 48", "5:19 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Ajay", "Dummy text 49", "6:14 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Silender", "Dummy text 50", "3:21 PM", 2, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Akhilesh", "Dummy text 51", "8:40 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Nitin", "Dummy text 52", "6:39 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Adnan", "Dummy text 53", "1:57 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Vijay", "You coming to office?", "9:18 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Yogesh", "Dummy text 55", "2:43 PM", 1, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Kabir", "Dummy text 56", "7:57 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Sarvesh", "Dummy text 57", "3:07 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Vivek", "Dummy text 58", "2:31 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Vansu dev", "Dummy text 59", "8:43 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Shafibul", "Dummy text 60", "6:56 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Som dutt", "Dummy text 61", "4:07 PM", 2, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Rajkumar", "Dummy text 62", "3:08 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Mubarik", "Dummy text 63", "9:06 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Niraj", "Dummy text 64", "8:19 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Axat", "Dummy text 65", "7:04 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Akkash", "Dummy text 66", "2:20 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Himanshu", "Dummy text 67", "1:24 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Sachin", "Dummy text 68", "2:30 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Bhupender", "Dummy text 69", "8:02 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Yognder", "Dummy text 70", "3:35 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Subhash", "Dummy text 71", "6:56 PM", 0, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Vikas", "Dummy text 72", "3:18 PM", 1, Chat.MsgType.RECIEVED));
        allChats.add(new Chat("Vinod", "Dummy text 73", "8:10 PM", 0, Chat.MsgType.SENT));
        allChats.add(new Chat("Salman", "Dummy text 74", "4:46 PM", 1, Chat.MsgType.RECIEVED));


        //-- Dummy Calls Data
        allCalls.add(new Call("Suvam Jain", "Septemeber 30, 1:15 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Amit", "Septemeber 30, 9:57 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Kushal", "Septemeber 27, 5:18 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Kasid", "Septemeber 23, 4:34 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Sanjay", "Septemeber 21, 4:38 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Abhi", "Septemeber 28, 3:48 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Aman", "Septemeber 29, 8:07 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Khursid", "Septemeber 27, 8:51 PM", 2, Call.CallType.MISSED));
        allCalls.add(new Call("Rajeev", "Septemeber 28, 3:28 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Durgesh", "Septemeber 30, 6:11 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Ram kumar", "Septemeber 30, 3:58 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Rohit", "Septemeber 30, 8:36 PM", 2, Call.CallType.MISSED));
        allCalls.add(new Call("Sparsh", "Septemeber 23, 3:45 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Santosh", "Septemeber 24, 4:48 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Dinesh", "Septemeber 26, 6:45 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Gulshan", "Septemeber 30, 2:30 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Nausad", "Septemeber 29, 7:18 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Md. afsar", "Septemeber 24, 8:33 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Moti lal", "Septemeber 29, 4:18 PM", 2, Call.CallType.MISSED));
        allCalls.add(new Call("Raj kumar", "Septemeber 21, 8:18 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Chotelal", "Septemeber 22, 7:55 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Rupesh", "Septemeber 23, 6:14 PM", 1, Call.CallType.OUTGOING));
        allCalls.add(new Call("Midda", "Septemeber 22, 6:52 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Ram singh", "Septemeber 27, 7:01 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Sarain", "Septemeber 23, 3:23 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Sumit", "Septemeber 30, 2:55 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Arindra", "Septemeber 21, 2:13 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Vikash", "Septemeber 25, 2:59 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Hemant", "Septemeber 25, 4:05 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Shivam", "Septemeber 27, 1:45 PM", 2, Call.CallType.MISSED));
        allCalls.add(new Call("Aakash", "Septemeber 22, 9:07 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Chandesh", "Septemeber 30, 1:46 PM", 2, Call.CallType.MISSED));
        allCalls.add(new Call("Irfan", "Septemeber 21, 2:19 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Azaruddin", "Septemeber 21, 9:48 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Manoj", "Septemeber 30, 2:18 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Raja babu", "Septemeber 30, 8:16 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Pawan", "Septemeber 30, 5:18 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Sandeep", "Septemeber 21, 8:43 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Parvesh", "Septemeber 27, 5:24 PM", 1, Call.CallType.OUTGOING));
        allCalls.add(new Call("Yogita", "Septemeber 24, 6:01 PM", 1, Call.CallType.OUTGOING));
        allCalls.add(new Call("Rajender", "Septemeber 26, 4:34 PM", 1, Call.CallType.OUTGOING));
        allCalls.add(new Call("Suraj", "Septemeber 25, 8:03 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Rizwan", "Septemeber 28, 5:18 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Deepak", "Septemeber 25, 2:12 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Rahul", "Septemeber 29, 1:55 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Abhishekh", "Septemeber 29, 2:32 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Ankit", "Septemeber 21, 1:04 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Arjun", "Septemeber 28, 5:33 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Tammanne", "Septemeber 26, 8:35 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Ajay", "Septemeber 22, 4:36 PM", 2, Call.CallType.MISSED));
        allCalls.add(new Call("Silender", "Septemeber 28, 6:41 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Akhilesh", "Septemeber 24, 1:44 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Nitin", "Septemeber 24, 9:52 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Adnan", "Septemeber 30, 5:10 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Vijay", "Septemeber 24, 6:35 PM", 1, Call.CallType.OUTGOING));
        allCalls.add(new Call("Yogesh", "Septemeber 29, 3:59 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Kabir", "Septemeber 24, 6:34 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Sarvesh", "Septemeber 25, 6:52 PM", 2, Call.CallType.MISSED));
        allCalls.add(new Call("Vivek", "Septemeber 28, 9:10 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Vansu dev", "Septemeber 23, 6:23 PM", 2, Call.CallType.MISSED));
        allCalls.add(new Call("Shafibul", "Septemeber 21, 1:18 PM", 2, Call.CallType.OUTGOING));
        allCalls.add(new Call("Som dutt", "Septemeber 27, 1:59 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Rajkumar", "Septemeber 23, 5:35 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Mubarik", "Septemeber 21, 7:21 PM", 1, Call.CallType.OUTGOING));
        allCalls.add(new Call("Niraj", "Septemeber 27, 3:08 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Axat", "Septemeber 22, 1:59 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Akkash", "Septemeber 23, 7:48 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Himanshu", "Septemeber 29, 5:17 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Sachin", "Septemeber 30, 9:29 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Bhupender", "Septemeber 26, 3:12 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Yognder", "Septemeber 23, 1:27 PM", 1, Call.CallType.MISSED));
        allCalls.add(new Call("Subhash", "Septemeber 28, 1:39 PM", 2, Call.CallType.INCOMING));
        allCalls.add(new Call("Vikas", "Septemeber 21, 9:15 PM", 1, Call.CallType.OUTGOING));
        allCalls.add(new Call("Vinod", "Septemeber 27, 1:43 PM", 1, Call.CallType.INCOMING));
        allCalls.add(new Call("Salman", "Septemeber 27, 6:43 PM", 2, Call.CallType.MISSED));

        Collections.sort(allChats, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return (((Chat)o1).getMsgTime().compareTo(((Chat)o2).getMsgTime()) * -1);
            }
        });


        Collections.sort(allCalls, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return (((Call)o1).getCallTime().compareTo(((Call)o2).getCallTime()) * -1);
            }
        });
    }

    public List<Object> getChatsData(int pageNumber) {
        int tillIndex = RECORDS_PER_PAGE * pageNumber;
        return allChats.subList(RECORDS_PER_PAGE * (pageNumber - 1) , tillIndex < allChats.size() ? tillIndex : allChats.size());
    }

    public List<Object> getCallsData(int pageNumber) {
        int tillIndex = RECORDS_PER_PAGE * pageNumber;
        return allCalls.subList(RECORDS_PER_PAGE * (pageNumber - 1) , tillIndex < allCalls.size() ? tillIndex : allCalls.size());
    }
}
