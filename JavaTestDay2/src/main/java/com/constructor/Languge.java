package com.constructor;

public class Languge {
    String language;
    //default constructor
    Languge(){};
    
    //parameterized constructor
     private Languge(String languge) {
         language = languge;
         System.out.println(language + "  is Programming Language");
    }
    public static void main(String[] args) {
     // call constructor by passing a single value
        Languge obj1 = new Languge("Java");
        Languge obj2 = new Languge("Python");
        Languge obj3 = new Languge("C");
       
    }

}
