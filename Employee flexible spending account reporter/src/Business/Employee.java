package Business;

import java.time.LocalDate;

public class Employee
{
	//These are the instance variables
	private int empID;
	private String fName;
	private String lName;
	private String email;
	private String typeOfClaim;
	private LocalDate dateOfService;
	private float amountRequested;
	
	//This is the constructor that sets each instance variable
	public Employee()
	{		
		empID = 0;
		fName = "";
		lName = "";
		email = "";
		typeOfClaim = "";
		dateOfService = LocalDate.now();
		amountRequested = 0.0f;
	}
	
	//This is the Constructor that accepts six arguments, and assigns those values into class variables
	public Employee(int empID, String fName, String lName, String email, LocalDate dateOfService,
			float amountRequested, String typeOfClaim)
	{
		this.empID = empID;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.typeOfClaim = typeOfClaim; 
		this.dateOfService = dateOfService;
		this.amountRequested = amountRequested;
	}

	//These are the get and set methods
	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTypeOfClaim() {
		return typeOfClaim;
	}

	public void setTypeOfClaim(String typeOfClaim) {
		this.typeOfClaim = typeOfClaim;
	}

	public LocalDate getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(LocalDate dateOfService) {
		this.dateOfService = dateOfService;
	}

	public float getAmountRequested() {
		return amountRequested;
	}

	public void setAmountRequested(float amountRequested) {
		this.amountRequested = amountRequested;
	}
}


