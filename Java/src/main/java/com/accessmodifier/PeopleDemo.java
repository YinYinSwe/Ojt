package com.accessmodifier;

public class PeopleDemo {

    public static void main(String[] args) {
       Student stu=new Student();
       stu.setName("Aye Aye");
       stu.setAge(17);
       System.out.println("Student Name:"+stu.getName()+'\n'+"Student age:"+stu.getAge());
       stu.display();
       stu.msg();
    }

}
