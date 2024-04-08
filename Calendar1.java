//Name: Evan Eggerud
//Class: CS 141
//Assignment Calendar 1
//Purpose: Print a calendar and print the current month and current day
import java.util.TimeZone;
import java.util.Calendar;
import java.util.Scanner; 
import java.text.SimpleDateFormat;
public class Calendar1{
public static void main(String[]args){
        Calendar localCalendar = Calendar.getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        String currentMonthString = monthFormat.format(localCalendar.getTime());
        int currentMonth = localCalendar.get(Calendar.MONTH) + 1; // Months are 0-based, so add 1
        int currentDay = localCalendar.get(Calendar.DAY_OF_MONTH);
        int begin = 1;
        int end = 7;
        System.out.println("\t\t\s\s\s" + currentMonthString); //print current month as a string (i.e. February)
    //this prints out the calendar weeks 1 through 5
    for (int i = 1; i<=5; i++){
        line();
        date(begin, end);
        System.out.println();
        spacing();
        begin += 7;
        end += 7;
    }   
    line(); //prints the final line under the calendar
    System.out.println("Month:" + currentMonth); //print the month and date as an integer
    System.out.println("Day:" + currentDay);
    }
    //this method prints the dividing rows using equal signs
    public static void line(){ 
        for (int i = 1; i <= 50; i++) {   
            System.out.print('=');}
            System.out.println();
    }
    //this method prints the dates and the columns on the date line.
    public static void date(int begin, int end){ 
        for (int i = begin; i <= end; i++){ 
            if (i >= 10){
            System.out.print("|" + i + "    ");
            } else {
                System.out.print("|" + i + "     ");
            }
            }
            System.out.print("|");
    }
    //this method fills out the rest of the calendar after the dates finish.
    public static void date2(int begin, int end){ 
        for (int i = begin; i <= end; i++){ 
            System.out.print("      |");
    }
}
    //this method prints out the the rest of the columns
    public static void spacing(){ 
        for (int i = 1; i<=8; i++){
            System.out.print("|      ");
            }
          System.out.println();
          for (int i = 1; i<=7; i++){
              System.out.print("|      ");
              }
        System.out.println("|");   
    }
    
}



