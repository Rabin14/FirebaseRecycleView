package com.techpathak.firebaserecycleview;

public class User {
    private String tid;
    private String tdate;

    public User(String tid, String tdate, String ttime) {
        this.tid = tid;
        this.tdate = tdate;
        this.ttime = ttime;
    }

    public User() {
    }


    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    private String ttime;


}
