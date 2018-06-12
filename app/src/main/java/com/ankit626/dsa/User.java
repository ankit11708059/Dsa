package com.ankit626.dsa;

import java.lang.ref.SoftReference;

/**
 * Created by Ankit Sharma on 11-06-2018.
 */

public class User {



    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public User(String t1, String t2, Integer t3) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    private String t1;
    private String t2;

    public Integer getT3() {
        return t3;
    }

    public void setT3(Integer t3) {
        this.t3 = t3;
    }

    private Integer t3;


}
