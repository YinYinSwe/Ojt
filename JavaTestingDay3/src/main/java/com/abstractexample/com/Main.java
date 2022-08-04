package com.abstractexample.com;

class Cat extends Animal{

    @Override
    public void sound() {
        System.out.println("meow meow");
    }

}

class Dog extends Animal{

    @Override
    public void sound() {
        System.out.println("woof woof");
    }

}
public class Main {

    public static void main(String[] args) {
        Cat arKyel = new Cat();
        arKyel.sound();     //call makeSound function through a object

        Dog waTote = new Dog();
        waTote.sound();  

    }

}
