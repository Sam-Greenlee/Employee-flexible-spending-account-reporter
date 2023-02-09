//Programmer: Samuel Greenlee
//Program: Java03 Program Assignment
//Description: should receive information
//	about an employee’s request for funds from
//	their flexible spending accounts, and write
//	that information to a text file
//Date Created On: 3/31/2020

package Presentation;

import Business.*;

import db.*;

import java.util.List;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;


import java.util.Scanner;

public class Presentation
{
    private static DAO<Employee> EmployeeFile = new EmployeeTextFile();

    public static void main(String args[]) 
    {
    	Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the employee flexible spending account requester\n");
        displayMenu();

        // perform 1 or more actions
        String action = "";
        while (!action.equalsIgnoreCase("exit")) 
        {
            // get the input from the user
            System.out.print("Enter a command: ");
            action = sc.nextLine();
            System.out.println();

            if (action.equalsIgnoreCase("list")) 
            {
                displayAllEmployees();
            } 
            else if (action.equalsIgnoreCase("add")) 
            {
                addEmployee();
            } 
            else if (action.equalsIgnoreCase("del") || 
                       action.equalsIgnoreCase("delete")) 
            {
                deleteEmployee();
            } 
            else if (action.equalsIgnoreCase("help") || 
                       action.equalsIgnoreCase("menu")) 
            {
                displayMenu();
            } 
            else if (action.equalsIgnoreCase("exit")) 
            {
                System.out.println("Bye.\n");
            } 
            else 
            {
                System.out.println("Error! Not a valid command.\n");
            }
        }
    }
    
    //This method displays the menu
    public static void displayMenu() 
    {
        System.out.println("COMMAND MENU");
        System.out.println("list    - List all employees"); 
        System.out.println("add     - Add a employee");
        System.out.println("del     - Delete a employee");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }

    //This method displays all employees
    public static void displayAllEmployees() 
    {
        System.out.println("EMPLOYEE LIST");

        List<Employee> employees = EmployeeFile.getAll();
        StringBuilder sb = new StringBuilder();
        for (Employee e : employees) {
        	sb.append(e.getEmpID());
            sb.append(StringUtils.padWithSpaces(e.getFName(), 12));
            sb.append(StringUtils.padWithSpaces(e.getLName(), 12));
            sb.append(StringUtils.padWithSpaces(e.getEmail(), 40));
            sb.append(StringUtils.padWithSpaces(e.getTypeOfClaim(), 4));
            sb.append(e.getDateOfService());
            sb.append(e.getAmountRequested());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    //This method mainly adds an employee
    public static void addEmployee() 
    {
    	//This gets and validates the empID
    	 String empID = "";
    	 boolean errorCheck = false;
    	 Scanner sc = new Scanner(System.in);
		 int min = 1;
		 int max = 99999;
		 
		 do
		 {   
			 System.out.print("Employee ID Number: ");
			 empID = sc.nextLine();
			 errorCheck = Validation.isInteger(empID, "Employee ID Number")&&  
			     		  Validation.isWithinRangeInteger(empID, min, max,"Employee ID Number");
		 }while(!errorCheck);
		 
		 //This gets and validates the fName
		 String fName = "";
		 
		 do
		 {
			 System.out.print("Employee First Name: ");
			 fName= sc.nextLine();
			 errorCheck = Validation.isStringPresent(fName, "Employee First Name");
		 }while(!errorCheck);
		 
		 //This gets and validates the lName
		 String lName = "";
		 
		 do
		 {
			 System.out.print("Employee Last Name: ");
			 lName= sc.nextLine();
			 errorCheck = Validation.isStringPresent(lName, "Employee Last Name");
		 }while(!errorCheck);
		 
		 //This gets and validates the email
		 String email = "";
		 
		 do
		 {
			 System.out.print("Employee Email Address: ");
			 email= sc.nextLine();
			 errorCheck = Validation.isStringPresent(email, "Employee Email Address");
		 }while(!errorCheck);
		 
		 //This gets and validates the typeOfClaim
		 String typeOfClaim = "";
		 
		 do
		 {
			 System.out.print("Employee Type Of Claim (Either H or C): ");
			 typeOfClaim= sc.nextLine();
			 errorCheck = Validation.isStringPresent(typeOfClaim, "Employee Type Of Claim");

			 if(!typeOfClaim.equals("H") && !typeOfClaim.equals("C"))
			 {
				System.out.print("Please enter the correct type of claim please\n");
				errorCheck = false;
			 }
			 else errorCheck = true;
			 
			 
		 }while(!errorCheck);
		 
		 //This gets and validates the dateOfService
		 String dateOfService = "";
		 
		 do
		 {
			 System.out.print("Date Of Service: "); 			
			 dateOfService = sc.nextLine();
			 errorCheck = Validation.isDateValid(dateOfService); 
			 
			 
			 LocalDate parsedDate = LocalDate.parse(dateOfService);
			 if (ChronoUnit.DAYS.between(parsedDate, LocalDate.now()) > 60 || ChronoUnit.DAYS.between(parsedDate, LocalDate.now()) < 0)
			 {
				 System.out.print("Date must be within previous 60 days from today.\n");
				 errorCheck = false;
			 }
			 else errorCheck = true;
			 
			 
		 }while(!errorCheck);
		 
		 //This gets and validates the amountRequested
		 String amountRequested = "";
		 float minimum = 1.00f;
		 float maximum = 10000.00f;
		 
		 do
		 {   
				System.out.print("Amount Requested: ");
				amountRequested = sc.nextLine();
	  	 		errorCheck = Validation.isDouble(amountRequested, "Amount Requested")&&
			     		     Validation.isWithinRangeDouble(amountRequested, minimum, maximum,"Amount Requested");
			
		 }while(!errorCheck);
		 
		 //This adds all the needed data to the employees text file
		 Employee employee = new Employee();
	        
		 employee.setEmpID(Integer.parseInt(empID));
		 employee.setFName(fName);
		 employee.setLName(lName);
		 employee.setEmail(email);
		 employee.setTypeOfClaim(typeOfClaim);
		 employee.setDateOfService(LocalDate.parse(dateOfService));
		 employee.setAmountRequested(Float.parseFloat(amountRequested));
		 EmployeeFile.add(employee);

		 System.out.println(amountRequested
				 + " has been added.\n");
    }

    //This method deletes any employee the user needs to delete
    public static void deleteEmployee() 
    {
    	Scanner sc = new Scanner(System.in);
    	
        System.out.print("Enter Employee ID to delete: ");
        String empID= sc.nextLine();
        
        Employee e = EmployeeFile.get(Integer.parseInt(empID));
        
        if (e != null) 
        {
            EmployeeFile.delete(e);
            System.out.println(e.getAmountRequested()
                    + " has been deleted.\n");
        } 
        else 
        {
            System.out.println("No employee matches that code.\n");
        }
    }
}