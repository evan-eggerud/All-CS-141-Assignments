//Names: Evan, Cam, Patrick
//Assignment: Payroll
//Class: CIS 141
/*Purpose: Payroll system that asks for # of employees, names of employees, their pay type, and how much they make and outputs their gross and net pay */

import java.util.Scanner;
public class Payroll{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        //Assign variables
        int count = 1;
        System.out.print("Please enter the number of employees: ");
        int employeeCount = input.nextInt();
        

        while (count <= employeeCount){ //test
            System.out.println(count);
            
        System.out.println("Enter your first name: ");
        String firstName = input.next();
        System.out.println("Enter your last name: ");
        String lastName = input.next();
        System.out.println("Your full name is " + firstName + " " + lastName);
        
        
        System.out.printf("Which Salary type are you?\nType 1 for Salary, 2 for Hourly: ");
        int salaryType = input.nextInt();
        
            count++; //update the counter
            if(salaryType == 1) { //salary section

            System.out.println("What is your annual Salary?");
            double Salary = input.nextDouble();
            double Pay1 = Salary / 52;
            double PayTax1 = Pay1 * .80;
            System.out.printf("\nYour gross pay was $%.2f", Pay1);
            System.out.printf("\nYour net pay was $%.2f", PayTax1);
        }
        else if(salaryType == 2) { //hourly sections

            System.out.println("You selected Hourly pay type.");
            System.out.println("How many hours did you work this week? ");
            double Hours2 = input.nextDouble();
            System.out.println("What is your hourly pay rate?");
            double PayRate = input.nextDouble();
            double Pay2 = Hours2 * PayRate;
            double PayTax2 = Pay2 * .80;
                   
             if(Hours2 > 40){ //creating a section for working overtime
            System.out.println("You worked Overtime!");
            double Hours3 = Hours2-40;
            double PayRateOT = PayRate * 1.5;
            double OtHours = Hours3 * PayRateOT;
            double Pay3 = OtHours + (PayRate * 40);
            double PayTax3 = Pay3 * .80;
             System.out.println("You made $" + Pay3 + " this week in gross pay");
            System.out.println("nYour net pay was $" + PayTax3);
            
             }
             else { //for people who worked normal hours
                System.out.println("You made $" + Pay2 + " this week in gross pay");
            System.out.println("Your net pay was $" + PayTax2);}
            
        }
        else{
            System.out.println("Invalid pay type entered. Please try again.");
        }
            
    }


    }





}