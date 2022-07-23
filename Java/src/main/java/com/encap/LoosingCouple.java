package com.encap;
interface Animal {
    void child();
}

class Cat implements Animal {
    public void child() {
        System.out.println("kitten");
    }
}

class Dog implements Animal {
    public void child() {
        System.out.println("puppy");
    }
}
public class LoosingCouple {
    public static void main(String args[]) {
        Animal obj = new Dog();
        obj.child();
        
        obj = new Cat();
        obj.child();
    }
}
