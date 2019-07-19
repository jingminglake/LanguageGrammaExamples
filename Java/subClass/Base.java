//package com.java.oop.test1;

public class Base {

    private String baseName = "base";
    private static String testName = "TestBase";

    public Base() {
        callName();
    }

    public void callName() {
        System.out.println(baseName);
    }
}
