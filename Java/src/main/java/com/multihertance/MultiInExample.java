package com.multihertance;
interface Printable {
    void print();
}

interface Showable {
    void show();
}
public class MultiInExample implements Printable,Showable{
    @Override
    public void show() {
        System.out.println("Hello");
        
    }

    @Override
    public void print() {
        System.out.println("Everybody");
        
    }
    public static void main(String[] args) {
        MultiInExample obj = new MultiInExample();
        obj.show();
        obj.print();
        
    }
}
