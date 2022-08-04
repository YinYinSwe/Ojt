package com.polymo;

public class Addition {
    public void add(int num1,int num2) {
        int result=num1+num2;
        System.out.println(result);
    }
    public void add(double num1,double num2) {
        double result=num1+num2;
        System.out.println(result);
    }
    public void add(int num1,int num2,int num3) {
        int result=num1+num2+num3;
        System.out.println(result);
    }
    public static void main(String[] args) {
       Addition a=new Addition();
       a.add(10, 20);
       a.add(20.2,10.0);
       a.add(3,45,56);

    }

}
