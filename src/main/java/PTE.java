/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ak144
 */
    
// PART TIME EMPLOYEE

public class PTE extends EmployeeInfo {
    
    
    public double hourlyWage;
    public double hoursPerWeek;
    public double weeksPerYear;
    
    
    public PTE(int eNum, String fName, String lName, String gender, int workLoc, double deductRate, double hourWage, double hoursPWeek, double weeksPYear) {
        super(eNum, fName, lName, gender, workLoc, deductRate);
        hourlyWage = hourWage;
        hoursPerWeek = hoursPWeek;
        weeksPerYear = weeksPYear;
        
    }
        
    
    public double calcNetAnnualIncome() {
        
        double net = (1.0 - deductRate) * hourlyWage * hoursPerWeek * weeksPerYear;
        return net;
        
    }
    
    public double getHourlyWage() {
    	return hourlyWage;
    }
    
    public double getHoursPerWeek() {
    	return hoursPerWeek;
    }
    
    public double getWeeksPerYear() {
    	return weeksPerYear;
    }
    
}
