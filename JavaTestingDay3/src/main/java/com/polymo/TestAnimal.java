package com.polymo;
 
class  Animals {
   public void move() {
       System.out.println("Animal can move");
   }
}
class Dog extends Animals{
    public void move() {
        System.out.println("Dog can run and walk"); 
    }
}
public class TestAnimal {

    public static void main(String[] args) {
       Animals a=new Animals();
       Animals d=new Dog();
       a.move();
       d.move();

    }

}
