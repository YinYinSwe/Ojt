package com.method;

public class StudentGrade {
    public static String getGrade(int percentage ) {       
        if(percentage>=60){  
            System.out.println("A grade"); 
            return "A grade"; //Return statement
        }else if(percentage>=40){  
            System.out.println("B grade"); 
            return "B grade"; //Return statement
        }else {  
            System.out.println("Not Eligible");  
            return "Not Eligible"; //Return statement
        }
   }
    
    public void studentInfo(String name,String phno,String email) {
       System.out.println("Student Info:"+name+"   "+ phno+"  "+email);
        
    }
    public int studentMark(int mark1,int mark2,int mark3) {
        int total=mark1+mark2+mark3;
        return total;
    }
    public static void main(String[] args) {
        String grade = StudentGrade.getGrade(70);
        System.out.println("Grade:"+grade);

        StudentGrade stu=new StudentGrade();
        stu.studentInfo("Aye Aye", "099876666", "ayeaye345@gmail.com");
         int totalmark=stu.studentMark(50,60,70);
         System.out.println("Student total Mark:"+totalmark);

    }

}
