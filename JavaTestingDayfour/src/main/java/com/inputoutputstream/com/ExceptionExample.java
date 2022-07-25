package com.inputoutputstream.com;

public class ExceptionExample {

    public static void main(String[] args) {
        int a=5;
        int b=0;
          try{
            System.out.println(a/b);
          }
        catch(ArithmeticException e){
          System.out.println(e.toString());
        }
          int[] arr = new int[4];
          
          // Now this statement will cause an exception
          int i = arr[4];
   
          // This statement will never execute
          // as above we caught with an exception
          System.out.println("Hi, I want to execute");
          try
          {
              // Creating an exception
              NumberFormatException ex =
                         new NumberFormatException("Exception");
    
              // Setting a cause of the exception
              ex.initCause(new NullPointerException(
                        "This is actual cause of the exception"));
    
              // Throwing an exception with cause.
              throw ex;
          }
    
          catch(NumberFormatException ex)
          {
              // displaying the exception
              System.out.println(ex);
    
              // Getting the actual cause of the exception
              System.out.println(ex.getCause());
          }
      }

    }


