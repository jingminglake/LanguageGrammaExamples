//package com.java.oop.test1;

public class Sub extends Base {

    private String baseName = "sub";
    private static String testName = "TestSub";

    public void callName() {
        System.out.println(baseName);
    }

    public static void main(String[] args) {
        Base base = new Sub();
    }
}
