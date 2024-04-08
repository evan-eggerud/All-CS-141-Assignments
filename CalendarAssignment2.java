//Name: Evan Eggerud
//Class: CS 141
//Assignment Calendar 2
//Purpose: Print a calendar based on entered date or today's date and print next and previous months. 
import java.util.TimeZone;
import java.util.Calendar;
import java.util.Scanner; 
import java.text.SimpleDateFormat;
public class CalendarAssignment2{
public static void main(String[]args){
        Scanner input = new Scanner(System.in);
        Calendar localCalendar = Calendar.getInstance();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        String currentMonthString = monthFormat.format(localCalendar.getTime());
        int currentMonth = 0;
        int currentDay = 0;
        int begin = 1;
        int end = 7;
        char command;
        boolean rerun = true;
        while (rerun){
        instructions();
        command = input.next().charAt(0);
        System.out.println("You selected " + command);
        //switch for user input to enter day, use current day, and if a calendar has been selected, print next or previous month. 
        switch (command){
            case 'e':
            System.out.println("Enter the month: ");
            currentMonth = input.nextInt();
            System.out.println("Enter the date: ");
            currentDay = input.nextInt();
            begin = 1;
            System.out.println("\t\t\t" + currentMonth);
            int[] updatedValues = monthAdd(currentMonth, currentDay, begin);
            begin = updatedValues[1];
            end = updatedValues[2];
            calendar(begin, end, currentMonth, currentDay);
            System.out.println("Month:" + currentMonth); //print the month and date as an integer
            System.out.println("Day:" + currentDay);
            break;
            case 't':
            currentMonth = localCalendar.get(Calendar.MONTH) + 1; // Months are 0-based, so add 1
            currentDay = localCalendar.get(Calendar.DAY_OF_MONTH);
            System.out.println("\t\t\s\s\s" + currentMonthString); //print current month as a string (i.e. February)
            begin = 1;
            updatedValues = monthAdd(currentMonth, currentDay, begin);
            begin = updatedValues[1];
            end = updatedValues[2];
            calendar(begin, end, currentMonth, currentDay);
            System.out.println("Month:" + currentMonth); //print the month and date as an integer
            System.out.println("Day:" + currentDay);
            break;
            case 'n':
            if (currentMonth==0){
                System.out.println("Please enter \"e\" or \"t\" first");
            } else{
            currentMonth ++;
            if (currentMonth > 12){
                currentMonth = 1;
            }
            System.out.println("\t\t\t" + (currentMonth));
            begin = 1;
            updatedValues = monthAdd(currentMonth, currentDay, begin);
            begin = updatedValues[1];
            end = updatedValues[2];
            calendar(begin, end, currentMonth, currentDay);
            System.out.println("Month:" + currentMonth); //print the month and date as an integer
            System.out.println("Day:" + currentDay);}
            
            break;
            case 'p':
            if (currentMonth==0){
                System.out.println("Please enter \"e\" or \"t\" first");
            } else{
            currentMonth --;
            if (currentMonth < 1){
                currentMonth = 12;
            }
            System.out.println("\t\t\t" + (currentMonth));
            begin = 1;
            updatedValues = monthAdd(currentMonth, currentDay, begin);
            begin = updatedValues[1];
            end = updatedValues[2];
            calendar(begin, end, currentMonth, currentDay);
            System.out.println("Month:" + currentMonth); //print the month and date as an integer
            System.out.println("Day:" + currentDay);
        }
            break;
            case 'q':
            System.out.println("You have quit the program");
            rerun = false;
            break;
            default:
            System.out.println("Invalid Command. Please try again.");
        }
    }
    }
    //Instructions method
    public static void instructions(){
        System.out.println("Please type a command");
        System.out.println("\t\"e\" to enter a date and display the corresponding calendar");
        System.out.println("\t\"t\" to get todays date and display today's calendar");
        System.out.println("\t\"n\" to display the next month");
        System.out.println("\t\"p\" to display the previous month");
        System.out.println("\t\"q\" to quit the program");
        System.out.println("Please enter command below:");
        
    }
    //This method prints out the Calendar body itself taking in the input of the currentMonth and begin/end from other methods to determine where to start
    public static void calendar(int begin, int end, int currentMonth, int currentDay){
        int endCondition = end;
        days();
        //this for loop will print out the date, spacing and line methods until either the line counter or endcondition is met depending on the month. 
        for (int i = 1; i<=6 && endCondition < 31; i++){
            if(end > 28){
                if(currentMonth == 1 || currentMonth == 3 || currentMonth == 5 || currentMonth == 7 || currentMonth == 8 || currentMonth == 10){
                    end = 31;
                    endCondition = 31;
                } else if(currentMonth == 9){
                    end = 30;
                    endCondition = 30;
                } else if(currentMonth == 2){
                    end = 29;
                    endCondition = 31;
                } else if(currentMonth == 9){
                    endCondition = 30;
                } else if(currentMonth == 4 || currentMonth == 6 || currentMonth == 11){
                end = 30;
                endCondition = 31;} else if (currentMonth == 12){
                    end = 31;
                    endCondition = 30;
                }    
            }
            line();
            date(begin, end, currentDay);
            int remainingSpaces = 7 - (end - (begin + 1));
            if (remainingSpaces > 0) {
                for (int j = 2; j < remainingSpaces; j++) {
                    System.out.print("|      ");
                }
            }
            System.out.println("|");
            spacing();
            begin += 7;
            end += 7;
        }
        line();
        return;
    }
    //print out the days on top of the calendar
    public static void days(){
        System.out.println("\s\s\sMon\s\s\sTue\s\s\s\sWed\s\s\s\sThu\s\s\s\sFri\s\s\s\sSat\s\s\s\sSun");
    }
    //Prints out the line row separating the calendar rows
    public static void line(){ 
        for (int i = 1; i <= 50; i++) {   
            System.out.print('=');}
            System.out.println();
    }
    
    //this method prints the dates and the columns on the date line.
    public static void date(int begin, int end, int currentDay){ 
        for (int i = begin; i <= end && i <= begin + 6; i++){ 
            if (i >= 10 && i != currentDay){
            System.out.print("|" + i + "    ");
            } else if(i < 10 && i > 0 && i != currentDay) {
                System.out.print("|" + i + "     ");
            } else if (i == currentDay){
                System.out.print("|\033[1mToday\033[0m ");
            } else if (i<1){
                System.out.print("|      ");
            }
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
    //This method creates a cumulative addition to determine when the month starts based on which month it is assuming the year starts on Monday and assuming we have leap year.
    public static int[] monthAdd(int currentMonth, int currentDay, int begin){
        int cumulativeDays = 0;
        for (int i = 1; i < currentMonth; i++) {
            switch (i) {
                case 1: cumulativeDays += 31; break;
                case 2: cumulativeDays += 29; break; 
                case 3: cumulativeDays += 31; break;
                case 4: cumulativeDays += 30; break;
                case 5: cumulativeDays += 31; break;
                case 6: cumulativeDays += 30; break;
                case 7: cumulativeDays += 31; break;
                case 8: cumulativeDays += 31; break;
                case 9: cumulativeDays += 30; break;
                case 10: cumulativeDays += 31; break;
                case 11: cumulativeDays += 30; break;
            }
    }
        return new int[] {(cumulativeDays)%7, begin - (((cumulativeDays)%7)), (begin+6) - (((cumulativeDays)%7))}; //using the begin value and the remainder based on cumulative days to determine where the calendar starts.
    }
}