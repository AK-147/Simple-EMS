import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyHashTable {
	
	// ATTRIBUTES

	// buckets is an array of ArrayList.  Each item in an ArrayList is an EmployeeInfo object.
	public ArrayList<EmployeeInfo>[] buckets;
	private int numInHashTable;

	// CONSTRUCTOR

	public MyHashTable(int howManyBuckets) {
		// Construct the hash table (open hashing/closed addressing) as an array of howManyBuckets ArrayLists.

		// Instantiate an array to have an ArrayList as each element of the array.

                buckets = new ArrayList[howManyBuckets];

		// For each element in the array, instantiate its ArrayList.
		for (int i = 0; i < howManyBuckets; i++) {
			buckets[i] = new ArrayList<EmployeeInfo>();  // Instantiate the ArrayList for bucket i.
		}
		
		numInHashTable = 0;
	}

	
	// METHODS
	
	public int getNumInHashTable() {
		return numInHashTable;
	}

	
	
	public int calcBucket(int keyValue) {
		// Returns the bucket number as the integer keyValue modulo the number of buckets for the hash table.
		return(keyValue % buckets.length);
	}
	

	
	public boolean addEmployee(EmployeeInfo theEmployee) {

		// Add the employee to the hash table.  Return true if employee is added successfully,
		// return false otherwise.
		
		if (theEmployee == null) {
			return(false);
		}
		else {
			int targetBucket = calcBucket(theEmployee.getEmpNum());
			// Add the employee to the ArrayList for the targetBucket.
			boolean addStatus = buckets[targetBucket].add(theEmployee);
			numInHashTable++;
			return addStatus;
		}
		
	} // end AddEmployee
	
	
	
        public EmployeeInfo searchEmployee(int employeeNum){
                // Determine the position of the employee in the ArrayList for the bucket that that employee hashes to.
                // If the employee is not found, return null.
		
		int targetBucket = calcBucket(employeeNum);
		
		// Walk through the ArrayList and look for the employee.
		for (int j = 0; j < buckets[targetBucket].size(); j++) {
			if (employeeNum == buckets[targetBucket].get(j).getEmpNum()) {
                            EmployeeInfo theEmployee = buckets[targetBucket].get(j);
                            return(theEmployee);
                        }
                }        
                return(null);
        }
                
                
                
	public int searchByEmployeeNumber(int employeeNum) {

		// Determine the position of the employee in the ArrayList for the bucket that that employee hashes to.
		// If the employee is not found, return -1.
		
		int targetBucket = calcBucket(employeeNum);
		
		// Walk through the ArrayList and look for the employee.
		for (int j = 0; j < buckets[targetBucket].size(); j++) {
			if (employeeNum == buckets[targetBucket].get(j).getEmpNum()) {
				return(j); // The employee number is for the employee at position j in the target bucket's ArrayList.
			}			
		}
		
		return(-1); // The employee number was not found for any employee in target bucket's ArrayList.
		
	} // end searchByEmployeeNumber
	
	
	
	public EmployeeInfo searchByFirstLastName(String fName, String lName) {

		// Search the entire hash table for the employee having the given first and last names.
		// If the employee is not found, return null, otherwise return the reference to the employee.
		
		for (int i = 0; i < buckets.length; i++) {
			for (int j = 0; j < buckets[i].size(); j++) {
				String theFName = buckets[i].get(j).getFirstName();
				String theLName = buckets[i].get(j).getLastName();
				if ((fName.equals(theFName)) && (lName.equals(theLName))) {
					EmployeeInfo theEmployee = buckets[i].get(j);
					return(theEmployee); // We've found the employee.
				}		
			}
		}
		return(null); // The employee was not found anywhere in the hash table.
		
	} // end searchByFirstLastName

	
	
	public EmployeeInfo removeEmployee(int employeeNum) {

		// Remove the employee from the hash table and return the reference to that employee.
		// If the employee is not in the hash table, return null.
		
		int targetBucket = calcBucket(employeeNum);
		int positionInArrayList = searchByEmployeeNumber(employeeNum);
	
		if (positionInArrayList < 0) {
			// Employee is not in the ArrayList for this target bucket.
			return(null);
		}
		else {
			EmployeeInfo employeeBeingRemoved = buckets[targetBucket].remove(positionInArrayList);
			numInHashTable--;
			return(employeeBeingRemoved);
		}
			
	} // end removeEmployee

	
	
	public void displayContents() {
		
		System.out.println("\n\nDISPLAYING THE CONTENTS OF A HASH TABLE!");
		
		System.out.println("\n  This hash table contains " + numInHashTable + " entries.");
		
		// Print the employee numbers for the employees stored in each bucket's ArrayList,
		// starting with bucket 0, then bucket 1, and so on.
		
		for (int i = 0; i < buckets.length; i++) {

		    // For the current bucket, print out the emp num for each item
                    // in its ArrayList.

                    System.out.println("\n  Examining the ArrayList for bucket " + i);
                    int listSize = buckets[i].size();
                    if (listSize == 0) {
                    	System.out.println("    Nothing in its ArrayList!");
                    }
                    else {
                        for (int j = 0; j < listSize; j++) {
                           int theEmpNum = buckets[i].get(j).getEmpNum();
                           System.out.println("    Employee " + theEmpNum);
                        }
                    }

                }

	} // end displayContents
        
        public void loadFile(String fileName){
            
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    buckets[i].clear();
                }        
            }
            
            try {
                File theFile = new File(fileName);
                Scanner fileScanner = new Scanner(theFile);
                
                while(fileScanner.hasNextLine()){
                    int eNumber = 0;
                    String fName = "";
                    String lName = "";
                    String gender = "";
                    double deductRate = 0.0;
                    double yearlySalary = 0.0;
                    double hourlyWage = 0.0;
                    double hoursPerWeek = 0.0;
                    double weeksPerYear = 0.0;
                    boolean isFTE = false;
                    
                    String data = fileScanner.nextLine();
                    List<String> theEmpsData = Arrays.asList(data.split(","));
                    System.out.println(theEmpsData);
                    
                    if (theEmpsData.size() == 6){
                        isFTE = true;
                    }
                    
                    int numLoopsDone = 0;
                    for(String readerPosition : theEmpsData){
                        numLoopsDone++;
                        if (numLoopsDone == 1){
                            eNumber = Integer.parseInt(readerPosition);
                        }
                        if (numLoopsDone == 2){
                            fName = readerPosition;
                        }
                        if (numLoopsDone == 3){
                            lName = readerPosition;
                        }
                        if (numLoopsDone == 4){
                            gender = readerPosition;
                        }
                        if (numLoopsDone == 5){
                            deductRate = Double.parseDouble(readerPosition);
                        }
                        if (numLoopsDone == 6){
                            if(isFTE == true){
                                yearlySalary = Double.parseDouble(readerPosition);
                            }
                            else{
                                hourlyWage = Double.parseDouble(readerPosition);
                            }
                        }
                        if (numLoopsDone == 7){
                            hoursPerWeek = Double.parseDouble(readerPosition);
                        }
                        if (numLoopsDone == 8){
                            weeksPerYear = Double.parseDouble(readerPosition);
                        }
                    }
                    if (isFTE == true){
                        FTE theFTE = new FTE(eNumber, fName, lName, gender, 0, deductRate, yearlySalary);
                        addEmployee(theFTE);
                    }
                    else{
                        PTE thePTE = new PTE(eNumber, fName, lName, gender, 0, deductRate, hourlyWage, hoursPerWeek, weeksPerYear);
                        addEmployee(thePTE);
                    }
                    
                }
                fileScanner.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MyHashTable.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
} // end MyHashTable
