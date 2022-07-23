package com.accessmodifier;

public class Student {
 private String name;
 private int age;

public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public int getAge() {
    return age;
}
public void setAge(int age) {
    this.age = age;
}
public void display() {
    System.out.println("Student Information");
}
void msg()
{
    System.out.println("Hello");
   } 
}
