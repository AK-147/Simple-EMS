/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ak144
 */

// FULL TIME EMPLOYEE

public class FTE extends EmployeeInfo {
    
    /**
     *
     */
    public double yearlySalary;
    
    /**
     *
     * @param eNum
     * @param fName
     * @param lName
     * @param gender
     * @param workLoc
     * @param deductRate
     * @param ySalary
     */
    public FTE(int eNum, String fName, String lName, String gender, int workLoc, double deductRate, double ySalary) {
        super(eNum, fName, lName, gender, workLoc, deductRate);
        yearlySalary = ySalary;
        
    }
    
    
    public double calcNetAnnualIncome() {
        
        double net = (1.0 - deductRate) * yearlySalary;
        return net;
        
    }
    
    public double getYearlySalary() {
    	return yearlySalary;
    }
    
}
